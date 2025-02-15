/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.bc;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;

public class SecretKeyData
extends ASN1Object {
    private final ASN1ObjectIdentifier keyAlgorithm;
    private final ASN1OctetString keyBytes;

    public SecretKeyData(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] byArray) {
        this.keyAlgorithm = aSN1ObjectIdentifier;
        this.keyBytes = new DEROctetString(Arrays.clone(byArray));
    }

    private SecretKeyData(ASN1Sequence aSN1Sequence) {
        this.keyAlgorithm = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.keyBytes = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public static SecretKeyData getInstance(Object object) {
        if (object instanceof SecretKeyData) {
            return (SecretKeyData)object;
        }
        if (object != null) {
            return new SecretKeyData(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public byte[] getKeyBytes() {
        return Arrays.clone(this.keyBytes.getOctets());
    }

    public ASN1ObjectIdentifier getKeyAlgorithm() {
        return this.keyAlgorithm;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.keyAlgorithm);
        aSN1EncodableVector.add(this.keyBytes);
        return new DERSequence(aSN1EncodableVector);
    }
}

