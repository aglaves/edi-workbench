package io.lunaver.edi.document.x12;

import java.util.ArrayList;
import java.util.List;

import io.lunaver.edi.document.anomoly.Anomaly;

public class GSFunctionalGroupSegment implements Segment {
    private String functionalIdCode;
    private String senderId;
    private String receiverId;
    private String date;
    private String time;
    private String controlNumber;
    private String agencyCode;
    private String version;
    private List<STTransactionSetSegment> stTransactionSets;
    private List<Anomaly> anomalies;
    private boolean closed;
    
    public GSFunctionalGroupSegment() {
        anomalies = new ArrayList<>();
        closed = false;
    }

    public String getFunctionalIdCode() {
        return functionalIdCode;
    }
    
    public void setFunctionalIdCode(String functionalIdCode) {
        this.functionalIdCode = functionalIdCode;
    }
    
    public String getSenderId() {
        return senderId;
    }
    
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
    
    public String getReceiverId() {
        return receiverId;
    }
    
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getTime() {
        return time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public String getControlNumber() {
        return controlNumber;
    }
    
    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }
    
    public String getAgencyCode() {
        return agencyCode;
    }
    
    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String version) {
        this.version = version;
    }

    public List<STTransactionSetSegment> getStTransactionSets() {
        return stTransactionSets;
    }

    public void setStTransactionSets(List<STTransactionSetSegment> stTransactionSets) {
        this.stTransactionSets = stTransactionSets;
    }

    public void addAnomoly(Anomaly anomaly) {
        if (anomalies == null)
            anomalies = new ArrayList<>();
        anomalies.add(anomaly);
    }

    public boolean isClosed() {
        return this.closed;
    }

    public void setClosed(boolean closed) {
        this.closed = true;
    }
}
