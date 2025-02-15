/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import java.io.OutputStream;
import java.security.AlgorithmParameters;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.jcajce.DefaultJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.EnvelopedDataHelper;
import org.bouncycastle.cms.jcajce.NamedJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.ProviderJcaJceExtHelper;
import org.bouncycastle.jcajce.io.MacOutputStream;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.operator.jcajce.JceGenericKey;

public class JceCMSMacCalculatorBuilder {
    private final ASN1ObjectIdentifier macOID;
    private final int keySize;
    private EnvelopedDataHelper helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
    private AlgorithmParameters algorithmParameters;
    private SecureRandom random;

    public JceCMSMacCalculatorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this(aSN1ObjectIdentifier, -1);
    }

    public JceCMSMacCalculatorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier, int n) {
        this.macOID = aSN1ObjectIdentifier;
        this.keySize = n;
    }

    public JceCMSMacCalculatorBuilder setProvider(Provider provider) {
        this.helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JceCMSMacCalculatorBuilder setProvider(String string) {
        this.helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(string));
        return this;
    }

    public JceCMSMacCalculatorBuilder setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }

    public JceCMSMacCalculatorBuilder setAlgorithmParameters(AlgorithmParameters algorithmParameters) {
        this.algorithmParameters = algorithmParameters;
        return this;
    }

    public MacCalculator build() throws CMSException {
        return new CMSMacCalculator(this.macOID, this.keySize, this.algorithmParameters, this.random);
    }

    private class CMSMacCalculator
    implements MacCalculator {
        private SecretKey encKey;
        private AlgorithmIdentifier algorithmIdentifier;
        private Mac mac;

        CMSMacCalculator(ASN1ObjectIdentifier aSN1ObjectIdentifier, int n, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws CMSException {
            KeyGenerator keyGenerator = JceCMSMacCalculatorBuilder.this.helper.createKeyGenerator(aSN1ObjectIdentifier);
            if (secureRandom == null) {
                secureRandom = new SecureRandom();
            }
            if (n < 0) {
                keyGenerator.init(secureRandom);
            } else {
                keyGenerator.init(n, secureRandom);
            }
            this.encKey = keyGenerator.generateKey();
            if (algorithmParameters == null) {
                algorithmParameters = JceCMSMacCalculatorBuilder.this.helper.generateParameters(aSN1ObjectIdentifier, this.encKey, secureRandom);
            }
            this.algorithmIdentifier = JceCMSMacCalculatorBuilder.this.helper.getAlgorithmIdentifier(aSN1ObjectIdentifier, algorithmParameters);
            this.mac = JceCMSMacCalculatorBuilder.this.helper.createContentMac(this.encKey, this.algorithmIdentifier);
        }

        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.algorithmIdentifier;
        }

        public OutputStream getOutputStream() {
            return new MacOutputStream(this.mac);
        }

        public byte[] getMac() {
            return this.mac.doFinal();
        }

        public GenericKey getKey() {
            return new JceGenericKey(this.algorithmIdentifier, this.encKey);
        }
    }
}

