/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.pkcs;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;

public class ContentInfo
extends ASN1Object
implements PKCSObjectIdentifiers {
    private ASN1ObjectIdentifier contentType;
    private ASN1Encodable content;
    private boolean isBer = true;

    public static ContentInfo getInstance(Object object) {
        if (object instanceof ContentInfo) {
            return (ContentInfo)object;
        }
        if (object != null) {
            return new ContentInfo(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private ContentInfo(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.contentType = (ASN1ObjectIdentifier)enumeration.nextElement();
        if (enumeration.hasMoreElements()) {
            this.content = ((ASN1TaggedObject)enumeration.nextElement()).getObject();
        }
        this.isBer = aSN1Sequence instanceof BERSequence;
    }

    public ContentInfo(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.contentType = aSN1ObjectIdentifier;
        this.content = aSN1Encodable;
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.contentType;
    }

    public ASN1Encodable getContent() {
        return this.content;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.contentType);
        if (this.content != null) {
            aSN1EncodableVector.add(new BERTaggedObject(true, 0, this.content));
        }
        if (this.isBer) {
            return new BERSequence(aSN1EncodableVector);
        }
        return new DLSequence(aSN1EncodableVector);
    }
}

