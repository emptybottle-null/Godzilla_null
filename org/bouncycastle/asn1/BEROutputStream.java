/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROutputStream;

public class BEROutputStream
extends DEROutputStream {
    public BEROutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public void writeObject(Object object) throws IOException {
        if (object == null) {
            this.writeNull();
        } else if (object instanceof ASN1Primitive) {
            ((ASN1Primitive)object).encode(this);
        } else if (object instanceof ASN1Encodable) {
            ((ASN1Encodable)object).toASN1Primitive().encode(this);
        } else {
            throw new IOException("object not BEREncodable");
        }
    }
}

