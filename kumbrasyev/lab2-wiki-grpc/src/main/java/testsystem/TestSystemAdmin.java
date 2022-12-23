package testsystem;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestSystemAdmin {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
        TestSystemGrpc.TestSystemBlockingStub stub = TestSystemGrpc.newBlockingStub(channel);

        int lastTest = 0;
        int lastQuestion = 0;
        int score = 0;

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        while (true) {
            printCommands();
            String input = scanner.nextLine();

            switch (input) {
                case "add-test" -> {
                    System.out.println("Enter name".trim());
                    NameStruct n = NameStruct.newBuilder().setName(scanner.nextLine()).build();
                    IdStruct idStruct = stub.addTest(n);
                    System.out.println("Test id: " + idStruct.getId());
                    lastTest = idStruct.getId();
                }
                case "add-question" -> {
                    System.out.println("Введите question".trim());
                    String q = scanner.nextLine();
                    QuestionStruct qq = QuestionStruct.newBuilder().setTestId(lastTest).setText(q).build();
                    IdStruct idStruct = stub.addQuestion(qq);
                    System.out.println("Question id: " + idStruct.getId());
                }
                case "add-answers" -> {
                    AnswerStruct.Builder a = AnswerStruct.newBuilder();
                    a.setTestId(lastTest);

                    System.out.println("Enter question id");
                    int id = Integer.parseInt(scanner.nextLine());
                    a.setQuestionId(id);

                    System.out.println("Enter num");
                    int num = Integer.parseInt(scanner.nextLine());

                    List<String> answers = new ArrayList<>();

                    for (int i = 0; i < num; i++) {
                        System.out.println("Enter question");
                        answers.add(scanner.nextLine());
                    }
                    System.out.println("Enter answer");
                    int correct = Integer.parseInt(scanner.nextLine());

                    a.setCorrect(correct);
                    a.addAllAnswers(answers);
                    Empty empty = stub.addAnswers(a.build());
                }
                default -> System.out.println("wrong request");
            }
        }
    }

    private static void printCommands() {
        System.out.print("Команды: \n");
        System.out.print("add-test ");
        System.out.print("add-question ");
        System.out.print("add-answers\n");
    }
}
