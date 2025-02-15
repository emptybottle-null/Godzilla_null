/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.openssl.jcajce;

import java.io.IOException;
import java.io.InputStream;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.pkcs.EncryptionScheme;
import org.bouncycastle.asn1.pkcs.KeyDerivationFunc;
import org.bouncycastle.asn1.pkcs.PBEParameter;
import org.bouncycastle.asn1.pkcs.PBES2Parameters;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.CharToByteConverter;
import org.bouncycastle.jcajce.PBKDF1KeyWithParameters;
import org.bouncycastle.jcajce.PKCS12KeyWithParameters;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.jcajce.PEMUtilities;
import org.bouncycastle.operator.InputDecryptor;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Strings;

public class JceOpenSSLPKCS8DecryptorProviderBuilder {
    private JcaJceHelper helper = new DefaultJcaJceHelper();

    public JceOpenSSLPKCS8DecryptorProviderBuilder setProvider(String string) {
        this.helper = new NamedJcaJceHelper(string);
        return this;
    }

    public JceOpenSSLPKCS8DecryptorProviderBuilder setProvider(Provider provider) {
        this.helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public InputDecryptorProvider build(final char[] cArray) throws OperatorCreationException {
        return new InputDecryptorProvider(){

            public InputDecryptor get(final AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
                try {
                    Cipher cipher;
                    if (PEMUtilities.isPKCS5Scheme2(algorithmIdentifier.getAlgorithm())) {
                        PBES2Parameters pBES2Parameters = PBES2Parameters.getInstance(algorithmIdentifier.getParameters());
                        KeyDerivationFunc keyDerivationFunc = pBES2Parameters.getKeyDerivationFunc();
                        EncryptionScheme encryptionScheme = pBES2Parameters.getEncryptionScheme();
                        PBKDF2Params pBKDF2Params = (PBKDF2Params)keyDerivationFunc.getParameters();
                        int n = pBKDF2Params.getIterationCount().intValue();
                        byte[] byArray = pBKDF2Params.getSalt();
                        String string = encryptionScheme.getAlgorithm().getId();
                        SecretKey secretKey = PEMUtilities.isHmacSHA1(pBKDF2Params.getPrf()) ? PEMUtilities.generateSecretKeyForPKCS5Scheme2(JceOpenSSLPKCS8DecryptorProviderBuilder.this.helper, string, cArray, byArray, n) : PEMUtilities.generateSecretKeyForPKCS5Scheme2(JceOpenSSLPKCS8DecryptorProviderBuilder.this.helper, string, cArray, byArray, n, pBKDF2Params.getPrf());
                        cipher = JceOpenSSLPKCS8DecryptorProviderBuilder.this.helper.createCipher(string);
                        AlgorithmParameters algorithmParameters = JceOpenSSLPKCS8DecryptorProviderBuilder.this.helper.createAlgorithmParameters(string);
                        algorithmParameters.init(encryptionScheme.getParameters().toASN1Primitive().getEncoded());
                        cipher.init(2, (Key)secretKey, algorithmParameters);
                    } else if (PEMUtilities.isPKCS12(algorithmIdentifier.getAlgorithm())) {
                        PKCS12PBEParams pKCS12PBEParams = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
                        cipher = JceOpenSSLPKCS8DecryptorProviderBuilder.this.helper.createCipher(algorithmIdentifier.getAlgorithm().getId());
                        cipher.init(2, new PKCS12KeyWithParameters(cArray, pKCS12PBEParams.getIV(), pKCS12PBEParams.getIterations().intValue()));
                    } else if (PEMUtilities.isPKCS5Scheme1(algorithmIdentifier.getAlgorithm())) {
                        PBEParameter pBEParameter = PBEParameter.getInstance(algorithmIdentifier.getParameters());
                        cipher = JceOpenSSLPKCS8DecryptorProviderBuilder.this.helper.createCipher(algorithmIdentifier.getAlgorithm().getId());
                        cipher.init(2, new PBKDF1KeyWithParameters(cArray, new CharToByteConverter(){

                            public String getType() {
                                return "ASCII";
                            }

                            public byte[] convert(char[] cArray) {
                                return Strings.toByteArray(cArray);
                            }
                        }, pBEParameter.getSalt(), pBEParameter.getIterationCount().intValue()));
                    } else {
                        throw new PEMException("Unknown algorithm: " + algorithmIdentifier.getAlgorithm());
                    }
                    return new InputDecryptor(){

                        public AlgorithmIdentifier getAlgorithmIdentifier() {
                            return algorithmIdentifier;
                        }

                        public InputStream getInputStream(InputStream inputStream) {
                            return new CipherInputStream(inputStream, cipher);
                        }
                    };
                } catch (IOException iOException) {
                    throw new OperatorCreationException(algorithmIdentifier.getAlgorithm() + " not available: " + iOException.getMessage(), iOException);
                } catch (GeneralSecurityException generalSecurityException) {
                    throw new OperatorCreationException(algorithmIdentifier.getAlgorithm() + " not available: " + generalSecurityException.getMessage(), generalSecurityException);
                }
            }
        };
    }
}

