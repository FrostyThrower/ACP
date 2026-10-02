from abc import ABC, abstractmethod

class IServer(ABC):

    @abstractmethod
    def deposita(self, id):
        pass

    @abstractmethod
    def preleva(self):
        pass