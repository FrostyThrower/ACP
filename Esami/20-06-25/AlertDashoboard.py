import stomp
import multiprocess
from time import sleep

def process_fun(data):

    print("[AlertDashboard-Process] Ricevuto data_temp: " + data)
    with open("alert.txt", "a") as file:
        file.write(f"{data}\n")


class AlertDashboardListener(stomp.ConnectionListener):

    def on_message(self, frame):

        data = str(frame.body)
        process = multiprocess.Process(target=process_fun, args=(data,))
        process.start()



if __name__ == "__main__":

    with open("alert.txt", "a") as file:
        file.write(f"\nNewStart:\n")

    conn = stomp.Connection([("localhost", 61613)])
    conn.connect(wait=True)

    conn.subscribe("/topic/alert", id=1, ack="auto")
    conn.set_listener("listener", AlertDashboardListener())

    print("[AlertDashboard] Connesso ed attivo...")

    sleep(30)

    conn.disconnect()