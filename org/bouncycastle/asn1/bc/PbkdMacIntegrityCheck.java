/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.bc;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.pkcs.KeyDerivationFunc;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.util.Arrays;

public class PbkdMacIntegrityCheck
extends ASN1Object {
    private final AlgorithmIdentifier macAlgorithm;
    private final KeyDerivationFunc pbkdAlgorithm;
    private final ASN1OctetString mac;

    public PbkdMacIntegrityCheck(AlgorithmIdentifier algorithmIdentifier, KeyDerivationFunc keyDerivationFunc, byte[] byArray) {
        this.macAlgorithm = algorithmIdentifier;
        this.pbkdAlgorithm = keyDerivationFunc;
        this.mac = new DEROctetString(Arrays.clone(byArray));
    }

    private PbkdMacIntegrityCheck(ASN1Sequence aSN1Sequence) {
        this.macAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.pbkdAlgorithm = KeyDerivationFunc.getInstance(aSN1Sequence.getObjectAt(1));
        this.mac = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(2));
    }

    public static PbkdMacIntegrityCheck getInstance(Object object) {
        if (object instanceof PbkdMacIntegrityCheck) {
            return (PbkdMacIntegrityCheck)object;
        }
        if (object != null) {
            return new PbkdMacIntegrityCheck(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public AlgorithmIdentifier getMacAlgorithm() {
        return this.macAlgorithm;
    }

    public KeyDerivationFunc getPbkdAlgorithm() {
        return this.pbkdAlgorithm;
    }

    public byte[] getMac() {
        return Arrays.clone(this.mac.getOctets());
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.macAlgorithm);
        aSN1EncodableVector.add(this.pbkdAlgorithm);
        aSN1EncodableVector.add(this.mac);
        return new DERSequence(aSN1EncodableVector);
    }
}

