/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;

public class IssuerSerial
extends ASN1Object {
    GeneralNames issuer;
    ASN1Integer serial;
    DERBitString issuerUID;

    public static IssuerSerial getInstance(Object object) {
        if (object instanceof IssuerSerial) {
            return (IssuerSerial)object;
        }
        if (object != null) {
            return new IssuerSerial(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public static IssuerSerial getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return IssuerSerial.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    private IssuerSerial(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 2 && aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        this.issuer = GeneralNames.getInstance(aSN1Sequence.getObjectAt(0));
        this.serial = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() == 3) {
            this.issuerUID = DERBitString.getInstance(aSN1Sequence.getObjectAt(2));
        }
    }

    public IssuerSerial(X500Name x500Name, BigInteger bigInteger) {
        this(new GeneralNames(new GeneralName(x500Name)), new ASN1Integer(bigInteger));
    }

    public IssuerSerial(GeneralNames generalNames, BigInteger bigInteger) {
        this(generalNames, new ASN1Integer(bigInteger));
    }

    public IssuerSerial(GeneralNames generalNames, ASN1Integer aSN1Integer) {
        this.issuer = generalNames;
        this.serial = aSN1Integer;
    }

    public GeneralNames getIssuer() {
        return this.issuer;
    }

    public ASN1Integer getSerial() {
        return this.serial;
    }

    public DERBitString getIssuerUID() {
        return this.issuerUID;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.issuer);
        aSN1EncodableVector.add(this.serial);
        if (this.issuerUID != null) {
            aSN1EncodableVector.add(this.issuerUID);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

