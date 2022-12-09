package car;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebService
public class CarServer {

    private List<Car> carList = new ArrayList<>();
    private List<Car> orders = new ArrayList<>();

    @WebMethod
    public String getAllCars() {
        String s = "";
        for (int i = 0; i < carList.size(); i++) {
            Car car = carList.get(i);
            s = s + (car.id + ", ");
            s = s + (car.model + ", ");
            s = s + (car.mileage + ", ");
            s = s + (car.condition + ", ");
            s = s + (car.cost + "\n");
        }
        return s.toString();
    }

    @WebMethod
    public String getByModel(String model) {
        StringBuilder s = new StringBuilder();
        s.append("List of cars:\n");
        for (Car car : carList) {
            if (!Objects.equals(car.model, model)) continue;
            s.append("id: ").append(car.id).append(", ");
            s.append("model: ").append(car.model).append(", ");
            s.append("mileage: ").append(car.mileage).append(", ");
            s.append("condition: ").append(car.condition).append(", ");
            s.append("cost: ").append(car.cost).append("\n");
        }
        return s.toString();
    }

    @WebMethod
    public int addCar(String model, int mileage, String condition, int cost) {
        int id = carList.size();
        Car car = new Car(id, model, mileage, condition, cost);
        carList.add(car);
        return id;
    }

    @WebMethod
    public void changeCar(int id, int mileage, String condition) {
        for (Car car : carList) {
            if (car.id == id) {
                car.mileage = mileage;
                car.condition = condition;
            }
        }
    }

    @WebMethod
    public int orderCar(int id, int days) {
        Car order = null;
        for (Car car : carList) {
            if (car.id == id) {
                order = car;
                break;
            }
        }
        if (order != null) {
            carList.remove(order);
            orders.add(order);
            return order.cost * days;
        }
        return 0;
    }

    @WebMethod
    public void returnCar(int id) {
        Car order = null;
        for (Car car : orders) {
            if (car.id == id) {
                order = car;
                break;
            }
        }
        if (order != null) {
            orders.remove(order);
            carList.add(order);
        }
    }

    public static void main(String[] args) {
        CarServer carServer = new CarServer();
        Endpoint.publish("http://localhost:8080/car", carServer);
    }
}
