package wiki;

import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

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
            String input = scanner.nextLine();

            if ("add-record".equals(input)) {
                String recordTitle = scanner.nextLine();
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
                    List<Integer> tags = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).toList();
                    for (int tag : tags) {
                        AttachTagRequest attachTagRequest = AttachTagRequest.newBuilder().setTagId(tag).build();
                        requestObserver.onNext(attachTagRequest);
                    }
                } catch (RuntimeException e) {
                    requestObserver.onError(e);
                    throw e;
                }
                requestObserver.onCompleted();
            } else if ("attach-file".equals(input)) {
                int recordId = scanner.nextInt();
                String fileName = scanner.nextLine();
                ByteString fileData = null;

                try {
                    // file to bytes[]
                    byte[] bytes = Files.readAllBytes(Paths.get(fileName));
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
                String tagName = scanner.nextLine();

                AddTagRequest addTagRequest = AddTagRequest.newBuilder()
                        .setTagName(tagName)
                        .build();

                AddTagResponse addTagResponse = stub.addTag(addTagRequest);
                System.out.println("addTagResponse.getTagId() = " + addTagResponse.getTagId());
            } else if ("get-file".equals(input)) {
                int recordId = scanner.nextInt();
                String fileName = scanner.nextLine();

                GetFileRequest getFileRequest = GetFileRequest.newBuilder()
                        .setRecordId(recordId)
                        .setFileName(fileName)
                        .build();

                GetFileResponse getFileResponse = stub.getFile(getFileRequest);

                try {

                    byte[] bytes = getFileResponse.toByteArray();

                    // bytes[] to file
                    Path path = Paths.get("/home/mkyong/test/phone2.png");
                    Files.write(path, bytes);

                    System.out.println("Done");

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if ("remove-tag".equals(input)) {
                int tagId = scanner.nextInt();

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
                        System.out.println(getTagResponse.getTagId());
                        System.out.println(getTagResponse.getTagName());
                        System.out.println();
                    }
                } catch (StatusRuntimeException ignored) {
                }
            } else if ("get-records".equals(input)) {
                Iterator<GetRecordResponse> getRecordResponseIterator;
                try {
                    getRecordResponseIterator = stub.getRecord(Empty.newBuilder().build());
                    while (getRecordResponseIterator.hasNext()) {
                        GetRecordResponse getRecordResponse = getRecordResponseIterator.next();
                        System.out.println(getRecordResponse.getRecordId());
                        System.out.println(getRecordResponse.getRecordTitle());
                        System.out.println();
                    }
                } catch (StatusRuntimeException ignored) {
                }
            } else if ("get-by-tag".equals(input)) {
                int tagId = Integer.parseInt(scanner.nextLine());
                GetRecordByTagRequest getRecordByTagRequest = GetRecordByTagRequest.newBuilder()
                        .setTagId(tagId)
                        .build();

                Iterator<GetRecordByTagResponse> getRecordByTagResponseIterator;
                try {
                    getRecordByTagResponseIterator = stub.getRecordByTag(getRecordByTagRequest);
                    while (getRecordByTagResponseIterator.hasNext()) {
                        GetRecordByTagResponse getRecordByTagResponse = getRecordByTagResponseIterator.next();
                        System.out.println(getRecordByTagResponse.getRecordId());
                        System.out.println(getRecordByTagResponse.getRecordTitle());
                        System.out.println();
                    }
                } catch (StatusRuntimeException ignored) {
                }
            } else {
                System.out.println("wrong request");
            }
        }
    }
}
