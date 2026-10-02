import requests, json

if __name__ == "__main__":

    IP = 'http://127.0.0.1:5000'

    local_note = {}


    # Punto 1
    note = ['forza il napoli', 'scemo chi legge']

    for i in range(2):

        response = requests.post(IP + '/note', json=json.dumps({'note' : note[i]}))

        print(response.json())
        id = response.json()['id']

        local_note[str(id)] = note[i]

    print("Punto 1 " + str(local_note))


    # Punto 2
    id = 0

    response = requests.get(IP + '/note/' + str(id))
    local_note['1'] = response.json()['note']

    print("Punto 2 " + str(local_note))

    # Punto 3

    response = requests.get(IP + '/notes')

    data = response.json()

    for i in range(len(data)):
        id = data[i]['id']
        local_note[id] = data[i]['note']

    print("Punto 3 " + str(local_note))


    # Punto 4

    new_note = "Mi chiamo pepp refresh"
    id = 0

    response = requests.put(IP + '/note/' + str(id), json=json.dumps({'note' : new_note}))

    local_note[str(id)] = new_note

    print("Punto 4 " + str(local_note))

    # Punto 5

    id = 1
    response = requests.delete(IP + '/note', params={'id' : str(id)})    

    del local_note[str(id)]

    print("Punto 5 " + str(local_note))

    # Punto 6

    response = requests.delete(IP + '/notes')

    local_note.clear()

    print("Punto 6 " + str(local_note))
