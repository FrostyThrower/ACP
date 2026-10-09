package sensor;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.73.0)",
    comments = "Source: sensor.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SensorHubGrpc {

  private SensorHubGrpc() {}

  public static final java.lang.String SERVICE_NAME = "sensor.SensorHub";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<sensor.Reading,
      sensor.Ack> getRegisterReadingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterReading",
      requestType = sensor.Reading.class,
      responseType = sensor.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sensor.Reading,
      sensor.Ack> getRegisterReadingMethod() {
    io.grpc.MethodDescriptor<sensor.Reading, sensor.Ack> getRegisterReadingMethod;
    if ((getRegisterReadingMethod = SensorHubGrpc.getRegisterReadingMethod) == null) {
      synchronized (SensorHubGrpc.class) {
        if ((getRegisterReadingMethod = SensorHubGrpc.getRegisterReadingMethod) == null) {
          SensorHubGrpc.getRegisterReadingMethod = getRegisterReadingMethod =
              io.grpc.MethodDescriptor.<sensor.Reading, sensor.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterReading"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sensor.Reading.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sensor.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new SensorHubMethodDescriptorSupplier("RegisterReading"))
              .build();
        }
      }
    }
    return getRegisterReadingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sensor.SensorQuery,
      sensor.Reading> getStreamReadingsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamReadings",
      requestType = sensor.SensorQuery.class,
      responseType = sensor.Reading.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<sensor.SensorQuery,
      sensor.Reading> getStreamReadingsMethod() {
    io.grpc.MethodDescriptor<sensor.SensorQuery, sensor.Reading> getStreamReadingsMethod;
    if ((getStreamReadingsMethod = SensorHubGrpc.getStreamReadingsMethod) == null) {
      synchronized (SensorHubGrpc.class) {
        if ((getStreamReadingsMethod = SensorHubGrpc.getStreamReadingsMethod) == null) {
          SensorHubGrpc.getStreamReadingsMethod = getStreamReadingsMethod =
              io.grpc.MethodDescriptor.<sensor.SensorQuery, sensor.Reading>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamReadings"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sensor.SensorQuery.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sensor.Reading.getDefaultInstance()))
              .setSchemaDescriptor(new SensorHubMethodDescriptorSupplier("StreamReadings"))
              .build();
        }
      }
    }
    return getStreamReadingsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<sensor.SensorQuery,
      sensor.Stats> getComputeStatsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ComputeStats",
      requestType = sensor.SensorQuery.class,
      responseType = sensor.Stats.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<sensor.SensorQuery,
      sensor.Stats> getComputeStatsMethod() {
    io.grpc.MethodDescriptor<sensor.SensorQuery, sensor.Stats> getComputeStatsMethod;
    if ((getComputeStatsMethod = SensorHubGrpc.getComputeStatsMethod) == null) {
      synchronized (SensorHubGrpc.class) {
        if ((getComputeStatsMethod = SensorHubGrpc.getComputeStatsMethod) == null) {
          SensorHubGrpc.getComputeStatsMethod = getComputeStatsMethod =
              io.grpc.MethodDescriptor.<sensor.SensorQuery, sensor.Stats>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ComputeStats"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sensor.SensorQuery.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  sensor.Stats.getDefaultInstance()))
              .setSchemaDescriptor(new SensorHubMethodDescriptorSupplier("ComputeStats"))
              .build();
        }
      }
    }
    return getComputeStatsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SensorHubStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SensorHubStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SensorHubStub>() {
        @java.lang.Override
        public SensorHubStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SensorHubStub(channel, callOptions);
        }
      };
    return SensorHubStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static SensorHubBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SensorHubBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SensorHubBlockingV2Stub>() {
        @java.lang.Override
        public SensorHubBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SensorHubBlockingV2Stub(channel, callOptions);
        }
      };
    return SensorHubBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SensorHubBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SensorHubBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SensorHubBlockingStub>() {
        @java.lang.Override
        public SensorHubBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SensorHubBlockingStub(channel, callOptions);
        }
      };
    return SensorHubBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SensorHubFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SensorHubFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SensorHubFutureStub>() {
        @java.lang.Override
        public SensorHubFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SensorHubFutureStub(channel, callOptions);
        }
      };
    return SensorHubFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * 3.1 — unaria: registra una singola lettura e restituisce Ack
     * </pre>
     */
    default void registerReading(sensor.Reading request,
        io.grpc.stub.StreamObserver<sensor.Ack> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegisterReadingMethod(), responseObserver);
    }

    /**
     * <pre>
     * 3.2 — server streaming: COMPLETA LA FIRMA.
     * </pre>
     */
    default void streamReadings(sensor.SensorQuery request,
        io.grpc.stub.StreamObserver<sensor.Reading> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStreamReadingsMethod(), responseObserver);
    }

    /**
     * <pre>
     * 3.3 — unaria, ma computazionalmente lenta: calcola le statistiche
     * di un sensore. Simula il costo con un Thread.sleep(2000).
     * </pre>
     */
    default void computeStats(sensor.SensorQuery request,
        io.grpc.stub.StreamObserver<sensor.Stats> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getComputeStatsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service SensorHub.
   */
  public static abstract class SensorHubImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return SensorHubGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service SensorHub.
   */
  public static final class SensorHubStub
      extends io.grpc.stub.AbstractAsyncStub<SensorHubStub> {
    private SensorHubStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SensorHubStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SensorHubStub(channel, callOptions);
    }

    /**
     * <pre>
     * 3.1 — unaria: registra una singola lettura e restituisce Ack
     * </pre>
     */
    public void registerReading(sensor.Reading request,
        io.grpc.stub.StreamObserver<sensor.Ack> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRegisterReadingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 3.2 — server streaming: COMPLETA LA FIRMA.
     * </pre>
     */
    public void streamReadings(sensor.SensorQuery request,
        io.grpc.stub.StreamObserver<sensor.Reading> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getStreamReadingsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 3.3 — unaria, ma computazionalmente lenta: calcola le statistiche
     * di un sensore. Simula il costo con un Thread.sleep(2000).
     * </pre>
     */
    public void computeStats(sensor.SensorQuery request,
        io.grpc.stub.StreamObserver<sensor.Stats> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getComputeStatsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service SensorHub.
   */
  public static final class SensorHubBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<SensorHubBlockingV2Stub> {
    private SensorHubBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SensorHubBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SensorHubBlockingV2Stub(channel, callOptions);
    }

    /**
     * <pre>
     * 3.1 — unaria: registra una singola lettura e restituisce Ack
     * </pre>
     */
    public sensor.Ack registerReading(sensor.Reading request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegisterReadingMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 3.2 — server streaming: COMPLETA LA FIRMA.
     * </pre>
     */
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/10918")
    public io.grpc.stub.BlockingClientCall<?, sensor.Reading>
        streamReadings(sensor.SensorQuery request) {
      return io.grpc.stub.ClientCalls.blockingV2ServerStreamingCall(
          getChannel(), getStreamReadingsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 3.3 — unaria, ma computazionalmente lenta: calcola le statistiche
     * di un sensore. Simula il costo con un Thread.sleep(2000).
     * </pre>
     */
    public sensor.Stats computeStats(sensor.SensorQuery request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getComputeStatsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service SensorHub.
   */
  public static final class SensorHubBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SensorHubBlockingStub> {
    private SensorHubBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SensorHubBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SensorHubBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * 3.1 — unaria: registra una singola lettura e restituisce Ack
     * </pre>
     */
    public sensor.Ack registerReading(sensor.Reading request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegisterReadingMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 3.2 — server streaming: COMPLETA LA FIRMA.
     * </pre>
     */
    public java.util.Iterator<sensor.Reading> streamReadings(
        sensor.SensorQuery request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getStreamReadingsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 3.3 — unaria, ma computazionalmente lenta: calcola le statistiche
     * di un sensore. Simula il costo con un Thread.sleep(2000).
     * </pre>
     */
    public sensor.Stats computeStats(sensor.SensorQuery request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getComputeStatsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service SensorHub.
   */
  public static final class SensorHubFutureStub
      extends io.grpc.stub.AbstractFutureStub<SensorHubFutureStub> {
    private SensorHubFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SensorHubFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SensorHubFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * 3.1 — unaria: registra una singola lettura e restituisce Ack
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<sensor.Ack> registerReading(
        sensor.Reading request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRegisterReadingMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * 3.3 — unaria, ma computazionalmente lenta: calcola le statistiche
     * di un sensore. Simula il costo con un Thread.sleep(2000).
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<sensor.Stats> computeStats(
        sensor.SensorQuery request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getComputeStatsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_READING = 0;
  private static final int METHODID_STREAM_READINGS = 1;
  private static final int METHODID_COMPUTE_STATS = 2;

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
        case METHODID_REGISTER_READING:
          serviceImpl.registerReading((sensor.Reading) request,
              (io.grpc.stub.StreamObserver<sensor.Ack>) responseObserver);
          break;
        case METHODID_STREAM_READINGS:
          serviceImpl.streamReadings((sensor.SensorQuery) request,
              (io.grpc.stub.StreamObserver<sensor.Reading>) responseObserver);
          break;
        case METHODID_COMPUTE_STATS:
          serviceImpl.computeStats((sensor.SensorQuery) request,
              (io.grpc.stub.StreamObserver<sensor.Stats>) responseObserver);
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
          getRegisterReadingMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sensor.Reading,
              sensor.Ack>(
                service, METHODID_REGISTER_READING)))
        .addMethod(
          getStreamReadingsMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              sensor.SensorQuery,
              sensor.Reading>(
                service, METHODID_STREAM_READINGS)))
        .addMethod(
          getComputeStatsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              sensor.SensorQuery,
              sensor.Stats>(
                service, METHODID_COMPUTE_STATS)))
        .build();
  }

  private static abstract class SensorHubBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SensorHubBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return sensor.Sensor.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SensorHub");
    }
  }

  private static final class SensorHubFileDescriptorSupplier
      extends SensorHubBaseDescriptorSupplier {
    SensorHubFileDescriptorSupplier() {}
  }

  private static final class SensorHubMethodDescriptorSupplier
      extends SensorHubBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    SensorHubMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (SensorHubGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SensorHubFileDescriptorSupplier())
              .addMethod(getRegisterReadingMethod())
              .addMethod(getStreamReadingsMethod())
              .addMethod(getComputeStatsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
