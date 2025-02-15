/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.ReasonFlags;
import org.bouncycastle.util.Strings;

public class DistributionPoint
extends ASN1Object {
    DistributionPointName distributionPoint;
    ReasonFlags reasons;
    GeneralNames cRLIssuer;

    public static DistributionPoint getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return DistributionPoint.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static DistributionPoint getInstance(Object object) {
        if (object == null || object instanceof DistributionPoint) {
            return (DistributionPoint)object;
        }
        if (object instanceof ASN1Sequence) {
            return new DistributionPoint((ASN1Sequence)object);
        }
        throw new IllegalArgumentException("Invalid DistributionPoint: " + object.getClass().getName());
    }

    public DistributionPoint(ASN1Sequence aSN1Sequence) {
        block5: for (int i = 0; i != aSN1Sequence.size(); ++i) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(i));
            switch (aSN1TaggedObject.getTagNo()) {
                case 0: {
                    this.distributionPoint = DistributionPointName.getInstance(aSN1TaggedObject, true);
                    continue block5;
                }
                case 1: {
                    this.reasons = new ReasonFlags(DERBitString.getInstance(aSN1TaggedObject, false));
                    continue block5;
                }
                case 2: {
                    this.cRLIssuer = GeneralNames.getInstance(aSN1TaggedObject, false);
                    continue block5;
                }
                default: {
                    throw new IllegalArgumentException("Unknown tag encountered in structure: " + aSN1TaggedObject.getTagNo());
                }
            }
        }
    }

    public DistributionPoint(DistributionPointName distributionPointName, ReasonFlags reasonFlags, GeneralNames generalNames) {
        this.distributionPoint = distributionPointName;
        this.reasons = reasonFlags;
        this.cRLIssuer = generalNames;
    }

    public DistributionPointName getDistributionPoint() {
        return this.distributionPoint;
    }

    public ReasonFlags getReasons() {
        return this.reasons;
    }

    public GeneralNames getCRLIssuer() {
        return this.cRLIssuer;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.distributionPoint != null) {
            aSN1EncodableVector.add(new DERTaggedObject(0, this.distributionPoint));
        }
        if (this.reasons != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.reasons));
        }
        if (this.cRLIssuer != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 2, this.cRLIssuer));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        String string = Strings.lineSeparator();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DistributionPoint: [");
        stringBuffer.append(string);
        if (this.distributionPoint != null) {
            this.appendObject(stringBuffer, string, "distributionPoint", this.distributionPoint.toString());
        }
        if (this.reasons != null) {
            this.appendObject(stringBuffer, string, "reasons", this.reasons.toString());
        }
        if (this.cRLIssuer != null) {
            this.appendObject(stringBuffer, string, "cRLIssuer", this.cRLIssuer.toString());
        }
        stringBuffer.append("]");
        stringBuffer.append(string);
        return stringBuffer.toString();
    }

    private void appendObject(StringBuffer stringBuffer, String string, String string2, String string3) {
        String string4 = "    ";
        stringBuffer.append(string4);
        stringBuffer.append(string2);
        stringBuffer.append(":");
        stringBuffer.append(string);
        stringBuffer.append(string4);
        stringBuffer.append(string4);
        stringBuffer.append(string3);
        stringBuffer.append(string);
    }
}

