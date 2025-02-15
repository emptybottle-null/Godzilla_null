/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.crmf;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cms.EnvelopedData;
import org.bouncycastle.asn1.crmf.PKMACValue;
import org.bouncycastle.asn1.crmf.SubsequentMessage;

public class POPOPrivKey
extends ASN1Object
implements ASN1Choice {
    public static final int thisMessage = 0;
    public static final int subsequentMessage = 1;
    public static final int dhMAC = 2;
    public static final int agreeMAC = 3;
    public static final int encryptedKey = 4;
    private int tagNo;
    private ASN1Encodable obj;

    private POPOPrivKey(ASN1TaggedObject aSN1TaggedObject) {
        this.tagNo = aSN1TaggedObject.getTagNo();
        switch (this.tagNo) {
            case 0: {
                this.obj = DERBitString.getInstance(aSN1TaggedObject, false);
                break;
            }
            case 1: {
                this.obj = SubsequentMessage.valueOf(ASN1Integer.getInstance(aSN1TaggedObject, false).getValue().intValue());
                break;
            }
            case 2: {
                this.obj = DERBitString.getInstance(aSN1TaggedObject, false);
                break;
            }
            case 3: {
                this.obj = PKMACValue.getInstance(aSN1TaggedObject, false);
                break;
            }
            case 4: {
                this.obj = EnvelopedData.getInstance(aSN1TaggedObject, false);
                break;
            }
            default: {
                throw new IllegalArgumentException("unknown tag in POPOPrivKey");
            }
        }
    }

    public static POPOPrivKey getInstance(Object object) {
        if (object instanceof POPOPrivKey) {
            return (POPOPrivKey)object;
        }
        if (object != null) {
            return new POPOPrivKey(ASN1TaggedObject.getInstance(object));
        }
        return null;
    }

    public static POPOPrivKey getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return POPOPrivKey.getInstance(ASN1TaggedObject.getInstance(aSN1TaggedObject, bl));
    }

    public POPOPrivKey(SubsequentMessage subsequentMessage) {
        this.tagNo = 1;
        this.obj = subsequentMessage;
    }

    public int getType() {
        return this.tagNo;
    }

    public ASN1Encodable getValue() {
        return this.obj;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERTaggedObject(false, this.tagNo, this.obj);
    }
}

