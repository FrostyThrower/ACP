import product_manager_pb2, product_manager_pb2_grpc
import grpc
import threading
from random import randint

NUM_THREADS = 10


def thread_fun(stub, id):

    method = randint(0, 1)
    if method == 0:
        #Sell
        serial_numaber = randint(1, 100)
        print(f"[User-{id} SELL] Invio richiesta - " + str(serial_numaber))
        response = stub.sell(product_manager_pb2.SerialNumber(int_value=serial_numaber))
        if response.bool_value == True:
            print(f"[User-{id} SELL] Ricevuta risposta: ack ")
        else:
            print(f"[User-{id} SELL] Ricevuta risposta: nack")
    else:
        #Buy
        print(f"[User-{id} BUY] Invio richiesta")
        response = stub.buy(product_manager_pb2.Empty())
        print(f"[User-{id} BUY] Ricevuta risposta - " + str(response.int_value))


if __name__ == "__main__":

    target = "localhost:8080"
    threads = []
    with grpc.insecure_channel(target) as channel:

        stub = product_manager_pb2_grpc.ProductManagerStub(channel)

        for i in range(NUM_THREADS):
            th = threading.Thread(target=thread_fun, args=(stub, str(i+1)))
            th.start()
            threads.append(th)

        for t in threads:
            t.join()
            