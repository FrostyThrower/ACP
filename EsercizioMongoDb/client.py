import order_manager_pb2, order_manager_pb2_grpc
import grpc, sys

def make_request(order_to_send):

    for order in order_to_send.values():
        yield order_manager_pb2.Order(id=order['id'], items=order['items'], descrizione=order['descrizione'], prezzo=order['prezzo'], destinazione=order['destinazione'])

if __name__ == "__main__":

    try:
        port_server = sys.argv[1]
    except:
        print("Insert port server")
        sys.exit(1)

    


    with grpc.insecure_channel("localhost:" + str(port_server)) as channel:

        stub = order_manager_pb2_grpc.OrderManagerStub(channel)

        response1 = stub.addOrder(order_manager_pb2.Order(id="", items="Capello-scontrino", descrizione="roba", prezzo=2.3, destinazione="PizzoCalabro"))
        
        response2 = stub.addOrder(order_manager_pb2.Order(id="", items="piselloenorme-cacca", descrizione="forzanapoli", prezzo=5.6, destinazione="Battipaglia"))
        
        response3 = stub.addOrder(order_manager_pb2.Order(id="", items="pc-cacca", descrizione="forzanapoli", prezzo=5.6, destinazione="Battipaglia"))



        order1 = stub.getOrder(order_manager_pb2.StringMassage(messaggio=response1.messaggio))
        order2 = stub.getOrder(order_manager_pb2.StringMassage(messaggio=response2.messaggio))

        print(order1)
        print(order2)

        for order in stub.searchOrders(order_manager_pb2.StringMassage(messaggio='cacca')):
            print(order)

        
        order_to_send = {
            "order1" : {
                'id' : "",
                'items' : "mouse-tastiera",
                'descrizione' : 'desc1',
                'prezzo' : 1.1,
                'destinazione' : "Napoli"
            } ,
            "order2" : {
                'id' : "",
                'items' : "monitor-bottiglia",
                'descrizione' : 'desc2',
                'prezzo' : 1.2,
                'destinazione' : "Roma"
            },
            "order3" : {
                'id' : "",
                'items' : "tappetino-cuffie",
                'descrizione' : 'desc3',
                'prezzo' : 1.3,
                'destinazione' : "Napoli"
            }
        }        

        for shipments in stub.processOrders(make_request(order_to_send)):
            print(shipments)

        response = stub.cancelShipment(order_manager_pb2.StringMassage(messaggio="1"))
    
        if response.messaggio == "Deleted":
            print("Shipment deleted successfully!")


        


