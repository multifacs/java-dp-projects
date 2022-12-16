package wiki;

import java.util.ArrayList;
import java.util.List;

public class Record {
    private int recordId;
    private String recordTitle;
    private String recordText;
    private List<Integer> tagList;
    private List<String> fileList;

    public Record(int recordId, String recordTitle, String recordText) {
        this.recordId = recordId;
        this.recordTitle = recordTitle;
        this.recordText = recordText;
        this.tagList = new ArrayList<>();
        this.fileList = new ArrayList<>();
    }

    public Record(int recordId, String recordTitle, String recordText, List<Integer> tagList, List<String> fileList) {
        this.recordId = recordId;
        this.recordTitle = recordTitle;
        this.recordText = recordText;
        this.tagList = tagList;
        this.fileList = fileList;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getRecordTitle() {
        return recordTitle;
    }

    public void setRecordTitle(String recordTitle) {
        this.recordTitle = recordTitle;
    }

    public String getRecordText() {
        return recordText;
    }

    public void setRecordText(String recordText) {
        this.recordText = recordText;
    }

    public List<Integer> getTagList() {
        return tagList;
    }

    public void setTagList(List<Integer> tagList) {
        this.tagList = tagList;
    }

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }
}
