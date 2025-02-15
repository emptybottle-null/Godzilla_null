/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Arrays;

public class Blake2bDigest
implements ExtendedDigest {
    private static final long[] blake2b_IV = new long[]{7640891576956012808L, -4942790177534073029L, 4354685564936845355L, -6534734903238641935L, 5840696475078001361L, -7276294671716946913L, 2270897969802886507L, 6620516959819538809L};
    private static final byte[][] blake2b_sigma = new byte[][]{{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, {14, 10, 4, 8, 9, 15, 13, 6, 1, 12, 0, 2, 11, 7, 5, 3}, {11, 8, 12, 0, 5, 2, 15, 13, 10, 14, 3, 6, 7, 1, 9, 4}, {7, 9, 3, 1, 13, 12, 11, 14, 2, 6, 5, 10, 4, 0, 15, 8}, {9, 0, 5, 7, 2, 4, 10, 15, 14, 1, 11, 12, 6, 8, 3, 13}, {2, 12, 6, 10, 0, 11, 8, 3, 4, 13, 7, 5, 15, 14, 1, 9}, {12, 5, 1, 15, 14, 13, 4, 10, 0, 7, 6, 3, 9, 2, 8, 11}, {13, 11, 7, 14, 12, 1, 3, 9, 5, 0, 15, 4, 8, 6, 2, 10}, {6, 15, 14, 9, 11, 3, 0, 8, 12, 2, 13, 7, 1, 4, 10, 5}, {10, 2, 8, 4, 7, 6, 1, 5, 15, 11, 9, 14, 3, 12, 13, 0}, {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, {14, 10, 4, 8, 9, 15, 13, 6, 1, 12, 0, 2, 11, 7, 5, 3}};
    private static int rOUNDS = 12;
    private static final int BLOCK_LENGTH_BYTES = 128;
    private int digestLength = 64;
    private int keyLength = 0;
    private byte[] salt = null;
    private byte[] personalization = null;
    private byte[] key = null;
    private byte[] buffer = null;
    private int bufferPos = 0;
    private long[] internalState = new long[16];
    private long[] chainValue = null;
    private long t0 = 0L;
    private long t1 = 0L;
    private long f0 = 0L;

    public Blake2bDigest() {
        this(512);
    }

    public Blake2bDigest(Blake2bDigest blake2bDigest) {
        this.bufferPos = blake2bDigest.bufferPos;
        this.buffer = Arrays.clone(blake2bDigest.buffer);
        this.keyLength = blake2bDigest.keyLength;
        this.key = Arrays.clone(blake2bDigest.key);
        this.digestLength = blake2bDigest.digestLength;
        this.chainValue = Arrays.clone(blake2bDigest.chainValue);
        this.personalization = Arrays.clone(blake2bDigest.personalization);
        this.salt = Arrays.clone(blake2bDigest.salt);
        this.t0 = blake2bDigest.t0;
        this.t1 = blake2bDigest.t1;
        this.f0 = blake2bDigest.f0;
    }

    public Blake2bDigest(int n) {
        if (n != 160 && n != 256 && n != 384 && n != 512) {
            throw new IllegalArgumentException("Blake2b digest restricted to one of [160, 256, 384, 512]");
        }
        this.buffer = new byte[128];
        this.keyLength = 0;
        this.digestLength = n / 8;
        this.init();
    }

    public Blake2bDigest(byte[] byArray) {
        this.buffer = new byte[128];
        if (byArray != null) {
            this.key = new byte[byArray.length];
            System.arraycopy(byArray, 0, this.key, 0, byArray.length);
            if (byArray.length > 64) {
                throw new IllegalArgumentException("Keys > 64 are not supported");
            }
            this.keyLength = byArray.length;
            System.arraycopy(byArray, 0, this.buffer, 0, byArray.length);
            this.bufferPos = 128;
        }
        this.digestLength = 64;
        this.init();
    }

    public Blake2bDigest(byte[] byArray, int n, byte[] byArray2, byte[] byArray3) {
        this.buffer = new byte[128];
        if (n < 1 || n > 64) {
            throw new IllegalArgumentException("Invalid digest length (required: 1 - 64)");
        }
        this.digestLength = n;
        if (byArray2 != null) {
            if (byArray2.length != 16) {
                throw new IllegalArgumentException("salt length must be exactly 16 bytes");
            }
            this.salt = new byte[16];
            System.arraycopy(byArray2, 0, this.salt, 0, byArray2.length);
        }
        if (byArray3 != null) {
            if (byArray3.length != 16) {
                throw new IllegalArgumentException("personalization length must be exactly 16 bytes");
            }
            this.personalization = new byte[16];
            System.arraycopy(byArray3, 0, this.personalization, 0, byArray3.length);
        }
        if (byArray != null) {
            this.key = new byte[byArray.length];
            System.arraycopy(byArray, 0, this.key, 0, byArray.length);
            if (byArray.length > 64) {
                throw new IllegalArgumentException("Keys > 64 are not supported");
            }
            this.keyLength = byArray.length;
            System.arraycopy(byArray, 0, this.buffer, 0, byArray.length);
            this.bufferPos = 128;
        }
        this.init();
    }

    private void init() {
        if (this.chainValue == null) {
            this.chainValue = new long[8];
            this.chainValue[0] = blake2b_IV[0] ^ (long)(this.digestLength | this.keyLength << 8 | 0x1010000);
            this.chainValue[1] = blake2b_IV[1];
            this.chainValue[2] = blake2b_IV[2];
            this.chainValue[3] = blake2b_IV[3];
            this.chainValue[4] = blake2b_IV[4];
            this.chainValue[5] = blake2b_IV[5];
            if (this.salt != null) {
                this.chainValue[4] = this.chainValue[4] ^ this.bytes2long(this.salt, 0);
                this.chainValue[5] = this.chainValue[5] ^ this.bytes2long(this.salt, 8);
            }
            this.chainValue[6] = blake2b_IV[6];
            this.chainValue[7] = blake2b_IV[7];
            if (this.personalization != null) {
                this.chainValue[6] = this.chainValue[6] ^ this.bytes2long(this.personalization, 0);
                this.chainValue[7] = this.chainValue[7] ^ this.bytes2long(this.personalization, 8);
            }
        }
    }

    private void initializeInternalState() {
        System.arraycopy(this.chainValue, 0, this.internalState, 0, this.chainValue.length);
        System.arraycopy(blake2b_IV, 0, this.internalState, this.chainValue.length, 4);
        this.internalState[12] = this.t0 ^ blake2b_IV[4];
        this.internalState[13] = this.t1 ^ blake2b_IV[5];
        this.internalState[14] = this.f0 ^ blake2b_IV[6];
        this.internalState[15] = blake2b_IV[7];
    }

    public void update(byte by) {
        int n = 0;
        n = 128 - this.bufferPos;
        if (n == 0) {
            this.t0 += 128L;
            if (this.t0 == 0L) {
                ++this.t1;
            }
        } else {
            this.buffer[this.bufferPos] = by;
            ++this.bufferPos;
            return;
        }
        this.compress(this.buffer, 0);
        Arrays.fill(this.buffer, (byte)0);
        this.buffer[0] = by;
        this.bufferPos = 1;
    }

    public void update(byte[] byArray, int n, int n2) {
        int n3;
        if (byArray == null || n2 == 0) {
            return;
        }
        int n4 = 0;
        if (this.bufferPos != 0) {
            n4 = 128 - this.bufferPos;
            if (n4 < n2) {
                System.arraycopy(byArray, n, this.buffer, this.bufferPos, n4);
                this.t0 += 128L;
                if (this.t0 == 0L) {
                    ++this.t1;
                }
                this.compress(this.buffer, 0);
                this.bufferPos = 0;
                Arrays.fill(this.buffer, (byte)0);
            } else {
                System.arraycopy(byArray, n, this.buffer, this.bufferPos, n2);
                this.bufferPos += n2;
                return;
            }
        }
        int n5 = n + n2 - 128;
        for (n3 = n + n4; n3 < n5; n3 += 128) {
            this.t0 += 128L;
            if (this.t0 == 0L) {
                ++this.t1;
            }
            this.compress(byArray, n3);
        }
        System.arraycopy(byArray, n3, this.buffer, 0, n + n2 - n3);
        this.bufferPos += n + n2 - n3;
    }

    public int doFinal(byte[] byArray, int n) {
        this.f0 = -1L;
        this.t0 += (long)this.bufferPos;
        if (this.t0 < 0L && (long)this.bufferPos > -this.t0) {
            ++this.t1;
        }
        this.compress(this.buffer, 0);
        Arrays.fill(this.buffer, (byte)0);
        Arrays.fill(this.internalState, 0L);
        for (int i = 0; i < this.chainValue.length && i * 8 < this.digestLength; ++i) {
            byte[] byArray2 = this.long2bytes(this.chainValue[i]);
            if (i * 8 < this.digestLength - 8) {
                System.arraycopy(byArray2, 0, byArray, n + i * 8, 8);
                continue;
            }
            System.arraycopy(byArray2, 0, byArray, n + i * 8, this.digestLength - i * 8);
        }
        Arrays.fill(this.chainValue, 0L);
        this.reset();
        return this.digestLength;
    }

    public void reset() {
        this.bufferPos = 0;
        this.f0 = 0L;
        this.t0 = 0L;
        this.t1 = 0L;
        this.chainValue = null;
        Arrays.fill(this.buffer, (byte)0);
        if (this.key != null) {
            System.arraycopy(this.key, 0, this.buffer, 0, this.key.length);
            this.bufferPos = 128;
        }
        this.init();
    }

    private void compress(byte[] byArray, int n) {
        int n2;
        this.initializeInternalState();
        long[] lArray = new long[16];
        for (n2 = 0; n2 < 16; ++n2) {
            lArray[n2] = this.bytes2long(byArray, n + n2 * 8);
        }
        for (n2 = 0; n2 < rOUNDS; ++n2) {
            this.G(lArray[blake2b_sigma[n2][0]], lArray[blake2b_sigma[n2][1]], 0, 4, 8, 12);
            this.G(lArray[blake2b_sigma[n2][2]], lArray[blake2b_sigma[n2][3]], 1, 5, 9, 13);
            this.G(lArray[blake2b_sigma[n2][4]], lArray[blake2b_sigma[n2][5]], 2, 6, 10, 14);
            this.G(lArray[blake2b_sigma[n2][6]], lArray[blake2b_sigma[n2][7]], 3, 7, 11, 15);
            this.G(lArray[blake2b_sigma[n2][8]], lArray[blake2b_sigma[n2][9]], 0, 5, 10, 15);
            this.G(lArray[blake2b_sigma[n2][10]], lArray[blake2b_sigma[n2][11]], 1, 6, 11, 12);
            this.G(lArray[blake2b_sigma[n2][12]], lArray[blake2b_sigma[n2][13]], 2, 7, 8, 13);
            this.G(lArray[blake2b_sigma[n2][14]], lArray[blake2b_sigma[n2][15]], 3, 4, 9, 14);
        }
        for (n2 = 0; n2 < this.chainValue.length; ++n2) {
            this.chainValue[n2] = this.chainValue[n2] ^ this.internalState[n2] ^ this.internalState[n2 + 8];
        }
    }

    private void G(long l, long l2, int n, int n2, int n3, int n4) {
        this.internalState[n] = this.internalState[n] + this.internalState[n2] + l;
        this.internalState[n4] = this.rotr64(this.internalState[n4] ^ this.internalState[n], 32);
        this.internalState[n3] = this.internalState[n3] + this.internalState[n4];
        this.internalState[n2] = this.rotr64(this.internalState[n2] ^ this.internalState[n3], 24);
        this.internalState[n] = this.internalState[n] + this.internalState[n2] + l2;
        this.internalState[n4] = this.rotr64(this.internalState[n4] ^ this.internalState[n], 16);
        this.internalState[n3] = this.internalState[n3] + this.internalState[n4];
        this.internalState[n2] = this.rotr64(this.internalState[n2] ^ this.internalState[n3], 63);
    }

    private long rotr64(long l, int n) {
        return l >>> n | l << 64 - n;
    }

    private final byte[] long2bytes(long l) {
        return new byte[]{(byte)l, (byte)(l >> 8), (byte)(l >> 16), (byte)(l >> 24), (byte)(l >> 32), (byte)(l >> 40), (byte)(l >> 48), (byte)(l >> 56)};
    }

    private final long bytes2long(byte[] byArray, int n) {
        return (long)byArray[n] & 0xFFL | ((long)byArray[n + 1] & 0xFFL) << 8 | ((long)byArray[n + 2] & 0xFFL) << 16 | ((long)byArray[n + 3] & 0xFFL) << 24 | ((long)byArray[n + 4] & 0xFFL) << 32 | ((long)byArray[n + 5] & 0xFFL) << 40 | ((long)byArray[n + 6] & 0xFFL) << 48 | ((long)byArray[n + 7] & 0xFFL) << 56;
    }

    public String getAlgorithmName() {
        return "Blake2b";
    }

    public int getDigestSize() {
        return this.digestLength;
    }

    public int getByteLength() {
        return 128;
    }

    public void clearKey() {
        if (this.key != null) {
            Arrays.fill(this.key, (byte)0);
            Arrays.fill(this.buffer, (byte)0);
        }
    }

    public void clearSalt() {
        if (this.salt != null) {
            Arrays.fill(this.salt, (byte)0);
        }
    }
}

