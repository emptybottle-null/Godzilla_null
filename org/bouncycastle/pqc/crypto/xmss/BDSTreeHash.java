/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import java.io.Serializable;
import java.util.Stack;
import org.bouncycastle.pqc.crypto.xmss.HashTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.LTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.pqc.crypto.xmss.WOTSPlus;
import org.bouncycastle.pqc.crypto.xmss.WOTSPlusPublicKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSNode;
import org.bouncycastle.pqc.crypto.xmss.XMSSNodeUtil;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class BDSTreeHash
implements Serializable {
    private static final long serialVersionUID = 1L;
    private XMSSNode tailNode;
    private final int initialHeight;
    private int height;
    private int nextIndex;
    private boolean initialized;
    private boolean finished;

    BDSTreeHash(int n) {
        this.initialHeight = n;
        this.initialized = false;
        this.finished = false;
    }

    void initialize(int n) {
        this.tailNode = null;
        this.height = this.initialHeight;
        this.nextIndex = n;
        this.initialized = true;
        this.finished = false;
    }

    void update(Stack<XMSSNode> stack, WOTSPlus wOTSPlus, byte[] byArray, byte[] byArray2, OTSHashAddress oTSHashAddress) {
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        if (this.finished || !this.initialized) {
            throw new IllegalStateException("finished or not initialized");
        }
        oTSHashAddress = (OTSHashAddress)((OTSHashAddress.Builder)((OTSHashAddress.Builder)((OTSHashAddress.Builder)new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress())).withTreeAddress(oTSHashAddress.getTreeAddress())).withOTSAddress(this.nextIndex).withChainAddress(oTSHashAddress.getChainAddress()).withHashAddress(oTSHashAddress.getHashAddress()).withKeyAndMask(oTSHashAddress.getKeyAndMask())).build();
        LTreeAddress lTreeAddress = (LTreeAddress)((LTreeAddress.Builder)((LTreeAddress.Builder)new LTreeAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress())).withTreeAddress(oTSHashAddress.getTreeAddress())).withLTreeAddress(this.nextIndex).build();
        HashTreeAddress hashTreeAddress = (HashTreeAddress)((HashTreeAddress.Builder)((HashTreeAddress.Builder)new HashTreeAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress())).withTreeAddress(oTSHashAddress.getTreeAddress())).withTreeIndex(this.nextIndex).build();
        wOTSPlus.importKeys(wOTSPlus.getWOTSPlusSecretKey(byArray2, oTSHashAddress), byArray);
        WOTSPlusPublicKeyParameters wOTSPlusPublicKeyParameters = wOTSPlus.getPublicKey(oTSHashAddress);
        XMSSNode xMSSNode = XMSSNodeUtil.lTree(wOTSPlus, wOTSPlusPublicKeyParameters, lTreeAddress);
        while (!stack.isEmpty() && stack.peek().getHeight() == xMSSNode.getHeight() && stack.peek().getHeight() != this.initialHeight) {
            hashTreeAddress = (HashTreeAddress)((HashTreeAddress.Builder)((HashTreeAddress.Builder)((HashTreeAddress.Builder)new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress())).withTreeAddress(hashTreeAddress.getTreeAddress())).withTreeHeight(hashTreeAddress.getTreeHeight()).withTreeIndex((hashTreeAddress.getTreeIndex() - 1) / 2).withKeyAndMask(hashTreeAddress.getKeyAndMask())).build();
            xMSSNode = XMSSNodeUtil.randomizeHash(wOTSPlus, stack.pop(), xMSSNode, hashTreeAddress);
            xMSSNode = new XMSSNode(xMSSNode.getHeight() + 1, xMSSNode.getValue());
            hashTreeAddress = (HashTreeAddress)((HashTreeAddress.Builder)((HashTreeAddress.Builder)((HashTreeAddress.Builder)new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress())).withTreeAddress(hashTreeAddress.getTreeAddress())).withTreeHeight(hashTreeAddress.getTreeHeight() + 1).withTreeIndex(hashTreeAddress.getTreeIndex()).withKeyAndMask(hashTreeAddress.getKeyAndMask())).build();
        }
        if (this.tailNode == null) {
            this.tailNode = xMSSNode;
        } else if (this.tailNode.getHeight() == xMSSNode.getHeight()) {
            hashTreeAddress = (HashTreeAddress)((HashTreeAddress.Builder)((HashTreeAddress.Builder)((HashTreeAddress.Builder)new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress())).withTreeAddress(hashTreeAddress.getTreeAddress())).withTreeHeight(hashTreeAddress.getTreeHeight()).withTreeIndex((hashTreeAddress.getTreeIndex() - 1) / 2).withKeyAndMask(hashTreeAddress.getKeyAndMask())).build();
            xMSSNode = XMSSNodeUtil.randomizeHash(wOTSPlus, this.tailNode, xMSSNode, hashTreeAddress);
            this.tailNode = xMSSNode = new XMSSNode(this.tailNode.getHeight() + 1, xMSSNode.getValue());
            hashTreeAddress = (HashTreeAddress)((HashTreeAddress.Builder)((HashTreeAddress.Builder)((HashTreeAddress.Builder)new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress())).withTreeAddress(hashTreeAddress.getTreeAddress())).withTreeHeight(hashTreeAddress.getTreeHeight() + 1).withTreeIndex(hashTreeAddress.getTreeIndex()).withKeyAndMask(hashTreeAddress.getKeyAndMask())).build();
        } else {
            stack.push(xMSSNode);
        }
        if (this.tailNode.getHeight() == this.initialHeight) {
            this.finished = true;
        } else {
            this.height = xMSSNode.getHeight();
            ++this.nextIndex;
        }
    }

    int getHeight() {
        if (!this.initialized || this.finished) {
            return Integer.MAX_VALUE;
        }
        return this.height;
    }

    int getIndexLeaf() {
        return this.nextIndex;
    }

    void setNode(XMSSNode xMSSNode) {
        this.tailNode = xMSSNode;
        this.height = xMSSNode.getHeight();
        if (this.height == this.initialHeight) {
            this.finished = true;
        }
    }

    boolean isFinished() {
        return this.finished;
    }

    boolean isInitialized() {
        return this.initialized;
    }

    public XMSSNode getTailNode() {
        return this.tailNode.clone();
    }
}

