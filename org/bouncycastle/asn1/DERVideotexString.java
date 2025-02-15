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

public class DERVideotexString
extends ASN1Primitive
implements ASN1String {
    private final byte[] string;

    public static DERVideotexString getInstance(Object object) {
        if (object == null || object instanceof DERVideotexString) {
            return (DERVideotexString)object;
        }
        if (object instanceof byte[]) {
            try {
                return (DERVideotexString)DERVideotexString.fromByteArray((byte[])object);
            } catch (Exception exception) {
                throw new IllegalArgumentException("encoding error in getInstance: " + exception.toString());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    public static DERVideotexString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        ASN1Primitive aSN1Primitive = aSN1TaggedObject.getObject();
        if (bl || aSN1Primitive instanceof DERVideotexString) {
            return DERVideotexString.getInstance(aSN1Primitive);
        }
        return new DERVideotexString(((ASN1OctetString)aSN1Primitive).getOctets());
    }

    public DERVideotexString(byte[] byArray) {
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
        aSN1OutputStream.writeEncoded(21, this.string);
    }

    public int hashCode() {
        return Arrays.hashCode(this.string);
    }

    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERVideotexString)) {
            return false;
        }
        DERVideotexString dERVideotexString = (DERVideotexString)aSN1Primitive;
        return Arrays.areEqual(this.string, dERVideotexString.string);
    }

    public String getString() {
        return Strings.fromByteArray(this.string);
    }
}

