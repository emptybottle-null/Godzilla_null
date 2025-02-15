/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.StreamUtil;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

public class DERIA5String
extends ASN1Primitive
implements ASN1String {
    private final byte[] string;

    public static DERIA5String getInstance(Object object) {
        if (object == null || object instanceof DERIA5String) {
            return (DERIA5String)object;
        }
        if (object instanceof byte[]) {
            try {
                return (DERIA5String)DERIA5String.fromByteArray((byte[])object);
            } catch (Exception exception) {
                throw new IllegalArgumentException("encoding error in getInstance: " + exception.toString());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    public static DERIA5String getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        ASN1Primitive aSN1Primitive = aSN1TaggedObject.getObject();
        if (bl || aSN1Primitive instanceof DERIA5String) {
            return DERIA5String.getInstance(aSN1Primitive);
        }
        return new DERIA5String(((ASN1OctetString)aSN1Primitive).getOctets());
    }

    DERIA5String(byte[] byArray) {
        this.string = byArray;
    }

    public DERIA5String(String string) {
        this(string, false);
    }

    public DERIA5String(String string, boolean bl) {
        if (string == null) {
            throw new NullPointerException("string cannot be null");
        }
        if (bl && !DERIA5String.isIA5String(string)) {
            throw new IllegalArgumentException("string contains illegal characters");
        }
        this.string = Strings.toByteArray(string);
    }

    public String getString() {
        return Strings.fromByteArray(this.string);
    }

    public String toString() {
        return this.getString();
    }

    public byte[] getOctets() {
        return Arrays.clone(this.string);
    }

    boolean isConstructed() {
        return false;
    }

    int encodedLength() {
        return 1 + StreamUtil.calculateBodyLength(this.string.length) + this.string.length;
    }

    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeEncoded(22, this.string);
    }

    public int hashCode() {
        return Arrays.hashCode(this.string);
    }

    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERIA5String)) {
            return false;
        }
        DERIA5String dERIA5String = (DERIA5String)aSN1Primitive;
        return Arrays.areEqual(this.string, dERIA5String.string);
    }

    public static boolean isIA5String(String string) {
        for (int i = string.length() - 1; i >= 0; --i) {
            char c = string.charAt(i);
            if (c <= '\u007f') continue;
            return false;
        }
        return true;
    }
}

