from IMagazzino import IMagazzino
import socket

BUFF_SIZE = 1024

class ProxyClient(IMagazzino):

    def __init__(self, port):

        self.port = port


    def deposita(self, articolo, id):

        host = 'localhost'

        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

        s.connect((host, self.port))

        message = "1;" + str(articolo) + ";" + str(id)

        s.send(message.encode('utf-8'))
        
        data = s.recv(BUFF_SIZE)

        s.close()
            

    def preleva(self, articolo):

        host = 'localhost'

        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect((host, self.port))

        message = "0;" + str(articolo)
        s.send(message.encode('utf-8'))
        
        data = s.recv(BUFF_SIZE)

        s.close()

        return data.decode('utf-8')    