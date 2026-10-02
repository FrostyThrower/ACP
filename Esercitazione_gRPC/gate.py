import threading, random, sys, time
import grpc
import sorting_pb2_grpc, sorting_pb2

MAX_THREAD = 3
DESTINATION = ["NA", "RM", "MI"]

class Gate_thread(threading.Thread):

    count = 0
    lock_count = threading.Lock()

    def __init__(self, port_server):

        threading.Thread.__init__(self)

        self.server = "localhost:" + str(port_server)

    
    def run(self):

        name = threading.current_thread().name

        for i in range(5):
        
            id = 0
            with Gate_thread.lock_count:
                id = Gate_thread.count
                Gate_thread.count = Gate_thread.count + 1      

            priority = random.randint(0, 1)
            if priority == 0:
                priority = "standard"
            elif priority == 1:
                priority = "express"


            destination = DESTINATION[random.randint(0,2)]

            print(f"[{name}] Inserimento pacco {id}, {priority} e {destination}")

            with grpc.insecure_channel(self.server) as channel:
                
                stub = sorting_pb2_grpc.SorterStub(channel)

                # Necessario in quanto si simula anche l'invio di priority sbagliata
                try: 
                    response = stub.deposit(sorting_pb2.Package(id=id, priority=priority, destination=destination))
                    if response.result == "deposited":
                        print(f"[{name}] Deposit success!")
                
                except grpc.RpcError as e:
                    if e.code() == grpc.StatusCode.INVALID_ARGUMENT:
                        print(f"[{name}] Priority sbagliata...")

            time.sleep(random.randint(1,3))


if __name__ == "__main__":

    try:
        port_server = sys.argv[1]
    except:
        print("Insert port server")
        sys.exit(1)

    thread = []


    for i in range(MAX_THREAD):

        t = Gate_thread(port_server)
        t.start()

        thread.append(t)

    for t in thread:

        t.join()
