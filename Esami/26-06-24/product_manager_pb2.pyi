from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Optional as _Optional

DESCRIPTOR: _descriptor.FileDescriptor

class SerialNumber(_message.Message):
    __slots__ = ("int_value",)
    INT_VALUE_FIELD_NUMBER: _ClassVar[int]
    int_value: int
    def __init__(self, int_value: _Optional[int] = ...) -> None: ...

class Empty(_message.Message):
    __slots__ = ()
    def __init__(self) -> None: ...

class Response(_message.Message):
    __slots__ = ("bool_value",)
    BOOL_VALUE_FIELD_NUMBER: _ClassVar[int]
    bool_value: bool
    def __init__(self, bool_value: _Optional[bool] = ...) -> None: ...
