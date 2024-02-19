package io.lunaver.edi.parser.x12;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.lunaver.edi.document.x12.X12Document;
import io.lunaver.edi.parser.exception.ParsingException;
import io.lunaver.edi.parser.x12.BufferedX12Parser;

public class BufferedX12ParserGenericSegmentTest {
    @Test
    public void verifyGenericSegmentCount() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(
                ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );
        Assertions.assertEquals(8, x12Document.getISAEnvelope().getGSFunctionalGroups()
            .get(0).getStTransactionSets()
            .get(0).getSegments().size());
    }
}
