from IMagazzino import IMagazzino
from abc import ABC, abstractmethod
import socket
from threading import Thread, current_thread

MAX_BUFF = 1024

class SkeletonServer(IMagazzino, ABC):

    @abstractmethod
    def deposita():
        pass

    @abstractmethod
    def preleva():
        pass

    def run_skeleton(self):

        host = 'localhost'
        port = 0

        thread_worker = []
        i = 0

        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.bind((host, port))

        s.listen(40)

        cur_port = s.getsockname()[1]
        print("[Server] Listening on port ", cur_port, ":", s.getsockname()[0])

        for i in range(30):

            c, addr = s.accept()

            th = Thread(name=f"Thread-{i}", target=func_thread, args=(c, self, 1024))
            th.start()

        for t in thread_worker:
            t.join()
        
        print('[Server] Closing socket...')
        s.close()


def func_thread(conn, ref, buff_size):

    data = conn.recv(buff_size)
    message = str(data.decode('utf-8'))

    print(f"[S-{current_thread().name}] Received {message}")

    method = int(message.split(';')[0])
    articolo = int(message.split(';')[1])

    if(method == 0): # preleva
                
        result_id = ref.preleva(articolo)
        conn.send(str(result_id).encode('utf-8'))

    elif(method == 1): # deposita
        
        id = int(message.split(';')[2])
        ref.deposita(articolo, id)

    else:
        print("[Server] ERRORE: Valore del metodo non supportato")

    conn.close()