package hairdresser;

import com.google.protobuf.Empty;
import hairshop.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HairShopServerApp extends HairShopGrpc.HairShopImplBase {

    String openingTime = "08:00";
    String closingTime = "20:00";
    static int port = 8080;

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(port).addService(new HairShopServerApp()).build();
        server.start();
        System.out.println("server started");
        server.awaitTermination();
    }

    public void newService(hairshop.NewService request,
                           io.grpc.stub.StreamObserver<hairshop.IdData> responseObserver) {
        int id = getServicesNum();
        ServiceClass service = new ServiceClass(
                id,
                request.getName(),
                request.getDur(),
                request.getCost()
        );
        boolean wasAdded = addServiceToList(service);

        if (!wasAdded) {
            IdData idData = IdData.newBuilder().setId(0).build();
            responseObserver.onNext(idData);
            responseObserver.onCompleted();
        }
        responseObserver.onNext(IdData.newBuilder().setId(id).build());
        responseObserver.onCompleted();
    }

    @Override
    public void deleteService(hairshop.IdData request,
                              io.grpc.stub.StreamObserver<hairshop.StatusData> responseObserver) {
        boolean wasDeleted = deleteServiceFromList(request.getId());
        String s = wasDeleted ? "deleted" : "wrong id";
        StatusData ss = StatusData.newBuilder().setStatus(s.toUpperCase()).build();
        responseObserver.onNext(ss);
        responseObserver.onCompleted();
    }

    @Override
    public void getServices(com.google.protobuf.Empty request,
                            io.grpc.stub.StreamObserver<hairshop.AllServices> responseObserver) {
        for (int i = 0; i < services.size(); i++) {
            AllServices allServices = AllServices
                    .newBuilder()
                    .setId(services.get(i).id)
                    .setName(services.get(i).name)
                    .setDur(services.get(i).dur)
                    .setCost(services.get(i).cost)
                    .build();
            responseObserver.onNext(allServices);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void getTime(hairshop.GetTime request,
                        io.grpc.stub.StreamObserver<hairshop.TimeData> responseObserver) {
        responseObserver.onNext(TimeData
                .newBuilder()
                .setTime(getTime(request.getDay()))
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void newAppointment(hairshop.AppointmentData request,
                               io.grpc.stub.StreamObserver<hairshop.AppointmentStatus> responseObserver) {
        int id = getAppointmentsNum();
        AppointmentClass appointment = new AppointmentClass(
                request.getDay(),
                request.getTime(),
                request.getId(),
                id
        );
        boolean wasAdded = addAppointmentToList(appointment);

        if (!wasAdded) {
            AppointmentStatus appointmentStatus = AppointmentStatus
                    .newBuilder()
                    .setCost(0)
                    .setId(0)
                    .setStatus("WRONG TIME")
                    .build();

            responseObserver.onNext(appointmentStatus);
            responseObserver.onCompleted();
            return;
        }

        ServiceClass serviceClass = services.get(0);
        for (ServiceClass s : services) {
            if (s.id == request.getId()) serviceClass = s;
        }

        AppointmentStatus appointmentStatus = AppointmentStatus
                .newBuilder()
                .setCost(serviceClass.cost)
                .setId(id)
                .setStatus("ADDED")
                .build();

        responseObserver.onNext(appointmentStatus);
        responseObserver.onCompleted();
    }

    @Override
    public void cancelAppointment(hairshop.IdData request,
                                  io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
        deleteAppointmentFromList(request.getId());
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    public List<AppointmentClass> appointments = new ArrayList<>();
    public List<ServiceClass> services = new ArrayList<>();

    public int getAppointmentsNum() {
        int id = appointments.size();
        boolean canUse = true;
        int sum = 0;
        for (AppointmentClass a : appointments) {
            sum += a.id;
            if (a.id == id) canUse = false;
        }
        if (canUse) return id;
        return sum + 1;
    }
    public int getServicesNum() {
        int id = services.size();
        boolean canUse = true;
        int sum = 0;
        for (ServiceClass a : services) {
            sum += a.id;
            if (a.id == id) canUse = false;
        }
        if (canUse) return id;
        return sum + 1;
    }
    public boolean addServiceToList(ServiceClass service) {
        boolean canAdd = true;
        for (ServiceClass a : services) {
            if (Objects.equals(a.name, service.name)) {
                canAdd = false;
                break;
            }
        }
        if (canAdd) {
            services.add(service);
            return true;
        }
        return false;
    }
    public boolean deleteServiceFromList(int id) {
        boolean wasDeleted = false;
        for (ServiceClass a : services) {
            if (Objects.equals(a.id, id)) {
                services.remove(a);
                wasDeleted = true;
            }
        }
        return wasDeleted;
    }
    public String getTime(int day) {
        List<String> times = new ArrayList<>();
        times.add(openingTime);
        if (appointments.size() == 0) {
            times.set(0, openingTime + ", " + closingTime);
            return times.get(0);
        }

        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).day == day) {
                int one = 1;
                int zero = 0;
                String curr = times.get(0);
                times.set(i, curr + "," + appointments.get(i).time / 60 + ":" + appointments.get(i).time % 60);
                int serviceId = appointments.get(i).service * one;
                int serviceDur = zero;

                for (ServiceClass s : services) {
                    if (s.id == serviceId) serviceDur = s.dur * one + zero;
                }
                int newTime = appointments.get(i).time + serviceDur;
                times.add(newTime / 60 + ":" + newTime % 60);
            }
        }
        times.set(times.size() - 1, times.get(times.size() - 1) + closingTime);

        return String.join(",", times);
    }
    public boolean addAppointmentToList(AppointmentClass appointment) {
        boolean canAdd = true;
        for (AppointmentClass a : appointments) {
            if (Objects.equals(a.time, appointment.time)) {
                canAdd = false;
                break;
            }
        }
        if (canAdd) {
            appointments.add(appointment);
            return true;
        };
        return false;
    }
    public void deleteAppointmentFromList(int id) {
        appointments.removeIf(a -> a.id == id);
    }
}
