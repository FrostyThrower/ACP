from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Optional as _Optional

DESCRIPTOR: _descriptor.FileDescriptor

class Temp(_message.Message):
    __slots__ = ("temperatura",)
    TEMPERATURA_FIELD_NUMBER: _ClassVar[int]
    temperatura: int
    def __init__(self, temperatura: _Optional[int] = ...) -> None: ...

class ReplyMedia(_message.Message):
    __slots__ = ("media",)
    MEDIA_FIELD_NUMBER: _ClassVar[int]
    media: float
    def __init__(self, media: _Optional[float] = ...) -> None: ...

class StringMessage(_message.Message):
    __slots__ = ("messge",)
    MESSGE_FIELD_NUMBER: _ClassVar[int]
    messge: str
    def __init__(self, messge: _Optional[str] = ...) -> None: ...

class Empty(_message.Message):
    __slots__ = ()
    def __init__(self) -> None: ...
