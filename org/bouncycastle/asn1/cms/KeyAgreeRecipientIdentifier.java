/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.cms.RecipientKeyIdentifier;

public class KeyAgreeRecipientIdentifier
extends ASN1Object
implements ASN1Choice {
    private IssuerAndSerialNumber issuerSerial;
    private RecipientKeyIdentifier rKeyID;

    public static KeyAgreeRecipientIdentifier getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return KeyAgreeRecipientIdentifier.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static KeyAgreeRecipientIdentifier getInstance(Object object) {
        if (object == null || object instanceof KeyAgreeRecipientIdentifier) {
            return (KeyAgreeRecipientIdentifier)object;
        }
        if (object instanceof ASN1Sequence) {
            return new KeyAgreeRecipientIdentifier(IssuerAndSerialNumber.getInstance(object));
        }
        if (object instanceof ASN1TaggedObject && ((ASN1TaggedObject)object).getTagNo() == 0) {
            return new KeyAgreeRecipientIdentifier(RecipientKeyIdentifier.getInstance((ASN1TaggedObject)object, false));
        }
        throw new IllegalArgumentException("Invalid KeyAgreeRecipientIdentifier: " + object.getClass().getName());
    }

    public KeyAgreeRecipientIdentifier(IssuerAndSerialNumber issuerAndSerialNumber) {
        this.issuerSerial = issuerAndSerialNumber;
        this.rKeyID = null;
    }

    public KeyAgreeRecipientIdentifier(RecipientKeyIdentifier recipientKeyIdentifier) {
        this.issuerSerial = null;
        this.rKeyID = recipientKeyIdentifier;
    }

    public IssuerAndSerialNumber getIssuerAndSerialNumber() {
        return this.issuerSerial;
    }

    public RecipientKeyIdentifier getRKeyID() {
        return this.rKeyID;
    }

    public ASN1Primitive toASN1Primitive() {
        if (this.issuerSerial != null) {
            return this.issuerSerial.toASN1Primitive();
        }
        return new DERTaggedObject(false, 0, this.rKeyID);
    }
}

