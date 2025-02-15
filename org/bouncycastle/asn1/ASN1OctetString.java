/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1OctetStringParser;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

public abstract class ASN1OctetString
extends ASN1Primitive
implements ASN1OctetStringParser {
    byte[] string;

    public static ASN1OctetString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        ASN1Primitive aSN1Primitive = aSN1TaggedObject.getObject();
        if (bl || aSN1Primitive instanceof ASN1OctetString) {
            return ASN1OctetString.getInstance(aSN1Primitive);
        }
        return BEROctetString.fromSequence(ASN1Sequence.getInstance(aSN1Primitive));
    }

    public static ASN1OctetString getInstance(Object object) {
        ASN1Primitive aSN1Primitive;
        if (object == null || object instanceof ASN1OctetString) {
            return (ASN1OctetString)object;
        }
        if (object instanceof byte[]) {
            try {
                return ASN1OctetString.getInstance(ASN1Primitive.fromByteArray((byte[])object));
            } catch (IOException iOException) {
                throw new IllegalArgumentException("failed to construct OCTET STRING from byte[]: " + iOException.getMessage());
            }
        }
        if (object instanceof ASN1Encodable && (aSN1Primitive = ((ASN1Encodable)object).toASN1Primitive()) instanceof ASN1OctetString) {
            return (ASN1OctetString)aSN1Primitive;
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    public ASN1OctetString(byte[] byArray) {
        if (byArray == null) {
            throw new NullPointerException("string cannot be null");
        }
        this.string = byArray;
    }

    public InputStream getOctetStream() {
        return new ByteArrayInputStream(this.string);
    }

    public ASN1OctetStringParser parser() {
        return this;
    }

    public byte[] getOctets() {
        return this.string;
    }

    public int hashCode() {
        return Arrays.hashCode(this.getOctets());
    }

    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1OctetString)) {
            return false;
        }
        ASN1OctetString aSN1OctetString = (ASN1OctetString)aSN1Primitive;
        return Arrays.areEqual(this.string, aSN1OctetString.string);
    }

    public ASN1Primitive getLoadedObject() {
        return this.toASN1Primitive();
    }

    ASN1Primitive toDERObject() {
        return new DEROctetString(this.string);
    }

    ASN1Primitive toDLObject() {
        return new DEROctetString(this.string);
    }

    abstract void encode(ASN1OutputStream var1) throws IOException;

    public String toString() {
        return "#" + Strings.fromByteArray(Hex.encode(this.string));
    }
}

