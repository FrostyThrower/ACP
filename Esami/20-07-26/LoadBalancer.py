from IBalancer import IBalancer
from LoadBalancerSkeleton import LoadBalancerSkeleton
import multiprocess
import multiprocessing
import stomp

class LoadBalancer(IBalancer):

    def __init__(self, dim_queue):
        self.queue = multiprocess.Queue(maxsize=dim_queue)

        cons_thread = multiprocess.Process(target=self.processCons_fun)
        cons_thread.start()
    
    def processCons_fun(self):

        conn = stomp.Connection([("127.0.0.1", 61613)], auto_content_length=False)
        conn.connect(wait=True)

        print("[LoadBalancer] Attivo...")

        try:
            while(True):

                request = str(self.queue.get())

                print("[LoadBalancer] Ricevuta richiesta - " + request)

                if request.find("GET") != -1:

                    conn.send("/topic/get", request)

                elif request.find("POST") != -1:

                    conn.send("/topic/post", request)

                elif request.find("DELETE") != -1:   

                    conn.send("/topic/delete", request)
        except KeyboardInterrupt:
            conn.disconnect() 

                

    def processProd_fun(self, method, domain, endpoint):

        request = f"{method} https://{domain}{endpoint}"
        self.queue.put(request)

    
    def request(self, method: str, domain: str, endpoint: str) -> int:

        process = multiprocess.Process(target=self.processProd_fun, args=(method, domain, endpoint))
        process.start()



if __name__ == "__main__":

    loadBalancer = LoadBalancer(6)
    loadBalancerSkeleton = LoadBalancerSkeleton(loadBalancer)
    loadBalancerSkeleton.run_skeleton()