/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.dvcs;

import org.bouncycastle.asn1.dvcs.Data;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.dvcs.DVCSConstructionException;
import org.bouncycastle.dvcs.DVCSRequestData;

public class VSDRequestData
extends DVCSRequestData {
    private CMSSignedData doc;

    VSDRequestData(Data data) throws DVCSConstructionException {
        super(data);
        this.initDocument();
    }

    private void initDocument() throws DVCSConstructionException {
        if (this.doc == null) {
            if (this.data.getMessage() == null) {
                throw new DVCSConstructionException("DVCSRequest.data.message should be specified for VSD service");
            }
            try {
                this.doc = new CMSSignedData(this.data.getMessage().getOctets());
            } catch (CMSException cMSException) {
                throw new DVCSConstructionException("Can't read CMS SignedData from input", cMSException);
            }
        }
    }

    public byte[] getMessage() {
        return this.data.getMessage().getOctets();
    }

    public CMSSignedData getParsedMessage() {
        return this.doc;
    }
}

