package carrental;

import com.google.protobuf.Empty;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class CarRentalServer extends CarRentalGrpc.CarRentalImplBase {

    private static ServerData serverData;

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080).addService(new CarRentalServer()).build();
        server.start();
        serverData = new ServerData();
        System.out.println("server started");
        server.awaitTermination();
    }

    @Override
    public void getCars(com.google.protobuf.Empty request,
                        io.grpc.stub.StreamObserver<carrental.GetCarsResponse> responseObserver) {
        for (CarData carData : serverData.getCars()) {
            GetCarsResponse getCarsResponse = GetCarsResponse.newBuilder()
                    .setId(carData.getId())
                    .setModel(carData.getModel())
                    .setKm(carData.getKm())
                    .setState(carData.getState())
                    .setCost(carData.getCost())
                    .build();
            responseObserver.onNext(getCarsResponse);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void getCarsByModel(carrental.GetCarsByModelRequest request,
                               io.grpc.stub.StreamObserver<carrental.GetCarsResponse> responseObserver) {
        for (CarData carData : serverData.getByModel(request.getModel())) {
            GetCarsResponse getCarsResponse = GetCarsResponse.newBuilder()
                    .setId(carData.getId())
                    .setModel(carData.getModel())
                    .setKm(carData.getKm())
                    .setState(carData.getState())
                    .setCost(carData.getCost())
                    .build();
            responseObserver.onNext(getCarsResponse);
        }
        responseObserver.onCompleted();

    }

    @Override
    public void addCar(carrental.AddCarRequest request,
                       io.grpc.stub.StreamObserver<carrental.AddCarResponse> responseObserver) {
        Short id = serverData.addCar(request.getModel(), (short) request.getKm(), request.getState(), (short) request.getCost());
        responseObserver.onNext(AddCarResponse.newBuilder().setId(id).build());
        responseObserver.onCompleted();
    }

    @Override
    public void changeCar(carrental.ChangeCarRequest request,
                          io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
        boolean state = serverData.changeCar((short) request.getId(), (short) request.getKm(), request.getState());
        System.out.println(state);
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void rentCar(carrental.RentCarRequest request,
                        io.grpc.stub.StreamObserver<carrental.RentCarResponse> responseObserver) {
        Short cost = serverData.rentCar((short) request.getId(), (short) request.getDuration());
        responseObserver.onNext(RentCarResponse.newBuilder().setCost((int) cost).build());
        responseObserver.onCompleted();
    }

    @Override
    public void returnCar(carrental.ReturnCarRequest request,
                          io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
        boolean status = serverData.returnCar((short) request.getId());
        System.out.println(status);
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
}
