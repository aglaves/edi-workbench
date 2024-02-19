package io.lunaver.edi.parser;

import java.io.IOException;
import java.io.InputStream;

import io.lunaver.edi.document.x12.X12Document;
import io.lunaver.edi.parser.exception.ParsingException;

public interface EDIDocumentParser {
    X12Document parse(InputStream documentStream) throws IOException, ParsingException;
}
