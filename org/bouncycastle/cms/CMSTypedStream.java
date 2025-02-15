/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.util.io.Streams;

public class CMSTypedStream {
    private static final int BUF_SIZ = 32768;
    private final ASN1ObjectIdentifier _oid;
    protected InputStream _in;

    public CMSTypedStream(InputStream inputStream) {
        this(PKCSObjectIdentifiers.data.getId(), inputStream, 32768);
    }

    public CMSTypedStream(String string, InputStream inputStream) {
        this(new ASN1ObjectIdentifier(string), inputStream, 32768);
    }

    public CMSTypedStream(String string, InputStream inputStream, int n) {
        this(new ASN1ObjectIdentifier(string), inputStream, n);
    }

    public CMSTypedStream(ASN1ObjectIdentifier aSN1ObjectIdentifier, InputStream inputStream) {
        this(aSN1ObjectIdentifier, inputStream, 32768);
    }

    public CMSTypedStream(ASN1ObjectIdentifier aSN1ObjectIdentifier, InputStream inputStream, int n) {
        this._oid = aSN1ObjectIdentifier;
        this._in = new FullReaderStream(new BufferedInputStream(inputStream, n));
    }

    protected CMSTypedStream(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this._oid = aSN1ObjectIdentifier;
    }

    public ASN1ObjectIdentifier getContentType() {
        return this._oid;
    }

    public InputStream getContentStream() {
        return this._in;
    }

    public void drain() throws IOException {
        Streams.drain(this._in);
        this._in.close();
    }

    private static class FullReaderStream
    extends FilterInputStream {
        FullReaderStream(InputStream inputStream) {
            super(inputStream);
        }

        public int read(byte[] byArray, int n, int n2) throws IOException {
            int n3 = Streams.readFully(this.in, byArray, n, n2);
            return n3 > 0 ? n3 : -1;
        }
    }
}

