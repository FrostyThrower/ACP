import product_manager_pb2, product_manager_pb2_grpc
import grpc
from concurrent import futures
import threading
import requests

MAX_SIZE = 5

class ProductManager(product_manager_pb2_grpc.ProductManagerServicer):

    def __init__(self):

        self.queue = []
        self.lock = threading.Lock()
        self.cv_buy = threading.Condition(self.lock)
        self.cv_sell = threading.Condition(self.lock)

    def send_to_historyServer(self, method, serial_number):

        data_to_send = {"operation" : method, "serial_number" : serial_number}
        response = requests.post(url="http://127.0.0.1:5000/update_history", json=data_to_send).json()
        print("[ProductManager] Aggiornamento dell'history effettuata con esito - " + response["result"])
        if response["result"] == "success":
            return True
        else:
            return False

    def sell(self, request, context):
        print("[Product Manager] Ricevuta SELL - " + str(request.int_value))

        with self.cv_sell:
            while len(self.queue) == MAX_SIZE:
                self.cv_sell.wait()
            self.queue.append(request.int_value)
            self.cv_buy.notify()

        result = self.send_to_historyServer("sell", request.int_value)

        return product_manager_pb2.Response(bool_value=result)

    def buy(self, request, context):

        print("[Product Manager] Ricevuta BUY")
        
        laptop_id = None

        with self.cv_buy:
            while len(self.queue) == 0:
                self.cv_buy.wait()
            laptop_id = self.queue.pop(0)
            self.cv_sell.notify()

        self.send_to_historyServer("buy", laptop_id)

        return product_manager_pb2.SerialNumber(int_value=laptop_id)


if __name__ == "__main__":

    port = 8080

    server = grpc.server(futures.ThreadPoolExecutor(max_workers=5))
    product_manager_pb2_grpc.add_ProductManagerServicer_to_server(ProductManager(), server)

    server.add_insecure_port("localhost:"+str(port)) 
    server.start()

    print("[Product Manager] Server attivo sulla porta 8080...")
    server.wait_for_termination()   