package hairsalon;

import java.time.LocalDateTime;

public class Appointment {
    private int id;
    private int service;
    private String date;
    private int hour;
    private int min;

    public Appointment(int id, int service, String date, int hour, int min) {
        this.id = id;
        this.service = service;
        this.date = date;
        this.hour = hour;
        this.min = min;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
