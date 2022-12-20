package techservice;

import com.google.protobuf.Empty;
import grpc.techservice.ReqAllData;
import grpc.techservice.ReqId;
import grpc.techservice.ReqIdDesc;
import grpc.techservice.ReqNewData;
import grpc.techservice.Status;
import grpc.techservice.TechServiceGrpc;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Server extends TechServiceGrpc.TechServiceImplBase {

    private final List<Request> allRequests;

    public Server() {
        allRequests = new ArrayList<>();
    }

    @Override
    public void addRequest(ReqNewData request,
                           StreamObserver<ReqId> responseObserver) {
        int id = allRequests.size();
        String status = "pending";
        Request newRequest;
        newRequest = new Request(id, status, request.getDescription(), request.getDate(), request.getName(), request.getEmail());
        allRequests.add(newRequest);
        responseObserver.onNext(ReqId.newBuilder().setId(id).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getStatus(ReqId request,
                          StreamObserver<Status> responseObserver) {
        String status = "not found";
        for (int i = 0; i < allRequests.size(); i++) {
            Request req = allRequests.get(i);
            if (req.getId() != request.getId()) {
                continue;
            }
            status = req.getStatus();
            break;
        }
        responseObserver.onNext(Status.newBuilder().setStatus(status).build());
        responseObserver.onCompleted();
    }

    @Override
    public void confirmRequest(ReqId request,
                               StreamObserver<Empty> responseObserver) {
        System.out.println("Confirmed");
    }

    @Override
    public void denyRequest(ReqIdDesc request,
                            StreamObserver<Empty> responseObserver) {
        System.out.println(request.getDescription());
        for (int i = 0; i < allRequests.size(); i++) {
            Request req = allRequests.get(i);
            if (req.getId() == request.getId()) {
                req.setStatus("pending");
                break;
            }
        }
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void getAll(Empty request,
                       StreamObserver<ReqAllData> responseObserver) {
        for (int i = 0; i < allRequests.size(); i++) {
            Request req = allRequests.get(i);
            ReqAllData.Builder builder = ReqAllData.newBuilder();
            builder.setId(req.getId());
            builder.setStatus(req.getStatus());
            builder.setDescription(req.getDescription());
            builder.setDate(req.getDate().toString());
            builder.setName(req.getName());
            builder.setEmail(req.getEmail());
            ReqAllData reqAllData = builder
                    .build();
            responseObserver.onNext(reqAllData);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void takeRequest(ReqId request,
                            StreamObserver<Empty> responseObserver) {
        for (int i = 0; i < allRequests.size(); i++) {
            Request req = allRequests.get(i);
            if (req.getId() == request.getId()) {
                req.setStatus("working");
                break;
            }
        }
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void markRequest(ReqIdDesc request,
                            StreamObserver<Empty> responseObserver) {
        for (int i = 0; i < allRequests.size(); i++) {
            Request req = allRequests.get(i);
            if (req.getId() == request.getId()) {
                req.setStatus("done");
                break;
            }
        }
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        io.grpc.Server server = ServerBuilder.forPort(getPort()).addService(new Server()).build();
        server.start();
        server.awaitTermination();
    }

    private static int getPort() {
        return 8080;
    }
}
