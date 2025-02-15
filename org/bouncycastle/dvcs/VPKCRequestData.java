/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.dvcs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bouncycastle.asn1.dvcs.Data;
import org.bouncycastle.asn1.dvcs.TargetEtcChain;
import org.bouncycastle.dvcs.DVCSConstructionException;
import org.bouncycastle.dvcs.DVCSRequestData;
import org.bouncycastle.dvcs.TargetChain;

public class VPKCRequestData
extends DVCSRequestData {
    private List chains;

    VPKCRequestData(Data data) throws DVCSConstructionException {
        super(data);
        TargetEtcChain[] targetEtcChainArray = data.getCerts();
        if (targetEtcChainArray == null) {
            throw new DVCSConstructionException("DVCSRequest.data.certs should be specified for VPKC service");
        }
        this.chains = new ArrayList(targetEtcChainArray.length);
        for (int i = 0; i != targetEtcChainArray.length; ++i) {
            this.chains.add(new TargetChain(targetEtcChainArray[i]));
        }
    }

    public List getCerts() {
        return Collections.unmodifiableList(this.chains);
    }
}

