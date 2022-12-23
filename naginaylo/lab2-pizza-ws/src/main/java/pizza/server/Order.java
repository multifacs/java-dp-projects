package pizza.server;

import java.util.HashMap;

public class Order {
    private final int id;
    private HashMap<Integer, Integer> pizzaHashMap = new HashMap<>();
    private String status = "cooking";

    public Order(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Order(int id, HashMap<Integer, Integer> pizzaHashMap) {
        this.id = id;
        this.pizzaHashMap = pizzaHashMap;
    }

    public HashMap<Integer, Integer> getPizzaHashMap() {
        return pizzaHashMap;
    }

    public void setPizzaHashMap(HashMap<Integer, Integer> pizzaHashMap) {
        this.pizzaHashMap = pizzaHashMap;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
};
