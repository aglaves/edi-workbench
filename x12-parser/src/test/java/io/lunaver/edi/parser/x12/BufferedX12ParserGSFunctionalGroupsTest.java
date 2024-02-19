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

public class BufferedX12ParserGSFunctionalGroupsTest {
    @Test
    public void verifyGSFunctionalGroupSizeWhenSingleFunctionalGroup() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(
                ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );
        Assertions.assertEquals(1, x12Document.getISAEnvelope().getGSFunctionalGroups().size());
    }

    @Test
    public void verifyGSFunctionalIdCode() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(
                ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );
        Assertions.assertEquals("PO", x12Document.getISAEnvelope().getGSFunctionalGroups().get(0).getFunctionalIdCode());
    }

    @Test
    public void verifyGSSenderId() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(
                ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );
        Assertions.assertEquals("GSender987654321", x12Document.getISAEnvelope().getGSFunctionalGroups().get(0).getSenderId());
    }

    @Test
    public void verifyGSReceiverId() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(
                ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );
        Assertions.assertEquals("GSReceiver1234567", x12Document.getISAEnvelope().getGSFunctionalGroups().get(0).getReceiverId());
    }

    @Test
    public void verifyGSDate() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(
                ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );
        Assertions.assertEquals("20240207", x12Document.getISAEnvelope().getGSFunctionalGroups().get(0).getDate());
    }

    @Test
    public void verifyGSTime() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(
                ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );
        Assertions.assertEquals("2246", x12Document.getISAEnvelope().getGSFunctionalGroups().get(0).getTime());
    }

    @Test
    public void verifyGSControlNumber() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(
                ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );
        Assertions.assertEquals("001", x12Document.getISAEnvelope().getGSFunctionalGroups().get(0).getControlNumber());
    }

    @Test
    public void verifyGSAgencyCode() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(
                ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );
        Assertions.assertEquals("X", x12Document.getISAEnvelope().getGSFunctionalGroups().get(0).getAgencyCode());
    }

    @Test
    public void verifyGSVersion() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(
                ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );
        Assertions.assertEquals("004010X357", x12Document.getISAEnvelope().getGSFunctionalGroups().get(0).getVersion());
    }

    // TODO Validate valid date
    // TODO Validate valid time
    // TODO Validate GE closes GS segment
    // TODO Validate sub element separator
}