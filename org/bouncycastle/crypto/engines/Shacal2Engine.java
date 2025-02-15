/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

public class Shacal2Engine
implements BlockCipher {
    private static final int[] K = new int[]{1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998};
    private static final int BLOCK_SIZE = 32;
    private boolean forEncryption = false;
    private static final int ROUNDS = 64;
    private int[] workingKey = null;

    public void reset() {
    }

    public String getAlgorithmName() {
        return "Shacal2";
    }

    public int getBlockSize() {
        return 32;
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("only simple KeyParameter expected.");
        }
        this.forEncryption = bl;
        this.workingKey = new int[64];
        this.setKey(((KeyParameter)cipherParameters).getKey());
    }

    public void setKey(byte[] byArray) {
        if (byArray.length == 0 || byArray.length > 64 || byArray.length < 16 || byArray.length % 8 != 0) {
            throw new IllegalArgumentException("Shacal2-key must be 16 - 64 bytes and multiple of 8");
        }
        this.bytes2ints(byArray, this.workingKey, 0, 0);
        for (int i = 16; i < 64; ++i) {
            this.workingKey[i] = ((this.workingKey[i - 2] >>> 17 | this.workingKey[i - 2] << -17) ^ (this.workingKey[i - 2] >>> 19 | this.workingKey[i - 2] << -19) ^ this.workingKey[i - 2] >>> 10) + this.workingKey[i - 7] + ((this.workingKey[i - 15] >>> 7 | this.workingKey[i - 15] << -7) ^ (this.workingKey[i - 15] >>> 18 | this.workingKey[i - 15] << -18) ^ this.workingKey[i - 15] >>> 3) + this.workingKey[i - 16];
        }
    }

    private void encryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        int[] nArray = new int[8];
        this.byteBlockToInts(byArray, nArray, n, 0);
        for (int i = 0; i < 64; ++i) {
            int n3 = ((nArray[4] >>> 6 | nArray[4] << -6) ^ (nArray[4] >>> 11 | nArray[4] << -11) ^ (nArray[4] >>> 25 | nArray[4] << -25)) + (nArray[4] & nArray[5] ^ ~nArray[4] & nArray[6]) + nArray[7] + K[i] + this.workingKey[i];
            nArray[7] = nArray[6];
            nArray[6] = nArray[5];
            nArray[5] = nArray[4];
            nArray[4] = nArray[3] + n3;
            nArray[3] = nArray[2];
            nArray[2] = nArray[1];
            nArray[1] = nArray[0];
            nArray[0] = n3 + ((nArray[0] >>> 2 | nArray[0] << -2) ^ (nArray[0] >>> 13 | nArray[0] << -13) ^ (nArray[0] >>> 22 | nArray[0] << -22)) + (nArray[0] & nArray[2] ^ nArray[0] & nArray[3] ^ nArray[2] & nArray[3]);
        }
        this.ints2bytes(nArray, byArray2, n2);
    }

    private void decryptBlock(byte[] byArray, int n, byte[] byArray2, int n2) {
        int[] nArray = new int[8];
        this.byteBlockToInts(byArray, nArray, n, 0);
        for (int i = 63; i > -1; --i) {
            int n3 = nArray[0] - ((nArray[1] >>> 2 | nArray[1] << -2) ^ (nArray[1] >>> 13 | nArray[1] << -13) ^ (nArray[1] >>> 22 | nArray[1] << -22)) - (nArray[1] & nArray[2] ^ nArray[1] & nArray[3] ^ nArray[2] & nArray[3]);
            nArray[0] = nArray[1];
            nArray[1] = nArray[2];
            nArray[2] = nArray[3];
            nArray[3] = nArray[4] - n3;
            nArray[4] = nArray[5];
            nArray[5] = nArray[6];
            nArray[6] = nArray[7];
            nArray[7] = n3 - K[i] - this.workingKey[i] - ((nArray[4] >>> 6 | nArray[4] << -6) ^ (nArray[4] >>> 11 | nArray[4] << -11) ^ (nArray[4] >>> 25 | nArray[4] << -25)) - (nArray[4] & nArray[5] ^ ~nArray[4] & nArray[6]);
        }
        this.ints2bytes(nArray, byArray2, n2);
    }

    public int processBlock(byte[] byArray, int n, byte[] byArray2, int n2) throws DataLengthException, IllegalStateException {
        if (this.workingKey == null) {
            throw new IllegalStateException("Shacal2 not initialised");
        }
        if (n + 32 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n2 + 32 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.forEncryption) {
            this.encryptBlock(byArray, n, byArray2, n2);
        } else {
            this.decryptBlock(byArray, n, byArray2, n2);
        }
        return 32;
    }

    private void byteBlockToInts(byte[] byArray, int[] nArray, int n, int n2) {
        for (int i = n2; i < 8; ++i) {
            nArray[i] = (byArray[n++] & 0xFF) << 24 | (byArray[n++] & 0xFF) << 16 | (byArray[n++] & 0xFF) << 8 | byArray[n++] & 0xFF;
        }
    }

    private void bytes2ints(byte[] byArray, int[] nArray, int n, int n2) {
        for (int i = n2; i < byArray.length / 4; ++i) {
            nArray[i] = (byArray[n++] & 0xFF) << 24 | (byArray[n++] & 0xFF) << 16 | (byArray[n++] & 0xFF) << 8 | byArray[n++] & 0xFF;
        }
    }

    private void ints2bytes(int[] nArray, byte[] byArray, int n) {
        for (int i = 0; i < nArray.length; ++i) {
            byArray[n++] = (byte)(nArray[i] >>> 24);
            byArray[n++] = (byte)(nArray[i] >>> 16);
            byArray[n++] = (byte)(nArray[i] >>> 8);
            byArray[n++] = (byte)nArray[i];
        }
    }
}

