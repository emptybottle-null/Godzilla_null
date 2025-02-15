/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.TweakableBlockCipherParameters;

public class ThreefishEngine
implements BlockCipher {
    public static final int BLOCKSIZE_256 = 256;
    public static final int BLOCKSIZE_512 = 512;
    public static final int BLOCKSIZE_1024 = 1024;
    private static final int TWEAK_SIZE_BYTES = 16;
    private static final int TWEAK_SIZE_WORDS = 2;
    private static final int ROUNDS_256 = 72;
    private static final int ROUNDS_512 = 72;
    private static final int ROUNDS_1024 = 80;
    private static final int MAX_ROUNDS = 80;
    private static final long C_240 = 2004413935125273122L;
    private static int[] MOD9 = new int[80];
    private static int[] MOD17 = new int[MOD9.length];
    private static int[] MOD5 = new int[MOD9.length];
    private static int[] MOD3 = new int[MOD9.length];
    private int blocksizeBytes;
    private int blocksizeWords;
    private long[] currentBlock;
    private long[] t = new long[5];
    private long[] kw;
    private ThreefishCipher cipher;
    private boolean forEncryption;

    public ThreefishEngine(int n) {
        this.blocksizeBytes = n / 8;
        this.blocksizeWords = this.blocksizeBytes / 8;
        this.currentBlock = new long[this.blocksizeWords];
        this.kw = new long[2 * this.blocksizeWords + 1];
        switch (n) {
            case 256: {
                this.cipher = new Threefish256Cipher(this.kw, this.t);
                break;
            }
            case 512: {
                this.cipher = new Threefish512Cipher(this.kw, this.t);
                break;
            }
            case 1024: {
                this.cipher = new Threefish1024Cipher(this.kw, this.t);
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid blocksize - Threefish is defined with block size of 256, 512, or 1024 bits");
            }
        }
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] byArray;
        byte[] byArray2;
        Object object;
        if (cipherParameters instanceof TweakableBlockCipherParameters) {
            object = (TweakableBlockCipherParameters)cipherParameters;
            byArray2 = ((TweakableBlockCipherParameters)object).getKey().getKey();
            byArray = ((TweakableBlockCipherParameters)object).getTweak();
        } else if (cipherParameters instanceof KeyParameter) {
            byArray2 = ((KeyParameter)cipherParameters).getKey();
            byArray = null;
        } else {
            throw new IllegalArgumentException("Invalid parameter passed to Threefish init - " + cipherParameters.getClass().getName());
        }
        object = null;
        long[] lArray = null;
        if (byArray2 != null) {
            if (byArray2.length != this.blocksizeBytes) {
                throw new IllegalArgumentException("Threefish key must be same size as block (" + this.blocksizeBytes + " bytes)");
            }
            object = new long[this.blocksizeWords];
            for (int i = 0; i < ((Object)object).length; ++i) {
                object[i] = ThreefishEngine.bytesToWord(byArray2, i * 8);
            }
        }
        if (byArray != null) {
            if (byArray.length != 16) {
                throw new IllegalArgumentException("Threefish tweak must be 16 bytes");
            }
            lArray = new long[]{ThreefishEngine.bytesToWord(byArray, 0), ThreefishEngine.bytesToWord(byArray, 8)};
        }
        this.init(bl, (long[])object, lArray);
    }

    public void init(boolean bl, long[] lArray, long[] lArray2) {
        this.forEncryption = bl;
        if (lArray != null) {
            this.setKey(lArray);
        }
        if (lArray2 != null) {
            this.setTweak(lArray2);
        }
    }

    private void setKey(long[] lArray) {
        if (lArray.length != this.blocksizeWords) {
            throw new IllegalArgumentException("Threefish key must be same size as block (" + this.blocksizeWords + " words)");
        }
        long l = 2004413935125273122L;
        for (int i = 0; i < this.blocksizeWords; ++i) {
            this.kw[i] = lArray[i];
            l ^= this.kw[i];
        }
        this.kw[this.blocksizeWords] = l;
        System.arraycopy(this.kw, 0, this.kw, this.blocksizeWords + 1, this.blocksizeWords);
    }

    private void setTweak(long[] lArray) {
        if (lArray.length != 2) {
            throw new IllegalArgumentException("Tweak must be 2 words.");
        }
        this.t[0] = lArray[0];
        this.t[1] = lArray[1];
        this.t[2] = this.t[0] ^ this.t[1];
        this.t[3] = this.t[0];
        this.t[4] = this.t[1];
    }

    public String getAlgorithmName() {
        return "Threefish-" + this.blocksizeBytes * 8;
    }

    public int getBlockSize() {
        return this.blocksizeBytes;
    }

    public void reset() {
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        int n3;
        if (n + this.blocksizeBytes > byArray.length) {
            throw new DataLengthException("Input buffer too short");
        }
        if (n2 + this.blocksizeBytes > byArray2.length) {
            throw new OutputLengthException("Output buffer too short");
        }
        for (n3 = 0; n3 < this.blocksizeBytes; n3 += 8) {
            this.currentBlock[n3 >> 3] = ThreefishEngine.bytesToWord(byArray, n + n3);
        }
        this.processBlock(this.currentBlock, this.currentBlock);
        for (n3 = 0; n3 < this.blocksizeBytes; n3 += 8) {
            ThreefishEngine.wordToBytes(this.currentBlock[n3 >> 3], byArray2, n2 + n3);
        }
        return this.blocksizeBytes;
    }

    public int processBlock(long[] lArray, long[] lArray2) throws DataLengthException, IllegalStateException {
        if (this.kw[this.blocksizeWords] == 0L) {
            throw new IllegalStateException("Threefish engine not initialised");
        }
        if (lArray.length != this.blocksizeWords) {
            throw new DataLengthException("Input buffer too short");
        }
        if (lArray2.length != this.blocksizeWords) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (this.forEncryption) {
            this.cipher.encryptBlock(lArray, lArray2);
        } else {
            this.cipher.decryptBlock(lArray, lArray2);
        }
        return this.blocksizeWords;
    }

    public static long bytesToWord(byte[] byArray, int n) {
        if (n + 8 > byArray.length) {
            throw new IllegalArgumentException();
        }
        long l = 0L;
        int n2 = n;
        l = (long)byArray[n2++] & 0xFFL;
        l |= ((long)byArray[n2++] & 0xFFL) << 8;
        l |= ((long)byArray[n2++] & 0xFFL) << 16;
        l |= ((long)byArray[n2++] & 0xFFL) << 24;
        l |= ((long)byArray[n2++] & 0xFFL) << 32;
        l |= ((long)byArray[n2++] & 0xFFL) << 40;
        l |= ((long)byArray[n2++] & 0xFFL) << 48;
        return l |= ((long)byArray[n2++] & 0xFFL) << 56;
    }

    public static void wordToBytes(long l, byte[] byArray, int n) {
        if (n + 8 > byArray.length) {
            throw new IllegalArgumentException();
        }
        int n2 = n;
        byArray[n2++] = (byte)l;
        byArray[n2++] = (byte)(l >> 8);
        byArray[n2++] = (byte)(l >> 16);
        byArray[n2++] = (byte)(l >> 24);
        byArray[n2++] = (byte)(l >> 32);
        byArray[n2++] = (byte)(l >> 40);
        byArray[n2++] = (byte)(l >> 48);
        byArray[n2++] = (byte)(l >> 56);
    }

    static long rotlXor(long l, int n, long l2) {
        return (l << n | l >>> -n) ^ l2;
    }

    static long xorRotr(long l, int n, long l2) {
        long l3 = l ^ l2;
        return l3 >>> n | l3 << -n;
    }

    static {
        for (int i = 0; i < MOD9.length; ++i) {
            ThreefishEngine.MOD17[i] = i % 17;
            ThreefishEngine.MOD9[i] = i % 9;
            ThreefishEngine.MOD5[i] = i % 5;
            ThreefishEngine.MOD3[i] = i % 3;
        }
    }

    private static final class Threefish1024Cipher
    extends ThreefishCipher {
        private static final int ROTATION_0_0 = 24;
        private static final int ROTATION_0_1 = 13;
        private static final int ROTATION_0_2 = 8;
        private static final int ROTATION_0_3 = 47;
        private static final int ROTATION_0_4 = 8;
        private static final int ROTATION_0_5 = 17;
        private static final int ROTATION_0_6 = 22;
        private static final int ROTATION_0_7 = 37;
        private static final int ROTATION_1_0 = 38;
        private static final int ROTATION_1_1 = 19;
        private static final int ROTATION_1_2 = 10;
        private static final int ROTATION_1_3 = 55;
        private static final int ROTATION_1_4 = 49;
        private static final int ROTATION_1_5 = 18;
        private static final int ROTATION_1_6 = 23;
        private static final int ROTATION_1_7 = 52;
        private static final int ROTATION_2_0 = 33;
        private static final int ROTATION_2_1 = 4;
        private static final int ROTATION_2_2 = 51;
        private static final int ROTATION_2_3 = 13;
        private static final int ROTATION_2_4 = 34;
        private static final int ROTATION_2_5 = 41;
        private static final int ROTATION_2_6 = 59;
        private static final int ROTATION_2_7 = 17;
        private static final int ROTATION_3_0 = 5;
        private static final int ROTATION_3_1 = 20;
        private static final int ROTATION_3_2 = 48;
        private static final int ROTATION_3_3 = 41;
        private static final int ROTATION_3_4 = 47;
        private static final int ROTATION_3_5 = 28;
        private static final int ROTATION_3_6 = 16;
        private static final int ROTATION_3_7 = 25;
        private static final int ROTATION_4_0 = 41;
        private static final int ROTATION_4_1 = 9;
        private static final int ROTATION_4_2 = 37;
        private static final int ROTATION_4_3 = 31;
        private static final int ROTATION_4_4 = 12;
        private static final int ROTATION_4_5 = 47;
        private static final int ROTATION_4_6 = 44;
        private static final int ROTATION_4_7 = 30;
        private static final int ROTATION_5_0 = 16;
        private static final int ROTATION_5_1 = 34;
        private static final int ROTATION_5_2 = 56;
        private static final int ROTATION_5_3 = 51;
        private static final int ROTATION_5_4 = 4;
        private static final int ROTATION_5_5 = 53;
        private static final int ROTATION_5_6 = 42;
        private static final int ROTATION_5_7 = 41;
        private static final int ROTATION_6_0 = 31;
        private static final int ROTATION_6_1 = 44;
        private static final int ROTATION_6_2 = 47;
        private static final int ROTATION_6_3 = 46;
        private static final int ROTATION_6_4 = 19;
        private static final int ROTATION_6_5 = 42;
        private static final int ROTATION_6_6 = 44;
        private static final int ROTATION_6_7 = 25;
        private static final int ROTATION_7_0 = 9;
        private static final int ROTATION_7_1 = 48;
        private static final int ROTATION_7_2 = 35;
        private static final int ROTATION_7_3 = 52;
        private static final int ROTATION_7_4 = 23;
        private static final int ROTATION_7_5 = 31;
        private static final int ROTATION_7_6 = 37;
        private static final int ROTATION_7_7 = 20;

        public Threefish1024Cipher(long[] lArray, long[] lArray2) {
            super(lArray, lArray2);
        }

        void encryptBlock(long[] lArray, long[] lArray2) {
            long[] lArray3 = this.kw;
            long[] lArray4 = this.t;
            int[] nArray = MOD17;
            int[] nArray2 = MOD3;
            if (lArray3.length != 33) {
                throw new IllegalArgumentException();
            }
            if (lArray4.length != 5) {
                throw new IllegalArgumentException();
            }
            long l = lArray[0];
            long l2 = lArray[1];
            long l3 = lArray[2];
            long l4 = lArray[3];
            long l5 = lArray[4];
            long l6 = lArray[5];
            long l7 = lArray[6];
            long l8 = lArray[7];
            long l9 = lArray[8];
            long l10 = lArray[9];
            long l11 = lArray[10];
            long l12 = lArray[11];
            long l13 = lArray[12];
            long l14 = lArray[13];
            long l15 = lArray[14];
            long l16 = lArray[15];
            l += lArray3[0];
            l2 += lArray3[1];
            l3 += lArray3[2];
            l4 += lArray3[3];
            l5 += lArray3[4];
            l6 += lArray3[5];
            l7 += lArray3[6];
            l8 += lArray3[7];
            l9 += lArray3[8];
            l10 += lArray3[9];
            l11 += lArray3[10];
            l12 += lArray3[11];
            l13 += lArray3[12];
            l14 += lArray3[13] + lArray4[0];
            l15 += lArray3[14] + lArray4[1];
            l16 += lArray3[15];
            for (int i = 1; i < 20; i += 2) {
                int n = nArray[i];
                int n2 = nArray2[i];
                l += l2;
                l2 = ThreefishEngine.rotlXor(l2, 24, l);
                l3 += l4;
                l4 = ThreefishEngine.rotlXor(l4, 13, l3);
                l5 += l6;
                l6 = ThreefishEngine.rotlXor(l6, 8, l5);
                l7 += l8;
                l8 = ThreefishEngine.rotlXor(l8, 47, l7);
                l9 += l10;
                l10 = ThreefishEngine.rotlXor(l10, 8, l9);
                l11 += l12;
                l12 = ThreefishEngine.rotlXor(l12, 17, l11);
                l13 += l14;
                l14 = ThreefishEngine.rotlXor(l14, 22, l13);
                l15 += l16;
                l16 = ThreefishEngine.rotlXor(l16, 37, l15);
                l += l10;
                l10 = ThreefishEngine.rotlXor(l10, 38, l);
                l3 += l14;
                l14 = ThreefishEngine.rotlXor(l14, 19, l3);
                l7 += l12;
                l12 = ThreefishEngine.rotlXor(l12, 10, l7);
                l5 += l16;
                l16 = ThreefishEngine.rotlXor(l16, 55, l5);
                l11 += l8;
                l8 = ThreefishEngine.rotlXor(l8, 49, l11);
                l13 += l4;
                l4 = ThreefishEngine.rotlXor(l4, 18, l13);
                l15 += l6;
                l6 = ThreefishEngine.rotlXor(l6, 23, l15);
                l9 += l2;
                l2 = ThreefishEngine.rotlXor(l2, 52, l9);
                l += l8;
                l8 = ThreefishEngine.rotlXor(l8, 33, l);
                l3 += l6;
                l6 = ThreefishEngine.rotlXor(l6, 4, l3);
                l5 += l4;
                l4 = ThreefishEngine.rotlXor(l4, 51, l5);
                l7 += l2;
                l2 = ThreefishEngine.rotlXor(l2, 13, l7);
                l13 += l16;
                l16 = ThreefishEngine.rotlXor(l16, 34, l13);
                l15 += l14;
                l14 = ThreefishEngine.rotlXor(l14, 41, l15);
                l9 += l12;
                l12 = ThreefishEngine.rotlXor(l12, 59, l9);
                l11 += l10;
                l10 = ThreefishEngine.rotlXor(l10, 17, l11);
                l += l16;
                l16 = ThreefishEngine.rotlXor(l16, 5, l);
                l3 += l12;
                l12 = ThreefishEngine.rotlXor(l12, 20, l3);
                l7 += l14;
                l14 = ThreefishEngine.rotlXor(l14, 48, l7);
                l5 += l10;
                l10 = ThreefishEngine.rotlXor(l10, 41, l5);
                l15 += l2;
                l2 = ThreefishEngine.rotlXor(l2, 47, l15);
                l9 += l6;
                l6 = ThreefishEngine.rotlXor(l6, 28, l9);
                l11 += l4;
                l4 = ThreefishEngine.rotlXor(l4, 16, l11);
                l13 += l8;
                l8 = ThreefishEngine.rotlXor(l8, 25, l13);
                l += lArray3[n];
                l2 += lArray3[n + 1];
                l3 += lArray3[n + 2];
                l4 += lArray3[n + 3];
                l5 += lArray3[n + 4];
                l6 += lArray3[n + 5];
                l7 += lArray3[n + 6];
                l8 += lArray3[n + 7];
                l9 += lArray3[n + 8];
                l10 += lArray3[n + 9];
                l11 += lArray3[n + 10];
                l12 += lArray3[n + 11];
                l13 += lArray3[n + 12];
                l14 += lArray3[n + 13] + lArray4[n2];
                l15 += lArray3[n + 14] + lArray4[n2 + 1];
                l16 += lArray3[n + 15] + (long)i;
                l += l2;
                l2 = ThreefishEngine.rotlXor(l2, 41, l);
                l3 += l4;
                l4 = ThreefishEngine.rotlXor(l4, 9, l3);
                l5 += l6;
                l6 = ThreefishEngine.rotlXor(l6, 37, l5);
                l7 += l8;
                l8 = ThreefishEngine.rotlXor(l8, 31, l7);
                l9 += l10;
                l10 = ThreefishEngine.rotlXor(l10, 12, l9);
                l11 += l12;
                l12 = ThreefishEngine.rotlXor(l12, 47, l11);
                l13 += l14;
                l14 = ThreefishEngine.rotlXor(l14, 44, l13);
                l15 += l16;
                l16 = ThreefishEngine.rotlXor(l16, 30, l15);
                l += l10;
                l10 = ThreefishEngine.rotlXor(l10, 16, l);
                l3 += l14;
                l14 = ThreefishEngine.rotlXor(l14, 34, l3);
                l7 += l12;
                l12 = ThreefishEngine.rotlXor(l12, 56, l7);
                l5 += l16;
                l16 = ThreefishEngine.rotlXor(l16, 51, l5);
                l11 += l8;
                l8 = ThreefishEngine.rotlXor(l8, 4, l11);
                l13 += l4;
                l4 = ThreefishEngine.rotlXor(l4, 53, l13);
                l15 += l6;
                l6 = ThreefishEngine.rotlXor(l6, 42, l15);
                l9 += l2;
                l2 = ThreefishEngine.rotlXor(l2, 41, l9);
                l += l8;
                l8 = ThreefishEngine.rotlXor(l8, 31, l);
                l3 += l6;
                l6 = ThreefishEngine.rotlXor(l6, 44, l3);
                l5 += l4;
                l4 = ThreefishEngine.rotlXor(l4, 47, l5);
                l7 += l2;
                l2 = ThreefishEngine.rotlXor(l2, 46, l7);
                l13 += l16;
                l16 = ThreefishEngine.rotlXor(l16, 19, l13);
                l15 += l14;
                l14 = ThreefishEngine.rotlXor(l14, 42, l15);
                l9 += l12;
                l12 = ThreefishEngine.rotlXor(l12, 44, l9);
                l11 += l10;
                l10 = ThreefishEngine.rotlXor(l10, 25, l11);
                l += l16;
                l16 = ThreefishEngine.rotlXor(l16, 9, l);
                l3 += l12;
                l12 = ThreefishEngine.rotlXor(l12, 48, l3);
                l7 += l14;
                l14 = ThreefishEngine.rotlXor(l14, 35, l7);
                l5 += l10;
                l10 = ThreefishEngine.rotlXor(l10, 52, l5);
                l15 += l2;
                l2 = ThreefishEngine.rotlXor(l2, 23, l15);
                l9 += l6;
                l6 = ThreefishEngine.rotlXor(l6, 31, l9);
                l11 += l4;
                l4 = ThreefishEngine.rotlXor(l4, 37, l11);
                l13 += l8;
                l8 = ThreefishEngine.rotlXor(l8, 20, l13);
                l += lArray3[n + 1];
                l2 += lArray3[n + 2];
                l3 += lArray3[n + 3];
                l4 += lArray3[n + 4];
                l5 += lArray3[n + 5];
                l6 += lArray3[n + 6];
                l7 += lArray3[n + 7];
                l8 += lArray3[n + 8];
                l9 += lArray3[n + 9];
                l10 += lArray3[n + 10];
                l11 += lArray3[n + 11];
                l12 += lArray3[n + 12];
                l13 += lArray3[n + 13];
                l14 += lArray3[n + 14] + lArray4[n2 + 1];
                l15 += lArray3[n + 15] + lArray4[n2 + 2];
                l16 += lArray3[n + 16] + (long)i + 1L;
            }
            lArray2[0] = l;
            lArray2[1] = l2;
            lArray2[2] = l3;
            lArray2[3] = l4;
            lArray2[4] = l5;
            lArray2[5] = l6;
            lArray2[6] = l7;
            lArray2[7] = l8;
            lArray2[8] = l9;
            lArray2[9] = l10;
            lArray2[10] = l11;
            lArray2[11] = l12;
            lArray2[12] = l13;
            lArray2[13] = l14;
            lArray2[14] = l15;
            lArray2[15] = l16;
        }

        void decryptBlock(long[] lArray, long[] lArray2) {
            long[] lArray3 = this.kw;
            long[] lArray4 = this.t;
            int[] nArray = MOD17;
            int[] nArray2 = MOD3;
            if (lArray3.length != 33) {
                throw new IllegalArgumentException();
            }
            if (lArray4.length != 5) {
                throw new IllegalArgumentException();
            }
            long l = lArray[0];
            long l2 = lArray[1];
            long l3 = lArray[2];
            long l4 = lArray[3];
            long l5 = lArray[4];
            long l6 = lArray[5];
            long l7 = lArray[6];
            long l8 = lArray[7];
            long l9 = lArray[8];
            long l10 = lArray[9];
            long l11 = lArray[10];
            long l12 = lArray[11];
            long l13 = lArray[12];
            long l14 = lArray[13];
            long l15 = lArray[14];
            long l16 = lArray[15];
            for (int i = 19; i >= 1; i -= 2) {
                int n = nArray[i];
                int n2 = nArray2[i];
                l -= lArray3[n + 1];
                l2 -= lArray3[n + 2];
                l3 -= lArray3[n + 3];
                l4 -= lArray3[n + 4];
                l5 -= lArray3[n + 5];
                l6 -= lArray3[n + 6];
                l7 -= lArray3[n + 7];
                l8 -= lArray3[n + 8];
                l9 -= lArray3[n + 9];
                l10 -= lArray3[n + 10];
                l11 -= lArray3[n + 11];
                l12 -= lArray3[n + 12];
                l13 -= lArray3[n + 13];
                l14 -= lArray3[n + 14] + lArray4[n2 + 1];
                l15 -= lArray3[n + 15] + lArray4[n2 + 2];
                l16 -= lArray3[n + 16] + (long)i + 1L;
                l16 = ThreefishEngine.xorRotr(l16, 9, l);
                l -= l16;
                l12 = ThreefishEngine.xorRotr(l12, 48, l3);
                l3 -= l12;
                l14 = ThreefishEngine.xorRotr(l14, 35, l7);
                l7 -= l14;
                l10 = ThreefishEngine.xorRotr(l10, 52, l5);
                l5 -= l10;
                l2 = ThreefishEngine.xorRotr(l2, 23, l15);
                l15 -= l2;
                l6 = ThreefishEngine.xorRotr(l6, 31, l9);
                l9 -= l6;
                l4 = ThreefishEngine.xorRotr(l4, 37, l11);
                l11 -= l4;
                l8 = ThreefishEngine.xorRotr(l8, 20, l13);
                l13 -= l8;
                l8 = ThreefishEngine.xorRotr(l8, 31, l);
                l -= l8;
                l6 = ThreefishEngine.xorRotr(l6, 44, l3);
                l3 -= l6;
                l4 = ThreefishEngine.xorRotr(l4, 47, l5);
                l5 -= l4;
                l2 = ThreefishEngine.xorRotr(l2, 46, l7);
                l7 -= l2;
                l16 = ThreefishEngine.xorRotr(l16, 19, l13);
                l13 -= l16;
                l14 = ThreefishEngine.xorRotr(l14, 42, l15);
                l15 -= l14;
                l12 = ThreefishEngine.xorRotr(l12, 44, l9);
                l9 -= l12;
                l10 = ThreefishEngine.xorRotr(l10, 25, l11);
                l11 -= l10;
                l10 = ThreefishEngine.xorRotr(l10, 16, l);
                l -= l10;
                l14 = ThreefishEngine.xorRotr(l14, 34, l3);
                l3 -= l14;
                l12 = ThreefishEngine.xorRotr(l12, 56, l7);
                l7 -= l12;
                l16 = ThreefishEngine.xorRotr(l16, 51, l5);
                l5 -= l16;
                l8 = ThreefishEngine.xorRotr(l8, 4, l11);
                l11 -= l8;
                l4 = ThreefishEngine.xorRotr(l4, 53, l13);
                l13 -= l4;
                l6 = ThreefishEngine.xorRotr(l6, 42, l15);
                l15 -= l6;
                l2 = ThreefishEngine.xorRotr(l2, 41, l9);
                l9 -= l2;
                l2 = ThreefishEngine.xorRotr(l2, 41, l);
                l -= l2;
                l4 = ThreefishEngine.xorRotr(l4, 9, l3);
                l3 -= l4;
                l6 = ThreefishEngine.xorRotr(l6, 37, l5);
                l5 -= l6;
                l8 = ThreefishEngine.xorRotr(l8, 31, l7);
                l7 -= l8;
                l10 = ThreefishEngine.xorRotr(l10, 12, l9);
                l9 -= l10;
                l12 = ThreefishEngine.xorRotr(l12, 47, l11);
                l11 -= l12;
                l14 = ThreefishEngine.xorRotr(l14, 44, l13);
                l13 -= l14;
                l16 = ThreefishEngine.xorRotr(l16, 30, l15);
                l15 -= l16;
                l -= lArray3[n];
                l2 -= lArray3[n + 1];
                l3 -= lArray3[n + 2];
                l4 -= lArray3[n + 3];
                l5 -= lArray3[n + 4];
                l6 -= lArray3[n + 5];
                l7 -= lArray3[n + 6];
                l8 -= lArray3[n + 7];
                l9 -= lArray3[n + 8];
                l10 -= lArray3[n + 9];
                l11 -= lArray3[n + 10];
                l12 -= lArray3[n + 11];
                l13 -= lArray3[n + 12];
                l14 -= lArray3[n + 13] + lArray4[n2];
                l15 -= lArray3[n + 14] + lArray4[n2 + 1];
                l16 -= lArray3[n + 15] + (long)i;
                l16 = ThreefishEngine.xorRotr(l16, 5, l);
                l -= l16;
                l12 = ThreefishEngine.xorRotr(l12, 20, l3);
                l3 -= l12;
                l14 = ThreefishEngine.xorRotr(l14, 48, l7);
                l7 -= l14;
                l10 = ThreefishEngine.xorRotr(l10, 41, l5);
                l5 -= l10;
                l2 = ThreefishEngine.xorRotr(l2, 47, l15);
                l15 -= l2;
                l6 = ThreefishEngine.xorRotr(l6, 28, l9);
                l9 -= l6;
                l4 = ThreefishEngine.xorRotr(l4, 16, l11);
                l11 -= l4;
                l8 = ThreefishEngine.xorRotr(l8, 25, l13);
                l13 -= l8;
                l8 = ThreefishEngine.xorRotr(l8, 33, l);
                l -= l8;
                l6 = ThreefishEngine.xorRotr(l6, 4, l3);
                l3 -= l6;
                l4 = ThreefishEngine.xorRotr(l4, 51, l5);
                l5 -= l4;
                l2 = ThreefishEngine.xorRotr(l2, 13, l7);
                l7 -= l2;
                l16 = ThreefishEngine.xorRotr(l16, 34, l13);
                l13 -= l16;
                l14 = ThreefishEngine.xorRotr(l14, 41, l15);
                l15 -= l14;
                l12 = ThreefishEngine.xorRotr(l12, 59, l9);
                l9 -= l12;
                l10 = ThreefishEngine.xorRotr(l10, 17, l11);
                l11 -= l10;
                l10 = ThreefishEngine.xorRotr(l10, 38, l);
                l -= l10;
                l14 = ThreefishEngine.xorRotr(l14, 19, l3);
                l3 -= l14;
                l12 = ThreefishEngine.xorRotr(l12, 10, l7);
                l7 -= l12;
                l16 = ThreefishEngine.xorRotr(l16, 55, l5);
                l5 -= l16;
                l8 = ThreefishEngine.xorRotr(l8, 49, l11);
                l11 -= l8;
                l4 = ThreefishEngine.xorRotr(l4, 18, l13);
                l13 -= l4;
                l6 = ThreefishEngine.xorRotr(l6, 23, l15);
                l15 -= l6;
                l2 = ThreefishEngine.xorRotr(l2, 52, l9);
                l9 -= l2;
                l2 = ThreefishEngine.xorRotr(l2, 24, l);
                l -= l2;
                l4 = ThreefishEngine.xorRotr(l4, 13, l3);
                l3 -= l4;
                l6 = ThreefishEngine.xorRotr(l6, 8, l5);
                l5 -= l6;
                l8 = ThreefishEngine.xorRotr(l8, 47, l7);
                l7 -= l8;
                l10 = ThreefishEngine.xorRotr(l10, 8, l9);
                l9 -= l10;
                l12 = ThreefishEngine.xorRotr(l12, 17, l11);
                l11 -= l12;
                l14 = ThreefishEngine.xorRotr(l14, 22, l13);
                l13 -= l14;
                l16 = ThreefishEngine.xorRotr(l16, 37, l15);
                l15 -= l16;
            }
            l -= lArray3[0];
            l2 -= lArray3[1];
            l3 -= lArray3[2];
            l4 -= lArray3[3];
            l5 -= lArray3[4];
            l6 -= lArray3[5];
            l7 -= lArray3[6];
            l8 -= lArray3[7];
            l9 -= lArray3[8];
            l10 -= lArray3[9];
            l11 -= lArray3[10];
            l12 -= lArray3[11];
            l13 -= lArray3[12];
            l14 -= lArray3[13] + lArray4[0];
            l15 -= lArray3[14] + lArray4[1];
            l16 -= lArray3[15];
            lArray2[0] = l;
            lArray2[1] = l2;
            lArray2[2] = l3;
            lArray2[3] = l4;
            lArray2[4] = l5;
            lArray2[5] = l6;
            lArray2[6] = l7;
            lArray2[7] = l8;
            lArray2[8] = l9;
            lArray2[9] = l10;
            lArray2[10] = l11;
            lArray2[11] = l12;
            lArray2[12] = l13;
            lArray2[13] = l14;
            lArray2[14] = l15;
            lArray2[15] = l16;
        }
    }

    private static final class Threefish256Cipher
    extends ThreefishCipher {
        private static final int ROTATION_0_0 = 14;
        private static final int ROTATION_0_1 = 16;
        private static final int ROTATION_1_0 = 52;
        private static final int ROTATION_1_1 = 57;
        private static final int ROTATION_2_0 = 23;
        private static final int ROTATION_2_1 = 40;
        private static final int ROTATION_3_0 = 5;
        private static final int ROTATION_3_1 = 37;
        private static final int ROTATION_4_0 = 25;
        private static final int ROTATION_4_1 = 33;
        private static final int ROTATION_5_0 = 46;
        private static final int ROTATION_5_1 = 12;
        private static final int ROTATION_6_0 = 58;
        private static final int ROTATION_6_1 = 22;
        private static final int ROTATION_7_0 = 32;
        private static final int ROTATION_7_1 = 32;

        public Threefish256Cipher(long[] lArray, long[] lArray2) {
            super(lArray, lArray2);
        }

        void encryptBlock(long[] lArray, long[] lArray2) {
            long[] lArray3 = this.kw;
            long[] lArray4 = this.t;
            int[] nArray = MOD5;
            int[] nArray2 = MOD3;
            if (lArray3.length != 9) {
                throw new IllegalArgumentException();
            }
            if (lArray4.length != 5) {
                throw new IllegalArgumentException();
            }
            long l = lArray[0];
            long l2 = lArray[1];
            long l3 = lArray[2];
            long l4 = lArray[3];
            l += lArray3[0];
            l2 += lArray3[1] + lArray4[0];
            l3 += lArray3[2] + lArray4[1];
            l4 += lArray3[3];
            for (int i = 1; i < 18; i += 2) {
                int n = nArray[i];
                int n2 = nArray2[i];
                l += l2;
                l2 = ThreefishEngine.rotlXor(l2, 14, l);
                l3 += l4;
                l4 = ThreefishEngine.rotlXor(l4, 16, l3);
                l += l4;
                l4 = ThreefishEngine.rotlXor(l4, 52, l);
                l3 += l2;
                l2 = ThreefishEngine.rotlXor(l2, 57, l3);
                l += l2;
                l2 = ThreefishEngine.rotlXor(l2, 23, l);
                l3 += l4;
                l4 = ThreefishEngine.rotlXor(l4, 40, l3);
                l += l4;
                l4 = ThreefishEngine.rotlXor(l4, 5, l);
                l3 += l2;
                l2 = ThreefishEngine.rotlXor(l2, 37, l3);
                l += lArray3[n];
                l3 += lArray3[n + 2] + lArray4[n2 + 1];
                l2 = ThreefishEngine.rotlXor(l2, 25, l += (l2 += lArray3[n + 1] + lArray4[n2]));
                l4 = ThreefishEngine.rotlXor(l4, 33, l3 += (l4 += lArray3[n + 3] + (long)i));
                l += l4;
                l4 = ThreefishEngine.rotlXor(l4, 46, l);
                l3 += l2;
                l2 = ThreefishEngine.rotlXor(l2, 12, l3);
                l += l2;
                l2 = ThreefishEngine.rotlXor(l2, 58, l);
                l3 += l4;
                l4 = ThreefishEngine.rotlXor(l4, 22, l3);
                l += l4;
                l4 = ThreefishEngine.rotlXor(l4, 32, l);
                l3 += l2;
                l2 = ThreefishEngine.rotlXor(l2, 32, l3);
                l += lArray3[n + 1];
                l2 += lArray3[n + 2] + lArray4[n2 + 1];
                l3 += lArray3[n + 3] + lArray4[n2 + 2];
                l4 += lArray3[n + 4] + (long)i + 1L;
            }
            lArray2[0] = l;
            lArray2[1] = l2;
            lArray2[2] = l3;
            lArray2[3] = l4;
        }

        void decryptBlock(long[] lArray, long[] lArray2) {
            long[] lArray3 = this.kw;
            long[] lArray4 = this.t;
            int[] nArray = MOD5;
            int[] nArray2 = MOD3;
            if (lArray3.length != 9) {
                throw new IllegalArgumentException();
            }
            if (lArray4.length != 5) {
                throw new IllegalArgumentException();
            }
            long l = lArray[0];
            long l2 = lArray[1];
            long l3 = lArray[2];
            long l4 = lArray[3];
            for (int i = 17; i >= 1; i -= 2) {
                int n = nArray[i];
                int n2 = nArray2[i];
                l2 -= lArray3[n + 2] + lArray4[n2 + 1];
                l4 -= lArray3[n + 4] + (long)i + 1L;
                l4 = ThreefishEngine.xorRotr(l4, 32, l -= lArray3[n + 1]);
                l2 = ThreefishEngine.xorRotr(l2, 32, l3 -= lArray3[n + 3] + lArray4[n2 + 2]);
                l3 -= l2;
                l2 = ThreefishEngine.xorRotr(l2, 58, l -= l4);
                l4 = ThreefishEngine.xorRotr(l4, 22, l3);
                l3 -= l4;
                l4 = ThreefishEngine.xorRotr(l4, 46, l -= l2);
                l2 = ThreefishEngine.xorRotr(l2, 12, l3);
                l3 -= l2;
                l2 = ThreefishEngine.xorRotr(l2, 25, l -= l4);
                l -= l2;
                l4 = ThreefishEngine.xorRotr(l4, 33, l3);
                l3 -= l4;
                l2 -= lArray3[n + 1] + lArray4[n2];
                l4 -= lArray3[n + 3] + (long)i;
                l4 = ThreefishEngine.xorRotr(l4, 5, l -= lArray3[n]);
                l2 = ThreefishEngine.xorRotr(l2, 37, l3 -= lArray3[n + 2] + lArray4[n2 + 1]);
                l3 -= l2;
                l2 = ThreefishEngine.xorRotr(l2, 23, l -= l4);
                l4 = ThreefishEngine.xorRotr(l4, 40, l3);
                l3 -= l4;
                l4 = ThreefishEngine.xorRotr(l4, 52, l -= l2);
                l2 = ThreefishEngine.xorRotr(l2, 57, l3);
                l3 -= l2;
                l2 = ThreefishEngine.xorRotr(l2, 14, l -= l4);
                l -= l2;
                l4 = ThreefishEngine.xorRotr(l4, 16, l3);
                l3 -= l4;
            }
            lArray2[0] = l -= lArray3[0];
            lArray2[1] = l2 -= lArray3[1] + lArray4[0];
            lArray2[2] = l3 -= lArray3[2] + lArray4[1];
            lArray2[3] = l4 -= lArray3[3];
        }
    }

    private static final class Threefish512Cipher
    extends ThreefishCipher {
        private static final int ROTATION_0_0 = 46;
        private static final int ROTATION_0_1 = 36;
        private static final int ROTATION_0_2 = 19;
        private static final int ROTATION_0_3 = 37;
        private static final int ROTATION_1_0 = 33;
        private static final int ROTATION_1_1 = 27;
        private static final int ROTATION_1_2 = 14;
        private static final int ROTATION_1_3 = 42;
        private static final int ROTATION_2_0 = 17;
        private static final int ROTATION_2_1 = 49;
        private static final int ROTATION_2_2 = 36;
        private static final int ROTATION_2_3 = 39;
        private static final int ROTATION_3_0 = 44;
        private static final int ROTATION_3_1 = 9;
        private static final int ROTATION_3_2 = 54;
        private static final int ROTATION_3_3 = 56;
        private static final int ROTATION_4_0 = 39;
        private static final int ROTATION_4_1 = 30;
        private static final int ROTATION_4_2 = 34;
        private static final int ROTATION_4_3 = 24;
        private static final int ROTATION_5_0 = 13;
        private static final int ROTATION_5_1 = 50;
        private static final int ROTATION_5_2 = 10;
        private static final int ROTATION_5_3 = 17;
        private static final int ROTATION_6_0 = 25;
        private static final int ROTATION_6_1 = 29;
        private static final int ROTATION_6_2 = 39;
        private static final int ROTATION_6_3 = 43;
        private static final int ROTATION_7_0 = 8;
        private static final int ROTATION_7_1 = 35;
        private static final int ROTATION_7_2 = 56;
        private static final int ROTATION_7_3 = 22;

        protected Threefish512Cipher(long[] lArray, long[] lArray2) {
            super(lArray, lArray2);
        }

        public void encryptBlock(long[] lArray, long[] lArray2) {
            long[] lArray3 = this.kw;
            long[] lArray4 = this.t;
            int[] nArray = MOD9;
            int[] nArray2 = MOD3;
            if (lArray3.length != 17) {
                throw new IllegalArgumentException();
            }
            if (lArray4.length != 5) {
                throw new IllegalArgumentException();
            }
            long l = lArray[0];
            long l2 = lArray[1];
            long l3 = lArray[2];
            long l4 = lArray[3];
            long l5 = lArray[4];
            long l6 = lArray[5];
            long l7 = lArray[6];
            long l8 = lArray[7];
            l += lArray3[0];
            l2 += lArray3[1];
            l3 += lArray3[2];
            l4 += lArray3[3];
            l5 += lArray3[4];
            l6 += lArray3[5] + lArray4[0];
            l7 += lArray3[6] + lArray4[1];
            l8 += lArray3[7];
            for (int i = 1; i < 18; i += 2) {
                int n = nArray[i];
                int n2 = nArray2[i];
                l += l2;
                l2 = ThreefishEngine.rotlXor(l2, 46, l);
                l3 += l4;
                l4 = ThreefishEngine.rotlXor(l4, 36, l3);
                l5 += l6;
                l6 = ThreefishEngine.rotlXor(l6, 19, l5);
                l7 += l8;
                l8 = ThreefishEngine.rotlXor(l8, 37, l7);
                l3 += l2;
                l2 = ThreefishEngine.rotlXor(l2, 33, l3);
                l5 += l8;
                l8 = ThreefishEngine.rotlXor(l8, 27, l5);
                l7 += l6;
                l6 = ThreefishEngine.rotlXor(l6, 14, l7);
                l += l4;
                l4 = ThreefishEngine.rotlXor(l4, 42, l);
                l5 += l2;
                l2 = ThreefishEngine.rotlXor(l2, 17, l5);
                l7 += l4;
                l4 = ThreefishEngine.rotlXor(l4, 49, l7);
                l += l6;
                l6 = ThreefishEngine.rotlXor(l6, 36, l);
                l3 += l8;
                l8 = ThreefishEngine.rotlXor(l8, 39, l3);
                l7 += l2;
                l2 = ThreefishEngine.rotlXor(l2, 44, l7);
                l += l8;
                l8 = ThreefishEngine.rotlXor(l8, 9, l);
                l3 += l6;
                l6 = ThreefishEngine.rotlXor(l6, 54, l3);
                l5 += l4;
                l4 = ThreefishEngine.rotlXor(l4, 56, l5);
                l += lArray3[n];
                l2 += lArray3[n + 1];
                l3 += lArray3[n + 2];
                l4 += lArray3[n + 3];
                l5 += lArray3[n + 4];
                l6 += lArray3[n + 5] + lArray4[n2];
                l7 += lArray3[n + 6] + lArray4[n2 + 1];
                l8 += lArray3[n + 7] + (long)i;
                l += l2;
                l2 = ThreefishEngine.rotlXor(l2, 39, l);
                l3 += l4;
                l4 = ThreefishEngine.rotlXor(l4, 30, l3);
                l5 += l6;
                l6 = ThreefishEngine.rotlXor(l6, 34, l5);
                l7 += l8;
                l8 = ThreefishEngine.rotlXor(l8, 24, l7);
                l3 += l2;
                l2 = ThreefishEngine.rotlXor(l2, 13, l3);
                l5 += l8;
                l8 = ThreefishEngine.rotlXor(l8, 50, l5);
                l7 += l6;
                l6 = ThreefishEngine.rotlXor(l6, 10, l7);
                l += l4;
                l4 = ThreefishEngine.rotlXor(l4, 17, l);
                l5 += l2;
                l2 = ThreefishEngine.rotlXor(l2, 25, l5);
                l7 += l4;
                l4 = ThreefishEngine.rotlXor(l4, 29, l7);
                l += l6;
                l6 = ThreefishEngine.rotlXor(l6, 39, l);
                l3 += l8;
                l8 = ThreefishEngine.rotlXor(l8, 43, l3);
                l7 += l2;
                l2 = ThreefishEngine.rotlXor(l2, 8, l7);
                l += l8;
                l8 = ThreefishEngine.rotlXor(l8, 35, l);
                l3 += l6;
                l6 = ThreefishEngine.rotlXor(l6, 56, l3);
                l5 += l4;
                l4 = ThreefishEngine.rotlXor(l4, 22, l5);
                l += lArray3[n + 1];
                l2 += lArray3[n + 2];
                l3 += lArray3[n + 3];
                l4 += lArray3[n + 4];
                l5 += lArray3[n + 5];
                l6 += lArray3[n + 6] + lArray4[n2 + 1];
                l7 += lArray3[n + 7] + lArray4[n2 + 2];
                l8 += lArray3[n + 8] + (long)i + 1L;
            }
            lArray2[0] = l;
            lArray2[1] = l2;
            lArray2[2] = l3;
            lArray2[3] = l4;
            lArray2[4] = l5;
            lArray2[5] = l6;
            lArray2[6] = l7;
            lArray2[7] = l8;
        }

        public void decryptBlock(long[] lArray, long[] lArray2) {
            long[] lArray3 = this.kw;
            long[] lArray4 = this.t;
            int[] nArray = MOD9;
            int[] nArray2 = MOD3;
            if (lArray3.length != 17) {
                throw new IllegalArgumentException();
            }
            if (lArray4.length != 5) {
                throw new IllegalArgumentException();
            }
            long l = lArray[0];
            long l2 = lArray[1];
            long l3 = lArray[2];
            long l4 = lArray[3];
            long l5 = lArray[4];
            long l6 = lArray[5];
            long l7 = lArray[6];
            long l8 = lArray[7];
            for (int i = 17; i >= 1; i -= 2) {
                int n = nArray[i];
                int n2 = nArray2[i];
                l -= lArray3[n + 1];
                l2 -= lArray3[n + 2];
                l3 -= lArray3[n + 3];
                l4 -= lArray3[n + 4];
                l5 -= lArray3[n + 5];
                l6 -= lArray3[n + 6] + lArray4[n2 + 1];
                l8 -= lArray3[n + 8] + (long)i + 1L;
                l2 = ThreefishEngine.xorRotr(l2, 8, l7 -= lArray3[n + 7] + lArray4[n2 + 2]);
                l7 -= l2;
                l8 = ThreefishEngine.xorRotr(l8, 35, l);
                l -= l8;
                l6 = ThreefishEngine.xorRotr(l6, 56, l3);
                l3 -= l6;
                l4 = ThreefishEngine.xorRotr(l4, 22, l5);
                l2 = ThreefishEngine.xorRotr(l2, 25, l5 -= l4);
                l5 -= l2;
                l4 = ThreefishEngine.xorRotr(l4, 29, l7);
                l7 -= l4;
                l6 = ThreefishEngine.xorRotr(l6, 39, l);
                l -= l6;
                l8 = ThreefishEngine.xorRotr(l8, 43, l3);
                l2 = ThreefishEngine.xorRotr(l2, 13, l3 -= l8);
                l3 -= l2;
                l8 = ThreefishEngine.xorRotr(l8, 50, l5);
                l5 -= l8;
                l6 = ThreefishEngine.xorRotr(l6, 10, l7);
                l7 -= l6;
                l4 = ThreefishEngine.xorRotr(l4, 17, l);
                l2 = ThreefishEngine.xorRotr(l2, 39, l -= l4);
                l -= l2;
                l4 = ThreefishEngine.xorRotr(l4, 30, l3);
                l3 -= l4;
                l6 = ThreefishEngine.xorRotr(l6, 34, l5);
                l5 -= l6;
                l8 = ThreefishEngine.xorRotr(l8, 24, l7);
                l7 -= l8;
                l -= lArray3[n];
                l2 -= lArray3[n + 1];
                l3 -= lArray3[n + 2];
                l4 -= lArray3[n + 3];
                l5 -= lArray3[n + 4];
                l6 -= lArray3[n + 5] + lArray4[n2];
                l8 -= lArray3[n + 7] + (long)i;
                l2 = ThreefishEngine.xorRotr(l2, 44, l7 -= lArray3[n + 6] + lArray4[n2 + 1]);
                l7 -= l2;
                l8 = ThreefishEngine.xorRotr(l8, 9, l);
                l -= l8;
                l6 = ThreefishEngine.xorRotr(l6, 54, l3);
                l3 -= l6;
                l4 = ThreefishEngine.xorRotr(l4, 56, l5);
                l2 = ThreefishEngine.xorRotr(l2, 17, l5 -= l4);
                l5 -= l2;
                l4 = ThreefishEngine.xorRotr(l4, 49, l7);
                l7 -= l4;
                l6 = ThreefishEngine.xorRotr(l6, 36, l);
                l -= l6;
                l8 = ThreefishEngine.xorRotr(l8, 39, l3);
                l2 = ThreefishEngine.xorRotr(l2, 33, l3 -= l8);
                l3 -= l2;
                l8 = ThreefishEngine.xorRotr(l8, 27, l5);
                l5 -= l8;
                l6 = ThreefishEngine.xorRotr(l6, 14, l7);
                l7 -= l6;
                l4 = ThreefishEngine.xorRotr(l4, 42, l);
                l2 = ThreefishEngine.xorRotr(l2, 46, l -= l4);
                l -= l2;
                l4 = ThreefishEngine.xorRotr(l4, 36, l3);
                l3 -= l4;
                l6 = ThreefishEngine.xorRotr(l6, 19, l5);
                l5 -= l6;
                l8 = ThreefishEngine.xorRotr(l8, 37, l7);
                l7 -= l8;
            }
            l -= lArray3[0];
            l2 -= lArray3[1];
            l3 -= lArray3[2];
            l4 -= lArray3[3];
            l5 -= lArray3[4];
            l6 -= lArray3[5] + lArray4[0];
            l7 -= lArray3[6] + lArray4[1];
            l8 -= lArray3[7];
            lArray2[0] = l;
            lArray2[1] = l2;
            lArray2[2] = l3;
            lArray2[3] = l4;
            lArray2[4] = l5;
            lArray2[5] = l6;
            lArray2[6] = l7;
            lArray2[7] = l8;
        }
    }

    private static abstract class ThreefishCipher {
        protected final long[] t;
        protected final long[] kw;

        protected ThreefishCipher(long[] lArray, long[] lArray2) {
            this.kw = lArray;
            this.t = lArray2;
        }

        abstract void encryptBlock(long[] var1, long[] var2);

        abstract void decryptBlock(long[] var1, long[] var2);
    }
}

