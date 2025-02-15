/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DLBitString;
import org.bouncycastle.asn1.StreamUtil;

public class DERBitString
extends ASN1BitString {
    public static DERBitString getInstance(Object object) {
        if (object == null || object instanceof DERBitString) {
            return (DERBitString)object;
        }
        if (object instanceof DLBitString) {
            return new DERBitString(((DLBitString)object).data, ((DLBitString)object).padBits);
        }
        if (object instanceof byte[]) {
            try {
                return (DERBitString)DERBitString.fromByteArray((byte[])object);
            } catch (Exception exception) {
                throw new IllegalArgumentException("encoding error in getInstance: " + exception.toString());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + object.getClass().getName());
    }

    public static DERBitString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        ASN1Primitive aSN1Primitive = aSN1TaggedObject.getObject();
        if (bl || aSN1Primitive instanceof DERBitString) {
            return DERBitString.getInstance(aSN1Primitive);
        }
        return DERBitString.fromOctetString(((ASN1OctetString)aSN1Primitive).getOctets());
    }

    protected DERBitString(byte by, int n) {
        this(DERBitString.toByteArray(by), n);
    }

    private static byte[] toByteArray(byte by) {
        byte[] byArray = new byte[]{by};
        return byArray;
    }

    public DERBitString(byte[] byArray, int n) {
        super(byArray, n);
    }

    public DERBitString(byte[] byArray) {
        this(byArray, 0);
    }

    public DERBitString(int n) {
        super(DERBitString.getBytes(n), DERBitString.getPadBits(n));
    }

    public DERBitString(ASN1Encodable aSN1Encodable) throws IOException {
        super(aSN1Encodable.toASN1Primitive().getEncoded("DER"), 0);
    }

    boolean isConstructed() {
        return false;
    }

    int encodedLength() {
        return 1 + StreamUtil.calculateBodyLength(this.data.length + 1) + this.data.length + 1;
    }

    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        byte[] byArray = DERBitString.derForm(this.data, this.padBits);
        byte[] byArray2 = new byte[byArray.length + 1];
        byArray2[0] = (byte)this.getPadBits();
        System.arraycopy(byArray, 0, byArray2, 1, byArray2.length - 1);
        aSN1OutputStream.writeEncoded(3, byArray2);
    }

    static DERBitString fromOctetString(byte[] byArray) {
        if (byArray.length < 1) {
            throw new IllegalArgumentException("truncated BIT STRING detected");
        }
        byte by = byArray[0];
        byte[] byArray2 = new byte[byArray.length - 1];
        if (byArray2.length != 0) {
            System.arraycopy(byArray, 1, byArray2, 0, byArray.length - 1);
        }
        return new DERBitString(byArray2, (int)by);
    }
}

