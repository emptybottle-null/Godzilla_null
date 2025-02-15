/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.crmf.jcajce;

import java.io.OutputStream;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.crmf.CRMFException;
import org.bouncycastle.cert.crmf.jcajce.CRMFHelper;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.DefaultSecretKeySizeProvider;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.operator.SecretKeySizeProvider;
import org.bouncycastle.operator.jcajce.JceGenericKey;

public class JceCRMFEncryptorBuilder {
    private static final SecretKeySizeProvider KEY_SIZE_PROVIDER = DefaultSecretKeySizeProvider.INSTANCE;
    private final ASN1ObjectIdentifier encryptionOID;
    private final int keySize;
    private CRMFHelper helper = new CRMFHelper(new DefaultJcaJceHelper());
    private SecureRandom random;

    public JceCRMFEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this(aSN1ObjectIdentifier, -1);
    }

    public JceCRMFEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier, int n) {
        this.encryptionOID = aSN1ObjectIdentifier;
        this.keySize = n;
    }

    public JceCRMFEncryptorBuilder setProvider(Provider provider) {
        this.helper = new CRMFHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceCRMFEncryptorBuilder setProvider(String string) {
        this.helper = new CRMFHelper(new NamedJcaJceHelper(string));
        return this;
    }

    public JceCRMFEncryptorBuilder setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public OutputEncryptor build() throws CRMFException {
        return new CRMFOutputEncryptor(this.encryptionOID, this.keySize, this.random);
    }

    private class CRMFOutputEncryptor
    implements OutputEncryptor {
        private SecretKey encKey;
        private AlgorithmIdentifier algorithmIdentifier;
        private Cipher cipher;

        CRMFOutputEncryptor(ASN1ObjectIdentifier aSN1ObjectIdentifier, int n, SecureRandom secureRandom) throws CRMFException {
            KeyGenerator keyGenerator = JceCRMFEncryptorBuilder.this.helper.createKeyGenerator(aSN1ObjectIdentifier);
            if (secureRandom == null) {
                secureRandom = new SecureRandom();
            }
            if (n < 0) {
                n = KEY_SIZE_PROVIDER.getKeySize(aSN1ObjectIdentifier);
            }
            if (n < 0) {
                keyGenerator.init(secureRandom);
            } else {
                keyGenerator.init(n, secureRandom);
            }
            this.cipher = JceCRMFEncryptorBuilder.this.helper.createCipher(aSN1ObjectIdentifier);
            this.encKey = keyGenerator.generateKey();
            AlgorithmParameters algorithmParameters = JceCRMFEncryptorBuilder.this.helper.generateParameters(aSN1ObjectIdentifier, this.encKey, secureRandom);
            try {
                this.cipher.init(1, (Key)this.encKey, algorithmParameters, secureRandom);
            } catch (GeneralSecurityException generalSecurityException) {
                throw new CRMFException("unable to initialize cipher: " + generalSecurityException.getMessage(), generalSecurityException);
            }
            if (algorithmParameters == null) {
                algorithmParameters = this.cipher.getParameters();
            }
            this.algorithmIdentifier = JceCRMFEncryptorBuilder.this.helper.getAlgorithmIdentifier(aSN1ObjectIdentifier, algorithmParameters);
        }

        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.algorithmIdentifier;
        }

        public OutputStream getOutputStream(OutputStream outputStream) {
            return new CipherOutputStream(outputStream, this.cipher);
        }

        public GenericKey getKey() {
            return new JceGenericKey(this.algorithmIdentifier, this.encKey);
        }
    }
}

