package carrental;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ServerData {
    private List<CarData> carDataList;

    public ServerData() {
        carDataList = new ArrayList<>();
    }

    private int createID() {
        int id = carDataList.size();

        for (CarData carData : carDataList) {
            if (carData.getId() == id) id++;
        }
        return id;
    }

    public Short addCar(String model, Short km, String state, Short cost) {
        Short id = (short) createID();
        CarData carData = new CarData(id, model, km, state, cost, (short) 0);
        carDataList.add(carData);
        return id;
    }

    public boolean changeCar(Short id, Short km, String state) {
        for (CarData carData : carDataList) {
            if (carData.getId() == id) {
                carData.setKm(km);
                carData.setState(state);
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
            if (Objects.equals(carData.getModel(), model)) {
                newList.add(carData);
            }
        }
        return newList;
    }

    public Short rentCar(Short id, Short time) {
        for (CarData carData : carDataList) {
            if (Objects.equals(carData.getId(), id)) {
                carData.setIsRented((short) (carData.getIsRented() + 1));
                return (short) (carData.getCost() * time);
            }
        }
        return (short) 0;
    }

    public boolean returnCar(Short id) {
        for (CarData carData : carDataList) {
            if (Objects.equals(carData.getId(), id)) {
                carData.setIsRented((short) (carData.getIsRented() - 1));
                return true;
            }
        }
        return false;
    }
}
