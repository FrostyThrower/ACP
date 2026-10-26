import socket

BUFF_SIZE = 3000

if __name__ == "__main__":

    soc = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

    soc.sendto("NAPOLINAPOLINAPOLI".encode('utf-8'), ("localhost", 8080))

    response, addr = soc.recvfrom(BUFF_SIZE)

    print(response.decode('utf-8'))

    soc.close()

