/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.dvcs;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.SignedData;
import org.bouncycastle.asn1.dvcs.DVCSObjectIdentifiers;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.dvcs.DVCSConstructionException;
import org.bouncycastle.dvcs.DVCSMessage;

public class DVCSResponse
extends DVCSMessage {
    private org.bouncycastle.asn1.dvcs.DVCSResponse asn1;

    public DVCSResponse(CMSSignedData cMSSignedData) throws DVCSConstructionException {
        this(SignedData.getInstance(cMSSignedData.toASN1Structure().getContent()).getEncapContentInfo());
    }

    public DVCSResponse(ContentInfo contentInfo) throws DVCSConstructionException {
        super(contentInfo);
        if (!DVCSObjectIdentifiers.id_ct_DVCSResponseData.equals(contentInfo.getContentType())) {
            throw new DVCSConstructionException("ContentInfo not a DVCS Response");
        }
        try {
            this.asn1 = contentInfo.getContent().toASN1Primitive() instanceof ASN1Sequence ? org.bouncycastle.asn1.dvcs.DVCSResponse.getInstance(contentInfo.getContent()) : org.bouncycastle.asn1.dvcs.DVCSResponse.getInstance(ASN1OctetString.getInstance(contentInfo.getContent()).getOctets());
        } catch (Exception exception) {
            throw new DVCSConstructionException("Unable to parse content: " + exception.getMessage(), exception);
        }
    }

    public ASN1Encodable getContent() {
        return this.asn1;
    }
}

