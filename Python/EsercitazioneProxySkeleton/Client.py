import threading, time, random, sys
from ProxyClient import ProxyClient


def thr_dep(thread_id, port):

    proxy = ProxyClient(port)

    for i in range(3):

        time.sleep(random.randint(2, 4))

        articolo = random.randint(0, 1) 
        id = random.randint(1, 100)

        print(f'[P-Thread-{thread_id}] Richiedo deposito {articolo}:{id}')

        proxy.deposita(articolo, id)

        print(f'[P-Thread-{thread_id}] Deposito effettuato {articolo}:{id}')

    print(f"[P-Thread-{thread_id}] Fine richieste...")

        
def thr_pre(thread_id, port):

    proxy = ProxyClient(port)


    for i in range(3):

        time.sleep(random.randint(2, 4))

        articolo = random.randint(0, 1) 

        print(f'[C-Thread-{thread_id}] Richiedo prelievo')

        result = proxy.preleva(articolo)

        print(f'[C-Thread-{thread_id}] Prelevato articolo {result} dalla coda degli {articolo}')

    print(f"[C-Thread-{thread_id}] Fine richieste...")

        
if __name__ == '__main__':

    try:  
        port_server = sys.argv[1]
    except IndexError:
        print("Specific port server")
        sys.exit(1)

    thread_deposito = []
    thread_preleva = []

    for i in range(5):

        th = threading.Thread(target=thr_dep, args=(i, int(port_server)))
        th.start()

        thread_deposito.append(th)

    time.sleep(0.5)

    for i in range(5):
        th = threading.Thread(target=thr_pre, args=(i, int(port_server)))
        th.start()

        thread_preleva.append(th)

    for t in thread_deposito:
        t.join()

    for t in thread_preleva:
        t.join()

        

