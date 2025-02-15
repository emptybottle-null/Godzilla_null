/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.StateAwareMessageSigner;
import org.bouncycastle.pqc.crypto.xmss.BDS;
import org.bouncycastle.pqc.crypto.xmss.BDSStateMap;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.pqc.crypto.xmss.WOTSPlus;
import org.bouncycastle.pqc.crypto.xmss.WOTSPlusParameters;
import org.bouncycastle.pqc.crypto.xmss.WOTSPlusSignature;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTPublicKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTSignature;
import org.bouncycastle.pqc.crypto.xmss.XMSSNode;
import org.bouncycastle.pqc.crypto.xmss.XMSSParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSReducedSignature;
import org.bouncycastle.pqc.crypto.xmss.XMSSUtil;
import org.bouncycastle.pqc.crypto.xmss.XMSSVerifierUtil;
import org.bouncycastle.util.Arrays;

public class XMSSMTSigner
implements StateAwareMessageSigner {
    private XMSSMTPrivateKeyParameters privateKey;
    private XMSSMTPrivateKeyParameters nextKeyGenerator;
    private XMSSMTPublicKeyParameters publicKey;
    private XMSSMTParameters params;
    private XMSSParameters xmssParams;
    private WOTSPlus wotsPlus;
    private boolean hasGenerated;
    private boolean initSign;

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (bl) {
            this.initSign = true;
            this.hasGenerated = false;
            this.nextKeyGenerator = this.privateKey = (XMSSMTPrivateKeyParameters)cipherParameters;
            this.params = this.privateKey.getParameters();
            this.xmssParams = this.params.getXMSSParameters();
        } else {
            this.initSign = false;
            this.publicKey = (XMSSMTPublicKeyParameters)cipherParameters;
            this.params = this.publicKey.getParameters();
            this.xmssParams = this.params.getXMSSParameters();
        }
        this.wotsPlus = new WOTSPlus(new WOTSPlusParameters(this.params.getDigest()));
    }

    public byte[] generateSignature(byte[] byArray) {
        if (byArray == null) {
            throw new NullPointerException("message == null");
        }
        if (this.initSign) {
            if (this.privateKey == null) {
                throw new IllegalStateException("signing key no longer usable");
            }
        } else {
            throw new IllegalStateException("signer not initialized for signature generation");
        }
        if (this.privateKey.getBDSState().isEmpty()) {
            throw new IllegalStateException("not initialized");
        }
        BDSStateMap bDSStateMap = this.privateKey.getBDSState();
        long l = this.privateKey.getIndex();
        int n = this.params.getHeight();
        int n2 = this.xmssParams.getHeight();
        if (!XMSSUtil.isIndexValid(n, l)) {
            throw new IllegalStateException("index out of bounds");
        }
        byte[] byArray2 = this.wotsPlus.getKhf().PRF(this.privateKey.getSecretKeyPRF(), XMSSUtil.toBytesBigEndian(l, 32));
        byte[] byArray3 = Arrays.concatenate(byArray2, this.privateKey.getRoot(), XMSSUtil.toBytesBigEndian(l, this.params.getDigestSize()));
        byte[] byArray4 = this.wotsPlus.getKhf().HMsg(byArray3, byArray);
        XMSSMTSignature xMSSMTSignature = new XMSSMTSignature.Builder(this.params).withIndex(l).withRandom(byArray2).build();
        long l2 = XMSSUtil.getTreeIndex(l, n2);
        int n3 = XMSSUtil.getLeafIndex(l, n2);
        this.wotsPlus.importKeys(new byte[this.params.getDigestSize()], this.privateKey.getPublicSeed());
        OTSHashAddress oTSHashAddress = (OTSHashAddress)((OTSHashAddress.Builder)new OTSHashAddress.Builder().withTreeAddress(l2)).withOTSAddress(n3).build();
        if (bDSStateMap.get(0) == null || n3 == 0) {
            bDSStateMap.put(0, new BDS(this.xmssParams, this.privateKey.getPublicSeed(), this.privateKey.getSecretKeySeed(), oTSHashAddress));
        }
        WOTSPlusSignature wOTSPlusSignature = this.wotsSign(byArray4, oTSHashAddress);
        XMSSReducedSignature xMSSReducedSignature = new XMSSReducedSignature.Builder(this.xmssParams).withWOTSPlusSignature(wOTSPlusSignature).withAuthPath(bDSStateMap.get(0).getAuthenticationPath()).build();
        xMSSMTSignature.getReducedSignatures().add(xMSSReducedSignature);
        for (int i = 1; i < this.params.getLayers(); ++i) {
            XMSSNode xMSSNode = bDSStateMap.get(i - 1).getRoot();
            n3 = XMSSUtil.getLeafIndex(l2, n2);
            l2 = XMSSUtil.getTreeIndex(l2, n2);
            oTSHashAddress = (OTSHashAddress)((OTSHashAddress.Builder)((OTSHashAddress.Builder)new OTSHashAddress.Builder().withLayerAddress(i)).withTreeAddress(l2)).withOTSAddress(n3).build();
            wOTSPlusSignature = this.wotsSign(xMSSNode.getValue(), oTSHashAddress);
            if (bDSStateMap.get(i) == null || XMSSUtil.isNewBDSInitNeeded(l, n2, i)) {
                bDSStateMap.put(i, new BDS(this.xmssParams, this.privateKey.getPublicSeed(), this.privateKey.getSecretKeySeed(), oTSHashAddress));
            }
            xMSSReducedSignature = new XMSSReducedSignature.Builder(this.xmssParams).withWOTSPlusSignature(wOTSPlusSignature).withAuthPath(bDSStateMap.get(i).getAuthenticationPath()).build();
            xMSSMTSignature.getReducedSignatures().add(xMSSReducedSignature);
        }
        this.hasGenerated = true;
        if (this.nextKeyGenerator != null) {
            this.nextKeyGenerator = this.privateKey = this.nextKeyGenerator.getNextKey();
        } else {
            this.privateKey = null;
        }
        return xMSSMTSignature.toByteArray();
    }

    public boolean verifySignature(byte[] byArray, byte[] byArray2) {
        if (byArray == null) {
            throw new NullPointerException("message == null");
        }
        if (byArray2 == null) {
            throw new NullPointerException("signature == null");
        }
        if (this.publicKey == null) {
            throw new NullPointerException("publicKey == null");
        }
        XMSSMTSignature xMSSMTSignature = new XMSSMTSignature.Builder(this.params).withSignature(byArray2).build();
        byte[] byArray3 = Arrays.concatenate(xMSSMTSignature.getRandom(), this.publicKey.getRoot(), XMSSUtil.toBytesBigEndian(xMSSMTSignature.getIndex(), this.params.getDigestSize()));
        byte[] byArray4 = this.wotsPlus.getKhf().HMsg(byArray3, byArray);
        long l = xMSSMTSignature.getIndex();
        int n = this.xmssParams.getHeight();
        long l2 = XMSSUtil.getTreeIndex(l, n);
        int n2 = XMSSUtil.getLeafIndex(l, n);
        this.wotsPlus.importKeys(new byte[this.params.getDigestSize()], this.publicKey.getPublicSeed());
        OTSHashAddress oTSHashAddress = (OTSHashAddress)((OTSHashAddress.Builder)new OTSHashAddress.Builder().withTreeAddress(l2)).withOTSAddress(n2).build();
        XMSSReducedSignature xMSSReducedSignature = xMSSMTSignature.getReducedSignatures().get(0);
        XMSSNode xMSSNode = XMSSVerifierUtil.getRootNodeFromSignature(this.wotsPlus, n, byArray4, xMSSReducedSignature, oTSHashAddress, n2);
        for (int i = 1; i < this.params.getLayers(); ++i) {
            xMSSReducedSignature = xMSSMTSignature.getReducedSignatures().get(i);
            n2 = XMSSUtil.getLeafIndex(l2, n);
            l2 = XMSSUtil.getTreeIndex(l2, n);
            oTSHashAddress = (OTSHashAddress)((OTSHashAddress.Builder)((OTSHashAddress.Builder)new OTSHashAddress.Builder().withLayerAddress(i)).withTreeAddress(l2)).withOTSAddress(n2).build();
            xMSSNode = XMSSVerifierUtil.getRootNodeFromSignature(this.wotsPlus, n, xMSSNode.getValue(), xMSSReducedSignature, oTSHashAddress, n2);
        }
        return Arrays.constantTimeAreEqual(xMSSNode.getValue(), this.publicKey.getRoot());
    }

    private WOTSPlusSignature wotsSign(byte[] byArray, OTSHashAddress oTSHashAddress) {
        if (byArray.length != this.params.getDigestSize()) {
            throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
        }
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        this.wotsPlus.importKeys(this.wotsPlus.getWOTSPlusSecretKey(this.privateKey.getSecretKeySeed(), oTSHashAddress), this.privateKey.getPublicSeed());
        return this.wotsPlus.sign(byArray, oTSHashAddress);
    }

    public AsymmetricKeyParameter getUpdatedPrivateKey() {
        if (this.hasGenerated) {
            XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters = this.privateKey;
            this.privateKey = null;
            this.nextKeyGenerator = null;
            return xMSSMTPrivateKeyParameters;
        }
        XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters = this.nextKeyGenerator.getNextKey();
        this.nextKeyGenerator = null;
        return xMSSMTPrivateKeyParameters;
    }
}

