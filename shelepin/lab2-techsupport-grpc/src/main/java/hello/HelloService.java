//package hello;
//
//import io.grpc.Server;
//import io.grpc.ServerBuilder;
//
//import java.io.IOException;
//
//import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
//
//public class HelloService extends HelloGrpc.HelloImplBase {
//    @Override
//    public void hello(hello.HelloRequest request,
//                      io.grpc.stub.StreamObserver<hello.HelloResponse> responseObserver) {
//        System.out.println("request.getMsg() = " + request.getMsg());
//        String response = "Hello, " + request.getWho();
//        HelloResponse helloResponse = HelloResponse.newBuilder().setMsg(response).build();
//        responseObserver.onNext(helloResponse);
//        responseObserver.onCompleted();
//    }
//
//    public static void main(String[] args) throws IOException, InterruptedException {
//        Server server = ServerBuilder.forPort(8080).addService(new HelloService()).build();
//        server.start();
//        System.out.println("server started");
//        server.awaitTermination();
//    }
//}
