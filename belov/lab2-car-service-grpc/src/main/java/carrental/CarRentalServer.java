package carrental;

import com.google.protobuf.Empty;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarRentalServer extends CarRentalGrpc.CarRentalImplBase {

    private static ServerData serverData;

    public static void main(String[] args) throws IOException, InterruptedException {
        CarRentalServer carRentalServer = new CarRentalServer();
        Server server = ServerBuilder.forPort(8080).addService(carRentalServer).build();
        server.start();
        serverData = new ServerData();
        server.awaitTermination();
    }

    @Override
    public void getCars(Empty request, StreamObserver<GetCarsResponse> responseObserver) {
        GetCarsResponse.Builder getCarsResponseBuilder = GetCarsResponse.newBuilder();
        List<CarStruct> carStructs = new ArrayList<>();

        for (CarData carData : serverData.getCars()) {
            CarStruct.Builder carStructBuilder = CarStruct.newBuilder();
            carStructBuilder.setId(carData.id);
            carStructBuilder.setModel(carData.model);
            carStructBuilder.setKm(carData.km);
            carStructBuilder.setState(carData.state);
            carStructBuilder.setCost(carData.cost);
            carStructs.add(carStructBuilder.build());
        }

        getCarsResponseBuilder.addAllCar(carStructs);
        responseObserver.onNext(getCarsResponseBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getCarsByModel(carrental.GetCarsByModelRequest request,
                               StreamObserver<GetCarsResponse> responseObserver) {
        GetCarsResponse.Builder getCarsResponseBuilder = GetCarsResponse.newBuilder();
        List<CarStruct> carStructs = new ArrayList<>();

        for (CarData carData : serverData.getByModel(request.getModel())) {
            CarStruct.Builder carStructBuilder = CarStruct.newBuilder();
            carStructBuilder.setId(carData.id);
            carStructBuilder.setModel(carData.model);
            carStructBuilder.setKm(carData.km);
            carStructBuilder.setState(carData.state);
            carStructBuilder.setCost(carData.cost);
            carStructs.add(carStructBuilder.build());
        }

        getCarsResponseBuilder.addAllCar(carStructs);
        responseObserver.onNext(getCarsResponseBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void addCar(AddCarRequest request,
                       StreamObserver<carrental.AddCarResponse> responseObserver) {
        int id = serverData.addCar(request.getModel(), request.getKm(), request.getState(), request.getCost());

        AddCarResponse.Builder addCarResponseBuilder = AddCarResponse.newBuilder();
        addCarResponseBuilder.setId(id);

        responseObserver.onNext(addCarResponseBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void changeCar(ChangeCarRequest request,
                          StreamObserver<Empty> responseObserver) {
        boolean state = serverData.changeCar(request.getId(), request.getKm(), request.getState());

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void rentCar(RentCarRequest request,
                        StreamObserver<RentCarResponse> responseObserver) {
        int cost = serverData.rentCar(request.getId(), request.getDuration());

        responseObserver.onNext(RentCarResponse.newBuilder().setCost(cost).build());
        responseObserver.onCompleted();
    }

    @Override
    public void returnCar(ReturnCarRequest request,
                          StreamObserver<Empty> responseObserver) {
        boolean status = serverData.returnCar(request.getId());

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
}
