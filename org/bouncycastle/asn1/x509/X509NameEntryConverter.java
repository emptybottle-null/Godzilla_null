/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.util.Strings;

public abstract class X509NameEntryConverter {
    protected ASN1Primitive convertHexEncoded(String string, int n) throws IOException {
        string = Strings.toLowerCase(string);
        byte[] byArray = new byte[(string.length() - n) / 2];
        for (int i = 0; i != byArray.length; ++i) {
            char c = string.charAt(i * 2 + n);
            char c2 = string.charAt(i * 2 + n + 1);
            byArray[i] = c < 'a' ? (byte)(c - 48 << 4) : (byte)(c - 97 + 10 << 4);
            if (c2 < 'a') {
                int n2 = i;
                byArray[n2] = (byte)(byArray[n2] | (byte)(c2 - 48));
                continue;
            }
            int n3 = i;
            byArray[n3] = (byte)(byArray[n3] | (byte)(c2 - 97 + 10));
        }
        ASN1InputStream aSN1InputStream = new ASN1InputStream(byArray);
        return aSN1InputStream.readObject();
    }

    protected boolean canBePrintable(String string) {
        return DERPrintableString.isPrintableString(string);
    }

    public abstract ASN1Primitive getConvertedValue(ASN1ObjectIdentifier var1, String var2);
}

