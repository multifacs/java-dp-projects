package encyclopedia;

import com.google.protobuf.ByteString;

import java.util.ArrayList;
import java.util.List;

public class Article {
    public int articleId;
    public String articleTitle;
    public String articleText;
    public List<Integer> tagList = new ArrayList<>();
    public List<String> fileNames = new ArrayList<>();
    public List<byte[]> files = new ArrayList<>();

    public Article(int articleId, String articleTitle, String articleText) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleText = articleText;
    }

    public void addFile(String fileName, byte[] file) {
        this.fileNames.add(fileName);
        this.files.add(file);
    }
}
