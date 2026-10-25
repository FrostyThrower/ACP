from abc import ABC, abstractmethod
import socket
import multiprocess

BUFF_SIZE = 20000

class ServerSkeleton(ABC):

    @abstractmethod
    def deposita(self, id_articolo: int) -> str:
        pass

    @abstractmethod
    def preleva(self) -> int:
        pass

    def _thread(self, conn):

        data = conn.recv(BUFF_SIZE)
        msg = str(data.decode('utf-8'))

        method = msg.split("#")[0]
        try:
            id_articolo = msg.split("#")[1]    
        except IndexError as e:
            pass

        if method == "deposita":
            response = self.deposita(int(id_articolo))

        else:
            response = self.preleva()

        print(response)

        conn.send(str(response).encode('utf-8'))
        conn.close()
        

    def run_skeleton(self):

        port = 8080

        soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        soc.bind(("localhost", port))

        soc.listen(10)
        print("[Server] Listen on port 8080...")

        try:
            while(True):
                conn, addr = soc.accept()

                th = multiprocess.Process(target=self._thread, args=(conn,))
                th.start()
        except BaseException as e:
            print(e)
        finally:
            soc.close()



