package grpc.techservice;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: TechService.proto")
public final class TechServiceGrpc {

  private TechServiceGrpc() {}

  public static final String SERVICE_NAME = "grpc.ex1.TechService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<grpc.techservice.ReqNewData,
      grpc.techservice.ReqId> METHOD_ADD_REQUEST =
      io.grpc.MethodDescriptor.<grpc.techservice.ReqNewData, grpc.techservice.ReqId>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "grpc.ex1.TechService", "addRequest"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              grpc.techservice.ReqNewData.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              grpc.techservice.ReqId.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<grpc.techservice.ReqId,
      grpc.techservice.Status> METHOD_GET_STATUS =
      io.grpc.MethodDescriptor.<grpc.techservice.ReqId, grpc.techservice.Status>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "grpc.ex1.TechService", "getStatus"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              grpc.techservice.ReqId.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              grpc.techservice.Status.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<grpc.techservice.ReqId,
      com.google.protobuf.Empty> METHOD_CONFIRM_REQUEST =
      io.grpc.MethodDescriptor.<grpc.techservice.ReqId, com.google.protobuf.Empty>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "grpc.ex1.TechService", "confirmRequest"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              grpc.techservice.ReqId.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.google.protobuf.Empty.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<grpc.techservice.ReqIdDesc,
      com.google.protobuf.Empty> METHOD_DENY_REQUEST =
      io.grpc.MethodDescriptor.<grpc.techservice.ReqIdDesc, com.google.protobuf.Empty>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "grpc.ex1.TechService", "denyRequest"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              grpc.techservice.ReqIdDesc.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.google.protobuf.Empty.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      grpc.techservice.ReqAllData> METHOD_GET_ALL =
      io.grpc.MethodDescriptor.<com.google.protobuf.Empty, grpc.techservice.ReqAllData>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "grpc.ex1.TechService", "getAll"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.google.protobuf.Empty.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              grpc.techservice.ReqAllData.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<grpc.techservice.ReqId,
      com.google.protobuf.Empty> METHOD_TAKE_REQUEST =
      io.grpc.MethodDescriptor.<grpc.techservice.ReqId, com.google.protobuf.Empty>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "grpc.ex1.TechService", "takeRequest"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              grpc.techservice.ReqId.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.google.protobuf.Empty.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<grpc.techservice.ReqIdDesc,
      com.google.protobuf.Empty> METHOD_MARK_REQUEST =
      io.grpc.MethodDescriptor.<grpc.techservice.ReqIdDesc, com.google.protobuf.Empty>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "grpc.ex1.TechService", "markRequest"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              grpc.techservice.ReqIdDesc.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.google.protobuf.Empty.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TechServiceStub newStub(io.grpc.Channel channel) {
    return new TechServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TechServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TechServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TechServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TechServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class TechServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void addRequest(grpc.techservice.ReqNewData request,
        io.grpc.stub.StreamObserver<grpc.techservice.ReqId> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ADD_REQUEST, responseObserver);
    }

    /**
     */
    public void getStatus(grpc.techservice.ReqId request,
        io.grpc.stub.StreamObserver<grpc.techservice.Status> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_STATUS, responseObserver);
    }

    /**
     */
    public void confirmRequest(grpc.techservice.ReqId request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CONFIRM_REQUEST, responseObserver);
    }

    /**
     */
    public void denyRequest(grpc.techservice.ReqIdDesc request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DENY_REQUEST, responseObserver);
    }

    /**
     */
    public void getAll(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<grpc.techservice.ReqAllData> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_ALL, responseObserver);
    }

    /**
     */
    public void takeRequest(grpc.techservice.ReqId request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_TAKE_REQUEST, responseObserver);
    }

    /**
     */
    public void markRequest(grpc.techservice.ReqIdDesc request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_MARK_REQUEST, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_ADD_REQUEST,
            asyncUnaryCall(
              new MethodHandlers<
                grpc.techservice.ReqNewData,
                grpc.techservice.ReqId>(
                  this, METHODID_ADD_REQUEST)))
          .addMethod(
            METHOD_GET_STATUS,
            asyncUnaryCall(
              new MethodHandlers<
                grpc.techservice.ReqId,
                grpc.techservice.Status>(
                  this, METHODID_GET_STATUS)))
          .addMethod(
            METHOD_CONFIRM_REQUEST,
            asyncUnaryCall(
              new MethodHandlers<
                grpc.techservice.ReqId,
                com.google.protobuf.Empty>(
                  this, METHODID_CONFIRM_REQUEST)))
          .addMethod(
            METHOD_DENY_REQUEST,
            asyncUnaryCall(
              new MethodHandlers<
                grpc.techservice.ReqIdDesc,
                com.google.protobuf.Empty>(
                  this, METHODID_DENY_REQUEST)))
          .addMethod(
            METHOD_GET_ALL,
            asyncServerStreamingCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                grpc.techservice.ReqAllData>(
                  this, METHODID_GET_ALL)))
          .addMethod(
            METHOD_TAKE_REQUEST,
            asyncUnaryCall(
              new MethodHandlers<
                grpc.techservice.ReqId,
                com.google.protobuf.Empty>(
                  this, METHODID_TAKE_REQUEST)))
          .addMethod(
            METHOD_MARK_REQUEST,
            asyncUnaryCall(
              new MethodHandlers<
                grpc.techservice.ReqIdDesc,
                com.google.protobuf.Empty>(
                  this, METHODID_MARK_REQUEST)))
          .build();
    }
  }

  /**
   */
  public static final class TechServiceStub extends io.grpc.stub.AbstractStub<TechServiceStub> {
    private TechServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TechServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TechServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TechServiceStub(channel, callOptions);
    }

    /**
     */
    public void addRequest(grpc.techservice.ReqNewData request,
        io.grpc.stub.StreamObserver<grpc.techservice.ReqId> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_REQUEST, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getStatus(grpc.techservice.ReqId request,
        io.grpc.stub.StreamObserver<grpc.techservice.Status> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_STATUS, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void confirmRequest(grpc.techservice.ReqId request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CONFIRM_REQUEST, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void denyRequest(grpc.techservice.ReqIdDesc request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DENY_REQUEST, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAll(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<grpc.techservice.ReqAllData> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_GET_ALL, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void takeRequest(grpc.techservice.ReqId request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_TAKE_REQUEST, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void markRequest(grpc.techservice.ReqIdDesc request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_MARK_REQUEST, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TechServiceBlockingStub extends io.grpc.stub.AbstractStub<TechServiceBlockingStub> {
    private TechServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TechServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TechServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TechServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.techservice.ReqId addRequest(grpc.techservice.ReqNewData request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ADD_REQUEST, getCallOptions(), request);
    }

    /**
     */
    public grpc.techservice.Status getStatus(grpc.techservice.ReqId request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_STATUS, getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty confirmRequest(grpc.techservice.ReqId request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CONFIRM_REQUEST, getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty denyRequest(grpc.techservice.ReqIdDesc request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DENY_REQUEST, getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<grpc.techservice.ReqAllData> getAll(
        com.google.protobuf.Empty request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_GET_ALL, getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty takeRequest(grpc.techservice.ReqId request) {
      return blockingUnaryCall(
          getChannel(), METHOD_TAKE_REQUEST, getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty markRequest(grpc.techservice.ReqIdDesc request) {
      return blockingUnaryCall(
          getChannel(), METHOD_MARK_REQUEST, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TechServiceFutureStub extends io.grpc.stub.AbstractStub<TechServiceFutureStub> {
    private TechServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TechServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TechServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TechServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.techservice.ReqId> addRequest(
        grpc.techservice.ReqNewData request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_REQUEST, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.techservice.Status> getStatus(
        grpc.techservice.ReqId request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_STATUS, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> confirmRequest(
        grpc.techservice.ReqId request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CONFIRM_REQUEST, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> denyRequest(
        grpc.techservice.ReqIdDesc request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DENY_REQUEST, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> takeRequest(
        grpc.techservice.ReqId request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_TAKE_REQUEST, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> markRequest(
        grpc.techservice.ReqIdDesc request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_MARK_REQUEST, getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_REQUEST = 0;
  private static final int METHODID_GET_STATUS = 1;
  private static final int METHODID_CONFIRM_REQUEST = 2;
  private static final int METHODID_DENY_REQUEST = 3;
  private static final int METHODID_GET_ALL = 4;
  private static final int METHODID_TAKE_REQUEST = 5;
  private static final int METHODID_MARK_REQUEST = 6;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TechServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TechServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_REQUEST:
          serviceImpl.addRequest((grpc.techservice.ReqNewData) request,
              (io.grpc.stub.StreamObserver<grpc.techservice.ReqId>) responseObserver);
          break;
        case METHODID_GET_STATUS:
          serviceImpl.getStatus((grpc.techservice.ReqId) request,
              (io.grpc.stub.StreamObserver<grpc.techservice.Status>) responseObserver);
          break;
        case METHODID_CONFIRM_REQUEST:
          serviceImpl.confirmRequest((grpc.techservice.ReqId) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_DENY_REQUEST:
          serviceImpl.denyRequest((grpc.techservice.ReqIdDesc) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_GET_ALL:
          serviceImpl.getAll((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<grpc.techservice.ReqAllData>) responseObserver);
          break;
        case METHODID_TAKE_REQUEST:
          serviceImpl.takeRequest((grpc.techservice.ReqId) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_MARK_REQUEST:
          serviceImpl.markRequest((grpc.techservice.ReqIdDesc) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
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

  private static final class TechServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.techservice.TechServiceProto.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TechServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TechServiceDescriptorSupplier())
              .addMethod(METHOD_ADD_REQUEST)
              .addMethod(METHOD_GET_STATUS)
              .addMethod(METHOD_CONFIRM_REQUEST)
              .addMethod(METHOD_DENY_REQUEST)
              .addMethod(METHOD_GET_ALL)
              .addMethod(METHOD_TAKE_REQUEST)
              .addMethod(METHOD_MARK_REQUEST)
              .build();
        }
      }
    }
    return result;
  }
}
