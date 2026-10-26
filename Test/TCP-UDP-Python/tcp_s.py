import socket

BUFF_SIZE = 3000

if __name__ == "__main__":

    port = 8080

    soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    soc.bind(("localhost", port))

    soc.listen()
    
    conn, addr = soc.accept()

    resposne = conn.recv(BUFF_SIZE)
    print(resposne.decode('utf-8'))

    conn.send("Forza il napoli".encode('utf-8'))
    conn.close()
    soc.close()


