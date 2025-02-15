/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;

public class SSL3Mac
implements Mac {
    private static final byte IPAD_BYTE = 54;
    private static final byte OPAD_BYTE = 92;
    static final byte[] IPAD = SSL3Mac.genPad((byte)54, 48);
    static final byte[] OPAD = SSL3Mac.genPad((byte)92, 48);
    private Digest digest;
    private int padLength;
    private byte[] secret;

    public SSL3Mac(Digest digest) {
        this.digest = digest;
        this.padLength = digest.getDigestSize() == 20 ? 40 : 48;
    }

    public String getAlgorithmName() {
        return this.digest.getAlgorithmName() + "/SSL3MAC";
    }

    public Digest getUnderlyingDigest() {
        return this.digest;
    }

    public void init(CipherParameters cipherParameters) {
        this.secret = Arrays.clone(((KeyParameter)cipherParameters).getKey());
        this.reset();
    }

    public int getMacSize() {
        return this.digest.getDigestSize();
    }

    public void update(byte by) {
        this.digest.update(by);
    }

    public void update(byte[] byArray, int n, int n2) {
        this.digest.update(byArray, n, n2);
    }

    public int doFinal(byte[] byArray, int n) {
        byte[] byArray2 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(byArray2, 0);
        this.digest.update(this.secret, 0, this.secret.length);
        this.digest.update(OPAD, 0, this.padLength);
        this.digest.update(byArray2, 0, byArray2.length);
        int n2 = this.digest.doFinal(byArray, n);
        this.reset();
        return n2;
    }

    public void reset() {
        this.digest.reset();
        this.digest.update(this.secret, 0, this.secret.length);
        this.digest.update(IPAD, 0, this.padLength);
    }

    private static byte[] genPad(byte by, int n) {
        byte[] byArray = new byte[n];
        Arrays.fill(byArray, by);
        return byArray;
    }
}

