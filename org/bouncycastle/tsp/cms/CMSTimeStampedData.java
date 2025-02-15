/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.tsp.cms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.Evidence;
import org.bouncycastle.asn1.cms.TimeStampAndCRL;
import org.bouncycastle.asn1.cms.TimeStampTokenEvidence;
import org.bouncycastle.asn1.cms.TimeStampedData;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.tsp.cms.ImprintDigestInvalidException;
import org.bouncycastle.tsp.cms.TimeStampDataUtil;

public class CMSTimeStampedData {
    private TimeStampedData timeStampedData;
    private ContentInfo contentInfo;
    private TimeStampDataUtil util;

    public CMSTimeStampedData(ContentInfo contentInfo) {
        this.initialize(contentInfo);
    }

    public CMSTimeStampedData(InputStream inputStream) throws IOException {
        try {
            this.initialize(ContentInfo.getInstance(new ASN1InputStream(inputStream).readObject()));
        } catch (ClassCastException classCastException) {
            throw new IOException("Malformed content: " + classCastException);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new IOException("Malformed content: " + illegalArgumentException);
        }
    }

    public CMSTimeStampedData(byte[] byArray) throws IOException {
        this(new ByteArrayInputStream(byArray));
    }

    private void initialize(ContentInfo contentInfo) {
        this.contentInfo = contentInfo;
        if (!CMSObjectIdentifiers.timestampedData.equals(contentInfo.getContentType())) {
            throw new IllegalArgumentException("Malformed content - type must be " + CMSObjectIdentifiers.timestampedData.getId());
        }
        this.timeStampedData = TimeStampedData.getInstance(contentInfo.getContent());
        this.util = new TimeStampDataUtil(this.timeStampedData);
    }

    public byte[] calculateNextHash(DigestCalculator digestCalculator) throws CMSException {
        return this.util.calculateNextHash(digestCalculator);
    }

    public CMSTimeStampedData addTimeStamp(TimeStampToken timeStampToken) throws CMSException {
        TimeStampAndCRL[] timeStampAndCRLArray = this.util.getTimeStamps();
        TimeStampAndCRL[] timeStampAndCRLArray2 = new TimeStampAndCRL[timeStampAndCRLArray.length + 1];
        System.arraycopy(timeStampAndCRLArray, 0, timeStampAndCRLArray2, 0, timeStampAndCRLArray.length);
        timeStampAndCRLArray2[timeStampAndCRLArray.length] = new TimeStampAndCRL(timeStampToken.toCMSSignedData().toASN1Structure());
        return new CMSTimeStampedData(new ContentInfo(CMSObjectIdentifiers.timestampedData, new TimeStampedData(this.timeStampedData.getDataUri(), this.timeStampedData.getMetaData(), this.timeStampedData.getContent(), new Evidence(new TimeStampTokenEvidence(timeStampAndCRLArray2)))));
    }

    public byte[] getContent() {
        if (this.timeStampedData.getContent() != null) {
            return this.timeStampedData.getContent().getOctets();
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

    public TimeStampToken[] getTimeStampTokens() throws CMSException {
        return this.util.getTimeStampTokens();
    }

    public void initialiseMessageImprintDigestCalculator(DigestCalculator digestCalculator) throws CMSException {
        this.util.initialiseMessageImprintDigestCalculator(digestCalculator);
    }

    public DigestCalculator getMessageImprintDigestCalculator(DigestCalculatorProvider digestCalculatorProvider) throws OperatorCreationException {
        return this.util.getMessageImprintDigestCalculator(digestCalculatorProvider);
    }

    public void validate(DigestCalculatorProvider digestCalculatorProvider, byte[] byArray) throws ImprintDigestInvalidException, CMSException {
        this.util.validate(digestCalculatorProvider, byArray);
    }

    public void validate(DigestCalculatorProvider digestCalculatorProvider, byte[] byArray, TimeStampToken timeStampToken) throws ImprintDigestInvalidException, CMSException {
        this.util.validate(digestCalculatorProvider, byArray, timeStampToken);
    }

    public byte[] getEncoded() throws IOException {
        return this.contentInfo.getEncoded();
    }
}

