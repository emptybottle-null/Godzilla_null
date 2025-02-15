/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Primitive;

public abstract class ASN1Null
extends ASN1Primitive {
    public static ASN1Null getInstance(Object object) {
        if (object instanceof ASN1Null) {
            return (ASN1Null)object;
        }
        if (object != null) {
            try {
                return ASN1Null.getInstance(ASN1Primitive.fromByteArray((byte[])object));
            } catch (IOException iOException) {
                throw new IllegalArgumentException("failed to construct NULL from byte[]: " + iOException.getMessage());
            } catch (ClassCastException classCastException) {
                throw new IllegalArgumentException("unknown object in getInstance(): " + object.getClass().getName());
            }
        }
        return null;
    }

    public int hashCode() {
        return -1;
    }

    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        return aSN1Primitive instanceof ASN1Null;
    }

    abstract void encode(ASN1OutputStream var1) throws IOException;

    public String toString() {
        return "NULL";
    }
}

