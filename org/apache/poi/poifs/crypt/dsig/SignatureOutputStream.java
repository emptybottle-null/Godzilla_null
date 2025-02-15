/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.dsig;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import org.apache.poi.poifs.crypt.HashAlgorithm;
import org.apache.poi.poifs.crypt.dsig.DigestOutputStream;

class SignatureOutputStream
extends DigestOutputStream {
    Signature signature;

    SignatureOutputStream(HashAlgorithm algo, PrivateKey key) {
        super(algo, key);
    }

    @Override
    public void init() throws GeneralSecurityException {
        String provider = SignatureOutputStream.isMSCapi(this.key) ? "SunMSCAPI" : "SunRsaSign";
        this.signature = Security.getProvider(provider) != null ? Signature.getInstance(this.algo.ecmaString + "withRSA", provider) : Signature.getInstance(this.algo.ecmaString + "withRSA");
        this.signature.initSign(this.key);
    }

    @Override
    public byte[] sign() throws SignatureException {
        return this.signature.sign();
    }

    @Override
    public void write(int b) throws IOException {
        try {
            this.signature.update((byte)b);
        } catch (SignatureException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void write(byte[] data, int off, int len) throws IOException {
        try {
            this.signature.update(data, off, len);
        } catch (SignatureException e) {
            throw new IOException(e);
        }
    }
}

