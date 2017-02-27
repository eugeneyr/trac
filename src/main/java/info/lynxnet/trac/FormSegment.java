package info.lynxnet.trac;

public class FormSegment implements FormElement {
    private String value;
    private int offset;

    public FormSegment(String value, int offset) {
        this.value = value;
        this.offset = offset;
    }

    public String getValue() {
        return value;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
