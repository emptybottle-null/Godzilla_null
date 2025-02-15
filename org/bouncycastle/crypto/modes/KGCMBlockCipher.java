/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.crypto.modes.KCTRBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

public class KGCMBlockCipher
implements AEADBlockCipher {
    private static final BigInteger ZERO = BigInteger.valueOf(0L);
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private static final BigInteger MASK_1_128 = new BigInteger("340282366920938463463374607431768211456", 10);
    private static final BigInteger MASK_2_128 = new BigInteger("340282366920938463463374607431768211455", 10);
    private static final BigInteger POLYRED_128 = new BigInteger("135", 10);
    private static final BigInteger MASK_1_256 = new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639936", 10);
    private static final BigInteger MASK_2_256 = new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639935", 10);
    private static final BigInteger POLYRED_256 = new BigInteger("1061", 10);
    private static final BigInteger MASK_1_512 = new BigInteger("13407807929942597099574024998205846127479365820592393377723561443721764030073546976801874298166903427690031858186486050853753882811946569946433649006084096", 10);
    private static final BigInteger MASK_2_512 = new BigInteger("13407807929942597099574024998205846127479365820592393377723561443721764030073546976801874298166903427690031858186486050853753882811946569946433649006084095", 10);
    private static final BigInteger POLYRED_512 = new BigInteger("293", 10);
    private static final int MIN_MAC_BITS = 64;
    private static final int BITS_IN_BYTE = 8;
    private BlockCipher engine;
    private BufferedBlockCipher ctrEngine;
    private int macSize;
    private boolean forEncryption;
    private byte[] initialAssociatedText;
    private byte[] macBlock;
    private byte[] iv;
    private byte[] H;
    private byte[] b;
    private byte[] temp;
    private int lambda_o;
    private int lambda_c;
    private ExposedByteArrayOutputStream associatedText = new ExposedByteArrayOutputStream();
    private ExposedByteArrayOutputStream data = new ExposedByteArrayOutputStream();

    public KGCMBlockCipher(BlockCipher blockCipher) {
        this.engine = blockCipher;
        this.ctrEngine = new BufferedBlockCipher(new KCTRBlockCipher(this.engine));
        this.macSize = 0;
        this.initialAssociatedText = new byte[this.engine.getBlockSize()];
        this.iv = new byte[this.engine.getBlockSize()];
        this.H = new byte[this.engine.getBlockSize()];
        this.b = new byte[this.engine.getBlockSize()];
        this.temp = new byte[this.engine.getBlockSize()];
        this.lambda_c = 0;
        this.lambda_o = 0;
        this.macBlock = null;
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        KeyParameter keyParameter;
        this.forEncryption = bl;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters)cipherParameters;
            byte[] byArray = aEADParameters.getNonce();
            int n = this.iv.length - byArray.length;
            Arrays.fill(this.iv, (byte)0);
            System.arraycopy(byArray, 0, this.iv, n, byArray.length);
            this.initialAssociatedText = aEADParameters.getAssociatedText();
            int n2 = aEADParameters.getMacSize();
            if (n2 < 64 || n2 > this.engine.getBlockSize() * 8 || n2 % 8 != 0) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + n2);
            }
            this.macSize = n2 / 8;
            keyParameter = aEADParameters.getKey();
            if (this.initialAssociatedText != null) {
                this.processAADBytes(this.initialAssociatedText, 0, this.initialAssociatedText.length);
            }
        } else if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV)cipherParameters;
            byte[] byArray = parametersWithIV.getIV();
            int n = this.iv.length - byArray.length;
            Arrays.fill(this.iv, (byte)0);
            System.arraycopy(byArray, 0, this.iv, n, byArray.length);
            this.initialAssociatedText = null;
            this.macSize = this.engine.getBlockSize();
            keyParameter = (KeyParameter)parametersWithIV.getParameters();
        } else {
            throw new IllegalArgumentException("Invalid parameter passed");
        }
        this.macBlock = new byte[this.engine.getBlockSize()];
        this.ctrEngine.init(true, new ParametersWithIV(keyParameter, this.iv));
        this.engine.init(true, keyParameter);
    }

    public String getAlgorithmName() {
        return this.engine.getAlgorithmName() + "/KGCM";
    }

    public BlockCipher getUnderlyingCipher() {
        return this.engine;
    }

    public void processAADByte(byte by) {
        this.associatedText.write(by);
    }

    public void processAADBytes(byte[] byArray, int n, int n2) {
        this.associatedText.write(byArray, n, n2);
    }

    private void processAAD(byte[] byArray, int n, int n2) {
        this.lambda_o = n2 * 8;
        this.engine.processBlock(this.H, 0, this.H, 0);
        int n3 = n2;
        int n4 = n;
        while (n3 > 0) {
            for (int i = 0; i < this.engine.getBlockSize(); ++i) {
                int n5 = i;
                this.b[n5] = (byte)(this.b[n5] ^ byArray[n4 + i]);
            }
            this.multiplyOverField(this.engine.getBlockSize() * 8, this.b, this.H, this.temp);
            this.temp = Arrays.reverse(this.temp);
            System.arraycopy(this.temp, 0, this.b, 0, this.engine.getBlockSize());
            n3 -= this.engine.getBlockSize();
            n4 += this.engine.getBlockSize();
        }
    }

    public int processByte(byte by, byte[] byArray, int n) throws DataLengthException, IllegalStateException {
        this.data.write(by);
        return 0;
    }

    public int processBytes(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws DataLengthException, IllegalStateException {
        if (byArray.length < n + n2) {
            throw new DataLengthException("input buffer too short");
        }
        this.data.write(byArray, n, n2);
        return 0;
    }

    public int doFinal(byte[] byArray, int n) throws IllegalStateException, InvalidCipherTextException {
        int n2;
        int n3 = this.data.size();
        if (this.associatedText.size() > 0) {
            this.processAAD(this.associatedText.getBuffer(), 0, this.associatedText.size());
        }
        if (this.forEncryption) {
            if (byArray.length - n < n3 + this.macSize) {
                throw new OutputLengthException("Output buffer too short");
            }
            this.lambda_c = n3 * 8;
            n2 = this.ctrEngine.processBytes(this.data.getBuffer(), 0, n3, byArray, n);
            n2 += this.ctrEngine.doFinal(byArray, n + n2);
            this.calculateMac(byArray, n, n3);
        } else {
            this.lambda_c = (n3 - this.macSize) * 8;
            this.calculateMac(this.data.getBuffer(), 0, n3 - this.macSize);
            n2 = this.ctrEngine.processBytes(this.data.getBuffer(), 0, n3 - this.macSize, byArray, n);
            n2 += this.ctrEngine.doFinal(byArray, n + n2);
        }
        n += n2;
        if (this.macBlock == null) {
            throw new IllegalStateException("mac is not calculated");
        }
        if (this.forEncryption) {
            System.arraycopy(this.macBlock, 0, byArray, n, this.macSize);
            this.reset();
            return n2 + this.macSize;
        }
        byte[] byArray2 = new byte[this.macSize];
        System.arraycopy(this.data.getBuffer(), n2, byArray2, 0, this.macSize);
        byte[] byArray3 = new byte[this.macSize];
        System.arraycopy(this.macBlock, 0, byArray3, 0, this.macSize);
        if (!Arrays.constantTimeAreEqual(byArray2, byArray3)) {
            throw new InvalidCipherTextException("mac verification failed");
        }
        this.reset();
        return n2;
    }

    public byte[] getMac() {
        byte[] byArray = new byte[this.macSize];
        System.arraycopy(this.macBlock, 0, byArray, 0, this.macSize);
        return byArray;
    }

    public int getUpdateOutputSize(int n) {
        return n;
    }

    public int getOutputSize(int n) {
        if (this.forEncryption) {
            return n;
        }
        return n + this.macSize;
    }

    public void reset() {
        this.H = new byte[this.engine.getBlockSize()];
        this.b = new byte[this.engine.getBlockSize()];
        this.temp = new byte[this.engine.getBlockSize()];
        this.lambda_c = 0;
        this.lambda_o = 0;
        this.engine.reset();
        this.data.reset();
        this.associatedText.reset();
        if (this.initialAssociatedText != null) {
            this.processAADBytes(this.initialAssociatedText, 0, this.initialAssociatedText.length);
        }
    }

    private void calculateMac(byte[] byArray, int n, int n2) {
        int n3;
        this.macBlock = new byte[this.engine.getBlockSize()];
        int n4 = n2;
        int n5 = n;
        while (n4 > 0) {
            for (n3 = 0; n3 < this.engine.getBlockSize(); ++n3) {
                int n6 = n3;
                this.b[n6] = (byte)(this.b[n6] ^ byArray[n3 + n5]);
            }
            this.multiplyOverField(this.engine.getBlockSize() * 8, this.b, this.H, this.temp);
            this.temp = Arrays.reverse(this.temp);
            System.arraycopy(this.temp, 0, this.b, 0, this.engine.getBlockSize());
            n4 -= this.engine.getBlockSize();
            n5 += this.engine.getBlockSize();
        }
        Arrays.fill(this.temp, (byte)0);
        this.intToBytes(this.lambda_o, this.temp, 0);
        this.intToBytes(this.lambda_c, this.temp, this.engine.getBlockSize() / 2);
        for (n3 = 0; n3 < this.engine.getBlockSize(); ++n3) {
            int n7 = n3;
            this.b[n7] = (byte)(this.b[n7] ^ this.temp[n3]);
        }
        this.engine.processBlock(this.b, 0, this.macBlock, 0);
    }

    private void intToBytes(int n, byte[] byArray, int n2) {
        byArray[n2 + 3] = (byte)(n >> 24);
        byArray[n2 + 2] = (byte)(n >> 16);
        byArray[n2 + 1] = (byte)(n >> 8);
        byArray[n2] = (byte)n;
    }

    private void multiplyOverField(int n, byte[] byArray, byte[] byArray2, byte[] byArray3) {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        byte[] byArray4 = new byte[this.engine.getBlockSize()];
        byte[] byArray5 = new byte[this.engine.getBlockSize()];
        System.arraycopy(byArray, 0, byArray4, 0, this.engine.getBlockSize());
        System.arraycopy(byArray2, 0, byArray5, 0, this.engine.getBlockSize());
        byArray4 = Arrays.reverse(byArray4);
        byArray5 = Arrays.reverse(byArray5);
        switch (n) {
            case 128: {
                bigInteger3 = MASK_1_128;
                bigInteger2 = MASK_2_128;
                bigInteger = POLYRED_128;
                break;
            }
            case 256: {
                bigInteger3 = MASK_1_256;
                bigInteger2 = MASK_2_256;
                bigInteger = POLYRED_256;
                break;
            }
            case 512: {
                bigInteger3 = MASK_1_512;
                bigInteger2 = MASK_2_512;
                bigInteger = POLYRED_512;
                break;
            }
            default: {
                bigInteger3 = MASK_1_128;
                bigInteger2 = MASK_2_128;
                bigInteger = POLYRED_128;
            }
        }
        BigInteger bigInteger4 = ZERO;
        BigInteger bigInteger5 = new BigInteger(1, byArray4);
        BigInteger bigInteger6 = new BigInteger(1, byArray5);
        while (!bigInteger6.equals(ZERO)) {
            if (bigInteger6.and(ONE).equals(ONE)) {
                bigInteger4 = bigInteger4.xor(bigInteger5);
            }
            if (!(bigInteger5 = bigInteger5.shiftLeft(1)).and(bigInteger3).equals(ZERO)) {
                bigInteger5 = bigInteger5.xor(bigInteger);
            }
            bigInteger6 = bigInteger6.shiftRight(1);
        }
        byte[] byArray6 = BigIntegers.asUnsignedByteArray(bigInteger4.and(bigInteger2));
        Arrays.fill(byArray3, (byte)0);
        System.arraycopy(byArray6, 0, byArray3, 0, byArray6.length);
    }

    private class ExposedByteArrayOutputStream
    extends ByteArrayOutputStream {
        public byte[] getBuffer() {
            return this.buf;
        }
    }
}

