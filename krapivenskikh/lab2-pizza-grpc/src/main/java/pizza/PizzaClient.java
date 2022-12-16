package pizza;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;
import java.util.Scanner;

public class PizzaClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
        PizzaServiceGrpc.PizzaServiceBlockingStub stub = PizzaServiceGrpc.newBlockingStub(channel);

        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Все команды:");
            System.out.println("1. get");
            System.out.println("2. order");
            System.out.println("3. check");
            System.out.println("4. quit");
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
                case "order", "2" -> {
                    MakeOrderRequest.Builder makeOrderRequestBuilder = MakeOrderRequest.newBuilder();

                    System.out.println("Введите pizzaId");
                    String pizzaId = scanner.nextLine().trim();
                    makeOrderRequestBuilder.setId(pizzaId);
                    System.out.println("Введите count");
                    Integer count = Integer.valueOf(scanner.nextLine().trim());
                    makeOrderRequestBuilder.setCount(count);

                    MakeOrderRequest makeOrderRequest = makeOrderRequestBuilder.build();
                    MakeOrderResponse makeOrderResponse = stub.orderPizza(makeOrderRequest);
                    System.out.println("id:");
                    System.out.println(makeOrderResponse.getId());
                    System.out.println("Стоимость:");
                    System.out.println(makeOrderResponse.getCost());
                }
                case "check", "3" -> {
                    CheckOrderRequest.Builder checkOrderRequestBuilder = CheckOrderRequest.newBuilder();

                    System.out.println("Введите orderId");
                    Integer orderId = Integer.valueOf(scanner.nextLine().trim());
                    checkOrderRequestBuilder.setId(orderId);

                    CheckOrderRequest checkOrderRequest = checkOrderRequestBuilder.build();
                    Status status = stub.checkOrder(checkOrderRequest);
                    System.out.println("Статус:");
                    System.out.println(status.getStatus());
                }
                case "quit", "4" -> {
                    flag = false;
                }
            }
        }
    }
}
