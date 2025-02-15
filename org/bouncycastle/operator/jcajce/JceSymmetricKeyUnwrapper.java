/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator.jcajce;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.SymmetricKeyUnwrapper;
import org.bouncycastle.operator.jcajce.JceGenericKey;
import org.bouncycastle.operator.jcajce.OperatorHelper;

public class JceSymmetricKeyUnwrapper
extends SymmetricKeyUnwrapper {
    private OperatorHelper helper = new OperatorHelper(new DefaultJcaJceHelper());
    private SecretKey secretKey;

    public JceSymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, SecretKey secretKey) {
        super(algorithmIdentifier);
        this.secretKey = secretKey;
    }

    public JceSymmetricKeyUnwrapper setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceSymmetricKeyUnwrapper setProvider(String string) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(string));
        return this;
    }

    public GenericKey generateUnwrappedKey(AlgorithmIdentifier algorithmIdentifier, byte[] byArray) throws OperatorException {
        try {
            Cipher cipher = this.helper.createSymmetricWrapper(this.getAlgorithmIdentifier().getAlgorithm());
            cipher.init(4, this.secretKey);
            return new JceGenericKey(algorithmIdentifier, cipher.unwrap(byArray, this.helper.getKeyAlgorithmName(algorithmIdentifier.getAlgorithm()), 3));
        } catch (InvalidKeyException invalidKeyException) {
            throw new OperatorException("key invalid in message.", invalidKeyException);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new OperatorException("can't find algorithm.", noSuchAlgorithmException);
        }
    }
}

