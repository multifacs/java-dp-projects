package hairsalon;

public class Service {
    private int id;
    private String name;
    private int duration;
    private int cost;

    public Service(int id, String name, int duration, int cost) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
