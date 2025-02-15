/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1OctetStringParser;
import org.bouncycastle.asn1.ASN1StreamParser;

class ConstructedOctetStream
extends InputStream {
    private final ASN1StreamParser _parser;
    private boolean _first = true;
    private InputStream _currentStream;

    ConstructedOctetStream(ASN1StreamParser aSN1StreamParser) {
        this._parser = aSN1StreamParser;
    }

    public int read(byte[] byArray, int n, int n2) throws IOException {
        if (this._currentStream == null) {
            if (!this._first) {
                return -1;
            }
            ASN1OctetStringParser aSN1OctetStringParser = (ASN1OctetStringParser)this._parser.readObject();
            if (aSN1OctetStringParser == null) {
                return -1;
            }
            this._first = false;
            this._currentStream = aSN1OctetStringParser.getOctetStream();
        }
        int n3 = 0;
        while (true) {
            int n4;
            if ((n4 = this._currentStream.read(byArray, n + n3, n2 - n3)) >= 0) {
                if ((n3 += n4) != n2) continue;
                return n3;
            }
            ASN1OctetStringParser aSN1OctetStringParser = (ASN1OctetStringParser)this._parser.readObject();
            if (aSN1OctetStringParser == null) {
                this._currentStream = null;
                return n3 < 1 ? -1 : n3;
            }
            this._currentStream = aSN1OctetStringParser.getOctetStream();
        }
    }

    public int read() throws IOException {
        if (this._currentStream == null) {
            if (!this._first) {
                return -1;
            }
            ASN1OctetStringParser aSN1OctetStringParser = (ASN1OctetStringParser)this._parser.readObject();
            if (aSN1OctetStringParser == null) {
                return -1;
            }
            this._first = false;
            this._currentStream = aSN1OctetStringParser.getOctetStream();
        }
        int n;
        while ((n = this._currentStream.read()) < 0) {
            ASN1OctetStringParser aSN1OctetStringParser = (ASN1OctetStringParser)this._parser.readObject();
            if (aSN1OctetStringParser == null) {
                this._currentStream = null;
                return -1;
            }
            this._currentStream = aSN1OctetStringParser.getOctetStream();
        }
        return n;
    }
}

