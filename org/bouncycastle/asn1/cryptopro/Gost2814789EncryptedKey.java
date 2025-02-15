/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cryptopro;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.util.Arrays;

public class Gost2814789EncryptedKey
extends ASN1Object {
    private final byte[] encryptedKey;
    private final byte[] maskKey;
    private final byte[] macKey;

    private Gost2814789EncryptedKey(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            this.encryptedKey = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(0)).getOctets());
            this.macKey = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets());
            this.maskKey = null;
        } else if (aSN1Sequence.size() == 3) {
            this.encryptedKey = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(0)).getOctets());
            this.maskKey = Arrays.clone(ASN1OctetString.getInstance(ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(1)), false).getOctets());
            this.macKey = Arrays.clone(ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(2)).getOctets());
        } else {
            throw new IllegalArgumentException("unknown sequence length: " + aSN1Sequence.size());
        }
    }

    public static Gost2814789EncryptedKey getInstance(Object object) {
        if (object instanceof Gost2814789EncryptedKey) {
            return (Gost2814789EncryptedKey)object;
        }
        if (object != null) {
            return new Gost2814789EncryptedKey(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public Gost2814789EncryptedKey(byte[] byArray, byte[] byArray2) {
        this(byArray, null, byArray2);
    }

    public Gost2814789EncryptedKey(byte[] byArray, byte[] byArray2, byte[] byArray3) {
        this.encryptedKey = Arrays.clone(byArray);
        this.maskKey = Arrays.clone(byArray2);
        this.macKey = Arrays.clone(byArray3);
    }

    public byte[] getEncryptedKey() {
        return this.encryptedKey;
    }

    public byte[] getMaskKey() {
        return this.maskKey;
    }

    public byte[] getMacKey() {
        return this.macKey;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new DEROctetString(this.encryptedKey));
        if (this.maskKey != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, new DEROctetString(this.encryptedKey)));
        }
        aSN1EncodableVector.add(new DEROctetString(this.macKey));
        return new DERSequence(aSN1EncodableVector);
    }
}

