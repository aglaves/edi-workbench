package io.lunaver.edi.document.x12;

public class IEASegment implements Segment {
    private String gsSegmentCount;
    private String controlNumber;
    
    public IEASegment() {}

    public String getGsSegmentCount() {
        return gsSegmentCount;
    }

    public void setGsSegmentCount(String gsSegmentCount) {
        this.gsSegmentCount = gsSegmentCount;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    
}
