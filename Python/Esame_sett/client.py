import threading
from random import randint
import genModelSelector_pb2, genModelSelector_pb2_grpc, grpc

NUM_THREADS = 5
PROMPTS = ["Questo è il primo prompt", "C'era una volta, non molto lontano da qui...", "Generami un sito web HTML", "Oh no, mi sono cagato nel puzzo (deh pefforza)"]

def thread_fun(id):

    taskId = f"task-{id}"
    taskType = randint(0, 1)
    if taskType == 1:
        taskType = "codeGen"
    else:
        taskType = "textGen"
    
    numPrompt = randint(1, 5)
    prompts = []
    for i in range(numPrompt):
        prompts.append(PROMPTS[randint(0, 3)])

    with grpc.insecure_channel("localhost:8082") as channel:

        stub = genModelSelector_pb2_grpc.GenModelSelectorStub(channel)

        print(f"[Client-{threading.current_thread().name}] Request - TaskId:{taskId}, TaskType:{taskType}, prompts:{prompts}, numPrompt:{numPrompt}")

        for response in stub.submitGenTask(genModelSelector_pb2.Request(taskId=taskId, taskType=taskType, prompts=prompts, numPrompt=numPrompt)):
            print(f"[Client-{threading.current_thread().name}] TaskId: {taskId} - Reply: {response.confidenceCheck}")




if __name__ == "__main__":

    threads = []

    for i in range(NUM_THREADS):
        t = threading.Thread(target=thread_fun, args=(i,))
        t.start()
        threads.append(t)

    for thread in threads:
        thread.join()
    