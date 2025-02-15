/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.cms.IssuerAndSerialNumber;
import org.bouncycastle.asn1.cms.KeyTransRecipientInfo;
import org.bouncycastle.asn1.cms.RecipientIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.AuthAttributesProvider;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSecureReadable;
import org.bouncycastle.cms.KeyTransRecipient;
import org.bouncycastle.cms.KeyTransRecipientId;
import org.bouncycastle.cms.Recipient;
import org.bouncycastle.cms.RecipientInformation;
import org.bouncycastle.cms.RecipientOperator;

public class KeyTransRecipientInformation
extends RecipientInformation {
    private KeyTransRecipientInfo info;

    KeyTransRecipientInformation(KeyTransRecipientInfo keyTransRecipientInfo, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        super(keyTransRecipientInfo.getKeyEncryptionAlgorithm(), algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        this.info = keyTransRecipientInfo;
        RecipientIdentifier recipientIdentifier = keyTransRecipientInfo.getRecipientIdentifier();
        if (recipientIdentifier.isTagged()) {
            ASN1OctetString aSN1OctetString = ASN1OctetString.getInstance(recipientIdentifier.getId());
            this.rid = new KeyTransRecipientId(aSN1OctetString.getOctets());
        } else {
            IssuerAndSerialNumber issuerAndSerialNumber = IssuerAndSerialNumber.getInstance(recipientIdentifier.getId());
            this.rid = new KeyTransRecipientId(issuerAndSerialNumber.getName(), issuerAndSerialNumber.getSerialNumber().getValue());
        }
    }

    protected RecipientOperator getRecipientOperator(Recipient recipient) throws CMSException {
        return ((KeyTransRecipient)recipient).getRecipientOperator(this.keyEncAlg, this.messageAlgorithm, this.info.getEncryptedKey().getOctets());
    }
}

