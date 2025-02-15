/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.ess;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.asn1.x509.IssuerSerial;

public class OtherCertID
extends ASN1Object {
    private ASN1Encodable otherCertHash;
    private IssuerSerial issuerSerial;

    public static OtherCertID getInstance(Object object) {
        if (object instanceof OtherCertID) {
            return (OtherCertID)object;
        }
        if (object != null) {
            return new OtherCertID(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private OtherCertID(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 1 || aSN1Sequence.size() > 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        this.otherCertHash = aSN1Sequence.getObjectAt(0).toASN1Primitive() instanceof ASN1OctetString ? ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(0)) : DigestInfo.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() > 1) {
            this.issuerSerial = IssuerSerial.getInstance(aSN1Sequence.getObjectAt(1));
        }
    }

    public OtherCertID(AlgorithmIdentifier algorithmIdentifier, byte[] byArray) {
        this.otherCertHash = new DigestInfo(algorithmIdentifier, byArray);
    }

    public OtherCertID(AlgorithmIdentifier algorithmIdentifier, byte[] byArray, IssuerSerial issuerSerial) {
        this.otherCertHash = new DigestInfo(algorithmIdentifier, byArray);
        this.issuerSerial = issuerSerial;
    }

    public AlgorithmIdentifier getAlgorithmHash() {
        if (this.otherCertHash.toASN1Primitive() instanceof ASN1OctetString) {
            return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1);
        }
        return DigestInfo.getInstance(this.otherCertHash).getAlgorithmId();
    }

    public byte[] getCertHash() {
        if (this.otherCertHash.toASN1Primitive() instanceof ASN1OctetString) {
            return ((ASN1OctetString)this.otherCertHash.toASN1Primitive()).getOctets();
        }
        return DigestInfo.getInstance(this.otherCertHash).getDigest();
    }

    public IssuerSerial getIssuerSerial() {
        return this.issuerSerial;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.otherCertHash);
        if (this.issuerSerial != null) {
            aSN1EncodableVector.add(this.issuerSerial);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

