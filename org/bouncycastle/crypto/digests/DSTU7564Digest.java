/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;

public class DSTU7564Digest
implements ExtendedDigest,
Memoable {
    private static final int ROWS = 8;
    private static final int REDUCTIONAL_POLYNOMIAL = 285;
    private static final int BITS_IN_BYTE = 8;
    private static final int NB_512 = 8;
    private static final int NB_1024 = 16;
    private static final int NR_512 = 10;
    private static final int NR_1024 = 14;
    private static final int STATE_BYTES_SIZE_512 = 64;
    private static final int STATE_BYTES_SIZE_1024 = 128;
    private int hashSize;
    private int blockSize;
    private int columns;
    private int rounds;
    private byte[] padded;
    private byte[][] state;
    private byte[][] tempState1;
    private byte[][] tempState2;
    private byte[] tempBuffer;
    private byte[] mixColumnsResult;
    private long[] tempLongBuffer;
    private long inputLength;
    private int bufOff;
    private byte[] buf;
    private static final byte[][] mds_matrix = new byte[][]{{1, 1, 5, 1, 8, 6, 7, 4}, {4, 1, 1, 5, 1, 8, 6, 7}, {7, 4, 1, 1, 5, 1, 8, 6}, {6, 7, 4, 1, 1, 5, 1, 8}, {8, 6, 7, 4, 1, 1, 5, 1}, {1, 8, 6, 7, 4, 1, 1, 5}, {5, 1, 8, 6, 7, 4, 1, 1}, {1, 5, 1, 8, 6, 7, 4, 1}};
    private static final byte[][] sBoxes = new byte[][]{{-88, 67, 95, 6, 107, 117, 108, 89, 113, -33, -121, -107, 23, -16, -40, 9, 109, -13, 29, -53, -55, 77, 44, -81, 121, -32, -105, -3, 111, 75, 69, 57, 62, -35, -93, 79, -76, -74, -102, 14, 31, -65, 21, -31, 73, -46, -109, -58, -110, 114, -98, 97, -47, 99, -6, -18, -12, 25, -43, -83, 88, -92, -69, -95, -36, -14, -125, 55, 66, -28, 122, 50, -100, -52, -85, 74, -113, 110, 4, 39, 46, -25, -30, 90, -106, 22, 35, 43, -62, 101, 102, 15, -68, -87, 71, 65, 52, 72, -4, -73, 106, -120, -91, 83, -122, -7, 91, -37, 56, 123, -61, 30, 34, 51, 36, 40, 54, -57, -78, 59, -114, 119, -70, -11, 20, -97, 8, 85, -101, 76, -2, 96, 92, -38, 24, 70, -51, 125, 33, -80, 63, 27, -119, -1, -21, -124, 105, 58, -99, -41, -45, 112, 103, 64, -75, -34, 93, 48, -111, -79, 120, 17, 1, -27, 0, 104, -104, -96, -59, 2, -90, 116, 45, 11, -94, 118, -77, -66, -50, -67, -82, -23, -118, 49, 28, -20, -15, -103, -108, -86, -10, 38, 47, -17, -24, -116, 53, 3, -44, 127, -5, 5, -63, 94, -112, 32, 61, -126, -9, -22, 10, 13, 126, -8, 80, 26, -60, 7, 87, -72, 60, 98, -29, -56, -84, 82, 100, 16, -48, -39, 19, 12, 18, 41, 81, -71, -49, -42, 115, -115, -127, 84, -64, -19, 78, 68, -89, 42, -123, 37, -26, -54, 124, -117, 86, -128}, {-50, -69, -21, -110, -22, -53, 19, -63, -23, 58, -42, -78, -46, -112, 23, -8, 66, 21, 86, -76, 101, 28, -120, 67, -59, 92, 54, -70, -11, 87, 103, -115, 49, -10, 100, 88, -98, -12, 34, -86, 117, 15, 2, -79, -33, 109, 115, 77, 124, 38, 46, -9, 8, 93, 68, 62, -97, 20, -56, -82, 84, 16, -40, -68, 26, 107, 105, -13, -67, 51, -85, -6, -47, -101, 104, 78, 22, -107, -111, -18, 76, 99, -114, 91, -52, 60, 25, -95, -127, 73, 123, -39, 111, 55, 96, -54, -25, 43, 72, -3, -106, 69, -4, 65, 18, 13, 121, -27, -119, -116, -29, 32, 48, -36, -73, 108, 74, -75, 63, -105, -44, 98, 45, 6, -92, -91, -125, 95, 42, -38, -55, 0, 126, -94, 85, -65, 17, -43, -100, -49, 14, 10, 61, 81, 125, -109, 27, -2, -60, 71, 9, -122, 11, -113, -99, 106, 7, -71, -80, -104, 24, 50, 113, 75, -17, 59, 112, -96, -28, 64, -1, -61, -87, -26, 120, -7, -117, 70, -128, 30, 56, -31, -72, -88, -32, 12, 35, 118, 29, 37, 36, 5, -15, 110, -108, 40, -102, -124, -24, -93, 79, 119, -45, -123, -30, 82, -14, -126, 80, 122, 47, 116, 83, -77, 97, -81, 57, 53, -34, -51, 31, -103, -84, -83, 114, 44, -35, -48, -121, -66, 94, -90, -20, 4, -58, 3, 52, -5, -37, 89, -74, -62, 1, -16, 90, -19, -89, 102, 33, 127, -118, 39, -57, -64, 41, -41}, {-109, -39, -102, -75, -104, 34, 69, -4, -70, 106, -33, 2, -97, -36, 81, 89, 74, 23, 43, -62, -108, -12, -69, -93, 98, -28, 113, -44, -51, 112, 22, -31, 73, 60, -64, -40, 92, -101, -83, -123, 83, -95, 122, -56, 45, -32, -47, 114, -90, 44, -60, -29, 118, 120, -73, -76, 9, 59, 14, 65, 76, -34, -78, -112, 37, -91, -41, 3, 17, 0, -61, 46, -110, -17, 78, 18, -99, 125, -53, 53, 16, -43, 79, -98, 77, -87, 85, -58, -48, 123, 24, -105, -45, 54, -26, 72, 86, -127, -113, 119, -52, -100, -71, -30, -84, -72, 47, 21, -92, 124, -38, 56, 30, 11, 5, -42, 20, 110, 108, 126, 102, -3, -79, -27, 96, -81, 94, 51, -121, -55, -16, 93, 109, 63, -120, -115, -57, -9, 29, -23, -20, -19, -128, 41, 39, -49, -103, -88, 80, 15, 55, 36, 40, 48, -107, -46, 62, 91, 64, -125, -77, 105, 87, 31, 7, 28, -118, -68, 32, -21, -50, -114, -85, -18, 49, -94, 115, -7, -54, 58, 26, -5, 13, -63, -2, -6, -14, 111, -67, -106, -35, 67, 82, -74, 8, -13, -82, -66, 25, -119, 50, 38, -80, -22, 75, 100, -124, -126, 107, -11, 121, -65, 1, 95, 117, 99, 27, 35, 61, 104, 42, 101, -24, -111, -10, -1, 19, 88, -15, 71, 10, 127, -59, -89, -25, 97, 90, 6, 70, 68, 66, 4, -96, -37, 57, -122, 84, -86, -116, 52, 33, -117, -8, 12, 116, 103}, {104, -115, -54, 77, 115, 75, 78, 42, -44, 82, 38, -77, 84, 30, 25, 31, 34, 3, 70, 61, 45, 74, 83, -125, 19, -118, -73, -43, 37, 121, -11, -67, 88, 47, 13, 2, -19, 81, -98, 17, -14, 62, 85, 94, -47, 22, 60, 102, 112, 93, -13, 69, 64, -52, -24, -108, 86, 8, -50, 26, 58, -46, -31, -33, -75, 56, 110, 14, -27, -12, -7, -122, -23, 79, -42, -123, 35, -49, 50, -103, 49, 20, -82, -18, -56, 72, -45, 48, -95, -110, 65, -79, 24, -60, 44, 113, 114, 68, 21, -3, 55, -66, 95, -86, -101, -120, -40, -85, -119, -100, -6, 96, -22, -68, 98, 12, 36, -90, -88, -20, 103, 32, -37, 124, 40, -35, -84, 91, 52, 126, 16, -15, 123, -113, 99, -96, 5, -102, 67, 119, 33, -65, 39, 9, -61, -97, -74, -41, 41, -62, -21, -64, -92, -117, -116, 29, -5, -1, -63, -78, -105, 46, -8, 101, -10, 117, 7, 4, 73, 51, -28, -39, -71, -48, 66, -57, 108, -112, 0, -114, 111, 80, 1, -59, -38, 71, 63, -51, 105, -94, -30, 122, -89, -58, -109, 15, 10, 6, -26, 43, -106, -93, 28, -81, 106, 18, -124, 57, -25, -80, -126, -9, -2, -99, -121, 92, -127, 53, -34, -76, -91, -4, -128, -17, -53, -69, 107, 118, -70, 90, 125, 120, 11, -107, -29, -83, 116, -104, 59, 54, 100, 109, -36, -16, 89, -87, 76, 23, 127, -111, -72, -55, 87, 27, -32, 97}};

    public DSTU7564Digest(DSTU7564Digest dSTU7564Digest) {
        this.copyIn(dSTU7564Digest);
    }

    private void copyIn(DSTU7564Digest dSTU7564Digest) {
        this.hashSize = dSTU7564Digest.hashSize;
        this.blockSize = dSTU7564Digest.blockSize;
        this.columns = dSTU7564Digest.columns;
        this.rounds = dSTU7564Digest.rounds;
        this.padded = Arrays.clone(dSTU7564Digest.padded);
        this.state = Arrays.clone(dSTU7564Digest.state);
        this.tempState1 = Arrays.clone(dSTU7564Digest.tempState1);
        this.tempState2 = Arrays.clone(dSTU7564Digest.tempState2);
        this.tempBuffer = Arrays.clone(dSTU7564Digest.tempBuffer);
        this.mixColumnsResult = Arrays.clone(dSTU7564Digest.mixColumnsResult);
        this.tempLongBuffer = Arrays.clone(dSTU7564Digest.tempLongBuffer);
        this.inputLength = dSTU7564Digest.inputLength;
        this.bufOff = dSTU7564Digest.bufOff;
        this.buf = Arrays.clone(dSTU7564Digest.buf);
    }

    public DSTU7564Digest(int n) {
        int n2;
        if (n != 256 && n != 384 && n != 512) {
            throw new IllegalArgumentException("Hash size is not recommended. Use 256/384/512 instead");
        }
        this.hashSize = n / 8;
        if (n > 256) {
            this.blockSize = 128;
            this.columns = 16;
            this.rounds = 14;
            this.state = new byte[128][];
        } else {
            this.blockSize = 64;
            this.columns = 8;
            this.rounds = 10;
            this.state = new byte[64][];
        }
        for (n2 = 0; n2 < this.state.length; ++n2) {
            this.state[n2] = new byte[this.columns];
        }
        this.state[0][0] = (byte)this.state.length;
        this.padded = null;
        this.tempState1 = new byte[128][];
        this.tempState2 = new byte[128][];
        for (n2 = 0; n2 < this.state.length; ++n2) {
            this.tempState1[n2] = new byte[8];
            this.tempState2[n2] = new byte[8];
        }
        this.tempBuffer = new byte[16];
        this.mixColumnsResult = new byte[8];
        this.tempLongBuffer = new long[this.columns];
        this.buf = new byte[this.blockSize];
    }

    public String getAlgorithmName() {
        return "DSTU7564";
    }

    public int getDigestSize() {
        return this.hashSize;
    }

    public int getByteLength() {
        return this.blockSize;
    }

    public void update(byte by) {
        this.buf[this.bufOff++] = by;
        if (this.bufOff == this.blockSize) {
            this.processBlock(this.buf, 0);
            this.bufOff = 0;
        }
        ++this.inputLength;
    }

    public void update(byte[] byArray, int n, int n2) {
        while (this.bufOff != 0 && n2 > 0) {
            this.update(byArray[n++]);
            --n2;
        }
        if (n2 > 0) {
            while (n2 > this.blockSize) {
                this.processBlock(byArray, n);
                n += this.blockSize;
                this.inputLength += (long)this.blockSize;
                n2 -= this.blockSize;
            }
            while (n2 > 0) {
                this.update(byArray[n++]);
                --n2;
            }
        }
    }

    public int doFinal(byte[] byArray, int n) {
        int n2;
        int n3;
        int n4;
        int n5;
        this.padded = this.pad(this.buf, 0, this.bufOff);
        int n6 = 0;
        for (int i = this.padded.length; i != 0; i -= this.blockSize) {
            this.processBlock(this.padded, n6);
            n6 += this.blockSize;
        }
        byte[][] byArrayArray = new byte[128][];
        for (n5 = 0; n5 < this.state.length; ++n5) {
            byArrayArray[n5] = new byte[8];
            System.arraycopy(this.state[n5], 0, byArrayArray[n5], 0, 8);
        }
        for (n5 = 0; n5 < this.rounds; ++n5) {
            for (n4 = 0; n4 < this.columns; ++n4) {
                byte[] byArray2 = byArrayArray[n4];
                byArray2[0] = (byte)(byArray2[0] ^ (byte)(n4 * 16 ^ n5));
            }
            for (n4 = 0; n4 < 8; ++n4) {
                for (n3 = 0; n3 < this.columns; ++n3) {
                    byArrayArray[n3][n4] = sBoxes[n4 % 4][byArrayArray[n3][n4] & 0xFF];
                }
            }
            n4 = -1;
            for (n3 = 0; n3 < 8; ++n3) {
                n4 = n3 == 7 && this.columns == 16 ? 11 : ++n4;
                for (n2 = 0; n2 < this.columns; ++n2) {
                    this.tempBuffer[(n2 + n4) % this.columns] = byArrayArray[n2][n3];
                }
                for (n2 = 0; n2 < this.columns; ++n2) {
                    byArrayArray[n2][n3] = this.tempBuffer[n2];
                }
            }
            for (n2 = 0; n2 < this.columns; ++n2) {
                int n7;
                Arrays.fill(this.mixColumnsResult, (byte)0);
                for (n7 = 7; n7 >= 0; --n7) {
                    n3 = 0;
                    for (int i = 7; i >= 0; --i) {
                        n3 = (byte)(n3 ^ this.multiplyGF(byArrayArray[n2][i], mds_matrix[n7][i]));
                    }
                    this.mixColumnsResult[n7] = n3;
                }
                for (n7 = 0; n7 < 8; ++n7) {
                    byArrayArray[n2][n7] = this.mixColumnsResult[n7];
                }
            }
        }
        for (n5 = 0; n5 < 8; ++n5) {
            for (n4 = 0; n4 < this.columns; ++n4) {
                byte[] byArray3 = this.state[n4];
                int n8 = n5;
                byArray3[n8] = (byte)(byArray3[n8] ^ byArrayArray[n4][n5]);
            }
        }
        byte[] byArray4 = new byte[8 * this.columns];
        n4 = 0;
        for (n3 = 0; n3 < this.columns; ++n3) {
            for (n2 = 0; n2 < 8; ++n2) {
                byArray4[n4] = this.state[n3][n2];
                ++n4;
            }
        }
        System.arraycopy(byArray4, byArray4.length - this.hashSize, byArray, n, this.hashSize);
        this.reset();
        return this.hashSize;
    }

    public void reset() {
        for (int i = 0; i < this.state.length; ++i) {
            this.state[i] = new byte[this.columns];
        }
        this.state[0][0] = (byte)this.state.length;
        this.inputLength = 0L;
        this.bufOff = 0;
        Arrays.fill(this.buf, (byte)0);
        if (this.padded != null) {
            Arrays.fill(this.padded, (byte)0);
        }
    }

    private void processBlock(byte[] byArray, int n) {
        int n2;
        int n3;
        for (n3 = 0; n3 < this.state.length; ++n3) {
            Arrays.fill(this.tempState1[n3], (byte)0);
            Arrays.fill(this.tempState2[n3], (byte)0);
        }
        for (n3 = 0; n3 < 8; ++n3) {
            for (n2 = 0; n2 < this.columns; ++n2) {
                this.tempState1[n2][n3] = (byte)(this.state[n2][n3] ^ byArray[n2 * 8 + n3 + n]);
                this.tempState2[n2][n3] = byArray[n2 * 8 + n3 + n];
            }
        }
        this.P();
        this.Q();
        for (n3 = 0; n3 < 8; ++n3) {
            for (n2 = 0; n2 < this.columns; ++n2) {
                byte[] byArray2 = this.state[n2];
                int n4 = n3;
                byArray2[n4] = (byte)(byArray2[n4] ^ (byte)(this.tempState1[n2][n3] ^ this.tempState2[n2][n3]));
            }
        }
    }

    private void Q() {
        for (int i = 0; i < this.rounds; ++i) {
            int n;
            int n2;
            int n3;
            for (n3 = 0; n3 < this.columns; ++n3) {
                this.tempLongBuffer[n3] = Pack.littleEndianToLong(this.tempState2[n3], 0);
                int n4 = n3;
                this.tempLongBuffer[n4] = this.tempLongBuffer[n4] + (0xF0F0F0F0F0F0F3L ^ ((long)(this.columns - n3 - 1) * 16L ^ (long)i) << 56);
                Pack.longToLittleEndian(this.tempLongBuffer[n3], this.tempState2[n3], 0);
            }
            for (n3 = 0; n3 < 8; ++n3) {
                for (n2 = 0; n2 < this.columns; ++n2) {
                    this.tempState2[n2][n3] = sBoxes[n3 % 4][this.tempState2[n2][n3] & 0xFF];
                }
            }
            n3 = -1;
            for (n2 = 0; n2 < 8; ++n2) {
                n3 = n2 == 7 && this.columns == 16 ? 11 : ++n3;
                for (n = 0; n < this.columns; ++n) {
                    this.tempBuffer[(n + n3) % this.columns] = this.tempState2[n][n2];
                }
                for (n = 0; n < this.columns; ++n) {
                    this.tempState2[n][n2] = this.tempBuffer[n];
                }
            }
            for (n = 0; n < this.columns; ++n) {
                int n5;
                Arrays.fill(this.mixColumnsResult, (byte)0);
                for (n5 = 7; n5 >= 0; --n5) {
                    n2 = 0;
                    for (int j = 7; j >= 0; --j) {
                        n2 = (byte)(n2 ^ this.multiplyGF(this.tempState2[n][j], mds_matrix[n5][j]));
                    }
                    this.mixColumnsResult[n5] = n2;
                }
                for (n5 = 0; n5 < 8; ++n5) {
                    this.tempState2[n][n5] = this.mixColumnsResult[n5];
                }
            }
        }
    }

    private void P() {
        for (int i = 0; i < this.rounds; ++i) {
            int n;
            int n2;
            int n3;
            for (n3 = 0; n3 < this.columns; ++n3) {
                byte[] byArray = this.tempState1[n3];
                byArray[0] = (byte)(byArray[0] ^ (byte)(n3 * 16 ^ i));
            }
            for (n3 = 0; n3 < 8; ++n3) {
                for (n2 = 0; n2 < this.columns; ++n2) {
                    this.tempState1[n2][n3] = sBoxes[n3 % 4][this.tempState1[n2][n3] & 0xFF];
                }
            }
            n3 = -1;
            for (n2 = 0; n2 < 8; ++n2) {
                n3 = n2 == 7 && this.columns == 16 ? 11 : ++n3;
                for (n = 0; n < this.columns; ++n) {
                    this.tempBuffer[(n + n3) % this.columns] = this.tempState1[n][n2];
                }
                for (n = 0; n < this.columns; ++n) {
                    this.tempState1[n][n2] = this.tempBuffer[n];
                }
            }
            for (n = 0; n < this.columns; ++n) {
                int n4;
                Arrays.fill(this.mixColumnsResult, (byte)0);
                for (n4 = 7; n4 >= 0; --n4) {
                    n2 = 0;
                    for (int j = 7; j >= 0; --j) {
                        n2 = (byte)(n2 ^ this.multiplyGF(this.tempState1[n][j], mds_matrix[n4][j]));
                    }
                    this.mixColumnsResult[n4] = n2;
                }
                for (n4 = 0; n4 < 8; ++n4) {
                    this.tempState1[n][n4] = this.mixColumnsResult[n4];
                }
            }
        }
    }

    private byte multiplyGF(byte by, byte by2) {
        byte by3 = 0;
        for (int i = 0; i < 8; ++i) {
            if ((by2 & 1) == 1) {
                by3 = (byte)(by3 ^ by);
            }
            byte by4 = (byte)(by & 0xFFFFFF80);
            by = (byte)(by << 1);
            if (by4 == -128) {
                by = (byte)(by ^ 0x11D);
            }
            by2 = (byte)(by2 >> 1);
        }
        return by3;
    }

    private byte[] pad(byte[] byArray, int n, int n2) {
        byte[] byArray2 = this.blockSize - n2 < 13 ? new byte[2 * this.blockSize] : new byte[this.blockSize];
        System.arraycopy(byArray, n, byArray2, 0, n2);
        byArray2[n2] = -128;
        Pack.longToLittleEndian(this.inputLength * 8L, byArray2, byArray2.length - 12);
        return byArray2;
    }

    public Memoable copy() {
        return new DSTU7564Digest(this);
    }

    public void reset(Memoable memoable) {
        DSTU7564Digest dSTU7564Digest = (DSTU7564Digest)memoable;
        this.copyIn(dSTU7564Digest);
    }
}

