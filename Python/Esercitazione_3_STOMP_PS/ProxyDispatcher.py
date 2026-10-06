import IServer, socket

BUFF_SIZE = 1024

class ProxyDispatcher(IServer.IServer):

    def __init__(self, port):

        self.port = int(port)
        

    def deposita(self, data):

        address = ("localhost", self.port)

        # Apro socket TCP
        self.s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.s.connect(address)

        message = "deposita;" + str(data)

        self.s.send(message.encode("utf-8"))

        response = self.s.recv(BUFF_SIZE)

        self.s.close()

    
    def preleva(self):

        address = ("localhost", self.port)

        # Apro socket TCP
        self.s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.s.connect(address)

        message = "preleva"

        self.s.send(message.encode("utf-8"))

        response = self.s.recv(BUFF_SIZE)

        self.s.close()

        return response.decode("utf-8")
        