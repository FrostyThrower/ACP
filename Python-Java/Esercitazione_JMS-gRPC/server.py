import grpc
from concurrent import futures
import threading
import server_pb2, server_pb2_grpc

MAX_SIZE = 5

class Server(server_pb2_grpc.ServerServicer):

    def __init__(self):

        self.queue = []
        self.lock = threading.Lock()
        self.cv_dep = threading.Condition(self.lock)
        self.cv_pre = threading.Condition(self.lock)

    def Preleva(self, request, context):

        print("[Server] Richiesta preleva")
        
        result = None

        with self.cv_pre:
            while len(self.queue) == 0:
                self.cv_pre.wait()
            result = self.queue.pop(0)
            self.cv_dep.notify()
        
        return server_pb2.Reply(response=str(result))
            

    def Deposita(self, request, context):

        print("[Server] Richiesta deposito")

        with self.cv_dep:
            while len(self.queue) == MAX_SIZE:
                self.cv_dep.wait()
            self.queue.append(request.id_articolo)
            self.cv_pre.notify()
              
        return server_pb2.Reply(response="deposited")


if __name__ == "__main__":

    port = "8080"

    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    server_pb2_grpc.add_ServerServicer_to_server(Server(), server)

    server.add_insecure_port("localhost:" + port)
    server.start()
    print("[Server] Listening on port 8080...")
    server.wait_for_termination()


    
