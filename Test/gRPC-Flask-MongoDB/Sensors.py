import threading
import requests
from random import randint
import time

MAX_THREADS = 5
MAX_MISURAZIONI = 5

URL = "http://127.0.0.1:5000/"
SENSOR_TYPE = ["temp", "press"]

def sensor(id): 

    # Registrazione del sensore
    data_type = SENSOR_TYPE[randint(0, len(SENSOR_TYPE)-1)]
    request_to_send = {"_id" : id, "data_type" : data_type}
    data = requests.post(url=URL+"sensor", json=request_to_send)
    response = data.json()
    print(f"[Sensor-{id}] Esito registrazione: " + response["result"])

    if response["result"] == "success":
        # Invio dati
        for i in range(MAX_MISURAZIONI):
            data = randint(0, 50)
            data_to_send = {"sensor_id" : id, "data" : data}
            data = requests.post(url=URL+f"data/{data_type}", json=data_to_send)
            response = data.json()
            print(f"[Sensor-{id}] Inviata misurazione con esito: " + response["result"])
            time.sleep(0.5)
    else:
        print(f"[Sensor-{id}] Impossibile inviare dati")


if __name__ == "__main__":


    threads = []
    for i in range(MAX_THREADS):
        th = threading.Thread(target=sensor, args=(i+1,))
        th.start()
        threads.append(th)

    for t in threads:
        t.join()