import time, random
from concurrent import futures
import grpc
import meteo_pb2, meteo_pb2_grpc

class StazioneMeteoServicer(meteo_pb2_grpc.StazioneMeteoServicer):

    def RilevaTemperatura(self, request, context):

        lett_temp = random.randint(-5, 40)
        timestamp = int(time.time())

        return meteo_pb2.RilevazioneReply(citta=request.citta, temperatura_c=lett_temp, timestamp_unix=timestamp)

    def Monitora(self, request, context):

        for i in range(request.campioni):

            yield self.RilevaTemperatura(request, context)
            time.sleep(1)

def serve():

    port = "50051"

    server = grpc.server(futures.ThreadPoolExecutor(max_workers=5))

    meteo_pb2_grpc.add_StazioneMeteoServicer_to_server(StazioneMeteoServicer(), server)
    server.add_insecure_port("[::]:" + port)
    server.start()

    print("[Server] Listening on port " + port)
    server.wait_for_termination()

if __name__ == "__main__":
    serve()