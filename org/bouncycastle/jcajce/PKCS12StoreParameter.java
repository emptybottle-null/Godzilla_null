/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce;

import java.io.OutputStream;
import java.security.KeyStore;

public class PKCS12StoreParameter
implements KeyStore.LoadStoreParameter {
    private final OutputStream out;
    private final KeyStore.ProtectionParameter protectionParameter;
    private final boolean forDEREncoding;

    public PKCS12StoreParameter(OutputStream outputStream, char[] cArray) {
        this(outputStream, cArray, false);
    }

    public PKCS12StoreParameter(OutputStream outputStream, KeyStore.ProtectionParameter protectionParameter) {
        this(outputStream, protectionParameter, false);
    }

    public PKCS12StoreParameter(OutputStream outputStream, char[] cArray, boolean bl) {
        this(outputStream, new KeyStore.PasswordProtection(cArray), bl);
    }

    public PKCS12StoreParameter(OutputStream outputStream, KeyStore.ProtectionParameter protectionParameter, boolean bl) {
        this.out = outputStream;
        this.protectionParameter = protectionParameter;
        this.forDEREncoding = bl;
    }

    public OutputStream getOutputStream() {
        return this.out;
    }

    public KeyStore.ProtectionParameter getProtectionParameter() {
        return this.protectionParameter;
    }

    public boolean isForDEREncoding() {
        return this.forDEREncoding;
    }
}

