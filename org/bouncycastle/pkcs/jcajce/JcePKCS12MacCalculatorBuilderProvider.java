/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pkcs.jcajce;

import java.io.OutputStream;
import java.security.Provider;
import javax.crypto.Mac;
import javax.crypto.spec.PBEParameterSpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.PKCS12Key;
import org.bouncycastle.jcajce.io.MacOutputStream;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCS12MacCalculatorBuilder;
import org.bouncycastle.pkcs.PKCS12MacCalculatorBuilderProvider;

public class JcePKCS12MacCalculatorBuilderProvider
implements PKCS12MacCalculatorBuilderProvider {
    private JcaJceHelper helper = new DefaultJcaJceHelper();

    public JcePKCS12MacCalculatorBuilderProvider setProvider(Provider provider) {
        this.helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcePKCS12MacCalculatorBuilderProvider setProvider(String string) {
        this.helper = new NamedJcaJceHelper(string);
        return this;
    }

    public PKCS12MacCalculatorBuilder get(final AlgorithmIdentifier algorithmIdentifier) {
        return new PKCS12MacCalculatorBuilder(){

            public MacCalculator build(char[] cArray) throws OperatorCreationException {
                final PKCS12PBEParams pKCS12PBEParams = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
                try {
                    final ASN1ObjectIdentifier aSN1ObjectIdentifier = algorithmIdentifier.getAlgorithm();
                    final Mac mac = JcePKCS12MacCalculatorBuilderProvider.this.helper.createMac(aSN1ObjectIdentifier.getId());
                    PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(pKCS12PBEParams.getIV(), pKCS12PBEParams.getIterations().intValue());
                    final PKCS12Key pKCS12Key = new PKCS12Key(cArray);
                    mac.init(pKCS12Key, pBEParameterSpec);
                    return new MacCalculator(){

                        public AlgorithmIdentifier getAlgorithmIdentifier() {
                            return new AlgorithmIdentifier(aSN1ObjectIdentifier, pKCS12PBEParams);
                        }

                        public OutputStream getOutputStream() {
                            return new MacOutputStream(mac);
                        }

                        public byte[] getMac() {
                            return mac.doFinal();
                        }

                        public GenericKey getKey() {
                            return new GenericKey(this.getAlgorithmIdentifier(), pKCS12Key.getEncoded());
                        }
                    };
                } catch (Exception exception) {
                    throw new OperatorCreationException("unable to create MAC calculator: " + exception.getMessage(), exception);
                }
            }

            public AlgorithmIdentifier getDigestAlgorithmIdentifier() {
                return new AlgorithmIdentifier(algorithmIdentifier.getAlgorithm(), DERNull.INSTANCE);
            }
        };
    }
}

