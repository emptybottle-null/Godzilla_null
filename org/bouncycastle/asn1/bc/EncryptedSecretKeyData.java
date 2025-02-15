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
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.util.Arrays;

public class EncryptedSecretKeyData
extends ASN1Object {
    private final AlgorithmIdentifier keyEncryptionAlgorithm;
    private final ASN1OctetString encryptedKeyData;

    public EncryptedSecretKeyData(AlgorithmIdentifier algorithmIdentifier, byte[] byArray) {
        this.keyEncryptionAlgorithm = algorithmIdentifier;
        this.encryptedKeyData = new DEROctetString(Arrays.clone(byArray));
    }

    private EncryptedSecretKeyData(ASN1Sequence aSN1Sequence) {
        this.keyEncryptionAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.encryptedKeyData = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public static EncryptedSecretKeyData getInstance(Object object) {
        if (object instanceof EncryptedSecretKeyData) {
            return (EncryptedSecretKeyData)object;
        }
        if (object != null) {
            return new EncryptedSecretKeyData(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public AlgorithmIdentifier getKeyEncryptionAlgorithm() {
        return this.keyEncryptionAlgorithm;
    }

    public byte[] getEncryptedKeyData() {
        return Arrays.clone(this.encryptedKeyData.getOctets());
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.keyEncryptionAlgorithm);
        aSN1EncodableVector.add(this.encryptedKeyData);
        return new DERSequence(aSN1EncodableVector);
    }
}

