from abc import ABC, abstractmethod

class IWorker(ABC):

    @abstractmethod
    def runTask(self, prompt: string, modello: string) -> bool:
        pass

    @abstractmethod
    def getConfidence(self) -> bool:
        pass
