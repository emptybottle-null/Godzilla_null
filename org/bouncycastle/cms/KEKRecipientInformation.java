/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.IOException;
import org.bouncycastle.asn1.cms.KEKIdentifier;
import org.bouncycastle.asn1.cms.KEKRecipientInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.AuthAttributesProvider;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSecureReadable;
import org.bouncycastle.cms.KEKRecipient;
import org.bouncycastle.cms.KEKRecipientId;
import org.bouncycastle.cms.Recipient;
import org.bouncycastle.cms.RecipientInformation;
import org.bouncycastle.cms.RecipientOperator;

public class KEKRecipientInformation
extends RecipientInformation {
    private KEKRecipientInfo info;

    KEKRecipientInformation(KEKRecipientInfo kEKRecipientInfo, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        super(kEKRecipientInfo.getKeyEncryptionAlgorithm(), algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        this.info = kEKRecipientInfo;
        KEKIdentifier kEKIdentifier = kEKRecipientInfo.getKekid();
        this.rid = new KEKRecipientId(kEKIdentifier.getKeyIdentifier().getOctets());
    }

    protected RecipientOperator getRecipientOperator(Recipient recipient) throws CMSException, IOException {
        return ((KEKRecipient)recipient).getRecipientOperator(this.keyEncAlg, this.messageAlgorithm, this.info.getEncryptedKey().getOctets());
    }
}

