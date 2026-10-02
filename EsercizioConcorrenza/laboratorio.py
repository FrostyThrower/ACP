from abc import ABC, abstractmethod
import time


class VolumeNonValidoError(ValueError):
    pass

class Campione(ABC):

    @classmethod
    def da_riga(cls, riga: str):      
        codice, volume_ml, priorita = riga.split(';')  
        return Campione(str(codice), float(volume_ml), int(priorita)) 

    def __init__(self, codice: str, volume_ml: float, priorita: int):
        self.codice = codice
        set_volue_ml(volume_ml)
        self.priorita = priorita

    @property
    def volume_ml(self):
        return self._volume_ml

    @volume_ml.setter
    def set_volue_ml(self, new_volume_ml):
        if(new_volume_ml <= 0):
            raise VolumeNonValidoError(f"volume non valide: {new_volume_ml}")
        else:
            self._volume_ml = new_volume_ml

    @abstractmethod
    def analizza(self):
        pass


    def __repr__(self):
        return "Codice: " + str(self.codice) + "volume_ml: " + str(self.volume_ml) + "priorita': " + str(self.priorita)

    def __lt__(self, other):
        if(type(other) is not Campione):
            raise NotImplementedError

        return self.priorita < other.priorita


    def __eq__(self, other):
        if(type(other) is not Campione):
            raise NotImplementedError

        return self.codice == other.codice

    def __hash__(self):
        return hash(self.codice)



class CampioneEmatico(Campione):

    def analizza(self) -> dict:
        time.sleep(self.volume_ml)
        return {}

class CampioneChimico(Campione):
    
    def analizza(self) -> dict:
        time.sleep(self.volume_ml)
        return {}


def leggi_percorso(percorso):
    
    with open(percorso, 'r') as file:
        
        for riga in file:
            riga.strip()
            if not riga:
                continue
            try:
                campione = Campione.da_riga(riga)
            except ValueError:
                continue
            yield campione

    