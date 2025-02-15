/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pkcs;

import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.ContentInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.SafeBag;
import org.bouncycastle.cms.CMSEncryptedData;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.pkcs.PKCS12SafeBag;
import org.bouncycastle.pkcs.PKCSException;

public class PKCS12SafeBagFactory {
    private ASN1Sequence safeBagSeq;

    public PKCS12SafeBagFactory(ContentInfo contentInfo) {
        if (contentInfo.getContentType().equals(PKCSObjectIdentifiers.encryptedData)) {
            throw new IllegalArgumentException("encryptedData requires constructor with decryptor.");
        }
        this.safeBagSeq = ASN1Sequence.getInstance(ASN1OctetString.getInstance(contentInfo.getContent()).getOctets());
    }

    public PKCS12SafeBagFactory(ContentInfo contentInfo, InputDecryptorProvider inputDecryptorProvider) throws PKCSException {
        if (contentInfo.getContentType().equals(PKCSObjectIdentifiers.encryptedData)) {
            CMSEncryptedData cMSEncryptedData = new CMSEncryptedData(org.bouncycastle.asn1.cms.ContentInfo.getInstance(contentInfo));
            try {
                this.safeBagSeq = ASN1Sequence.getInstance(cMSEncryptedData.getContent(inputDecryptorProvider));
            } catch (CMSException cMSException) {
                throw new PKCSException("unable to extract data: " + cMSException.getMessage(), cMSException);
            }
            return;
        }
        throw new IllegalArgumentException("encryptedData requires constructor with decryptor.");
    }

    public PKCS12SafeBag[] getSafeBags() {
        PKCS12SafeBag[] pKCS12SafeBagArray = new PKCS12SafeBag[this.safeBagSeq.size()];
        for (int i = 0; i != this.safeBagSeq.size(); ++i) {
            pKCS12SafeBagArray[i] = new PKCS12SafeBag(SafeBag.getInstance(this.safeBagSeq.getObjectAt(i)));
        }
        return pKCS12SafeBagArray;
    }
}

