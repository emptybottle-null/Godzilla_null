/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.tsp.cms;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.Evidence;
import org.bouncycastle.asn1.cms.TimeStampAndCRL;
import org.bouncycastle.asn1.cms.TimeStampedData;
import org.bouncycastle.asn1.cms.TimeStampedDataParser;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.tsp.TimeStampTokenInfo;
import org.bouncycastle.tsp.cms.ImprintDigestInvalidException;
import org.bouncycastle.tsp.cms.MetaDataUtil;
import org.bouncycastle.util.Arrays;

class TimeStampDataUtil {
    private final TimeStampAndCRL[] timeStamps;
    private final MetaDataUtil metaDataUtil;

    TimeStampDataUtil(TimeStampedData timeStampedData) {
        this.metaDataUtil = new MetaDataUtil(timeStampedData.getMetaData());
        Evidence evidence = timeStampedData.getTemporalEvidence();
        this.timeStamps = evidence.getTstEvidence().toTimeStampAndCRLArray();
    }

    TimeStampDataUtil(TimeStampedDataParser timeStampedDataParser) throws IOException {
        this.metaDataUtil = new MetaDataUtil(timeStampedDataParser.getMetaData());
        Evidence evidence = timeStampedDataParser.getTemporalEvidence();
        this.timeStamps = evidence.getTstEvidence().toTimeStampAndCRLArray();
    }

    TimeStampToken getTimeStampToken(TimeStampAndCRL timeStampAndCRL) throws CMSException {
        ContentInfo contentInfo = timeStampAndCRL.getTimeStampToken();
        try {
            TimeStampToken timeStampToken = new TimeStampToken(contentInfo);
            return timeStampToken;
        } catch (IOException iOException) {
            throw new CMSException("unable to parse token data: " + iOException.getMessage(), iOException);
        } catch (TSPException tSPException) {
            if (tSPException.getCause() instanceof CMSException) {
                throw (CMSException)tSPException.getCause();
            }
            throw new CMSException("token data invalid: " + tSPException.getMessage(), tSPException);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new CMSException("token data invalid: " + illegalArgumentException.getMessage(), illegalArgumentException);
        }
    }

    void initialiseMessageImprintDigestCalculator(DigestCalculator digestCalculator) throws CMSException {
        this.metaDataUtil.initialiseMessageImprintDigestCalculator(digestCalculator);
    }

    DigestCalculator getMessageImprintDigestCalculator(DigestCalculatorProvider digestCalculatorProvider) throws OperatorCreationException {
        try {
            TimeStampToken timeStampToken = this.getTimeStampToken(this.timeStamps[0]);
            TimeStampTokenInfo timeStampTokenInfo = timeStampToken.getTimeStampInfo();
            ASN1ObjectIdentifier aSN1ObjectIdentifier = timeStampTokenInfo.getMessageImprintAlgOID();
            DigestCalculator digestCalculator = digestCalculatorProvider.get(new AlgorithmIdentifier(aSN1ObjectIdentifier));
            this.initialiseMessageImprintDigestCalculator(digestCalculator);
            return digestCalculator;
        } catch (CMSException cMSException) {
            throw new OperatorCreationException("unable to extract algorithm ID: " + cMSException.getMessage(), cMSException);
        }
    }

    TimeStampToken[] getTimeStampTokens() throws CMSException {
        TimeStampToken[] timeStampTokenArray = new TimeStampToken[this.timeStamps.length];
        for (int i = 0; i < this.timeStamps.length; ++i) {
            timeStampTokenArray[i] = this.getTimeStampToken(this.timeStamps[i]);
        }
        return timeStampTokenArray;
    }

    TimeStampAndCRL[] getTimeStamps() {
        return this.timeStamps;
    }

    byte[] calculateNextHash(DigestCalculator digestCalculator) throws CMSException {
        TimeStampAndCRL timeStampAndCRL = this.timeStamps[this.timeStamps.length - 1];
        OutputStream outputStream = digestCalculator.getOutputStream();
        try {
            outputStream.write(timeStampAndCRL.getEncoded("DER"));
            outputStream.close();
            return digestCalculator.getDigest();
        } catch (IOException iOException) {
            throw new CMSException("exception calculating hash: " + iOException.getMessage(), iOException);
        }
    }

    void validate(DigestCalculatorProvider digestCalculatorProvider, byte[] byArray) throws ImprintDigestInvalidException, CMSException {
        byte[] byArray2 = byArray;
        for (int i = 0; i < this.timeStamps.length; ++i) {
            try {
                TimeStampToken timeStampToken = this.getTimeStampToken(this.timeStamps[i]);
                if (i > 0) {
                    TimeStampTokenInfo timeStampTokenInfo = timeStampToken.getTimeStampInfo();
                    DigestCalculator digestCalculator = digestCalculatorProvider.get(timeStampTokenInfo.getHashAlgorithm());
                    digestCalculator.getOutputStream().write(this.timeStamps[i - 1].getEncoded("DER"));
                    byArray2 = digestCalculator.getDigest();
                }
                this.compareDigest(timeStampToken, byArray2);
                continue;
            } catch (IOException iOException) {
                throw new CMSException("exception calculating hash: " + iOException.getMessage(), iOException);
            } catch (OperatorCreationException operatorCreationException) {
                throw new CMSException("cannot create digest: " + operatorCreationException.getMessage(), operatorCreationException);
            }
        }
    }

    void validate(DigestCalculatorProvider digestCalculatorProvider, byte[] byArray, TimeStampToken timeStampToken) throws ImprintDigestInvalidException, CMSException {
        byte[] byArray2;
        byte[] byArray3 = byArray;
        try {
            byArray2 = timeStampToken.getEncoded();
        } catch (IOException iOException) {
            throw new CMSException("exception encoding timeStampToken: " + iOException.getMessage(), iOException);
        }
        for (int i = 0; i < this.timeStamps.length; ++i) {
            try {
                TimeStampToken timeStampToken2 = this.getTimeStampToken(this.timeStamps[i]);
                if (i > 0) {
                    TimeStampTokenInfo timeStampTokenInfo = timeStampToken2.getTimeStampInfo();
                    DigestCalculator digestCalculator = digestCalculatorProvider.get(timeStampTokenInfo.getHashAlgorithm());
                    digestCalculator.getOutputStream().write(this.timeStamps[i - 1].getEncoded("DER"));
                    byArray3 = digestCalculator.getDigest();
                }
                this.compareDigest(timeStampToken2, byArray3);
                if (!Arrays.areEqual(timeStampToken2.getEncoded(), byArray2)) continue;
                return;
            } catch (IOException iOException) {
                throw new CMSException("exception calculating hash: " + iOException.getMessage(), iOException);
            } catch (OperatorCreationException operatorCreationException) {
                throw new CMSException("cannot create digest: " + operatorCreationException.getMessage(), operatorCreationException);
            }
        }
        throw new ImprintDigestInvalidException("passed in token not associated with timestamps present", timeStampToken);
    }

    private void compareDigest(TimeStampToken timeStampToken, byte[] byArray) throws ImprintDigestInvalidException {
        TimeStampTokenInfo timeStampTokenInfo = timeStampToken.getTimeStampInfo();
        byte[] byArray2 = timeStampTokenInfo.getMessageImprintDigest();
        if (!Arrays.areEqual(byArray, byArray2)) {
            throw new ImprintDigestInvalidException("hash calculated is different from MessageImprintDigest found in TimeStampToken", timeStampToken);
        }
    }

    String getFileName() {
        return this.metaDataUtil.getFileName();
    }

    String getMediaType() {
        return this.metaDataUtil.getMediaType();
    }

    AttributeTable getOtherMetaData() {
        return new AttributeTable(this.metaDataUtil.getOtherMetaData());
    }
}

