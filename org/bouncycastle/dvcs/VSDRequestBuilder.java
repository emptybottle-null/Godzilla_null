/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.dvcs;

import java.io.IOException;
import java.util.Date;
import org.bouncycastle.asn1.dvcs.DVCSRequestInformationBuilder;
import org.bouncycastle.asn1.dvcs.DVCSTime;
import org.bouncycastle.asn1.dvcs.Data;
import org.bouncycastle.asn1.dvcs.ServiceType;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.dvcs.DVCSException;
import org.bouncycastle.dvcs.DVCSRequest;
import org.bouncycastle.dvcs.DVCSRequestBuilder;

public class VSDRequestBuilder
extends DVCSRequestBuilder {
    public VSDRequestBuilder() {
        super(new DVCSRequestInformationBuilder(ServiceType.VSD));
    }

    public void setRequestTime(Date date) {
        this.requestInformationBuilder.setRequestTime(new DVCSTime(date));
    }

    public DVCSRequest build(CMSSignedData cMSSignedData) throws DVCSException {
        try {
            Data data = new Data(cMSSignedData.getEncoded());
            return this.createDVCRequest(data);
        } catch (IOException iOException) {
            throw new DVCSException("Failed to encode CMS signed data", iOException);
        }
    }
}

