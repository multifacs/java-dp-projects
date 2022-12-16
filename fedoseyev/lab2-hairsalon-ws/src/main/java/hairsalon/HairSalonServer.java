package hairsalon;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
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
            s.append("cost: ").append(service.getCost()).append("\n");
        }
        return s.toString();
    }

    @WebMethod
    public int addService(String name, int duration, int cost) {
        int id = serviceList.size();
        Service service = new Service(id, name, duration, cost);
        serviceList.add(service);
        return id;
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
    public String getFreeTime(String date) {
        List<String> times = new ArrayList<>();
        times.add("00:00");
        if (appointmentList.size() == 0) {
            times.set(0, "00:00, 24:00");

            return times.get(0);
        }

        for (int i = 0; i < appointmentList.size(); i++) {
            if (appointmentList.get(i).getDate() == date) {
                String curr = times.get(0);
                times.set(i, curr + "," + appointmentList.get(i).getHour() + ":" + appointmentList.get(i).getMin());
                int serviceId = appointmentList.get(i).getService();
                int serviceDur = 0;

                for (Service s : serviceList) {
                    if (s.getId() == serviceId) serviceDur = s.getDuration();
                }
                int newHour = appointmentList.get(i).getHour() + serviceDur / 60;
                int newMin = appointmentList.get(i).getMin() + serviceDur % 60;
                times.add(newHour + ":" + newMin);
            }
        }
        times.set(times.size() - 1, times.get(times.size() - 1) + ", 24:00");

        String result = String.join(",", times);
        return result;
    }

    @WebMethod
    public String makeAppointment(String date, int hour, int min, int service) {
        for (Appointment app : appointmentList) {
            if (app.getDate() == date && app.getHour() == hour && app.getMin() == min) {
                return "DENIED";
            }
        }
        int id = appointmentList.size();
        Appointment appointment = new Appointment(id, service, date, hour, min);
        appointmentList.add(appointment);
        return "ACCEPTED";
    }

    @WebMethod
    public void removeAppointment(int id) {
        for (Appointment app : appointmentList) {
            if (app.getId() == id) {
                appointmentList.remove(app);
                return;
            }
        }
    }

    public static void main(String[] args) {
        HairSalonServer hairSalonServer = new HairSalonServer();
        Endpoint.publish("http://localhost:8888/hairsalon", hairSalonServer);

//        LocalDateTime time1 = LocalDateTime.now();
//        time1 = time1.minusHours(5);
//        LocalDateTime time2 = time1.minusHours(1);
//        System.out.println(time1);
//        System.out.println(time2);
//        System.out.println(time2.toEpochSecond(OffsetDateTime.now().getOffset()) - time1.toEpochSecond(OffsetDateTime.now().getOffset()));
    }
}
