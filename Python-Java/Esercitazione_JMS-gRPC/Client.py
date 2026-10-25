import stomp
from random import randint
import time

QUEUE_TO_SEND = "/queue/richieste"
QUEUE_TO_RECEIVE = "/queue/risposte"
NUM_REQUEST = 10

class ClientListener(stomp.ConnectionListener):

    def on_message(self, frame):
        print(f"[Client] Ricevuto messaggio: {frame.body}, correlation-id={frame.headers["correlation-id"]}")

if __name__ == "__main__":

    conn = stomp.Connection([("127.0.0.1", 61613)], auto_content_length=False)
    conn.connect(wait=True)

    conn.set_listener("listener", ClientListener())

    for i in range(int(NUM_REQUEST/2)):
        id_articolo = randint(0, 100)
        msg = "deposita#" + str(id_articolo)
        conn.send(destination=QUEUE_TO_SEND, body=msg, headers={"correlation-id" : f"{i+1}", "reply-to" : QUEUE_TO_RECEIVE})
        print(f"[Client] Inviata richiesta deposito: id_articolo={id_articolo}, correlation-id={i+1}")

    for i in range(int(NUM_REQUEST/2), NUM_REQUEST):
        msg = "preleva"
        conn.send(destination=QUEUE_TO_SEND, body=msg, headers={"correlation-id" : f"{i+1}", "reply-to" : QUEUE_TO_RECEIVE})
        print(f"[Client] Inviata richiesta preleva: correlation-id={i+1}")

    
    conn.subscribe(destination=QUEUE_TO_RECEIVE, id=1, ack="auto")

    time.sleep(10)

    conn.disconnect()
        

    