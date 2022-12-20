package encyclopedia;

import com.google.protobuf.ByteString;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.grpc.stub.StreamObserver;
import com.google.protobuf.Empty;

public class EncyclopediaServer extends EncyclopediaServiceGrpc.EncyclopediaServiceImplBase {
    private static List<Article> articles = new ArrayList<>();
    private static List<Tag> tags = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(getPort()).addService(new EncyclopediaServer()).build();

        server.start();
        server.awaitTermination();
    }

    private static int getPort() {
        return 8080;
    }

    @Override
    public void newArticle(NewArticleRequest request, StreamObserver<NewArticleResponse> responseObserver) {
        int id = articles.size();
        Article article = new Article(id, request.getArticleTitle(), request.getArticleText());
        articles.add(article);

        responseObserver.onNext(NewArticleResponse.newBuilder().setArticleId(id).build());
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<AddTagRequest> addTag(StreamObserver<Empty> responseObserver) {
        StreamObserver<AddTagRequest> streamObserver = new StreamObserver<>() {
            @Override
            public void onNext(AddTagRequest addTagRequest) {
                for (int i = 0; i < articles.size(); i++) {
                    Article article = articles.get(i);
                    if (article.articleId == addTagRequest.getArticleId()) {
                        article.tagList.add(addTagRequest.getTagId());
                    }
                }
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(Empty.newBuilder().build());
                responseObserver.onCompleted();
            }

            @Override
            public void onError(Throwable t) {
            }
        };
        return streamObserver;
    }

    @Override
    public void addFile(AddFileRequest request, StreamObserver<Empty> responseObserver) {

        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            if (article.articleId == request.getArticleId()) {
                article.addFile(request.getFileName(), request.getFileData().toByteArray());
            }
        }
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void newTag(NewTagRequest request, StreamObserver<NewTagResponse> responseObserver) {
        int id = tags.size();
        Tag tag = new Tag(id, request.getTagName());
        boolean add = tags.add(tag);

        responseObserver.onNext(NewTagResponse.newBuilder().setTagId(id).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getFile(GetFileRequest request, StreamObserver<GetFileResponse> responseObserver) {

        byte[] file = null;

        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            if (article.articleId == request.getArticleId()) {
                for (String name : article.fileNames) {
                    if (Objects.equals(name, request.getFileName())) {
                        int index = article.fileNames.indexOf(name);
                        file = article.files.get(index);
                    }
                }
            }
        }

        responseObserver.onNext(GetFileResponse.newBuilder().setFileData(ByteString.copyFrom(file)).build());
        responseObserver.onCompleted();
    }

    @Override
    public void removeTag(RemoveTagRequest request, StreamObserver<Empty> responseObserver) {
        tags.removeIf(tag -> {
            return tag.getTagId() == request.getTagId();
        });
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void getTag(Empty request, StreamObserver<GetTagResponse> responseObserver) {
        for (int i = 0; i < tags.size(); i++) {
            Tag tag = tags.get(i);
            GetTagResponse getTagResponse;
            GetTagResponse.Builder builder = GetTagResponse.newBuilder();
            builder.setTagId(tag.getTagId());
            builder.setTagName(tag.getTagName());
            getTagResponse = builder
                    .build();
            responseObserver.onNext(getTagResponse);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void getArticle(Empty request, StreamObserver<GetArticleResponse> responseObserver) {
        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            GetArticleResponse getArticleResponse;
            GetArticleResponse.Builder builder = GetArticleResponse.newBuilder();
            builder.setArticleId(article.articleId);
            builder.setArticleTitle(article.articleTitle);
            getArticleResponse = builder
                    .build();
            responseObserver.onNext(getArticleResponse);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void getArticleByTag(GetArticleByTagRequest request, StreamObserver<GetArticleByTagResponse> responseObserver) {

        List<Article> newArticles = new ArrayList<>();
        for (int i = 0; i < articles.size(); i++) {
            Article article1 = articles.get(i);
            if (article1.tagList.contains(request.getTagId())) {
                newArticles.add(article1);
                break;
            }
        }

        for (int i = 0; i < newArticles.size(); i++) {
            Article article = newArticles.get(i);
            GetArticleByTagResponse getArticleByTagResponse = GetArticleByTagResponse.newBuilder()
                    .setArticleId(article.articleId)
                    .setArticleTitle(article.articleTitle)
                    .build();
            responseObserver.onNext(getArticleByTagResponse);
        }
        responseObserver.onCompleted();
    }
}
