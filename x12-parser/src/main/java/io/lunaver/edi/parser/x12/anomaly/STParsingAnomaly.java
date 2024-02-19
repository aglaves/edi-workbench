package io.lunaver.edi.parser.x12.anomaly;

import io.lunaver.edi.document.anomoly.Anomaly;

public class STParsingAnomaly implements Anomaly {
    private String message;

    public STParsingAnomaly(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
    
}
