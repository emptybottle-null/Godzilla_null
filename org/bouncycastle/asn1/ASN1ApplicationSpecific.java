/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1ParsingException;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.StreamUtil;
import org.bouncycastle.util.Arrays;

public abstract class ASN1ApplicationSpecific
extends ASN1Primitive {
    protected final boolean isConstructed;
    protected final int tag;
    protected final byte[] octets;

    ASN1ApplicationSpecific(boolean bl, int n, byte[] byArray) {
        this.isConstructed = bl;
        this.tag = n;
        this.octets = Arrays.clone(byArray);
    }

    public static ASN1ApplicationSpecific getInstance(Object object) {
        if (object == null || object instanceof ASN1ApplicationSpecific) {
            return (ASN1ApplicationSpecific)object;
        }
        if (object instanceof byte[]) {
            try {
                return ASN1ApplicationSpecific.getInstance(ASN1Primitive.fromByteArray((byte[])object));
            } catch (IOException iOException) {
                throw new IllegalArgumentException("Failed to construct object from byte[]: " + iOException.getMessage());
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + object.getClass().getName());
    }

    protected static int getLengthOfHeader(byte[] byArray) {
        int n = byArray[1] & 0xFF;
        if (n == 128) {
            return 2;
        }
        if (n > 127) {
            int n2 = n & 0x7F;
            if (n2 > 4) {
                throw new IllegalStateException("DER length more than 4 bytes: " + n2);
            }
            return n2 + 2;
        }
        return 2;
    }

    public boolean isConstructed() {
        return this.isConstructed;
    }

    public byte[] getContents() {
        return Arrays.clone(this.octets);
    }

    public int getApplicationTag() {
        return this.tag;
    }

    public ASN1Primitive getObject() throws IOException {
        return ASN1Primitive.fromByteArray(this.getContents());
    }

    public ASN1Primitive getObject(int n) throws IOException {
        if (n >= 31) {
            throw new IOException("unsupported tag number");
        }
        byte[] byArray = this.getEncoded();
        byte[] byArray2 = this.replaceTagNumber(n, byArray);
        if ((byArray[0] & 0x20) != 0) {
            byArray2[0] = (byte)(byArray2[0] | 0x20);
        }
        return ASN1Primitive.fromByteArray(byArray2);
    }

    int encodedLength() throws IOException {
        return StreamUtil.calculateTagLength(this.tag) + StreamUtil.calculateBodyLength(this.octets.length) + this.octets.length;
    }

    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        int n = 64;
        if (this.isConstructed) {
            n |= 0x20;
        }
        aSN1OutputStream.writeEncoded(n, this.tag, this.octets);
    }

    boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1ApplicationSpecific)) {
            return false;
        }
        ASN1ApplicationSpecific aSN1ApplicationSpecific = (ASN1ApplicationSpecific)aSN1Primitive;
        return this.isConstructed == aSN1ApplicationSpecific.isConstructed && this.tag == aSN1ApplicationSpecific.tag && Arrays.areEqual(this.octets, aSN1ApplicationSpecific.octets);
    }

    public int hashCode() {
        return (this.isConstructed ? 1 : 0) ^ this.tag ^ Arrays.hashCode(this.octets);
    }

    private byte[] replaceTagNumber(int n, byte[] byArray) throws IOException {
        int n2 = byArray[0] & 0x1F;
        int n3 = 1;
        if (n2 == 31) {
            int n4;
            n2 = 0;
            if (((n4 = byArray[n3++] & 0xFF) & 0x7F) == 0) {
                throw new ASN1ParsingException("corrupted stream - invalid high tag number found");
            }
            while (n4 >= 0 && (n4 & 0x80) != 0) {
                n2 |= n4 & 0x7F;
                n2 <<= 7;
                n4 = byArray[n3++] & 0xFF;
            }
        }
        byte[] byArray2 = new byte[byArray.length - n3 + 1];
        System.arraycopy(byArray, n3, byArray2, 1, byArray2.length - 1);
        byArray2[0] = (byte)n;
        return byArray2;
    }
}

