package testsystem;

import com.google.protobuf.Empty;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestSystemServer extends TestSystemGrpc.TestSystemImplBase {

    private static final List<Test> tests = new ArrayList<>();
    private static int someconst = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080).addService(new TestSystemServer()).build();
        server.start();
        server.awaitTermination();
    }

    @Override
    public void addTest(NameStruct request,
                        StreamObserver<IdStruct> responseObserver) {
        Test test = new Test();
        test.name = request.getName();



        test.id = tests.size() + someconst;
        tests.add(test);

        IdStruct i = IdStruct.newBuilder().setId(test.id).build();
        responseObserver.onNext(i);
        responseObserver.onCompleted();
    }
    @Override
    public void addQuestion(testsystem.QuestionStruct request,
                            StreamObserver<IdStruct> responseObserver) {
        int id = -1;
        for (int i = 0; i < tests.size(); i++) {
            Test test = tests.get(i);
            if (test.id == request.getTestId()) {
                test.questions.add(request.getText());
                id = test.questions.size() - 1;
                test.answers.add(new ArrayList<>());
                test.correct.add(0);
            }
        }
        IdStruct i = IdStruct.newBuilder().setId(id).build();
        responseObserver.onNext(i);
        responseObserver.onCompleted();
    }
    @Override
    public void addAnswers(AnswerStruct request,
                           StreamObserver<Empty> responseObserver) {
        for (int i = 0; i < tests.size(); i++) {
            Test test = tests.get(i);
            if (test.id == request.getTestId()) {
                List<String> answers = new ArrayList<>();
                answers = request.getAnswersList();
                test.correct.set(request.getQuestionId(), request.getCorrect());
            }
        }

        Empty e = Empty.newBuilder().build();
        responseObserver.onNext(e);
        responseObserver.onCompleted();
    }
    @Override
    public void getTests(Empty request,
                         StreamObserver<GetTestsResponse> responseObserver) {
        GetTestsResponse.Builder getTestsResponse = GetTestsResponse.newBuilder();
        List<TestStruct> testStructs = new ArrayList<>();
        for (int i = 0; i < tests.size(); i++) {
            Test test = tests.get(i);
            TestStruct testStruct = TestStruct.newBuilder().setId(test.id).setName(test.name).build();
            testStructs.add(testStruct);
        }
        getTestsResponse.addAllTests(testStructs);

        GetTestsResponse g = getTestsResponse.build();
        responseObserver.onNext(g);
        responseObserver.onCompleted();
    }
    @Override
    public void startTest(IdStruct request,
                          StreamObserver<Empty> responseObserver) {
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
    @Override
    public void getNextQuestion(GetNext request,
                                StreamObserver<GetNextQuestionResponse> responseObserver) {
        GetNextQuestionResponse.Builder getNextQuestionResponse = GetNextQuestionResponse.newBuilder();
        for (Iterator<Test> iterator = tests.iterator(); iterator.hasNext(); ) {
            Test test = iterator.next();
            if (test.id == request.getTestId()) {
                if (request.getQuestionId() != test.questions.size()) {
                    AnswerStruct.Builder answerStruct = AnswerStruct.newBuilder();
                    answerStruct.setQuestionId(request.getQuestionId());
                    answerStruct.setCorrect(test.correct.get(request.getQuestionId()));
                    answerStruct.addAllAnswers(test.answers.get(request.getQuestionId()));
                    getNextQuestionResponse.setText(test.questions.get(request.getQuestionId()));
                    getNextQuestionResponse.setAnswers(answerStruct.build());
                } else {
                    getNextQuestionResponse.setText("the end of the test");
                }
            }
        }
        responseObserver.onNext(getNextQuestionResponse.build());
        responseObserver.onCompleted();
    }
    @Override
    public void answerQuestion(AnswerQuestionRequest request,
                               StreamObserver<IdStruct> responseObserver) {
        IdStruct.Builder idStruct = IdStruct.newBuilder();
        for (Iterator<Test> iterator = tests.iterator(); iterator.hasNext(); ) {
            Test test = iterator.next();
            if (test.correct.get(request.getQuestionId()) == request.getAnswer()) {
                idStruct.setId(10);
            } else {
                idStruct.setId(0);
            }
        }
        responseObserver.onNext(idStruct.build());
        responseObserver.onCompleted();
    }
    @Override
    public void endTest(IdStruct request,
                        StreamObserver<IdStruct> responseObserver) {
        responseObserver.onNext(IdStruct.newBuilder().setId(1000 + someconst).build());
        responseObserver.onCompleted();
    }
}
