import sys, random
import grpc
import sorting_pb2, sorting_pb2_grpc

DESTINATION = ["NA", "RM", "MI"]

if __name__ == "__main__":

    try:
        port_server = sys.argv[1]
    except:
        print("Insert server port")
        sys.exit(1)


    print(f"[Monitor] Starting...")

    server = "localhost:"+str(port_server)


    with grpc.insecure_channel(server) as channel:
        stub = sorting_pb2_grpc.SorterStub(channel)

        for response in stub.watchStatus(sorting_pb2.Empty()):
            print(f"[Monitor] Num packages standard: {response.standard_size}, Num packages express: {response.express_size}, Num sorted packages: {response.total_sorted}")

    destination = DESTINATION[random.randint(0, 2)]
    with grpc.insecure_channel(server) as channel:
        stub = sorting_pb2_grpc.SorterStub(channel)

        for response in stub.listPackage(sorting_pb2.DestinationRequest(destination=destination)):
            print(f"[Monitor] Package {response.id}, {response.priority}, {response.destination}")

    print(f"[Monitor] Ending...")