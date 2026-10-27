import stomp
import multiprocess
from time import sleep

NUM_PROCESS = 2
QUEUE_MAXSIZE = 5

class WorkerListener(stomp.ConnectionListener):

    def __init__(self):

        self.queue = multiprocess.Queue(maxsize=QUEUE_MAXSIZE)
        self.lock = multiprocess.Lock()

    def deploy(self, obj):

        self.queue.put(obj)

    def stopAll(self):

        for i in range(self.queue.qsize()):
                print("[Worker] Rimosso " + self.queue.get())


    def on_message(self, frame):

        req = str(frame.body)
        
        method = req.split("#")[0]

        if method == "deploy":

            print("[Worker] Ricevuta richiesta DEPLOY")

            id = req.split("#")[1]
            name = req.split("#")[2]

            data_to_add = f"{id}-{name}"

            self.deploy(data_to_add)

            print(f"[Worker] Agginuto obj: id={id}, name={name}")

        else:

            print("[Worker] Ricevuta richiesta STOPALL")

            self.stopAll()

            print("[Worker] Cancellato tutto")




def process_fun(type):

    conn = stomp.Connection([("127.0.0.1", 61613)], auto_content_length=False)
    conn.connect(wait=True)

    conn.set_listener("listener_"+type, WorkerListener())
    conn.subscribe(destination="/topic/"+type, id=1, ack="auto")   

    print(f"[Worker-Thread-{type}] Avviato...") 

    sleep(120)

    conn.disconnect()


if __name__ == "__main__":

    process_gpu = multiprocess.Process(target=process_fun, args=("gpu",))
    process_gpu.start()

    process_rt = multiprocess.Process(target=process_fun, args=("rt",))
    process_rt.start()





