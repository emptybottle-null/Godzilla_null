/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.tsp.cms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfoParser;
import org.bouncycastle.asn1.cms.TimeStampedDataParser;
import org.bouncycastle.cms.CMSContentInfoParser;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.tsp.cms.ImprintDigestInvalidException;
import org.bouncycastle.tsp.cms.TimeStampDataUtil;
import org.bouncycastle.util.io.Streams;

public class CMSTimeStampedDataParser
extends CMSContentInfoParser {
    private TimeStampedDataParser timeStampedData;
    private TimeStampDataUtil util;

    public CMSTimeStampedDataParser(InputStream inputStream) throws CMSException {
        super(inputStream);
        this.initialize(this._contentInfo);
    }

    public CMSTimeStampedDataParser(byte[] byArray) throws CMSException {
        this(new ByteArrayInputStream(byArray));
    }

    private void initialize(ContentInfoParser contentInfoParser) throws CMSException {
        try {
            if (!CMSObjectIdentifiers.timestampedData.equals(contentInfoParser.getContentType())) {
                throw new IllegalArgumentException("Malformed content - type must be " + CMSObjectIdentifiers.timestampedData.getId());
            }
            this.timeStampedData = TimeStampedDataParser.getInstance(contentInfoParser.getContent(16));
        } catch (IOException iOException) {
            throw new CMSException("parsing exception: " + iOException.getMessage(), iOException);
        }
    }

    public byte[] calculateNextHash(DigestCalculator digestCalculator) throws CMSException {
        return this.util.calculateNextHash(digestCalculator);
    }

    public InputStream getContent() {
        if (this.timeStampedData.getContent() != null) {
            return this.timeStampedData.getContent().getOctetStream();
        }
        return null;
    }

    public URI getDataUri() throws URISyntaxException {
        DERIA5String dERIA5String = this.timeStampedData.getDataUri();
        if (dERIA5String != null) {
            return new URI(dERIA5String.getString());
        }
        return null;
    }

    public String getFileName() {
        return this.util.getFileName();
    }

    public String getMediaType() {
        return this.util.getMediaType();
    }

    public AttributeTable getOtherMetaData() {
        return this.util.getOtherMetaData();
    }

    public void initialiseMessageImprintDigestCalculator(DigestCalculator digestCalculator) throws CMSException {
        this.util.initialiseMessageImprintDigestCalculator(digestCalculator);
    }

    public DigestCalculator getMessageImprintDigestCalculator(DigestCalculatorProvider digestCalculatorProvider) throws OperatorCreationException {
        try {
            this.parseTimeStamps();
        } catch (CMSException cMSException) {
            throw new OperatorCreationException("unable to extract algorithm ID: " + cMSException.getMessage(), cMSException);
        }
        return this.util.getMessageImprintDigestCalculator(digestCalculatorProvider);
    }

    public TimeStampToken[] getTimeStampTokens() throws CMSException {
        this.parseTimeStamps();
        return this.util.getTimeStampTokens();
    }

    public void validate(DigestCalculatorProvider digestCalculatorProvider, byte[] byArray) throws ImprintDigestInvalidException, CMSException {
        this.parseTimeStamps();
        this.util.validate(digestCalculatorProvider, byArray);
    }

    public void validate(DigestCalculatorProvider digestCalculatorProvider, byte[] byArray, TimeStampToken timeStampToken) throws ImprintDigestInvalidException, CMSException {
        this.parseTimeStamps();
        this.util.validate(digestCalculatorProvider, byArray, timeStampToken);
    }

    private void parseTimeStamps() throws CMSException {
        try {
            if (this.util == null) {
                InputStream inputStream = this.getContent();
                if (inputStream != null) {
                    Streams.drain(inputStream);
                }
                this.util = new TimeStampDataUtil(this.timeStampedData);
            }
        } catch (IOException iOException) {
            throw new CMSException("unable to parse evidence block: " + iOException.getMessage(), iOException);
        }
    }
}

