/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.crmf;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.crmf.POPOPrivKey;
import org.bouncycastle.asn1.crmf.POPOSigningKey;

public class ProofOfPossession
extends ASN1Object
implements ASN1Choice {
    public static final int TYPE_RA_VERIFIED = 0;
    public static final int TYPE_SIGNING_KEY = 1;
    public static final int TYPE_KEY_ENCIPHERMENT = 2;
    public static final int TYPE_KEY_AGREEMENT = 3;
    private int tagNo;
    private ASN1Encodable obj;

    private ProofOfPossession(ASN1TaggedObject aSN1TaggedObject) {
        this.tagNo = aSN1TaggedObject.getTagNo();
        switch (this.tagNo) {
            case 0: {
                this.obj = DERNull.INSTANCE;
                break;
            }
            case 1: {
                this.obj = POPOSigningKey.getInstance(aSN1TaggedObject, false);
                break;
            }
            case 2: 
            case 3: {
                this.obj = POPOPrivKey.getInstance(aSN1TaggedObject, true);
                break;
            }
            default: {
                throw new IllegalArgumentException("unknown tag: " + this.tagNo);
            }
        }
    }

    public static ProofOfPossession getInstance(Object object) {
        if (object == null || object instanceof ProofOfPossession) {
            return (ProofOfPossession)object;
        }
        if (object instanceof ASN1TaggedObject) {
            return new ProofOfPossession((ASN1TaggedObject)object);
        }
        throw new IllegalArgumentException("Invalid object: " + object.getClass().getName());
    }

    public ProofOfPossession() {
        this.tagNo = 0;
        this.obj = DERNull.INSTANCE;
    }

    public ProofOfPossession(POPOSigningKey pOPOSigningKey) {
        this.tagNo = 1;
        this.obj = pOPOSigningKey;
    }

    public ProofOfPossession(int n, POPOPrivKey pOPOPrivKey) {
        this.tagNo = n;
        this.obj = pOPOPrivKey;
    }

    public int getType() {
        return this.tagNo;
    }

    public ASN1Encodable getObject() {
        return this.obj;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERTaggedObject(false, this.tagNo, this.obj);
    }
}

