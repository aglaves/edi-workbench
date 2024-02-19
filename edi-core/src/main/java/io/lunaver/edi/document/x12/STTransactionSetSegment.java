package io.lunaver.edi.document.x12;

import java.util.ArrayList;
import java.util.List;

import io.lunaver.edi.document.anomoly.Anomaly;

public class STTransactionSetSegment implements Segment {
    private String identifierCode;
    private String controlNumber;
    private List<Segment> segments;
    private List<Anomaly> anomalies;
    private boolean closed = true;

    public STTransactionSetSegment(){
        anomalies = new ArrayList<>();
        closed = false;
    }

    public String getIdentifierCode() {
        return identifierCode;
    }

    public void setIdentifierCode(String identifierCode) {
        this.identifierCode = identifierCode;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    public void addSegment(Segment segment) {
        if (this.segments == null)
            segments = new ArrayList<>();
        segments.add(segment);
    }

    public List<Segment> getSegments() {
        return this.segments;
    }

    public Segment getSegment(int segmentNumber) {
        if (this.segments == null) return null;
        return segments.get(segmentNumber);
    }

    public void addAnomaly(Anomaly anomaly) {
        if (this.anomalies == null)
            anomalies = new ArrayList<>();
        anomalies.add(anomaly);
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
