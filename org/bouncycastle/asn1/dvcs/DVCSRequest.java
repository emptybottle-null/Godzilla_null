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
import org.bouncycastle.asn1.dvcs.DVCSRequestInformation;
import org.bouncycastle.asn1.dvcs.Data;
import org.bouncycastle.asn1.x509.GeneralName;

public class DVCSRequest
extends ASN1Object {
    private DVCSRequestInformation requestInformation;
    private Data data;
    private GeneralName transactionIdentifier;

    public DVCSRequest(DVCSRequestInformation dVCSRequestInformation, Data data) {
        this(dVCSRequestInformation, data, null);
    }

    public DVCSRequest(DVCSRequestInformation dVCSRequestInformation, Data data, GeneralName generalName) {
        this.requestInformation = dVCSRequestInformation;
        this.data = data;
        this.transactionIdentifier = generalName;
    }

    private DVCSRequest(ASN1Sequence aSN1Sequence) {
        this.requestInformation = DVCSRequestInformation.getInstance(aSN1Sequence.getObjectAt(0));
        this.data = Data.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() > 2) {
            this.transactionIdentifier = GeneralName.getInstance(aSN1Sequence.getObjectAt(2));
        }
    }

    public static DVCSRequest getInstance(Object object) {
        if (object instanceof DVCSRequest) {
            return (DVCSRequest)object;
        }
        if (object != null) {
            return new DVCSRequest(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public static DVCSRequest getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return DVCSRequest.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.requestInformation);
        aSN1EncodableVector.add(this.data);
        if (this.transactionIdentifier != null) {
            aSN1EncodableVector.add(this.transactionIdentifier);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        return "DVCSRequest {\nrequestInformation: " + this.requestInformation + "\n" + "data: " + this.data + "\n" + (this.transactionIdentifier != null ? "transactionIdentifier: " + this.transactionIdentifier + "\n" : "") + "}\n";
    }

    public Data getData() {
        return this.data;
    }

    public DVCSRequestInformation getRequestInformation() {
        return this.requestInformation;
    }

    public GeneralName getTransactionIdentifier() {
        return this.transactionIdentifier;
    }
}

