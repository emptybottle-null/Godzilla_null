/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.dvcs;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.dvcs.TargetEtcChain;
import org.bouncycastle.asn1.x509.DigestInfo;

public class Data
extends ASN1Object
implements ASN1Choice {
    private ASN1OctetString message;
    private DigestInfo messageImprint;
    private ASN1Sequence certs;

    public Data(byte[] byArray) {
        this.message = new DEROctetString(byArray);
    }

    public Data(ASN1OctetString aSN1OctetString) {
        this.message = aSN1OctetString;
    }

    public Data(DigestInfo digestInfo) {
        this.messageImprint = digestInfo;
    }

    public Data(TargetEtcChain targetEtcChain) {
        this.certs = new DERSequence(targetEtcChain);
    }

    public Data(TargetEtcChain[] targetEtcChainArray) {
        this.certs = new DERSequence(targetEtcChainArray);
    }

    private Data(ASN1Sequence aSN1Sequence) {
        this.certs = aSN1Sequence;
    }

    public static Data getInstance(Object object) {
        if (object instanceof Data) {
            return (Data)object;
        }
        if (object instanceof ASN1OctetString) {
            return new Data((ASN1OctetString)object);
        }
        if (object instanceof ASN1Sequence) {
            return new Data(DigestInfo.getInstance(object));
        }
        if (object instanceof ASN1TaggedObject) {
            return new Data(ASN1Sequence.getInstance((ASN1TaggedObject)object, false));
        }
        throw new IllegalArgumentException("Unknown object submitted to getInstance: " + object.getClass().getName());
    }

    public static Data getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return Data.getInstance(aSN1TaggedObject.getObject());
    }

    public ASN1Primitive toASN1Primitive() {
        if (this.message != null) {
            return this.message.toASN1Primitive();
        }
        if (this.messageImprint != null) {
            return this.messageImprint.toASN1Primitive();
        }
        return new DERTaggedObject(false, 0, this.certs);
    }

    public String toString() {
        if (this.message != null) {
            return "Data {\n" + this.message + "}\n";
        }
        if (this.messageImprint != null) {
            return "Data {\n" + this.messageImprint + "}\n";
        }
        return "Data {\n" + this.certs + "}\n";
    }

    public ASN1OctetString getMessage() {
        return this.message;
    }

    public DigestInfo getMessageImprint() {
        return this.messageImprint;
    }

    public TargetEtcChain[] getCerts() {
        if (this.certs == null) {
            return null;
        }
        TargetEtcChain[] targetEtcChainArray = new TargetEtcChain[this.certs.size()];
        for (int i = 0; i != targetEtcChainArray.length; ++i) {
            targetEtcChainArray[i] = TargetEtcChain.getInstance(this.certs.getObjectAt(i));
        }
        return targetEtcChainArray;
    }
}

