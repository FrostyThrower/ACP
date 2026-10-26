import threading
from time import sleep
from ClientProxy import ClientProxy
from random import randint

NUM_THREADS = 5
NUM_REQUESTS = 5

METHODS = ["GET", "POST", "DELETE"]
DOMAINS = ["www.google.com", "www.unina.it", "www.youtube.com", "www.facebook.com"]
ENDPOINTS = ["/homepage", "/posts/123", "/path_to"]

def thread_func():

    loadBalancer = ClientProxy()
    for i in range(NUM_REQUESTS):

        method = METHODS[randint(0, len(METHODS)-1)]
        domain = DOMAINS[randint(0, len(DOMAINS)-1)]
        endpoint = ENDPOINTS[randint(0, len(ENDPOINTS)-1)]

        loadBalancer.request(method, domain, endpoint)
        print(threading.current_thread().name + " Inviato richiesta: " + f"{method}  https://{domain}{endpoint}")

        sleep(1)

if __name__ == "__main__":

    threads = []
    for i in range(NUM_THREADS):

        th = threading.Thread(target=thread_func, name=f"Thread-{i+1}")
        th.start()
        threads.append(th)

    print("[Client] Threads avviati...")

    for t in threads:
        t.join()

