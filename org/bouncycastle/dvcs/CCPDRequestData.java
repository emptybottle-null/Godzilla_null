/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.dvcs;

import org.bouncycastle.asn1.dvcs.Data;
import org.bouncycastle.dvcs.DVCSConstructionException;
import org.bouncycastle.dvcs.DVCSRequestData;
import org.bouncycastle.dvcs.MessageImprint;

public class CCPDRequestData
extends DVCSRequestData {
    CCPDRequestData(Data data) throws DVCSConstructionException {
        super(data);
        this.initDigest();
    }

    private void initDigest() throws DVCSConstructionException {
        if (this.data.getMessageImprint() == null) {
            throw new DVCSConstructionException("DVCSRequest.data.messageImprint should be specified for CCPD service");
        }
    }

    public MessageImprint getMessageImprint() {
        return new MessageImprint(this.data.getMessageImprint());
    }
}

