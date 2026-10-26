from IBalancer import IBalancer
import socket

BUFF_SIZE = 65000

class LoadBalancerSkeleton(IBalancer):

    def __init__(self, ref):
        self.ref = ref

    def request(self, method: str, domain: str, endpoint: str) -> int:
        
        self.ref.request(method, domain, endpoint)

    def run_skeleton(self):

        address_server = ("localhost", 8080)

        soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        soc.bind(address_server)

        soc.listen()

        while(True):
            conn, addr = soc.accept()

            data = conn.recv(BUFF_SIZE)
            text = data.decode('utf-8')

            method = text.split("#")[0]
            domain = text.split('#')[1]
            endpoint = text.split('#')[2]

            self.request(method, domain, endpoint)
