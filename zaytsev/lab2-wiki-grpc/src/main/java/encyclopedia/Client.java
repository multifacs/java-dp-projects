package encyclopedia;

import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;
import encyclopedia.EncyclopediaServiceGrpc.EncyclopediaServiceBlockingStub;
import encyclopedia.EncyclopediaServiceGrpc.EncyclopediaServiceStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Client {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(getLocalhost(), getPort()).usePlaintext().build();
        EncyclopediaServiceBlockingStub stub = EncyclopediaServiceGrpc.newBlockingStub(channel);
        EncyclopediaServiceStub asyncStub = EncyclopediaServiceGrpc.newStub(channel);

        StreamObserver<Empty> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(Empty e) {
                System.out.println("on Next");
            }
            @Override
            public void onCompleted() {
                System.out.println("on Completed");
            }
            @Override
            public void onError(Throwable t) {
                System.out.println("on Error");
            }
        };

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("New article, Add tags, Add file, New tag, Get file, Remove tag, Get tags, Get articles, Get by tag");
            String input = scanner.nextLine();

            switch (input) {
                case "New article" -> {
                    addNewArticle(stub, scanner);
                }
                case "Add tags" -> {
                    addTags(asyncStub, responseObserver, scanner);
                }
                case "Add file" -> {
                    addFile(stub, scanner);
                }
                case "New tag" -> {
                    addNewTag(stub, scanner);
                }
                case "Get file" -> {
                    getFile(stub, scanner);
                }
                case "Remove tag" -> {
                    removeTag(stub, scanner);
                }
                case "Get tags" -> {
                    getTags(stub);
                }
                case "Get articles" -> {
                    getArticles(stub);
                }
                case "Get by tag" -> {
                    getByTag(stub, scanner);
                }
                default -> System.out.println("wrong request");
            }
        }
    }

    private static String getLocalhost() {
        return "localhost";
    }

    private static int getPort() {
        return 8080;
    }

    private static void getByTag(EncyclopediaServiceBlockingStub stub, Scanner scanner) {
        int tagId = Integer.parseInt(scanner.nextLine());
        GetArticleByTagRequest getArticleByTagRequest;
        getArticleByTagRequest = GetArticleByTagRequest.newBuilder()
                .setTagId(tagId)
                .build();

        Iterator<GetArticleByTagResponse> getArticleByTagResponseIterator;
        try {
            getArticleByTagResponseIterator = stub.getArticleByTag(getArticleByTagRequest);
            while (true) {
                if (!getArticleByTagResponseIterator.hasNext()) break;
                GetArticleByTagResponse getArticleByTagResponse = getArticleByTagResponseIterator.next();
                printArticle(getArticleByTagResponse.getArticleId(), getArticleByTagResponse.getArticleTitle());
            }
        } catch (StatusRuntimeException ignored) {
        }
    }

    private static void getArticles(EncyclopediaServiceBlockingStub stub) {
        Iterator<GetArticleResponse> getArticleResponseIterator;
        try {
            getArticleResponseIterator = stub.getArticle(Empty.newBuilder().build());
            while (true) {
                if (!getArticleResponseIterator.hasNext()) break;
                GetArticleResponse getArticleResponse = getArticleResponseIterator.next();
                printArticle(getArticleResponse.getArticleId(),
                        getArticleResponse.getArticleTitle());
            }
        } catch (StatusRuntimeException ignored) {
        }
    }

    private static void getTags(EncyclopediaServiceBlockingStub stub) {
        Iterator<GetTagResponse> getTagResponseIterator;
        try {
            getTagResponseIterator = stub.getTag(Empty.newBuilder().build());
            while (true) {
                if (!getTagResponseIterator.hasNext()) break;
                GetTagResponse getTagResponse = getTagResponseIterator.next();
                printArticle(getTagResponse.getTagId(), getTagResponse.getTagName());
            }
        } catch (StatusRuntimeException ignored) {
        }
    }

    private static void removeTag(EncyclopediaServiceBlockingStub stub, Scanner scanner) {
        int tagId = scanner.nextInt();

        RemoveTagRequest removeTagRequest;
        removeTagRequest = RemoveTagRequest.newBuilder()
                .setTagId(tagId)
                .build();
        stub.removeTag(removeTagRequest);
    }

    private static void getFile(EncyclopediaServiceBlockingStub stub, Scanner scanner) {
        int articleId;
        articleId = Integer.parseInt(scanner.nextLine());
        String fileName;
        fileName = scanner.nextLine();

        GetFileRequest getFileRequest;
        getFileRequest = GetFileRequest.newBuilder()
                .setArticleId(articleId)
                .setFileName(fileName)
                .build();

        GetFileResponse getFileResponse = stub.getFile(getFileRequest);

        try {
            var bytes = getFileResponse.toByteArray();
            var path = Paths.get("src/main/resources/1.txt");
            Path write = Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addNewTag(EncyclopediaServiceBlockingStub stub, Scanner scanner) {
        String tagName;
        tagName = scanner.nextLine();
        NewTagRequest newTagRequest;
        newTagRequest = NewTagRequest.newBuilder()
                .setTagName(tagName)
                .build();
        NewTagResponse newTagResponse;
        newTagResponse = stub.newTag(newTagRequest);
        System.out.printf("newTagResponse.getTagId() = %d%n", newTagResponse.getTagId());
    }

    private static void addFile(EncyclopediaServiceBlockingStub stub, Scanner scanner) {
        int articleId;
        System.out.println("Article ID:");
        articleId = Integer.parseInt(scanner.nextLine());
        String fileName;
        System.out.println("File name:");
        fileName = scanner.nextLine();

        System.out.println("fileName = " + fileName);

        ByteString fileData = null;

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current absolute path is: " + s);

        try {
            var bytes = Files.readAllBytes(Paths.get(fileName));
            fileData = ByteString.copyFrom(bytes);
            System.out.println("file read");

        } catch (IOException ignored) {
        }

        if (null != fileData) {
            AddFileRequest addFileRequest;
            addFileRequest = AddFileRequest.newBuilder()
                    .setArticleId(articleId)
                    .setFileName(fileName)
                    .setFileData(fileData)
                    .build();

            Empty empty = stub.addFile(addFileRequest);
        }

    }

    private static void addTags(EncyclopediaServiceStub asyncStub, StreamObserver<Empty> responseObserver, Scanner scanner) {
        StreamObserver<AddTagRequest> requestObserver;
        requestObserver = asyncStub.addTag(responseObserver);
        try {
            List<Integer> tags;
            List<Integer> list = new ArrayList<>();
            for (String s : scanner.nextLine().split(" ")) {
                Integer parseInt = Integer.parseInt(s);
                list.add(parseInt);
            }
            tags = list;
            for (int i = 0; i < tags.size(); i++) {
                int tag = tags.get(i);
                AddTagRequest addTagRequest = AddTagRequest.newBuilder().setTagId(tag).build();
                requestObserver.onNext(addTagRequest);
            }
        } catch (RuntimeException ignored) {
        }
        requestObserver.onCompleted();
    }

    private static void addNewArticle(EncyclopediaServiceBlockingStub stub, Scanner scanner) {
        String articleTitle;
        System.out.println("Article title:");
        articleTitle = scanner.nextLine();
        String articleText;
        System.out.println("Article text:");
        articleText = scanner.nextLine();
        NewArticleRequest newArticleRequest;
        newArticleRequest = NewArticleRequest.newBuilder()
                .setArticleTitle(articleTitle)
                .setArticleText(articleText)
                .build();
        NewArticleResponse newArticleResponse;
        newArticleResponse = stub.newArticle(newArticleRequest);
        System.out.printf("newArticleResponse.getArticleId() = %d%n", newArticleResponse.getArticleId());
    }

    private static void printArticle(int getArticleByTagResponse, String getArticleByTagResponse1) {
        System.out.println(getArticleByTagResponse);
        System.out.println(getArticleByTagResponse1);
        System.out.println();
    }
}
