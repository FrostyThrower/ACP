import requests


def print_diz(diz):
    for item in diz.values():
        print(item)

if __name__ == "__main__":

    myDiz = {}

    notes = [{"text" : "Ciaooo, sono una nota", "tag" : "test" }, 
            {"text" : "Lezione 3 - Il forza napoli: Perché è così forte?", "tag" : "università"}]

    URL = "http://127.0.0.1:5000/"

    id = 0
    for note in notes:
        id += 1
        myDiz[id] = note
        requests.post(url=URL+"note", json=note)

    print_diz(myDiz)


    id = 2
    data = requests.get(url=URL+"/note/"+str(id))
    response = data.json()
    print(response)

    data = requests.get(url=URL+"/notes")
    response = data.json()
    print(response)

    newNote = {"note" : "sono una nuova nota", "tag" : "forzNapoli"} 
    data = requests.put(url=URL+"/note/2", json=newNote)
    print(data.json())
    myDiz["2"] = newNote

    print_diz(myDiz)


    