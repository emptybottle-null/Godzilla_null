/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.openssl.jcajce;

import java.io.IOException;
import java.io.OutputStream;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.EncryptionScheme;
import org.bouncycastle.asn1.pkcs.KeyDerivationFunc;
import org.bouncycastle.asn1.pkcs.PBES2Parameters;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.PKCS12KeyWithParameters;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.openssl.jcajce.PEMUtilities;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.operator.jcajce.JceGenericKey;

public class JceOpenSSLPKCS8EncryptorBuilder {
    public static final String AES_128_CBC = NISTObjectIdentifiers.id_aes128_CBC.getId();
    public static final String AES_192_CBC = NISTObjectIdentifiers.id_aes192_CBC.getId();
    public static final String AES_256_CBC = NISTObjectIdentifiers.id_aes256_CBC.getId();
    public static final String DES3_CBC = PKCSObjectIdentifiers.des_EDE3_CBC.getId();
    public static final String PBE_SHA1_RC4_128 = PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4.getId();
    public static final String PBE_SHA1_RC4_40 = PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC4.getId();
    public static final String PBE_SHA1_3DES = PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC.getId();
    public static final String PBE_SHA1_2DES = PKCSObjectIdentifiers.pbeWithSHAAnd2_KeyTripleDES_CBC.getId();
    public static final String PBE_SHA1_RC2_128 = PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC2_CBC.getId();
    public static final String PBE_SHA1_RC2_40 = PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC.getId();
    private JcaJceHelper helper = new DefaultJcaJceHelper();
    private AlgorithmParameters params;
    private ASN1ObjectIdentifier algOID;
    byte[] salt;
    int iterationCount;
    private Cipher cipher;
    private SecureRandom random;
    private AlgorithmParameterGenerator paramGen;
    private char[] password;
    private SecretKey key;
    private AlgorithmIdentifier prf = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA1, DERNull.INSTANCE);

    public JceOpenSSLPKCS8EncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.algOID = aSN1ObjectIdentifier;
        this.iterationCount = 2048;
    }

    public JceOpenSSLPKCS8EncryptorBuilder setRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public JceOpenSSLPKCS8EncryptorBuilder setPasssword(char[] cArray) {
        this.password = cArray;
        return this;
    }

    public JceOpenSSLPKCS8EncryptorBuilder setPRF(AlgorithmIdentifier algorithmIdentifier) {
        this.prf = algorithmIdentifier;
        return this;
    }

    public JceOpenSSLPKCS8EncryptorBuilder setIterationCount(int n) {
        this.iterationCount = n;
        return this;
    }

    public JceOpenSSLPKCS8EncryptorBuilder setProvider(String string) {
        this.helper = new NamedJcaJceHelper(string);
        return this;
    }

    public JceOpenSSLPKCS8EncryptorBuilder setProvider(Provider provider) {
        this.helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public OutputEncryptor build() throws OperatorCreationException {
        AlgorithmIdentifier algorithmIdentifier;
        if (this.random == null) {
            this.random = new SecureRandom();
        }
        try {
            this.cipher = this.helper.createCipher(this.algOID.getId());
            if (PEMUtilities.isPKCS5Scheme2(this.algOID)) {
                this.paramGen = this.helper.createAlgorithmParameterGenerator(this.algOID.getId());
            }
        } catch (GeneralSecurityException generalSecurityException) {
            throw new OperatorCreationException(this.algOID + " not available: " + generalSecurityException.getMessage(), generalSecurityException);
        }
        if (PEMUtilities.isPKCS5Scheme2(this.algOID)) {
            this.salt = new byte[PEMUtilities.getSaltSize(this.prf.getAlgorithm())];
            this.random.nextBytes(this.salt);
            this.params = this.paramGen.generateParameters();
            try {
                EncryptionScheme encryptionScheme = new EncryptionScheme(this.algOID, ASN1Primitive.fromByteArray(this.params.getEncoded()));
                KeyDerivationFunc keyDerivationFunc = new KeyDerivationFunc(PKCSObjectIdentifiers.id_PBKDF2, new PBKDF2Params(this.salt, this.iterationCount, this.prf));
                ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                aSN1EncodableVector.add(keyDerivationFunc);
                aSN1EncodableVector.add(encryptionScheme);
                algorithmIdentifier = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_PBES2, PBES2Parameters.getInstance(new DERSequence(aSN1EncodableVector)));
            } catch (IOException iOException) {
                throw new OperatorCreationException(iOException.getMessage(), iOException);
            }
            try {
                this.key = PEMUtilities.isHmacSHA1(this.prf) ? PEMUtilities.generateSecretKeyForPKCS5Scheme2(this.helper, this.algOID.getId(), this.password, this.salt, this.iterationCount) : PEMUtilities.generateSecretKeyForPKCS5Scheme2(this.helper, this.algOID.getId(), this.password, this.salt, this.iterationCount, this.prf);
                this.cipher.init(1, (Key)this.key, this.params);
            } catch (GeneralSecurityException generalSecurityException) {
                throw new OperatorCreationException(generalSecurityException.getMessage(), generalSecurityException);
            }
        }
        if (PEMUtilities.isPKCS12(this.algOID)) {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            this.salt = new byte[20];
            this.random.nextBytes(this.salt);
            aSN1EncodableVector.add(new DEROctetString(this.salt));
            aSN1EncodableVector.add(new ASN1Integer(this.iterationCount));
            algorithmIdentifier = new AlgorithmIdentifier(this.algOID, PKCS12PBEParams.getInstance(new DERSequence(aSN1EncodableVector)));
            try {
                this.cipher.init(1, new PKCS12KeyWithParameters(this.password, this.salt, this.iterationCount));
            } catch (GeneralSecurityException generalSecurityException) {
                throw new OperatorCreationException(generalSecurityException.getMessage(), generalSecurityException);
            }
        } else {
            throw new OperatorCreationException("unknown algorithm: " + this.algOID, null);
        }
        return new OutputEncryptor(){

            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return algorithmIdentifier;
            }

            public OutputStream getOutputStream(OutputStream outputStream) {
                return new CipherOutputStream(outputStream, JceOpenSSLPKCS8EncryptorBuilder.this.cipher);
            }

            public GenericKey getKey() {
                return new JceGenericKey(algorithmIdentifier, JceOpenSSLPKCS8EncryptorBuilder.this.key);
            }
        };
    }
}

