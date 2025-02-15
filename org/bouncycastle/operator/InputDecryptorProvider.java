/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.InputDecryptor;
import org.bouncycastle.operator.OperatorCreationException;

public interface InputDecryptorProvider {
    public InputDecryptor get(AlgorithmIdentifier var1) throws OperatorCreationException;
}

