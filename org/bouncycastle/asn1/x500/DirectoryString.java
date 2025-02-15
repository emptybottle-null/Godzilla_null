/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x500;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBMPString;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.DERT61String;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.DERUniversalString;

public class DirectoryString
extends ASN1Object
implements ASN1Choice,
ASN1String {
    private ASN1String string;

    public static DirectoryString getInstance(Object object) {
        if (object == null || object instanceof DirectoryString) {
            return (DirectoryString)object;
        }
        if (object instanceof DERT61String) {
            return new DirectoryString((DERT61String)object);
        }
        if (object instanceof DERPrintableString) {
            return new DirectoryString((DERPrintableString)object);
        }
        if (object instanceof DERUniversalString) {
            return new DirectoryString((DERUniversalString)object);
        }
        if (object instanceof DERUTF8String) {
            return new DirectoryString((DERUTF8String)object);
        }
        if (object instanceof DERBMPString) {
            return new DirectoryString((DERBMPString)object);
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    public static DirectoryString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        if (!bl) {
            throw new IllegalArgumentException("choice item must be explicitly tagged");
        }
        return DirectoryString.getInstance(aSN1TaggedObject.getObject());
    }

    private DirectoryString(DERT61String dERT61String) {
        this.string = dERT61String;
    }

    private DirectoryString(DERPrintableString dERPrintableString) {
        this.string = dERPrintableString;
    }

    private DirectoryString(DERUniversalString dERUniversalString) {
        this.string = dERUniversalString;
    }

    private DirectoryString(DERUTF8String dERUTF8String) {
        this.string = dERUTF8String;
    }

    private DirectoryString(DERBMPString dERBMPString) {
        this.string = dERBMPString;
    }

    public DirectoryString(String string) {
        this.string = new DERUTF8String(string);
    }

    public String getString() {
        return this.string.getString();
    }

    public String toString() {
        return this.string.getString();
    }

    public ASN1Primitive toASN1Primitive() {
        return ((ASN1Encodable)((Object)this.string)).toASN1Primitive();
    }
}

