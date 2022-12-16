package pizza;

import com.google.protobuf.Empty;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PizzaServer extends PizzaServiceGrpc.PizzaServiceImplBase {

    private final ServerController serverController = new ServerController();

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080).addService(new PizzaServer()).build();
        server.start();
        server.awaitTermination();
    }
    @Override
    public void getAllPizzas(Empty request,
                             StreamObserver<AllPizzasResponse> responseObserver) {
        AllPizzasResponse.Builder allPizzasResponseBuilder = AllPizzasResponse.newBuilder();

        for (PizzaType pizzaType : serverController.getPizzas()) {
            Pizza.Builder pizzaBuilder = Pizza.newBuilder();
            pizzaBuilder.setId(pizzaType.id());
            pizzaBuilder.setName(pizzaType.name());
            pizzaBuilder.setDescription(pizzaType.description());
            pizzaBuilder.setCost(pizzaType.cost());
            Pizza pizza = pizzaBuilder.build();
            allPizzasResponseBuilder.addPizzas(pizza);
        }
        AllPizzasResponse allPizzasResponse = allPizzasResponseBuilder.build();

        responseObserver.onNext(allPizzasResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void addNewPizza(AddPizzaRequest request,
                            StreamObserver<PizzaId> responseObserver) {
        String id = serverController.addNewPizza(request.getName(), request.getDescription(), request.getCost());
        PizzaId pizzaId = PizzaId.newBuilder().setId(id).build();

        responseObserver.onNext(pizzaId);
        responseObserver.onCompleted();
    }

    @Override
    public void deletePizza(PizzaId request,
                            StreamObserver<Status> responseObserver) {
        String status = serverController.deletePizza(request.getId());
        Status status1 = Status.newBuilder().setStatus(status).build();

        responseObserver.onNext(status1);
        responseObserver.onCompleted();
    }

    @Override
    public void updateOrder(UpdateOrderRequest request,
                            StreamObserver<Empty> responseObserver) {
        serverController.changeOrder(request.getId(), request.getStatus());

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void orderPizza(MakeOrderRequest request,
                           StreamObserver<MakeOrderResponse> responseObserver) {
        OrderType orderType = serverController.makeOrder(request.getId(), request.getCount());
        MakeOrderResponse.Builder makeOrderResponseBuilder = MakeOrderResponse.newBuilder();

        makeOrderResponseBuilder.setId(orderType.orderId);
        makeOrderResponseBuilder.setCost(orderType.cost);
        MakeOrderResponse makeOrderResponse = makeOrderResponseBuilder.build();

        responseObserver.onNext(makeOrderResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void checkOrder(CheckOrderRequest request,
                           StreamObserver<Status> responseObserver) {
        String status = serverController.checkOrder(request.getId());

        responseObserver.onNext(Status.newBuilder().setStatus(status).build());
        responseObserver.onCompleted();
    }
}

class ServerController {
    private final List<PizzaType> pizzas = new ArrayList<>();
    private final List<OrderType> orders = new ArrayList<>();

    public List<PizzaType> getPizzas() {
        return pizzas;
    }

    public String addNewPizza(String name, String description, Integer cost) {
        String id = name + "_" + pizzas.size();
        PizzaType pizza = new PizzaType(id, name, description, cost);
        pizzas.add(pizza);
        return id;
    }

    public String deletePizza(String id) {
        for (PizzaType pizza : pizzas) {
            if (Objects.equals(pizza.id(), id)) {
                pizzas.remove(pizza);
                return "Deleted";
            }
        }
        return "Error";
    }

    public void changeOrder(Integer id, String status) {
        for (OrderType order : orders) {
            if (Objects.equals(order.orderId, id)) {
                order.status = status;
            }
        }
    }

    public OrderType makeOrder(String pizzaId, Integer count) {
        Integer id = orders.size();
        PizzaType pizza = null;
        for (PizzaType pizza1 : pizzas) {
            if (pizza1.id() == pizzaId) {
                pizza = pizza1;
            }
        }
        if (pizza == null) return null;
        OrderType order = new OrderType(id, pizza.cost() * count, pizzaId, count);
        orders.add(order);
        return order;
    }

    public String checkOrder(Integer orderId) {
        for (OrderType order : orders) {
            if (Objects.equals(order.orderId, orderId)) {
                return order.status;
            }
        }
        return "Not found";
    }
}

record PizzaType (String id, String name, String description, Integer cost) {}
class OrderType {
    public Integer orderId;
    public Integer cost;
    public String status;
    public String pizzaId;
    public Integer count;

    public OrderType(Integer orderId, Integer cost, String pizzaId, Integer count) {
        this.orderId = orderId;
        this.cost = cost;
        this.status = "cooking";
        this.pizzaId = pizzaId;
        this.count = count;
    }
}
