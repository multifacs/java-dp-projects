package techservice;

import java.time.LocalDate;

public class Request {
    public int id;
    public String status;
    public String description;
    public LocalDate date;
    public String name;
    public String email;

    public Request(int id, String status, String description, LocalDate date, String name, String email) {
        this.id = id;
        this.status = status;
        this.description = description;
        this.date = date;
        this.name = name;
        this.email = email;
    }
}
