/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROutputStream;
import org.bouncycastle.asn1.DLOutputStream;
import org.bouncycastle.util.Encodable;

public abstract class ASN1Object
implements ASN1Encodable,
Encodable {
    public byte[] getEncoded() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ASN1OutputStream aSN1OutputStream = new ASN1OutputStream(byteArrayOutputStream);
        aSN1OutputStream.writeObject(this);
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] getEncoded(String string) throws IOException {
        if (string.equals("DER")) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DEROutputStream dEROutputStream = new DEROutputStream(byteArrayOutputStream);
            dEROutputStream.writeObject(this);
            return byteArrayOutputStream.toByteArray();
        }
        if (string.equals("DL")) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DLOutputStream dLOutputStream = new DLOutputStream(byteArrayOutputStream);
            dLOutputStream.writeObject(this);
            return byteArrayOutputStream.toByteArray();
        }
        return this.getEncoded();
    }

    public int hashCode() {
        return this.toASN1Primitive().hashCode();
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ASN1Encodable)) {
            return false;
        }
        ASN1Encodable aSN1Encodable = (ASN1Encodable)object;
        return this.toASN1Primitive().equals(aSN1Encodable.toASN1Primitive());
    }

    public ASN1Primitive toASN1Object() {
        return this.toASN1Primitive();
    }

    protected static boolean hasEncodedTagValue(Object object, int n) {
        return object instanceof byte[] && ((byte[])object)[0] == n;
    }

    public abstract ASN1Primitive toASN1Primitive();
}

