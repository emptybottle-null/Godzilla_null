/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pkcs.jcajce;

import java.io.InputStream;
import java.security.Key;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.cryptopro.GOST28147Parameters;
import org.bouncycastle.asn1.pkcs.PBEParameter;
import org.bouncycastle.asn1.pkcs.PBES2Parameters;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.PasswordConverter;
import org.bouncycastle.jcajce.PBKDF1Key;
import org.bouncycastle.jcajce.PKCS12KeyWithParameters;
import org.bouncycastle.jcajce.spec.GOST28147ParameterSpec;
import org.bouncycastle.jcajce.spec.PBKDF2KeySpec;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.DefaultSecretKeySizeProvider;
import org.bouncycastle.operator.InputDecryptor;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.SecretKeySizeProvider;

public class JcePKCSPBEInputDecryptorProviderBuilder {
    private JcaJceHelper helper = new DefaultJcaJceHelper();
    private boolean wrongPKCS12Zero = false;
    private SecretKeySizeProvider keySizeProvider = DefaultSecretKeySizeProvider.INSTANCE;

    public JcePKCSPBEInputDecryptorProviderBuilder setProvider(Provider provider) {
        this.helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcePKCSPBEInputDecryptorProviderBuilder setProvider(String string) {
        this.helper = new NamedJcaJceHelper(string);
        return this;
    }

    public JcePKCSPBEInputDecryptorProviderBuilder setTryWrongPKCS12Zero(boolean bl) {
        this.wrongPKCS12Zero = bl;
        return this;
    }

    public JcePKCSPBEInputDecryptorProviderBuilder setKeySizeProvider(SecretKeySizeProvider secretKeySizeProvider) {
        this.keySizeProvider = secretKeySizeProvider;
        return this;
    }

    public InputDecryptorProvider build(final char[] cArray) {
        return new InputDecryptorProvider(){
            private Cipher cipher;
            private AlgorithmIdentifier encryptionAlg;

            public InputDecryptor get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
                block7: {
                    ASN1ObjectIdentifier aSN1ObjectIdentifier = algorithmIdentifier.getAlgorithm();
                    try {
                        if (aSN1ObjectIdentifier.on(PKCSObjectIdentifiers.pkcs_12PbeIds)) {
                            PKCS12PBEParams pKCS12PBEParams = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
                            this.cipher = JcePKCSPBEInputDecryptorProviderBuilder.this.helper.createCipher(aSN1ObjectIdentifier.getId());
                            this.cipher.init(2, new PKCS12KeyWithParameters(cArray, JcePKCSPBEInputDecryptorProviderBuilder.this.wrongPKCS12Zero, pKCS12PBEParams.getIV(), pKCS12PBEParams.getIterations().intValue()));
                            this.encryptionAlg = algorithmIdentifier;
                            break block7;
                        }
                        if (aSN1ObjectIdentifier.equals(PKCSObjectIdentifiers.id_PBES2)) {
                            PBES2Parameters pBES2Parameters = PBES2Parameters.getInstance(algorithmIdentifier.getParameters());
                            PBKDF2Params pBKDF2Params = PBKDF2Params.getInstance(pBES2Parameters.getKeyDerivationFunc().getParameters());
                            AlgorithmIdentifier algorithmIdentifier2 = AlgorithmIdentifier.getInstance(pBES2Parameters.getEncryptionScheme());
                            SecretKeyFactory secretKeyFactory = JcePKCSPBEInputDecryptorProviderBuilder.this.helper.createSecretKeyFactory(pBES2Parameters.getKeyDerivationFunc().getAlgorithm().getId());
                            SecretKey secretKey = pBKDF2Params.isDefaultPrf() ? secretKeyFactory.generateSecret(new PBEKeySpec(cArray, pBKDF2Params.getSalt(), pBKDF2Params.getIterationCount().intValue(), JcePKCSPBEInputDecryptorProviderBuilder.this.keySizeProvider.getKeySize(algorithmIdentifier2))) : secretKeyFactory.generateSecret(new PBKDF2KeySpec(cArray, pBKDF2Params.getSalt(), pBKDF2Params.getIterationCount().intValue(), JcePKCSPBEInputDecryptorProviderBuilder.this.keySizeProvider.getKeySize(algorithmIdentifier2), pBKDF2Params.getPrf()));
                            this.cipher = JcePKCSPBEInputDecryptorProviderBuilder.this.helper.createCipher(pBES2Parameters.getEncryptionScheme().getAlgorithm().getId());
                            this.encryptionAlg = AlgorithmIdentifier.getInstance(pBES2Parameters.getEncryptionScheme());
                            ASN1Encodable aSN1Encodable = pBES2Parameters.getEncryptionScheme().getParameters();
                            if (aSN1Encodable instanceof ASN1OctetString) {
                                this.cipher.init(2, (Key)secretKey, new IvParameterSpec(ASN1OctetString.getInstance(aSN1Encodable).getOctets()));
                            } else {
                                GOST28147Parameters gOST28147Parameters = GOST28147Parameters.getInstance(aSN1Encodable);
                                this.cipher.init(2, (Key)secretKey, new GOST28147ParameterSpec(gOST28147Parameters.getEncryptionParamSet(), gOST28147Parameters.getIV()));
                            }
                            break block7;
                        }
                        if (aSN1ObjectIdentifier.equals(PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC) || aSN1ObjectIdentifier.equals(PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC)) {
                            PBEParameter pBEParameter = PBEParameter.getInstance(algorithmIdentifier.getParameters());
                            this.cipher = JcePKCSPBEInputDecryptorProviderBuilder.this.helper.createCipher(aSN1ObjectIdentifier.getId());
                            this.cipher.init(2, (Key)new PBKDF1Key(cArray, PasswordConverter.ASCII), new PBEParameterSpec(pBEParameter.getSalt(), pBEParameter.getIterationCount().intValue()));
                            break block7;
                        }
                        throw new OperatorCreationException("unable to create InputDecryptor: algorithm " + aSN1ObjectIdentifier + " unknown.");
                    } catch (Exception exception) {
                        throw new OperatorCreationException("unable to create InputDecryptor: " + exception.getMessage(), exception);
                    }
                }
                return new InputDecryptor(){

                    public AlgorithmIdentifier getAlgorithmIdentifier() {
                        return encryptionAlg;
                    }

                    public InputStream getInputStream(InputStream inputStream) {
                        return new CipherInputStream(inputStream, cipher);
                    }
                };
            }
        };
    }
}

