import stomp
from time import sleep
import threading
from random import randint

CLIENT_USR = ["pippo", "ciruzzo", "franco"]
HOTEL = ["Hotel Pietrarsa", "Hotel Miramare", "Hotel Napoli"]

NUM_THREADS_CREATE = 4
NUM_THREADS_UPDATE = 2

def thread_create(conn, operator):
    
    client = CLIENT_USR[randint(0, len(CLIENT_USR)-1)]
    hotel = HOTEL[randint(0, len(HOTEL)-1)]

    nights = randint(0, 10)
    people = randint(0, 5)
    cost = randint(25, 100)

    data_to_send = f"create#{client}#{hotel}#{operator}#{nights}#{people}#{cost}"

    conn.send("/topic/request", data_to_send)

    print("[Operator] Inviata richiesta: " + data_to_send)



def thread_update(conn, operator):
    
    nights = randint(0, 10)
    discount = randint(0, 50)    

    data_to_send = f"update#{discount}#{operator}#{nights}"

    conn.send("/topic/request", data_to_send)
    print("[Operator] Inviata richiesta: " + data_to_send)

    


class OperatorListener(stomp.ConnectionListener):

    def on_message(self, frame):

        print("[Operator] Ricevuta risposta: " + frame.body)


if __name__ == "__main__":

    conn = stomp.Connection([("127.0.0.1", 61613)])
    conn.connect(wait=True)

    conn.set_listener("list2", OperatorListener())
    conn.subscribe("/topic/response", id=1, ack="auto")

    operator = input("[Operator] Inserire valore operator: ")

    for i in range(NUM_THREADS_CREATE):
        th = threading.Thread(target=thread_create, args=(conn, operator))
        th.start()

    for i in range(NUM_THREADS_UPDATE):
        th = threading.Thread(target=thread_update, args=(conn, operator))
        th.start()

    print("[Operator] Operator attivo...")

    sleep(120)

    conn.disconnect()