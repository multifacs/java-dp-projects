package wiki;

import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WikiServer extends WikiServiceGrpc.WikiServiceImplBase {

    private static final List<Record> records = new ArrayList<>();
    private static final List<String> tags = new ArrayList<>();
    private static final List<String> filenames = new ArrayList<>();
    private static final List<ByteString> files = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080).addService(new WikiServer()).build();
        server.start();
        server.awaitTermination();
    }

    @Override
    public void getRecords(Empty request,
                          StreamObserver<GetRecordResponse> responseObserver) {
        GetRecordResponse.Builder getRecords = GetRecordResponse.newBuilder();

        for (Record record : records) {
            RecordSimpleStruct.Builder recordSimpleStruct = RecordSimpleStruct.newBuilder();
            recordSimpleStruct.setId(record.getId());
            recordSimpleStruct.setName(record.getName());
            getRecords.addRecords(recordSimpleStruct);
        }

        responseObserver.onNext(getRecords.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getRecordsByTag(TagId request,
                               StreamObserver<GetRecordResponse> responseObserver) {
        GetRecordResponse.Builder getRecords = GetRecordResponse.newBuilder();

        for (Record record : records) {
            if (record.getTags().contains(request.getTag())) {
                RecordSimpleStruct.Builder recordSimpleStruct = RecordSimpleStruct.newBuilder();
                recordSimpleStruct.setId(record.getId());
                recordSimpleStruct.setName(record.getName());
                getRecords.addRecords(recordSimpleStruct);
            }
        }

        responseObserver.onNext(getRecords.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getFile(GetFileRequest request,
                        StreamObserver<FileData> responseObserver) {
        FileData.Builder fileData = FileData.newBuilder();
        for (ByteString file : files) {
            if (files.indexOf(file) == filenames.indexOf(request.getFileName())) {
                fileData.setFile(file);
            }
        }

        responseObserver.onNext(fileData.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getTags(Empty request,
                        StreamObserver<GetTagResponse> responseObserver) {
        GetTagResponse.Builder getTags = GetTagResponse.newBuilder();

        for (String tag : tags) {
            TagId.Builder tagId = TagId.newBuilder();
            tagId.setTag(tag);
            getTags.addTags(tagId);
        }

        responseObserver.onNext(getTags.build());
        responseObserver.onCompleted();
    }

    @Override
    public void addRecord(AddRecordRequest request,
                          StreamObserver<RecordId> responseObserver) {
        Record record = new Record(records.size(), request.getName(), request.getText());
        records.add(record);
        responseObserver.onNext(RecordId.newBuilder().setId(record.getId()).build());
        responseObserver.onCompleted();
    }

    @Override
    public void attachTag(AttachTagRequest request,
                          StreamObserver<Empty> responseObserver) {
        for (Record record : records) {
            if (record.getId() == request.getId()) {
                for (TagId tag : request.getTagsList()) {
                    if (tags.contains(tag.getTag())) {
                        record.getTags().add(tag.getTag());
                    }
                }
            }
        }
    }

    @Override
    public void attachFile(AttachFileRequest request,
                           StreamObserver<Empty> responseObserver) {
        for (Record record : records) {
            if (record.getId() == request.getId()) {
                record.getFiles().add(request.getFileName());
            }
        }
        filenames.add(request.getFileName());
        files.add(request.getFile());

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void addTag(TagId request,
                       StreamObserver<Empty> responseObserver) {
        tags.add(request.getTag());

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void removeTag(TagId request,
                          StreamObserver<Empty> responseObserver) {
        tags.removeIf(x -> Objects.equals(x, request.getTag()));

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
}
