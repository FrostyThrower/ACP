from flask import Flask, request
import json

app = Flask(__name__)

db_notes = {}
id_count_gen = 0

def add_note(note) -> int:

    global id_count_gen

    # Genero l'id
    new_id = id_count_gen
    id_count_gen += 1

    # Aggiungo nota al database
    db_notes[str(new_id)] = note

    return new_id
    
    
def get_note(*id):

    if not id:
        note_return = []
        for id_note, note in db_notes.items():
            note_return.append({'id' : id_note, 'note' : note})    
        return note_return 
    else:
        id = id[0]
        if str(id) in db_notes:
            return db_notes[id]
        else:
            return None
    
    
def update_note(id, note):
    id_result = -1

    if str(id) in db_notes:
        db_notes[str(id)] = note
   
    else:
        id_result = add_note(note)

    return id_result

def delete_note(*id):

    if not id:

        db_notes.clear()

        return True

    else:
        id = id[0]
        if str(id) in db_notes:
            del db_notes[str(id)]
            return True
        else:
            return False


@app.route("/note", methods=["POST", "DELETE"])
def note():

    if request.method == 'POST':
        # Faccio il parsing dei data nel formato json

        data = request.get_json(force=False, silent=False, cache=False)
        data = json.loads(data)
        
        # Faccio l'add    
        id = add_note(data['note'])

        print(f"[Server-Post/note] Add note {data['note']}, id:{id}")
            
        return json.dumps({'result' : 'added', 'id' : str(id)})

    elif request.method == "DELETE":
        params = request.args

        print(f"[Server-Delete/note] Delete note by id : {params['id']}")

        result = delete_note(params['id'])

        if result:
            return json.dumps({'result' : 'deleted', 'id' : params['id']})
        else:
            return json.dumps({'result' : 'note not found'}), 404
    


@app.route("/note/<id>", methods=["GET", "PUT"])
def note_id(id):

    if request.method == 'GET':
        print(f"[Server-Get/note/id] Get note id:{id}")

        note = get_note(id)

        if note is None:
            return json.dumps({'result' : 'note not found'}), 404
        else:
            return json.dumps({'result' : 'note found', 'note' : f'{note}'})
    
    elif request.method == 'PUT':
        print(f"[Server-Put/note/id] Update/Create note by id:{id}")

        data = json.loads(request.get_json(force=False, silent=False, cache=False))        

        result = update_note(id, data['note'])

        if result > 0:
            return json.dumps({'result' : 'added', 'id' : str(result)})
        else:
            return json.dumps({'result' : 'updated', 'id' : str(id)})



@app.route("/notes", methods=["GET", "DELETE"])
def notes():

    if request.method == "GET":
        print(f"[Server-Get/notes] Get all notes")

        notes = get_note()
        return notes

    elif request.method == "DELETE":
        print(f"[Server-Delete/notes] Delete all notes")

        result = delete_note()

        if result:
            return json.dumps({'result' : 'no more notes'})
    

if __name__ == "__main__":
    app.run(debug=True)