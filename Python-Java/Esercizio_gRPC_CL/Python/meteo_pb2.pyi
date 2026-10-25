from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Optional as _Optional

DESCRIPTOR: _descriptor.FileDescriptor

class CittaRequest(_message.Message):
    __slots__ = ("citta", "campioni")
    CITTA_FIELD_NUMBER: _ClassVar[int]
    CAMPIONI_FIELD_NUMBER: _ClassVar[int]
    citta: str
    campioni: int
    def __init__(self, citta: _Optional[str] = ..., campioni: _Optional[int] = ...) -> None: ...

class RilevazioneReply(_message.Message):
    __slots__ = ("citta", "temperatura_c", "timestamp_unix")
    CITTA_FIELD_NUMBER: _ClassVar[int]
    TEMPERATURA_C_FIELD_NUMBER: _ClassVar[int]
    TIMESTAMP_UNIX_FIELD_NUMBER: _ClassVar[int]
    citta: str
    temperatura_c: float
    timestamp_unix: int
    def __init__(self, citta: _Optional[str] = ..., temperatura_c: _Optional[float] = ..., timestamp_unix: _Optional[int] = ...) -> None: ...
