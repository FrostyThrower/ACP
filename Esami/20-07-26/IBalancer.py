from abc import ABC, abstractmethod

class IBalancer(ABC):

    @abstractmethod
    def request(self, method: str, domain: str, endpoint: str) -> int:
        pass