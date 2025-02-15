/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

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
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.jcajce.DefaultJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.EnvelopedDataHelper;
import org.bouncycastle.cms.jcajce.NamedJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.ProviderJcaJceExtHelper;
import org.bouncycastle.operator.DefaultSecretKeySizeProvider;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.operator.SecretKeySizeProvider;
import org.bouncycastle.operator.jcajce.JceGenericKey;

public class JceCMSContentEncryptorBuilder {
    private static final SecretKeySizeProvider KEY_SIZE_PROVIDER = DefaultSecretKeySizeProvider.INSTANCE;
    private final ASN1ObjectIdentifier encryptionOID;
    private final int keySize;
    private EnvelopedDataHelper helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
    private SecureRandom random;
    private AlgorithmParameters algorithmParameters;

    public JceCMSContentEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this(aSN1ObjectIdentifier, KEY_SIZE_PROVIDER.getKeySize(aSN1ObjectIdentifier));
    }

    public JceCMSContentEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier, int n) {
        this.encryptionOID = aSN1ObjectIdentifier;
        int n2 = KEY_SIZE_PROVIDER.getKeySize(aSN1ObjectIdentifier);
        if (aSN1ObjectIdentifier.equals(PKCSObjectIdentifiers.des_EDE3_CBC)) {
            if (n != 168 && n != n2) {
                throw new IllegalArgumentException("incorrect keySize for encryptionOID passed to builder.");
            }
            this.keySize = 168;
        } else if (aSN1ObjectIdentifier.equals(OIWObjectIdentifiers.desCBC)) {
            if (n != 56 && n != n2) {
                throw new IllegalArgumentException("incorrect keySize for encryptionOID passed to builder.");
            }
            this.keySize = 56;
        } else {
            if (n2 > 0 && n2 != n) {
                throw new IllegalArgumentException("incorrect keySize for encryptionOID passed to builder.");
            }
            this.keySize = n;
        }
    }

    public JceCMSContentEncryptorBuilder setProvider(Provider provider) {
        this.helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JceCMSContentEncryptorBuilder setProvider(String string) {
        this.helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(string));
        return this;
    }

    public JceCMSContentEncryptorBuilder setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public JceCMSContentEncryptorBuilder setAlgorithmParameters(AlgorithmParameters algorithmParameters) {
        this.algorithmParameters = algorithmParameters;
        return this;
    }

    public OutputEncryptor build() throws CMSException {
        return new CMSOutputEncryptor(this.encryptionOID, this.keySize, this.algorithmParameters, this.random);
    }

    private class CMSOutputEncryptor
    implements OutputEncryptor {
        private SecretKey encKey;
        private AlgorithmIdentifier algorithmIdentifier;
        private Cipher cipher;

        CMSOutputEncryptor(ASN1ObjectIdentifier aSN1ObjectIdentifier, int n, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws CMSException {
            KeyGenerator keyGenerator = JceCMSContentEncryptorBuilder.this.helper.createKeyGenerator(aSN1ObjectIdentifier);
            if (secureRandom == null) {
                secureRandom = new SecureRandom();
            }
            if (n < 0) {
                keyGenerator.init(secureRandom);
            } else {
                keyGenerator.init(n, secureRandom);
            }
            this.cipher = JceCMSContentEncryptorBuilder.this.helper.createCipher(aSN1ObjectIdentifier);
            this.encKey = keyGenerator.generateKey();
            if (algorithmParameters == null) {
                algorithmParameters = JceCMSContentEncryptorBuilder.this.helper.generateParameters(aSN1ObjectIdentifier, this.encKey, secureRandom);
            }
            try {
                this.cipher.init(1, (Key)this.encKey, algorithmParameters, secureRandom);
            } catch (GeneralSecurityException generalSecurityException) {
                throw new CMSException("unable to initialize cipher: " + generalSecurityException.getMessage(), generalSecurityException);
            }
            if (algorithmParameters == null) {
                algorithmParameters = this.cipher.getParameters();
            }
            this.algorithmIdentifier = JceCMSContentEncryptorBuilder.this.helper.getAlgorithmIdentifier(aSN1ObjectIdentifier, algorithmParameters);
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

