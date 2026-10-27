import checker_pb2, checker_pb2_grpc
import grpc
from random import randint

NUM_RICHIESTA = 5
NUM_VALORI_TEMP = 5

def generate_temp_data():

    for j in range(NUM_VALORI_TEMP):

        # Costruisco messaggio
        temp_data = randint(50, 100)
        print("[Sensor] Data_type: " + str(temp_data))
        yield checker_pb2.Temp(temperatura=temp_data)

if __name__ == "__main__":

    target = "localhost:8080"

    with grpc.insecure_channel(target) as channel:
        stub = checker_pb2_grpc.CheckerStub(channel)

        for i in range(NUM_RICHIESTA):

            print("[Sensor] Invio stream di data_temp n. " + str(i+1))

            # Invio lo stream di data_temp
            response = stub.stream_temp(generate_temp_data())
            print("[Sensor] Response: " + response.messge)

            # Invio richiesta di media
            response = stub.get_average(checker_pb2.Empty())
            print("[Sensor] Media: " + str(response.media))



