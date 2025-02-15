/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.crmf;

import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.crmf.EncryptedKey;

public class PKIArchiveOptions
extends ASN1Object
implements ASN1Choice {
    public static final int encryptedPrivKey = 0;
    public static final int keyGenParameters = 1;
    public static final int archiveRemGenPrivKey = 2;
    private ASN1Encodable value;

    public static PKIArchiveOptions getInstance(Object object) {
        if (object == null || object instanceof PKIArchiveOptions) {
            return (PKIArchiveOptions)object;
        }
        if (object instanceof ASN1TaggedObject) {
            return new PKIArchiveOptions((ASN1TaggedObject)object);
        }
        throw new IllegalArgumentException("unknown object: " + object);
    }

    private PKIArchiveOptions(ASN1TaggedObject aSN1TaggedObject) {
        switch (aSN1TaggedObject.getTagNo()) {
            case 0: {
                this.value = EncryptedKey.getInstance(aSN1TaggedObject.getObject());
                break;
            }
            case 1: {
                this.value = ASN1OctetString.getInstance(aSN1TaggedObject, false);
                break;
            }
            case 2: {
                this.value = ASN1Boolean.getInstance(aSN1TaggedObject, false);
                break;
            }
            default: {
                throw new IllegalArgumentException("unknown tag number: " + aSN1TaggedObject.getTagNo());
            }
        }
    }

    public PKIArchiveOptions(EncryptedKey encryptedKey) {
        this.value = encryptedKey;
    }

    public PKIArchiveOptions(ASN1OctetString aSN1OctetString) {
        this.value = aSN1OctetString;
    }

    public PKIArchiveOptions(boolean bl) {
        this.value = ASN1Boolean.getInstance(bl);
    }

    public int getType() {
        if (this.value instanceof EncryptedKey) {
            return 0;
        }
        if (this.value instanceof ASN1OctetString) {
            return 1;
        }
        return 2;
    }

    public ASN1Encodable getValue() {
        return this.value;
    }

    public ASN1Primitive toASN1Primitive() {
        if (this.value instanceof EncryptedKey) {
            return new DERTaggedObject(true, 0, this.value);
        }
        if (this.value instanceof ASN1OctetString) {
            return new DERTaggedObject(false, 1, this.value);
        }
        return new DERTaggedObject(false, 2, this.value);
    }
}

