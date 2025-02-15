/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import org.bouncycastle.pqc.crypto.xmss.BDS;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSUtil;
import org.bouncycastle.util.Integers;

public class BDSStateMap
implements Serializable {
    private final Map<Integer, BDS> bdsState = new TreeMap<Integer, BDS>();

    BDSStateMap() {
    }

    BDSStateMap(XMSSMTParameters xMSSMTParameters, long l, byte[] byArray, byte[] byArray2) {
        for (long i = 0L; i < l; ++i) {
            this.updateState(xMSSMTParameters, i, byArray, byArray2);
        }
    }

    BDSStateMap(BDSStateMap bDSStateMap, XMSSMTParameters xMSSMTParameters, long l, byte[] byArray, byte[] byArray2) {
        for (Integer n : bDSStateMap.bdsState.keySet()) {
            this.bdsState.put(n, bDSStateMap.bdsState.get(n));
        }
        this.updateState(xMSSMTParameters, l, byArray, byArray2);
    }

    private void updateState(XMSSMTParameters xMSSMTParameters, long l, byte[] byArray, byte[] byArray2) {
        XMSSParameters xMSSParameters = xMSSMTParameters.getXMSSParameters();
        int n = xMSSParameters.getHeight();
        long l2 = XMSSUtil.getTreeIndex(l, n);
        int n2 = XMSSUtil.getLeafIndex(l, n);
        OTSHashAddress oTSHashAddress = (OTSHashAddress)((OTSHashAddress.Builder)new OTSHashAddress.Builder().withTreeAddress(l2)).withOTSAddress(n2).build();
        if (n2 < (1 << n) - 1) {
            if (this.get(0) == null || n2 == 0) {
                this.put(0, new BDS(xMSSParameters, byArray, byArray2, oTSHashAddress));
            }
            this.update(0, byArray, byArray2, oTSHashAddress);
        }
        for (int i = 1; i < xMSSMTParameters.getLayers(); ++i) {
            n2 = XMSSUtil.getLeafIndex(l2, n);
            l2 = XMSSUtil.getTreeIndex(l2, n);
            oTSHashAddress = (OTSHashAddress)((OTSHashAddress.Builder)((OTSHashAddress.Builder)new OTSHashAddress.Builder().withLayerAddress(i)).withTreeAddress(l2)).withOTSAddress(n2).build();
            if (n2 >= (1 << n) - 1 || !XMSSUtil.isNewAuthenticationPathNeeded(l, n, i)) continue;
            if (this.get(i) == null) {
                this.put(i, new BDS(xMSSMTParameters.getXMSSParameters(), byArray, byArray2, oTSHashAddress));
            }
            this.update(i, byArray, byArray2, oTSHashAddress);
        }
    }

    void setXMSS(XMSSParameters xMSSParameters) {
        for (Integer n : this.bdsState.keySet()) {
            BDS bDS = this.bdsState.get(n);
            bDS.setXMSS(xMSSParameters);
            bDS.validate();
        }
    }

    public boolean isEmpty() {
        return this.bdsState.isEmpty();
    }

    public BDS get(int n) {
        return this.bdsState.get(Integers.valueOf(n));
    }

    public BDS update(int n, byte[] byArray, byte[] byArray2, OTSHashAddress oTSHashAddress) {
        return this.bdsState.put(Integers.valueOf(n), this.bdsState.get(Integers.valueOf(n)).getNextState(byArray, byArray2, oTSHashAddress));
    }

    public void put(int n, BDS bDS) {
        this.bdsState.put(Integers.valueOf(n), bDS);
    }
}

