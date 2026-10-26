from IBalancer import IBalancer
import socket


class ClientProxy(IBalancer):

    def request(self, method: str, domain: str, endpoint: str) -> int:
        
        soc = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

        soc.connect(("localhost", 8080))
        
        data = f"{method}#{domain}#{endpoint}"
        
        soc.send(data.encode('utf-8'))

        soc.close()