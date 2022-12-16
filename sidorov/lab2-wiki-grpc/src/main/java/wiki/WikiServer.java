package wiki;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.List;

import io.grpc.stub.StreamObserver;
import com.google.protobuf.Empty;

public class WikiServer extends WikiServiceGrpc.WikiServiceImplBase {
    private static WikiData wikiData;

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080).addService(new WikiServer()).build();
        wikiData = new WikiData();
        server.start();
        System.out.println("server started");
        server.awaitTermination();
    }

    @Override
    public void addRecord(AddRecordRequest request, StreamObserver<AddRecordResponse> responseObserver) {
        int id = wikiData.addRecord(request.getRecordTitle(), request.getRecordText());
        responseObserver.onNext(AddRecordResponse.newBuilder().setRecordId(id).build());
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<AttachTagRequest> attachTag(StreamObserver<Empty> responseObserver) {
        return new StreamObserver<AttachTagRequest>() {
            @Override
            public void onNext(AttachTagRequest attachTagRequest) {
                wikiData.attachTag(attachTagRequest.getRecordId(), attachTagRequest.getTagId());
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(Empty.newBuilder().build());
                responseObserver.onCompleted();
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }
        };
    }

    @Override
    public void attachFile(AttachFileRequest request, StreamObserver<Empty> responseObserver) {
        wikiData.attachFile(request.getRecordId(), request.getFileName(), request.getFileData());
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void addTag(AddTagRequest request, StreamObserver<AddTagResponse> responseObserver) {
        int id = wikiData.addTag(request.getTagName());
        responseObserver.onNext(AddTagResponse.newBuilder().setTagId(id).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getFile(GetFileRequest request, StreamObserver<GetFileResponse> responseObserver) {
        File file = wikiData.getFile(request.getRecordId(), request.getFileName());
        responseObserver.onNext(GetFileResponse.newBuilder().setFileData(file.getFileData()).build());
        responseObserver.onCompleted();
    }

    @Override
    public void removeTag(RemoveTagRequest request, StreamObserver<Empty> responseObserver) {
        wikiData.removeTag(request.getTagId());
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void getTag(Empty request, StreamObserver<GetTagResponse> responseObserver) {
        List<Tag> tags = wikiData.getTags();
        for (Tag tag : tags) {
            GetTagResponse getTagResponse = GetTagResponse.newBuilder()
                    .setTagId(tag.getTagId())
                    .setTagName(tag.getTagName())
                    .build();
            responseObserver.onNext(getTagResponse);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void getRecord(Empty request, StreamObserver<GetRecordResponse> responseObserver) {
        List<Record> records = wikiData.getRecords();
        for (Record record : records) {
            GetRecordResponse getRecordResponse = GetRecordResponse.newBuilder()
                    .setRecordId(record.getRecordId())
                    .setRecordTitle(record.getRecordTitle())
                    .build();
            responseObserver.onNext(getRecordResponse);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void getRecordByTag(GetRecordByTagRequest request, StreamObserver<GetRecordByTagResponse> responseObserver) {
        List<Record> records = wikiData.getRecordsByTag(request.getTagId());
        for (Record record : records) {
            GetRecordByTagResponse getRecordByTagResponse = GetRecordByTagResponse.newBuilder()
                    .setRecordId(record.getRecordId())
                    .setRecordTitle(record.getRecordTitle())
                    .build();
            responseObserver.onNext(getRecordByTagResponse);
        }
        responseObserver.onCompleted();
    }
}
