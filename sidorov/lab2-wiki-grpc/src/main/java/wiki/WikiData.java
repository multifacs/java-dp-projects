package wiki;

import com.google.protobuf.ByteString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WikiData {
    private List<Record> records;
    private List<Tag> tags;
    private List<File> files;

    public WikiData() {
        records = new ArrayList<>();
        tags = new ArrayList<>();
        files = new ArrayList<>();

        Record record1 = new Record(0, "Record 1", "Some text");
        Record record2 = new Record(1, "Record 2", "Some text");

        Tag tag1 = new Tag(0, "Sports");
        Tag tag2 = new Tag(1, "Animals");

        attachTag(0, 0);
        attachTag(1, 1);

        records.addAll(List.of(record1, record2));
        tags.addAll(List.of(tag1, tag2));
    }

    public int addRecord(String recordTitle, String recordText) {
        int id = records.size();
        Record record = new Record(id, recordTitle, recordText);
        records.add(record);
        return id;
    }

    public void attachTag(int recordId, int tagId) {
        for (Record record : records) {
            if (record.getRecordId() == recordId) {
                record.getTagList().add(tagId);
            }
        }
    }

    public void attachFile(int recordId, String fileName, ByteString fileData) {
        File file = new File(fileName, fileData);
        files.add(file);
        for (Record record : records) {
            if (record.getRecordId() == recordId) {
                record.getFileList().add(fileName);
            }
        }
    }

    public int addTag(String tagName) {
        int id = tags.size();
        Tag tag = new Tag(id, tagName);
        tags.add(tag);
        return id;
    }

    public File getFile(int recordId, String fileName) {
        for (Record record : records) {
            if (record.getRecordId() == recordId) {
                for (String name : record.getFileList()) {
                    if (Objects.equals(name, fileName)) {
                        for (File file : files) {
                            if (Objects.equals(file.getFileName(), fileName)) {
                                return file;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public void removeTag(int tagId) {
        tags.removeIf(tag -> tag.getTagId() == tagId);
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Record> getRecords() {
        return records;
    }

    public List<Record> getRecordsByTag(int tagId) {
        return records
                .stream()
                .filter(record -> record.getTagList().contains(tagId))
                .toList();
    }
}
