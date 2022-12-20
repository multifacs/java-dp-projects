package techservice;

import com.google.protobuf.Empty;
import grpc.techservice.GetAllResponse;
import grpc.techservice.NewRequestResponse;
import grpc.techservice.StatusResponse;
import grpc.techservice.TechServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TechServiceServer extends TechServiceGrpc.TechServiceImplBase {

    private List<Request> requestList = new ArrayList<>();

    @Override
    public void newRequest(grpc.techservice.NewRequest request,
                           io.grpc.stub.StreamObserver<grpc.techservice.NewRequestResponse> responseObserver) {

        int id = requestList.size();
        String status = "pending";
        LocalDate date = LocalDate.parse(request.getDate());
        Request newRequest = new Request(id, status, request.getDescription(), date, request.getName(), request.getEmail());
        requestList.add(newRequest);
        System.out.println("requestList = " + requestList);

        NewRequestResponse newRequestResponse = NewRequestResponse.newBuilder().setId(id).build();
        responseObserver.onNext(newRequestResponse);
        responseObserver.onCompleted();
    }
    @Override
    public void statusRequest(grpc.techservice.StatusRequest request,
                              io.grpc.stub.StreamObserver<grpc.techservice.StatusResponse> responseObserver) {

        String status = "not found";
        for (Request req : requestList) {
            if (req.id == request.getId()) {
                status = req.status;
                break;
            }
        }

        StatusResponse statusResponse = StatusResponse.newBuilder().setStatus(status).build();
        responseObserver.onNext(statusResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void confirmRequest(grpc.techservice.ConfirmRequest request,
                               io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
        for (Request req : requestList) {
            if (req.id == request.getId()) {
                req.status = "confirmed";
                break;
            }
        }
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void denyRequest(grpc.techservice.DenyRequest request,
                            io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
        System.out.println(request.getDescription());
        for (Request req : requestList) {
            if (req.id == request.getId()) {
                req.status = "pending";
                break;
            }
        }
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void getAllRequest(com.google.protobuf.Empty request,
                              io.grpc.stub.StreamObserver<grpc.techservice.GetAllResponse> responseObserver) {
        for (Request req : requestList) {
            GetAllResponse getAllResponse = GetAllResponse.newBuilder()
                    .setId(req.id)
                    .setStatus(req.status)
                    .setDescription(req.description)
                    .setDate(req.date.toString())
                    .setName(req.name)
                    .setEmail(req.email)
                    .build();
            responseObserver.onNext(getAllResponse);
        }
        responseObserver.onCompleted();

    }

    @Override
    public void takeRequest(grpc.techservice.TakeRequest request,
                            io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
        for (Request req : requestList) {
            if (req.id == request.getId()) {
                req.status = "working";
                break;
            }
        }
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void doneRequest(grpc.techservice.DoneRequest request,
                            io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
        for (Request req : requestList) {
            if (req.id == request.getId()) {
                req.status = "done";
                break;
            }
        }
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080).addService(new TechServiceServer()).build();
        server.start();
        System.out.println("server started");
        server.awaitTermination();
    }
}
