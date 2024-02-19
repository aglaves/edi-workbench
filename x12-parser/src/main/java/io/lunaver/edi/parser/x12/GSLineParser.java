package io.lunaver.edi.parser.x12;

import java.util.Scanner;

import io.lunaver.edi.document.x12.GESegment;
import io.lunaver.edi.document.x12.GSFunctionalGroupSegment;
import io.lunaver.edi.parser.x12.anomaly.GSParsingAnomaly;

public class GSLineParser {
    public GSFunctionalGroupSegment parseFunctionalGroupSegment(Scanner scanner) {
        GSFunctionalGroupSegment gsFunctionalGroup = new GSFunctionalGroupSegment();
        // TODO Add error check if not enough elements
        // TODO If subelement separator exists, add error message 
        if (scanner.hasNext())
            gsFunctionalGroup.setFunctionalIdCode(scanner.next());
        if (scanner.hasNext())
            gsFunctionalGroup.setSenderId(scanner.next());
        if (scanner.hasNext())
            gsFunctionalGroup.setReceiverId(scanner.next());
        if (scanner.hasNext())
            gsFunctionalGroup.setDate(scanner.next());
        if (scanner.hasNext())
            gsFunctionalGroup.setTime(scanner.next());
        if (scanner.hasNext())
            gsFunctionalGroup.setControlNumber(scanner.next());
        if (scanner.hasNext())
            gsFunctionalGroup.setAgencyCode(scanner.next());
        if (scanner.hasNext())
            gsFunctionalGroup.setVersion(scanner.next());

        return gsFunctionalGroup;
    }

    public GESegment parseGESegment(Scanner scanner) {
        GESegment geSegment = new GESegment();

        if (scanner.hasNext())
            geSegment.setStTransactionSetCount(scanner.next());
        if (scanner.hasNext())
            geSegment.setControlNumber(scanner.next());

            // TODO Add error checking
        return geSegment;
    }

    public void processGESegment(GSFunctionalGroupSegment gsFunctionalGroup, GESegment geSegment) {
        verifyGEControlNumber(gsFunctionalGroup, geSegment.getControlNumber());
        verifyStTransactionSetCount(gsFunctionalGroup, geSegment.getStTransactionSetCount());

        if (gsFunctionalGroup.isClosed())
            gsFunctionalGroup.addAnomoly(new GSParsingAnomaly("GE Segment already received."));

        gsFunctionalGroup.setClosed(true);
    }

    private void verifyGEControlNumber(GSFunctionalGroupSegment gsFunctionalGroup, String geControlNumber) {
        if (geControlNumber == null) gsFunctionalGroup.addAnomoly(new GSParsingAnomaly("Control Number on GE Segment is missing"));
        if ("".equals(geControlNumber.trim())) gsFunctionalGroup.addAnomoly(new GSParsingAnomaly("Control Number on GE Segment is missing"));
        if (!gsFunctionalGroup.getControlNumber().equals(geControlNumber))
            gsFunctionalGroup.addAnomoly(
                new GSParsingAnomaly("Control Number on GE Segment does not match GS Control Number.  GS Control Number: " 
                    + gsFunctionalGroup.getControlNumber() + " GE Control Number: " + geControlNumber));
    }

    private void verifyStTransactionSetCount(GSFunctionalGroupSegment gsFunctionalGroup, String parsedSegmentCount) {
        if ((parsedSegmentCount == null) || ("".equals(parsedSegmentCount.trim())))
            gsFunctionalGroup.addAnomoly(new GSParsingAnomaly("Segment count on GE Segment is missing"));
        try {
            int segmentCount = Integer.parseInt(parsedSegmentCount);
            if (segmentCount != gsFunctionalGroup.getStTransactionSets().size())
            gsFunctionalGroup.addAnomoly(new GSParsingAnomaly("Segment count on GE Segment does not match number of segments."));
        } catch (NumberFormatException e) {
            gsFunctionalGroup.addAnomoly(new GSParsingAnomaly("Segment count on GE Segment must be an integer."));
        }
    }
}
