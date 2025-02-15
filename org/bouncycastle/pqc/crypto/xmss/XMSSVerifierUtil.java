/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.pqc.crypto.xmss.HashTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.LTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.pqc.crypto.xmss.WOTSPlus;
import org.bouncycastle.pqc.crypto.xmss.WOTSPlusPublicKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSNode;
import org.bouncycastle.pqc.crypto.xmss.XMSSNodeUtil;
import org.bouncycastle.pqc.crypto.xmss.XMSSReducedSignature;

class XMSSVerifierUtil {
    XMSSVerifierUtil() {
    }

    static XMSSNode getRootNodeFromSignature(WOTSPlus wOTSPlus, int n, byte[] byArray, XMSSReducedSignature xMSSReducedSignature, OTSHashAddress oTSHashAddress, int n2) {
        if (byArray.length != wOTSPlus.getParams().getDigestSize()) {
            throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
        }
        if (xMSSReducedSignature == null) {
            throw new NullPointerException("signature == null");
        }
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        LTreeAddress lTreeAddress = (LTreeAddress)((LTreeAddress.Builder)((LTreeAddress.Builder)new LTreeAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress())).withTreeAddress(oTSHashAddress.getTreeAddress())).withLTreeAddress(oTSHashAddress.getOTSAddress()).build();
        HashTreeAddress hashTreeAddress = (HashTreeAddress)((HashTreeAddress.Builder)((HashTreeAddress.Builder)new HashTreeAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress())).withTreeAddress(oTSHashAddress.getTreeAddress())).withTreeIndex(oTSHashAddress.getOTSAddress()).build();
        WOTSPlusPublicKeyParameters wOTSPlusPublicKeyParameters = wOTSPlus.getPublicKeyFromSignature(byArray, xMSSReducedSignature.getWOTSPlusSignature(), oTSHashAddress);
        XMSSNode[] xMSSNodeArray = new XMSSNode[2];
        xMSSNodeArray[0] = XMSSNodeUtil.lTree(wOTSPlus, wOTSPlusPublicKeyParameters, lTreeAddress);
        for (int i = 0; i < n; ++i) {
            hashTreeAddress = (HashTreeAddress)((HashTreeAddress.Builder)((HashTreeAddress.Builder)((HashTreeAddress.Builder)new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress())).withTreeAddress(hashTreeAddress.getTreeAddress())).withTreeHeight(i).withTreeIndex(hashTreeAddress.getTreeIndex()).withKeyAndMask(hashTreeAddress.getKeyAndMask())).build();
            if (Math.floor(n2 / (1 << i)) % 2.0 == 0.0) {
                hashTreeAddress = (HashTreeAddress)((HashTreeAddress.Builder)((HashTreeAddress.Builder)((HashTreeAddress.Builder)new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress())).withTreeAddress(hashTreeAddress.getTreeAddress())).withTreeHeight(hashTreeAddress.getTreeHeight()).withTreeIndex(hashTreeAddress.getTreeIndex() / 2).withKeyAndMask(hashTreeAddress.getKeyAndMask())).build();
                xMSSNodeArray[1] = XMSSNodeUtil.randomizeHash(wOTSPlus, xMSSNodeArray[0], xMSSReducedSignature.getAuthPath().get(i), hashTreeAddress);
                xMSSNodeArray[1] = new XMSSNode(xMSSNodeArray[1].getHeight() + 1, xMSSNodeArray[1].getValue());
            } else {
                hashTreeAddress = (HashTreeAddress)((HashTreeAddress.Builder)((HashTreeAddress.Builder)((HashTreeAddress.Builder)new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress())).withTreeAddress(hashTreeAddress.getTreeAddress())).withTreeHeight(hashTreeAddress.getTreeHeight()).withTreeIndex((hashTreeAddress.getTreeIndex() - 1) / 2).withKeyAndMask(hashTreeAddress.getKeyAndMask())).build();
                xMSSNodeArray[1] = XMSSNodeUtil.randomizeHash(wOTSPlus, xMSSReducedSignature.getAuthPath().get(i), xMSSNodeArray[0], hashTreeAddress);
                xMSSNodeArray[1] = new XMSSNode(xMSSNodeArray[1].getHeight() + 1, xMSSNodeArray[1].getValue());
            }
            xMSSNodeArray[0] = xMSSNodeArray[1];
        }
        return xMSSNodeArray[0];
    }
}

