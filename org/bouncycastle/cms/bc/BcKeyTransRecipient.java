/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.bc;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KeyTransRecipient;
import org.bouncycastle.cms.bc.CMSUtils;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.bc.BcRSAAsymmetricKeyUnwrapper;

public abstract class BcKeyTransRecipient
implements KeyTransRecipient {
    private AsymmetricKeyParameter recipientKey;

    public BcKeyTransRecipient(AsymmetricKeyParameter asymmetricKeyParameter) {
        this.recipientKey = asymmetricKeyParameter;
    }

    protected CipherParameters extractSecretKey(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] byArray) throws CMSException {
        BcRSAAsymmetricKeyUnwrapper bcRSAAsymmetricKeyUnwrapper = new BcRSAAsymmetricKeyUnwrapper(algorithmIdentifier, this.recipientKey);
        try {
            return CMSUtils.getBcKey(bcRSAAsymmetricKeyUnwrapper.generateUnwrappedKey(algorithmIdentifier2, byArray));
        } catch (OperatorException operatorException) {
            throw new CMSException("exception unwrapping key: " + operatorException.getMessage(), operatorException);
        }
    }
}

