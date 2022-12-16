package wiki;

import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class WikiClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
        WikiServiceGrpc.WikiServiceBlockingStub stub = WikiServiceGrpc.newBlockingStub(channel);
        WikiServiceGrpc.WikiServiceStub asyncStub = WikiServiceGrpc.newStub(channel);

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        while (true) {
            System.out.println("Команды: ");
            System.out.println("add-record");
            System.out.println("attach-tags");
            System.out.println("attach-file");
            System.out.println("get-file");
            System.out.println("add-tag");
            System.out.println("get-file");
            System.out.println("remove-tag");
            System.out.println("get-records");
            System.out.println("get-tags");
            System.out.println("get-by-tag");
            System.out.println();
            String input = scanner.nextLine();

            if ("add-record".equals(input)) {
                System.out.println("Введите recordTitle");
                String recordTitle = scanner.nextLine();
                System.out.println("Введите recordText");
                String recordText = scanner.nextLine();
                AddRecordRequest addRecordRequest = AddRecordRequest.newBuilder()
                        .setRecordTitle(recordTitle)
                        .setRecordText(recordText)
                        .build();
                AddRecordResponse addRecordResponse = stub.addRecord(addRecordRequest);
                System.out.println("addRecordResponse.getRecordId() = " + addRecordResponse.getRecordId());
            } else if ("attach-tags".equals(input)) {
                StreamObserver<Empty> responseObserver = new StreamObserver<>() {
                    @Override
                    public void onNext(Empty e) {
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable t) {
                    }
                };

                StreamObserver<AttachTagRequest> requestObserver = asyncStub.attachTag(responseObserver);
                try {
                    System.out.println("Введите recordId");
                    int recordId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Введите tags через пробел");
                    List<Integer> tags = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).toList();
                    for (int tag : tags) {
                        AttachTagRequest attachTagRequest = AttachTagRequest.newBuilder().setTagId(tag).setRecordId(recordId).build();
                        requestObserver.onNext(attachTagRequest);
                    }
                } catch (RuntimeException e) {
                    requestObserver.onError(e);
                    throw e;
                }
                requestObserver.onCompleted();
            } else if ("attach-file".equals(input)) {
                System.out.println("Введите recordId");
                int recordId = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите fileName");
                String fileName = scanner.nextLine();

                String filePath = "src/main/resources/" + fileName;
                ByteString fileData = null;

                try {
                    // file to bytes[]
                    byte[] bytes = Files.readAllBytes(Paths.get(filePath));
                    fileData = ByteString.copyFrom(bytes);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (fileData == null) continue;

                AttachFileRequest attachFileRequest = AttachFileRequest.newBuilder()
                        .setRecordId(recordId)
                        .setFileName(fileName)
                        .setFileData(fileData)
                        .build();

                stub.attachFile(attachFileRequest);
            } else if ("add-tag".equals(input)) {
                System.out.println("Введите tagName");
                String tagName = scanner.nextLine();

                AddTagRequest addTagRequest = AddTagRequest.newBuilder()
                        .setTagName(tagName)
                        .build();

                AddTagResponse addTagResponse = stub.addTag(addTagRequest);
                System.out.println("addTagResponse.getTagId() = " + addTagResponse.getTagId());
            } else if ("get-file".equals(input)) {
                System.out.println("Введите recordId");
                int recordId = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите fileName как путь");
                String fileName = scanner.nextLine();

                String filePath = "src/main/resources/" + fileName + "_downloaded";

                GetFileRequest getFileRequest = GetFileRequest.newBuilder()
                        .setRecordId(recordId)
                        .setFileName(fileName)
                        .build();

                GetFileResponse getFileResponse = stub.getFile(getFileRequest);

                try {

                    byte[] bytes = getFileResponse.toByteArray();

                    // bytes[] to file
                    File myObj = new File(filePath);
                    myObj.createNewFile();
                    Path file = Paths.get(filePath);
                    Files.write(file, bytes);

                    System.out.println("Done");

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if ("remove-tag".equals(input)) {
                System.out.println("Введите tagId");
                int tagId = Integer.parseInt(scanner.nextLine());

                RemoveTagRequest removeTagRequest = RemoveTagRequest.newBuilder()
                        .setTagId(tagId)
                        .build();
                stub.removeTag(removeTagRequest);
            } else if ("get-tags".equals(input)) {
                Iterator<GetTagResponse> getTagResponseIterator;
                try {
                    getTagResponseIterator = stub.getTag(Empty.newBuilder().build());
                    while (getTagResponseIterator.hasNext()) {
                        GetTagResponse getTagResponse = getTagResponseIterator.next();
                        System.out.println("tagId: " + getTagResponse.getTagId() + ", tagName: " + getTagResponse.getTagName());
                    }
                    System.out.println();
                } catch (StatusRuntimeException ignored) {
                }
            } else if ("get-records".equals(input)) {
                Iterator<GetRecordResponse> getRecordResponseIterator;
                try {
                    getRecordResponseIterator = stub.getRecord(Empty.newBuilder().build());
                    while (getRecordResponseIterator.hasNext()) {
                        GetRecordResponse getRecordResponse = getRecordResponseIterator.next();
                        System.out.println("recordId: " + getRecordResponse.getRecordId() + ", recordTitle: " + getRecordResponse.getRecordTitle());
                    }
                    System.out.println();
                } catch (StatusRuntimeException ignored) {
                }
            } else if ("get-by-tag".equals(input)) {
                System.out.println("Введите tagId");
                int tagId = Integer.parseInt(scanner.nextLine());
                GetRecordByTagRequest getRecordByTagRequest = GetRecordByTagRequest.newBuilder()
                        .setTagId(tagId)
                        .build();

                Iterator<GetRecordByTagResponse> getRecordByTagResponseIterator;
                try {
                    getRecordByTagResponseIterator = stub.getRecordByTag(getRecordByTagRequest);
                    while (getRecordByTagResponseIterator.hasNext()) {
                        GetRecordByTagResponse getRecordByTagResponse = getRecordByTagResponseIterator.next();
                        System.out.println("recordId: " + getRecordByTagResponse.getRecordId() + ", recordTitle: " + getRecordByTagResponse.getRecordTitle());
                    }
                    System.out.println();
                } catch (StatusRuntimeException ignored) {
                }
            } else {
                System.out.println("wrong request");
            }
        }
    }
}
