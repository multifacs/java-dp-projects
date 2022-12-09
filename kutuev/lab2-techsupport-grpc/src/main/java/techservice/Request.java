package techservice;

import java.time.LocalDate;

public class Request {
    private int id;
    private String status;
    private final String description;
    private final String date;
    private final String name;
    private final String email;

    public Request(int id, String status, String description, String date, String name, String email) {
        this.id = id;
        this.status = status;
        this.description = description;
        this.date = date;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }
    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
