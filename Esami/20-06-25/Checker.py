import checker_pb2, checker_pb2_grpc
import grpc
from concurrent import futures
import multiprocess
import stomp

class Checker(checker_pb2_grpc.CheckerServicer):

    def __init__(self, queue_size, soglia, conn):
        self.queue = multiprocess.Queue(maxsize=queue_size)
        self.lock = multiprocess.Lock()
        self.conn = conn
        self.soglia = soglia

    def stream_temp(self, request_iterator, context):

        print("[Checker] Richiesta stream_temp...")

        result = ""

        for request in request_iterator:

            data = int(request.temperatura)
            print("[Checker] Temp_data: " + str(data))
            with self.lock:
                self.queue.put(data)

            if data > self.soglia:
                self.conn.send("/topic/alert", str(data))
                result = "ALERT"

            else:
                result = "NORMAL"

        return checker_pb2.StringMessage(messge=result)

    def get_average(self, request, context):

        print("[Checker] Ricevuta richiesta di media...")

        sum = 0
        count_data = 0
        with self.lock:
            for i in range(self.queue.qsize()):
                sum += self.queue.get()
                count_data = i+1
        
        media = sum / count_data
        print("[Checker] Media calcolata:" + str(media))
        return checker_pb2.ReplyMedia(media=media)


if __name__ == "__main__":

    soglia = int(input("[Checker] Inserire soglia max temp_data: "))

    conn = stomp.Connection([("localhost", 61613)])
    conn.connect(wait=True)
    conn.subscribe("/topic/alert", id=1, ack="auto")

    server = grpc.server(futures.ThreadPoolExecutor(max_workers=5))
    checker_pb2_grpc.add_CheckerServicer_to_server(Checker(5, soglia, conn), server)
    server.add_insecure_port("[::]:8080")
    server.start()

    print("[Checker] Ascoltando sulla porta 8080...")

    server.wait_for_termination()
    conn.disconnect()