/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.cms.KeyTransRecipientInfo;
import org.bouncycastle.asn1.cms.RecipientIdentifier;
import org.bouncycastle.asn1.cms.RecipientInfo;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.RecipientInfoGenerator;
import org.bouncycastle.operator.AsymmetricKeyWrapper;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;

public abstract class KeyTransRecipientInfoGenerator
implements RecipientInfoGenerator {
    protected final AsymmetricKeyWrapper wrapper;
    private IssuerAndSerialNumber issuerAndSerial;
    private byte[] subjectKeyIdentifier;

    protected KeyTransRecipientInfoGenerator(IssuerAndSerialNumber issuerAndSerialNumber, AsymmetricKeyWrapper asymmetricKeyWrapper) {
        this.issuerAndSerial = issuerAndSerialNumber;
        this.wrapper = asymmetricKeyWrapper;
    }

    protected KeyTransRecipientInfoGenerator(byte[] byArray, AsymmetricKeyWrapper asymmetricKeyWrapper) {
        this.subjectKeyIdentifier = byArray;
        this.wrapper = asymmetricKeyWrapper;
    }

    public final RecipientInfo generate(GenericKey genericKey) throws CMSException {
        byte[] byArray;
        try {
            byArray = this.wrapper.generateWrappedKey(genericKey);
        } catch (OperatorException operatorException) {
            throw new CMSException("exception wrapping content key: " + operatorException.getMessage(), operatorException);
        }
        RecipientIdentifier recipientIdentifier = this.issuerAndSerial != null ? new RecipientIdentifier(this.issuerAndSerial) : new RecipientIdentifier(new DEROctetString(this.subjectKeyIdentifier));
        return new RecipientInfo(new KeyTransRecipientInfo(recipientIdentifier, this.wrapper.getAlgorithmIdentifier(), new DEROctetString(byArray)));
    }
}

