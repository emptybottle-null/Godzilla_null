/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.dvcs;

import org.bouncycastle.asn1.dvcs.Data;
import org.bouncycastle.dvcs.DVCSConstructionException;
import org.bouncycastle.dvcs.DVCSRequestData;

public class CPDRequestData
extends DVCSRequestData {
    CPDRequestData(Data data) throws DVCSConstructionException {
        super(data);
        this.initMessage();
    }

    private void initMessage() throws DVCSConstructionException {
        if (this.data.getMessage() == null) {
            throw new DVCSConstructionException("DVCSRequest.data.message should be specified for CPD service");
        }
    }

    public byte[] getMessage() {
        return this.data.getMessage().getOctets();
    }
}

