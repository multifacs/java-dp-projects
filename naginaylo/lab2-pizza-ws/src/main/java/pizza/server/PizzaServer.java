package pizza.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@WebService
public class PizzaServer {

    private final List<Pizza> pizzas = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();

    @WebMethod
    public String getPizzas() {
        StringBuilder s = new StringBuilder();
        for (Iterator<Pizza> iterator = pizzas.iterator(); iterator.hasNext(); ) {
            Pizza pizza = iterator.next();
            s.append("id: ").append(pizza.getId()).append(" ");
            s.append("name: ").append(pizza.getName().toLowerCase()).append("\n");
            s.append("desc: ").append(pizza.getDesc().toLowerCase()).append("\n");
            s.append("cost: ").append(pizza.getCost()).append("\n\n");
        }
        return s.toString();
    }

    @WebMethod
    public int addPizza(String name, String desc, int cost) {
        Pizza pizza = new Pizza(pizzas.size(), name, desc, cost);
        pizzas.add(pizza);
        return pizza.getId();
    }

    @WebMethod
    public void removePizza(int id) {
        for (Pizza pizza : pizzas) {
            if (pizza.getId() == id) {
                pizzas.remove(pizza);
                break;
            }
        }
    }

    @WebMethod
    public void changeStatus(int id, String status) {
        for (Order order : orders) {
            if (order.getId() == id) {
                order.setStatus(status);
                break;
            }
        }
    }

    @WebMethod
    public String orderPizza(String hash) {
        List<String> parsed = List.of(hash.split(" "));
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < parsed.size(); i += 2) {
            hashMap.put(Integer.parseInt(parsed.get(i)), Integer.parseInt(parsed.get(i + 1)));
        }
        Order order = new Order(orders.size(), hashMap);
        orders.add(order);
        int cost = 0;
        for (Integer key : hashMap.keySet()) {
            for (Pizza pizza : pizzas) {
                if (pizza.getId() == key) {
                    cost += pizza.getCost() * hashMap.get(key);
                }
            }
        }
        return order.getId() + " " + cost;
    }

    @WebMethod
    public String getStatus(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order.getStatus();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        PizzaServer server = new PizzaServer();
        Endpoint.publish("http://localhost:8000/Pizza", server);
        System.out.println("Server started");
    }
}
