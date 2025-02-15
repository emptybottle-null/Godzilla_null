/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cms.KEKIdentifier;
import org.bouncycastle.asn1.cms.KEKRecipientInfo;
import org.bouncycastle.asn1.cms.RecipientInfo;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.RecipientInfoGenerator;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.SymmetricKeyWrapper;

public abstract class KEKRecipientInfoGenerator
implements RecipientInfoGenerator {
    private final KEKIdentifier kekIdentifier;
    protected final SymmetricKeyWrapper wrapper;

    protected KEKRecipientInfoGenerator(KEKIdentifier kEKIdentifier, SymmetricKeyWrapper symmetricKeyWrapper) {
        this.kekIdentifier = kEKIdentifier;
        this.wrapper = symmetricKeyWrapper;
    }

    public final RecipientInfo generate(GenericKey genericKey) throws CMSException {
        try {
            DEROctetString dEROctetString = new DEROctetString(this.wrapper.generateWrappedKey(genericKey));
            return new RecipientInfo(new KEKRecipientInfo(this.kekIdentifier, this.wrapper.getAlgorithmIdentifier(), dEROctetString));
        } catch (OperatorException operatorException) {
            throw new CMSException("exception wrapping content key: " + operatorException.getMessage(), operatorException);
        }
    }
}

