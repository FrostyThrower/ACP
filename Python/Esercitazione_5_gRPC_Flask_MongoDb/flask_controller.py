from flask import Flask, request
import json
from database_controller import Database_controller

app = Flask(__name__)
    
@app.post("/sensors")
def sensor():
    print("[Controller] Ricevuta richiesta di aggiunta sensore")

    data = request.get_json(force=False, silent=False, cache=False)

    id = data['id']
    data_type = data["data_type"]

    result = Database_controller.add_sensor(id, data_type)

    if result:
        print(f"[Controller] Sensor (id:{id}, data_type:{data_type}) added successfully")
        return json.dumps({ 'result' : 'success'})
    else:
        print(f"[Controller] Sensor (id:{id}, data_type:{data_type}) failed")
        return json.dumps({ 'result' : 'failed: sensor already added'})



@app.post("/data/<data_type>")
def send_data(data_type):
    
    print("[Controller] Ricevuta richiesta di aggiunta dato")

    data = request.get_json(force=False, silent=False, cache=False)
    sensor_id = data['sensor_id']
    data = data['data']

    if data_type not in ("temp", "press"):
        return json.dumps({'result' : 'failed: data_type error'})

    result = Database_controller.add_data(sensor_id, data, data_type)


    if result:
        return json.dumps({'result' : 'success'})
    else:
        return json.dumps({'result' : 'failed'})


        
    


if __name__ == "__main__":
    
    # Inizializzo classe gestrice
    Database_controller.init(reset=True)
    
    app.run(debug=True)