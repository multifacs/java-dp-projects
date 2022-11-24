package hairsalon;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@WebService
public class HairSalonServer {

    private final List<Service> serviceList = new ArrayList<>();
    private final List<Appointment> appointmentList = new ArrayList<>();

    @WebMethod
    public String getServices() {
        StringBuilder s = new StringBuilder();
        for (Service service : serviceList) {
            s.append("id: ").append(service.getId()).append(", ");
            s.append("name: ").append(service.getName()).append(", ");
            s.append("duration: ").append(service.getDuration()).append("\n");
        }
        return s.toString();
    }

    @WebMethod
    public void addService(String name, int duration) {
        int id = serviceList.size();
        Service service = new Service(id, name, duration);
        serviceList.add(service);
    }

    @WebMethod
    public int removeService(int id) {
        Service toDelete = null;
        for (Service service : serviceList) {
            if (id == service.getId()) {
                toDelete = service;
            }
        }
        if (toDelete != null) {
            serviceList.remove(toDelete);
            return 0;
        }
        return 1;
    }

    @WebMethod
    public void addAppointment(LocalDateTime date, int service) {
        Appointment appointment = new Appointment(service, date);
        appointmentList.add(appointment);

        LocalDateTime tome = LocalDateTime.now();
        tome.toEpochSecond(OffsetDateTime.now().getOffset());
    }

    public static void main(String[] args) {
        HairSalonServer hairSalonServer = new HairSalonServer();
        Endpoint.publish("http://localhost:8888/hairsalon", hairSalonServer);

        LocalDateTime time1 = LocalDateTime.now();
        time1 = time1.minusHours(5);
        LocalDateTime time2 = time1.minusHours(1);
        System.out.println(time1);
        System.out.println(time2);
        System.out.println(time2.toEpochSecond(OffsetDateTime.now().getOffset()) - time1.toEpochSecond(OffsetDateTime.now().getOffset()));
    }
}
