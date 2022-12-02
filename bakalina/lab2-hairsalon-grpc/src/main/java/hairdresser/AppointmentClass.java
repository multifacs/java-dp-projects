package hairdresser;

public class AppointmentClass {
    public Integer day;
    public Integer time;
    public Integer service;
    public Integer id;

    public AppointmentClass(Integer day, Integer time, Integer service, Integer id) {
        this.day = day;
        this.time = time;
        this.service = service;
        this.id = id;
    }
}
