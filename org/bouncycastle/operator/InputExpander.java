/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator;

import java.io.InputStream;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public interface InputExpander {
    public AlgorithmIdentifier getAlgorithmIdentifier();

    public InputStream getInputStream(InputStream var1);
}

