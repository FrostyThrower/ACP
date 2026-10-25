import stomp


if __name__ == "__main__":

    queueConn = stomp.Connection([('127.0.0.1', 61613)])
    queueConn.connect(wait=True)

    queueConn.send("/queue/test", "FORZA NAPOLI SEMPRE")

    queueConn.disconnect()