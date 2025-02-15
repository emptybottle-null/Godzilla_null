/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsUtils;

public class CertificateRequest {
    protected short[] certificateTypes;
    protected Vector supportedSignatureAlgorithms;
    protected Vector certificateAuthorities;

    public CertificateRequest(short[] sArray, Vector vector, Vector vector2) {
        this.certificateTypes = sArray;
        this.supportedSignatureAlgorithms = vector;
        this.certificateAuthorities = vector2;
    }

    public short[] getCertificateTypes() {
        return this.certificateTypes;
    }

    public Vector getSupportedSignatureAlgorithms() {
        return this.supportedSignatureAlgorithms;
    }

    public Vector getCertificateAuthorities() {
        return this.certificateAuthorities;
    }

    public void encode(OutputStream outputStream) throws IOException {
        if (this.certificateTypes == null || this.certificateTypes.length == 0) {
            TlsUtils.writeUint8(0, outputStream);
        } else {
            TlsUtils.writeUint8ArrayWithUint8Length(this.certificateTypes, outputStream);
        }
        if (this.supportedSignatureAlgorithms != null) {
            TlsUtils.encodeSupportedSignatureAlgorithms(this.supportedSignatureAlgorithms, false, outputStream);
        }
        if (this.certificateAuthorities == null || this.certificateAuthorities.isEmpty()) {
            TlsUtils.writeUint16(0, outputStream);
        } else {
            Object object;
            int n;
            Vector<byte[]> vector = new Vector<byte[]>(this.certificateAuthorities.size());
            int n2 = 0;
            for (n = 0; n < this.certificateAuthorities.size(); ++n) {
                object = (X500Name)this.certificateAuthorities.elementAt(n);
                byte[] byArray = ((ASN1Object)object).getEncoded("DER");
                vector.addElement(byArray);
                n2 += byArray.length + 2;
            }
            TlsUtils.checkUint16(n2);
            TlsUtils.writeUint16(n2, outputStream);
            for (n = 0; n < vector.size(); ++n) {
                object = (byte[])vector.elementAt(n);
                TlsUtils.writeOpaque16((byte[])object, outputStream);
            }
        }
    }

    public static CertificateRequest parse(TlsContext tlsContext, InputStream inputStream) throws IOException {
        int n = TlsUtils.readUint8(inputStream);
        short[] sArray = new short[n];
        for (int i = 0; i < n; ++i) {
            sArray[i] = TlsUtils.readUint8(inputStream);
        }
        Vector vector = null;
        if (TlsUtils.isTLSv12(tlsContext)) {
            vector = TlsUtils.parseSupportedSignatureAlgorithms(false, inputStream);
        }
        Vector<X500Name> vector2 = new Vector<X500Name>();
        byte[] byArray = TlsUtils.readOpaque16(inputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        while (byteArrayInputStream.available() > 0) {
            byte[] byArray2 = TlsUtils.readOpaque16(byteArrayInputStream);
            ASN1Primitive aSN1Primitive = TlsUtils.readDERObject(byArray2);
            vector2.addElement(X500Name.getInstance(aSN1Primitive));
        }
        return new CertificateRequest(sArray, vector, vector2);
    }
}

