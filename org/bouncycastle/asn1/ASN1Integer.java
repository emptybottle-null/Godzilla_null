/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.StreamUtil;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Properties;

public class ASN1Integer
extends ASN1Primitive {
    private final byte[] bytes;

    public static ASN1Integer getInstance(Object object) {
        if (object == null || object instanceof ASN1Integer) {
            return (ASN1Integer)object;
        }
        if (object instanceof byte[]) {
            try {
                return (ASN1Integer)ASN1Integer.fromByteArray((byte[])object);
            } catch (Exception exception) {
                throw new IllegalArgumentException("encoding error in getInstance: " + exception.toString());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    public static ASN1Integer getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        ASN1Primitive aSN1Primitive = aSN1TaggedObject.getObject();
        if (bl || aSN1Primitive instanceof ASN1Integer) {
            return ASN1Integer.getInstance(aSN1Primitive);
        }
        return new ASN1Integer(ASN1OctetString.getInstance(aSN1TaggedObject.getObject()).getOctets());
    }

    public ASN1Integer(long l) {
        this.bytes = BigInteger.valueOf(l).toByteArray();
    }

    public ASN1Integer(BigInteger bigInteger) {
        this.bytes = bigInteger.toByteArray();
    }

    public ASN1Integer(byte[] byArray) {
        this(byArray, true);
    }

    ASN1Integer(byte[] byArray, boolean bl) {
        if (!Properties.isOverrideSet("org.bouncycastle.asn1.allow_unsafe_integer") && ASN1Integer.isMalformed(byArray)) {
            throw new IllegalArgumentException("malformed integer");
        }
        this.bytes = bl ? Arrays.clone(byArray) : byArray;
    }

    static boolean isMalformed(byte[] byArray) {
        if (byArray.length > 1) {
            if (byArray[0] == 0 && (byArray[1] & 0x80) == 0) {
                return true;
            }
            if (byArray[0] == -1 && (byArray[1] & 0x80) != 0) {
                return true;
            }
        }
        return false;
    }

    public BigInteger getValue() {
        return new BigInteger(this.bytes);
    }

    public BigInteger getPositiveValue() {
        return new BigInteger(1, this.bytes);
    }

    boolean isConstructed() {
        return false;
    }

    int encodedLength() {
        return 1 + StreamUtil.calculateBodyLength(this.bytes.length) + this.bytes.length;
    }

    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeEncoded(2, this.bytes);
    }

    public int hashCode() {
        int n = 0;
        for (int i = 0; i != this.bytes.length; ++i) {
            n ^= (this.bytes[i] & 0xFF) << i % 4;
        }
        return n;
    }

    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1Integer)) {
            return false;
        }
        ASN1Integer aSN1Integer = (ASN1Integer)aSN1Primitive;
        return Arrays.areEqual(this.bytes, aSN1Integer.bytes);
    }

    public String toString() {
        return this.getValue().toString();
    }
}

