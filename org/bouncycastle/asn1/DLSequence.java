/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.StreamUtil;

public class DLSequence
extends ASN1Sequence {
    private int bodyLength = -1;

    public DLSequence() {
    }

    public DLSequence(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
    }

    public DLSequence(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector);
    }

    public DLSequence(ASN1Encodable[] aSN1EncodableArray) {
        super(aSN1EncodableArray);
    }

    private int getBodyLength() throws IOException {
        if (this.bodyLength < 0) {
            int n = 0;
            Enumeration enumeration = this.getObjects();
            while (enumeration.hasMoreElements()) {
                Object e = enumeration.nextElement();
                n += ((ASN1Encodable)e).toASN1Primitive().toDLObject().encodedLength();
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
        ASN1OutputStream aSN1OutputStream2 = aSN1OutputStream.getDLSubStream();
        int n = this.getBodyLength();
        aSN1OutputStream.write(48);
        aSN1OutputStream.writeLength(n);
        Enumeration enumeration = this.getObjects();
        while (enumeration.hasMoreElements()) {
            Object e = enumeration.nextElement();
            aSN1OutputStream2.writeObject((ASN1Encodable)e);
        }
    }
}

