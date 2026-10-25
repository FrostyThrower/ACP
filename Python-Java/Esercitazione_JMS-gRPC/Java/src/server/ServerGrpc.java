package server;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.73.0)",
    comments = "Source: server.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ServerGrpc {

  private ServerGrpc() {}

  public static final java.lang.String SERVICE_NAME = "server.Server";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<server.ServerOuterClass.Request,
      server.ServerOuterClass.Reply> getDepositaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposita",
      requestType = server.ServerOuterClass.Request.class,
      responseType = server.ServerOuterClass.Reply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.ServerOuterClass.Request,
      server.ServerOuterClass.Reply> getDepositaMethod() {
    io.grpc.MethodDescriptor<server.ServerOuterClass.Request, server.ServerOuterClass.Reply> getDepositaMethod;
    if ((getDepositaMethod = ServerGrpc.getDepositaMethod) == null) {
      synchronized (ServerGrpc.class) {
        if ((getDepositaMethod = ServerGrpc.getDepositaMethod) == null) {
          ServerGrpc.getDepositaMethod = getDepositaMethod =
              io.grpc.MethodDescriptor.<server.ServerOuterClass.Request, server.ServerOuterClass.Reply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deposita"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.ServerOuterClass.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.ServerOuterClass.Reply.getDefaultInstance()))
              .setSchemaDescriptor(new ServerMethodDescriptorSupplier("Deposita"))
              .build();
        }
      }
    }
    return getDepositaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.ServerOuterClass.Empty,
      server.ServerOuterClass.Reply> getPrelevaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Preleva",
      requestType = server.ServerOuterClass.Empty.class,
      responseType = server.ServerOuterClass.Reply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.ServerOuterClass.Empty,
      server.ServerOuterClass.Reply> getPrelevaMethod() {
    io.grpc.MethodDescriptor<server.ServerOuterClass.Empty, server.ServerOuterClass.Reply> getPrelevaMethod;
    if ((getPrelevaMethod = ServerGrpc.getPrelevaMethod) == null) {
      synchronized (ServerGrpc.class) {
        if ((getPrelevaMethod = ServerGrpc.getPrelevaMethod) == null) {
          ServerGrpc.getPrelevaMethod = getPrelevaMethod =
              io.grpc.MethodDescriptor.<server.ServerOuterClass.Empty, server.ServerOuterClass.Reply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Preleva"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.ServerOuterClass.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.ServerOuterClass.Reply.getDefaultInstance()))
              .setSchemaDescriptor(new ServerMethodDescriptorSupplier("Preleva"))
              .build();
        }
      }
    }
    return getPrelevaMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ServerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServerStub>() {
        @java.lang.Override
        public ServerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServerStub(channel, callOptions);
        }
      };
    return ServerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static ServerBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServerBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServerBlockingV2Stub>() {
        @java.lang.Override
        public ServerBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServerBlockingV2Stub(channel, callOptions);
        }
      };
    return ServerBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ServerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServerBlockingStub>() {
        @java.lang.Override
        public ServerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServerBlockingStub(channel, callOptions);
        }
      };
    return ServerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ServerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ServerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ServerFutureStub>() {
        @java.lang.Override
        public ServerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ServerFutureStub(channel, callOptions);
        }
      };
    return ServerFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void deposita(server.ServerOuterClass.Request request,
        io.grpc.stub.StreamObserver<server.ServerOuterClass.Reply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDepositaMethod(), responseObserver);
    }

    /**
     */
    default void preleva(server.ServerOuterClass.Empty request,
        io.grpc.stub.StreamObserver<server.ServerOuterClass.Reply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPrelevaMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Server.
   */
  public static abstract class ServerImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ServerGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Server.
   */
  public static final class ServerStub
      extends io.grpc.stub.AbstractAsyncStub<ServerStub> {
    private ServerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServerStub(channel, callOptions);
    }

    /**
     */
    public void deposita(server.ServerOuterClass.Request request,
        io.grpc.stub.StreamObserver<server.ServerOuterClass.Reply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDepositaMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void preleva(server.ServerOuterClass.Empty request,
        io.grpc.stub.StreamObserver<server.ServerOuterClass.Reply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPrelevaMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Server.
   */
  public static final class ServerBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<ServerBlockingV2Stub> {
    private ServerBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServerBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServerBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public server.ServerOuterClass.Reply deposita(server.ServerOuterClass.Request request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDepositaMethod(), getCallOptions(), request);
    }

    /**
     */
    public server.ServerOuterClass.Reply preleva(server.ServerOuterClass.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPrelevaMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service Server.
   */
  public static final class ServerBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ServerBlockingStub> {
    private ServerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServerBlockingStub(channel, callOptions);
    }

    /**
     */
    public server.ServerOuterClass.Reply deposita(server.ServerOuterClass.Request request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDepositaMethod(), getCallOptions(), request);
    }

    /**
     */
    public server.ServerOuterClass.Reply preleva(server.ServerOuterClass.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPrelevaMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Server.
   */
  public static final class ServerFutureStub
      extends io.grpc.stub.AbstractFutureStub<ServerFutureStub> {
    private ServerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ServerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ServerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.ServerOuterClass.Reply> deposita(
        server.ServerOuterClass.Request request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDepositaMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.ServerOuterClass.Reply> preleva(
        server.ServerOuterClass.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPrelevaMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DEPOSITA = 0;
  private static final int METHODID_PRELEVA = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DEPOSITA:
          serviceImpl.deposita((server.ServerOuterClass.Request) request,
              (io.grpc.stub.StreamObserver<server.ServerOuterClass.Reply>) responseObserver);
          break;
        case METHODID_PRELEVA:
          serviceImpl.preleva((server.ServerOuterClass.Empty) request,
              (io.grpc.stub.StreamObserver<server.ServerOuterClass.Reply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getDepositaMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              server.ServerOuterClass.Request,
              server.ServerOuterClass.Reply>(
                service, METHODID_DEPOSITA)))
        .addMethod(
          getPrelevaMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              server.ServerOuterClass.Empty,
              server.ServerOuterClass.Reply>(
                service, METHODID_PRELEVA)))
        .build();
  }

  private static abstract class ServerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ServerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return server.ServerOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Server");
    }
  }

  private static final class ServerFileDescriptorSupplier
      extends ServerBaseDescriptorSupplier {
    ServerFileDescriptorSupplier() {}
  }

  private static final class ServerMethodDescriptorSupplier
      extends ServerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ServerMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ServerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ServerFileDescriptorSupplier())
              .addMethod(getDepositaMethod())
              .addMethod(getPrelevaMethod())
              .build();
        }
      }
    }
    return result;
  }
}
