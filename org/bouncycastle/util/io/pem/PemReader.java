/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.io.pem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.io.pem.PemHeader;
import org.bouncycastle.util.io.pem.PemObject;

public class PemReader
extends BufferedReader {
    private static final String BEGIN = "-----BEGIN ";
    private static final String END = "-----END ";

    public PemReader(Reader reader) {
        super(reader);
    }

    public PemObject readPemObject() throws IOException {
        String string = this.readLine();
        while (string != null && !string.startsWith(BEGIN)) {
            string = this.readLine();
        }
        if (string != null) {
            string = string.substring(BEGIN.length());
            int n = string.indexOf(45);
            String string2 = string.substring(0, n);
            if (n > 0) {
                return this.loadObject(string2);
            }
        }
        return null;
    }

    private PemObject loadObject(String string) throws IOException {
        String string2;
        String string3 = END + string;
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList<PemHeader> arrayList = new ArrayList<PemHeader>();
        while ((string2 = this.readLine()) != null) {
            if (string2.indexOf(":") >= 0) {
                int n = string2.indexOf(58);
                String string4 = string2.substring(0, n);
                String string5 = string2.substring(n + 1).trim();
                arrayList.add(new PemHeader(string4, string5));
                continue;
            }
            if (string2.indexOf(string3) != -1) break;
            stringBuffer.append(string2.trim());
        }
        if (string2 == null) {
            throw new IOException(string3 + " not found");
        }
        return new PemObject(string, arrayList, Base64.decode(stringBuffer.toString()));
    }
}

