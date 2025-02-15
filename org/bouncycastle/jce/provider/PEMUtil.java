/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.provider;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.util.encoders.Base64;

public class PEMUtil {
    private final String _header1;
    private final String _header2;
    private final String _footer1;
    private final String _footer2;

    PEMUtil(String string) {
        this._header1 = "-----BEGIN " + string + "-----";
        this._header2 = "-----BEGIN X509 " + string + "-----";
        this._footer1 = "-----END " + string + "-----";
        this._footer2 = "-----END X509 " + string + "-----";
    }

    private String readLine(InputStream inputStream) throws IOException {
        int n;
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            if ((n = inputStream.read()) != 13 && n != 10 && n >= 0) {
                if (n == 13) continue;
                stringBuffer.append((char)n);
                continue;
            }
            if (n < 0 || stringBuffer.length() != 0) break;
        }
        if (n < 0) {
            return null;
        }
        return stringBuffer.toString();
    }

    ASN1Sequence readPEMObject(InputStream inputStream) throws IOException {
        String string;
        StringBuffer stringBuffer = new StringBuffer();
        while ((string = this.readLine(inputStream)) != null && !string.startsWith(this._header1) && !string.startsWith(this._header2)) {
        }
        while ((string = this.readLine(inputStream)) != null && !string.startsWith(this._footer1) && !string.startsWith(this._footer2)) {
            stringBuffer.append(string);
        }
        if (stringBuffer.length() != 0) {
            ASN1Primitive aSN1Primitive = new ASN1InputStream(Base64.decode(stringBuffer.toString())).readObject();
            if (!(aSN1Primitive instanceof ASN1Sequence)) {
                throw new IOException("malformed PEM data encountered");
            }
            return (ASN1Sequence)aSN1Primitive;
        }
        return null;
    }
}

