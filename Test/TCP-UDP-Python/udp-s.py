import socket

BUFF_SIZE = 3000

if __name__ == "__main__":

    soc = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    soc.bind(("localhost", 8080))
    
    response, addr = soc.recvfrom(BUFF_SIZE)
    print(response.decode('utf-8'))
    soc.sendto("forza il napoli".encode('utf-8'), addr)

    soc.close()