/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.gmss;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.pqc.crypto.gmss.GMSSDigestProvider;
import org.bouncycastle.pqc.crypto.gmss.GMSSKeyParameters;
import org.bouncycastle.pqc.crypto.gmss.GMSSParameters;
import org.bouncycastle.pqc.crypto.gmss.GMSSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.gmss.GMSSPublicKeyParameters;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSUtil;
import org.bouncycastle.pqc.crypto.gmss.util.WinternitzOTSVerify;
import org.bouncycastle.pqc.crypto.gmss.util.WinternitzOTSignature;
import org.bouncycastle.util.Arrays;

public class GMSSSigner
implements MessageSigner {
    private GMSSUtil gmssUtil = new GMSSUtil();
    private byte[] pubKeyBytes;
    private Digest messDigestTrees;
    private int mdLength;
    private int numLayer;
    private Digest messDigestOTS;
    private WinternitzOTSignature ots;
    private GMSSDigestProvider digestProvider;
    private int[] index;
    private byte[][][] currentAuthPaths;
    private byte[][] subtreeRootSig;
    private GMSSParameters gmssPS;
    private GMSSRandom gmssRandom;
    GMSSKeyParameters key;
    private SecureRandom random;

    public GMSSSigner(GMSSDigestProvider gMSSDigestProvider) {
        this.digestProvider = gMSSDigestProvider;
        this.messDigestOTS = this.messDigestTrees = gMSSDigestProvider.get();
        this.mdLength = this.messDigestTrees.getDigestSize();
        this.gmssRandom = new GMSSRandom(this.messDigestTrees);
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (bl) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
                this.random = parametersWithRandom.getRandom();
                this.key = (GMSSPrivateKeyParameters)parametersWithRandom.getParameters();
                this.initSign();
            } else {
                this.random = new SecureRandom();
                this.key = (GMSSPrivateKeyParameters)cipherParameters;
                this.initSign();
            }
        } else {
            this.key = (GMSSPublicKeyParameters)cipherParameters;
            this.initVerify();
        }
    }

    private void initSign() {
        int n;
        this.messDigestTrees.reset();
        GMSSPrivateKeyParameters gMSSPrivateKeyParameters = (GMSSPrivateKeyParameters)this.key;
        if (gMSSPrivateKeyParameters.isUsed()) {
            throw new IllegalStateException("Private key already used");
        }
        if (gMSSPrivateKeyParameters.getIndex(0) >= gMSSPrivateKeyParameters.getNumLeafs(0)) {
            throw new IllegalStateException("No more signatures can be generated");
        }
        this.gmssPS = gMSSPrivateKeyParameters.getParameters();
        this.numLayer = this.gmssPS.getNumOfLayers();
        byte[] byArray = gMSSPrivateKeyParameters.getCurrentSeeds()[this.numLayer - 1];
        byte[] byArray2 = new byte[this.mdLength];
        byte[] byArray3 = new byte[this.mdLength];
        System.arraycopy(byArray, 0, byArray3, 0, this.mdLength);
        byArray2 = this.gmssRandom.nextSeed(byArray3);
        this.ots = new WinternitzOTSignature(byArray2, this.digestProvider.get(), this.gmssPS.getWinternitzParameter()[this.numLayer - 1]);
        byte[][][] byArray4 = gMSSPrivateKeyParameters.getCurrentAuthPaths();
        this.currentAuthPaths = new byte[this.numLayer][][];
        for (int i = 0; i < this.numLayer; ++i) {
            this.currentAuthPaths[i] = new byte[byArray4[i].length][this.mdLength];
            for (n = 0; n < byArray4[i].length; ++n) {
                System.arraycopy(byArray4[i][n], 0, this.currentAuthPaths[i][n], 0, this.mdLength);
            }
        }
        this.index = new int[this.numLayer];
        System.arraycopy(gMSSPrivateKeyParameters.getIndex(), 0, this.index, 0, this.numLayer);
        this.subtreeRootSig = new byte[this.numLayer - 1][];
        for (n = 0; n < this.numLayer - 1; ++n) {
            byte[] byArray5 = gMSSPrivateKeyParameters.getSubtreeRootSig(n);
            this.subtreeRootSig[n] = new byte[byArray5.length];
            System.arraycopy(byArray5, 0, this.subtreeRootSig[n], 0, byArray5.length);
        }
        gMSSPrivateKeyParameters.markUsed();
    }

    public byte[] generateSignature(byte[] byArray) {
        byte[] byArray2 = new byte[this.mdLength];
        byArray2 = this.ots.getSignature(byArray);
        byte[] byArray3 = this.gmssUtil.concatenateArray(this.currentAuthPaths[this.numLayer - 1]);
        byte[] byArray4 = this.gmssUtil.intToBytesLittleEndian(this.index[this.numLayer - 1]);
        byte[] byArray5 = new byte[byArray4.length + byArray2.length + byArray3.length];
        System.arraycopy(byArray4, 0, byArray5, 0, byArray4.length);
        System.arraycopy(byArray2, 0, byArray5, byArray4.length, byArray2.length);
        System.arraycopy(byArray3, 0, byArray5, byArray4.length + byArray2.length, byArray3.length);
        byte[] byArray6 = new byte[]{};
        for (int i = this.numLayer - 1 - 1; i >= 0; --i) {
            byArray3 = this.gmssUtil.concatenateArray(this.currentAuthPaths[i]);
            byArray4 = this.gmssUtil.intToBytesLittleEndian(this.index[i]);
            byte[] byArray7 = new byte[byArray6.length];
            System.arraycopy(byArray6, 0, byArray7, 0, byArray6.length);
            byArray6 = new byte[byArray7.length + byArray4.length + this.subtreeRootSig[i].length + byArray3.length];
            System.arraycopy(byArray7, 0, byArray6, 0, byArray7.length);
            System.arraycopy(byArray4, 0, byArray6, byArray7.length, byArray4.length);
            System.arraycopy(this.subtreeRootSig[i], 0, byArray6, byArray7.length + byArray4.length, this.subtreeRootSig[i].length);
            System.arraycopy(byArray3, 0, byArray6, byArray7.length + byArray4.length + this.subtreeRootSig[i].length, byArray3.length);
        }
        byte[] byArray8 = new byte[byArray5.length + byArray6.length];
        System.arraycopy(byArray5, 0, byArray8, 0, byArray5.length);
        System.arraycopy(byArray6, 0, byArray8, byArray5.length, byArray6.length);
        return byArray8;
    }

    private void initVerify() {
        this.messDigestTrees.reset();
        GMSSPublicKeyParameters gMSSPublicKeyParameters = (GMSSPublicKeyParameters)this.key;
        this.pubKeyBytes = gMSSPublicKeyParameters.getPublicKey();
        this.gmssPS = gMSSPublicKeyParameters.getParameters();
        this.numLayer = this.gmssPS.getNumOfLayers();
    }

    public boolean verifySignature(byte[] byArray, byte[] byArray2) {
        boolean bl = false;
        this.messDigestOTS.reset();
        byte[] byArray3 = byArray;
        int n = 0;
        for (int i = this.numLayer - 1; i >= 0; --i) {
            int n2;
            WinternitzOTSVerify winternitzOTSVerify = new WinternitzOTSVerify(this.digestProvider.get(), this.gmssPS.getWinternitzParameter()[i]);
            int n3 = winternitzOTSVerify.getSignatureLength();
            byArray = byArray3;
            int n4 = this.gmssUtil.bytesToIntLittleEndian(byArray2, n);
            byte[] byArray4 = new byte[n3];
            System.arraycopy(byArray2, n += 4, byArray4, 0, n3);
            n += n3;
            byte[] byArray5 = winternitzOTSVerify.Verify(byArray, byArray4);
            if (byArray5 == null) {
                System.err.println("OTS Public Key is null in GMSSSignature.verify");
                return false;
            }
            byte[][] byArray6 = new byte[this.gmssPS.getHeightOfTrees()[i]][this.mdLength];
            for (n2 = 0; n2 < byArray6.length; ++n2) {
                System.arraycopy(byArray2, n, byArray6[n2], 0, this.mdLength);
                n += this.mdLength;
            }
            byArray3 = new byte[this.mdLength];
            byArray3 = byArray5;
            n2 = 1 << byArray6.length;
            n2 += n4;
            for (int j = 0; j < byArray6.length; ++j) {
                byte[] byArray7 = new byte[this.mdLength << 1];
                if (n2 % 2 == 0) {
                    System.arraycopy(byArray3, 0, byArray7, 0, this.mdLength);
                    System.arraycopy(byArray6[j], 0, byArray7, this.mdLength, this.mdLength);
                    n2 /= 2;
                } else {
                    System.arraycopy(byArray6[j], 0, byArray7, 0, this.mdLength);
                    System.arraycopy(byArray3, 0, byArray7, this.mdLength, byArray3.length);
                    n2 = (n2 - 1) / 2;
                }
                this.messDigestTrees.update(byArray7, 0, byArray7.length);
                byArray3 = new byte[this.messDigestTrees.getDigestSize()];
                this.messDigestTrees.doFinal(byArray3, 0);
            }
        }
        if (Arrays.areEqual(this.pubKeyBytes, byArray3)) {
            bl = true;
        }
        return bl;
    }
}

