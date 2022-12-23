package pizza.server;

public class Pizza {
    private final int id;
    private final String name;
    private final String desc;
    private final int cost;

    public Pizza(int id, String name, String desc, int cost) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getCost() {
        return cost;
    }
};