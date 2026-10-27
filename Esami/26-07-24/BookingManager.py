import stomp
import requests
from time import sleep

class BookingManagerListener(stomp.ConnectionListener):

    def __init__(self):
        self.URL = "http://127.0.0.1:5000/"
    
    def on_message(self, frame):
        
        data = str(frame.body)
        tipo_richiesta = data.split("#")[0]

        if tipo_richiesta.lower() == "create":

            print("[BookingManager] Ricevuta richiesta di CREATE")

            # Estraggo valori
            client = data.split("#")[1]
            hotel = data.split("#")[2]
            operator = data.split("#")[3]
            nights = int(data.split("#")[4])
            people = int(data.split("#")[5])
            cost = int(data.split("#")[6])

            # Costruisco dizionario
            data_to_send = {
                "client" : client,
                "hotel" : hotel,
                "operator" : operator,
                "nights" : nights,
                "people" : people,
                "cost" : cost
            }

            response = requests.post(url=self.URL+"prenotazione", json=data_to_send)
            data = response.json()
            conn.send("/topic/response", data["risultato"])

        elif tipo_richiesta.lower() == "update":

            print("[BookingManager] Ricevuta richiesta di UPDATE")


            # Estraggo valori
            discount = int(data.split("#")[1])
            operator = data.split("#")[2]
            nights = int(data.split("#")[3])

            data_to_send = {
                "discount" : discount,
                "operator" : operator,
                "nights" : nights
            }

            response = requests.post(url=self.URL+"sconto", json=data_to_send)
            data = response.json()
            conn.send("/topic/response", data)

        else:
            print("[BookingManager] ERROR Tipo di richiesta sconosciuta")



if __name__ == "__main__":

    conn = stomp.Connection([("127.0.0.1", 61613)])
    conn.connect(wait=True)

    conn.set_listener("list1", BookingManagerListener())
    conn.subscribe("/topic/request", id=1, ack="auto")

    print("[BookingManager] BookingManager avviato...")

    sleep(120)

    conn.disconnect()