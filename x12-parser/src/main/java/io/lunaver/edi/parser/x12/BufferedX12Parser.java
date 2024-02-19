package io.lunaver.edi.parser.x12;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import io.lunaver.edi.document.x12.GESegment;
import io.lunaver.edi.document.x12.GSFunctionalGroupSegment;
import io.lunaver.edi.document.x12.IEASegment;
import io.lunaver.edi.document.x12.SESegment;
import io.lunaver.edi.document.x12.STTransactionSetSegment;
import io.lunaver.edi.document.x12.Segment;
import io.lunaver.edi.document.x12.X12Document;
import io.lunaver.edi.parser.exception.MissingParameterException;
import io.lunaver.edi.parser.exception.ParsingException;

public class BufferedX12Parser implements X12Parser {
    @Override
    public X12Document parse(InputStream documentStream) throws IOException, ParsingException {
        if (documentStream == null)
            throw new MissingParameterException("Document input stream is a required parameter.");
        
        GenericX12Document x12Document = new GenericX12Document(documentStream);
        Scanner scanner = null;
        try {
            scanner = new Scanner(documentStream)
                .useDelimiter("\\Q" + String.valueOf(x12Document.getSegmentSeparator()) + "\\E");

            while (scanner.hasNext()) {
                String segmentLine = scanner.next();
                Segment segment = parseSegment(segmentLine, x12Document.getElementSeparator());

                if (segment instanceof GSFunctionalGroupSegment)
                    x12Document.beginGSFunctionalGroup((GSFunctionalGroupSegment)segment);
                else if (segment instanceof STTransactionSetSegment)
                    x12Document.beginSTTransactionSet((STTransactionSetSegment) segment);
                else if (segment instanceof SESegment)
                    x12Document.endSTTransactionSet((SESegment) segment);
                else if (segment instanceof GESegment)
                    x12Document.endGSFunctionalGroup((GESegment) segment);
                else if (segment instanceof IEASegment)
                    x12Document.endISAEnvelope((IEASegment) segment);
                else
                    x12Document.addSegment(segment);
                
            }
        } finally {
            if (scanner != null) scanner.close();
        }
        return x12Document;
    }

    private Segment parseSegment(String segmentLine, char elementSeparator) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(segmentLine)
                .useDelimiter("\\Q" + String.valueOf(elementSeparator) + "\\E");

            
            if (scanner.hasNext()) {
                String segmentType = scanner.next();

                if ("GS".equals(segmentType))
                    return new GSLineParser().parseFunctionalGroupSegment(scanner);
                else if ("ST".equals(segmentType))
                    return new STLineParser().parseSTransactionSetSegment(scanner);
                else if ("SE".equals(segmentType))
                    return new STLineParser().parseSESegment(scanner);
                else if ("GE".equals(segmentType))
                    return new GSLineParser().parseGESegment(scanner);
                else if ("IEA".equals(segmentType))
                    return new ISALineParser().parseIEASegment(scanner);
                else
                    return new GenericSegmentParser().parseGenericSegment(segmentType, scanner);
            }

            // TODO Don't return null here.  Throw exception if segment not created.
            return null;
        } finally {
            if (scanner != null) scanner.close();
        }
    }
}
