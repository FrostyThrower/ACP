import statistics_pb2_grpc, statistics_pb2
import grpc
from concurrent import futures
from DbController import DbController


class Statistics(statistics_pb2_grpc.StatisticsServicer):

    def __init__(self):
        self.db = DbController("statistics")

    def getSensors(self, request, context):

        print("[Statistics] Ricevuta getSensors")

        result = self.db.getSensors()

        for sensor in result:
            yield statistics_pb2.Sensor(id=sensor["_id"], data_type=sensor["data_type"])

    def getMean(self, request, context):

        print("[Statistics] Ricevuta getMean")

        sensor_id = request.sensor_id
        data_type = request.data_type

        data_misur = self.db.getDataById(data_type, sensor_id)

        sum = 0
        for misur in data_misur:
            sum += misur["data"]
        
        media = sum / len(data_misur)
        return statistics_pb2.StringMessage(data=str(media))


if __name__ == "__main__":

    server = grpc.server(futures.ThreadPoolExecutor(max_workers=5))
    statistics_pb2_grpc.add_StatisticsServicer_to_server(Statistics(), server)

    server.add_insecure_port("[::]:8080")
    server.start()

    print("[Statistics] Avviato server su porta 8080...")

    server.wait_for_termination()
