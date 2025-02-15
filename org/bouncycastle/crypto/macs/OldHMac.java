/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.params.KeyParameter;

public class OldHMac
implements Mac {
    private static final int BLOCK_LENGTH = 64;
    private static final byte IPAD = 54;
    private static final byte OPAD = 92;
    private Digest digest;
    private int digestSize;
    private byte[] inputPad = new byte[64];
    private byte[] outputPad = new byte[64];

    public OldHMac(Digest digest) {
        this.digest = digest;
        this.digestSize = digest.getDigestSize();
    }

    public String getAlgorithmName() {
        return this.digest.getAlgorithmName() + "/HMAC";
    }

    public Digest getUnderlyingDigest() {
        return this.digest;
    }

    public void init(CipherParameters cipherParameters) {
        int n;
        this.digest.reset();
        byte[] byArray = ((KeyParameter)cipherParameters).getKey();
        if (byArray.length > 64) {
            this.digest.update(byArray, 0, byArray.length);
            this.digest.doFinal(this.inputPad, 0);
            for (n = this.digestSize; n < this.inputPad.length; ++n) {
                this.inputPad[n] = 0;
            }
        } else {
            System.arraycopy(byArray, 0, this.inputPad, 0, byArray.length);
            for (n = byArray.length; n < this.inputPad.length; ++n) {
                this.inputPad[n] = 0;
            }
        }
        this.outputPad = new byte[this.inputPad.length];
        System.arraycopy(this.inputPad, 0, this.outputPad, 0, this.inputPad.length);
        n = 0;
        while (n < this.inputPad.length) {
            int n2 = n++;
            this.inputPad[n2] = (byte)(this.inputPad[n2] ^ 0x36);
        }
        n = 0;
        while (n < this.outputPad.length) {
            int n3 = n++;
            this.outputPad[n3] = (byte)(this.outputPad[n3] ^ 0x5C);
        }
        this.digest.update(this.inputPad, 0, this.inputPad.length);
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
        byte[] byArray2 = new byte[this.digestSize];
        this.digest.doFinal(byArray2, 0);
        this.digest.update(this.outputPad, 0, this.outputPad.length);
        this.digest.update(byArray2, 0, byArray2.length);
        int n2 = this.digest.doFinal(byArray, n);
        this.reset();
        return n2;
    }

    public void reset() {
        this.digest.reset();
        this.digest.update(this.inputPad, 0, this.inputPad.length);
    }
}

