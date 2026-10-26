import socket

BUFF_SIZE = 3000

if __name__ == "__main__":

    soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    soc.connect(("localhost", 8080))
    soc.send("forza il napoli 3".encode('utf-8'))

    response = soc.recv(BUFF_SIZE)

    soc.close()

    print(response.decode('utf-8'))
    