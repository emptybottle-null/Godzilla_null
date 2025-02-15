/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.prng.drbg;

import java.util.Hashtable;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.crypto.prng.drbg.SP80090DRBG;
import org.bouncycastle.crypto.prng.drbg.Utils;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;

public class HashSP800DRBG
implements SP80090DRBG {
    private static final byte[] ONE = new byte[]{1};
    private static final long RESEED_MAX = 0x800000000000L;
    private static final int MAX_BITS_REQUEST = 262144;
    private static final Hashtable seedlens = new Hashtable();
    private Digest _digest;
    private byte[] _V;
    private byte[] _C;
    private long _reseedCounter;
    private EntropySource _entropySource;
    private int _securityStrength;
    private int _seedLength;

    public HashSP800DRBG(Digest digest, int n, EntropySource entropySource, byte[] byArray, byte[] byArray2) {
        if (n > Utils.getMaxSecurityStrength(digest)) {
            throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
        }
        if (entropySource.entropySize() < n) {
            throw new IllegalArgumentException("Not enough entropy for security strength required");
        }
        this._digest = digest;
        this._entropySource = entropySource;
        this._securityStrength = n;
        this._seedLength = (Integer)seedlens.get(digest.getAlgorithmName());
        byte[] byArray3 = this.getEntropy();
        byte[] byArray4 = Arrays.concatenate(byArray3, byArray2, byArray);
        byte[] byArray5 = Utils.hash_df(this._digest, byArray4, this._seedLength);
        this._V = byArray5;
        byte[] byArray6 = new byte[this._V.length + 1];
        System.arraycopy(this._V, 0, byArray6, 1, this._V.length);
        this._C = Utils.hash_df(this._digest, byArray6, this._seedLength);
        this._reseedCounter = 1L;
    }

    public int getBlockSize() {
        return this._digest.getDigestSize() * 8;
    }

    public int generate(byte[] byArray, byte[] byArray2, boolean bl) {
        byte[] byArray3;
        byte[] byArray4;
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
            byArray4 = new byte[1 + this._V.length + byArray2.length];
            byArray4[0] = 2;
            System.arraycopy(this._V, 0, byArray4, 1, this._V.length);
            System.arraycopy(byArray2, 0, byArray4, 1 + this._V.length, byArray2.length);
            byArray3 = this.hash(byArray4);
            this.addTo(this._V, byArray3);
        }
        byArray4 = this.hashgen(this._V, n);
        byArray3 = new byte[this._V.length + 1];
        System.arraycopy(this._V, 0, byArray3, 1, this._V.length);
        byArray3[0] = 3;
        byte[] byArray5 = this.hash(byArray3);
        this.addTo(this._V, byArray5);
        this.addTo(this._V, this._C);
        byte[] byArray6 = new byte[]{(byte)(this._reseedCounter >> 24), (byte)(this._reseedCounter >> 16), (byte)(this._reseedCounter >> 8), (byte)this._reseedCounter};
        this.addTo(this._V, byArray6);
        ++this._reseedCounter;
        System.arraycopy(byArray4, 0, byArray, 0, byArray.length);
        return n;
    }

    private byte[] getEntropy() {
        byte[] byArray = this._entropySource.getEntropy();
        if (byArray.length < (this._securityStrength + 7) / 8) {
            throw new IllegalStateException("Insufficient entropy provided by entropy source");
        }
        return byArray;
    }

    private void addTo(byte[] byArray, byte[] byArray2) {
        int n;
        int n2;
        int n3 = 0;
        for (n2 = 1; n2 <= byArray2.length; ++n2) {
            n = (byArray[byArray.length - n2] & 0xFF) + (byArray2[byArray2.length - n2] & 0xFF) + n3;
            n3 = n > 255 ? 1 : 0;
            byArray[byArray.length - n2] = (byte)n;
        }
        for (n2 = byArray2.length + 1; n2 <= byArray.length; ++n2) {
            n = (byArray[byArray.length - n2] & 0xFF) + n3;
            n3 = n > 255 ? 1 : 0;
            byArray[byArray.length - n2] = (byte)n;
        }
    }

    public void reseed(byte[] byArray) {
        byte[] byArray2 = this.getEntropy();
        byte[] byArray3 = Arrays.concatenate(ONE, this._V, byArray2, byArray);
        byte[] byArray4 = Utils.hash_df(this._digest, byArray3, this._seedLength);
        this._V = byArray4;
        byte[] byArray5 = new byte[this._V.length + 1];
        byArray5[0] = 0;
        System.arraycopy(this._V, 0, byArray5, 1, this._V.length);
        this._C = Utils.hash_df(this._digest, byArray5, this._seedLength);
        this._reseedCounter = 1L;
    }

    private byte[] hash(byte[] byArray) {
        byte[] byArray2 = new byte[this._digest.getDigestSize()];
        this.doHash(byArray, byArray2);
        return byArray2;
    }

    private void doHash(byte[] byArray, byte[] byArray2) {
        this._digest.update(byArray, 0, byArray.length);
        this._digest.doFinal(byArray2, 0);
    }

    private byte[] hashgen(byte[] byArray, int n) {
        int n2 = this._digest.getDigestSize();
        int n3 = n / 8 / n2;
        byte[] byArray2 = new byte[byArray.length];
        System.arraycopy(byArray, 0, byArray2, 0, byArray.length);
        byte[] byArray3 = new byte[n / 8];
        byte[] byArray4 = new byte[this._digest.getDigestSize()];
        for (int i = 0; i <= n3; ++i) {
            this.doHash(byArray2, byArray4);
            int n4 = byArray3.length - i * byArray4.length > byArray4.length ? byArray4.length : byArray3.length - i * byArray4.length;
            System.arraycopy(byArray4, 0, byArray3, i * byArray4.length, n4);
            this.addTo(byArray2, ONE);
        }
        return byArray3;
    }

    static {
        seedlens.put("SHA-1", Integers.valueOf(440));
        seedlens.put("SHA-224", Integers.valueOf(440));
        seedlens.put("SHA-256", Integers.valueOf(440));
        seedlens.put("SHA-512/256", Integers.valueOf(440));
        seedlens.put("SHA-512/224", Integers.valueOf(440));
        seedlens.put("SHA-384", Integers.valueOf(888));
        seedlens.put("SHA-512", Integers.valueOf(888));
    }
}

