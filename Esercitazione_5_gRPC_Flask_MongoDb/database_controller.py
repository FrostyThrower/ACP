from pymongo import MongoClient
import json

class Database_controller():

    coll_sensors = None
    coll_temp_data = None
    coll_press_data = None

    @classmethod
    def init(cls, reset=False):

        print("[Controller] Inizializzo database ed instauro connessione...")

        database_service = MongoClient("localhost", 27017)
        db = database_service["Flask_gRPC_MongoDb"]
        cls.coll_sensors = db["sensors"]
        cls.coll_temp_data = db["temp_data"]
        cls.coll_press_data = db["press_data"]

        if reset:
            cls.reset()


    @classmethod
    def add_sensor(cls, id, data_type) -> bool:
        # verifico che il sensore non sia già presente
        if cls.coll_sensors.find_one({'id' : id}) is not None:
            return False

        cls.coll_sensors.insert_one({ 'id' : id, 'data_type' : data_type})
    
        return True

    @classmethod
    def add_data(cls, sensor_id, data, type) -> bool:
        if type == "temp":
            cls.coll_temp_data.insert_one({'sensor_id' : sensor_id, 'data': data})
            return True
        else:
            cls.coll_press_data.insert_one({'sensor_id' : sensor_id, 'data': data})
            return True

    @classmethod
    def get_sensors(cls):
        return list(cls.coll_sensors.find())

    @classmethod
    def get_data(cls, sensor_id, data_type):

        if data_type == "temp":
            return list(cls.coll_temp_data.find({'sensor_id' : sensor_id}))
        else: 
            return list(cls.coll_press_data.find({'sensor_id' : sensor_id}))

    @classmethod
    def reset(cls):
        # Pulisco le collection
        cls.coll_sensors.delete_many({})
        cls.coll_temp_data.delete_many({})
        cls.coll_press_data.delete_many({})
