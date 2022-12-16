package pizza;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;
import java.util.Scanner;

public class PizzaEmployee {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
        PizzaServiceGrpc.PizzaServiceBlockingStub stub = PizzaServiceGrpc.newBlockingStub(channel);

        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Все команды:");
            System.out.println("1. get");
            System.out.println("2. add");
            System.out.println("3. delete");
            System.out.println("4. update");
            System.out.println("5. quit");
            String input = scanner.nextLine();

            switch (input) {
                case "get", "1" -> {
                    Empty empty = Empty.newBuilder().build();
                    AllPizzasResponse allPizzas = stub.getAllPizzas(empty);
                    List<Pizza> pizzaList = allPizzas.getPizzasList();

                    for (Pizza pizza : pizzaList) {
                        System.out.println(pizza.getId());
                    }
                }
                case "add", "2" -> {
                    AddPizzaRequest.Builder addPizzaRequestBuilder = AddPizzaRequest.newBuilder();

                    System.out.println("Введите name");
                    String name = scanner.nextLine().trim();
                    addPizzaRequestBuilder.setName(name);
                    System.out.println("Введите description");
                    String description = scanner.nextLine().trim();
                    addPizzaRequestBuilder.setDescription(description);
                    System.out.println("Введите cost");
                    Integer cost = Integer.valueOf(scanner.nextLine().trim());
                    addPizzaRequestBuilder.setCost(cost);

                    AddPizzaRequest addPizzaRequest = addPizzaRequestBuilder.build();
                    PizzaId pizzaId = stub.addNewPizza(addPizzaRequest);
                    System.out.println("id:");
                    System.out.println(pizzaId.getId());
                }
                case "delete", "3" -> {
                    PizzaId.Builder pizzaIdBuilder = PizzaId.newBuilder();

                    System.out.println("Введите pizzaId");
                    String id = scanner.nextLine().trim();
                    pizzaIdBuilder.setId(id);

                    PizzaId pizzaId = pizzaIdBuilder.build();
                    Status status = stub.deletePizza(pizzaId);
                    System.out.println("status:");
                    System.out.println(status.getStatus());
                }
                case "update", "4" -> {
                    UpdateOrderRequest.Builder updateOrderRequestBuilder = UpdateOrderRequest.newBuilder();

                    System.out.println("Введите orderId");
                    Integer id = Integer.valueOf(scanner.nextLine().trim());
                    updateOrderRequestBuilder.setId(id);
                    System.out.println("Введите status");
                    String status = scanner.nextLine().trim();
                    updateOrderRequestBuilder.setStatus(status);

                    UpdateOrderRequest updateOrderRequest = updateOrderRequestBuilder.build();
                    stub.updateOrder(updateOrderRequest);
                }
                case "quit", "5" -> {
                    flag = false;
                }
            }
        }
    }
}
