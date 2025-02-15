/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1OutputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.LazyConstructionEnumeration;
import org.bouncycastle.asn1.StreamUtil;

class LazyEncodedSequence
extends ASN1Sequence {
    private byte[] encoded;

    LazyEncodedSequence(byte[] byArray) throws IOException {
        this.encoded = byArray;
    }

    private void parse() {
        LazyConstructionEnumeration lazyConstructionEnumeration = new LazyConstructionEnumeration(this.encoded);
        while (lazyConstructionEnumeration.hasMoreElements()) {
            this.seq.addElement(lazyConstructionEnumeration.nextElement());
        }
        this.encoded = null;
    }

    public synchronized ASN1Encodable getObjectAt(int n) {
        if (this.encoded != null) {
            this.parse();
        }
        return super.getObjectAt(n);
    }

    public synchronized Enumeration getObjects() {
        if (this.encoded == null) {
            return super.getObjects();
        }
        return new LazyConstructionEnumeration(this.encoded);
    }

    public synchronized int size() {
        if (this.encoded != null) {
            this.parse();
        }
        return super.size();
    }

    ASN1Primitive toDERObject() {
        if (this.encoded != null) {
            this.parse();
        }
        return super.toDERObject();
    }

    ASN1Primitive toDLObject() {
        if (this.encoded != null) {
            this.parse();
        }
        return super.toDLObject();
    }

    int encodedLength() throws IOException {
        if (this.encoded != null) {
            return 1 + StreamUtil.calculateBodyLength(this.encoded.length) + this.encoded.length;
        }
        return super.toDLObject().encodedLength();
    }

    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        if (this.encoded != null) {
            aSN1OutputStream.writeEncoded(48, this.encoded);
        } else {
            super.toDLObject().encode(aSN1OutputStream);
        }
    }
}

