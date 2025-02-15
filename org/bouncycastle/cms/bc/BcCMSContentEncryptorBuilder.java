/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.bc;

import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSAlgorithm;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.bc.EnvelopedDataHelper;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.io.CipherOutputStream;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.util.Integers;

public class BcCMSContentEncryptorBuilder {
    private static Map keySizes = new HashMap();
    private final ASN1ObjectIdentifier encryptionOID;
    private final int keySize;
    private EnvelopedDataHelper helper = new EnvelopedDataHelper();
    private SecureRandom random;

    private static int getKeySize(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Integer n = (Integer)keySizes.get(aSN1ObjectIdentifier);
        if (n != null) {
            return n;
        }
        return -1;
    }

    public BcCMSContentEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this(aSN1ObjectIdentifier, BcCMSContentEncryptorBuilder.getKeySize(aSN1ObjectIdentifier));
    }

    public BcCMSContentEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier, int n) {
        this.encryptionOID = aSN1ObjectIdentifier;
        this.keySize = n;
    }

    public BcCMSContentEncryptorBuilder setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public OutputEncryptor build() throws CMSException {
        return new CMSOutputEncryptor(this.encryptionOID, this.keySize, this.random);
    }

    static {
        keySizes.put(CMSAlgorithm.AES128_CBC, Integers.valueOf(128));
        keySizes.put(CMSAlgorithm.AES192_CBC, Integers.valueOf(192));
        keySizes.put(CMSAlgorithm.AES256_CBC, Integers.valueOf(256));
        keySizes.put(CMSAlgorithm.CAMELLIA128_CBC, Integers.valueOf(128));
        keySizes.put(CMSAlgorithm.CAMELLIA192_CBC, Integers.valueOf(192));
        keySizes.put(CMSAlgorithm.CAMELLIA256_CBC, Integers.valueOf(256));
    }

    private class CMSOutputEncryptor
    implements OutputEncryptor {
        private KeyParameter encKey;
        private AlgorithmIdentifier algorithmIdentifier;
        private Object cipher;

        CMSOutputEncryptor(ASN1ObjectIdentifier aSN1ObjectIdentifier, int n, SecureRandom secureRandom) throws CMSException {
            if (secureRandom == null) {
                secureRandom = new SecureRandom();
            }
            CipherKeyGenerator cipherKeyGenerator = BcCMSContentEncryptorBuilder.this.helper.createKeyGenerator(aSN1ObjectIdentifier, secureRandom);
            this.encKey = new KeyParameter(cipherKeyGenerator.generateKey());
            this.algorithmIdentifier = BcCMSContentEncryptorBuilder.this.helper.generateAlgorithmIdentifier(aSN1ObjectIdentifier, this.encKey, secureRandom);
            BcCMSContentEncryptorBuilder.this.helper;
            this.cipher = EnvelopedDataHelper.createContentCipher(true, this.encKey, this.algorithmIdentifier);
        }

        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.algorithmIdentifier;
        }

        public OutputStream getOutputStream(OutputStream outputStream) {
            if (this.cipher instanceof BufferedBlockCipher) {
                return new CipherOutputStream(outputStream, (BufferedBlockCipher)this.cipher);
            }
            return new CipherOutputStream(outputStream, (StreamCipher)this.cipher);
        }

        public GenericKey getKey() {
            return new GenericKey(this.algorithmIdentifier, this.encKey.getKey());
        }
    }
}

