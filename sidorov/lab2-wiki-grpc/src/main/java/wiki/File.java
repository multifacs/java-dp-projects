package wiki;

import com.google.protobuf.ByteString;

public class File {
    private String fileName;
    private ByteString fileData;

    public File(String fileName, ByteString fileData) {
        this.fileName = fileName;
        this.fileData = fileData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ByteString getFileData() {
        return fileData;
    }

    public void setFileData(ByteString fileData) {
        this.fileData = fileData;
    }
}
