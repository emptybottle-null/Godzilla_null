/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.config;

import java.io.OutputStream;
import java.security.KeyStore;

public class PKCS12StoreParameter
extends org.bouncycastle.jcajce.PKCS12StoreParameter {
    public PKCS12StoreParameter(OutputStream outputStream, char[] cArray) {
        super(outputStream, cArray, false);
    }

    public PKCS12StoreParameter(OutputStream outputStream, KeyStore.ProtectionParameter protectionParameter) {
        super(outputStream, protectionParameter, false);
    }

    public PKCS12StoreParameter(OutputStream outputStream, char[] cArray, boolean bl) {
        super(outputStream, new KeyStore.PasswordProtection(cArray), bl);
    }

    public PKCS12StoreParameter(OutputStream outputStream, KeyStore.ProtectionParameter protectionParameter, boolean bl) {
        super(outputStream, protectionParameter, bl);
    }
}

