from IWorker import IWorker
import socket
import threading

BUFFER_SIZE = 65508

class WorkerSkeleton():

    def __init__(self, worker):
        
        self.port = 8080

        self.server = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self.server.bind(("localhost", self.port))
        print("[Worker] Listening on port 8080...")

        self.worker = worker


    def _workerThread(self, msg, addr):


        message = msg.decode("utf-8")

        method = message.split("#")[0]

        if method == "runTask":
            
            prompt = message.split("#")[1]
            modello = message.split("#")[2]

            result = self.worker.runTask(prompt, modello)

            print("[Worker] Request runTask - result " + str(result))

            if result is True:
                result = "1"
            else:
                result = "0"

            self.server.sendto(result.encode('utf-8'), addr)

        elif method == "getConfidence":

            result = self.worker.getConfidence()

            print("[Worker] Request getConfidence - result " + str(result))

            if result is True:
                result = "1"
            else:
                result = "0"

            self.server.sendto(result.encode('utf-8'), addr)
            
           
        else:
            print("[Worker] Error : Unknown method!")


    def runSkeleton(self):

        while(True):

            msg, addr = self.server.recvfrom(BUFFER_SIZE)

            th = threading.Thread(target=self._workerThread, args=(msg, addr))
            th.start()

