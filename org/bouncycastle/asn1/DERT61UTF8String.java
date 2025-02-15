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
import org.bouncycastle.asn1.DERT61String;
import org.bouncycastle.asn1.StreamUtil;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

public class DERT61UTF8String
extends ASN1Primitive
implements ASN1String {
    private byte[] string;

    public static DERT61UTF8String getInstance(Object object) {
        if (object instanceof DERT61String) {
            return new DERT61UTF8String(((DERT61String)object).getOctets());
        }
        if (object == null || object instanceof DERT61UTF8String) {
            return (DERT61UTF8String)object;
        }
        if (object instanceof byte[]) {
            try {
                return new DERT61UTF8String(((DERT61String)DERT61UTF8String.fromByteArray((byte[])object)).getOctets());
            } catch (Exception exception) {
                throw new IllegalArgumentException("encoding error in getInstance: " + exception.toString());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    public static DERT61UTF8String getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        ASN1Primitive aSN1Primitive = aSN1TaggedObject.getObject();
        if (bl || aSN1Primitive instanceof DERT61String || aSN1Primitive instanceof DERT61UTF8String) {
            return DERT61UTF8String.getInstance(aSN1Primitive);
        }
        return new DERT61UTF8String(ASN1OctetString.getInstance(aSN1Primitive).getOctets());
    }

    public DERT61UTF8String(byte[] byArray) {
        this.string = byArray;
    }

    public DERT61UTF8String(String string) {
        this(Strings.toUTF8ByteArray(string));
    }

    public String getString() {
        return Strings.fromUTF8ByteArray(this.string);
    }

    public String toString() {
        return this.getString();
    }

    boolean isConstructed() {
        return false;
    }

    int encodedLength() {
        return 1 + StreamUtil.calculateBodyLength(this.string.length) + this.string.length;
    }

    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeEncoded(20, this.string);
    }

    public byte[] getOctets() {
        return Arrays.clone(this.string);
    }

    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERT61UTF8String)) {
            return false;
        }
        return Arrays.areEqual(this.string, ((DERT61UTF8String)aSN1Primitive).string);
    }

    public int hashCode() {
        return Arrays.hashCode(this.string);
    }
}

