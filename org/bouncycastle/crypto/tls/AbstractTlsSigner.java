/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsSigner;

public abstract class AbstractTlsSigner
implements TlsSigner {
    protected TlsContext context;

    public void init(TlsContext tlsContext) {
        this.context = tlsContext;
    }

    public byte[] generateRawSignature(AsymmetricKeyParameter asymmetricKeyParameter, byte[] byArray) throws CryptoException {
        return this.generateRawSignature(null, asymmetricKeyParameter, byArray);
    }

    public boolean verifyRawSignature(byte[] byArray, AsymmetricKeyParameter asymmetricKeyParameter, byte[] byArray2) throws CryptoException {
        return this.verifyRawSignature(null, byArray, asymmetricKeyParameter, byArray2);
    }

    public Signer createSigner(AsymmetricKeyParameter asymmetricKeyParameter) {
        return this.createSigner(null, asymmetricKeyParameter);
    }

    public Signer createVerifyer(AsymmetricKeyParameter asymmetricKeyParameter) {
        return this.createVerifyer(null, asymmetricKeyParameter);
    }
}

