package techsupport;

import java.time.LocalDate;

public class Task {
    public Integer id;
    public String status;
    public String desc;
    public LocalDate date;
    public String client;
    public String email;

    public Task() {
        id = 0;
        status = "PENDING";
        desc = "";
        date = LocalDate.now();
        client = "";
        email = "";
    }

    public Task(Integer id, String status, String desc, LocalDate date, String client, String email) {
        this.id = id;
        this.status = status;
        this.desc = desc;
        this.date = date;
        this.client = client;
        this.email = email;
    }
}
