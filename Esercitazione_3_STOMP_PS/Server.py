from SkeletonServer import SkeletonServer
import threading

WHO_I_AM = "[Server"

QUEUE_SIZE = 5


class Server(SkeletonServer):

    def __init__(self):

        self.queue = []
        self.lock = threading.Lock()
        self.cv_prod = threading.Condition(self.lock)
        self.cv_cons = threading.Condition(self.lock)

    def deposita(self, id) -> bool:

        with self.cv_prod:
            while len(self.queue) == QUEUE_SIZE:
                self.cv_prod.wait()
            
            self.queue.append(id)

            self.cv_cons.notify()

            return True
        
        return False


    def preleva(self) -> str:

        result = ""

        with self.cv_cons:
            while len(self.queue) == 0:
                self.cv_cons.wait()
            
            result = self.queue.pop(0)

            self.cv_prod.notify()

        return  result

    
if __name__ == "__main__":

    server = Server()
    server.run_skeleton()