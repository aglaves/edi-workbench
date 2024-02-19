package io.lunaver.edi.document.x12;

public class GESegment implements Segment {
    private String stTransactionSetCount;
    private String controlNumber;
    
    public GESegment() {}

    public String getStTransactionSetCount() {
        return stTransactionSetCount;
    }

    public void setStTransactionSetCount(String stTransactionSetCount) {
        this.stTransactionSetCount = stTransactionSetCount;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }
}
