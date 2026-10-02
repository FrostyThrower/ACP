from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Optional as _Optional

DESCRIPTOR: _descriptor.FileDescriptor

class Empty(_message.Message):
    __slots__ = ()
    def __init__(self) -> None: ...

class Ack(_message.Message):
    __slots__ = ("result",)
    RESULT_FIELD_NUMBER: _ClassVar[int]
    result: str
    def __init__(self, result: _Optional[str] = ...) -> None: ...

class Package(_message.Message):
    __slots__ = ("id", "priority", "destination")
    ID_FIELD_NUMBER: _ClassVar[int]
    PRIORITY_FIELD_NUMBER: _ClassVar[int]
    DESTINATION_FIELD_NUMBER: _ClassVar[int]
    id: int
    priority: str
    destination: str
    def __init__(self, id: _Optional[int] = ..., priority: _Optional[str] = ..., destination: _Optional[str] = ...) -> None: ...

class CollectRequest(_message.Message):
    __slots__ = ("priority", "courier_id")
    PRIORITY_FIELD_NUMBER: _ClassVar[int]
    COURIER_ID_FIELD_NUMBER: _ClassVar[int]
    priority: str
    courier_id: str
    def __init__(self, priority: _Optional[str] = ..., courier_id: _Optional[str] = ...) -> None: ...

class Status(_message.Message):
    __slots__ = ("standard_size", "express_size", "total_sorted")
    STANDARD_SIZE_FIELD_NUMBER: _ClassVar[int]
    EXPRESS_SIZE_FIELD_NUMBER: _ClassVar[int]
    TOTAL_SORTED_FIELD_NUMBER: _ClassVar[int]
    standard_size: int
    express_size: int
    total_sorted: int
    def __init__(self, standard_size: _Optional[int] = ..., express_size: _Optional[int] = ..., total_sorted: _Optional[int] = ...) -> None: ...

class DestinationRequest(_message.Message):
    __slots__ = ("destination",)
    DESTINATION_FIELD_NUMBER: _ClassVar[int]
    destination: str
    def __init__(self, destination: _Optional[str] = ...) -> None: ...
