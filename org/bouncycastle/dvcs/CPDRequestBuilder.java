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

public class CPDRequestBuilder
extends DVCSRequestBuilder {
    public CPDRequestBuilder() {
        super(new DVCSRequestInformationBuilder(ServiceType.CPD));
    }

    public DVCSRequest build(byte[] byArray) throws DVCSException {
        Data data = new Data(byArray);
        return this.createDVCRequest(data);
    }
}

