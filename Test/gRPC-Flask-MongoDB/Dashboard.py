import grpc
import statistics_pb2, statistics_pb2_grpc

if __name__ == "__main__":

    target = "localhost:8080"
    with grpc.insecure_channel(target) as channel:
        
        stub = statistics_pb2_grpc.StatisticsStub(channel)

        print("[Dashboard] Ottengo dati dei sensori...")

        for sensor in stub.getSensors(statistics_pb2.Empty()):
            
            sensor_id = sensor.id
            data_type = sensor.data_type

            print(f"[Dashboard] Sensore - id:{sensor_id}, data_type={data_type}")

            response = stub.getMean(statistics_pb2.MeanRequest(sensor_id=sensor_id, data_type=data_type))
            media = response.data
            print("[Dashboard] Media delle misurazioni: " + media)

