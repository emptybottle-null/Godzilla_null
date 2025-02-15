/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.crmf.jcajce;

import java.io.InputStream;
import java.security.Key;
import java.security.PrivateKey;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.crmf.CRMFException;
import org.bouncycastle.cert.crmf.ValueDecryptorGenerator;
import org.bouncycastle.cert.crmf.jcajce.CRMFHelper;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.InputDecryptor;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.jcajce.JceAsymmetricKeyUnwrapper;

public class JceAsymmetricValueDecryptorGenerator
implements ValueDecryptorGenerator {
    private PrivateKey recipientKey;
    private CRMFHelper helper = new CRMFHelper(new DefaultJcaJceHelper());
    private Provider provider = null;
    private String providerName = null;

    public JceAsymmetricValueDecryptorGenerator(PrivateKey privateKey) {
        this.recipientKey = privateKey;
    }

    public JceAsymmetricValueDecryptorGenerator setProvider(Provider provider) {
        this.helper = new CRMFHelper(new ProviderJcaJceHelper(provider));
        this.provider = provider;
        this.providerName = null;
        return this;
    }

    public JceAsymmetricValueDecryptorGenerator setProvider(String string) {
        this.helper = new CRMFHelper(new NamedJcaJceHelper(string));
        this.provider = null;
        this.providerName = string;
        return this;
    }

    private Key extractSecretKey(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] byArray) throws CRMFException {
        try {
            JceAsymmetricKeyUnwrapper jceAsymmetricKeyUnwrapper = new JceAsymmetricKeyUnwrapper(algorithmIdentifier, this.recipientKey);
            if (this.provider != null) {
                jceAsymmetricKeyUnwrapper.setProvider(this.provider);
            }
            if (this.providerName != null) {
                jceAsymmetricKeyUnwrapper.setProvider(this.providerName);
            }
            return new SecretKeySpec((byte[])jceAsymmetricKeyUnwrapper.generateUnwrappedKey(algorithmIdentifier2, byArray).getRepresentation(), algorithmIdentifier2.getAlgorithm().getId());
        } catch (OperatorException operatorException) {
            throw new CRMFException("key invalid in message: " + operatorException.getMessage(), operatorException);
        }
    }

    public InputDecryptor getValueDecryptor(AlgorithmIdentifier algorithmIdentifier, final AlgorithmIdentifier algorithmIdentifier2, byte[] byArray) throws CRMFException {
        Key key = this.extractSecretKey(algorithmIdentifier, algorithmIdentifier2, byArray);
        final Cipher cipher = this.helper.createContentCipher(key, algorithmIdentifier2);
        return new InputDecryptor(){

            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return algorithmIdentifier2;
            }

            public InputStream getInputStream(InputStream inputStream) {
                return new CipherInputStream(inputStream, cipher);
            }
        };
    }
}

