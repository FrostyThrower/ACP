import statistics_pb2, statistics_pb2_grpc, grpc


if __name__ == "__main__":

    server = "localhost:5001"

    with grpc.insecure_channel(server) as channel:

        stub = statistics_pb2_grpc.StatisticsStub(channel)

        for sensor in stub.getSensors(statistics_pb2.Empty()):
            
            sensor_id = sensor.sensor_id
            data_type = sensor.data_type

            response = stub.getMean(statistics_pb2.MeanRequest(sensor_id=str(sensor_id), data_type=str(data_type)))

            print(f"[Dashoboard] Sensor_id:{sensor_id} ({data_type}) - Media: {response.message}")

