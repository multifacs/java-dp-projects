package encyclopedia;

public class Tag {
    private int tagId;
    private String tagName;

    public Tag(int tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public int getTagId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }

}
