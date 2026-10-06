import sorting_pb2_grpc, sorting_pb2
import grpc
import threading, time, concurrent

MAX_DIM = 5
MAX_STATUS_MESS = 10

class Sorter(sorting_pb2_grpc.SorterServicer):

    def __init__(self):
        self.queue_standard = []
        self.queue_express = []

        self.lock_standard = threading.Lock()
        self.lock_express = threading.Lock()

        self.cv_prod_standard = threading.Condition(self.lock_standard)
        self.cv_cons_standard = threading.Condition(self.lock_standard)

        self.cv_prod_express = threading.Condition(self.lock_express)
        self.cv_cons_express = threading.Condition(self.lock_express)
        
        self.path_sorted_standard = "sorted_standard.txt"

        with open(self.path_sorted_standard, 'w') as txt:

            txt.truncate(0)

        self.path_sorted_express = "sorted_express.txt"

        with open(self.path_sorted_express, 'w') as txt:
            
            txt.truncate(0)

        self.sorted_lock = threading.Lock()
        self.id_sorted = 0



    def deposit(self, request, context):

        print(f"[{threading.current_thread().name}] deposit id={request.id}, priority={request.priority}")
        
        if request.priority == "standard":

            with self.cv_prod_standard:

                while len(self.queue_standard) == MAX_DIM:
                    self.cv_prod_standard.wait()

                self.queue_standard.append([request.id, request.destination])

                self.cv_cons_standard.notify()

                print(f"[{threading.current_thread().name}] Queue_standard {len(self.queue_standard)}")

        elif request.priority == "express":
            
            with self.cv_prod_express:

                while len(self.queue_express) == MAX_DIM:
                    self.cv_prod_express.wait()

                self.queue_express.append([request.id, request.destination])

                self.cv_cons_express.notify()
        
                print(f"[{threading.current_thread().name}] Queue_express {len(self.queue_express)}")
        
        else:

            context.abort(grpc.StatusCode.INVALID_ARGUMENT, "priority not valid")            

        print(f"[{threading.current_thread().name}] deposit success!")
        return sorting_pb2.Ack(result= "deposited")
    
    def collect(self, request, context):

        print(f"[{threading.current_thread().name}] collect")
        
        if request.priority == "standard":
            
            with self.cv_cons_standard:

                while len(self.queue_standard) == 0:
                    self.cv_cons_standard.wait(timeout=5)
                
                result = self.queue_standard.pop(0)

                self.cv_prod_standard.notify()

            with open(self.path_sorted_standard, 'a') as txt:
                with self.sorted_lock:
                    self.id_sorted = self.id_sorted + 1
                txt.write(str(result[0]) + " - " + str(request.courier_id)+"\n")

            return sorting_pb2.Package(id=result[0], priority="standard", destination=result[1])

        elif request.priority == "express":

            with self.cv_cons_express:
                while len(self.queue_express) == 0:
                    self.cv_cons_express.wait(timeout=5)
                
                result = self.queue_express.pop(0)
            
                self.cv_prod_express.notify()

            with open(self.path_sorted_express, 'a') as txt:
                with self.sorted_lock:            
                    self.id_sorted = self.id_sorted + 1
                txt.write(str(result[0]) + " - " + str(request.courier_id)+"\n")

            return sorting_pb2.Package(id=result[0], priority="express", destination=result[1])
        
        else:
            context.abort(grpc.StatusCode.INVALID_ARGUMENT, "priority not valid")            

    def watchStatus(self, request, context):

        print(f"[{threading.current_thread().name}] watchStatus")

        for i in range(MAX_STATUS_MESS):

            time.sleep(2)
            yield sorting_pb2.Status(standard_size=len(self.queue_standard), express_size=len(self.queue_express), total_sorted=self.id_sorted)
    
    def listPackage(self, request, context):

        print(f"[{threading.current_thread().name}] listPackage")


        with self.lock_standard:

            for p in self.queue_standard:

                if(p[1] == request.destination):
                    yield sorting_pb2.Package(id=p[0], priority="standard", destination=p[1])

        with self.lock_express:

            for p in self.queue_express:

                if(p[1] == request.destination):
                    yield sorting_pb2.Package(id=p[0], priority="express", destination=p[1])   


def server():

    port = "50051"
    server = grpc.server(concurrent.futures.ThreadPoolExecutor(max_workers=4))

    sorting_pb2_grpc.add_SorterServicer_to_server(Sorter(), server)

    server.add_insecure_port("[::]:" + port)

    server.start()
    print("Server started, listining on " + port)

    server.wait_for_termination()

if __name__ == "__main__":
    server()