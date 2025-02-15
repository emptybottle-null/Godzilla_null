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
import org.bouncycastle.util.encoders.Hex;

public class DERApplicationSpecific
extends ASN1ApplicationSpecific {
    DERApplicationSpecific(boolean bl, int n, byte[] byArray) {
        super(bl, n, byArray);
    }

    public DERApplicationSpecific(int n, byte[] byArray) {
        this(false, n, byArray);
    }

    public DERApplicationSpecific(int n, ASN1Encodable aSN1Encodable) throws IOException {
        this(true, n, aSN1Encodable);
    }

    public DERApplicationSpecific(boolean bl, int n, ASN1Encodable aSN1Encodable) throws IOException {
        super(bl || aSN1Encodable.toASN1Primitive().isConstructed(), n, DERApplicationSpecific.getEncoding(bl, aSN1Encodable));
    }

    private static byte[] getEncoding(boolean bl, ASN1Encodable aSN1Encodable) throws IOException {
        byte[] byArray = aSN1Encodable.toASN1Primitive().getEncoded("DER");
        if (bl) {
            return byArray;
        }
        int n = DERApplicationSpecific.getLengthOfHeader(byArray);
        byte[] byArray2 = new byte[byArray.length - n];
        System.arraycopy(byArray, n, byArray2, 0, byArray2.length);
        return byArray2;
    }

    public DERApplicationSpecific(int n, ASN1EncodableVector aSN1EncodableVector) {
        super(true, n, DERApplicationSpecific.getEncodedVector(aSN1EncodableVector));
    }

    private static byte[] getEncodedVector(ASN1EncodableVector aSN1EncodableVector) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i != aSN1EncodableVector.size(); ++i) {
            try {
                byteArrayOutputStream.write(((ASN1Object)aSN1EncodableVector.get(i)).getEncoded("DER"));
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
        aSN1OutputStream.writeEncoded(n, this.tag, this.octets);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        if (this.isConstructed()) {
            stringBuffer.append("CONSTRUCTED ");
        }
        stringBuffer.append("APPLICATION ");
        stringBuffer.append(Integer.toString(this.getApplicationTag()));
        stringBuffer.append("]");
        if (this.octets != null) {
            stringBuffer.append(" #");
            stringBuffer.append(Hex.toHexString(this.octets));
        } else {
            stringBuffer.append(" #null");
        }
        stringBuffer.append(" ");
        return stringBuffer.toString();
    }
}

