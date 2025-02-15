/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.mozilla;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.mozilla.PublicKeyAndChallenge;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class SignedPublicKeyAndChallenge
extends ASN1Object {
    private final PublicKeyAndChallenge pubKeyAndChal;
    private final ASN1Sequence pkacSeq;

    public static SignedPublicKeyAndChallenge getInstance(Object object) {
        if (object instanceof SignedPublicKeyAndChallenge) {
            return (SignedPublicKeyAndChallenge)object;
        }
        if (object != null) {
            return new SignedPublicKeyAndChallenge(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private SignedPublicKeyAndChallenge(ASN1Sequence aSN1Sequence) {
        this.pkacSeq = aSN1Sequence;
        this.pubKeyAndChal = PublicKeyAndChallenge.getInstance(aSN1Sequence.getObjectAt(0));
    }

    public ASN1Primitive toASN1Primitive() {
        return this.pkacSeq;
    }

    public PublicKeyAndChallenge getPublicKeyAndChallenge() {
        return this.pubKeyAndChal;
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return AlgorithmIdentifier.getInstance(this.pkacSeq.getObjectAt(1));
    }

    public DERBitString getSignature() {
        return DERBitString.getInstance(this.pkacSeq.getObjectAt(2));
    }
}

