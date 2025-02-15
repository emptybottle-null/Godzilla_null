/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.macs.CBCBlockCipherMac;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.crypto.modes.SICBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

public class CCMBlockCipher
implements AEADBlockCipher {
    private BlockCipher cipher;
    private int blockSize;
    private boolean forEncryption;
    private byte[] nonce;
    private byte[] initialAssociatedText;
    private int macSize;
    private CipherParameters keyParam;
    private byte[] macBlock;
    private ExposedByteArrayOutputStream associatedText = new ExposedByteArrayOutputStream();
    private ExposedByteArrayOutputStream data = new ExposedByteArrayOutputStream();

    public CCMBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        this.blockSize = blockCipher.getBlockSize();
        this.macBlock = new byte[this.blockSize];
        if (this.blockSize != 16) {
            throw new IllegalArgumentException("cipher required with a block size of 16.");
        }
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        CipherParameters cipherParameters2;
        this.forEncryption = bl;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters)cipherParameters;
            this.nonce = aEADParameters.getNonce();
            this.initialAssociatedText = aEADParameters.getAssociatedText();
            this.macSize = aEADParameters.getMacSize() / 8;
            cipherParameters2 = aEADParameters.getKey();
        } else if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV)cipherParameters;
            this.nonce = parametersWithIV.getIV();
            this.initialAssociatedText = null;
            this.macSize = this.macBlock.length / 2;
            cipherParameters2 = parametersWithIV.getParameters();
        } else {
            throw new IllegalArgumentException("invalid parameters passed to CCM: " + cipherParameters.getClass().getName());
        }
        if (cipherParameters2 != null) {
            this.keyParam = cipherParameters2;
        }
        if (this.nonce == null || this.nonce.length < 7 || this.nonce.length > 13) {
            throw new IllegalArgumentException("nonce must have length from 7 to 13 octets");
        }
        this.reset();
    }

    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/CCM";
    }

    public void processAADByte(byte by) {
        this.associatedText.write(by);
    }

    public void processAADBytes(byte[] byArray, int n, int n2) {
        this.associatedText.write(byArray, n, n2);
    }

    public int processByte(byte by, byte[] byArray, int n) throws DataLengthException, IllegalStateException {
        this.data.write(by);
        return 0;
    }

    public int processBytes(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws DataLengthException, IllegalStateException {
        if (byArray.length < n + n2) {
            throw new DataLengthException("Input buffer too short");
        }
        this.data.write(byArray, n, n2);
        return 0;
    }

    public int doFinal(byte[] byArray, int n) throws IllegalStateException, InvalidCipherTextException {
        int n2 = this.processPacket(this.data.getBuffer(), 0, this.data.size(), byArray, n);
        this.reset();
        return n2;
    }

    public void reset() {
        this.cipher.reset();
        this.associatedText.reset();
        this.data.reset();
    }

    public byte[] getMac() {
        byte[] byArray = new byte[this.macSize];
        System.arraycopy(this.macBlock, 0, byArray, 0, byArray.length);
        return byArray;
    }

    public int getUpdateOutputSize(int n) {
        return 0;
    }

    public int getOutputSize(int n) {
        int n2 = n + this.data.size();
        if (this.forEncryption) {
            return n2 + this.macSize;
        }
        return n2 < this.macSize ? 0 : n2 - this.macSize;
    }

    public byte[] processPacket(byte[] byArray, int n, int n2) throws IllegalStateException, InvalidCipherTextException {
        byte[] byArray2;
        if (this.forEncryption) {
            byArray2 = new byte[n2 + this.macSize];
        } else {
            if (n2 < this.macSize) {
                throw new InvalidCipherTextException("data too short");
            }
            byArray2 = new byte[n2 - this.macSize];
        }
        this.processPacket(byArray, n, n2, byArray2, 0);
        return byArray2;
    }

    public int processPacket(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws IllegalStateException, InvalidCipherTextException, DataLengthException {
        int n4;
        int n5;
        int n6;
        if (this.keyParam == null) {
            throw new IllegalStateException("CCM cipher unitialized.");
        }
        int n7 = this.nonce.length;
        int n8 = 15 - n7;
        if (n8 < 4 && n2 >= (n6 = 1 << 8 * n8)) {
            throw new IllegalStateException("CCM packet too large for choice of q.");
        }
        byte[] byArray3 = new byte[this.blockSize];
        byArray3[0] = (byte)(n8 - 1 & 7);
        System.arraycopy(this.nonce, 0, byArray3, 1, this.nonce.length);
        SICBlockCipher sICBlockCipher = new SICBlockCipher(this.cipher);
        sICBlockCipher.init(this.forEncryption, new ParametersWithIV(this.keyParam, byArray3));
        int n9 = n3;
        if (this.forEncryption) {
            n5 = n2 + this.macSize;
            if (byArray2.length < n5 + n3) {
                throw new OutputLengthException("Output buffer too short.");
            }
            this.calculateMac(byArray, n, n2, this.macBlock);
            byte[] byArray4 = new byte[this.blockSize];
            sICBlockCipher.processBlock(this.macBlock, 0, byArray4, 0);
            for (n4 = n; n4 < n + n2 - this.blockSize; n4 += this.blockSize) {
                sICBlockCipher.processBlock(byArray, n4, byArray2, n9);
                n9 += this.blockSize;
            }
            byte[] byArray5 = new byte[this.blockSize];
            System.arraycopy(byArray, n4, byArray5, 0, n2 + n - n4);
            sICBlockCipher.processBlock(byArray5, 0, byArray5, 0);
            System.arraycopy(byArray5, 0, byArray2, n9, n2 + n - n4);
            System.arraycopy(byArray4, 0, byArray2, n3 + n2, this.macSize);
        } else {
            if (n2 < this.macSize) {
                throw new InvalidCipherTextException("data too short");
            }
            n5 = n2 - this.macSize;
            if (byArray2.length < n5 + n3) {
                throw new OutputLengthException("Output buffer too short.");
            }
            System.arraycopy(byArray, n + n5, this.macBlock, 0, this.macSize);
            sICBlockCipher.processBlock(this.macBlock, 0, this.macBlock, 0);
            for (int i = this.macSize; i != this.macBlock.length; ++i) {
                this.macBlock[i] = 0;
            }
            while (n4 < n + n5 - this.blockSize) {
                sICBlockCipher.processBlock(byArray, n4, byArray2, n9);
                n9 += this.blockSize;
                n4 += this.blockSize;
            }
            byte[] byArray6 = new byte[this.blockSize];
            System.arraycopy(byArray, n4, byArray6, 0, n5 - (n4 - n));
            sICBlockCipher.processBlock(byArray6, 0, byArray6, 0);
            System.arraycopy(byArray6, 0, byArray2, n9, n5 - (n4 - n));
            byte[] byArray7 = new byte[this.blockSize];
            this.calculateMac(byArray2, n3, n5, byArray7);
            if (!Arrays.constantTimeAreEqual(this.macBlock, byArray7)) {
                throw new InvalidCipherTextException("mac check in CCM failed");
            }
        }
        return n5;
    }

    private int calculateMac(byte[] byArray, int n, int n2, byte[] byArray2) {
        CBCBlockCipherMac cBCBlockCipherMac = new CBCBlockCipherMac(this.cipher, this.macSize * 8);
        cBCBlockCipherMac.init(this.keyParam);
        byte[] byArray3 = new byte[16];
        if (this.hasAssociatedText()) {
            byArray3[0] = (byte)(byArray3[0] | 0x40);
        }
        byArray3[0] = (byte)(byArray3[0] | ((cBCBlockCipherMac.getMacSize() - 2) / 2 & 7) << 3);
        byArray3[0] = (byte)(byArray3[0] | 15 - this.nonce.length - 1 & 7);
        System.arraycopy(this.nonce, 0, byArray3, 1, this.nonce.length);
        int n3 = n2;
        int n4 = 1;
        while (n3 > 0) {
            byArray3[byArray3.length - n4] = (byte)(n3 & 0xFF);
            n3 >>>= 8;
            ++n4;
        }
        cBCBlockCipherMac.update(byArray3, 0, byArray3.length);
        if (this.hasAssociatedText()) {
            int n5;
            int n6 = this.getAssociatedTextLength();
            if (n6 < 65280) {
                cBCBlockCipherMac.update((byte)(n6 >> 8));
                cBCBlockCipherMac.update((byte)n6);
                n5 = 2;
            } else {
                cBCBlockCipherMac.update((byte)-1);
                cBCBlockCipherMac.update((byte)-2);
                cBCBlockCipherMac.update((byte)(n6 >> 24));
                cBCBlockCipherMac.update((byte)(n6 >> 16));
                cBCBlockCipherMac.update((byte)(n6 >> 8));
                cBCBlockCipherMac.update((byte)n6);
                n5 = 6;
            }
            if (this.initialAssociatedText != null) {
                cBCBlockCipherMac.update(this.initialAssociatedText, 0, this.initialAssociatedText.length);
            }
            if (this.associatedText.size() > 0) {
                cBCBlockCipherMac.update(this.associatedText.getBuffer(), 0, this.associatedText.size());
            }
            if ((n5 = (n5 + n6) % 16) != 0) {
                for (int i = n5; i != 16; ++i) {
                    cBCBlockCipherMac.update((byte)0);
                }
            }
        }
        cBCBlockCipherMac.update(byArray, n, n2);
        return cBCBlockCipherMac.doFinal(byArray2, 0);
    }

    private int getAssociatedTextLength() {
        return this.associatedText.size() + (this.initialAssociatedText == null ? 0 : this.initialAssociatedText.length);
    }

    private boolean hasAssociatedText() {
        return this.getAssociatedTextLength() > 0;
    }

    private class ExposedByteArrayOutputStream
    extends ByteArrayOutputStream {
        public byte[] getBuffer() {
            return this.buf;
        }
    }
}

