/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.jcajce;

import java.security.Key;
import java.security.Provider;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KEKRecipient;
import org.bouncycastle.cms.jcajce.DefaultJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.EnvelopedDataHelper;
import org.bouncycastle.cms.jcajce.NamedJcaJceExtHelper;
import org.bouncycastle.cms.jcajce.ProviderJcaJceExtHelper;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.SymmetricKeyUnwrapper;

public abstract class JceKEKRecipient
implements KEKRecipient {
    private SecretKey recipientKey;
    protected EnvelopedDataHelper helper;
    protected EnvelopedDataHelper contentHelper;
    protected boolean validateKeySize;

    public JceKEKRecipient(SecretKey secretKey) {
        this.contentHelper = this.helper = new EnvelopedDataHelper(new DefaultJcaJceExtHelper());
        this.validateKeySize = false;
        this.recipientKey = secretKey;
    }

    public JceKEKRecipient setProvider(Provider provider) {
        this.contentHelper = this.helper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JceKEKRecipient setProvider(String string) {
        this.contentHelper = this.helper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(string));
        return this;
    }

    public JceKEKRecipient setContentProvider(Provider provider) {
        this.contentHelper = new EnvelopedDataHelper(new ProviderJcaJceExtHelper(provider));
        return this;
    }

    public JceKEKRecipient setContentProvider(String string) {
        this.contentHelper = new EnvelopedDataHelper(new NamedJcaJceExtHelper(string));
        return this;
    }

    public JceKEKRecipient setKeySizeValidation(boolean bl) {
        this.validateKeySize = bl;
        return this;
    }

    protected Key extractSecretKey(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] byArray) throws CMSException {
        SymmetricKeyUnwrapper symmetricKeyUnwrapper = this.helper.createSymmetricUnwrapper(algorithmIdentifier, this.recipientKey);
        try {
            Key key = this.helper.getJceKey(algorithmIdentifier2.getAlgorithm(), symmetricKeyUnwrapper.generateUnwrappedKey(algorithmIdentifier2, byArray));
            if (this.validateKeySize) {
                this.helper.keySizeCheck(algorithmIdentifier2, key);
            }
            return key;
        } catch (OperatorException operatorException) {
            throw new CMSException("exception unwrapping key: " + operatorException.getMessage(), operatorException);
        }
    }
}

