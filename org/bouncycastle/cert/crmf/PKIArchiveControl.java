/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.crmf;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.EnvelopedData;
import org.bouncycastle.asn1.crmf.CRMFObjectIdentifiers;
import org.bouncycastle.asn1.crmf.EncryptedKey;
import org.bouncycastle.asn1.crmf.PKIArchiveOptions;
import org.bouncycastle.cert.crmf.CRMFException;
import org.bouncycastle.cert.crmf.Control;
import org.bouncycastle.cms.CMSEnvelopedData;
import org.bouncycastle.cms.CMSException;

public class PKIArchiveControl
implements Control {
    public static final int encryptedPrivKey = 0;
    public static final int keyGenParameters = 1;
    public static final int archiveRemGenPrivKey = 2;
    private static final ASN1ObjectIdentifier type = CRMFObjectIdentifiers.id_regCtrl_pkiArchiveOptions;
    private final PKIArchiveOptions pkiArchiveOptions;

    public PKIArchiveControl(PKIArchiveOptions pKIArchiveOptions) {
        this.pkiArchiveOptions = pKIArchiveOptions;
    }

    public ASN1ObjectIdentifier getType() {
        return type;
    }

    public ASN1Encodable getValue() {
        return this.pkiArchiveOptions;
    }

    public int getArchiveType() {
        return this.pkiArchiveOptions.getType();
    }

    public boolean isEnvelopedData() {
        EncryptedKey encryptedKey = EncryptedKey.getInstance(this.pkiArchiveOptions.getValue());
        return !encryptedKey.isEncryptedValue();
    }

    public CMSEnvelopedData getEnvelopedData() throws CRMFException {
        try {
            EncryptedKey encryptedKey = EncryptedKey.getInstance(this.pkiArchiveOptions.getValue());
            EnvelopedData envelopedData = EnvelopedData.getInstance(encryptedKey.getValue());
            return new CMSEnvelopedData(new ContentInfo(CMSObjectIdentifiers.envelopedData, envelopedData));
        } catch (CMSException cMSException) {
            throw new CRMFException("CMS parsing error: " + cMSException.getMessage(), cMSException.getCause());
        } catch (Exception exception) {
            throw new CRMFException("CRMF parsing error: " + exception.getMessage(), exception);
        }
    }
}

