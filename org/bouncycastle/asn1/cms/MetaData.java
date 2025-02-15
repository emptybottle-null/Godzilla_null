/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.cms.Attributes;

public class MetaData
extends ASN1Object {
    private ASN1Boolean hashProtected;
    private DERUTF8String fileName;
    private DERIA5String mediaType;
    private Attributes otherMetaData;

    public MetaData(ASN1Boolean aSN1Boolean, DERUTF8String dERUTF8String, DERIA5String dERIA5String, Attributes attributes) {
        this.hashProtected = aSN1Boolean;
        this.fileName = dERUTF8String;
        this.mediaType = dERIA5String;
        this.otherMetaData = attributes;
    }

    private MetaData(ASN1Sequence aSN1Sequence) {
        this.hashProtected = ASN1Boolean.getInstance(aSN1Sequence.getObjectAt(0));
        int n = 1;
        if (n < aSN1Sequence.size() && aSN1Sequence.getObjectAt(n) instanceof DERUTF8String) {
            this.fileName = DERUTF8String.getInstance(aSN1Sequence.getObjectAt(n++));
        }
        if (n < aSN1Sequence.size() && aSN1Sequence.getObjectAt(n) instanceof DERIA5String) {
            this.mediaType = DERIA5String.getInstance(aSN1Sequence.getObjectAt(n++));
        }
        if (n < aSN1Sequence.size()) {
            this.otherMetaData = Attributes.getInstance(aSN1Sequence.getObjectAt(n++));
        }
    }

    public static MetaData getInstance(Object object) {
        if (object instanceof MetaData) {
            return (MetaData)object;
        }
        if (object != null) {
            return new MetaData(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.hashProtected);
        if (this.fileName != null) {
            aSN1EncodableVector.add(this.fileName);
        }
        if (this.mediaType != null) {
            aSN1EncodableVector.add(this.mediaType);
        }
        if (this.otherMetaData != null) {
            aSN1EncodableVector.add(this.otherMetaData);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public boolean isHashProtected() {
        return this.hashProtected.isTrue();
    }

    public DERUTF8String getFileName() {
        return this.fileName;
    }

    public DERIA5String getMediaType() {
        return this.mediaType;
    }

    public Attributes getOtherMetaData() {
        return this.otherMetaData;
    }
}

