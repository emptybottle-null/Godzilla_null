/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.crypto.tls.TlsUtils;

public class Certificate {
    public static final Certificate EMPTY_CHAIN = new Certificate(new org.bouncycastle.asn1.x509.Certificate[0]);
    protected org.bouncycastle.asn1.x509.Certificate[] certificateList;

    public Certificate(org.bouncycastle.asn1.x509.Certificate[] certificateArray) {
        if (certificateArray == null) {
            throw new IllegalArgumentException("'certificateList' cannot be null");
        }
        this.certificateList = certificateArray;
    }

    public org.bouncycastle.asn1.x509.Certificate[] getCertificateList() {
        return this.cloneCertificateList();
    }

    public org.bouncycastle.asn1.x509.Certificate getCertificateAt(int n) {
        return this.certificateList[n];
    }

    public int getLength() {
        return this.certificateList.length;
    }

    public boolean isEmpty() {
        return this.certificateList.length == 0;
    }

    public void encode(OutputStream outputStream) throws IOException {
        byte[] byArray;
        int n;
        Vector<byte[]> vector = new Vector<byte[]>(this.certificateList.length);
        int n2 = 0;
        for (n = 0; n < this.certificateList.length; ++n) {
            byArray = this.certificateList[n].getEncoded("DER");
            vector.addElement(byArray);
            n2 += byArray.length + 3;
        }
        TlsUtils.checkUint24(n2);
        TlsUtils.writeUint24(n2, outputStream);
        for (n = 0; n < vector.size(); ++n) {
            byArray = (byte[])vector.elementAt(n);
            TlsUtils.writeOpaque24(byArray, outputStream);
        }
    }

    public static Certificate parse(InputStream inputStream) throws IOException {
        Object[] objectArray;
        int n = TlsUtils.readUint24(inputStream);
        if (n == 0) {
            return EMPTY_CHAIN;
        }
        byte[] byArray = TlsUtils.readFully(n, inputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byArray);
        Vector<org.bouncycastle.asn1.x509.Certificate> vector = new Vector<org.bouncycastle.asn1.x509.Certificate>();
        while (byteArrayInputStream.available() > 0) {
            objectArray = TlsUtils.readOpaque24(byteArrayInputStream);
            ASN1Primitive aSN1Primitive = TlsUtils.readASN1Object(objectArray);
            vector.addElement(org.bouncycastle.asn1.x509.Certificate.getInstance(aSN1Primitive));
        }
        objectArray = new org.bouncycastle.asn1.x509.Certificate[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            objectArray[i] = (byte)((org.bouncycastle.asn1.x509.Certificate)vector.elementAt(i));
        }
        return new Certificate((org.bouncycastle.asn1.x509.Certificate[])objectArray);
    }

    protected org.bouncycastle.asn1.x509.Certificate[] cloneCertificateList() {
        org.bouncycastle.asn1.x509.Certificate[] certificateArray = new org.bouncycastle.asn1.x509.Certificate[this.certificateList.length];
        System.arraycopy(this.certificateList, 0, certificateArray, 0, certificateArray.length);
        return certificateArray;
    }
}

