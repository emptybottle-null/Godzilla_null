/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.cmp.Challenge;

public class POPODecKeyChallContent
extends ASN1Object {
    private ASN1Sequence content;

    private POPODecKeyChallContent(ASN1Sequence aSN1Sequence) {
        this.content = aSN1Sequence;
    }

    public static POPODecKeyChallContent getInstance(Object object) {
        if (object instanceof POPODecKeyChallContent) {
            return (POPODecKeyChallContent)object;
        }
        if (object != null) {
            return new POPODecKeyChallContent(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public Challenge[] toChallengeArray() {
        Challenge[] challengeArray = new Challenge[this.content.size()];
        for (int i = 0; i != challengeArray.length; ++i) {
            challengeArray[i] = Challenge.getInstance(this.content.getObjectAt(i));
        }
        return challengeArray;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.content;
    }
}

