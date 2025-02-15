/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.crypto.tls.TlsCredentials;

public interface TlsSignerCredentials
extends TlsCredentials {
    public byte[] generateCertificateSignature(byte[] var1) throws IOException;

    public SignatureAndHashAlgorithm getSignatureAndHashAlgorithm();
}

