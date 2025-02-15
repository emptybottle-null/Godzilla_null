/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

public class CamelliaLightEngine
implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int MASK8 = 255;
    private boolean initialized;
    private boolean _keyis128;
    private int[] subkey = new int[96];
    private int[] kw = new int[8];
    private int[] ke = new int[12];
    private int[] state = new int[4];
    private static final int[] SIGMA = new int[]{-1600231809, 1003262091, -1233459112, 1286239154, -957401297, -380665154, 1426019237, -237801700, 283453434, -563598051, -1336506174, -1276722691};
    private static final byte[] SBOX1 = new byte[]{112, -126, 44, -20, -77, 39, -64, -27, -28, -123, 87, 53, -22, 12, -82, 65, 35, -17, 107, -109, 69, 25, -91, 33, -19, 14, 79, 78, 29, 101, -110, -67, -122, -72, -81, -113, 124, -21, 31, -50, 62, 48, -36, 95, 94, -59, 11, 26, -90, -31, 57, -54, -43, 71, 93, 61, -39, 1, 90, -42, 81, 86, 108, 77, -117, 13, -102, 102, -5, -52, -80, 45, 116, 18, 43, 32, -16, -79, -124, -103, -33, 76, -53, -62, 52, 126, 118, 5, 109, -73, -87, 49, -47, 23, 4, -41, 20, 88, 58, 97, -34, 27, 17, 28, 50, 15, -100, 22, 83, 24, -14, 34, -2, 68, -49, -78, -61, -75, 122, -111, 36, 8, -24, -88, 96, -4, 105, 80, -86, -48, -96, 125, -95, -119, 98, -105, 84, 91, 30, -107, -32, -1, 100, -46, 16, -60, 0, 72, -93, -9, 117, -37, -118, 3, -26, -38, 9, 63, -35, -108, -121, 92, -125, 2, -51, 74, -112, 51, 115, 103, -10, -13, -99, 127, -65, -30, 82, -101, -40, 38, -56, 55, -58, 59, -127, -106, 111, 75, 19, -66, 99, 46, -23, 121, -89, -116, -97, 110, -68, -114, 41, -11, -7, -74, 47, -3, -76, 89, 120, -104, 6, 106, -25, 70, 113, -70, -44, 37, -85, 66, -120, -94, -115, -6, 114, 7, -71, 85, -8, -18, -84, 10, 54, 73, 42, 104, 60, 56, -15, -92, 64, 40, -45, 123, -69, -55, 67, -63, 21, -29, -83, -12, 119, -57, -128, -98};

    private static int rightRotate(int n, int n2) {
        return (n >>> n2) + (n << 32 - n2);
    }

    private static int leftRotate(int n, int n2) {
        return (n << n2) + (n >>> 32 - n2);
    }

    private static void roldq(int n, int[] nArray, int n2, int[] nArray2, int n3) {
        nArray2[0 + n3] = nArray[0 + n2] << n | nArray[1 + n2] >>> 32 - n;
        nArray2[1 + n3] = nArray[1 + n2] << n | nArray[2 + n2] >>> 32 - n;
        nArray2[2 + n3] = nArray[2 + n2] << n | nArray[3 + n2] >>> 32 - n;
        nArray2[3 + n3] = nArray[3 + n2] << n | nArray[0 + n2] >>> 32 - n;
        nArray[0 + n2] = nArray2[0 + n3];
        nArray[1 + n2] = nArray2[1 + n3];
        nArray[2 + n2] = nArray2[2 + n3];
        nArray[3 + n2] = nArray2[3 + n3];
    }

    private static void decroldq(int n, int[] nArray, int n2, int[] nArray2, int n3) {
        nArray2[2 + n3] = nArray[0 + n2] << n | nArray[1 + n2] >>> 32 - n;
        nArray2[3 + n3] = nArray[1 + n2] << n | nArray[2 + n2] >>> 32 - n;
        nArray2[0 + n3] = nArray[2 + n2] << n | nArray[3 + n2] >>> 32 - n;
        nArray2[1 + n3] = nArray[3 + n2] << n | nArray[0 + n2] >>> 32 - n;
        nArray[0 + n2] = nArray2[2 + n3];
        nArray[1 + n2] = nArray2[3 + n3];
        nArray[2 + n2] = nArray2[0 + n3];
        nArray[3 + n2] = nArray2[1 + n3];
    }

    private static void roldqo32(int n, int[] nArray, int n2, int[] nArray2, int n3) {
        nArray2[0 + n3] = nArray[1 + n2] << n - 32 | nArray[2 + n2] >>> 64 - n;
        nArray2[1 + n3] = nArray[2 + n2] << n - 32 | nArray[3 + n2] >>> 64 - n;
        nArray2[2 + n3] = nArray[3 + n2] << n - 32 | nArray[0 + n2] >>> 64 - n;
        nArray2[3 + n3] = nArray[0 + n2] << n - 32 | nArray[1 + n2] >>> 64 - n;
        nArray[0 + n2] = nArray2[0 + n3];
        nArray[1 + n2] = nArray2[1 + n3];
        nArray[2 + n2] = nArray2[2 + n3];
        nArray[3 + n2] = nArray2[3 + n3];
    }

    private static void decroldqo32(int n, int[] nArray, int n2, int[] nArray2, int n3) {
        nArray2[2 + n3] = nArray[1 + n2] << n - 32 | nArray[2 + n2] >>> 64 - n;
        nArray2[3 + n3] = nArray[2 + n2] << n - 32 | nArray[3 + n2] >>> 64 - n;
        nArray2[0 + n3] = nArray[3 + n2] << n - 32 | nArray[0 + n2] >>> 64 - n;
        nArray2[1 + n3] = nArray[0 + n2] << n - 32 | nArray[1 + n2] >>> 64 - n;
        nArray[0 + n2] = nArray2[2 + n3];
        nArray[1 + n2] = nArray2[3 + n3];
        nArray[2 + n2] = nArray2[0 + n3];
        nArray[3 + n2] = nArray2[1 + n3];
    }

    private int bytes2int(byte[] byArray, int n) {
        int n2 = 0;
        for (int i = 0; i < 4; ++i) {
            n2 = (n2 << 8) + (byArray[i + n] & 0xFF);
        }
        return n2;
    }

    private void int2bytes(int n, byte[] byArray, int n2) {
        for (int i = 0; i < 4; ++i) {
            byArray[3 - i + n2] = (byte)n;
            n >>>= 8;
        }
    }

    private byte lRot8(byte by, int n) {
        return (byte)(by << n | (by & 0xFF) >>> 8 - n);
    }

    private int sbox2(int n) {
        return this.lRot8(SBOX1[n], 1) & 0xFF;
    }

    private int sbox3(int n) {
        return this.lRot8(SBOX1[n], 7) & 0xFF;
    }

    private int sbox4(int n) {
        return SBOX1[this.lRot8((byte)n, 1) & 0xFF] & 0xFF;
    }

    private void camelliaF2(int[] nArray, int[] nArray2, int n) {
        int n2 = nArray[0] ^ nArray2[0 + n];
        int n3 = this.sbox4(n2 & 0xFF);
        n3 |= this.sbox3(n2 >>> 8 & 0xFF) << 8;
        n3 |= this.sbox2(n2 >>> 16 & 0xFF) << 16;
        n3 |= (SBOX1[n2 >>> 24 & 0xFF] & 0xFF) << 24;
        int n4 = nArray[1] ^ nArray2[1 + n];
        int n5 = SBOX1[n4 & 0xFF] & 0xFF;
        n5 |= this.sbox4(n4 >>> 8 & 0xFF) << 8;
        n5 |= this.sbox3(n4 >>> 16 & 0xFF) << 16;
        n5 |= this.sbox2(n4 >>> 24 & 0xFF) << 24;
        n5 = CamelliaLightEngine.leftRotate(n5, 8);
        n3 ^= n5;
        n5 = CamelliaLightEngine.leftRotate(n5, 8) ^ n3;
        n3 = CamelliaLightEngine.rightRotate(n3, 8) ^ n5;
        nArray[2] = nArray[2] ^ (CamelliaLightEngine.leftRotate(n5, 16) ^ n3);
        nArray[3] = nArray[3] ^ CamelliaLightEngine.leftRotate(n3, 8);
        n2 = nArray[2] ^ nArray2[2 + n];
        n3 = this.sbox4(n2 & 0xFF);
        n3 |= this.sbox3(n2 >>> 8 & 0xFF) << 8;
        n3 |= this.sbox2(n2 >>> 16 & 0xFF) << 16;
        n3 |= (SBOX1[n2 >>> 24 & 0xFF] & 0xFF) << 24;
        n4 = nArray[3] ^ nArray2[3 + n];
        n5 = SBOX1[n4 & 0xFF] & 0xFF;
        n5 |= this.sbox4(n4 >>> 8 & 0xFF) << 8;
        n5 |= this.sbox3(n4 >>> 16 & 0xFF) << 16;
        n5 |= this.sbox2(n4 >>> 24 & 0xFF) << 24;
        n5 = CamelliaLightEngine.leftRotate(n5, 8);
        n3 ^= n5;
        n5 = CamelliaLightEngine.leftRotate(n5, 8) ^ n3;
        n3 = CamelliaLightEngine.rightRotate(n3, 8) ^ n5;
        nArray[0] = nArray[0] ^ (CamelliaLightEngine.leftRotate(n5, 16) ^ n3);
        nArray[1] = nArray[1] ^ CamelliaLightEngine.leftRotate(n3, 8);
    }

    private void camelliaFLs(int[] nArray, int[] nArray2, int n) {
        nArray[1] = nArray[1] ^ CamelliaLightEngine.leftRotate(nArray[0] & nArray2[0 + n], 1);
        nArray[0] = nArray[0] ^ (nArray2[1 + n] | nArray[1]);
        nArray[2] = nArray[2] ^ (nArray2[3 + n] | nArray[3]);
        nArray[3] = nArray[3] ^ CamelliaLightEngine.leftRotate(nArray2[2 + n] & nArray[2], 1);
    }

    private void setKey(boolean bl, byte[] byArray) {
        int n;
        int[] nArray = new int[8];
        int[] nArray2 = new int[4];
        int[] nArray3 = new int[4];
        int[] nArray4 = new int[4];
        switch (byArray.length) {
            case 16: {
                this._keyis128 = true;
                nArray[0] = this.bytes2int(byArray, 0);
                nArray[1] = this.bytes2int(byArray, 4);
                nArray[2] = this.bytes2int(byArray, 8);
                nArray[3] = this.bytes2int(byArray, 12);
                nArray[7] = 0;
                nArray[6] = 0;
                nArray[5] = 0;
                nArray[4] = 0;
                break;
            }
            case 24: {
                nArray[0] = this.bytes2int(byArray, 0);
                nArray[1] = this.bytes2int(byArray, 4);
                nArray[2] = this.bytes2int(byArray, 8);
                nArray[3] = this.bytes2int(byArray, 12);
                nArray[4] = this.bytes2int(byArray, 16);
                nArray[5] = this.bytes2int(byArray, 20);
                nArray[6] = ~nArray[4];
                nArray[7] = ~nArray[5];
                this._keyis128 = false;
                break;
            }
            case 32: {
                nArray[0] = this.bytes2int(byArray, 0);
                nArray[1] = this.bytes2int(byArray, 4);
                nArray[2] = this.bytes2int(byArray, 8);
                nArray[3] = this.bytes2int(byArray, 12);
                nArray[4] = this.bytes2int(byArray, 16);
                nArray[5] = this.bytes2int(byArray, 20);
                nArray[6] = this.bytes2int(byArray, 24);
                nArray[7] = this.bytes2int(byArray, 28);
                this._keyis128 = false;
                break;
            }
            default: {
                throw new IllegalArgumentException("key sizes are only 16/24/32 bytes.");
            }
        }
        for (n = 0; n < 4; ++n) {
            nArray2[n] = nArray[n] ^ nArray[n + 4];
        }
        this.camelliaF2(nArray2, SIGMA, 0);
        for (n = 0; n < 4; ++n) {
            int n2 = n;
            nArray2[n2] = nArray2[n2] ^ nArray[n];
        }
        this.camelliaF2(nArray2, SIGMA, 4);
        if (this._keyis128) {
            if (bl) {
                this.kw[0] = nArray[0];
                this.kw[1] = nArray[1];
                this.kw[2] = nArray[2];
                this.kw[3] = nArray[3];
                CamelliaLightEngine.roldq(15, nArray, 0, this.subkey, 4);
                CamelliaLightEngine.roldq(30, nArray, 0, this.subkey, 12);
                CamelliaLightEngine.roldq(15, nArray, 0, nArray4, 0);
                this.subkey[18] = nArray4[2];
                this.subkey[19] = nArray4[3];
                CamelliaLightEngine.roldq(17, nArray, 0, this.ke, 4);
                CamelliaLightEngine.roldq(17, nArray, 0, this.subkey, 24);
                CamelliaLightEngine.roldq(17, nArray, 0, this.subkey, 32);
                this.subkey[0] = nArray2[0];
                this.subkey[1] = nArray2[1];
                this.subkey[2] = nArray2[2];
                this.subkey[3] = nArray2[3];
                CamelliaLightEngine.roldq(15, nArray2, 0, this.subkey, 8);
                CamelliaLightEngine.roldq(15, nArray2, 0, this.ke, 0);
                CamelliaLightEngine.roldq(15, nArray2, 0, nArray4, 0);
                this.subkey[16] = nArray4[0];
                this.subkey[17] = nArray4[1];
                CamelliaLightEngine.roldq(15, nArray2, 0, this.subkey, 20);
                CamelliaLightEngine.roldqo32(34, nArray2, 0, this.subkey, 28);
                CamelliaLightEngine.roldq(17, nArray2, 0, this.kw, 4);
            } else {
                this.kw[4] = nArray[0];
                this.kw[5] = nArray[1];
                this.kw[6] = nArray[2];
                this.kw[7] = nArray[3];
                CamelliaLightEngine.decroldq(15, nArray, 0, this.subkey, 28);
                CamelliaLightEngine.decroldq(30, nArray, 0, this.subkey, 20);
                CamelliaLightEngine.decroldq(15, nArray, 0, nArray4, 0);
                this.subkey[16] = nArray4[0];
                this.subkey[17] = nArray4[1];
                CamelliaLightEngine.decroldq(17, nArray, 0, this.ke, 0);
                CamelliaLightEngine.decroldq(17, nArray, 0, this.subkey, 8);
                CamelliaLightEngine.decroldq(17, nArray, 0, this.subkey, 0);
                this.subkey[34] = nArray2[0];
                this.subkey[35] = nArray2[1];
                this.subkey[32] = nArray2[2];
                this.subkey[33] = nArray2[3];
                CamelliaLightEngine.decroldq(15, nArray2, 0, this.subkey, 24);
                CamelliaLightEngine.decroldq(15, nArray2, 0, this.ke, 4);
                CamelliaLightEngine.decroldq(15, nArray2, 0, nArray4, 0);
                this.subkey[18] = nArray4[2];
                this.subkey[19] = nArray4[3];
                CamelliaLightEngine.decroldq(15, nArray2, 0, this.subkey, 12);
                CamelliaLightEngine.decroldqo32(34, nArray2, 0, this.subkey, 4);
                CamelliaLightEngine.roldq(17, nArray2, 0, this.kw, 0);
            }
        } else {
            for (n = 0; n < 4; ++n) {
                nArray3[n] = nArray2[n] ^ nArray[n + 4];
            }
            this.camelliaF2(nArray3, SIGMA, 8);
            if (bl) {
                this.kw[0] = nArray[0];
                this.kw[1] = nArray[1];
                this.kw[2] = nArray[2];
                this.kw[3] = nArray[3];
                CamelliaLightEngine.roldqo32(45, nArray, 0, this.subkey, 16);
                CamelliaLightEngine.roldq(15, nArray, 0, this.ke, 4);
                CamelliaLightEngine.roldq(17, nArray, 0, this.subkey, 32);
                CamelliaLightEngine.roldqo32(34, nArray, 0, this.subkey, 44);
                CamelliaLightEngine.roldq(15, nArray, 4, this.subkey, 4);
                CamelliaLightEngine.roldq(15, nArray, 4, this.ke, 0);
                CamelliaLightEngine.roldq(30, nArray, 4, this.subkey, 24);
                CamelliaLightEngine.roldqo32(34, nArray, 4, this.subkey, 36);
                CamelliaLightEngine.roldq(15, nArray2, 0, this.subkey, 8);
                CamelliaLightEngine.roldq(30, nArray2, 0, this.subkey, 20);
                this.ke[8] = nArray2[1];
                this.ke[9] = nArray2[2];
                this.ke[10] = nArray2[3];
                this.ke[11] = nArray2[0];
                CamelliaLightEngine.roldqo32(49, nArray2, 0, this.subkey, 40);
                this.subkey[0] = nArray3[0];
                this.subkey[1] = nArray3[1];
                this.subkey[2] = nArray3[2];
                this.subkey[3] = nArray3[3];
                CamelliaLightEngine.roldq(30, nArray3, 0, this.subkey, 12);
                CamelliaLightEngine.roldq(30, nArray3, 0, this.subkey, 28);
                CamelliaLightEngine.roldqo32(51, nArray3, 0, this.kw, 4);
            } else {
                this.kw[4] = nArray[0];
                this.kw[5] = nArray[1];
                this.kw[6] = nArray[2];
                this.kw[7] = nArray[3];
                CamelliaLightEngine.decroldqo32(45, nArray, 0, this.subkey, 28);
                CamelliaLightEngine.decroldq(15, nArray, 0, this.ke, 4);
                CamelliaLightEngine.decroldq(17, nArray, 0, this.subkey, 12);
                CamelliaLightEngine.decroldqo32(34, nArray, 0, this.subkey, 0);
                CamelliaLightEngine.decroldq(15, nArray, 4, this.subkey, 40);
                CamelliaLightEngine.decroldq(15, nArray, 4, this.ke, 8);
                CamelliaLightEngine.decroldq(30, nArray, 4, this.subkey, 20);
                CamelliaLightEngine.decroldqo32(34, nArray, 4, this.subkey, 8);
                CamelliaLightEngine.decroldq(15, nArray2, 0, this.subkey, 36);
                CamelliaLightEngine.decroldq(30, nArray2, 0, this.subkey, 24);
                this.ke[2] = nArray2[1];
                this.ke[3] = nArray2[2];
                this.ke[0] = nArray2[3];
                this.ke[1] = nArray2[0];
                CamelliaLightEngine.decroldqo32(49, nArray2, 0, this.subkey, 4);
                this.subkey[46] = nArray3[0];
                this.subkey[47] = nArray3[1];
                this.subkey[44] = nArray3[2];
                this.subkey[45] = nArray3[3];
                CamelliaLightEngine.decroldq(30, nArray3, 0, this.subkey, 32);
                CamelliaLightEngine.decroldq(30, nArray3, 0, this.subkey, 16);
                CamelliaLightEngine.roldqo32(51, nArray3, 0, this.kw, 0);
            }
        }
    }

    private int processBlock128(byte[] byArray, int n, byte[] byArray2, int n2) {
        for (int i = 0; i < 4; ++i) {
            this.state[i] = this.bytes2int(byArray, n + i * 4);
            int n3 = i;
            this.state[n3] = this.state[n3] ^ this.kw[i];
        }
        this.camelliaF2(this.state, this.subkey, 0);
        this.camelliaF2(this.state, this.subkey, 4);
        this.camelliaF2(this.state, this.subkey, 8);
        this.camelliaFLs(this.state, this.ke, 0);
        this.camelliaF2(this.state, this.subkey, 12);
        this.camelliaF2(this.state, this.subkey, 16);
        this.camelliaF2(this.state, this.subkey, 20);
        this.camelliaFLs(this.state, this.ke, 4);
        this.camelliaF2(this.state, this.subkey, 24);
        this.camelliaF2(this.state, this.subkey, 28);
        this.camelliaF2(this.state, this.subkey, 32);
        this.state[2] = this.state[2] ^ this.kw[4];
        this.state[3] = this.state[3] ^ this.kw[5];
        this.state[0] = this.state[0] ^ this.kw[6];
        this.state[1] = this.state[1] ^ this.kw[7];
        this.int2bytes(this.state[2], byArray2, n2);
        this.int2bytes(this.state[3], byArray2, n2 + 4);
        this.int2bytes(this.state[0], byArray2, n2 + 8);
        this.int2bytes(this.state[1], byArray2, n2 + 12);
        return 16;
    }

    private int processBlock192or256(byte[] byArray, int n, byte[] byArray2, int n2) {
        for (int i = 0; i < 4; ++i) {
            this.state[i] = this.bytes2int(byArray, n + i * 4);
            int n3 = i;
            this.state[n3] = this.state[n3] ^ this.kw[i];
        }
        this.camelliaF2(this.state, this.subkey, 0);
        this.camelliaF2(this.state, this.subkey, 4);
        this.camelliaF2(this.state, this.subkey, 8);
        this.camelliaFLs(this.state, this.ke, 0);
        this.camelliaF2(this.state, this.subkey, 12);
        this.camelliaF2(this.state, this.subkey, 16);
        this.camelliaF2(this.state, this.subkey, 20);
        this.camelliaFLs(this.state, this.ke, 4);
        this.camelliaF2(this.state, this.subkey, 24);
        this.camelliaF2(this.state, this.subkey, 28);
        this.camelliaF2(this.state, this.subkey, 32);
        this.camelliaFLs(this.state, this.ke, 8);
        this.camelliaF2(this.state, this.subkey, 36);
        this.camelliaF2(this.state, this.subkey, 40);
        this.camelliaF2(this.state, this.subkey, 44);
        this.state[2] = this.state[2] ^ this.kw[4];
        this.state[3] = this.state[3] ^ this.kw[5];
        this.state[0] = this.state[0] ^ this.kw[6];
        this.state[1] = this.state[1] ^ this.kw[7];
        this.int2bytes(this.state[2], byArray2, n2);
        this.int2bytes(this.state[3], byArray2, n2 + 4);
        this.int2bytes(this.state[0], byArray2, n2 + 8);
        this.int2bytes(this.state[1], byArray2, n2 + 12);
        return 16;
    }

    public String getAlgorithmName() {
        return "Camellia";
    }

    public int getBlockSize() {
        return 16;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("only simple KeyParameter expected.");
        }
        this.setKey(bl, ((KeyParameter)cipherParameters).getKey());
        this.initialized = true;
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws IllegalStateException {
        if (!this.initialized) {
            throw new IllegalStateException("Camellia is not initialized");
        }
        if (n + 16 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + 16 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this._keyis128) {
            return this.processBlock128(byArray, n, byArray2, n2);
        }
        return this.processBlock192or256(byArray, n, byArray2, n2);
    }

    public void reset() {
    }
}

