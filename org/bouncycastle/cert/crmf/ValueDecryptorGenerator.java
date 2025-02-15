/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.crmf;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.crmf.CRMFException;
import org.bouncycastle.operator.InputDecryptor;

public interface ValueDecryptorGenerator {
    public InputDecryptor getValueDecryptor(AlgorithmIdentifier var1, AlgorithmIdentifier var2, byte[] var3) throws CRMFException;
}

