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
import org.bouncycastle.asn1.dvcs.ServiceType;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.dvcs.CCPDRequestData;
import org.bouncycastle.dvcs.CPDRequestData;
import org.bouncycastle.dvcs.DVCSConstructionException;
import org.bouncycastle.dvcs.DVCSMessage;
import org.bouncycastle.dvcs.DVCSRequestData;
import org.bouncycastle.dvcs.DVCSRequestInfo;
import org.bouncycastle.dvcs.VPKCRequestData;
import org.bouncycastle.dvcs.VSDRequestData;

public class DVCSRequest
extends DVCSMessage {
    private org.bouncycastle.asn1.dvcs.DVCSRequest asn1;
    private DVCSRequestInfo reqInfo;
    private DVCSRequestData data;

    public DVCSRequest(CMSSignedData cMSSignedData) throws DVCSConstructionException {
        this(SignedData.getInstance(cMSSignedData.toASN1Structure().getContent()).getEncapContentInfo());
    }

    public DVCSRequest(ContentInfo contentInfo) throws DVCSConstructionException {
        super(contentInfo);
        if (!DVCSObjectIdentifiers.id_ct_DVCSRequestData.equals(contentInfo.getContentType())) {
            throw new DVCSConstructionException("ContentInfo not a DVCS Request");
        }
        try {
            this.asn1 = contentInfo.getContent().toASN1Primitive() instanceof ASN1Sequence ? org.bouncycastle.asn1.dvcs.DVCSRequest.getInstance(contentInfo.getContent()) : org.bouncycastle.asn1.dvcs.DVCSRequest.getInstance(ASN1OctetString.getInstance(contentInfo.getContent()).getOctets());
        } catch (Exception exception) {
            throw new DVCSConstructionException("Unable to parse content: " + exception.getMessage(), exception);
        }
        this.reqInfo = new DVCSRequestInfo(this.asn1.getRequestInformation());
        int n = this.reqInfo.getServiceType();
        if (n == ServiceType.CPD.getValue().intValue()) {
            this.data = new CPDRequestData(this.asn1.getData());
        } else if (n == ServiceType.VSD.getValue().intValue()) {
            this.data = new VSDRequestData(this.asn1.getData());
        } else if (n == ServiceType.VPKC.getValue().intValue()) {
            this.data = new VPKCRequestData(this.asn1.getData());
        } else if (n == ServiceType.CCPD.getValue().intValue()) {
            this.data = new CCPDRequestData(this.asn1.getData());
        } else {
            throw new DVCSConstructionException("Unknown service type: " + n);
        }
    }

    public ASN1Encodable getContent() {
        return this.asn1;
    }

    public DVCSRequestInfo getRequestInfo() {
        return this.reqInfo;
    }

    public DVCSRequestData getData() {
        return this.data;
    }

    public GeneralName getTransactionIdentifier() {
        return this.asn1.getTransactionIdentifier();
    }
}

