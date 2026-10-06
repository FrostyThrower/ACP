from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Optional as _Optional

DESCRIPTOR: _descriptor.FileDescriptor

class Order(_message.Message):
    __slots__ = ("id", "items", "descrizione", "prezzo", "destinazione")
    ID_FIELD_NUMBER: _ClassVar[int]
    ITEMS_FIELD_NUMBER: _ClassVar[int]
    DESCRIZIONE_FIELD_NUMBER: _ClassVar[int]
    PREZZO_FIELD_NUMBER: _ClassVar[int]
    DESTINAZIONE_FIELD_NUMBER: _ClassVar[int]
    id: str
    items: str
    descrizione: str
    prezzo: float
    destinazione: str
    def __init__(self, id: _Optional[str] = ..., items: _Optional[str] = ..., descrizione: _Optional[str] = ..., prezzo: _Optional[float] = ..., destinazione: _Optional[str] = ...) -> None: ...

class CombinedShipment(_message.Message):
    __slots__ = ("id", "stato", "ordini")
    ID_FIELD_NUMBER: _ClassVar[int]
    STATO_FIELD_NUMBER: _ClassVar[int]
    ORDINI_FIELD_NUMBER: _ClassVar[int]
    id: str
    stato: str
    ordini: str
    def __init__(self, id: _Optional[str] = ..., stato: _Optional[str] = ..., ordini: _Optional[str] = ...) -> None: ...

class StringMassage(_message.Message):
    __slots__ = ("messaggio",)
    MESSAGGIO_FIELD_NUMBER: _ClassVar[int]
    messaggio: str
    def __init__(self, messaggio: _Optional[str] = ...) -> None: ...
