package io.lunaver.edi.document.x12;

public class SESegment implements Segment {
    private String segmentCount;
    private String controlNumber;

    public SESegment() {}

    public String getSegmentCount() {
        return segmentCount;
    }

    public void setSegmentCount(String segmentCount) {
        this.segmentCount = segmentCount;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    
}
