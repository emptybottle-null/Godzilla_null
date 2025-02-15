/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator;

import java.io.OutputStream;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public interface ContentVerifier {
    public AlgorithmIdentifier getAlgorithmIdentifier();

    public OutputStream getOutputStream();

    public boolean verify(byte[] var1);
}

