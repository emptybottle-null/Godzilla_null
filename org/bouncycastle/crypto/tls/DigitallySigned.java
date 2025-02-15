/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.crypto.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsUtils;

public class DigitallySigned {
    protected SignatureAndHashAlgorithm algorithm;
    protected byte[] signature;

    public DigitallySigned(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] byArray) {
        if (byArray == null) {
            throw new IllegalArgumentException("'signature' cannot be null");
        }
        this.algorithm = signatureAndHashAlgorithm;
        this.signature = byArray;
    }

    public SignatureAndHashAlgorithm getAlgorithm() {
        return this.algorithm;
    }

    public byte[] getSignature() {
        return this.signature;
    }

    public void encode(OutputStream outputStream) throws IOException {
        if (this.algorithm != null) {
            this.algorithm.encode(outputStream);
        }
        TlsUtils.writeOpaque16(this.signature, outputStream);
    }

    public static DigitallySigned parse(TlsContext tlsContext, InputStream inputStream) throws IOException {
        SignatureAndHashAlgorithm signatureAndHashAlgorithm = null;
        if (TlsUtils.isTLSv12(tlsContext)) {
            signatureAndHashAlgorithm = SignatureAndHashAlgorithm.parse(inputStream);
        }
        byte[] byArray = TlsUtils.readOpaque16(inputStream);
        return new DigitallySigned(signatureAndHashAlgorithm, byArray);
    }
}

