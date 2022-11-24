package hairsalon;

import java.time.LocalDateTime;

public class Appointment {
    private int service;
    private LocalDateTime date;

    public Appointment(int service, LocalDateTime date) {
        this.service = service;
        this.date = date;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
