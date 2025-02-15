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

public class DERVisibleString
extends ASN1Primitive
implements ASN1String {
    private final byte[] string;

    public static DERVisibleString getInstance(Object object) {
        if (object == null || object instanceof DERVisibleString) {
            return (DERVisibleString)object;
        }
        if (object instanceof byte[]) {
            try {
                return (DERVisibleString)DERVisibleString.fromByteArray((byte[])object);
            } catch (Exception exception) {
                throw new IllegalArgumentException("encoding error in getInstance: " + exception.toString());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    public static DERVisibleString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        ASN1Primitive aSN1Primitive = aSN1TaggedObject.getObject();
        if (bl || aSN1Primitive instanceof DERVisibleString) {
            return DERVisibleString.getInstance(aSN1Primitive);
        }
        return new DERVisibleString(ASN1OctetString.getInstance(aSN1Primitive).getOctets());
    }

    DERVisibleString(byte[] byArray) {
        this.string = byArray;
    }

    public DERVisibleString(String string) {
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
        aSN1OutputStream.writeEncoded(26, this.string);
    }

    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERVisibleString)) {
            return false;
        }
        return Arrays.areEqual(this.string, ((DERVisibleString)aSN1Primitive).string);
    }

    public int hashCode() {
        return Arrays.hashCode(this.string);
    }
}

