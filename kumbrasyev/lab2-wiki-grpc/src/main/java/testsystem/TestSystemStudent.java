package testsystem;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;
import java.util.Scanner;

public class TestSystemStudent {
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
                case "get-tests" -> {
                    GetTestsResponse tests = stub.getTests(Empty.newBuilder().build());
                    List<TestStruct> testStructs = tests.getTestsList();
                    for (int i = 0; i < testStructs.size(); i++) {
                        TestStruct t = testStructs.get(i);
                        System.out.println(t.getId());
                    }
                }
                case "start-test" -> {
                    System.out.println("Введите id");
                    int id = Integer.parseInt(scanner.nextLine());
                    lastTest = id;

                    Empty empty = stub.startTest(IdStruct.newBuilder().setId(id).build());
                }
                case "get-next" -> {
                    GetNext.Builder getNext = GetNext.newBuilder();
                    getNext.setTestId(lastTest);
                    getNext.setQuestionId(lastQuestion);
                    GetNextQuestionResponse nextQuestion = stub.getNextQuestion(getNext.build());

                    if (!nextQuestion.getText().equals("the end of the test")) {
                        System.out.println(nextQuestion.getText());
                        for (String s : nextQuestion.getAnswers().getAnswersList()) {
                            System.out.println(s);
                        }
                    } else {
                        System.out.println("end");
                    }
                }
                case "answer" -> {
                    AnswerQuestionRequest.Builder ans = AnswerQuestionRequest.newBuilder();
                    ans.setTestId(lastTest);
                    ans.setQuestionId(lastQuestion);

                    System.out.println("Enter answer id");
                    int answer = Integer.parseInt(scanner.nextLine());
                    ans.setAnswer(answer);

                    IdStruct idStruct = stub.answerQuestion(ans.build());
                    score += idStruct.getId();
                    lastQuestion++;
                }
                case "end" -> {
                    IdStruct idStruct = stub.endTest(IdStruct.newBuilder().build());
                    System.out.println(score);
                    score = 0;
                    lastTest = 0;
                    lastQuestion = 0;
                }
                default -> System.out.println("wrong request");
            }
        }
    }

    private static void printCommands() {
        System.out.print("Команды: \n");
        System.out.print("get-tests ");
        System.out.print("start-test ");
        System.out.print("get-next ");
        System.out.print("answer ");
        System.out.print("end\n");
    }
}
