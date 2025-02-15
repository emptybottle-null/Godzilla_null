/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.xmss;

import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.pqc.crypto.xmss.KeyedHashFunctions;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.pqc.crypto.xmss.WOTSPlusParameters;
import org.bouncycastle.pqc.crypto.xmss.WOTSPlusPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.WOTSPlusPublicKeyParameters;
import org.bouncycastle.pqc.crypto.xmss.WOTSPlusSignature;
import org.bouncycastle.pqc.crypto.xmss.XMSSUtil;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
final class WOTSPlus {
    private final WOTSPlusParameters params;
    private final KeyedHashFunctions khf;
    private byte[] secretKeySeed;
    private byte[] publicSeed;

    protected WOTSPlus(WOTSPlusParameters wOTSPlusParameters) {
        if (wOTSPlusParameters == null) {
            throw new NullPointerException("params == null");
        }
        this.params = wOTSPlusParameters;
        int n = wOTSPlusParameters.getDigestSize();
        this.khf = new KeyedHashFunctions(wOTSPlusParameters.getDigest(), n);
        this.secretKeySeed = new byte[n];
        this.publicSeed = new byte[n];
    }

    void importKeys(byte[] byArray, byte[] byArray2) {
        if (byArray == null) {
            throw new NullPointerException("secretKeySeed == null");
        }
        if (byArray.length != this.params.getDigestSize()) {
            throw new IllegalArgumentException("size of secretKeySeed needs to be equal to size of digest");
        }
        if (byArray2 == null) {
            throw new NullPointerException("publicSeed == null");
        }
        if (byArray2.length != this.params.getDigestSize()) {
            throw new IllegalArgumentException("size of publicSeed needs to be equal to size of digest");
        }
        this.secretKeySeed = byArray;
        this.publicSeed = byArray2;
    }

    protected WOTSPlusSignature sign(byte[] byArray, OTSHashAddress oTSHashAddress) {
        int n;
        if (byArray == null) {
            throw new NullPointerException("messageDigest == null");
        }
        if (byArray.length != this.params.getDigestSize()) {
            throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
        }
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        List<Integer> list = this.convertToBaseW(byArray, this.params.getWinternitzParameter(), this.params.getLen1());
        int n2 = 0;
        for (n = 0; n < this.params.getLen1(); ++n) {
            n2 += this.params.getWinternitzParameter() - 1 - list.get(n);
        }
        n = (int)Math.ceil((double)(this.params.getLen2() * XMSSUtil.log2(this.params.getWinternitzParameter())) / 8.0);
        List<Integer> list2 = this.convertToBaseW(XMSSUtil.toBytesBigEndian(n2 <<= 8 - this.params.getLen2() * XMSSUtil.log2(this.params.getWinternitzParameter()) % 8, n), this.params.getWinternitzParameter(), this.params.getLen2());
        list.addAll(list2);
        byte[][] byArrayArray = new byte[this.params.getLen()][];
        for (int i = 0; i < this.params.getLen(); ++i) {
            oTSHashAddress = (OTSHashAddress)((OTSHashAddress.Builder)((OTSHashAddress.Builder)((OTSHashAddress.Builder)new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress())).withTreeAddress(oTSHashAddress.getTreeAddress())).withOTSAddress(oTSHashAddress.getOTSAddress()).withChainAddress(i).withHashAddress(oTSHashAddress.getHashAddress()).withKeyAndMask(oTSHashAddress.getKeyAndMask())).build();
            byArrayArray[i] = this.chain(this.expandSecretKeySeed(i), 0, list.get(i), oTSHashAddress);
        }
        return new WOTSPlusSignature(this.params, byArrayArray);
    }

    protected boolean verifySignature(byte[] byArray, WOTSPlusSignature wOTSPlusSignature, OTSHashAddress oTSHashAddress) {
        if (byArray == null) {
            throw new NullPointerException("messageDigest == null");
        }
        if (byArray.length != this.params.getDigestSize()) {
            throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
        }
        if (wOTSPlusSignature == null) {
            throw new NullPointerException("signature == null");
        }
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        byte[][] byArray2 = this.getPublicKeyFromSignature(byArray, wOTSPlusSignature, oTSHashAddress).toByteArray();
        return XMSSUtil.areEqual(byArray2, this.getPublicKey(oTSHashAddress).toByteArray());
    }

    protected WOTSPlusPublicKeyParameters getPublicKeyFromSignature(byte[] byArray, WOTSPlusSignature wOTSPlusSignature, OTSHashAddress oTSHashAddress) {
        int n;
        if (byArray == null) {
            throw new NullPointerException("messageDigest == null");
        }
        if (byArray.length != this.params.getDigestSize()) {
            throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
        }
        if (wOTSPlusSignature == null) {
            throw new NullPointerException("signature == null");
        }
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        List<Integer> list = this.convertToBaseW(byArray, this.params.getWinternitzParameter(), this.params.getLen1());
        int n2 = 0;
        for (n = 0; n < this.params.getLen1(); ++n) {
            n2 += this.params.getWinternitzParameter() - 1 - list.get(n);
        }
        n = (int)Math.ceil((double)(this.params.getLen2() * XMSSUtil.log2(this.params.getWinternitzParameter())) / 8.0);
        List<Integer> list2 = this.convertToBaseW(XMSSUtil.toBytesBigEndian(n2 <<= 8 - this.params.getLen2() * XMSSUtil.log2(this.params.getWinternitzParameter()) % 8, n), this.params.getWinternitzParameter(), this.params.getLen2());
        list.addAll(list2);
        byte[][] byArrayArray = new byte[this.params.getLen()][];
        for (int i = 0; i < this.params.getLen(); ++i) {
            oTSHashAddress = (OTSHashAddress)((OTSHashAddress.Builder)((OTSHashAddress.Builder)((OTSHashAddress.Builder)new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress())).withTreeAddress(oTSHashAddress.getTreeAddress())).withOTSAddress(oTSHashAddress.getOTSAddress()).withChainAddress(i).withHashAddress(oTSHashAddress.getHashAddress()).withKeyAndMask(oTSHashAddress.getKeyAndMask())).build();
            byArrayArray[i] = this.chain(wOTSPlusSignature.toByteArray()[i], list.get(i), this.params.getWinternitzParameter() - 1 - list.get(i), oTSHashAddress);
        }
        return new WOTSPlusPublicKeyParameters(this.params, byArrayArray);
    }

    private byte[] chain(byte[] byArray, int n, int n2, OTSHashAddress oTSHashAddress) {
        int n3 = this.params.getDigestSize();
        if (byArray == null) {
            throw new NullPointerException("startHash == null");
        }
        if (byArray.length != n3) {
            throw new IllegalArgumentException("startHash needs to be " + n3 + "bytes");
        }
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        if (oTSHashAddress.toByteArray() == null) {
            throw new NullPointerException("otsHashAddress byte array == null");
        }
        if (n + n2 > this.params.getWinternitzParameter() - 1) {
            throw new IllegalArgumentException("max chain length must not be greater than w");
        }
        if (n2 == 0) {
            return byArray;
        }
        byte[] byArray2 = this.chain(byArray, n, n2 - 1, oTSHashAddress);
        oTSHashAddress = (OTSHashAddress)((OTSHashAddress.Builder)((OTSHashAddress.Builder)((OTSHashAddress.Builder)new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress())).withTreeAddress(oTSHashAddress.getTreeAddress())).withOTSAddress(oTSHashAddress.getOTSAddress()).withChainAddress(oTSHashAddress.getChainAddress()).withHashAddress(n + n2 - 1).withKeyAndMask(0)).build();
        byte[] byArray3 = this.khf.PRF(this.publicSeed, oTSHashAddress.toByteArray());
        oTSHashAddress = (OTSHashAddress)((OTSHashAddress.Builder)((OTSHashAddress.Builder)((OTSHashAddress.Builder)new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress())).withTreeAddress(oTSHashAddress.getTreeAddress())).withOTSAddress(oTSHashAddress.getOTSAddress()).withChainAddress(oTSHashAddress.getChainAddress()).withHashAddress(oTSHashAddress.getHashAddress()).withKeyAndMask(1)).build();
        byte[] byArray4 = this.khf.PRF(this.publicSeed, oTSHashAddress.toByteArray());
        byte[] byArray5 = new byte[n3];
        for (int i = 0; i < n3; ++i) {
            byArray5[i] = (byte)(byArray2[i] ^ byArray4[i]);
        }
        byArray2 = this.khf.F(byArray3, byArray5);
        return byArray2;
    }

    private List<Integer> convertToBaseW(byte[] byArray, int n, int n2) {
        if (byArray == null) {
            throw new NullPointerException("msg == null");
        }
        if (n != 4 && n != 16) {
            throw new IllegalArgumentException("w needs to be 4 or 16");
        }
        int n3 = XMSSUtil.log2(n);
        if (n2 > 8 * byArray.length / n3) {
            throw new IllegalArgumentException("outLength too big");
        }
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < byArray.length; ++i) {
            for (int j = 8 - n3; j >= 0; j -= n3) {
                arrayList.add(byArray[i] >> j & n - 1);
                if (arrayList.size() != n2) continue;
                return arrayList;
            }
        }
        return arrayList;
    }

    protected byte[] getWOTSPlusSecretKey(byte[] byArray, OTSHashAddress oTSHashAddress) {
        oTSHashAddress = (OTSHashAddress)((OTSHashAddress.Builder)((OTSHashAddress.Builder)new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress())).withTreeAddress(oTSHashAddress.getTreeAddress())).withOTSAddress(oTSHashAddress.getOTSAddress()).build();
        return this.khf.PRF(byArray, oTSHashAddress.toByteArray());
    }

    private byte[] expandSecretKeySeed(int n) {
        if (n < 0 || n >= this.params.getLen()) {
            throw new IllegalArgumentException("index out of bounds");
        }
        return this.khf.PRF(this.secretKeySeed, XMSSUtil.toBytesBigEndian(n, 32));
    }

    protected WOTSPlusParameters getParams() {
        return this.params;
    }

    protected KeyedHashFunctions getKhf() {
        return this.khf;
    }

    protected byte[] getSecretKeySeed() {
        return XMSSUtil.cloneArray(this.getSecretKeySeed());
    }

    protected byte[] getPublicSeed() {
        return XMSSUtil.cloneArray(this.publicSeed);
    }

    protected WOTSPlusPrivateKeyParameters getPrivateKey() {
        byte[][] byArrayArray = new byte[this.params.getLen()][];
        for (int i = 0; i < byArrayArray.length; ++i) {
            byArrayArray[i] = this.expandSecretKeySeed(i);
        }
        return new WOTSPlusPrivateKeyParameters(this.params, byArrayArray);
    }

    protected WOTSPlusPublicKeyParameters getPublicKey(OTSHashAddress oTSHashAddress) {
        if (oTSHashAddress == null) {
            throw new NullPointerException("otsHashAddress == null");
        }
        byte[][] byArrayArray = new byte[this.params.getLen()][];
        for (int i = 0; i < this.params.getLen(); ++i) {
            oTSHashAddress = (OTSHashAddress)((OTSHashAddress.Builder)((OTSHashAddress.Builder)((OTSHashAddress.Builder)new OTSHashAddress.Builder().withLayerAddress(oTSHashAddress.getLayerAddress())).withTreeAddress(oTSHashAddress.getTreeAddress())).withOTSAddress(oTSHashAddress.getOTSAddress()).withChainAddress(i).withHashAddress(oTSHashAddress.getHashAddress()).withKeyAndMask(oTSHashAddress.getKeyAndMask())).build();
            byArrayArray[i] = this.chain(this.expandSecretKeySeed(i), 0, this.params.getWinternitzParameter() - 1, oTSHashAddress);
        }
        return new WOTSPlusPublicKeyParameters(this.params, byArrayArray);
    }
}

