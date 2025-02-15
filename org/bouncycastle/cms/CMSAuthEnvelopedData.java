/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.cms.AuthEnvelopedData;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.EncryptedContentInfo;
import org.bouncycastle.asn1.cms.OriginatorInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSEnvelopedHelper;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSecureReadable;
import org.bouncycastle.cms.CMSUtils;
import org.bouncycastle.cms.RecipientInformationStore;

class CMSAuthEnvelopedData {
    RecipientInformationStore recipientInfoStore;
    ContentInfo contentInfo;
    private OriginatorInfo originator;
    private AlgorithmIdentifier authEncAlg;
    private ASN1Set authAttrs;
    private byte[] mac;
    private ASN1Set unauthAttrs;

    public CMSAuthEnvelopedData(byte[] byArray) throws CMSException {
        this(CMSUtils.readContentInfo(byArray));
    }

    public CMSAuthEnvelopedData(InputStream inputStream) throws CMSException {
        this(CMSUtils.readContentInfo(inputStream));
    }

    public CMSAuthEnvelopedData(ContentInfo contentInfo) throws CMSException {
        this.contentInfo = contentInfo;
        AuthEnvelopedData authEnvelopedData = AuthEnvelopedData.getInstance(contentInfo.getContent());
        this.originator = authEnvelopedData.getOriginatorInfo();
        ASN1Set aSN1Set = authEnvelopedData.getRecipientInfos();
        EncryptedContentInfo encryptedContentInfo = authEnvelopedData.getAuthEncryptedContentInfo();
        this.authEncAlg = encryptedContentInfo.getContentEncryptionAlgorithm();
        CMSSecureReadable cMSSecureReadable = new CMSSecureReadable(){

            public InputStream getInputStream() throws IOException, CMSException {
                return null;
            }
        };
        this.recipientInfoStore = CMSEnvelopedHelper.buildRecipientInformationStore(aSN1Set, this.authEncAlg, cMSSecureReadable);
        this.authAttrs = authEnvelopedData.getAuthAttrs();
        this.mac = authEnvelopedData.getMac().getOctets();
        this.unauthAttrs = authEnvelopedData.getUnauthAttrs();
    }
}

