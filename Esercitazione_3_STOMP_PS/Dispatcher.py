import stomp, ProxyDispatcher
import time, json, sys, multiprocessing

WHO_I_AM = "[Dispatcher] "


def proc_fun(server_port, data):

    proxy = ProxyDispatcher.ProxyDispatcher(server_port)
       
    if data["method"] == "0": # deposita

        proxy.deposita(data["id"])

    else:   # preleva 
        
        result = proxy.preleva()

        print(WHO_I_AM + "Send " + result)

        conn = stomp.Connection([("localhost", 61613)])
        conn.connect(wait=True)

        conn.send('/topic/response', result)

        conn.disconnect()

        


class Listener_and_forward_request(stomp.ConnectionListener):

    def __init__(self, server_port):

        stomp.ConnectionListener.__init__(self)
        self.server_port = server_port

    def on_message(self, frame):
        data = json.loads(frame.body)
        print(WHO_I_AM + f"Message: {data["method"]}, {data["id"]}")
        
        process = multiprocessing.Process(target=proc_fun, args=(server_port, data))
        process.start()


if __name__ == "__main__":

    try:
        server_port = sys.argv[1]
    except:
        print("Insert server port")
        sys.exit(1)

    print(WHO_I_AM + "Start connection to broker...")

    # Istauro connessione con broker
    conn = stomp.Connection([("localhost", 61613)])
    conn.connect(wait=True)

    print(WHO_I_AM + "Subscribe to topic/request")

    # Iscrivo il listener
    conn.set_listener("listener_and_forward", Listener_and_forward_request(server_port))
    conn.subscribe("/topic/request", id=1, ack="auto")
    
    print(WHO_I_AM + "Waiting messages...")

    time.sleep(30)

    conn.disconnect()