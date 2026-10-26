from flask import Flask, request
import threading

app = Flask(__name__)

class note_db():

    def __init__(self):

        self.diz = {}
        self.lock = threading.Lock()
    
    def gen_id(self):
        with self.lock:
            if(len(self.diz) == 0):
                ultimo_id = 0
            else:
                ultimo_id, valore = next(reversed(self.diz.items()))
        return str(int(ultimo_id)+1)


    def add_note(self, note):

        id = self.gen_id()

        with self.lock:
            self.diz[id] = note

        print(self.diz)

        return id
        
    def find_note(self, id):

        with self.lock:
            note = self.diz[str(id)]
        
        if(note == None):
            return "Note not found"
        else:
            return note
    
    def get_notes(self):

        with self.lock:
            diz_return = self.diz

        return diz_return

    def update_note(self, id, note):

        if self.find_note(id) == "Note not found":
            id = self.add_note(note)
            return ("added", id)
        else:
            with self.lock:
                self.diz[str(id)] = note
            print(self.diz)
            return ("updated", id) 
    
    def delete_note(self, id):
        
        if self.find_note(id) == "Note not found":
            return "Note not found"
        else:
            with self.lock:
                self.diz.pop(id)
            return ("deleted", id)

    def delete_notes(self):
        with self.lock:
            self.diz.clear()

        return "No more Notes"
    

@app.post("/note")
def note_post():
    data = request.get_json()

    id = note_db.add_note(data)
    return {"result" : "added", "id" : id}


@app.route("/note/<id>", methods=["GET", "PUT", "DELETE"])
def note_id_get(id):
    
    if request.method == "GET":
        note = note_db.find_note(id)
        return {"result" : note}

    elif request.method == "PUT":
        data = request.get_json()

        msg, id = note_db.update_note(id, data)
        return {"result" : msg, "id" : id}

    elif request.method == "DELETE":
        result, id_return = note_db.delete_note(id)
        return {"result" : result, "id" : id_return}



@app.route("/notes", methods=["GET", "DELETE"])
def notes():


    if request.method == "GET":
        return note_db.get_notes()
    
    elif request.method == "DELETE":
        result = note_db.delete_notes()
        return {"result" : result}



if __name__ == "__main__":
    note_db = note_db()
    app.run(debug=True)