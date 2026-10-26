
from pymongo import MongoClient

class DbController():

    def __init__(self, who_i_am):
        # client
        self.client = MongoClient("localhost", 27017)

        # database
        self.db = self.client["senso_misuraDb"]

        #collection
        self.collection_sensori = self.db["sensors"]
        self.collection_temp = self.db["temp_data"]
        self.collection_press = self.db["press_data"]

        if who_i_am == "controller":
            self.collection_sensori.delete_many({})
            self.collection_press.delete_many({})
            self.collection_temp.delete_many({})

    def printDb(self):

        print("-- [DbController] Sensors: ")
        for doc in self.collection_sensori.find():
            print(doc)

        print("-- [DbController] temp_data: ")
        for doc in self.collection_temp.find():
            print(doc)

        print("-- [DbController] press_data: ")
        for doc in self.collection_press.find():
            print(doc)
        
    def findSensor(self, id):

        result = self.collection_sensori.find_one({"_id" : id})
        return result

    def addSensor(self, note):
        
        id = note["_id"]

        if self.findSensor(id) is None:
            self.collection_sensori.insert_one(note)
            self.printDb()
            return True
        else:  
            return False

    def addData(self, data, sensor_id, type):

        result = self.findSensor(sensor_id)

        # Check esistenza sensore
        if result is None:
            return "ERROR - Sensor_id inesistente"
        
        # Check corrispondenza type sens e type data
        elif result["data_type"] != type:
            return "ERROR - Tipo dato non coincidente con tipo sensore"

        if type == "temp":
            self.collection_temp.insert_one(data)
        elif type == "press":
            self.collection_press.insert_one(data)
        else:
            print("[DbController] ERROR: Tipo non riconosciuto...")
            return "ERROR - Tipo sconosciuto"

        self.printDb()
        return "success"

    def getSensors(self):
        
        return list(self.collection_sensori.find({}))

    def getDataById(self, data_type, id):

        if data_type == "press":

            return list(self.collection_press.find({"sensor_id" : id}))

        elif data_type == "temp":

            return list(self.collection_temp.find({"sensor_id" : id}))
        
        else:
            print("[DbController] ERRORE - Data_type sconosciuto")