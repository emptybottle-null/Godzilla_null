/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.dvcs;

import org.bouncycastle.asn1.dvcs.DVCSRequestInformationBuilder;
import org.bouncycastle.asn1.dvcs.Data;
import org.bouncycastle.asn1.dvcs.ServiceType;
import org.bouncycastle.dvcs.DVCSException;
import org.bouncycastle.dvcs.DVCSRequest;
import org.bouncycastle.dvcs.DVCSRequestBuilder;
import org.bouncycastle.dvcs.MessageImprint;

public class CCPDRequestBuilder
extends DVCSRequestBuilder {
    public CCPDRequestBuilder() {
        super(new DVCSRequestInformationBuilder(ServiceType.CCPD));
    }

    public DVCSRequest build(MessageImprint messageImprint) throws DVCSException {
        Data data = new Data(messageImprint.toASN1Structure());
        return this.createDVCRequest(data);
    }
}

