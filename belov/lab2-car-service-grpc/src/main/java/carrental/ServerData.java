package carrental;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServerData {
    private final List<CarData> carDataList;

    public ServerData() {
        carDataList = new ArrayList<>();
    }

    private int createID() {
        int id = carDataList.size();

        for (CarData carData : carDataList) {
            if (carData.id == id) id++;
        }
        return id;
    }

    public int addCar(String model, int km, String state, int cost) {
        int id = createID();
        CarData carData = new CarData(id, model, km, state, cost, (short) 0);
        carDataList.add(carData);
        return id;
    }

    public boolean changeCar(int id, int km, String state) {
        for (CarData carData : carDataList) {
            if (carData.id == id) {
                carData.km = km;
                carData.state = state;
                return true;
            }
        }
        return false;
    }

    public List<CarData> getCars() {
        return carDataList;
    }

    public List<CarData> getByModel(String model) {
        List<CarData> newList = new ArrayList<>();
        for (CarData carData : carDataList) {
            if (Objects.equals(carData.model, model)) {
                newList.add(carData);
            }
        }
        return newList;
    }

    public int rentCar(int id, int time) {
        for (CarData carData : carDataList) {
            if (carData.id == id) {
                carData.rentCount = carData.rentCount + 1;
                return carData.cost * time;
            }
        }
        return 0;
    }

    public boolean returnCar(int id) {
        for (CarData carData : carDataList) {
            if (carData.id == id) {
                carData.rentCount = carData.rentCount - 1;
                return true;
            }
        }
        return false;
    }
}
