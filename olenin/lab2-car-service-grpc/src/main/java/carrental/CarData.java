package carrental;

public class CarData {
    private Short id;
    private String model;
    private Short km;
    private String state;
    private Short cost;
    private Short isRented;

    public CarData(Short id, String model, Short km, String state, Short cost, Short isRented) {
        this.id = id;
        this.model = model;
        this.km = km;
        this.state = state;
        this.cost = cost;
        this.isRented = isRented;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Short getKm() {
        return km;
    }

    public void setKm(Short km) {
        this.km = km;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Short getCost() {
        return cost;
    }

    public void setCost(Short cost) {
        this.cost = cost;
    }

    public Short getIsRented() {
        return isRented;
    }

    public void setIsRented(Short isRented) {
        this.isRented = isRented;
    }
}
