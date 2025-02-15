/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.ess;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.IssuerSerial;

public class ESSCertID
extends ASN1Object {
    private ASN1OctetString certHash;
    private IssuerSerial issuerSerial;

    public static ESSCertID getInstance(Object object) {
        if (object instanceof ESSCertID) {
            return (ESSCertID)object;
        }
        if (object != null) {
            return new ESSCertID(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private ESSCertID(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 1 || aSN1Sequence.size() > 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        this.certHash = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() > 1) {
            this.issuerSerial = IssuerSerial.getInstance(aSN1Sequence.getObjectAt(1));
        }
    }

    public ESSCertID(byte[] byArray) {
        this.certHash = new DEROctetString(byArray);
    }

    public ESSCertID(byte[] byArray, IssuerSerial issuerSerial) {
        this.certHash = new DEROctetString(byArray);
        this.issuerSerial = issuerSerial;
    }

    public byte[] getCertHash() {
        return this.certHash.getOctets();
    }

    public IssuerSerial getIssuerSerial() {
        return this.issuerSerial;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.certHash);
        if (this.issuerSerial != null) {
            aSN1EncodableVector.add(this.issuerSerial);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

