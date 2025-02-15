/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;

public class SipHash
implements Mac {
    protected final int c;
    protected final int d;
    protected long k0;
    protected long k1;
    protected long v0;
    protected long v1;
    protected long v2;
    protected long v3;
    protected long m = 0L;
    protected int wordPos = 0;
    protected int wordCount = 0;

    public SipHash() {
        this.c = 2;
        this.d = 4;
    }

    public SipHash(int n, int n2) {
        this.c = n;
        this.d = n2;
    }

    public String getAlgorithmName() {
        return "SipHash-" + this.c + "-" + this.d;
    }

    public int getMacSize() {
        return 8;
    }

    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("'params' must be an instance of KeyParameter");
        }
        KeyParameter keyParameter = (KeyParameter)cipherParameters;
        byte[] byArray = keyParameter.getKey();
        if (byArray.length != 16) {
            throw new IllegalArgumentException("'params' must be a 128-bit key");
        }
        this.k0 = Pack.littleEndianToLong(byArray, 0);
        this.k1 = Pack.littleEndianToLong(byArray, 8);
        this.reset();
    }

    public void update(byte by) throws IllegalStateException {
        this.m >>>= 8;
        this.m |= ((long)by & 0xFFL) << 56;
        if (++this.wordPos == 8) {
            this.processMessageWord();
            this.wordPos = 0;
        }
    }

    public void update(byte[] byArray, int n, int n2) throws DataLengthException, IllegalStateException {
        int n3;
        int n4 = n2 & 0xFFFFFFF8;
        if (this.wordPos == 0) {
            for (n3 = 0; n3 < n4; n3 += 8) {
                this.m = Pack.littleEndianToLong(byArray, n + n3);
                this.processMessageWord();
            }
            while (n3 < n2) {
                this.m >>>= 8;
                this.m |= ((long)byArray[n + n3] & 0xFFL) << 56;
                ++n3;
            }
            this.wordPos = n2 - n4;
        } else {
            int n5 = this.wordPos << 3;
            while (n3 < n4) {
                long l = Pack.littleEndianToLong(byArray, n + n3);
                this.m = l << n5 | this.m >>> -n5;
                this.processMessageWord();
                this.m = l;
                n3 += 8;
            }
            while (n3 < n2) {
                this.m >>>= 8;
                this.m |= ((long)byArray[n + n3] & 0xFFL) << 56;
                if (++this.wordPos == 8) {
                    this.processMessageWord();
                    this.wordPos = 0;
                }
                ++n3;
            }
        }
    }

    public long doFinal() throws DataLengthException, IllegalStateException {
        this.m >>>= 7 - this.wordPos << 3;
        this.m >>>= 8;
        this.m |= ((long)((this.wordCount << 3) + this.wordPos) & 0xFFL) << 56;
        this.processMessageWord();
        this.v2 ^= 0xFFL;
        this.applySipRounds(this.d);
        long l = this.v0 ^ this.v1 ^ this.v2 ^ this.v3;
        this.reset();
        return l;
    }

    public int doFinal(byte[] byArray, int n) throws DataLengthException, IllegalStateException {
        long l = this.doFinal();
        Pack.longToLittleEndian(l, byArray, n);
        return 8;
    }

    public void reset() {
        this.v0 = this.k0 ^ 0x736F6D6570736575L;
        this.v1 = this.k1 ^ 0x646F72616E646F6DL;
        this.v2 = this.k0 ^ 0x6C7967656E657261L;
        this.v3 = this.k1 ^ 0x7465646279746573L;
        this.m = 0L;
        this.wordPos = 0;
        this.wordCount = 0;
    }

    protected void processMessageWord() {
        ++this.wordCount;
        this.v3 ^= this.m;
        this.applySipRounds(this.c);
        this.v0 ^= this.m;
    }

    protected void applySipRounds(int n) {
        long l = this.v0;
        long l2 = this.v1;
        long l3 = this.v2;
        long l4 = this.v3;
        for (int i = 0; i < n; ++i) {
            l += l2;
            l3 += l4;
            l2 = SipHash.rotateLeft(l2, 13);
            l4 = SipHash.rotateLeft(l4, 16);
            l2 ^= l;
            l4 ^= l3;
            l = SipHash.rotateLeft(l, 32);
            l3 += l2;
            l += l4;
            l2 = SipHash.rotateLeft(l2, 17);
            l4 = SipHash.rotateLeft(l4, 21);
            l2 ^= l3;
            l4 ^= l;
            l3 = SipHash.rotateLeft(l3, 32);
        }
        this.v0 = l;
        this.v1 = l2;
        this.v2 = l3;
        this.v3 = l4;
    }

    protected static long rotateLeft(long l, int n) {
        return l << n | l >>> -n;
    }
}

