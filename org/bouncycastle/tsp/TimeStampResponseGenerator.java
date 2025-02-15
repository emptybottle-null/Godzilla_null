/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.tsp;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.cmp.PKIFreeText;
import org.bouncycastle.asn1.cmp.PKIStatusInfo;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.tsp.TimeStampResp;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.tsp.TSPValidationException;
import org.bouncycastle.tsp.TimeStampRequest;
import org.bouncycastle.tsp.TimeStampResponse;
import org.bouncycastle.tsp.TimeStampTokenGenerator;

public class TimeStampResponseGenerator {
    int status;
    ASN1EncodableVector statusStrings;
    int failInfo;
    private TimeStampTokenGenerator tokenGenerator;
    private Set acceptedAlgorithms;
    private Set acceptedPolicies;
    private Set acceptedExtensions;

    public TimeStampResponseGenerator(TimeStampTokenGenerator timeStampTokenGenerator, Set set) {
        this(timeStampTokenGenerator, set, null, null);
    }

    public TimeStampResponseGenerator(TimeStampTokenGenerator timeStampTokenGenerator, Set set, Set set2) {
        this(timeStampTokenGenerator, set, set2, null);
    }

    public TimeStampResponseGenerator(TimeStampTokenGenerator timeStampTokenGenerator, Set set, Set set2, Set set3) {
        this.tokenGenerator = timeStampTokenGenerator;
        this.acceptedAlgorithms = this.convert(set);
        this.acceptedPolicies = this.convert(set2);
        this.acceptedExtensions = this.convert(set3);
        this.statusStrings = new ASN1EncodableVector();
    }

    private void addStatusString(String string) {
        this.statusStrings.add(new DERUTF8String(string));
    }

    private void setFailInfoField(int n) {
        this.failInfo |= n;
    }

    private PKIStatusInfo getPKIStatusInfo() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.status));
        if (this.statusStrings.size() > 0) {
            aSN1EncodableVector.add(PKIFreeText.getInstance(new DERSequence(this.statusStrings)));
        }
        if (this.failInfo != 0) {
            FailInfo failInfo = new FailInfo(this.failInfo);
            aSN1EncodableVector.add(failInfo);
        }
        return PKIStatusInfo.getInstance(new DERSequence(aSN1EncodableVector));
    }

    public TimeStampResponse generate(TimeStampRequest timeStampRequest, BigInteger bigInteger, Date date) throws TSPException {
        try {
            return this.generateGrantedResponse(timeStampRequest, bigInteger, date, "Operation Okay");
        } catch (Exception exception) {
            return this.generateRejectedResponse(exception);
        }
    }

    public TimeStampResponse generateGrantedResponse(TimeStampRequest timeStampRequest, BigInteger bigInteger, Date date) throws TSPException {
        return this.generateGrantedResponse(timeStampRequest, bigInteger, date, null);
    }

    public TimeStampResponse generateGrantedResponse(TimeStampRequest timeStampRequest, BigInteger bigInteger, Date date, String string) throws TSPException {
        return this.generateGrantedResponse(timeStampRequest, bigInteger, date, string, null);
    }

    public TimeStampResponse generateGrantedResponse(TimeStampRequest timeStampRequest, BigInteger bigInteger, Date date, String string, Extensions extensions) throws TSPException {
        ContentInfo contentInfo;
        if (date == null) {
            throw new TSPValidationException("The time source is not available.", 512);
        }
        timeStampRequest.validate(this.acceptedAlgorithms, this.acceptedPolicies, this.acceptedExtensions);
        this.status = 0;
        this.statusStrings = new ASN1EncodableVector();
        if (string != null) {
            this.addStatusString(string);
        }
        PKIStatusInfo pKIStatusInfo = this.getPKIStatusInfo();
        try {
            contentInfo = this.tokenGenerator.generate(timeStampRequest, bigInteger, date, extensions).toCMSSignedData().toASN1Structure();
        } catch (TSPException tSPException) {
            throw tSPException;
        } catch (Exception exception) {
            throw new TSPException("Timestamp token received cannot be converted to ContentInfo", exception);
        }
        try {
            return new TimeStampResponse(new DLSequence(new ASN1Encodable[]{pKIStatusInfo.toASN1Primitive(), contentInfo.toASN1Primitive()}));
        } catch (IOException iOException) {
            throw new TSPException("created badly formatted response!");
        }
    }

    public TimeStampResponse generateRejectedResponse(Exception exception) throws TSPException {
        if (exception instanceof TSPValidationException) {
            return this.generateFailResponse(2, ((TSPValidationException)exception).getFailureCode(), exception.getMessage());
        }
        return this.generateFailResponse(2, 0x40000000, exception.getMessage());
    }

    public TimeStampResponse generateFailResponse(int n, int n2, String string) throws TSPException {
        this.status = n;
        this.statusStrings = new ASN1EncodableVector();
        this.setFailInfoField(n2);
        if (string != null) {
            this.addStatusString(string);
        }
        PKIStatusInfo pKIStatusInfo = this.getPKIStatusInfo();
        TimeStampResp timeStampResp = new TimeStampResp(pKIStatusInfo, null);
        try {
            return new TimeStampResponse(timeStampResp);
        } catch (IOException iOException) {
            throw new TSPException("created badly formatted response!");
        }
    }

    private Set convert(Set set) {
        if (set == null) {
            return set;
        }
        HashSet<ASN1ObjectIdentifier> hashSet = new HashSet<ASN1ObjectIdentifier>(set.size());
        for (Object e : set) {
            if (e instanceof String) {
                hashSet.add(new ASN1ObjectIdentifier((String)e));
                continue;
            }
            hashSet.add((ASN1ObjectIdentifier)e);
        }
        return hashSet;
    }

    class FailInfo
    extends DERBitString {
        FailInfo(int n) {
            super(FailInfo.getBytes(n), FailInfo.getPadBits(n));
        }
    }
}

