/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.StreamUtil;

public class DERSet
extends ASN1Set {
    private int bodyLength = -1;

    public DERSet() {
    }

    public DERSet(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
    }

    public DERSet(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector, true);
    }

    public DERSet(ASN1Encodable[] aSN1EncodableArray) {
        super(aSN1EncodableArray, true);
    }

    DERSet(ASN1EncodableVector aSN1EncodableVector, boolean bl) {
        super(aSN1EncodableVector, bl);
    }

    private int getBodyLength() throws IOException {
        if (this.bodyLength < 0) {
            int n = 0;
            Enumeration enumeration = this.getObjects();
            while (enumeration.hasMoreElements()) {
                Object e = enumeration.nextElement();
                n += ((ASN1Encodable)e).toASN1Primitive().toDERObject().encodedLength();
            }
            this.bodyLength = n;
        }
        return this.bodyLength;
    }

    int encodedLength() throws IOException {
        int n = this.getBodyLength();
        return 1 + StreamUtil.calculateBodyLength(n) + n;
    }

    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        ASN1OutputStream aSN1OutputStream2 = aSN1OutputStream.getDERSubStream();
        int n = this.getBodyLength();
        aSN1OutputStream.write(49);
        aSN1OutputStream.writeLength(n);
        Enumeration enumeration = this.getObjects();
        while (enumeration.hasMoreElements()) {
            Object e = enumeration.nextElement();
            aSN1OutputStream2.writeObject((ASN1Encodable)e);
        }
    }
}

