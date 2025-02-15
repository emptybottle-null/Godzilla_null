/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1Generator;

public class BERGenerator
extends ASN1Generator {
    private boolean _tagged = false;
    private boolean _isExplicit;
    private int _tagNo;

    protected BERGenerator(OutputStream outputStream) {
        super(outputStream);
    }

    protected BERGenerator(OutputStream outputStream, int n, boolean bl) {
        super(outputStream);
        this._tagged = true;
        this._isExplicit = bl;
        this._tagNo = n;
    }

    public OutputStream getRawOutputStream() {
        return this._out;
    }

    private void writeHdr(int n) throws IOException {
        this._out.write(n);
        this._out.write(128);
    }

    protected void writeBERHeader(int n) throws IOException {
        if (this._tagged) {
            int n2 = this._tagNo | 0x80;
            if (this._isExplicit) {
                this.writeHdr(n2 | 0x20);
                this.writeHdr(n);
            } else if ((n & 0x20) != 0) {
                this.writeHdr(n2 | 0x20);
            } else {
                this.writeHdr(n2);
            }
        } else {
            this.writeHdr(n);
        }
    }

    protected void writeBEREnd() throws IOException {
        this._out.write(0);
        this._out.write(0);
        if (this._tagged && this._isExplicit) {
            this._out.write(0);
            this._out.write(0);
        }
    }
}

