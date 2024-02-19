package io.lunaver.edi.parser.x12;

import java.util.Scanner;

import io.lunaver.edi.document.x12.IEASegment;
import io.lunaver.edi.document.x12.ISAEnvelopeSegment;
import io.lunaver.edi.parser.x12.anomaly.ISAParsingAnomaly;

public class ISALineParser {
    public ISAEnvelopeSegment parseISAEnvelope(String isaEnvelopeLine, char elementSeparator, char segmentSeparator) {
            String[] elements = isaEnvelopeLine.split("\\Q" + String.valueOf(elementSeparator) + "\\E");
            ISAEnvelopeSegment isaSegment = new ISAEnvelopeSegment();
            if (!validateElementLength(elements[1], 2))
                isaSegment.addAnomoly(new ISAParsingAnomaly("Authorization Information Qualifier (ISA01) is not the correct length.  It should be exactly 2 characters."));
            isaSegment.setAuthorizationInformationQualifier(elements[1]);
            
            if (validateElementLength(elements[2], 10)) isaSegment.setAuthorizationInformation(elements[2]);
            if (validateElementLength(elements[3], 2)) isaSegment.setSecurityInformationQualifier(elements[3]);
            if (validateElementLength(elements[4], 10)) isaSegment.setSecurityInformation(elements[4]);
            if (validateElementLength(elements[5], 2)) isaSegment.setInterchangeSenderIdQualifier(elements[5]);
            if (validateElementLength(elements[6], 15)) isaSegment.setInterchangeSenderId(elements[6]);
            if (validateElementLength(elements[7], 2)) isaSegment.setInterchangeReceiverIdQualifier(elements[7]);
            if (validateElementLength(elements[8], 15)) isaSegment.setInterchangeReceiverId(elements[8]);
            // TODO Validate Date format
            if (validateElementLength(elements[9], 6)) isaSegment.setInterchangeEnvelopeDate(elements[9]);
            // TODO Validate Time format
            if (validateElementLength(elements[10], 4)) isaSegment.setInterchangeEnvelopeTime(elements[10]);
            // TODO Check both version and repition separator.  If version < 00402 or repitition separator = 'U' then we don't have a repetition separator
            if (validateElementLength(elements[11], 1)) isaSegment.setRepititionSeparator(elements[11]);
            if (validateElementLength(elements[12], 5)) isaSegment.setInterchangeControlVersionNumber(elements[12]);
            if (validateElementLength(elements[13], 9)) isaSegment.setInterchangeControlNumber(elements[13]);
            // TODO Change to TA1 Acknowledgment Requested
            if (validateElementLength(elements[14], 1)) isaSegment.setAcknowledgmentRequested(elements[14]);
            if (validateElementLength(elements[15], 1)) isaSegment.setUsageIndicator(elements[15]);
            return isaSegment;
    }

    public IEASegment parseIEASegment(Scanner scanner) {
        IEASegment ieaSegment = new IEASegment();

        if (scanner.hasNext())
            ieaSegment.setGsSegmentCount(scanner.next());
        if (scanner.hasNext())
            ieaSegment.setControlNumber(scanner.next());

        // TODO Add validation

        return ieaSegment;
    }

    public void processIEASegment(ISAEnvelopeSegment isaEnvelope, IEASegment ieaSegment) {
        verifyIEAControlNumber(isaEnvelope, ieaSegment.getControlNumber());
        verifyGSFunctionalGroupsCount(isaEnvelope, ieaSegment.getGsSegmentCount());

        if (isaEnvelope.isClosed())
            isaEnvelope.addAnomoly(new ISAParsingAnomaly("IEA Segment already received."));

        isaEnvelope.setClosed(true);
    }

    private void verifyIEAControlNumber(ISAEnvelopeSegment isaEnvelope, String ieaControlNumber) {
        if (ieaControlNumber == null) isaEnvelope.addAnomoly(new ISAParsingAnomaly("Control Number on IEA Segment is missing"));
        if ("".equals(ieaControlNumber.trim())) isaEnvelope.addAnomoly(new ISAParsingAnomaly("Control Number on IEA Segment is missing"));
        if (!isaEnvelope.getInterchangeControlNumber().equals(ieaControlNumber))
            isaEnvelope.addAnomoly(
                new ISAParsingAnomaly("Control Number on IEA Segment does not match ISA Control Number.  ISA Control Number: " 
                    + isaEnvelope.getInterchangeControlNumber() + " IEA Control Number: " + ieaControlNumber));
    }

    private void verifyGSFunctionalGroupsCount(ISAEnvelopeSegment isaEnvelope, String parsedSegmentCount) {
        if ((parsedSegmentCount == null) || ("".equals(parsedSegmentCount.trim())))
            isaEnvelope.addAnomoly(new ISAParsingAnomaly("Segment count on IEA Segment is missing"));
        try {
            int segmentCount = Integer.parseInt(parsedSegmentCount);
            if (segmentCount != isaEnvelope.getGSFunctionalGroups().size())
                isaEnvelope.addAnomoly(new ISAParsingAnomaly("Segment count on IEA Segment does not match number of segments."));
        } catch (NumberFormatException e) {
            isaEnvelope.addAnomoly(new ISAParsingAnomaly("Segment count on IEA Segment must be an integer."));
        }
    }

    private boolean validateElementLength(String element, int length) {
        return element != null && element.length() == length;
    }
}
