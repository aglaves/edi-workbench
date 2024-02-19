package io.lunaver.edi.parser.x12.anomaly;

import io.lunaver.edi.document.anomoly.Anomaly;

public class GSParsingAnomaly implements Anomaly {
    private String message;

    public GSParsingAnomaly(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
    
}
