from flask import Flask, request
from pymongo import MongoClient
import json

class DbController():

    def __init__(self, rs):

        client = MongoClient("127.0.0.1", 27017)
        db = client["db_server"]
        self.collection_prenotazioni = db["prenotazioni"]

        if rs == "1":
            self.collection_prenotazioni.delete_many({})
            self.printDb()

    def printDb(self):

        print("[DbController] Db:")

        for prenotazione in list(self.collection_prenotazioni.find({})):
            print(prenotazione)

    def aggiungiPrenotazione(self, data):

        self.collection_prenotazioni.insert_one(data)
        # print("[DbController] Aggiunta prenotazione: " )
        self.printDb()


    def applicaSconto(self, data):
        
        operator = data["operator"]
        prenotazioni = {}

        prenotazioni_trovate = self.collection_prenotazioni.find({"operator" : operator})
        for prenotazione in prenotazioni_trovate:
            if prenotazione["nights"] >= data["nights"]:
                nuovo_costo = prenotazione["cost"] - data["discount"]
                if nuovo_costo < 0:
                    nuovo_costo = 0
                print("[DbController] Aggiornamento sconto=" + str(nuovo_costo) + "\n")
                self.collection_prenotazioni.update_one({"_id" : prenotazione["_id"]}, {"$set" : {"cost" : nuovo_costo}})
                prenotazione["_id"] = str(prenotazione["_id"])
                prenotazioni[nuovo_costo] = prenotazione
        self.printDb()
        return prenotazioni


app = Flask(__name__)

@app.post("/prenotazione")
def creaPrenotazione():
    
    print("[DbServer] Ricevuta richiesta aggiunta prenotazione")

    data = request.get_json()
    db.aggiungiPrenotazione(data)
    return {"risultato" : "Prenotazione creata"}



@app.post("/sconto")
def applicaSconto():
    
    print("[DbServer] Ricevuta richiesta applicazoine sconto")

    data = request.get_json()
    prenotazioni_cambiate = db.applicaSconto(data)
    return prenotazioni_cambiate
    


if __name__ == "__main__":

    rs = input()
    db = DbController(rs)
    app.run(debug=True)