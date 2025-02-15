/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.StateAwareMessageSigner;
import org.bouncycastle.pqc.crypto.xmss.KeyedHashFunctions;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.pqc.crypto.xmss.WOTSPlusSignature;
import org.bouncycastle.pqc.crypto.xmss.XMSSNode;
import org.bouncycastle.pqc.crypto.xmss.XMSSParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSPublicKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.XMSSSignature;
import org.bouncycastle.pqc.crypto.xmss.XMSSUtil;
import org.bouncycastle.pqc.crypto.xmss.XMSSVerifierUtil;
import org.bouncycastle.util.Arrays;

public class XMSSSigner
implements StateAwareMessageSigner {
    private XMSSPrivateKeyParameters privateKey;
    private XMSSPrivateKeyParameters nextKeyGenerator;
    private XMSSPublicKeyParameters publicKey;
    private XMSSParameters params;
    private KeyedHashFunctions khf;
    private boolean initSign;
    private boolean hasGenerated;

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (bl) {
            this.initSign = true;
            this.hasGenerated = false;
            this.nextKeyGenerator = this.privateKey = (XMSSPrivateKeyParameters)cipherParameters;
            this.params = this.privateKey.getParameters();
            this.khf = this.params.getWOTSPlus().getKhf();
        } else {
            this.initSign = false;
            this.publicKey = (XMSSPublicKeyParameters)cipherParameters;
            this.params = this.publicKey.getParameters();
            this.khf = this.params.getWOTSPlus().getKhf();
        }
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
        if (this.privateKey.getBDSState().getAuthenticationPath().isEmpty()) {
            throw new IllegalStateException("not initialized");
        }
        int n = this.privateKey.getIndex();
        if (!XMSSUtil.isIndexValid(this.params.getHeight(), n)) {
            throw new IllegalStateException("index out of bounds");
        }
        byte[] byArray2 = this.khf.PRF(this.privateKey.getSecretKeyPRF(), XMSSUtil.toBytesBigEndian(n, 32));
        byte[] byArray3 = Arrays.concatenate(byArray2, this.privateKey.getRoot(), XMSSUtil.toBytesBigEndian(n, this.params.getDigestSize()));
        byte[] byArray4 = this.khf.HMsg(byArray3, byArray);
        OTSHashAddress oTSHashAddress = (OTSHashAddress)new OTSHashAddress.Builder().withOTSAddress(n).build();
        WOTSPlusSignature wOTSPlusSignature = this.wotsSign(byArray4, oTSHashAddress);
        XMSSSignature xMSSSignature = (XMSSSignature)new XMSSSignature.Builder(this.params).withIndex(n).withRandom(byArray2).withWOTSPlusSignature(wOTSPlusSignature).withAuthPath(this.privateKey.getBDSState().getAuthenticationPath()).build();
        this.hasGenerated = true;
        if (this.nextKeyGenerator != null) {
            this.nextKeyGenerator = this.privateKey = this.nextKeyGenerator.getNextKey();
        } else {
            this.privateKey = null;
        }
        return xMSSSignature.toByteArray();
    }

    public boolean verifySignature(byte[] byArray, byte[] byArray2) {
        XMSSSignature xMSSSignature = new XMSSSignature.Builder(this.params).withSignature(byArray2).build();
        int n = xMSSSignature.getIndex();
        this.params.getWOTSPlus().importKeys(new byte[this.params.getDigestSize()], this.publicKey.getPublicSeed());
        byte[] byArray3 = Arrays.concatenate(xMSSSignature.getRandom(), this.publicKey.getRoot(), XMSSUtil.toBytesBigEndian(n, this.params.getDigestSize()));
        byte[] byArray4 = this.khf.HMsg(byArray3, byArray);
        int n2 = this.params.getHeight();
        int n3 = XMSSUtil.getLeafIndex(n, n2);
        OTSHashAddress oTSHashAddress = (OTSHashAddress)new OTSHashAddress.Builder().withOTSAddress(n).build();
        XMSSNode xMSSNode = XMSSVerifierUtil.getRootNodeFromSignature(this.params.getWOTSPlus(), n2, byArray4, xMSSSignature, oTSHashAddress, n3);
        return Arrays.constantTimeAreEqual(xMSSNode.getValue(), this.publicKey.getRoot());
    }

    public AsymmetricKeyParameter getUpdatedPrivateKey() {
        if (this.hasGenerated) {
            XMSSPrivateKeyParameters xMSSPrivateKeyParameters = this.privateKey;
            this.privateKey = null;
            this.nextKeyGenerator = null;
            return xMSSPrivateKeyParameters;
        }
        XMSSPrivateKeyParameters xMSSPrivateKeyParameters = this.nextKeyGenerator.getNextKey();
        this.nextKeyGenerator = null;
        return xMSSPrivateKeyParameters;
    }

    private WOTSPlusSignature wotsSign(byte[] byArray, OTSHashAddress oTSHashAddress) {
        if (byArray.length != this.params.getDigestSize()) {
            throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
        }
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        this.params.getWOTSPlus().importKeys(this.params.getWOTSPlus().getWOTSPlusSecretKey(this.privateKey.getSecretKeySeed(), oTSHashAddress), this.privateKey.getPublicSeed());
        return this.params.getWOTSPlus().sign(byArray, oTSHashAddress);
    }
}

