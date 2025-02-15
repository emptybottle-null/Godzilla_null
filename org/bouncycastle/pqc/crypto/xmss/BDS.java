/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import org.bouncycastle.pqc.crypto.xmss.BDSTreeHash;
import org.bouncycastle.pqc.crypto.xmss.HashTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.LTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.pqc.crypto.xmss.WOTSPlus;
import org.bouncycastle.pqc.crypto.xmss.WOTSPlusPublicKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSNode;
import org.bouncycastle.pqc.crypto.xmss.XMSSNodeUtil;
import org.bouncycastle.pqc.crypto.xmss.XMSSParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSUtil;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class BDS
implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient WOTSPlus wotsPlus;
    private final int treeHeight;
    private final List<BDSTreeHash> treeHashInstances;
    private int k;
    private XMSSNode root;
    private List<XMSSNode> authenticationPath;
    private Map<Integer, LinkedList<XMSSNode>> retain;
    private Stack<XMSSNode> stack;
    private Map<Integer, XMSSNode> keep;
    private int index;
    private boolean used;

    BDS(XMSSParameters xMSSParameters, int n) {
        this(xMSSParameters.getWOTSPlus(), xMSSParameters.getHeight(), xMSSParameters.getK());
        this.index = n;
        this.used = true;
    }

    BDS(XMSSParameters xMSSParameters, byte[] byArray, byte[] byArray2, OTSHashAddress oTSHashAddress) {
        this(xMSSParameters.getWOTSPlus(), xMSSParameters.getHeight(), xMSSParameters.getK());
        this.initialize(byArray, byArray2, oTSHashAddress);
    }

    BDS(XMSSParameters xMSSParameters, byte[] byArray, byte[] byArray2, OTSHashAddress oTSHashAddress, int n) {
        this(xMSSParameters.getWOTSPlus(), xMSSParameters.getHeight(), xMSSParameters.getK());
        this.initialize(byArray, byArray2, oTSHashAddress);
        while (this.index < n) {
            this.nextAuthenticationPath(byArray, byArray2, oTSHashAddress);
            this.used = false;
        }
    }

    private BDS(WOTSPlus wOTSPlus, int n, int n2) {
        this.wotsPlus = wOTSPlus;
        this.treeHeight = n;
        this.k = n2;
        if (n2 > n || n2 < 2 || (n - n2) % 2 != 0) {
            throw new IllegalArgumentException("illegal value for BDS parameter k");
        }
        this.authenticationPath = new ArrayList<XMSSNode>();
        this.retain = new TreeMap<Integer, LinkedList<XMSSNode>>();
        this.stack = new Stack();
        this.treeHashInstances = new ArrayList<BDSTreeHash>();
        for (int i = 0; i < n - n2; ++i) {
            this.treeHashInstances.add(new BDSTreeHash(i));
        }
        this.keep = new TreeMap<Integer, XMSSNode>();
        this.index = 0;
        this.used = false;
    }

    private BDS(BDS bDS, byte[] byArray, byte[] byArray2, OTSHashAddress oTSHashAddress) {
        this.wotsPlus = bDS.wotsPlus;
        this.treeHeight = bDS.treeHeight;
        this.k = bDS.k;
        this.root = bDS.root;
        this.authenticationPath = new ArrayList<XMSSNode>(bDS.authenticationPath);
        this.retain = bDS.retain;
        this.stack = (Stack)bDS.stack.clone();
        this.treeHashInstances = bDS.treeHashInstances;
        this.keep = new TreeMap<Integer, XMSSNode>(bDS.keep);
        this.index = bDS.index;
        this.nextAuthenticationPath(byArray, byArray2, oTSHashAddress);
        bDS.used = true;
    }

    public BDS getNextState(byte[] byArray, byte[] byArray2, OTSHashAddress oTSHashAddress) {
        return new BDS(this, byArray, byArray2, oTSHashAddress);
    }

    private void initialize(byte[] byArray, byte[] byArray2, OTSHashAddress oTSHashAddress) {
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        LTreeAddress lTreeAddress = (LTreeAddress)((LTreeAddress.Builder)((LTreeAddress.Builder)new LTreeAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress())).withTreeAddress(oTSHashAddress.getTreeAddress())).build();
        HashTreeAddress hashTreeAddress = (HashTreeAddress)((HashTreeAddress.Builder)((HashTreeAddress.Builder)new HashTreeAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress())).withTreeAddress(oTSHashAddress.getTreeAddress())).build();
        for (int i = 0; i < 1 << this.treeHeight; ++i) {
            oTSHashAddress = (OTSHashAddress)((OTSHashAddress.Builder)((OTSHashAddress.Builder)((OTSHashAddress.Builder)new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress())).withTreeAddress(oTSHashAddress.getTreeAddress())).withOTSAddress(i).withChainAddress(oTSHashAddress.getChainAddress()).withHashAddress(oTSHashAddress.getHashAddress()).withKeyAndMask(oTSHashAddress.getKeyAndMask())).build();
            this.wotsPlus.importKeys(this.wotsPlus.getWOTSPlusSecretKey(byArray2, oTSHashAddress), byArray);
            WOTSPlusPublicKeyParameters wOTSPlusPublicKeyParameters = this.wotsPlus.getPublicKey(oTSHashAddress);
            lTreeAddress = (LTreeAddress)((LTreeAddress.Builder)((LTreeAddress.Builder)((LTreeAddress.Builder)new LTreeAddress.Builder().withLayerAddress(lTreeAddress.getLayerAddress())).withTreeAddress(lTreeAddress.getTreeAddress())).withLTreeAddress(i).withTreeHeight(lTreeAddress.getTreeHeight()).withTreeIndex(lTreeAddress.getTreeIndex()).withKeyAndMask(lTreeAddress.getKeyAndMask())).build();
            XMSSNode xMSSNode = XMSSNodeUtil.lTree(this.wotsPlus, wOTSPlusPublicKeyParameters, lTreeAddress);
            hashTreeAddress = (HashTreeAddress)((HashTreeAddress.Builder)((HashTreeAddress.Builder)((HashTreeAddress.Builder)new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress())).withTreeAddress(hashTreeAddress.getTreeAddress())).withTreeIndex(i).withKeyAndMask(hashTreeAddress.getKeyAndMask())).build();
            while (!this.stack.isEmpty() && this.stack.peek().getHeight() == xMSSNode.getHeight()) {
                int n = (int)Math.floor(i / (1 << xMSSNode.getHeight()));
                if (n == 1) {
                    this.authenticationPath.add(xMSSNode.clone());
                }
                if (n == 3 && xMSSNode.getHeight() < this.treeHeight - this.k) {
                    this.treeHashInstances.get(xMSSNode.getHeight()).setNode(xMSSNode.clone());
                }
                if (n >= 3 && (n & 1) == 1 && xMSSNode.getHeight() >= this.treeHeight - this.k && xMSSNode.getHeight() <= this.treeHeight - 2) {
                    if (this.retain.get(xMSSNode.getHeight()) == null) {
                        LinkedList<XMSSNode> linkedList = new LinkedList<XMSSNode>();
                        linkedList.add(xMSSNode.clone());
                        this.retain.put(xMSSNode.getHeight(), linkedList);
                    } else {
                        this.retain.get(xMSSNode.getHeight()).add(xMSSNode.clone());
                    }
                }
                hashTreeAddress = (HashTreeAddress)((HashTreeAddress.Builder)((HashTreeAddress.Builder)((HashTreeAddress.Builder)new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress())).withTreeAddress(hashTreeAddress.getTreeAddress())).withTreeHeight(hashTreeAddress.getTreeHeight()).withTreeIndex((hashTreeAddress.getTreeIndex() - 1) / 2).withKeyAndMask(hashTreeAddress.getKeyAndMask())).build();
                xMSSNode = XMSSNodeUtil.randomizeHash(this.wotsPlus, this.stack.pop(), xMSSNode, hashTreeAddress);
                xMSSNode = new XMSSNode(xMSSNode.getHeight() + 1, xMSSNode.getValue());
                hashTreeAddress = (HashTreeAddress)((HashTreeAddress.Builder)((HashTreeAddress.Builder)((HashTreeAddress.Builder)new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress())).withTreeAddress(hashTreeAddress.getTreeAddress())).withTreeHeight(hashTreeAddress.getTreeHeight() + 1).withTreeIndex(hashTreeAddress.getTreeIndex()).withKeyAndMask(hashTreeAddress.getKeyAndMask())).build();
            }
            this.stack.push(xMSSNode);
        }
        this.root = this.stack.pop();
    }

    private void nextAuthenticationPath(byte[] byArray, byte[] byArray2, OTSHashAddress oTSHashAddress) {
        Serializable serializable;
        Object object;
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        if (this.used) {
            throw new IllegalStateException("index already used");
        }
        if (this.index > (1 << this.treeHeight) - 2) {
            throw new IllegalStateException("index out of bounds");
        }
        LTreeAddress lTreeAddress = (LTreeAddress)((LTreeAddress.Builder)((LTreeAddress.Builder)new LTreeAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress())).withTreeAddress(oTSHashAddress.getTreeAddress())).build();
        HashTreeAddress hashTreeAddress = (HashTreeAddress)((HashTreeAddress.Builder)((HashTreeAddress.Builder)new HashTreeAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress())).withTreeAddress(oTSHashAddress.getTreeAddress())).build();
        int n = XMSSUtil.calculateTau(this.index, this.treeHeight);
        if ((this.index >> n + 1 & 1) == 0 && n < this.treeHeight - 1) {
            this.keep.put(n, this.authenticationPath.get(n).clone());
        }
        if (n == 0) {
            oTSHashAddress = (OTSHashAddress)((OTSHashAddress.Builder)((OTSHashAddress.Builder)((OTSHashAddress.Builder)new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress())).withTreeAddress(oTSHashAddress.getTreeAddress())).withOTSAddress(this.index).withChainAddress(oTSHashAddress.getChainAddress()).withHashAddress(oTSHashAddress.getHashAddress()).withKeyAndMask(oTSHashAddress.getKeyAndMask())).build();
            this.wotsPlus.importKeys(this.wotsPlus.getWOTSPlusSecretKey(byArray2, oTSHashAddress), byArray);
            object = this.wotsPlus.getPublicKey(oTSHashAddress);
            lTreeAddress = (LTreeAddress)((LTreeAddress.Builder)((LTreeAddress.Builder)((LTreeAddress.Builder)new LTreeAddress.Builder().withLayerAddress(lTreeAddress.getLayerAddress())).withTreeAddress(lTreeAddress.getTreeAddress())).withLTreeAddress(this.index).withTreeHeight(lTreeAddress.getTreeHeight()).withTreeIndex(lTreeAddress.getTreeIndex()).withKeyAndMask(lTreeAddress.getKeyAndMask())).build();
            serializable = XMSSNodeUtil.lTree(this.wotsPlus, (WOTSPlusPublicKeyParameters)object, lTreeAddress);
            this.authenticationPath.set(0, (XMSSNode)serializable);
        } else {
            int n2;
            hashTreeAddress = (HashTreeAddress)((HashTreeAddress.Builder)((HashTreeAddress.Builder)((HashTreeAddress.Builder)new HashTreeAddress.Builder().withLayerAddress(hashTreeAddress.getLayerAddress())).withTreeAddress(hashTreeAddress.getTreeAddress())).withTreeHeight(n - 1).withTreeIndex(this.index >> n).withKeyAndMask(hashTreeAddress.getKeyAndMask())).build();
            object = XMSSNodeUtil.randomizeHash(this.wotsPlus, this.authenticationPath.get(n - 1), this.keep.get(n - 1), hashTreeAddress);
            object = new XMSSNode(((XMSSNode)object).getHeight() + 1, ((XMSSNode)object).getValue());
            this.authenticationPath.set(n, (XMSSNode)object);
            this.keep.remove(n - 1);
            for (n2 = 0; n2 < n; ++n2) {
                if (n2 < this.treeHeight - this.k) {
                    this.authenticationPath.set(n2, this.treeHashInstances.get(n2).getTailNode());
                    continue;
                }
                this.authenticationPath.set(n2, this.retain.get(n2).removeFirst());
            }
            n2 = Math.min(n, this.treeHeight - this.k);
            for (int i = 0; i < n2; ++i) {
                int n3 = this.index + 1 + 3 * (1 << i);
                if (n3 >= 1 << this.treeHeight) continue;
                this.treeHashInstances.get(i).initialize(n3);
            }
        }
        for (int i = 0; i < this.treeHeight - this.k >> 1; ++i) {
            serializable = this.getBDSTreeHashInstanceForUpdate();
            if (serializable == null) continue;
            ((BDSTreeHash)serializable).update(this.stack, this.wotsPlus, byArray, byArray2, oTSHashAddress);
        }
        ++this.index;
    }

    boolean isUsed() {
        return this.used;
    }

    private BDSTreeHash getBDSTreeHashInstanceForUpdate() {
        BDSTreeHash bDSTreeHash = null;
        for (BDSTreeHash bDSTreeHash2 : this.treeHashInstances) {
            if (bDSTreeHash2.isFinished() || !bDSTreeHash2.isInitialized()) continue;
            if (bDSTreeHash == null) {
                bDSTreeHash = bDSTreeHash2;
                continue;
            }
            if (bDSTreeHash2.getHeight() < bDSTreeHash.getHeight()) {
                bDSTreeHash = bDSTreeHash2;
                continue;
            }
            if (bDSTreeHash2.getHeight() != bDSTreeHash.getHeight() || bDSTreeHash2.getIndexLeaf() >= bDSTreeHash.getIndexLeaf()) continue;
            bDSTreeHash = bDSTreeHash2;
        }
        return bDSTreeHash;
    }

    protected void validate() {
        if (this.authenticationPath == null) {
            throw new IllegalStateException("authenticationPath == null");
        }
        if (this.retain == null) {
            throw new IllegalStateException("retain == null");
        }
        if (this.stack == null) {
            throw new IllegalStateException("stack == null");
        }
        if (this.treeHashInstances == null) {
            throw new IllegalStateException("treeHashInstances == null");
        }
        if (this.keep == null) {
            throw new IllegalStateException("keep == null");
        }
        if (!XMSSUtil.isIndexValid(this.treeHeight, this.index)) {
            throw new IllegalStateException("index in BDS state out of bounds");
        }
    }

    protected int getTreeHeight() {
        return this.treeHeight;
    }

    protected XMSSNode getRoot() {
        return this.root.clone();
    }

    protected List<XMSSNode> getAuthenticationPath() {
        ArrayList<XMSSNode> arrayList = new ArrayList<XMSSNode>();
        for (XMSSNode xMSSNode : this.authenticationPath) {
            arrayList.add(xMSSNode.clone());
        }
        return arrayList;
    }

    protected void setXMSS(XMSSParameters xMSSParameters) {
        if (this.treeHeight != xMSSParameters.getHeight()) {
            throw new IllegalStateException("wrong height");
        }
        this.wotsPlus = xMSSParameters.getWOTSPlus();
    }

    protected int getIndex() {
        return this.index;
    }
}

