//package hello;
//
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//
//public class HelloClient {
//    public static void main(String[] args) {
//        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
//        HelloGrpc.HelloBlockingStub stub = HelloGrpc.newBlockingStub(channel);
//
//        HelloRequest helloRequest = HelloRequest.newBuilder().setMsg("Hello").setWho("Alex").build();
//        HelloResponse helloResponse = stub.hello(helloRequest);
//        System.out.println("helloResponse.getMsg() = " + helloResponse.getMsg());
//    }
//}
