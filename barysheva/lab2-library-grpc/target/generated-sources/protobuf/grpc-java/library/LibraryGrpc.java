package library;

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
    comments = "Source: Library.proto")
public final class LibraryGrpc {

  private LibraryGrpc() {}

  public static final String SERVICE_NAME = "library.Library";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      library.GetBooksResponse> METHOD_GET_BOOKS =
      io.grpc.MethodDescriptor.<com.google.protobuf.Empty, library.GetBooksResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "library.Library", "getBooks"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.google.protobuf.Empty.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              library.GetBooksResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<library.AddBookRequest,
      com.google.protobuf.Empty> METHOD_ADD_BOOK =
      io.grpc.MethodDescriptor.<library.AddBookRequest, com.google.protobuf.Empty>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "library.Library", "addBook"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              library.AddBookRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.google.protobuf.Empty.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<library.AddCopyRequest,
      com.google.protobuf.Empty> METHOD_ADD_COPY =
      io.grpc.MethodDescriptor.<library.AddCopyRequest, com.google.protobuf.Empty>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "library.Library", "addCopy"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              library.AddCopyRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.google.protobuf.Empty.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<library.TakeBookRequest,
      library.TakeBookResponse> METHOD_TAKE_BOOK =
      io.grpc.MethodDescriptor.<library.TakeBookRequest, library.TakeBookResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "library.Library", "takeBook"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              library.TakeBookRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              library.TakeBookResponse.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<library.ReturnBookRequest,
      com.google.protobuf.Empty> METHOD_RETURN_BOOK =
      io.grpc.MethodDescriptor.<library.ReturnBookRequest, com.google.protobuf.Empty>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "library.Library", "returnBook"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              library.ReturnBookRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.google.protobuf.Empty.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LibraryStub newStub(io.grpc.Channel channel) {
    return new LibraryStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LibraryBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new LibraryBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LibraryFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new LibraryFutureStub(channel);
  }

  /**
   */
  public static abstract class LibraryImplBase implements io.grpc.BindableService {

    /**
     */
    public void getBooks(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<library.GetBooksResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_BOOKS, responseObserver);
    }

    /**
     */
    public void addBook(library.AddBookRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ADD_BOOK, responseObserver);
    }

    /**
     */
    public void addCopy(library.AddCopyRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ADD_COPY, responseObserver);
    }

    /**
     */
    public void takeBook(library.TakeBookRequest request,
        io.grpc.stub.StreamObserver<library.TakeBookResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_TAKE_BOOK, responseObserver);
    }

    /**
     */
    public void returnBook(library.ReturnBookRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_RETURN_BOOK, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_BOOKS,
            asyncServerStreamingCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                library.GetBooksResponse>(
                  this, METHODID_GET_BOOKS)))
          .addMethod(
            METHOD_ADD_BOOK,
            asyncUnaryCall(
              new MethodHandlers<
                library.AddBookRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_ADD_BOOK)))
          .addMethod(
            METHOD_ADD_COPY,
            asyncUnaryCall(
              new MethodHandlers<
                library.AddCopyRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_ADD_COPY)))
          .addMethod(
            METHOD_TAKE_BOOK,
            asyncUnaryCall(
              new MethodHandlers<
                library.TakeBookRequest,
                library.TakeBookResponse>(
                  this, METHODID_TAKE_BOOK)))
          .addMethod(
            METHOD_RETURN_BOOK,
            asyncUnaryCall(
              new MethodHandlers<
                library.ReturnBookRequest,
                com.google.protobuf.Empty>(
                  this, METHODID_RETURN_BOOK)))
          .build();
    }
  }

  /**
   */
  public static final class LibraryStub extends io.grpc.stub.AbstractStub<LibraryStub> {
    private LibraryStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LibraryStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LibraryStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LibraryStub(channel, callOptions);
    }

    /**
     */
    public void getBooks(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<library.GetBooksResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_GET_BOOKS, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addBook(library.AddBookRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_BOOK, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addCopy(library.AddCopyRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_COPY, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void takeBook(library.TakeBookRequest request,
        io.grpc.stub.StreamObserver<library.TakeBookResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_TAKE_BOOK, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void returnBook(library.ReturnBookRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_RETURN_BOOK, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class LibraryBlockingStub extends io.grpc.stub.AbstractStub<LibraryBlockingStub> {
    private LibraryBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LibraryBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LibraryBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LibraryBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<library.GetBooksResponse> getBooks(
        com.google.protobuf.Empty request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_GET_BOOKS, getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty addBook(library.AddBookRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ADD_BOOK, getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty addCopy(library.AddCopyRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ADD_COPY, getCallOptions(), request);
    }

    /**
     */
    public library.TakeBookResponse takeBook(library.TakeBookRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_TAKE_BOOK, getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty returnBook(library.ReturnBookRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_RETURN_BOOK, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class LibraryFutureStub extends io.grpc.stub.AbstractStub<LibraryFutureStub> {
    private LibraryFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LibraryFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LibraryFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LibraryFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> addBook(
        library.AddBookRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_BOOK, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> addCopy(
        library.AddCopyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_COPY, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<library.TakeBookResponse> takeBook(
        library.TakeBookRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_TAKE_BOOK, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> returnBook(
        library.ReturnBookRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_RETURN_BOOK, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_BOOKS = 0;
  private static final int METHODID_ADD_BOOK = 1;
  private static final int METHODID_ADD_COPY = 2;
  private static final int METHODID_TAKE_BOOK = 3;
  private static final int METHODID_RETURN_BOOK = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LibraryImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LibraryImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BOOKS:
          serviceImpl.getBooks((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<library.GetBooksResponse>) responseObserver);
          break;
        case METHODID_ADD_BOOK:
          serviceImpl.addBook((library.AddBookRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_ADD_COPY:
          serviceImpl.addCopy((library.AddCopyRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_TAKE_BOOK:
          serviceImpl.takeBook((library.TakeBookRequest) request,
              (io.grpc.stub.StreamObserver<library.TakeBookResponse>) responseObserver);
          break;
        case METHODID_RETURN_BOOK:
          serviceImpl.returnBook((library.ReturnBookRequest) request,
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

  private static final class LibraryDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return library.LibraryProto.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (LibraryGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LibraryDescriptorSupplier())
              .addMethod(METHOD_GET_BOOKS)
              .addMethod(METHOD_ADD_BOOK)
              .addMethod(METHOD_ADD_COPY)
              .addMethod(METHOD_TAKE_BOOK)
              .addMethod(METHOD_RETURN_BOOK)
              .build();
        }
      }
    }
    return result;
  }
}
