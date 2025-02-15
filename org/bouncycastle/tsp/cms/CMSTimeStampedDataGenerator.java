/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.tsp.cms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.Evidence;
import org.bouncycastle.asn1.cms.TimeStampAndCRL;
import org.bouncycastle.asn1.cms.TimeStampTokenEvidence;
import org.bouncycastle.asn1.cms.TimeStampedData;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.tsp.cms.CMSTimeStampedData;
import org.bouncycastle.tsp.cms.CMSTimeStampedGenerator;
import org.bouncycastle.util.io.Streams;

public class CMSTimeStampedDataGenerator
extends CMSTimeStampedGenerator {
    public CMSTimeStampedData generate(TimeStampToken timeStampToken) throws CMSException {
        return this.generate(timeStampToken, (InputStream)null);
    }

    public CMSTimeStampedData generate(TimeStampToken timeStampToken, byte[] byArray) throws CMSException {
        return this.generate(timeStampToken, new ByteArrayInputStream(byArray));
    }

    public CMSTimeStampedData generate(TimeStampToken timeStampToken, InputStream inputStream) throws CMSException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (inputStream != null) {
            try {
                Streams.pipeAll(inputStream, byteArrayOutputStream);
            } catch (IOException iOException) {
                throw new CMSException("exception encapsulating content: " + iOException.getMessage(), iOException);
            }
        }
        BEROctetString bEROctetString = null;
        if (byteArrayOutputStream.size() != 0) {
            bEROctetString = new BEROctetString(byteArrayOutputStream.toByteArray());
        }
        TimeStampAndCRL timeStampAndCRL = new TimeStampAndCRL(timeStampToken.toCMSSignedData().toASN1Structure());
        DERIA5String dERIA5String = null;
        if (this.dataUri != null) {
            dERIA5String = new DERIA5String(this.dataUri.toString());
        }
        return new CMSTimeStampedData(new ContentInfo(CMSObjectIdentifiers.timestampedData, new TimeStampedData(dERIA5String, this.metaData, bEROctetString, new Evidence(new TimeStampTokenEvidence(timeStampAndCRL)))));
    }
}

