package io.lunaver.edi.parser.x12;

import java.util.Scanner;

import io.lunaver.edi.document.x12.GenericSegment;

public class GenericSegmentParser {
    public GenericSegment parseGenericSegment(String elementType, Scanner scanner) {
        GenericSegment genericSegment = new GenericSegment(elementType);
        
        while (scanner.hasNext()) {
            genericSegment.addElement(scanner.next());
        }

        return genericSegment;
    }
}
