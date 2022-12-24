package wiki;

import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WikiClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
        WikiServiceGrpc.WikiServiceBlockingStub stub = WikiServiceGrpc.newBlockingStub(channel);
        WikiServiceGrpc.WikiServiceStub asyncStub = WikiServiceGrpc.newStub(channel);

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        while (true) {
            System.out.print("Команды: \n");
            System.out.print("add-record ");
            System.out.print("attach-tags ");
            System.out.print("attach-file ");
            System.out.print("get-file ");
            System.out.print("add-tag ");
            System.out.print("get-file ");
            System.out.print("remove-tag ");
            System.out.print("get-records ");
            System.out.print("get-tags ");
            System.out.print("get-by-tag\n");
            String input = scanner.nextLine();

            switch (input) {
                case "add-record" -> {
                    System.out.println("Введите name");
                    String name = scanner.nextLine();
                    System.out.println("Введите text");
                    String text = scanner.nextLine();

                    AddRecordRequest.Builder addRecordRequest = AddRecordRequest.newBuilder();
                    addRecordRequest.setName(name);
                    addRecordRequest.setText(text);

                    RecordId recordId = stub.addRecord(addRecordRequest.build());
                    System.out.println("ID: " + recordId);
                }
                case "attach-tags" -> {
                    System.out.println("Введите id");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Введите tags через пробел");
                    List<String> tags = List.of(scanner.nextLine().split(" "));

                    AttachTagRequest.Builder attachTagRequest = AttachTagRequest.newBuilder();
                    attachTagRequest.setId(id);

                    List<TagId> tagIds = new ArrayList<>();
                    for (String tag : tags) {
                        tagIds.add(TagId.newBuilder().setTag(tag).build());
                    }

                    attachTagRequest.addAllTags(tagIds);
                    Empty empty = stub.attachTag(attachTagRequest.build());
                }
                case "attach-file" -> {
                    System.out.println("Введите id");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Введите filename");
                    String filename = scanner.nextLine();
                    System.out.println("Введите file");
                    ByteString file = ByteString.copyFromUtf8(scanner.nextLine());

                    AttachFileRequest.Builder attachFileRequest = AttachFileRequest.newBuilder();
                    attachFileRequest.setId(id);
                    attachFileRequest.setFileName(filename);
                    attachFileRequest.setFile(file);

                    Empty empty = stub.attachFile(attachFileRequest.build());
                }
                case "add-tag" -> {
                    System.out.println("Введите tag");
                    String tag = scanner.nextLine();

                    TagId.Builder tagId = TagId.newBuilder();
                    tagId.setTag(tag);

                    Empty empty = stub.addTag(tagId.build());
                }
                case "get-file" -> {
                    System.out.println("Введите id");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Введите filename");
                    String filename = scanner.nextLine();

                    GetFileRequest.Builder getFileRequest = GetFileRequest.newBuilder();
                    getFileRequest.setId(id);
                    getFileRequest.setFileName(filename);

                    FileData file = stub.getFile(getFileRequest.build());
                    System.out.println(file.getFile());
                }
                case "remove-tag" -> {
                    System.out.println("Введите tag");
                    String tag = scanner.nextLine();

                    Empty empty = stub.removeTag(TagId.newBuilder().setTag(tag).build());
                }
                case "get-tags" -> {
                    GetTagResponse tags = stub.getTags(Empty.newBuilder().build());
                    List<TagId> tagIds = tags.getTagsList();

                    for (TagId tagId : tagIds) {
                        System.out.print(tagId.getTag() + " ");
                    }
                    System.out.println();
                }
                case "get-records" -> {
                    GetRecordResponse recs = stub.getRecords(Empty.newBuilder().build());
                    List<RecordSimpleStruct> recordSimpleStructs = recs.getRecordsList();

                    for (RecordSimpleStruct recordSimpleStruct : recordSimpleStructs) {
                        System.out.print(recordSimpleStruct.getId() + " ");
                        System.out.print(recordSimpleStruct.getName() + "\n");
                    }
                }
                case "get-by-tag" -> {
                    System.out.println("Введите tag");
                    String tag = scanner.nextLine();

                    GetRecordResponse recs = stub.getRecordsByTag(TagId.newBuilder().setTag(tag).build());
                    List<RecordSimpleStruct> recordSimpleStructs = recs.getRecordsList();

                    for (RecordSimpleStruct recordSimpleStruct : recordSimpleStructs) {
                        System.out.print(recordSimpleStruct.getId() + " ");
                        System.out.print(recordSimpleStruct.getName() + "\n");
                    }
                }
                default -> System.out.println("wrong request");
            }
        }
    }
}
