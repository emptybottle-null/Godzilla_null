/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.BERGenerator;
import org.bouncycastle.asn1.BEROutputStream;

public class BERSequenceGenerator
extends BERGenerator {
    public BERSequenceGenerator(OutputStream outputStream) throws IOException {
        super(outputStream);
        this.writeBERHeader(48);
    }

    public BERSequenceGenerator(OutputStream outputStream, int n, boolean bl) throws IOException {
        super(outputStream, n, bl);
        this.writeBERHeader(48);
    }

    public void addObject(ASN1Encodable aSN1Encodable) throws IOException {
        aSN1Encodable.toASN1Primitive().encode(new BEROutputStream(this._out));
    }

    public void close() throws IOException {
        this.writeBEREnd();
    }
}

