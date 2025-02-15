/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.dvcs;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cmp.PKIStatusInfo;
import org.bouncycastle.asn1.dvcs.DVCSRequestInformation;
import org.bouncycastle.asn1.dvcs.DVCSTime;
import org.bouncycastle.asn1.dvcs.TargetEtcChain;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.PolicyInformation;

public class DVCSCertInfo
extends ASN1Object {
    private int version = 1;
    private DVCSRequestInformation dvReqInfo;
    private DigestInfo messageImprint;
    private ASN1Integer serialNumber;
    private DVCSTime responseTime;
    private PKIStatusInfo dvStatus;
    private PolicyInformation policy;
    private ASN1Set reqSignature;
    private ASN1Sequence certs;
    private Extensions extensions;
    private static final int DEFAULT_VERSION = 1;
    private static final int TAG_DV_STATUS = 0;
    private static final int TAG_POLICY = 1;
    private static final int TAG_REQ_SIGNATURE = 2;
    private static final int TAG_CERTS = 3;

    public DVCSCertInfo(DVCSRequestInformation dVCSRequestInformation, DigestInfo digestInfo, ASN1Integer aSN1Integer, DVCSTime dVCSTime) {
        this.dvReqInfo = dVCSRequestInformation;
        this.messageImprint = digestInfo;
        this.serialNumber = aSN1Integer;
        this.responseTime = dVCSTime;
    }

    private DVCSCertInfo(ASN1Sequence aSN1Sequence) {
        ASN1Primitive aSN1Primitive;
        int n = 0;
        ASN1Encodable aSN1Encodable = aSN1Sequence.getObjectAt(n++);
        try {
            aSN1Primitive = ASN1Integer.getInstance(aSN1Encodable);
            this.version = ((ASN1Integer)aSN1Primitive).getValue().intValue();
            aSN1Encodable = aSN1Sequence.getObjectAt(n++);
        } catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        this.dvReqInfo = DVCSRequestInformation.getInstance(aSN1Encodable);
        aSN1Encodable = aSN1Sequence.getObjectAt(n++);
        this.messageImprint = DigestInfo.getInstance(aSN1Encodable);
        aSN1Encodable = aSN1Sequence.getObjectAt(n++);
        this.serialNumber = ASN1Integer.getInstance(aSN1Encodable);
        aSN1Encodable = aSN1Sequence.getObjectAt(n++);
        this.responseTime = DVCSTime.getInstance(aSN1Encodable);
        block10: while (n < aSN1Sequence.size()) {
            if ((aSN1Encodable = aSN1Sequence.getObjectAt(n++)) instanceof ASN1TaggedObject) {
                aSN1Primitive = ASN1TaggedObject.getInstance(aSN1Encodable);
                int n2 = ((ASN1TaggedObject)aSN1Primitive).getTagNo();
                switch (n2) {
                    case 0: {
                        this.dvStatus = PKIStatusInfo.getInstance((ASN1TaggedObject)aSN1Primitive, false);
                        continue block10;
                    }
                    case 1: {
                        this.policy = PolicyInformation.getInstance(ASN1Sequence.getInstance((ASN1TaggedObject)aSN1Primitive, false));
                        continue block10;
                    }
                    case 2: {
                        this.reqSignature = ASN1Set.getInstance((ASN1TaggedObject)aSN1Primitive, false);
                        continue block10;
                    }
                    case 3: {
                        this.certs = ASN1Sequence.getInstance((ASN1TaggedObject)aSN1Primitive, false);
                        continue block10;
                    }
                }
                throw new IllegalArgumentException("Unknown tag encountered: " + n2);
            }
            try {
                this.extensions = Extensions.getInstance(aSN1Encodable);
            } catch (IllegalArgumentException illegalArgumentException) {}
        }
    }

    public static DVCSCertInfo getInstance(Object object) {
        if (object instanceof DVCSCertInfo) {
            return (DVCSCertInfo)object;
        }
        if (object != null) {
            return new DVCSCertInfo(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public static DVCSCertInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return DVCSCertInfo.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.version != 1) {
            aSN1EncodableVector.add(new ASN1Integer(this.version));
        }
        aSN1EncodableVector.add(this.dvReqInfo);
        aSN1EncodableVector.add(this.messageImprint);
        aSN1EncodableVector.add(this.serialNumber);
        aSN1EncodableVector.add(this.responseTime);
        if (this.dvStatus != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.dvStatus));
        }
        if (this.policy != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.policy));
        }
        if (this.reqSignature != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 2, this.reqSignature));
        }
        if (this.certs != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 3, this.certs));
        }
        if (this.extensions != null) {
            aSN1EncodableVector.add(this.extensions);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DVCSCertInfo {\n");
        if (this.version != 1) {
            stringBuffer.append("version: " + this.version + "\n");
        }
        stringBuffer.append("dvReqInfo: " + this.dvReqInfo + "\n");
        stringBuffer.append("messageImprint: " + this.messageImprint + "\n");
        stringBuffer.append("serialNumber: " + this.serialNumber + "\n");
        stringBuffer.append("responseTime: " + this.responseTime + "\n");
        if (this.dvStatus != null) {
            stringBuffer.append("dvStatus: " + this.dvStatus + "\n");
        }
        if (this.policy != null) {
            stringBuffer.append("policy: " + this.policy + "\n");
        }
        if (this.reqSignature != null) {
            stringBuffer.append("reqSignature: " + this.reqSignature + "\n");
        }
        if (this.certs != null) {
            stringBuffer.append("certs: " + this.certs + "\n");
        }
        if (this.extensions != null) {
            stringBuffer.append("extensions: " + this.extensions + "\n");
        }
        stringBuffer.append("}\n");
        return stringBuffer.toString();
    }

    public int getVersion() {
        return this.version;
    }

    private void setVersion(int n) {
        this.version = n;
    }

    public DVCSRequestInformation getDvReqInfo() {
        return this.dvReqInfo;
    }

    private void setDvReqInfo(DVCSRequestInformation dVCSRequestInformation) {
        this.dvReqInfo = dVCSRequestInformation;
    }

    public DigestInfo getMessageImprint() {
        return this.messageImprint;
    }

    private void setMessageImprint(DigestInfo digestInfo) {
        this.messageImprint = digestInfo;
    }

    public ASN1Integer getSerialNumber() {
        return this.serialNumber;
    }

    public DVCSTime getResponseTime() {
        return this.responseTime;
    }

    public PKIStatusInfo getDvStatus() {
        return this.dvStatus;
    }

    public PolicyInformation getPolicy() {
        return this.policy;
    }

    public ASN1Set getReqSignature() {
        return this.reqSignature;
    }

    public TargetEtcChain[] getCerts() {
        if (this.certs != null) {
            return TargetEtcChain.arrayFromSequence(this.certs);
        }
        return null;
    }

    public Extensions getExtensions() {
        return this.extensions;
    }
}

