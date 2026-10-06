import sys, time
import threading
import sorting_pb2, sorting_pb2_grpc, grpc

from random import randint
from concurrent import futures

NUM_WORKER = 3
NUM_COLLECT = 4

def courier_func(port_server, id):

    name = threading.current_thread().name

    print(f"[{name}] Avvio...")

    server = "localhost:"+str(port_server)

    for i in range(NUM_COLLECT):

        priority = randint(0, 1)
        if priority == 0:
            priority = "standard"
        else:
            priority = "express"
        
        print(f"[{name}] Request package {priority}:{id}")

        with grpc.insecure_channel(server) as channel:

            stub = sorting_pb2_grpc.SorterStub(channel)


            response = stub.collect(sorting_pb2.CollectRequest(priority=priority, courier_id=str(id)))
            print(f"[{name}] Collect package {response.id}, {response.priority}, {response.destination}")
        
        time.sleep(randint(2, 4))

    print(f"[{name}] End thread...")



if __name__ == "__main__":

    try:
        port_server = sys.argv[1]
    except:
        print("Insert port server")
        sys.exit(1)

    thread_worker = []

    for i in range(NUM_WORKER):
        th = threading.Thread(target=courier_func, args=(port_server, i), name="Thread-"+str(i))
        th.start()

        thread_worker.append(th)

    for t in thread_worker:
        t.join()
    
    print("[COURIER] End courier...")


