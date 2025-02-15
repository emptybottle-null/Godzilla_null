/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.asn1;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.util.Arrays;

public class XMSSPrivateKey
extends ASN1Object {
    private final int index;
    private final byte[] secretKeySeed;
    private final byte[] secretKeyPRF;
    private final byte[] publicSeed;
    private final byte[] root;
    private final byte[] bdsState;

    public XMSSPrivateKey(int n, byte[] byArray, byte[] byArray2, byte[] byArray3, byte[] byArray4, byte[] byArray5) {
        this.index = n;
        this.secretKeySeed = Arrays.clone(byArray);
        this.secretKeyPRF = Arrays.clone(byArray2);
        this.publicSeed = Arrays.clone(byArray3);
        this.root = Arrays.clone(byArray4);
        this.bdsState = Arrays.clone(byArray5);
    }

    private XMSSPrivateKey(ASN1Sequence aSN1Sequence) {
        if (!ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).getValue().equals(BigInteger.valueOf(0L))) {
            throw new IllegalArgumentException("unknown version of sequence");
        }
        if (aSN1Sequence.size() != 2 && aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("key sequence wrong size");
        }
        ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
        this.index = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(0)).getValue().intValue();
        this.secretKeySeed = Arrays.clone(DEROctetString.getInstance(aSN1Sequence2.getObjectAt(1)).getOctets());
        this.secretKeyPRF = Arrays.clone(DEROctetString.getInstance(aSN1Sequence2.getObjectAt(2)).getOctets());
        this.publicSeed = Arrays.clone(DEROctetString.getInstance(aSN1Sequence2.getObjectAt(3)).getOctets());
        this.root = Arrays.clone(DEROctetString.getInstance(aSN1Sequence2.getObjectAt(4)).getOctets());
        this.bdsState = (byte[])(aSN1Sequence.size() == 3 ? Arrays.clone(DEROctetString.getInstance(ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(2)), true).getOctets()) : null);
    }

    public static XMSSPrivateKey getInstance(Object object) {
        if (object instanceof XMSSPrivateKey) {
            return (XMSSPrivateKey)object;
        }
        if (object != null) {
            return new XMSSPrivateKey(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public int getIndex() {
        return this.index;
    }

    public byte[] getSecretKeySeed() {
        return Arrays.clone(this.secretKeySeed);
    }

    public byte[] getSecretKeyPRF() {
        return Arrays.clone(this.secretKeyPRF);
    }

    public byte[] getPublicSeed() {
        return Arrays.clone(this.publicSeed);
    }

    public byte[] getRoot() {
        return Arrays.clone(this.root);
    }

    public byte[] getBdsState() {
        return Arrays.clone(this.bdsState);
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(0L));
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        aSN1EncodableVector2.add(new ASN1Integer(this.index));
        aSN1EncodableVector2.add(new DEROctetString(this.secretKeySeed));
        aSN1EncodableVector2.add(new DEROctetString(this.secretKeyPRF));
        aSN1EncodableVector2.add(new DEROctetString(this.publicSeed));
        aSN1EncodableVector2.add(new DEROctetString(this.root));
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        aSN1EncodableVector.add(new DERTaggedObject(true, 0, new DEROctetString(this.bdsState)));
        return new DERSequence(aSN1EncodableVector);
    }
}

