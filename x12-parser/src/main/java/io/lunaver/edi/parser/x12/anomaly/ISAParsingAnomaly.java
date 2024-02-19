package io.lunaver.edi.parser.x12.anomaly;

import io.lunaver.edi.document.anomoly.Anomaly;

public class ISAParsingAnomaly implements Anomaly {
    private String message;

    public ISAParsingAnomaly(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}