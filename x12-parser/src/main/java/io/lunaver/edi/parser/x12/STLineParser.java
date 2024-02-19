package io.lunaver.edi.parser.x12;

import java.util.Scanner;

import io.lunaver.edi.document.x12.SESegment;
import io.lunaver.edi.document.x12.STTransactionSetSegment;
import io.lunaver.edi.parser.x12.anomaly.STParsingAnomaly;

public class STLineParser {
    public STTransactionSetSegment parseSTransactionSetSegment(Scanner scanner) {
        STTransactionSetSegment stTransactionSet = new STTransactionSetSegment();
        if (scanner.hasNext())
            stTransactionSet.setIdentifierCode(scanner.next());
        if (scanner.hasNext())
            stTransactionSet.setControlNumber(scanner.next());

        // TODO Validate identifier code and control number exist

        return stTransactionSet;
    }

    public SESegment parseSESegment(Scanner scanner) {
        SESegment seSegment = new SESegment();

        if (scanner.hasNext())
            seSegment.setSegmentCount(scanner.next());
        if (scanner.hasNext())
            seSegment.setControlNumber(scanner.next());

        // TODO Validate segment count and control number exist

        return seSegment;
    }

    public void processSESegment(STTransactionSetSegment stTransactionSet, SESegment seSegment) {
        verifySEControlNumber(stTransactionSet, seSegment.getControlNumber());
        verifySegmentCount(stTransactionSet, seSegment.getSegmentCount());

        if (stTransactionSet.isClosed())
            stTransactionSet.addAnomaly(new STParsingAnomaly("SE Segment already received."));

        stTransactionSet.setClosed(true);
    }

    private void verifySEControlNumber(STTransactionSetSegment stTransactionSet, String seControlNumber) {
        if (seControlNumber == null) stTransactionSet.addAnomaly(new STParsingAnomaly("Control Number on SE Segment is missing"));
        if ("".equals(seControlNumber.trim())) stTransactionSet.addAnomaly(new STParsingAnomaly("Control Number on SE Segment is missing"));
        if (!stTransactionSet.getControlNumber().equals(seControlNumber))
            stTransactionSet.addAnomaly(
                new STParsingAnomaly("Control Number on SE Segment does not match ST Control Number.  ST Control Number: " 
                    + stTransactionSet.getControlNumber() + " SE Control Number: " + seControlNumber));
    }

    private void verifySegmentCount(STTransactionSetSegment stTransactionSet, String parsedSegmentCount) {
        if ((parsedSegmentCount == null) || ("".equals(parsedSegmentCount.trim())))
            stTransactionSet.addAnomaly(new STParsingAnomaly("Segment count on SE Segment is missing"));
        try {
            int segmentCount = Integer.parseInt(parsedSegmentCount);
            if (segmentCount != stTransactionSet.getSegments().size())
                stTransactionSet.addAnomaly(new STParsingAnomaly("Segment count on SE Segment does not match number of segments."));
        } catch (NumberFormatException e) {
            stTransactionSet.addAnomaly(new STParsingAnomaly("Segment count on SE Segment must be an integer."));
        }
    }
}
