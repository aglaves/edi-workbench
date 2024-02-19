package io.lunaver.edi.parser.x12;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.lunaver.edi.document.anomoly.Anomaly;
import io.lunaver.edi.document.x12.GESegment;
import io.lunaver.edi.document.x12.GSFunctionalGroupSegment;
import io.lunaver.edi.document.x12.IEASegment;
import io.lunaver.edi.document.x12.ISAEnvelopeSegment;
import io.lunaver.edi.document.x12.SESegment;
import io.lunaver.edi.document.x12.STTransactionSetSegment;
import io.lunaver.edi.document.x12.Segment;
import io.lunaver.edi.document.x12.X12Document;
import io.lunaver.edi.parser.exception.ParsingException;
import io.lunaver.edi.parser.x12.anomaly.ISAParsingAnomaly;

public class GenericX12Document implements X12Document {
    private static final int LENGTH_OF_ISA_SEGMENT = 106;
    private static final String ISA_SEGMENT_NAME = "ISA";
    
    private char elementSeparator;
    private char segmentSeparator;
    private char subElementSeparator;

    private List<Anomaly> anomolies = new ArrayList<>();
    private ISAEnvelopeSegment isaEnvelope;
    private GSFunctionalGroupSegment currentGSFunctionalGroup;
    private STTransactionSetSegment currentStTransactionSet;

    public GenericX12Document(InputStream inputStream) throws IOException, ParsingException {
        initializeDocument(inputStream);
    }

    private void initializeDocument(InputStream documentStream) throws IOException, ParsingException {
        byte[] bytes = new byte[LENGTH_OF_ISA_SEGMENT];

        documentStream.read(bytes, 0, LENGTH_OF_ISA_SEGMENT);        
        
        String firstLine = new String(bytes, 0, LENGTH_OF_ISA_SEGMENT);
        if (!validateISALineLength(firstLine)) {
            addAnomoly(new ISAParsingAnomaly("Length of X12 document is too short."));
            return;
        }

        if (!validateISASegmentName(firstLine)) {
            addAnomoly(new ISAParsingAnomaly("X12 document does not start with ISA segment."));
            return;
        } 
        
        try {
            setElementSeparator(parseElementSeparator(firstLine));
        } catch (ParsingException e) {
            addAnomoly(new ISAParsingAnomaly(e.getMessage()));
        }

        try {
            setSegmentSeparator(parseSegmentSeparator(firstLine));
        } catch (ParsingException e) {
            addAnomoly(new ISAParsingAnomaly(e.getMessage()));
        }

        try {
            setSubElementSeparator(parseSubElementSeparator(firstLine));
        } catch (ParsingException e) {
            addAnomoly(new ISAParsingAnomaly(e.getMessage()));
        }

        // TODO Check version and if 4020 or greater, parse loop segment
        
        setISAEnvelope(new ISALineParser().parseISAEnvelope(firstLine, getElementSeparator(), getSegmentSeparator()));
    }

    private boolean validateISALineLength(String line) {
        if (line.length() < LENGTH_OF_ISA_SEGMENT)
            return false;
        return true;
    }

    private boolean validateISASegmentName(String line) {
        if (!line.startsWith(ISA_SEGMENT_NAME))
            return false;
        return true;
    }

    private char parseElementSeparator(String line) throws ParsingException {
        if (line.length()<4) throw new ParsingException("X12 document is too short.");
        return line.charAt(3);
    }

    private char parseSegmentSeparator(String line) throws ParsingException {
        if (line.length()<LENGTH_OF_ISA_SEGMENT) throw new ParsingException("X12 document is too short.");
        return line.charAt(LENGTH_OF_ISA_SEGMENT - 1);
    }

    private char parseSubElementSeparator(String line) throws ParsingException {
        if (line.length()<LENGTH_OF_ISA_SEGMENT - 1) throw new ParsingException("X12 document is too short.");
        return line.charAt(LENGTH_OF_ISA_SEGMENT - 2); 
    }

    public void addAnomoly(Anomaly anomaly) {
        anomolies.add(anomaly);
    }

    public char getElementSeparator() {
        return this.elementSeparator;
    }

    public void setElementSeparator(char elementSeparator) {
        this.elementSeparator = elementSeparator;
    }

    public char getSegmentSeparator() {
        return segmentSeparator;
    }

    public void setSegmentSeparator(char segmentSeparator) {
        this.segmentSeparator = segmentSeparator;
    }

    public ISAEnvelopeSegment getISAEnvelope() {
        return this.isaEnvelope;
    }

    public void setISAEnvelope(ISAEnvelopeSegment isaEnvelope) {
        this.isaEnvelope = isaEnvelope;
    }

    public char getSubElementSeparator() {
        return subElementSeparator;
    }

    public void setSubElementSeparator(char subElementSeparator) {
        this.subElementSeparator = subElementSeparator;
    }

    public void beginGSFunctionalGroup(GSFunctionalGroupSegment gsFunctionalGroup) {
        // TODO Validate that we are in an ISA segment.  It is possible someone incorrectly goes straight to a GS without an ISA.
        // if (this.gsFunctionalGroups == null)
        //     gsFunctionalGroups = new ArrayList<>();
        // gsFunctionalGroups.add(gsFunctionalGroup);
        isaEnvelope.addGSFunctionalGroup(gsFunctionalGroup);

        currentGSFunctionalGroup = gsFunctionalGroup;
    }

    public void endGSFunctionalGroup(GESegment geSegment) {
        new GSLineParser().processGESegment(currentGSFunctionalGroup, geSegment);
    }

    public void beginSTTransactionSet(STTransactionSetSegment stTransactionSet) {
        // TODO Validate that we are in an existing GS Segment.  It is possible someone incorrectly goes straight to an ST without a GS
        if (currentGSFunctionalGroup.getStTransactionSets() == null)
            currentGSFunctionalGroup.setStTransactionSets(new ArrayList<>());
        currentGSFunctionalGroup.getStTransactionSets().add(stTransactionSet);
        currentStTransactionSet = stTransactionSet;
    }

    public void endSTTransactionSet(SESegment seSegment) {
        new STLineParser().processSESegment(currentStTransactionSet, seSegment);
    }

    public void endISAEnvelope(IEASegment ieaSegment) {
        new ISALineParser().processIEASegment(isaEnvelope, ieaSegment);
    }

    public void addSegment(Segment segment) {
        currentStTransactionSet.addSegment(segment);
    }
}
