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

public class DERBMPString
extends ASN1Primitive
implements ASN1String {
    private final char[] string;

    public static DERBMPString getInstance(Object object) {
        if (object == null || object instanceof DERBMPString) {
            return (DERBMPString)object;
        }
        if (object instanceof byte[]) {
            try {
                return (DERBMPString)DERBMPString.fromByteArray((byte[])object);
            } catch (Exception exception) {
                throw new IllegalArgumentException("encoding error in getInstance: " + exception.toString());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    public static DERBMPString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        ASN1Primitive aSN1Primitive = aSN1TaggedObject.getObject();
        if (bl || aSN1Primitive instanceof DERBMPString) {
            return DERBMPString.getInstance(aSN1Primitive);
        }
        return new DERBMPString(ASN1OctetString.getInstance(aSN1Primitive).getOctets());
    }

    DERBMPString(byte[] byArray) {
        char[] cArray = new char[byArray.length / 2];
        for (int i = 0; i != cArray.length; ++i) {
            cArray[i] = (char)(byArray[2 * i] << 8 | byArray[2 * i + 1] & 0xFF);
        }
        this.string = cArray;
    }

    DERBMPString(char[] cArray) {
        this.string = cArray;
    }

    public DERBMPString(String string) {
        this.string = string.toCharArray();
    }

    public String getString() {
        return new String(this.string);
    }

    public String toString() {
        return this.getString();
    }

    public int hashCode() {
        return Arrays.hashCode(this.string);
    }

    protected boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERBMPString)) {
            return false;
        }
        DERBMPString dERBMPString = (DERBMPString)aSN1Primitive;
        return Arrays.areEqual(this.string, dERBMPString.string);
    }

    boolean isConstructed() {
        return false;
    }

    int encodedLength() {
        return 1 + StreamUtil.calculateBodyLength(this.string.length * 2) + this.string.length * 2;
    }

    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.write(30);
        aSN1OutputStream.writeLength(this.string.length * 2);
        for (int i = 0; i != this.string.length; ++i) {
            char c = this.string[i];
            aSN1OutputStream.write((byte)(c >> 8));
            aSN1OutputStream.write((byte)c);
        }
    }
}

