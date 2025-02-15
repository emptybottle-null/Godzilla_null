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

public class DERUTF8String
extends ASN1Primitive
implements ASN1String {
    private final byte[] string;

    public static DERUTF8String getInstance(Object object) {
        if (object == null || object instanceof DERUTF8String) {
            return (DERUTF8String)object;
        }
        if (object instanceof byte[]) {
            try {
                return (DERUTF8String)DERUTF8String.fromByteArray((byte[])object);
            } catch (Exception exception) {
                throw new IllegalArgumentException("encoding error in getInstance: " + exception.toString());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    public static DERUTF8String getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        ASN1Primitive aSN1Primitive = aSN1TaggedObject.getObject();
        if (bl || aSN1Primitive instanceof DERUTF8String) {
            return DERUTF8String.getInstance(aSN1Primitive);
        }
        return new DERUTF8String(ASN1OctetString.getInstance(aSN1Primitive).getOctets());
    }

    DERUTF8String(byte[] byArray) {
        this.string = byArray;
    }

    public DERUTF8String(String string) {
        this.string = Strings.toUTF8ByteArray(string);
    }

    public String getString() {
        return Strings.fromUTF8ByteArray(this.string);
    }

    public String toString() {
        return this.getString();
    }

    public int hashCode() {
        return Arrays.hashCode(this.string);
    }

    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERUTF8String)) {
            return false;
        }
        DERUTF8String dERUTF8String = (DERUTF8String)aSN1Primitive;
        return Arrays.areEqual(this.string, dERUTF8String.string);
    }

    boolean isConstructed() {
        return false;
    }

    int encodedLength() throws IOException {
        return 1 + StreamUtil.calculateBodyLength(this.string.length) + this.string.length;
    }

    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeEncoded(12, this.string);
    }
}

