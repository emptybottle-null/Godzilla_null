/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.openssl.jcajce;

import java.security.Provider;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.openssl.PEMDecryptor;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.PasswordException;
import org.bouncycastle.openssl.jcajce.PEMUtilities;

public class JcePEMDecryptorProviderBuilder {
    private JcaJceHelper helper = new DefaultJcaJceHelper();

    public JcePEMDecryptorProviderBuilder setProvider(Provider provider) {
        this.helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcePEMDecryptorProviderBuilder setProvider(String string) {
        this.helper = new NamedJcaJceHelper(string);
        return this;
    }

    public PEMDecryptorProvider build(final char[] cArray) {
        return new PEMDecryptorProvider(){

            public PEMDecryptor get(final String string) {
                return new PEMDecryptor(){

                    public byte[] decrypt(byte[] byArray, byte[] byArray2) throws PEMException {
                        if (cArray == null) {
                            throw new PasswordException("Password is null, but a password is required");
                        }
                        return PEMUtilities.crypt(false, JcePEMDecryptorProviderBuilder.this.helper, byArray, cArray, string, byArray2);
                    }
                };
            }
        };
    }
}

