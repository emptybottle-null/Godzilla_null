/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cryptopro;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;

public class Gost2814789KeyWrapParameters
extends ASN1Object {
    private final ASN1ObjectIdentifier encryptionParamSet;
    private final byte[] ukm;

    private Gost2814789KeyWrapParameters(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            this.encryptionParamSet = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
            this.ukm = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets();
        } else if (aSN1Sequence.size() == 1) {
            this.encryptionParamSet = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
            this.ukm = null;
        } else {
            throw new IllegalArgumentException("unknown sequence length: " + aSN1Sequence.size());
        }
    }

    public static Gost2814789KeyWrapParameters getInstance(Object object) {
        if (object instanceof Gost2814789KeyWrapParameters) {
            return (Gost2814789KeyWrapParameters)object;
        }
        if (object != null) {
            return new Gost2814789KeyWrapParameters(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public Gost2814789KeyWrapParameters(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this(aSN1ObjectIdentifier, null);
    }

    public Gost2814789KeyWrapParameters(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] byArray) {
        this.encryptionParamSet = aSN1ObjectIdentifier;
        this.ukm = Arrays.clone(byArray);
    }

    public ASN1ObjectIdentifier getEncryptionParamSet() {
        return this.encryptionParamSet;
    }

    public byte[] getUkm() {
        return this.ukm;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.encryptionParamSet);
        if (this.ukm != null) {
            aSN1EncodableVector.add(new DEROctetString(this.ukm));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

