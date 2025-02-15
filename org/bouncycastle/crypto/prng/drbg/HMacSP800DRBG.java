/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.prng.drbg;

import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.crypto.prng.drbg.SP80090DRBG;
import org.bouncycastle.crypto.prng.drbg.Utils;
import org.bouncycastle.util.Arrays;

public class HMacSP800DRBG
implements SP80090DRBG {
    private static final long RESEED_MAX = 0x800000000000L;
    private static final int MAX_BITS_REQUEST = 262144;
    private byte[] _K;
    private byte[] _V;
    private long _reseedCounter;
    private EntropySource _entropySource;
    private Mac _hMac;
    private int _securityStrength;

    public HMacSP800DRBG(Mac mac, int n, EntropySource entropySource, byte[] byArray, byte[] byArray2) {
        if (n > Utils.getMaxSecurityStrength(mac)) {
            throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
        }
        if (entropySource.entropySize() < n) {
            throw new IllegalArgumentException("Not enough entropy for security strength required");
        }
        this._securityStrength = n;
        this._entropySource = entropySource;
        this._hMac = mac;
        byte[] byArray3 = this.getEntropy();
        byte[] byArray4 = Arrays.concatenate(byArray3, byArray2, byArray);
        this._K = new byte[mac.getMacSize()];
        this._V = new byte[this._K.length];
        Arrays.fill(this._V, (byte)1);
        this.hmac_DRBG_Update(byArray4);
        this._reseedCounter = 1L;
    }

    private void hmac_DRBG_Update(byte[] byArray) {
        this.hmac_DRBG_Update_Func(byArray, (byte)0);
        if (byArray != null) {
            this.hmac_DRBG_Update_Func(byArray, (byte)1);
        }
    }

    private void hmac_DRBG_Update_Func(byte[] byArray, byte by) {
        this._hMac.init(new KeyParameter(this._K));
        this._hMac.update(this._V, 0, this._V.length);
        this._hMac.update(by);
        if (byArray != null) {
            this._hMac.update(byArray, 0, byArray.length);
        }
        this._hMac.doFinal(this._K, 0);
        this._hMac.init(new KeyParameter(this._K));
        this._hMac.update(this._V, 0, this._V.length);
        this._hMac.doFinal(this._V, 0);
    }

    public int getBlockSize() {
        return this._V.length * 8;
    }

    public int generate(byte[] byArray, byte[] byArray2, boolean bl) {
        int n = byArray.length * 8;
        if (n > 262144) {
            throw new IllegalArgumentException("Number of bits per request limited to 262144");
        }
        if (this._reseedCounter > 0x800000000000L) {
            return -1;
        }
        if (bl) {
            this.reseed(byArray2);
            byArray2 = null;
        }
        if (byArray2 != null) {
            this.hmac_DRBG_Update(byArray2);
        }
        byte[] byArray3 = new byte[byArray.length];
        int n2 = byArray.length / this._V.length;
        this._hMac.init(new KeyParameter(this._K));
        for (int i = 0; i < n2; ++i) {
            this._hMac.update(this._V, 0, this._V.length);
            this._hMac.doFinal(this._V, 0);
            System.arraycopy(this._V, 0, byArray3, i * this._V.length, this._V.length);
        }
        if (n2 * this._V.length < byArray3.length) {
            this._hMac.update(this._V, 0, this._V.length);
            this._hMac.doFinal(this._V, 0);
            System.arraycopy(this._V, 0, byArray3, n2 * this._V.length, byArray3.length - n2 * this._V.length);
        }
        this.hmac_DRBG_Update(byArray2);
        ++this._reseedCounter;
        System.arraycopy(byArray3, 0, byArray, 0, byArray.length);
        return n;
    }

    public void reseed(byte[] byArray) {
        byte[] byArray2 = this.getEntropy();
        byte[] byArray3 = Arrays.concatenate(byArray2, byArray);
        this.hmac_DRBG_Update(byArray3);
        this._reseedCounter = 1L;
    }

    private byte[] getEntropy() {
        byte[] byArray = this._entropySource.getEntropy();
        if (byArray.length < (this._securityStrength + 7) / 8) {
            throw new IllegalStateException("Insufficient entropy provided by entropy source");
        }
        return byArray;
    }
}

