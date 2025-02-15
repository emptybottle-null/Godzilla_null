/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.MaxBytesExceededException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.SkippingStreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.Strings;

public class Salsa20Engine
implements SkippingStreamCipher {
    public static final int DEFAULT_ROUNDS = 20;
    private static final int STATE_SIZE = 16;
    private static final int[] TAU_SIGMA = Pack.littleEndianToInt(Strings.toByteArray("expand 16-byte kexpand 32-byte k"), 0, 8);
    protected static final byte[] sigma = Strings.toByteArray("expand 32-byte k");
    protected static final byte[] tau = Strings.toByteArray("expand 16-byte k");
    protected int rounds;
    private int index = 0;
    protected int[] engineState = new int[16];
    protected int[] x = new int[16];
    private byte[] keyStream = new byte[64];
    private boolean initialised = false;
    private int cW0;
    private int cW1;
    private int cW2;

    protected void packTauOrSigma(int n, int[] nArray, int n2) {
        int n3 = (n - 16) / 4;
        nArray[n2] = TAU_SIGMA[n3];
        nArray[n2 + 1] = TAU_SIGMA[n3 + 1];
        nArray[n2 + 2] = TAU_SIGMA[n3 + 2];
        nArray[n2 + 3] = TAU_SIGMA[n3 + 3];
    }

    public Salsa20Engine() {
        this(20);
    }

    public Salsa20Engine(int n) {
        if (n <= 0 || (n & 1) != 0) {
            throw new IllegalArgumentException("'rounds' must be a positive, even number");
        }
        this.rounds = n;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException(this.getAlgorithmName() + " Init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV)cipherParameters;
        byte[] byArray = parametersWithIV.getIV();
        if (byArray == null || byArray.length != this.getNonceSize()) {
            throw new IllegalArgumentException(this.getAlgorithmName() + " requires exactly " + this.getNonceSize() + " bytes of IV");
        }
        CipherParameters cipherParameters2 = parametersWithIV.getParameters();
        if (cipherParameters2 == null) {
            if (!this.initialised) {
                throw new IllegalStateException(this.getAlgorithmName() + " KeyParameter can not be null for first initialisation");
            }
            this.setKey(null, byArray);
        } else if (cipherParameters2 instanceof KeyParameter) {
            this.setKey(((KeyParameter)cipherParameters2).getKey(), byArray);
        } else {
            throw new IllegalArgumentException(this.getAlgorithmName() + " Init parameters must contain a KeyParameter (or null for re-init)");
        }
        this.reset();
        this.initialised = true;
    }

    protected int getNonceSize() {
        return 8;
    }

    public String getAlgorithmName() {
        String string = "Salsa20";
        if (this.rounds != 20) {
            string = string + "/" + this.rounds;
        }
        return string;
    }

    public byte returnByte(byte by) {
        if (this.limitExceeded()) {
            throw new MaxBytesExceededException("2^70 byte limit per IV; Change IV");
        }
        byte by2 = (byte)(this.keyStream[this.index] ^ by);
        this.index = this.index + 1 & 0x3F;
        if (this.index == 0) {
            this.advanceCounter();
            this.generateKeyStream(this.keyStream);
        }
        return by2;
    }

    protected void advanceCounter(long l) {
        int n = (int)(l >>> 32);
        int n2 = (int)l;
        if (n > 0) {
            this.engineState[9] = this.engineState[9] + n;
        }
        int n3 = this.engineState[8];
        this.engineState[8] = this.engineState[8] + n2;
        if (n3 != 0 && this.engineState[8] < n3) {
            this.engineState[9] = this.engineState[9] + 1;
        }
    }

    protected void advanceCounter() {
        this.engineState[8] = this.engineState[8] + 1;
        if (this.engineState[8] == 0) {
            this.engineState[9] = this.engineState[9] + 1;
        }
    }

    protected void retreatCounter(long l) {
        int n = (int)(l >>> 32);
        int n2 = (int)l;
        if (n != 0) {
            if (((long)this.engineState[9] & 0xFFFFFFFFL) >= ((long)n & 0xFFFFFFFFL)) {
                this.engineState[9] = this.engineState[9] - n;
            } else {
                throw new IllegalStateException("attempt to reduce counter past zero.");
            }
        }
        if (((long)this.engineState[8] & 0xFFFFFFFFL) >= ((long)n2 & 0xFFFFFFFFL)) {
            this.engineState[8] = this.engineState[8] - n2;
        } else if (this.engineState[9] != 0) {
            this.engineState[9] = this.engineState[9] - 1;
            this.engineState[8] = this.engineState[8] - n2;
        } else {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
    }

    protected void retreatCounter() {
        if (this.engineState[8] == 0 && this.engineState[9] == 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        this.engineState[8] = this.engineState[8] - 1;
        if (this.engineState[8] == -1) {
            this.engineState[9] = this.engineState[9] - 1;
        }
    }

    public int processBytes(byte[] byArray, int n, int n2, byte[] byArray2, int n3) {
        if (!this.initialised) {
            throw new IllegalStateException(this.getAlgorithmName() + " not initialised");
        }
        if (n + n2 > byArray.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (n3 + n2 > byArray2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.limitExceeded(n2)) {
            throw new MaxBytesExceededException("2^70 byte limit per IV would be exceeded; Change IV");
        }
        for (int i = 0; i < n2; ++i) {
            byArray2[i + n3] = (byte)(this.keyStream[this.index] ^ byArray[i + n]);
            this.index = this.index + 1 & 0x3F;
            if (this.index != 0) continue;
            this.advanceCounter();
            this.generateKeyStream(this.keyStream);
        }
        return n2;
    }

    public long skip(long l) {
        if (l >= 0L) {
            long l2 = l;
            if (l2 >= 64L) {
                long l3 = l2 / 64L;
                this.advanceCounter(l3);
                l2 -= l3 * 64L;
            }
            int n = this.index;
            this.index = this.index + (int)l2 & 0x3F;
            if (this.index < n) {
                this.advanceCounter();
            }
        } else {
            long l4;
            long l5 = -l;
            if (l5 >= 64L) {
                l4 = l5 / 64L;
                this.retreatCounter(l4);
                l5 -= l4 * 64L;
            }
            for (l4 = 0L; l4 < l5; ++l4) {
                if (this.index == 0) {
                    this.retreatCounter();
                }
                this.index = this.index - 1 & 0x3F;
            }
        }
        this.generateKeyStream(this.keyStream);
        return l;
    }

    public long seekTo(long l) {
        this.reset();
        return this.skip(l);
    }

    public long getPosition() {
        return this.getCounter() * 64L + (long)this.index;
    }

    public void reset() {
        this.index = 0;
        this.resetLimitCounter();
        this.resetCounter();
        this.generateKeyStream(this.keyStream);
    }

    protected long getCounter() {
        return (long)this.engineState[9] << 32 | (long)this.engineState[8] & 0xFFFFFFFFL;
    }

    protected void resetCounter() {
        this.engineState[9] = 0;
        this.engineState[8] = 0;
    }

    protected void setKey(byte[] byArray, byte[] byArray2) {
        if (byArray != null) {
            if (byArray.length != 16 && byArray.length != 32) {
                throw new IllegalArgumentException(this.getAlgorithmName() + " requires 128 bit or 256 bit key");
            }
            int n = (byArray.length - 16) / 4;
            this.engineState[0] = TAU_SIGMA[n];
            this.engineState[5] = TAU_SIGMA[n + 1];
            this.engineState[10] = TAU_SIGMA[n + 2];
            this.engineState[15] = TAU_SIGMA[n + 3];
            Pack.littleEndianToInt(byArray, 0, this.engineState, 1, 4);
            Pack.littleEndianToInt(byArray, byArray.length - 16, this.engineState, 11, 4);
        }
        Pack.littleEndianToInt(byArray2, 0, this.engineState, 6, 2);
    }

    protected void generateKeyStream(byte[] byArray) {
        Salsa20Engine.salsaCore(this.rounds, this.engineState, this.x);
        Pack.intToLittleEndian(this.x, byArray, 0);
    }

    public static void salsaCore(int n, int[] nArray, int[] nArray2) {
        if (nArray.length != 16) {
            throw new IllegalArgumentException();
        }
        if (nArray2.length != 16) {
            throw new IllegalArgumentException();
        }
        if (n % 2 != 0) {
            throw new IllegalArgumentException("Number of rounds must be even");
        }
        int n2 = nArray[0];
        int n3 = nArray[1];
        int n4 = nArray[2];
        int n5 = nArray[3];
        int n6 = nArray[4];
        int n7 = nArray[5];
        int n8 = nArray[6];
        int n9 = nArray[7];
        int n10 = nArray[8];
        int n11 = nArray[9];
        int n12 = nArray[10];
        int n13 = nArray[11];
        int n14 = nArray[12];
        int n15 = nArray[13];
        int n16 = nArray[14];
        int n17 = nArray[15];
        for (int i = n; i > 0; i -= 2) {
            n10 ^= Salsa20Engine.rotl((n6 ^= Salsa20Engine.rotl(n2 + n14, 7)) + n2, 9);
            n2 ^= Salsa20Engine.rotl((n14 ^= Salsa20Engine.rotl(n10 + n6, 13)) + n10, 18);
            n15 ^= Salsa20Engine.rotl((n11 ^= Salsa20Engine.rotl(n7 + n3, 7)) + n7, 9);
            n7 ^= Salsa20Engine.rotl((n3 ^= Salsa20Engine.rotl(n15 + n11, 13)) + n15, 18);
            n4 ^= Salsa20Engine.rotl((n16 ^= Salsa20Engine.rotl(n12 + n8, 7)) + n12, 9);
            n12 ^= Salsa20Engine.rotl((n8 ^= Salsa20Engine.rotl(n4 + n16, 13)) + n4, 18);
            n9 ^= Salsa20Engine.rotl((n5 ^= Salsa20Engine.rotl(n17 + n13, 7)) + n17, 9);
            n17 ^= Salsa20Engine.rotl((n13 ^= Salsa20Engine.rotl(n9 + n5, 13)) + n9, 18);
            n4 ^= Salsa20Engine.rotl((n3 ^= Salsa20Engine.rotl(n2 + n5, 7)) + n2, 9);
            n2 ^= Salsa20Engine.rotl((n5 ^= Salsa20Engine.rotl(n4 + n3, 13)) + n4, 18);
            n9 ^= Salsa20Engine.rotl((n8 ^= Salsa20Engine.rotl(n7 + n6, 7)) + n7, 9);
            n7 ^= Salsa20Engine.rotl((n6 ^= Salsa20Engine.rotl(n9 + n8, 13)) + n9, 18);
            n10 ^= Salsa20Engine.rotl((n13 ^= Salsa20Engine.rotl(n12 + n11, 7)) + n12, 9);
            n12 ^= Salsa20Engine.rotl((n11 ^= Salsa20Engine.rotl(n10 + n13, 13)) + n10, 18);
            n15 ^= Salsa20Engine.rotl((n14 ^= Salsa20Engine.rotl(n17 + n16, 7)) + n17, 9);
            n17 ^= Salsa20Engine.rotl((n16 ^= Salsa20Engine.rotl(n15 + n14, 13)) + n15, 18);
        }
        nArray2[0] = n2 + nArray[0];
        nArray2[1] = n3 + nArray[1];
        nArray2[2] = n4 + nArray[2];
        nArray2[3] = n5 + nArray[3];
        nArray2[4] = n6 + nArray[4];
        nArray2[5] = n7 + nArray[5];
        nArray2[6] = n8 + nArray[6];
        nArray2[7] = n9 + nArray[7];
        nArray2[8] = n10 + nArray[8];
        nArray2[9] = n11 + nArray[9];
        nArray2[10] = n12 + nArray[10];
        nArray2[11] = n13 + nArray[11];
        nArray2[12] = n14 + nArray[12];
        nArray2[13] = n15 + nArray[13];
        nArray2[14] = n16 + nArray[14];
        nArray2[15] = n17 + nArray[15];
    }

    protected static int rotl(int n, int n2) {
        return n << n2 | n >>> -n2;
    }

    private void resetLimitCounter() {
        this.cW0 = 0;
        this.cW1 = 0;
        this.cW2 = 0;
    }

    private boolean limitExceeded() {
        if (++this.cW0 == 0 && ++this.cW1 == 0) {
            return (++this.cW2 & 0x20) != 0;
        }
        return false;
    }

    private boolean limitExceeded(int n) {
        this.cW0 += n;
        if (this.cW0 < n && this.cW0 >= 0 && ++this.cW1 == 0) {
            return (++this.cW2 & 0x20) != 0;
        }
        return false;
    }
}

