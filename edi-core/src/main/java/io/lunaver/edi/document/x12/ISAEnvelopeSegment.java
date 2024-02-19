package io.lunaver.edi.document.x12;

import java.util.ArrayList;
import java.util.List;

import io.lunaver.edi.document.anomoly.Anomaly;

public class ISAEnvelopeSegment implements Segment {
    private String authorizationInformationQualifier;
    private String authorizationInformation;
    private String securityInformationQualifier;
    private String securityInformation;
    private String interchangeSenderIdQualifier;
    private String interchangeSenderId;
    private String interchangeReceiverIdQualifier;
    private String interchangeReceiverId;
    private String interchangeEnvelopeDate;
    private String interchangeEnvelopeTime;
    private String repititionSeparator;
    private String interchangeControlStandardIdentifier;
    private String interchangeControlVersionNumber;
    private String interchangeControlNumber;
    private String acknowledgmentRequested;
    private String usageIndicator;
    private String subElementSeparator;
    private List<GSFunctionalGroupSegment> gsFunctionalGroups;
    private List<Anomaly> anomalies;
    private boolean closed;

    public ISAEnvelopeSegment() {
        anomalies = new ArrayList<>();
        closed = false;
    }

    public String getAuthorizationInformationQualifier() {
        return authorizationInformationQualifier;
    }

    public void setAuthorizationInformationQualifier(String authorizationInformationQualifier) {
        this.authorizationInformationQualifier = authorizationInformationQualifier;
    }
    
    public String getAuthorizationInformation() {
        return authorizationInformation;
    }
    
    public void setAuthorizationInformation(String authorizationInformation) {
        this.authorizationInformation = authorizationInformation;
    }
    
    public String getSecurityInformationQualifier() {
        return securityInformationQualifier;
    }
    
    public void setSecurityInformationQualifier(String securityInformationQualifier) {
        this.securityInformationQualifier = securityInformationQualifier;
    }
    
    public String getSecurityInformation() {
        return securityInformation;
    }
    
    public void setSecurityInformation(String securityInformation) {
        this.securityInformation = securityInformation;
    }
    
    public String getInterchangeSenderIdQualifier() {
        return interchangeSenderIdQualifier;
    }
    
    public void setInterchangeSenderIdQualifier(String interchangeSenderIdQualifier) {
        this.interchangeSenderIdQualifier = interchangeSenderIdQualifier;
    }
    
    public String getInterchangeSenderId() {
        return interchangeSenderId;
    }
    
    public void setInterchangeSenderId(String interchangeSenderId) {
        this.interchangeSenderId = interchangeSenderId;
    }
    
    public String getInterchangeReceiverIdQualifier() {
        return interchangeReceiverIdQualifier;
    }
    
    public void setInterchangeReceiverIdQualifier(String interchangeReceiverIdQualifier) {
        this.interchangeReceiverIdQualifier = interchangeReceiverIdQualifier;
    }
    
    public String getInterchangeReceiverId() {
        return interchangeReceiverId;
    }
    
    public void setInterchangeReceiverId(String interchangeReceiverId) {
        this.interchangeReceiverId = interchangeReceiverId;
    }
    
    public String getInterchangeEnvelopeDate() {
        return interchangeEnvelopeDate;
    }
    
    public void setInterchangeEnvelopeDate(String interchangeEnvelopeDate) {
        this.interchangeEnvelopeDate = interchangeEnvelopeDate;
    }
    
    public String getInterchangeEnvelopeTime() {
        return interchangeEnvelopeTime;
    }
    
    public void setInterchangeEnvelopeTime(String interchangeEnvelopeTime) {
        this.interchangeEnvelopeTime = interchangeEnvelopeTime;
    }
    
    public String getInterchangeControlStandardIdentifier() {
        return interchangeControlStandardIdentifier;
    }
    
    public void setInterchangeControlStandardIdentifier(String interchangeControlStandardIdentifier) {
        this.interchangeControlStandardIdentifier = interchangeControlStandardIdentifier;
    }
    
    public String getInterchangeControlVersionNumber() {
        return interchangeControlVersionNumber;
    }
    
    public void setInterchangeControlVersionNumber(String interchangeControlVersionNumber) {
        this.interchangeControlVersionNumber = interchangeControlVersionNumber;
    }
    
    public String getInterchangeControlNumber() {
        return interchangeControlNumber;
    }
    
    public void setInterchangeControlNumber(String interchangeControlNumber) {
        this.interchangeControlNumber = interchangeControlNumber;
    }
    
    public String getAcknowledgmentRequested() {
        return acknowledgmentRequested;
    }
    
    public void setAcknowledgmentRequested(String acknowledgmentRequested) {
        this.acknowledgmentRequested = acknowledgmentRequested;
    }
    
    public String getUsageIndicator() {
        return usageIndicator;
    }
    
    public void setUsageIndicator(String usageIndicator) {
        this.usageIndicator = usageIndicator;
    }
    
    public String getSubElementSeparator() {
        return subElementSeparator;
    }
    
    public void setSubElementSeparator(String subElementSeparator) {
        this.subElementSeparator = subElementSeparator;
    }

    public String getRepititionSeparator() {
        return repititionSeparator;
    }

    public void setRepititionSeparator(String repititionSeparator) {
        this.repititionSeparator = repititionSeparator;
    }

    public List<GSFunctionalGroupSegment> getGSFunctionalGroups() {
        return gsFunctionalGroups;
    }

    public void addGSFunctionalGroup(GSFunctionalGroupSegment gsFunctionalGroup) {
        if (this.gsFunctionalGroups == null)
            gsFunctionalGroups = new ArrayList<>();
        gsFunctionalGroups.add(gsFunctionalGroup);
    }

    public List<Anomaly> getAnomalies() {
        return anomalies;
    }

    public void addAnomoly(Anomaly anomaly) {
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
