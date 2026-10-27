from flask import Flask, request

app = Flask(__name__)


@app.post("/update_history")
def update_history():

    print("[History Server] Ricevuta richiesta di aggiornamento")
    data = request.get_json()
    try: 
        with open("history.txt", "a") as file:
            file.write(f"{data["operation"]} - {data["serial_number"]}\n")
    except IOError as e:
        print("[History Server] ERRORE: aggiornamento non riuscito " + e.strerror)
        return {"result" : "failed"}

    print("[History Server] Aggiornamento avvenuto con successo")
    return {"result" : "success"}
    

if __name__ == "__main__":
    app.run()