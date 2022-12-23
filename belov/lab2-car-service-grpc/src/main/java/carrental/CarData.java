package carrental;

public class CarData {
    public int id;
    public String model;
    public int km;
    public String state;
    public int cost;
    public int rentCount;

    public CarData(int id, String model, int km, String state, int cost, int rentCount) {
        this.id = id;
        this.model = model;
        this.km = km;
        this.state = state;
        this.cost = cost;
        this.rentCount = rentCount;
    }
}
