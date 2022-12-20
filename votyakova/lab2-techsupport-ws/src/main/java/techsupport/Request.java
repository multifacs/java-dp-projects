package techsupport;

import java.time.LocalDate;

public class Request {
    public int id;
    public int status;
    public String desc;
    public LocalDate date;
    public String client;
    public String email;

    public Request(int id, int status, String desc, LocalDate date, String client, String email) {
        this.id = id;
        this.status = status;
        this.desc = desc;
        this.date = date;
        this.client = client;
        this.email = email;
    }
}
