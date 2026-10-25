from abc import ABC, abstractmethod

class IService(ABC):

    @abstractmethod
    def deposita(self, id_articolo: int) -> str:
        pass

    @abstractmethod
    def preleva(self) -> int:
        pass