package io.lunaver.edi.document.x12;

import io.lunaver.edi.document.EDIDocument;

public interface X12Document extends EDIDocument {
    public char getSegmentSeparator();
    char getElementSeparator();
    char getSubElementSeparator();
    ISAEnvelopeSegment getISAEnvelope();
}
