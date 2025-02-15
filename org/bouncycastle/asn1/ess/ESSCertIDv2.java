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
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.IssuerSerial;
import org.bouncycastle.util.Arrays;

public class ESSCertIDv2
extends ASN1Object {
    private AlgorithmIdentifier hashAlgorithm;
    private byte[] certHash;
    private IssuerSerial issuerSerial;
    private static final AlgorithmIdentifier DEFAULT_ALG_ID = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256);

    public static ESSCertIDv2 getInstance(Object object) {
        if (object instanceof ESSCertIDv2) {
            return (ESSCertIDv2)object;
        }
        if (object != null) {
            return new ESSCertIDv2(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private ESSCertIDv2(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() > 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        int n = 0;
        this.hashAlgorithm = aSN1Sequence.getObjectAt(0) instanceof ASN1OctetString ? DEFAULT_ALG_ID : AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(n++).toASN1Primitive());
        this.certHash = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(n++).toASN1Primitive()).getOctets();
        if (aSN1Sequence.size() > n) {
            this.issuerSerial = IssuerSerial.getInstance(aSN1Sequence.getObjectAt(n));
        }
    }

    public ESSCertIDv2(byte[] byArray) {
        this(null, byArray, null);
    }

    public ESSCertIDv2(AlgorithmIdentifier algorithmIdentifier, byte[] byArray) {
        this(algorithmIdentifier, byArray, null);
    }

    public ESSCertIDv2(byte[] byArray, IssuerSerial issuerSerial) {
        this(null, byArray, issuerSerial);
    }

    public ESSCertIDv2(AlgorithmIdentifier algorithmIdentifier, byte[] byArray, IssuerSerial issuerSerial) {
        this.hashAlgorithm = algorithmIdentifier == null ? DEFAULT_ALG_ID : algorithmIdentifier;
        this.certHash = Arrays.clone(byArray);
        this.issuerSerial = issuerSerial;
    }

    public AlgorithmIdentifier getHashAlgorithm() {
        return this.hashAlgorithm;
    }

    public byte[] getCertHash() {
        return Arrays.clone(this.certHash);
    }

    public IssuerSerial getIssuerSerial() {
        return this.issuerSerial;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (!this.hashAlgorithm.equals(DEFAULT_ALG_ID)) {
            aSN1EncodableVector.add(this.hashAlgorithm);
        }
        aSN1EncodableVector.add(new DEROctetString(this.certHash).toASN1Primitive());
        if (this.issuerSerial != null) {
            aSN1EncodableVector.add(this.issuerSerial);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

