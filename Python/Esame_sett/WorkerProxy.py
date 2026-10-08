from IWorker import IWorker
import socket

BUFFER_SIZE = 65508

class WorkerProxy(IWorker):
    
    def __init__(self):
        self.server = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self.addr = ("localhost", 8080)


    def runTask(self, prompt, modello):
        
        message = str("runTask#" + prompt + "#" + modello)
        self.server.sendto(message.encode("utf-8"), self.addr)
        data, addr = self.server.recvfrom(BUFFER_SIZE)
        if data.decode('utf-8') == "1":
            return True
        else:
            return False

    def getConfidence(self):
        
        message = "getConfidence"
        self.server.sendto(message.encode("utf-8"), self.addr)
        data, addr = self.server.recvfrom(BUFFER_SIZE)
        if data.decode('utf-8') == "1":
            return True
        else:
            return False