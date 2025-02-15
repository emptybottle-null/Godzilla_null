/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.macs;

import java.util.Hashtable;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Memoable;

public class HMac
implements Mac {
    private static final byte IPAD = 54;
    private static final byte OPAD = 92;
    private Digest digest;
    private int digestSize;
    private int blockLength;
    private Memoable ipadState;
    private Memoable opadState;
    private byte[] inputPad;
    private byte[] outputBuf;
    private static Hashtable blockLengths = new Hashtable();

    private static int getByteLength(Digest digest) {
        if (digest instanceof ExtendedDigest) {
            return ((ExtendedDigest)digest).getByteLength();
        }
        Integer n = (Integer)blockLengths.get(digest.getAlgorithmName());
        if (n == null) {
            throw new IllegalArgumentException("unknown digest passed: " + digest.getAlgorithmName());
        }
        return n;
    }

    public HMac(Digest digest) {
        this(digest, HMac.getByteLength(digest));
    }

    private HMac(Digest digest, int n) {
        this.digest = digest;
        this.digestSize = digest.getDigestSize();
        this.blockLength = n;
        this.inputPad = new byte[this.blockLength];
        this.outputBuf = new byte[this.blockLength + this.digestSize];
    }

    public String getAlgorithmName() {
        return this.digest.getAlgorithmName() + "/HMAC";
    }

    public Digest getUnderlyingDigest() {
        return this.digest;
    }

    public void init(CipherParameters cipherParameters) {
        this.digest.reset();
        byte[] byArray = ((KeyParameter)cipherParameters).getKey();
        int n = byArray.length;
        if (n > this.blockLength) {
            this.digest.update(byArray, 0, n);
            this.digest.doFinal(this.inputPad, 0);
            n = this.digestSize;
        } else {
            System.arraycopy(byArray, 0, this.inputPad, 0, n);
        }
        for (int i = n; i < this.inputPad.length; ++i) {
            this.inputPad[i] = 0;
        }
        System.arraycopy(this.inputPad, 0, this.outputBuf, 0, this.blockLength);
        HMac.xorPad(this.inputPad, this.blockLength, (byte)54);
        HMac.xorPad(this.outputBuf, this.blockLength, (byte)92);
        if (this.digest instanceof Memoable) {
            this.opadState = ((Memoable)((Object)this.digest)).copy();
            ((Digest)((Object)this.opadState)).update(this.outputBuf, 0, this.blockLength);
        }
        this.digest.update(this.inputPad, 0, this.inputPad.length);
        if (this.digest instanceof Memoable) {
            this.ipadState = ((Memoable)((Object)this.digest)).copy();
        }
    }

    public int getMacSize() {
        return this.digestSize;
    }

    public void update(byte by) {
        this.digest.update(by);
    }

    public void update(byte[] byArray, int n, int n2) {
        this.digest.update(byArray, n, n2);
    }

    public int doFinal(byte[] byArray, int n) {
        this.digest.doFinal(this.outputBuf, this.blockLength);
        if (this.opadState != null) {
            ((Memoable)((Object)this.digest)).reset(this.opadState);
            this.digest.update(this.outputBuf, this.blockLength, this.digest.getDigestSize());
        } else {
            this.digest.update(this.outputBuf, 0, this.outputBuf.length);
        }
        int n2 = this.digest.doFinal(byArray, n);
        for (int i = this.blockLength; i < this.outputBuf.length; ++i) {
            this.outputBuf[i] = 0;
        }
        if (this.ipadState != null) {
            ((Memoable)((Object)this.digest)).reset(this.ipadState);
        } else {
            this.digest.update(this.inputPad, 0, this.inputPad.length);
        }
        return n2;
    }

    public void reset() {
        this.digest.reset();
        this.digest.update(this.inputPad, 0, this.inputPad.length);
    }

    private static void xorPad(byte[] byArray, int n, byte by) {
        int n2 = 0;
        while (n2 < n) {
            int n3 = n2++;
            byArray[n3] = (byte)(byArray[n3] ^ by);
        }
    }

    static {
        blockLengths.put("GOST3411", Integers.valueOf(32));
        blockLengths.put("MD2", Integers.valueOf(16));
        blockLengths.put("MD4", Integers.valueOf(64));
        blockLengths.put("MD5", Integers.valueOf(64));
        blockLengths.put("RIPEMD128", Integers.valueOf(64));
        blockLengths.put("RIPEMD160", Integers.valueOf(64));
        blockLengths.put("SHA-1", Integers.valueOf(64));
        blockLengths.put("SHA-224", Integers.valueOf(64));
        blockLengths.put("SHA-256", Integers.valueOf(64));
        blockLengths.put("SHA-384", Integers.valueOf(128));
        blockLengths.put("SHA-512", Integers.valueOf(128));
        blockLengths.put("Tiger", Integers.valueOf(64));
        blockLengths.put("Whirlpool", Integers.valueOf(64));
    }
}

