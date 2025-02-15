/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.cms.KEKIdentifier;
import org.bouncycastle.cms.KEKRecipientInfoGenerator;
import org.bouncycastle.operator.jcajce.JceSymmetricKeyWrapper;

public class JceKEKRecipientInfoGenerator
extends KEKRecipientInfoGenerator {
    public JceKEKRecipientInfoGenerator(KEKIdentifier kEKIdentifier, SecretKey secretKey) {
        super(kEKIdentifier, new JceSymmetricKeyWrapper(secretKey));
    }

    public JceKEKRecipientInfoGenerator(byte[] byArray, SecretKey secretKey) {
        this(new KEKIdentifier(byArray, null, null), secretKey);
    }

    public JceKEKRecipientInfoGenerator setProvider(Provider provider) {
        ((JceSymmetricKeyWrapper)this.wrapper).setProvider(provider);
        return this;
    }

    public JceKEKRecipientInfoGenerator setProvider(String string) {
        ((JceSymmetricKeyWrapper)this.wrapper).setProvider(string);
        return this;
    }

    public JceKEKRecipientInfoGenerator setSecureRandom(SecureRandom secureRandom) {
        ((JceSymmetricKeyWrapper)this.wrapper).setSecureRandom(secureRandom);
        return this;
    }
}

