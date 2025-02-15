/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.dvcs;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cmp.PKIStatusInfo;
import org.bouncycastle.asn1.x509.GeneralName;

public class DVCSErrorNotice
extends ASN1Object {
    private PKIStatusInfo transactionStatus;
    private GeneralName transactionIdentifier;

    public DVCSErrorNotice(PKIStatusInfo pKIStatusInfo) {
        this(pKIStatusInfo, null);
    }

    public DVCSErrorNotice(PKIStatusInfo pKIStatusInfo, GeneralName generalName) {
        this.transactionStatus = pKIStatusInfo;
        this.transactionIdentifier = generalName;
    }

    private DVCSErrorNotice(ASN1Sequence aSN1Sequence) {
        this.transactionStatus = PKIStatusInfo.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() > 1) {
            this.transactionIdentifier = GeneralName.getInstance(aSN1Sequence.getObjectAt(1));
        }
    }

    public static DVCSErrorNotice getInstance(Object object) {
        if (object instanceof DVCSErrorNotice) {
            return (DVCSErrorNotice)object;
        }
        if (object != null) {
            return new DVCSErrorNotice(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public static DVCSErrorNotice getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return DVCSErrorNotice.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.transactionStatus);
        if (this.transactionIdentifier != null) {
            aSN1EncodableVector.add(this.transactionIdentifier);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        return "DVCSErrorNotice {\ntransactionStatus: " + this.transactionStatus + "\n" + (this.transactionIdentifier != null ? "transactionIdentifier: " + this.transactionIdentifier + "\n" : "") + "}\n";
    }

    public PKIStatusInfo getTransactionStatus() {
        return this.transactionStatus;
    }

    public GeneralName getTransactionIdentifier() {
        return this.transactionIdentifier;
    }
}

