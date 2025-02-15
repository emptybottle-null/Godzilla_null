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

public class DERGraphicString
extends ASN1Primitive
implements ASN1String {
    private final byte[] string;

    public static DERGraphicString getInstance(Object object) {
        if (object == null || object instanceof DERGraphicString) {
            return (DERGraphicString)object;
        }
        if (object instanceof byte[]) {
            try {
                return (DERGraphicString)DERGraphicString.fromByteArray((byte[])object);
            } catch (Exception exception) {
                throw new IllegalArgumentException("encoding error in getInstance: " + exception.toString());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    public static DERGraphicString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        ASN1Primitive aSN1Primitive = aSN1TaggedObject.getObject();
        if (bl || aSN1Primitive instanceof DERGraphicString) {
            return DERGraphicString.getInstance(aSN1Primitive);
        }
        return new DERGraphicString(((ASN1OctetString)aSN1Primitive).getOctets());
    }

    public DERGraphicString(byte[] byArray) {
        this.string = Arrays.clone(byArray);
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
        aSN1OutputStream.writeEncoded(25, this.string);
    }

    public int hashCode() {
        return Arrays.hashCode(this.string);
    }

    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERGraphicString)) {
            return false;
        }
        DERGraphicString dERGraphicString = (DERGraphicString)aSN1Primitive;
        return Arrays.areEqual(this.string, dERGraphicString.string);
    }

    public String getString() {
        return Strings.fromByteArray(this.string);
    }
}

