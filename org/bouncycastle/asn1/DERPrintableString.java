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

public class DERPrintableString
extends ASN1Primitive
implements ASN1String {
    private final byte[] string;

    public static DERPrintableString getInstance(Object object) {
        if (object == null || object instanceof DERPrintableString) {
            return (DERPrintableString)object;
        }
        if (object instanceof byte[]) {
            try {
                return (DERPrintableString)DERPrintableString.fromByteArray((byte[])object);
            } catch (Exception exception) {
                throw new IllegalArgumentException("encoding error in getInstance: " + exception.toString());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    public static DERPrintableString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        ASN1Primitive aSN1Primitive = aSN1TaggedObject.getObject();
        if (bl || aSN1Primitive instanceof DERPrintableString) {
            return DERPrintableString.getInstance(aSN1Primitive);
        }
        return new DERPrintableString(ASN1OctetString.getInstance(aSN1Primitive).getOctets());
    }

    DERPrintableString(byte[] byArray) {
        this.string = byArray;
    }

    public DERPrintableString(String string) {
        this(string, false);
    }

    public DERPrintableString(String string, boolean bl) {
        if (bl && !DERPrintableString.isPrintableString(string)) {
            throw new IllegalArgumentException("string contains illegal characters");
        }
        this.string = Strings.toByteArray(string);
    }

    public String getString() {
        return Strings.fromByteArray(this.string);
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
        aSN1OutputStream.writeEncoded(19, this.string);
    }

    public int hashCode() {
        return Arrays.hashCode(this.string);
    }

    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERPrintableString)) {
            return false;
        }
        DERPrintableString dERPrintableString = (DERPrintableString)aSN1Primitive;
        return Arrays.areEqual(this.string, dERPrintableString.string);
    }

    public String toString() {
        return this.getString();
    }

    public static boolean isPrintableString(String string) {
        block3: for (int i = string.length() - 1; i >= 0; --i) {
            char c = string.charAt(i);
            if (c > '\u007f') {
                return false;
            }
            if ('a' <= c && c <= 'z' || 'A' <= c && c <= 'Z' || '0' <= c && c <= '9') continue;
            switch (c) {
                case ' ': 
                case '\'': 
                case '(': 
                case ')': 
                case '+': 
                case ',': 
                case '-': 
                case '.': 
                case '/': 
                case ':': 
                case '=': 
                case '?': {
                    continue block3;
                }
                default: {
                    return false;
                }
            }
        }
        return true;
    }
}

