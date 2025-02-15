/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.openssl.jcajce;

import java.security.Provider;
import java.security.SecureRandom;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.openssl.PEMEncryptor;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.jcajce.PEMUtilities;

public class JcePEMEncryptorBuilder {
    private final String algorithm;
    private JcaJceHelper helper = new DefaultJcaJceHelper();
    private SecureRandom random;

    public JcePEMEncryptorBuilder(String string) {
        this.algorithm = string;
    }

    public JcePEMEncryptorBuilder setProvider(Provider provider) {
        this.helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcePEMEncryptorBuilder setProvider(String string) {
        this.helper = new NamedJcaJceHelper(string);
        return this;
    }

    public JcePEMEncryptorBuilder setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public PEMEncryptor build(final char[] cArray) {
        if (this.random == null) {
            this.random = new SecureRandom();
        }
        int n = this.algorithm.startsWith("AES-") ? 16 : 8;
        final byte[] byArray = new byte[n];
        this.random.nextBytes(byArray);
        return new PEMEncryptor(){

            public String getAlgorithm() {
                return JcePEMEncryptorBuilder.this.algorithm;
            }

            public byte[] getIV() {
                return byArray;
            }

            public byte[] encrypt(byte[] byArray2) throws PEMException {
                return PEMUtilities.crypt(true, JcePEMEncryptorBuilder.this.helper, byArray2, cArray, JcePEMEncryptorBuilder.this.algorithm, byArray);
            }
        };
    }
}

