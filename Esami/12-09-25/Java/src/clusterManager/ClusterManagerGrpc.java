package clusterManager;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.73.0)",
    comments = "Source: cluster_manager.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ClusterManagerGrpc {

  private ClusterManagerGrpc() {}

  public static final java.lang.String SERVICE_NAME = "clusterManager.ClusterManager";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<clusterManager.Request,
      clusterManager.Empty> getDeployMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deploy",
      requestType = clusterManager.Request.class,
      responseType = clusterManager.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<clusterManager.Request,
      clusterManager.Empty> getDeployMethod() {
    io.grpc.MethodDescriptor<clusterManager.Request, clusterManager.Empty> getDeployMethod;
    if ((getDeployMethod = ClusterManagerGrpc.getDeployMethod) == null) {
      synchronized (ClusterManagerGrpc.class) {
        if ((getDeployMethod = ClusterManagerGrpc.getDeployMethod) == null) {
          ClusterManagerGrpc.getDeployMethod = getDeployMethod =
              io.grpc.MethodDescriptor.<clusterManager.Request, clusterManager.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deploy"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  clusterManager.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  clusterManager.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ClusterManagerMethodDescriptorSupplier("deploy"))
              .build();
        }
      }
    }
    return getDeployMethod;
  }

  private static volatile io.grpc.MethodDescriptor<clusterManager.StringMessage,
      clusterManager.Empty> getStopAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "stop_all",
      requestType = clusterManager.StringMessage.class,
      responseType = clusterManager.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<clusterManager.StringMessage,
      clusterManager.Empty> getStopAllMethod() {
    io.grpc.MethodDescriptor<clusterManager.StringMessage, clusterManager.Empty> getStopAllMethod;
    if ((getStopAllMethod = ClusterManagerGrpc.getStopAllMethod) == null) {
      synchronized (ClusterManagerGrpc.class) {
        if ((getStopAllMethod = ClusterManagerGrpc.getStopAllMethod) == null) {
          ClusterManagerGrpc.getStopAllMethod = getStopAllMethod =
              io.grpc.MethodDescriptor.<clusterManager.StringMessage, clusterManager.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "stop_all"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  clusterManager.StringMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  clusterManager.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new ClusterManagerMethodDescriptorSupplier("stop_all"))
              .build();
        }
      }
    }
    return getStopAllMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ClusterManagerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClusterManagerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClusterManagerStub>() {
        @java.lang.Override
        public ClusterManagerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClusterManagerStub(channel, callOptions);
        }
      };
    return ClusterManagerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static ClusterManagerBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClusterManagerBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClusterManagerBlockingV2Stub>() {
        @java.lang.Override
        public ClusterManagerBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClusterManagerBlockingV2Stub(channel, callOptions);
        }
      };
    return ClusterManagerBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ClusterManagerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClusterManagerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClusterManagerBlockingStub>() {
        @java.lang.Override
        public ClusterManagerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClusterManagerBlockingStub(channel, callOptions);
        }
      };
    return ClusterManagerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ClusterManagerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClusterManagerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClusterManagerFutureStub>() {
        @java.lang.Override
        public ClusterManagerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClusterManagerFutureStub(channel, callOptions);
        }
      };
    return ClusterManagerFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void deploy(clusterManager.Request request,
        io.grpc.stub.StreamObserver<clusterManager.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeployMethod(), responseObserver);
    }

    /**
     */
    default void stopAll(clusterManager.StringMessage request,
        io.grpc.stub.StreamObserver<clusterManager.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStopAllMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ClusterManager.
   */
  public static abstract class ClusterManagerImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ClusterManagerGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ClusterManager.
   */
  public static final class ClusterManagerStub
      extends io.grpc.stub.AbstractAsyncStub<ClusterManagerStub> {
    private ClusterManagerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClusterManagerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClusterManagerStub(channel, callOptions);
    }

    /**
     */
    public void deploy(clusterManager.Request request,
        io.grpc.stub.StreamObserver<clusterManager.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeployMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void stopAll(clusterManager.StringMessage request,
        io.grpc.stub.StreamObserver<clusterManager.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getStopAllMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ClusterManager.
   */
  public static final class ClusterManagerBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<ClusterManagerBlockingV2Stub> {
    private ClusterManagerBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClusterManagerBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClusterManagerBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public clusterManager.Empty deploy(clusterManager.Request request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeployMethod(), getCallOptions(), request);
    }

    /**
     */
    public clusterManager.Empty stopAll(clusterManager.StringMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStopAllMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service ClusterManager.
   */
  public static final class ClusterManagerBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ClusterManagerBlockingStub> {
    private ClusterManagerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClusterManagerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClusterManagerBlockingStub(channel, callOptions);
    }

    /**
     */
    public clusterManager.Empty deploy(clusterManager.Request request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeployMethod(), getCallOptions(), request);
    }

    /**
     */
    public clusterManager.Empty stopAll(clusterManager.StringMessage request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getStopAllMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ClusterManager.
   */
  public static final class ClusterManagerFutureStub
      extends io.grpc.stub.AbstractFutureStub<ClusterManagerFutureStub> {
    private ClusterManagerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClusterManagerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClusterManagerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<clusterManager.Empty> deploy(
        clusterManager.Request request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeployMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<clusterManager.Empty> stopAll(
        clusterManager.StringMessage request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getStopAllMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DEPLOY = 0;
  private static final int METHODID_STOP_ALL = 1;

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
        case METHODID_DEPLOY:
          serviceImpl.deploy((clusterManager.Request) request,
              (io.grpc.stub.StreamObserver<clusterManager.Empty>) responseObserver);
          break;
        case METHODID_STOP_ALL:
          serviceImpl.stopAll((clusterManager.StringMessage) request,
              (io.grpc.stub.StreamObserver<clusterManager.Empty>) responseObserver);
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
          getDeployMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              clusterManager.Request,
              clusterManager.Empty>(
                service, METHODID_DEPLOY)))
        .addMethod(
          getStopAllMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              clusterManager.StringMessage,
              clusterManager.Empty>(
                service, METHODID_STOP_ALL)))
        .build();
  }

  private static abstract class ClusterManagerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ClusterManagerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return clusterManager.ClusterManagerOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ClusterManager");
    }
  }

  private static final class ClusterManagerFileDescriptorSupplier
      extends ClusterManagerBaseDescriptorSupplier {
    ClusterManagerFileDescriptorSupplier() {}
  }

  private static final class ClusterManagerMethodDescriptorSupplier
      extends ClusterManagerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ClusterManagerMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ClusterManagerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ClusterManagerFileDescriptorSupplier())
              .addMethod(getDeployMethod())
              .addMethod(getStopAllMethod())
              .build();
        }
      }
    }
    return result;
  }
}
