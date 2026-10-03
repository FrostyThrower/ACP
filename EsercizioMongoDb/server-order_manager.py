import order_manager_pb2, order_manager_pb2_grpc
import grpc, concurrent, threading
from pymongo import MongoClient

class OrderManager(order_manager_pb2_grpc.OrderManagerServicer):

    def __init__(self):

        order_manager_pb2_grpc.OrderManagerServicer.__init__(self)

        # Istauro connessione con database
        database_servicer = MongoClient("localhost", 27017)

        self.db = database_servicer['EsercizioMongoDb']
        self.orders = self.db['orders']
        self.shipments = self.db['shipments']

        print(f"[S-OrderManager] Collection orders : {self.orders}")
        print(f"[S-OrderManager] Collection shipments : {self.shipments}")

        # Pulisco le collection
        self.orders.delete_many({})
        self.shipments.delete_many({})

        # Id
        self.next_id_orders = 0
        self.lock_id_order = threading.Lock()

        self.next_id_shipments = 0
        self.lock_id_shipments = threading.Lock()



    def _addSingleOrder(self, request) -> int:

        # Recupero l'id da usare
        id_to_insert = 0

        with self.lock_id_order:
            id_to_insert = self.next_id_orders
            self.next_id_orders = self.next_id_orders + 1

        # Recupero gli items dell'ordine
        items = request.items.split('-')

        # Aggiungo la richiesta di ordine all'interno della collection Orders
        self.orders.insert_one({
            'id' : str(id_to_insert),
            'items' : items,
            'descrizione' : request.descrizione,
            'prezzo' : request.prezzo,
            'destinazione' : request.destinazione
        })

        print(f"[{threading.current_thread().name}] Add order id:{id_to_insert}, items:{request.items}, descrizione:{request.descrizione}, prezzo:{str(request.prezzo)}, destinazione:{request.destinazione}")
        return id_to_insert
    
    def addOrder(self, request, context):
        
        print(f"[{threading.current_thread().name}] Request to add an order")

        id = self._addSingleOrder(request)

        return order_manager_pb2.StringMassage(messaggio=str(id))        

    def getOrder(self, request, context):

        print(f"[{threading.current_thread().name}] Request to return an order")
        
        id_order_to_search = request.messaggio

        # Ricerco nella collection order
        order = self.orders.find_one({"id" : str(id_order_to_search)})

        if order is None:
            return order_manager_pb2.Order()
        else:
            return order_manager_pb2.Order(id=order["id"], items="-".join(order["items"]), descrizione=order["descrizione"], prezzo=float(order["prezzo"]), destinazione=order["destinazione"])
            

    def searchOrders(self, request, context):

        print(f"[{threading.current_thread().name}] Request to search orders")        
        
        item_to_search = request.messaggio

        result_search = self.orders.find({"items" : str(item_to_search)})

        found_one = False
        for order in result_search:

            found_one = True
            yield order_manager_pb2.Order(id=order["id"], items="-".join(order["items"]), descrizione=order["descrizione"], prezzo=float(order["prezzo"]), destinazione=order["destinazione"])

        if not found_one:
            context.abort(grpc.StatusCode.NOT_FOUND, "Nessun ordine trovato")

    def processOrders(self, request_iterator, context):

        print(f"[{threading.current_thread().name}] Request to process orders")

        orders_dest = {}

        for request in request_iterator:
            id = self._addSingleOrder(request)

            if request.destinazione not in orders_dest:
                orders_dest[request.destinazione] = [str(id)]
            else:
                orders_dest[request.destinazione].append(str(id))
        
        for dest in orders_dest.values():
            
            id_to_insert = 0

            with self.lock_id_shipments:
                id_to_insert = self.next_id_shipments
                self.next_id_shipments = self.next_id_shipments + 1

            self.shipments.insert_one({
                'id' : str(id_to_insert),
                'stato' : 'Processing',
                'ordini' : dest
            })

                    
            yield order_manager_pb2.CombinedShipment(id=str(id_to_insert), stato='Processing', ordini='-'.join(dest))
    


            
    def cancelShipment(self, request, context):

        print(f"[{threading.current_thread().name}] Request to delete shiptment")

        id_shiptment_to_delete = request.messaggio

        self.shipments.delete_one({'id' : str(id_shiptment_to_delete)})

        print(list(self.shipments.find()))

        return order_manager_pb2.StringMassage(messaggio="Deleted")        
        

if __name__ == "__main__":

    # inizializzo server
    port = "50001"

    server = grpc.server(concurrent.futures.ThreadPoolExecutor(max_workers=3))

    order_manager_pb2_grpc.add_OrderManagerServicer_to_server(OrderManager(), server)

    server.add_insecure_port("[::]:" + port)

    server.start()
    print("Server started, listening on port " + port)

    server.wait_for_termination()



    