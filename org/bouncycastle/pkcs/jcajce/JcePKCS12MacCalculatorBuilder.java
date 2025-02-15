/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pkcs.jcajce;

import java.io.OutputStream;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.Mac;
import javax.crypto.spec.PBEParameterSpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
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

public class JcePKCS12MacCalculatorBuilder
implements PKCS12MacCalculatorBuilder {
    private JcaJceHelper helper = new DefaultJcaJceHelper();
    private ASN1ObjectIdentifier algorithm;
    private SecureRandom random;
    private int saltLength;
    private int iterationCount = 1024;

    public JcePKCS12MacCalculatorBuilder() {
        this(OIWObjectIdentifiers.idSHA1);
    }

    public JcePKCS12MacCalculatorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.algorithm = aSN1ObjectIdentifier;
    }

    public JcePKCS12MacCalculatorBuilder setProvider(Provider provider) {
        this.helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcePKCS12MacCalculatorBuilder setProvider(String string) {
        this.helper = new NamedJcaJceHelper(string);
        return this;
    }

    public JcePKCS12MacCalculatorBuilder setIterationCount(int n) {
        this.iterationCount = n;
        return this;
    }

    public AlgorithmIdentifier getDigestAlgorithmIdentifier() {
        return new AlgorithmIdentifier(this.algorithm, DERNull.INSTANCE);
    }

    public MacCalculator build(char[] cArray) throws OperatorCreationException {
        if (this.random == null) {
            this.random = new SecureRandom();
        }
        try {
            final Mac mac = this.helper.createMac(this.algorithm.getId());
            this.saltLength = mac.getMacLength();
            final byte[] byArray = new byte[this.saltLength];
            this.random.nextBytes(byArray);
            PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(byArray, this.iterationCount);
            final PKCS12Key pKCS12Key = new PKCS12Key(cArray);
            mac.init(pKCS12Key, pBEParameterSpec);
            return new MacCalculator(){

                public AlgorithmIdentifier getAlgorithmIdentifier() {
                    return new AlgorithmIdentifier(JcePKCS12MacCalculatorBuilder.this.algorithm, new PKCS12PBEParams(byArray, JcePKCS12MacCalculatorBuilder.this.iterationCount));
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
}

