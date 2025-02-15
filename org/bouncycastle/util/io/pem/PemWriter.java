/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.io.pem;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.io.pem.PemHeader;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemObjectGenerator;

public class PemWriter
extends BufferedWriter {
    private static final int LINE_LENGTH = 64;
    private final int nlLength;
    private char[] buf = new char[64];

    public PemWriter(Writer writer) {
        super(writer);
        String string = Strings.lineSeparator();
        this.nlLength = string != null ? string.length() : 2;
    }

    public int getOutputSize(PemObject pemObject) {
        int n = 2 * (pemObject.getType().length() + 10 + this.nlLength) + 6 + 4;
        if (!pemObject.getHeaders().isEmpty()) {
            for (PemHeader pemHeader : pemObject.getHeaders()) {
                n += pemHeader.getName().length() + ": ".length() + pemHeader.getValue().length() + this.nlLength;
            }
            n += this.nlLength;
        }
        int n2 = (pemObject.getContent().length + 2) / 3 * 4;
        return n += n2 + (n2 + 64 - 1) / 64 * this.nlLength;
    }

    public void writeObject(PemObjectGenerator pemObjectGenerator) throws IOException {
        PemObject pemObject = pemObjectGenerator.generate();
        this.writePreEncapsulationBoundary(pemObject.getType());
        if (!pemObject.getHeaders().isEmpty()) {
            for (PemHeader pemHeader : pemObject.getHeaders()) {
                this.write(pemHeader.getName());
                this.write(": ");
                this.write(pemHeader.getValue());
                this.newLine();
            }
            this.newLine();
        }
        this.writeEncoded(pemObject.getContent());
        this.writePostEncapsulationBoundary(pemObject.getType());
    }

    private void writeEncoded(byte[] byArray) throws IOException {
        byArray = Base64.encode(byArray);
        for (int i = 0; i < byArray.length; i += this.buf.length) {
            int n;
            for (n = 0; n != this.buf.length && i + n < byArray.length; ++n) {
                this.buf[n] = (char)byArray[i + n];
            }
            this.write(this.buf, 0, n);
            this.newLine();
        }
    }

    private void writePreEncapsulationBoundary(String string) throws IOException {
        this.write("-----BEGIN " + string + "-----");
        this.newLine();
    }

    private void writePostEncapsulationBoundary(String string) throws IOException {
        this.write("-----END " + string + "-----");
        this.newLine();
    }
}

