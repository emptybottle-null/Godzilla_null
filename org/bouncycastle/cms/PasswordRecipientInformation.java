/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.cms.PasswordRecipientInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.AuthAttributesProvider;
import org.bouncycastle.cms.CMSAlgorithm;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSecureReadable;
import org.bouncycastle.cms.PasswordRecipient;
import org.bouncycastle.cms.PasswordRecipientId;
import org.bouncycastle.cms.Recipient;
import org.bouncycastle.cms.RecipientInformation;
import org.bouncycastle.cms.RecipientOperator;
import org.bouncycastle.util.Integers;

public class PasswordRecipientInformation
extends RecipientInformation {
    static Map KEYSIZES = new HashMap();
    static Map BLOCKSIZES = new HashMap();
    private PasswordRecipientInfo info;

    PasswordRecipientInformation(PasswordRecipientInfo passwordRecipientInfo, AlgorithmIdentifier algorithmIdentifier, CMSSecureReadable cMSSecureReadable, AuthAttributesProvider authAttributesProvider) {
        super(passwordRecipientInfo.getKeyEncryptionAlgorithm(), algorithmIdentifier, cMSSecureReadable, authAttributesProvider);
        this.info = passwordRecipientInfo;
        this.rid = new PasswordRecipientId();
    }

    public String getKeyDerivationAlgOID() {
        if (this.info.getKeyDerivationAlgorithm() != null) {
            return this.info.getKeyDerivationAlgorithm().getAlgorithm().getId();
        }
        return null;
    }

    public byte[] getKeyDerivationAlgParams() {
        try {
            ASN1Encodable aSN1Encodable;
            if (this.info.getKeyDerivationAlgorithm() != null && (aSN1Encodable = this.info.getKeyDerivationAlgorithm().getParameters()) != null) {
                return aSN1Encodable.toASN1Primitive().getEncoded();
            }
            return null;
        } catch (Exception exception) {
            throw new RuntimeException("exception getting encryption parameters " + exception);
        }
    }

    public AlgorithmIdentifier getKeyDerivationAlgorithm() {
        return this.info.getKeyDerivationAlgorithm();
    }

    protected RecipientOperator getRecipientOperator(Recipient recipient) throws CMSException, IOException {
        PasswordRecipient passwordRecipient = (PasswordRecipient)recipient;
        AlgorithmIdentifier algorithmIdentifier = AlgorithmIdentifier.getInstance(this.info.getKeyEncryptionAlgorithm());
        AlgorithmIdentifier algorithmIdentifier2 = AlgorithmIdentifier.getInstance(algorithmIdentifier.getParameters());
        int n = (Integer)KEYSIZES.get(algorithmIdentifier2.getAlgorithm());
        byte[] byArray = passwordRecipient.calculateDerivedKey(passwordRecipient.getPasswordConversionScheme(), this.getKeyDerivationAlgorithm(), n);
        return passwordRecipient.getRecipientOperator(algorithmIdentifier2, this.messageAlgorithm, byArray, this.info.getEncryptedKey().getOctets());
    }

    static {
        BLOCKSIZES.put(CMSAlgorithm.DES_EDE3_CBC, Integers.valueOf(8));
        BLOCKSIZES.put(CMSAlgorithm.AES128_CBC, Integers.valueOf(16));
        BLOCKSIZES.put(CMSAlgorithm.AES192_CBC, Integers.valueOf(16));
        BLOCKSIZES.put(CMSAlgorithm.AES256_CBC, Integers.valueOf(16));
        KEYSIZES.put(CMSAlgorithm.DES_EDE3_CBC, Integers.valueOf(192));
        KEYSIZES.put(CMSAlgorithm.AES128_CBC, Integers.valueOf(128));
        KEYSIZES.put(CMSAlgorithm.AES192_CBC, Integers.valueOf(192));
        KEYSIZES.put(CMSAlgorithm.AES256_CBC, Integers.valueOf(256));
    }
}

