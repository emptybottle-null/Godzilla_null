/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.crmf;

import java.io.IOException;
import org.bouncycastle.asn1.cms.EnvelopedData;
import org.bouncycastle.asn1.crmf.CRMFObjectIdentifiers;
import org.bouncycastle.asn1.crmf.EncKeyWithID;
import org.bouncycastle.asn1.crmf.EncryptedKey;
import org.bouncycastle.asn1.crmf.PKIArchiveOptions;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.cert.crmf.PKIArchiveControl;
import org.bouncycastle.cms.CMSEnvelopedData;
import org.bouncycastle.cms.CMSEnvelopedDataGenerator;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.RecipientInfoGenerator;
import org.bouncycastle.operator.OutputEncryptor;

public class PKIArchiveControlBuilder {
    private CMSEnvelopedDataGenerator envGen;
    private CMSProcessableByteArray keyContent;

    public PKIArchiveControlBuilder(PrivateKeyInfo privateKeyInfo, GeneralName generalName) {
        EncKeyWithID encKeyWithID = new EncKeyWithID(privateKeyInfo, generalName);
        try {
            this.keyContent = new CMSProcessableByteArray(CRMFObjectIdentifiers.id_ct_encKeyWithID, encKeyWithID.getEncoded());
        } catch (IOException iOException) {
            throw new IllegalStateException("unable to encode key and general name info");
        }
        this.envGen = new CMSEnvelopedDataGenerator();
    }

    public PKIArchiveControlBuilder addRecipientGenerator(RecipientInfoGenerator recipientInfoGenerator) {
        this.envGen.addRecipientInfoGenerator(recipientInfoGenerator);
        return this;
    }

    public PKIArchiveControl build(OutputEncryptor outputEncryptor) throws CMSException {
        CMSEnvelopedData cMSEnvelopedData = this.envGen.generate(this.keyContent, outputEncryptor);
        EnvelopedData envelopedData = EnvelopedData.getInstance(cMSEnvelopedData.toASN1Structure().getContent());
        return new PKIArchiveControl(new PKIArchiveOptions(new EncryptedKey(envelopedData)));
    }
}

