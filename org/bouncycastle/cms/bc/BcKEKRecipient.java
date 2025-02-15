/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms.bc;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KEKRecipient;
import org.bouncycastle.cms.bc.CMSUtils;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.SymmetricKeyUnwrapper;
import org.bouncycastle.operator.bc.BcSymmetricKeyUnwrapper;

public abstract class BcKEKRecipient
implements KEKRecipient {
    private SymmetricKeyUnwrapper unwrapper;

    public BcKEKRecipient(BcSymmetricKeyUnwrapper bcSymmetricKeyUnwrapper) {
        this.unwrapper = bcSymmetricKeyUnwrapper;
    }

    protected CipherParameters extractSecretKey(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] byArray) throws CMSException {
        try {
            return CMSUtils.getBcKey(this.unwrapper.generateUnwrappedKey(algorithmIdentifier2, byArray));
        } catch (OperatorException operatorException) {
            throw new CMSException("exception unwrapping key: " + operatorException.getMessage(), operatorException);
        }
    }
}

