import stomp
import time

class Listener(stomp.ConnectionListener):

    def on_message(self, frame):
        print(frame.body)

if __name__ == "__main__":


    queueConn = stomp.Connection([("127.0.0.1", 61613)])
    queueConn.set_listener('1', Listener())
    queueConn.connect(wait=True)

    queueConn.subscribe(destination="/queue/test", id=1, ack="auto")

    time.sleep(60)

    queueConn.disconnect()

