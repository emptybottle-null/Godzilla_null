/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cms.KEKRecipientInfo;
import org.bouncycastle.asn1.cms.KeyAgreeRecipientInfo;
import org.bouncycastle.asn1.cms.KeyTransRecipientInfo;
import org.bouncycastle.asn1.cms.OtherRecipientInfo;
import org.bouncycastle.asn1.cms.PasswordRecipientInfo;

public class RecipientInfo
extends ASN1Object
implements ASN1Choice {
    ASN1Encodable info;

    public RecipientInfo(KeyTransRecipientInfo keyTransRecipientInfo) {
        this.info = keyTransRecipientInfo;
    }

    public RecipientInfo(KeyAgreeRecipientInfo keyAgreeRecipientInfo) {
        this.info = new DERTaggedObject(false, 1, keyAgreeRecipientInfo);
    }

    public RecipientInfo(KEKRecipientInfo kEKRecipientInfo) {
        this.info = new DERTaggedObject(false, 2, kEKRecipientInfo);
    }

    public RecipientInfo(PasswordRecipientInfo passwordRecipientInfo) {
        this.info = new DERTaggedObject(false, 3, passwordRecipientInfo);
    }

    public RecipientInfo(OtherRecipientInfo otherRecipientInfo) {
        this.info = new DERTaggedObject(false, 4, otherRecipientInfo);
    }

    public RecipientInfo(ASN1Primitive aSN1Primitive) {
        this.info = aSN1Primitive;
    }

    public static RecipientInfo getInstance(Object object) {
        if (object == null || object instanceof RecipientInfo) {
            return (RecipientInfo)object;
        }
        if (object instanceof ASN1Sequence) {
            return new RecipientInfo((ASN1Sequence)object);
        }
        if (object instanceof ASN1TaggedObject) {
            return new RecipientInfo((ASN1TaggedObject)object);
        }
        throw new IllegalArgumentException("unknown object in factory: " + object.getClass().getName());
    }

    public ASN1Integer getVersion() {
        if (this.info instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)this.info;
            switch (aSN1TaggedObject.getTagNo()) {
                case 1: {
                    return KeyAgreeRecipientInfo.getInstance(aSN1TaggedObject, false).getVersion();
                }
                case 2: {
                    return this.getKEKInfo(aSN1TaggedObject).getVersion();
                }
                case 3: {
                    return PasswordRecipientInfo.getInstance(aSN1TaggedObject, false).getVersion();
                }
                case 4: {
                    return new ASN1Integer(0L);
                }
            }
            throw new IllegalStateException("unknown tag");
        }
        return KeyTransRecipientInfo.getInstance(this.info).getVersion();
    }

    public boolean isTagged() {
        return this.info instanceof ASN1TaggedObject;
    }

    public ASN1Encodable getInfo() {
        if (this.info instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)this.info;
            switch (aSN1TaggedObject.getTagNo()) {
                case 1: {
                    return KeyAgreeRecipientInfo.getInstance(aSN1TaggedObject, false);
                }
                case 2: {
                    return this.getKEKInfo(aSN1TaggedObject);
                }
                case 3: {
                    return PasswordRecipientInfo.getInstance(aSN1TaggedObject, false);
                }
                case 4: {
                    return OtherRecipientInfo.getInstance(aSN1TaggedObject, false);
                }
            }
            throw new IllegalStateException("unknown tag");
        }
        return KeyTransRecipientInfo.getInstance(this.info);
    }

    private KEKRecipientInfo getKEKInfo(ASN1TaggedObject aSN1TaggedObject) {
        if (aSN1TaggedObject.isExplicit()) {
            return KEKRecipientInfo.getInstance(aSN1TaggedObject, true);
        }
        return KEKRecipientInfo.getInstance(aSN1TaggedObject, false);
    }

    public ASN1Primitive toASN1Primitive() {
        return this.info.toASN1Primitive();
    }
}

