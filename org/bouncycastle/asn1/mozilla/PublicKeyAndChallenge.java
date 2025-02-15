/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.mozilla;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;

public class PublicKeyAndChallenge
extends ASN1Object {
    private ASN1Sequence pkacSeq;
    private SubjectPublicKeyInfo spki;
    private DERIA5String challenge;

    public static PublicKeyAndChallenge getInstance(Object object) {
        if (object instanceof PublicKeyAndChallenge) {
            return (PublicKeyAndChallenge)object;
        }
        if (object != null) {
            return new PublicKeyAndChallenge(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private PublicKeyAndChallenge(ASN1Sequence aSN1Sequence) {
        this.pkacSeq = aSN1Sequence;
        this.spki = SubjectPublicKeyInfo.getInstance(aSN1Sequence.getObjectAt(0));
        this.challenge = DERIA5String.getInstance(aSN1Sequence.getObjectAt(1));
    }

    public ASN1Primitive toASN1Primitive() {
        return this.pkacSeq;
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.spki;
    }

    public DERIA5String getChallenge() {
        return this.challenge;
    }
}

