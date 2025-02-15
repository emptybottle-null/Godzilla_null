/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.bouncycastle.crypto.tls.CertChainType;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.crypto.tls.URLAndHash;

public class CertificateURL {
    protected short type;
    protected Vector urlAndHashList;

    public CertificateURL(short s, Vector vector) {
        if (!CertChainType.isValid(s)) {
            throw new IllegalArgumentException("'type' is not a valid CertChainType value");
        }
        if (vector == null || vector.isEmpty()) {
            throw new IllegalArgumentException("'urlAndHashList' must have length > 0");
        }
        this.type = s;
        this.urlAndHashList = vector;
    }

    public short getType() {
        return this.type;
    }

    public Vector getURLAndHashList() {
        return this.urlAndHashList;
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsUtils.writeUint8(this.type, outputStream);
        ListBuffer16 listBuffer16 = new ListBuffer16();
        for (int i = 0; i < this.urlAndHashList.size(); ++i) {
            URLAndHash uRLAndHash = (URLAndHash)this.urlAndHashList.elementAt(i);
            uRLAndHash.encode(listBuffer16);
        }
        listBuffer16.encodeTo(outputStream);
    }

    public static CertificateURL parse(TlsContext tlsContext, InputStream inputStream) throws IOException {
        short s = TlsUtils.readUint8(inputStream);
        if (!CertChainType.isValid(s)) {
            throw new TlsFatalAlert(50);
        }
        int n = TlsUtils.readUint16(inputStream);
        if (n < 1) {
            throw new TlsFatalAlert(50);
        }
        byte[] byArray = TlsUtils.readFully(n, inputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        Vector<URLAndHash> vector = new Vector<URLAndHash>();
        while (byteArrayInputStream.available() > 0) {
            URLAndHash uRLAndHash = URLAndHash.parse(tlsContext, byteArrayInputStream);
            vector.addElement(uRLAndHash);
        }
        return new CertificateURL(s, vector);
    }

    class ListBuffer16
    extends ByteArrayOutputStream {
        ListBuffer16() throws IOException {
            TlsUtils.writeUint16(0, this);
        }

        void encodeTo(OutputStream outputStream) throws IOException {
            int n = this.count - 2;
            TlsUtils.checkUint16(n);
            TlsUtils.writeUint16(n, this.buf, 0);
            outputStream.write(this.buf, 0, this.count);
            this.buf = null;
        }
    }
}

