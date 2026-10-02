import threading
import time
from multiprocessing import Queue

class Botteghino:
    
    def __init__(self, max):

        self.queue = []
        self.max = max


        self.counter = 0

        self.lock = threading.Lock()

        self.buff_is_full = threading.Condition(self.lock)
        self.buff_is_empty = threading.Condition(self.lock)


    def acquisto_biglietto(self):
        with self.buff_is_empty:
            while len(self.queue) == 0:
                self.buff_is_empty.wait()
            
            biglietto = self.queue.pop(0)

            self.buff_is_full.notify()

        return biglietto

    def rinserisci_biglietto(self, thread_cons):
        
        with self.buff_is_full:

            print(f"Queue size {len(self.queue)}")

            while len(self.queue) == self.max:
                self.buff_is_full.wait()
            
            self.queue.append([thread_cons, self.counter])
            self.counter = self.counter + 1

            self.buff_is_empty.notify()



        
class Producer_thread(threading.Thread):

    def __init__(self, thread_id, bott):

        threading.Thread.__init__(self)
        self.id = thread_id
        self.bott = bott

    def run(self):

        for i in range(250):
            self.bott.rinserisci_biglietto(self.id)
            
            print(f"[Thread-{self.id}] Inserito biglietto")


class Consumer_thread(threading.Thread):

    def __init__(self, thread_id, bott):

        threading.Thread.__init__(self)
        self.thread_id = thread_id
        self.botteghino = bott

    def run(self):

        print(f"[Thread-{self.thread_id}] Thread avviato")

        for i in range(100):

            bigl = self.botteghino.acquisto_biglietto()

            print(f"[Thread-{self.thread_id}] {bigl}")

if __name__ == "__main__":

    bott = Botteghino(500)
    producer_thread = []
    consumer_thread = []


    for i in range(2):

        th = Producer_thread(i, bott)
        th.start()

        producer_thread.append(th)

    for i in range(5):

        
        th = Consumer_thread(i, bott)
        th.start()

        consumer_thread.append(th)

    for t in producer_thread:
        t.join()

    for t in consumer_thread:
        t.join()

