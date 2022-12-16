package hairsalon;

public class Check {
    private String status;
    private int cost;
    private int id;

    public Check(String status, int cost, int id) {
        this.status = status;
        this.cost = cost;
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
