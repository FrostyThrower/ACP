from flask import Flask, request
from DbController import DbController

app = Flask(__name__)

@app.post("/sensor")
def sensorAdd():

    data = request.get_json()
    result = db.addSensor(data)
    if result:
       return {"result" : "success"}
    else:
       return {"result" : "sensors already add"}

@app.post("/data/<data_type>")
def dataAdd(data_type):

    data = request.get_json()
    result = db.addData(data, data["sensor_id"], data_type)
    return {"result" : result}

if __name__ == "__main__":
    db = DbController("controller")
    app.run()