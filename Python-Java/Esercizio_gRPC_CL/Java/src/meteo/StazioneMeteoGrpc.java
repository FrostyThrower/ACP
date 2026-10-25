package meteo;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.73.0)",
    comments = "Source: meteo.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class StazioneMeteoGrpc {

  private StazioneMeteoGrpc() {}

  public static final java.lang.String SERVICE_NAME = "meteo.StazioneMeteo";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<meteo.CittaRequest,
      meteo.RilevazioneReply> getRilevaTemperaturaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RilevaTemperatura",
      requestType = meteo.CittaRequest.class,
      responseType = meteo.RilevazioneReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<meteo.CittaRequest,
      meteo.RilevazioneReply> getRilevaTemperaturaMethod() {
    io.grpc.MethodDescriptor<meteo.CittaRequest, meteo.RilevazioneReply> getRilevaTemperaturaMethod;
    if ((getRilevaTemperaturaMethod = StazioneMeteoGrpc.getRilevaTemperaturaMethod) == null) {
      synchronized (StazioneMeteoGrpc.class) {
        if ((getRilevaTemperaturaMethod = StazioneMeteoGrpc.getRilevaTemperaturaMethod) == null) {
          StazioneMeteoGrpc.getRilevaTemperaturaMethod = getRilevaTemperaturaMethod =
              io.grpc.MethodDescriptor.<meteo.CittaRequest, meteo.RilevazioneReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RilevaTemperatura"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  meteo.CittaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  meteo.RilevazioneReply.getDefaultInstance()))
              .setSchemaDescriptor(new StazioneMeteoMethodDescriptorSupplier("RilevaTemperatura"))
              .build();
        }
      }
    }
    return getRilevaTemperaturaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<meteo.CittaRequest,
      meteo.RilevazioneReply> getMonitoraMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Monitora",
      requestType = meteo.CittaRequest.class,
      responseType = meteo.RilevazioneReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<meteo.CittaRequest,
      meteo.RilevazioneReply> getMonitoraMethod() {
    io.grpc.MethodDescriptor<meteo.CittaRequest, meteo.RilevazioneReply> getMonitoraMethod;
    if ((getMonitoraMethod = StazioneMeteoGrpc.getMonitoraMethod) == null) {
      synchronized (StazioneMeteoGrpc.class) {
        if ((getMonitoraMethod = StazioneMeteoGrpc.getMonitoraMethod) == null) {
          StazioneMeteoGrpc.getMonitoraMethod = getMonitoraMethod =
              io.grpc.MethodDescriptor.<meteo.CittaRequest, meteo.RilevazioneReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Monitora"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  meteo.CittaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  meteo.RilevazioneReply.getDefaultInstance()))
              .setSchemaDescriptor(new StazioneMeteoMethodDescriptorSupplier("Monitora"))
              .build();
        }
      }
    }
    return getMonitoraMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StazioneMeteoStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StazioneMeteoStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StazioneMeteoStub>() {
        @java.lang.Override
        public StazioneMeteoStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StazioneMeteoStub(channel, callOptions);
        }
      };
    return StazioneMeteoStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static StazioneMeteoBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StazioneMeteoBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StazioneMeteoBlockingV2Stub>() {
        @java.lang.Override
        public StazioneMeteoBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StazioneMeteoBlockingV2Stub(channel, callOptions);
        }
      };
    return StazioneMeteoBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StazioneMeteoBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StazioneMeteoBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StazioneMeteoBlockingStub>() {
        @java.lang.Override
        public StazioneMeteoBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StazioneMeteoBlockingStub(channel, callOptions);
        }
      };
    return StazioneMeteoBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StazioneMeteoFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StazioneMeteoFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StazioneMeteoFutureStub>() {
        @java.lang.Override
        public StazioneMeteoFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StazioneMeteoFutureStub(channel, callOptions);
        }
      };
    return StazioneMeteoFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void rilevaTemperatura(meteo.CittaRequest request,
        io.grpc.stub.StreamObserver<meteo.RilevazioneReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRilevaTemperaturaMethod(), responseObserver);
    }

    /**
     */
    default void monitora(meteo.CittaRequest request,
        io.grpc.stub.StreamObserver<meteo.RilevazioneReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMonitoraMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service StazioneMeteo.
   */
  public static abstract class StazioneMeteoImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return StazioneMeteoGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service StazioneMeteo.
   */
  public static final class StazioneMeteoStub
      extends io.grpc.stub.AbstractAsyncStub<StazioneMeteoStub> {
    private StazioneMeteoStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StazioneMeteoStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StazioneMeteoStub(channel, callOptions);
    }

    /**
     */
    public void rilevaTemperatura(meteo.CittaRequest request,
        io.grpc.stub.StreamObserver<meteo.RilevazioneReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRilevaTemperaturaMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void monitora(meteo.CittaRequest request,
        io.grpc.stub.StreamObserver<meteo.RilevazioneReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getMonitoraMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service StazioneMeteo.
   */
  public static final class StazioneMeteoBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<StazioneMeteoBlockingV2Stub> {
    private StazioneMeteoBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StazioneMeteoBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StazioneMeteoBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public meteo.RilevazioneReply rilevaTemperatura(meteo.CittaRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRilevaTemperaturaMethod(), getCallOptions(), request);
    }

    /**
     */
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/10918")
    public io.grpc.stub.BlockingClientCall<?, meteo.RilevazioneReply>
        monitora(meteo.CittaRequest request) {
      return io.grpc.stub.ClientCalls.blockingV2ServerStreamingCall(
          getChannel(), getMonitoraMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service StazioneMeteo.
   */
  public static final class StazioneMeteoBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<StazioneMeteoBlockingStub> {
    private StazioneMeteoBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StazioneMeteoBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StazioneMeteoBlockingStub(channel, callOptions);
    }

    /**
     */
    public meteo.RilevazioneReply rilevaTemperatura(meteo.CittaRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRilevaTemperaturaMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<meteo.RilevazioneReply> monitora(
        meteo.CittaRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getMonitoraMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service StazioneMeteo.
   */
  public static final class StazioneMeteoFutureStub
      extends io.grpc.stub.AbstractFutureStub<StazioneMeteoFutureStub> {
    private StazioneMeteoFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StazioneMeteoFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StazioneMeteoFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<meteo.RilevazioneReply> rilevaTemperatura(
        meteo.CittaRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRilevaTemperaturaMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_RILEVA_TEMPERATURA = 0;
  private static final int METHODID_MONITORA = 1;

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
        case METHODID_RILEVA_TEMPERATURA:
          serviceImpl.rilevaTemperatura((meteo.CittaRequest) request,
              (io.grpc.stub.StreamObserver<meteo.RilevazioneReply>) responseObserver);
          break;
        case METHODID_MONITORA:
          serviceImpl.monitora((meteo.CittaRequest) request,
              (io.grpc.stub.StreamObserver<meteo.RilevazioneReply>) responseObserver);
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
          getRilevaTemperaturaMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              meteo.CittaRequest,
              meteo.RilevazioneReply>(
                service, METHODID_RILEVA_TEMPERATURA)))
        .addMethod(
          getMonitoraMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              meteo.CittaRequest,
              meteo.RilevazioneReply>(
                service, METHODID_MONITORA)))
        .build();
  }

  private static abstract class StazioneMeteoBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StazioneMeteoBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return meteo.Meteo.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StazioneMeteo");
    }
  }

  private static final class StazioneMeteoFileDescriptorSupplier
      extends StazioneMeteoBaseDescriptorSupplier {
    StazioneMeteoFileDescriptorSupplier() {}
  }

  private static final class StazioneMeteoMethodDescriptorSupplier
      extends StazioneMeteoBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    StazioneMeteoMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (StazioneMeteoGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StazioneMeteoFileDescriptorSupplier())
              .addMethod(getRilevaTemperaturaMethod())
              .addMethod(getMonitoraMethod())
              .build();
        }
      }
    }
    return result;
  }
}
