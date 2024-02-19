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

public class BufferedX12ParserISAEnvelopeTest {
    @Test
    public void verifyElementSeparator() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );
        Assertions.assertEquals("*".charAt(0), x12Document.getElementSeparator());
    }

    @Test
    public void verifySubElementSeparator() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );
        Assertions.assertEquals(">".charAt(0), x12Document.getSubElementSeparator());
    }

    @Test
    public void verifySegmentSeparator() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );
        Assertions.assertEquals("~".charAt(0), x12Document.getSegmentSeparator());
    }

    @Test
    public void verifyISAEnvelopeSet() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );

        Assertions.assertNotNull(x12Document);
    }

    @Test
    public void verifyISAEnvelopeAuthorizationInformationQualifierSet() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );

        Assertions.assertEquals("AT", x12Document.getISAEnvelope().getAuthorizationInformationQualifier());
    }

    @Test
    public void verifyISAEnvelopeAuthorizationInformationSet() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );

        Assertions.assertEquals("Athrizatn ", x12Document.getISAEnvelope().getAuthorizationInformation());
    }

    @Test
    public void verifyISAEnvelopeSecurityInformationQualifierSet() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );

        Assertions.assertEquals("SC", x12Document.getISAEnvelope().getSecurityInformationQualifier());
    }

    @Test
    public void verifyISAEnvelopeSecurityInformationSet() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );

        Assertions.assertEquals("Scrity Id ", x12Document.getISAEnvelope().getSecurityInformation());
    }

    @Test
    public void verifyISAEnvelopeSenderIdQualifierSet() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );

        Assertions.assertEquals("00", x12Document.getISAEnvelope().getInterchangeSenderIdQualifier());
    }

    @Test
    public void verifyISAEnvelopeSenderIdSet() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );

        Assertions.assertEquals("Sender987654321", x12Document.getISAEnvelope().getInterchangeSenderId());
    }

    @Test
    public void verifyISAEnvelopeReceiverIdQualifyQualifierSet() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );

        Assertions.assertEquals("ZZ", x12Document.getISAEnvelope().getInterchangeReceiverIdQualifier());
    }

    @Test
    public void verifyISAEnvelopeReceiverIdSet() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );

        Assertions.assertEquals("Receiver1234567", x12Document.getISAEnvelope().getInterchangeReceiverId());
    }

    @Test
    public void verifyISAInterchangeEnvelopeDateSet() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );

        Assertions.assertEquals("240206", x12Document.getISAEnvelope().getInterchangeEnvelopeDate());
    }

    @Test
    public void verifyISAInterchangeEnvelopeTimeSet() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );

        Assertions.assertEquals("2246", x12Document.getISAEnvelope().getInterchangeEnvelopeTime());
    }

    @Test
    public void verifyISARepitionSeparatorSet() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );

        Assertions.assertEquals("U", x12Document.getISAEnvelope().getRepititionSeparator());
    }

    @Test
    public void verifyISAInterchangeVersionNumberSet() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );

        Assertions.assertEquals("00401", x12Document.getISAEnvelope().getInterchangeControlVersionNumber());
    }

    @Test
    public void verifyISAInterchangeControlNumberSet() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );

        Assertions.assertEquals("123456789", x12Document.getISAEnvelope().getInterchangeControlNumber());
    }

    @Test
    public void verifyISAAcknowledgmentRequestedSet() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );

        Assertions.assertEquals("0", x12Document.getISAEnvelope().getAcknowledgmentRequested());
    }

    @Test
    public void verifyISAUsageSet() throws IOException, ParsingException, URISyntaxException {
        X12Document x12Document = new BufferedX12Parser().parse(
            Files.newInputStream(Path.of(ClassLoader.getSystemClassLoader().getResource("x12\\850\\Basic850.x12").toURI()))
        );

        Assertions.assertEquals("T", x12Document.getISAEnvelope().getUsageIndicator());
    }
}
