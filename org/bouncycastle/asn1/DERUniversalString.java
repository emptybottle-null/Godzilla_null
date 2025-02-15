/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1ParsingException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.StreamUtil;
import org.bouncycastle.util.Arrays;

public class DERUniversalString
extends ASN1Primitive
implements ASN1String {
    private static final char[] table = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final byte[] string;

    public static DERUniversalString getInstance(Object object) {
        if (object == null || object instanceof DERUniversalString) {
            return (DERUniversalString)object;
        }
        if (object instanceof byte[]) {
            try {
                return (DERUniversalString)DERUniversalString.fromByteArray((byte[])object);
            } catch (Exception exception) {
                throw new IllegalArgumentException("encoding error getInstance: " + exception.toString());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    public static DERUniversalString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        ASN1Primitive aSN1Primitive = aSN1TaggedObject.getObject();
        if (bl || aSN1Primitive instanceof DERUniversalString) {
            return DERUniversalString.getInstance(aSN1Primitive);
        }
        return new DERUniversalString(((ASN1OctetString)aSN1Primitive).getOctets());
    }

    public DERUniversalString(byte[] byArray) {
        this.string = Arrays.clone(byArray);
    }

    public String getString() {
        StringBuffer stringBuffer = new StringBuffer("#");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ASN1OutputStream aSN1OutputStream = new ASN1OutputStream(byteArrayOutputStream);
        try {
            aSN1OutputStream.writeObject(this);
        } catch (IOException iOException) {
            throw new ASN1ParsingException("internal error encoding BitString");
        }
        byte[] byArray = byteArrayOutputStream.toByteArray();
        for (int i = 0; i != byArray.length; ++i) {
            stringBuffer.append(table[byArray[i] >>> 4 & 0xF]);
            stringBuffer.append(table[byArray[i] & 0xF]);
        }
        return stringBuffer.toString();
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
        aSN1OutputStream.writeEncoded(28, this.getOctets());
    }

    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERUniversalString)) {
            return false;
        }
        return Arrays.areEqual(this.string, ((DERUniversalString)aSN1Primitive).string);
    }

    public int hashCode() {
        return Arrays.hashCode(this.string);
    }
}

