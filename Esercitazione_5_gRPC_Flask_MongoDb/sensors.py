import multiprocessing
from random import randint
import requests, json

NUM_SENSORS = 5
NUM_MISUR = 5
IP = "http://127.0.0.1:5000"

def process_fun(id, data_type):

    # Iscrizione del sensore
    response = requests.post(IP + "/sensors", json={ 'id' : str(id), 'data_type' : data_type})

    
    result = response.json()['result']
    
    if result == "success":
        print(f"[Sensor-{id}] Iscritto con successo alla lista dei sensori")
    else:
        print(f"[Sensor-{id}] Iscrizione fallita")

    for i in range(NUM_MISUR):

        data = randint(1, 50)
        response = requests.post(IP + f"/data/{data_type}", json={"sensor_id" : str(id), "data" : str(data)})
        
        result = response.json()['result']

        if result == "success":
            print(f"[Sensor-{id}] Dato memorizzato con successo")
        else:
            print(f"[Sensor-{id}] Dato non memorizzato")



if __name__ == "__main__":

    threads = []

    for i in range(NUM_SENSORS):

        data_type = randint(0, 1)
        if data_type == 1:
            data_type = "press"
        else:
            data_type = "temp"

        t = multiprocessing.Process(target=process_fun, args=(i, data_type))
        t.start()

        threads.append(t)

