/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBMPString;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.DERVisibleString;

public class DisplayText
extends ASN1Object
implements ASN1Choice {
    public static final int CONTENT_TYPE_IA5STRING = 0;
    public static final int CONTENT_TYPE_BMPSTRING = 1;
    public static final int CONTENT_TYPE_UTF8STRING = 2;
    public static final int CONTENT_TYPE_VISIBLESTRING = 3;
    public static final int DISPLAY_TEXT_MAXIMUM_SIZE = 200;
    int contentType;
    ASN1String contents;

    public DisplayText(int n, String string) {
        if (string.length() > 200) {
            string = string.substring(0, 200);
        }
        this.contentType = n;
        switch (n) {
            case 0: {
                this.contents = new DERIA5String(string);
                break;
            }
            case 2: {
                this.contents = new DERUTF8String(string);
                break;
            }
            case 3: {
                this.contents = new DERVisibleString(string);
                break;
            }
            case 1: {
                this.contents = new DERBMPString(string);
                break;
            }
            default: {
                this.contents = new DERUTF8String(string);
            }
        }
    }

    public DisplayText(String string) {
        if (string.length() > 200) {
            string = string.substring(0, 200);
        }
        this.contentType = 2;
        this.contents = new DERUTF8String(string);
    }

    private DisplayText(ASN1String aSN1String) {
        this.contents = aSN1String;
        if (aSN1String instanceof DERUTF8String) {
            this.contentType = 2;
        } else if (aSN1String instanceof DERBMPString) {
            this.contentType = 1;
        } else if (aSN1String instanceof DERIA5String) {
            this.contentType = 0;
        } else if (aSN1String instanceof DERVisibleString) {
            this.contentType = 3;
        } else {
            throw new IllegalArgumentException("unknown STRING type in DisplayText");
        }
    }

    public static DisplayText getInstance(Object object) {
        if (object instanceof ASN1String) {
            return new DisplayText((ASN1String)object);
        }
        if (object == null || object instanceof DisplayText) {
            return (DisplayText)object;
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    public static DisplayText getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return DisplayText.getInstance(aSN1TaggedObject.getObject());
    }

    public ASN1Primitive toASN1Primitive() {
        return (ASN1Primitive)((Object)this.contents);
    }

    public String getString() {
        return this.contents.getString();
    }
}

