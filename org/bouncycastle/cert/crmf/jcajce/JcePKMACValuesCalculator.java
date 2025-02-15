/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.crmf.jcajce;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.Provider;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.crmf.CRMFException;
import org.bouncycastle.cert.crmf.PKMACValuesCalculator;
import org.bouncycastle.cert.crmf.jcajce.CRMFHelper;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;

public class JcePKMACValuesCalculator
implements PKMACValuesCalculator {
    private MessageDigest digest;
    private Mac mac;
    private CRMFHelper helper = new CRMFHelper(new DefaultJcaJceHelper());

    public JcePKMACValuesCalculator setProvider(Provider provider) {
        this.helper = new CRMFHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JcePKMACValuesCalculator setProvider(String string) {
        this.helper = new CRMFHelper(new NamedJcaJceHelper(string));
        return this;
    }

    public void setup(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) throws CRMFException {
        this.digest = this.helper.createDigest(algorithmIdentifier.getAlgorithm());
        this.mac = this.helper.createMac(algorithmIdentifier2.getAlgorithm());
    }

    public byte[] calculateDigest(byte[] byArray) {
        return this.digest.digest(byArray);
    }

    public byte[] calculateMac(byte[] byArray, byte[] byArray2) throws CRMFException {
        try {
            this.mac.init(new SecretKeySpec(byArray, this.mac.getAlgorithm()));
            return this.mac.doFinal(byArray2);
        } catch (GeneralSecurityException generalSecurityException) {
            throw new CRMFException("failure in setup: " + generalSecurityException.getMessage(), generalSecurityException);
        }
    }
}

