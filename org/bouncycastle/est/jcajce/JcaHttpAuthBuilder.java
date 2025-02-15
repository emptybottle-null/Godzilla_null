/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est.jcajce;

import java.security.Provider;
import java.security.SecureRandom;
import org.bouncycastle.est.HttpAuth;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;

public class JcaHttpAuthBuilder {
    private JcaDigestCalculatorProviderBuilder providerBuilder = new JcaDigestCalculatorProviderBuilder();
    private final String realm;
    private final String username;
    private final char[] password;
    private SecureRandom random = new SecureRandom();

    public JcaHttpAuthBuilder(String string, char[] cArray) {
        this(null, string, cArray);
    }

    public JcaHttpAuthBuilder(String string, String string2, char[] cArray) {
        this.realm = string;
        this.username = string2;
        this.password = cArray;
    }

    public JcaHttpAuthBuilder setProvider(Provider provider) {
        this.providerBuilder.setProvider(provider);
        return this;
    }

    public JcaHttpAuthBuilder setProvider(String string) {
        this.providerBuilder.setProvider(string);
        return this;
    }

    public JcaHttpAuthBuilder setNonceGenerator(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public HttpAuth build() throws OperatorCreationException {
        return new HttpAuth(this.realm, this.username, this.password, this.random, this.providerBuilder.build());
    }
}

