import genModelSelector_pb2, genModelSelector_pb2_grpc
import WorkerProxy
import grpc
from concurrent import futures
import threading

class GenModelSelector (genModelSelector_pb2_grpc.GenModelSelectorServicer):
    
    def __init__(self):

        genModelSelector_pb2_grpc.add_GenModelSelectorServicer_to_server.__init__()
        self.worker = WorkerProxy.WorkerProxy()
        

    def submitGenTask(self, request, context):

        print(f"[GenModelSelector-{threading.current_thread().name}] TaskID:{request.taskId}, TaskType:{request.taskType}, NumPrompts: {request.numPrompt}")


        taskId = request.taskId
        taskType = request.taskType
        numPrompts = request.numPrompt

        prompts = []
        for prompt in request.prompts:
            prompts.append(prompt)

        prompts = request.prompts

        modello = ""

        if taskType == "codeGen":
            modello = "CodeT5"
        elif taskType == "textGen":
            modello = "T5"
        else:
            print(f"[GenModelSelector-{threading.current_thread().name}] Error: unknown tasktype")
            return


        for prompt in prompts:

            result = self.worker.runTask(prompt, modello)
            
            if result is False:
                yield genModelSelector_pb2.Result(confidenceCheck=result)

            result = self.worker.getConfidence()

            yield genModelSelector_pb2.Result(confidenceCheck=result)
        

if __name__ == "__main__":

    server = grpc.server(futures.ThreadPoolExecutor(max_workers=2))
    genModelSelector_pb2_grpc.add_GenModelSelectorServicer_to_server(GenModelSelector(), server)
    server.add_insecure_port("[::]:" + "8082")
    server.start()

    print("[GenModelSelector] Server gRPC running on port 8082...")

    server.wait_for_termination()
