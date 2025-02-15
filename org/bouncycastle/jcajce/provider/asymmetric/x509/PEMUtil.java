/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.x509;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.util.encoders.Base64;

class PEMUtil {
    private final String _header1;
    private final String _header2;
    private final String _header3;
    private final String _footer1;
    private final String _footer2;
    private final String _footer3;

    PEMUtil(String string) {
        this._header1 = "-----BEGIN " + string + "-----";
        this._header2 = "-----BEGIN X509 " + string + "-----";
        this._header3 = "-----BEGIN PKCS7-----";
        this._footer1 = "-----END " + string + "-----";
        this._footer2 = "-----END X509 " + string + "-----";
        this._footer3 = "-----END PKCS7-----";
    }

    private String readLine(InputStream inputStream) throws IOException {
        int n;
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            if ((n = inputStream.read()) != 13 && n != 10 && n >= 0) {
                stringBuffer.append((char)n);
                continue;
            }
            if (n < 0 || stringBuffer.length() != 0) break;
        }
        if (n < 0) {
            return null;
        }
        if (n == 13) {
            inputStream.mark(1);
            n = inputStream.read();
            if (n == 10) {
                inputStream.mark(1);
            }
            if (n > 0) {
                inputStream.reset();
            }
        }
        return stringBuffer.toString();
    }

    ASN1Sequence readPEMObject(InputStream inputStream) throws IOException {
        String string;
        StringBuffer stringBuffer = new StringBuffer();
        while (!((string = this.readLine(inputStream)) == null || string.startsWith(this._header1) || string.startsWith(this._header2) || string.startsWith(this._header3))) {
        }
        while (!((string = this.readLine(inputStream)) == null || string.startsWith(this._footer1) || string.startsWith(this._footer2) || string.startsWith(this._footer3))) {
            stringBuffer.append(string);
        }
        if (stringBuffer.length() != 0) {
            try {
                return ASN1Sequence.getInstance(Base64.decode(stringBuffer.toString()));
            } catch (Exception exception) {
                throw new IOException("malformed PEM data encountered");
            }
        }
        return null;
    }
}

