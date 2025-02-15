/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.jcajce.CMSUtils;
import org.bouncycastle.cms.jcajce.DefaultJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.EnvelopedDataHelper;
import org.bouncycastle.cms.jcajce.NamedJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.ProviderJcaJceExtHelper;

public class JceAlgorithmIdentifierConverter {
    private EnvelopedDataHelper helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
    private SecureRandom random;

    public JceAlgorithmIdentifierConverter setProvider(Provider provider) {
        this.helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JceAlgorithmIdentifierConverter setProvider(String string) {
        this.helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(string));
        return this;
    }

    public AlgorithmParameters getAlgorithmParameters(AlgorithmIdentifier algorithmIdentifier) throws CMSException {
        ASN1Encodable aSN1Encodable = algorithmIdentifier.getParameters();
        if (aSN1Encodable == null) {
            return null;
        }
        try {
            AlgorithmParameters algorithmParameters = this.helper.createAlgorithmParameters(algorithmIdentifier.getAlgorithm());
            CMSUtils.loadParameters(algorithmParameters, algorithmIdentifier.getParameters());
            return algorithmParameters;
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new CMSException("can't find parameters for algorithm", noSuchAlgorithmException);
        } catch (NoSuchProviderException noSuchProviderException) {
            throw new CMSException("can't find provider for algorithm", noSuchProviderException);
        }
    }
}

