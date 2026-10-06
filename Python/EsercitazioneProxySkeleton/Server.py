from IMagazzino import IMagazzino
from multiprocessing import Queue, Lock, Condition
from SkeletonServer import SkeletonServer

MAX_QUEUE = 5
PATH_FILE_LAPTOP = 'laptop.txt'
PATH_FILE_SMARTPHONE = 'smartphone.txt'


class Server(SkeletonServer):

    def __init__(self):

        self.queue_laptop = []
        self.queue_smartphone = []

        self.lock_queue_laptop = Lock()
        self.lock_queue_smartphone = Lock()

        self.cv_queue_laptop_not_empty = Condition(self.lock_queue_laptop)
        self.cv_queue_laptop_not_full = Condition(self.lock_queue_laptop)

        self.cv_queue_smartphone_not_empty = Condition(self.lock_queue_smartphone)
        self.cv_queue_smartphone_not_full = Condition(self.lock_queue_smartphone)

        self.path_smartphone = 'smartphone.txt'
        self.path_laptop = 'laptop.txt'

        with open(self.path_laptop, 'w') as file:
            file.truncate(0)
        
        with open(self.path_smartphone, 'w') as file:
            file.truncate(0)


    def deposita(self, articolo: int, id: int):
        
        success = True

        if(articolo == 0): # smartphone
            
            with self.cv_queue_smartphone_not_full:
                while len(self.queue_smartphone) == MAX_QUEUE:
                    self.cv_queue_smartphone_not_full.wait()
                
                self.queue_smartphone.append(id)
                self.cv_queue_smartphone_not_empty.notify()

                with open(self.path_smartphone, 'a') as file:
                    file.write("Deposito: " + str(self.queue_smartphone) + f"add {id}" + "\n")


        elif (articolo == 1): # laptop

            with self.cv_queue_laptop_not_full:
                while len(self.queue_laptop) == MAX_QUEUE:
                    self.cv_queue_laptop_not_full.wait()
                
                self.queue_laptop.append(id)
                self.cv_queue_laptop_not_empty.notify()

                with open(self.path_laptop, 'a') as file:
                    file.write("Deposito: " + str(self.queue_laptop) + f"add {id}" + "\n")    

        else:
            print("[Server] Valore articolo non supportato")

            success = False

        print(f"[Server] Depositato {articolo}:{id}")
        return success


    def preleva(self, articolo) -> int:
        
        id = -1

        if (articolo == 0): # smartphone

            with self.cv_queue_smartphone_not_empty:
                while len(self.queue_smartphone) == 0:
                    self.cv_queue_smartphone_not_empty.wait()

                id = self.queue_smartphone.pop(0)

                self.cv_queue_smartphone_not_full.notify()

                with open(self.path_smartphone, 'a') as file:
                    file.write("Prelievo" + str(self.queue_smartphone) + f"remove {id}" + "\n")

            



            print(f"[Server] Prelevato {articolo}:{id}") 

        elif (articolo == 1): # laptop

            with self.cv_queue_laptop_not_empty:
                while len(self.queue_laptop) == 0:
                    self.cv_queue_laptop_not_empty.wait()
                
                id = self.queue_laptop.pop(0)
                self.cv_queue_laptop_not_full.notify()
                            
                with open(self.path_laptop, 'a') as file:
                    file.write("Prelievo" + str(self.queue_laptop) + f"remove {id}" + "\n")

            print(f"[Server] Prelevato {articolo}:{id}")

        else:
            print("[Server] Valore articolo non supportato")

            id = -1

        return id



if __name__ == '__main__':
    
    server = Server()
    server.run_skeleton()