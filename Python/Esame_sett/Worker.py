from IWorker import IWorker
from WorkerSkeleton import WorkerSkeleton
from random import randint, uniform

class Worker(IWorker):

    def __init__(self):
        pass
    
    def runTask(self, prompt: string, modello: string) -> bool:
        
        # Scrivo sul file
        with open("generation.txt", "w") as file:

            file.write("\n")
            file.write(prompt + " - " + modello + "\n")


        return bool(randint(0, 1))


    def getConfidence(self) -> bool:
        
        confidence = bool(uniform(0, 1))
        if confidence > 0.7:
            return True
        else:
            return False
            
if __name__ == "__main__":

    worker = Worker()
    skeleton = WorkerSkeleton(worker)
    skeleton.runSkeleton()
