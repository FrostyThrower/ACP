import stomp, time, json
from random import randint

NUM_REQUESTS = 10
WHO_I_AM = "[Client] "

class Client_listener(stomp.ConnectionListener):

    def on_message(self, frame):
        print(WHO_I_AM + "Received" + frame.body)

if __name__ == "__main__":

    print(WHO_I_AM + "Start connection to broker...")

    # Definisco la connesione tra client e brocker
    conn = stomp.Connection([("127.0.0.1", 61613)])
    conn.connect(wait=True)
    
    # Invio richieste
    for i in range(NUM_REQUESTS):

        # creo il messaggio come JSON
        message = {
            "method" : f"{randint(0, 1)}",
            "id" : f"{randint(0, 100)}"
        }

        print(WHO_I_AM + f"Invio messaggio: {message["method"]}, {message["id"]} ")

        # La topic (come la queue) sono auto-create
        conn.send("/topic/request", json.dumps(message), content_type="application/json")

   

    # setto il listener per ricevere i messaggi
    conn.set_listener("listener", Client_listener())
      
    # lo iscrivo al topic corretto
    conn.subscribe("/topic/response", id=1, ack="auto")

    time.sleep(30)

    conn.disconnect()
