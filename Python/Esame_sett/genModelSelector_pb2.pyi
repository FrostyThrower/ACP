from google.protobuf.internal import containers as _containers
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from collections.abc import Iterable as _Iterable
from typing import ClassVar as _ClassVar, Optional as _Optional

DESCRIPTOR: _descriptor.FileDescriptor

class Request(_message.Message):
    __slots__ = ("taskId", "taskType", "prompts", "numPrompt")
    TASKID_FIELD_NUMBER: _ClassVar[int]
    TASKTYPE_FIELD_NUMBER: _ClassVar[int]
    PROMPTS_FIELD_NUMBER: _ClassVar[int]
    NUMPROMPT_FIELD_NUMBER: _ClassVar[int]
    taskId: str
    taskType: str
    prompts: _containers.RepeatedScalarFieldContainer[str]
    numPrompt: int
    def __init__(self, taskId: _Optional[str] = ..., taskType: _Optional[str] = ..., prompts: _Optional[_Iterable[str]] = ..., numPrompt: _Optional[int] = ...) -> None: ...

class Result(_message.Message):
    __slots__ = ("confidenceCheck",)
    CONFIDENCECHECK_FIELD_NUMBER: _ClassVar[int]
    confidenceCheck: bool
    def __init__(self, confidenceCheck: _Optional[bool] = ...) -> None: ...
