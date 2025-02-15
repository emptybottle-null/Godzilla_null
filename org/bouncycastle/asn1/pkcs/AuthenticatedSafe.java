/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.pkcs;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.pkcs.ContentInfo;

public class AuthenticatedSafe
extends ASN1Object {
    private ContentInfo[] info;
    private boolean isBer = true;

    private AuthenticatedSafe(ASN1Sequence aSN1Sequence) {
        this.info = new ContentInfo[aSN1Sequence.size()];
        for (int i = 0; i != this.info.length; ++i) {
            this.info[i] = ContentInfo.getInstance(aSN1Sequence.getObjectAt(i));
        }
        this.isBer = aSN1Sequence instanceof BERSequence;
    }

    public static AuthenticatedSafe getInstance(Object object) {
        if (object instanceof AuthenticatedSafe) {
            return (AuthenticatedSafe)object;
        }
        if (object != null) {
            return new AuthenticatedSafe(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public AuthenticatedSafe(ContentInfo[] contentInfoArray) {
        this.info = contentInfoArray;
    }

    public ContentInfo[] getContentInfo() {
        return this.info;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != this.info.length; ++i) {
            aSN1EncodableVector.add(this.info[i]);
        }
        if (this.isBer) {
            return new BERSequence(aSN1EncodableVector);
        }
        return new DLSequence(aSN1EncodableVector);
    }
}

