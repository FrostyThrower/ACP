import stomp
from random import randint
import time

NUM_REQ = 10

class ClientListener(stomp.ConnectionListener):

    def __init__(self):
        pass

    def on_message(self, frame):
        print(f"[Client] Receive response {frame.headers["correlation-id"]} : {frame.body} ")




if __name__ == "__main__":

    conn = stomp.Connection([("127.0.0.1", 61613)], auto_content_length=False)
    conn.connect(wait=True)

    conn.set_listener('client', ClientListener())

    # Request deposita
    for i in range(int(NUM_REQ/2)):
        id_articolo = randint(0, 100)
        msg = "deposita" + "#" + str(id_articolo)
        conn.send(destination='/queue/request', body=msg, headers={"correlation-id" : f"{i}", "reply-to" : "/queue/reply"})
        print(f"[Client] Send deposited request: id = {id_articolo}, correlation-id = {i} ")

    # Request preleva
    for i in range (int(NUM_REQ/2), NUM_REQ):
        msg = "preleva"
        conn.send(destination='/queue/request', body=msg, headers={"correlation-id" : f"{i}", "reply-to" : "/queue/reply"})
        print(f"[Client] Send deposited request: correlation-id = {i} ")


    conn.subscribe(destination="/queue/reply", id=1, ack="auto")

    time.sleep(10)

    conn.disconnect()
        