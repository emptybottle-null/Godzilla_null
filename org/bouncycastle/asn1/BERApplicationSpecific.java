/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.bouncycastle.asn1.ASN1ApplicationSpecific;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1ParsingException;

public class BERApplicationSpecific
extends ASN1ApplicationSpecific {
    BERApplicationSpecific(boolean bl, int n, byte[] byArray) {
        super(bl, n, byArray);
    }

    public BERApplicationSpecific(int n, ASN1Encodable aSN1Encodable) throws IOException {
        this(true, n, aSN1Encodable);
    }

    public BERApplicationSpecific(boolean bl, int n, ASN1Encodable aSN1Encodable) throws IOException {
        super(bl || aSN1Encodable.toASN1Primitive().isConstructed(), n, BERApplicationSpecific.getEncoding(bl, aSN1Encodable));
    }

    private static byte[] getEncoding(boolean bl, ASN1Encodable aSN1Encodable) throws IOException {
        byte[] byArray = aSN1Encodable.toASN1Primitive().getEncoded("BER");
        if (bl) {
            return byArray;
        }
        int n = BERApplicationSpecific.getLengthOfHeader(byArray);
        byte[] byArray2 = new byte[byArray.length - n];
        System.arraycopy(byArray, n, byArray2, 0, byArray2.length);
        return byArray2;
    }

    public BERApplicationSpecific(int n, ASN1EncodableVector aSN1EncodableVector) {
        super(true, n, BERApplicationSpecific.getEncodedVector(aSN1EncodableVector));
    }

    private static byte[] getEncodedVector(ASN1EncodableVector aSN1EncodableVector) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i != aSN1EncodableVector.size(); ++i) {
            try {
                byteArrayOutputStream.write(((ASN1Object)aSN1EncodableVector.get(i)).getEncoded("BER"));
                continue;
            } catch (IOException iOException) {
                throw new ASN1ParsingException("malformed object: " + iOException, iOException);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        int n = 64;
        if (this.isConstructed) {
            n |= 0x20;
        }
        aSN1OutputStream.writeTag(n, this.tag);
        aSN1OutputStream.write(128);
        aSN1OutputStream.write(this.octets);
        aSN1OutputStream.write(0);
        aSN1OutputStream.write(0);
    }
}

