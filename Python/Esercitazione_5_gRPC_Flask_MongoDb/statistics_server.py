import statistics_pb2, statistics_pb2_grpc
from database_controller import Database_controller
import json
import grpc
from concurrent import futures

class StatisticsService(statistics_pb2_grpc.StatisticsServicer):

    def __init__(self):
        statistics_pb2_grpc.StatisticsServicer.__init__(self)
        Database_controller.init()

    def getSensors(self, request, context):
        
        for sensor in Database_controller.get_sensors():
            yield statistics_pb2.Sensor(sensor_id=sensor['id'], data_type=sensor['data_type'])


    def getMean(self, request, context):
        
        sensor_id = request.sensor_id
        data_type = request.data_type

        result = Database_controller.get_data(sensor_id, data_type)

        somma = 0

        for data in result:
            somma = somma + int(data['data'])
        
        media = somma / len(result)

        return statistics_pb2.StringMessage(message=str(media))


if __name__ == "__main__":

    port = 5001
    
    server = grpc.server(concurrent.futures.ThreadPoolExecutor(max_workers=2))
    statistics_pb2_grpc.add_StatisticsServicer_to_server(StatisticsService(), server)

    server.add_insecure_port("[::]:" + str(port))
    server.start()

    print("[Stati-server] Listening on port 5001...\n")

    server.wait_for_termination()