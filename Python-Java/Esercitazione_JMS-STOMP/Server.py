from ServerSkeleton import ServerSkeleton
import multiprocess 

MAX_SIZE = 5

class Server(ServerSkeleton):

    def __init__(self):
        self.queue = multiprocess.Queue(MAX_SIZE)

    def deposita(self, id_articolo):

        print("[Server] Ricevuta richiesta di deposito")
        
        self.queue.put(id_articolo)

        return "deposited"
        

    def preleva(self):

        print("[Server] Ricevuta richiesta di prelievo")

        return self.queue.get()

if __name__ == "__main__":

    server = Server()
    server.run_skeleton()