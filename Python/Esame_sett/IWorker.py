from abc import ABC, abstractmethod

class IWorker(ABC):

    @abstractmethod
    def runTask(self, prompt, modello) -> bool:
        pass

    @abstractmethod
    def getConfidence(self) -> bool:
        pass
