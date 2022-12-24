package wiki;

import java.util.ArrayList;
import java.util.List;

public class Record {
    private final int id;
    private final String name;
    private final String text;
    private final List<String> tags = new ArrayList<>();
    private final List<String> files = new ArrayList<>();

    public Record(int id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<String> getFiles() {
        return files;
    }
}
