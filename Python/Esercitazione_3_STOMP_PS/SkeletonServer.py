from IServer import IServer
from abc import ABC, abstractmethod
import socket
import threading


WHO_I_AM = "[Server"
BUFF_SIZE = 1024

class SkeletonServer(IServer, ABC):

    @abstractmethod
    def deposita(self):
        pass


    @abstractmethod
    def preleva(self):
        pass

    def _func_thread(self, c, addr):

        print(WHO_I_AM + f"-{threading.current_thread().name}] Accept connection from {addr}")

        data = c.recv(BUFF_SIZE)

        message = str(data.decode("utf-8"))

        method = message.split(";")[0]

        if method == "deposita": # deposita

            id = message.split(";")[1]
            print(WHO_I_AM + f"-{threading.current_thread().name}] Received deposit request (id={id})")
            
            result = self.deposita(id)

            if result:
                print(WHO_I_AM + f"-{threading.current_thread().name}] Deposit success!")
            else:
                print(WHO_I_AM + f"-{threading.current_thread().name}] Deposit failed")

        elif method == "preleva": # preleva
            
            print(WHO_I_AM + f"-{threading.current_thread().name}] Received retrieve request")

            result = self.preleva()

            c.send(result.encode("utf-8"))

            print(WHO_I_AM + f"-{threading.current_thread().name}] Send messages {result}")

        else:
            print(WHO_I_AM + f"-{threading.current_thread().name}] ERROR: unknown method")

        c.close()



    def run_skeleton(self):

        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.bind(("localhost", 0))

        s.listen(5)

        cur_port = s.getsockname()[1]

        print(WHO_I_AM + f"] Server listening on port {cur_port} ...")

        threads = []

        for i in range(10):

            c, addr = s.accept()

            th = threading.Thread(target=self._func_thread, args=(c, addr), name=f"Thread-{i}")
            th.start()
            threads.append(th)

        for t in threads:
            t.join()


        print(WHO_I_AM + "] Closing socket...")
        s.close()