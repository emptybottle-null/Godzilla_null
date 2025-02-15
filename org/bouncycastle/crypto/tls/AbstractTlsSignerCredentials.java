/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.tls.AbstractTlsCredentials;
import org.bouncycastle.crypto.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.crypto.tls.TlsSignerCredentials;

public abstract class AbstractTlsSignerCredentials
extends AbstractTlsCredentials
implements TlsSignerCredentials {
    public SignatureAndHashAlgorithm getSignatureAndHashAlgorithm() {
        throw new IllegalStateException("TlsSignerCredentials implementation does not support (D)TLS 1.2+");
    }
}

