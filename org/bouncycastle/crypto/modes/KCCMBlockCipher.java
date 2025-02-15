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
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

public class KCCMBlockCipher
implements AEADBlockCipher {
    private static final int BYTES_IN_INT = 4;
    private static final int BITS_IN_BYTE = 8;
    private static final int MAX_MAC_BIT_LENGTH = 512;
    private static final int MIN_MAC_BIT_LENGTH = 64;
    private BlockCipher engine;
    private int macSize;
    private boolean forEncryption;
    private byte[] initialAssociatedText;
    private byte[] mac;
    private byte[] macBlock;
    private byte[] nonce;
    private byte[] G1;
    private byte[] buffer;
    private byte[] s;
    private byte[] counter;
    private ExposedByteArrayOutputStream associatedText = new ExposedByteArrayOutputStream();
    private ExposedByteArrayOutputStream data = new ExposedByteArrayOutputStream();
    private int Nb_ = 4;

    private void setNb(int n) {
        if (n != 4 && n != 6 && n != 8) {
            throw new IllegalArgumentException("Nb = 4 is recommended by DSTU7624 but can be changed to only 6 or 8 in this implementation");
        }
        this.Nb_ = n;
    }

    public KCCMBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, 4);
    }

    public KCCMBlockCipher(BlockCipher blockCipher, int n) {
        this.engine = blockCipher;
        this.macSize = blockCipher.getBlockSize();
        this.nonce = new byte[blockCipher.getBlockSize()];
        this.initialAssociatedText = new byte[blockCipher.getBlockSize()];
        this.mac = new byte[blockCipher.getBlockSize()];
        this.macBlock = new byte[blockCipher.getBlockSize()];
        this.G1 = new byte[blockCipher.getBlockSize()];
        this.buffer = new byte[blockCipher.getBlockSize()];
        this.s = new byte[blockCipher.getBlockSize()];
        this.counter = new byte[blockCipher.getBlockSize()];
        this.setNb(n);
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        CipherParameters cipherParameters2;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters)cipherParameters;
            if (aEADParameters.getMacSize() > 512 || aEADParameters.getMacSize() < 64 || aEADParameters.getMacSize() % 8 != 0) {
                throw new IllegalArgumentException("Invalid mac size specified");
            }
            this.nonce = aEADParameters.getNonce();
            this.macSize = aEADParameters.getMacSize() / 8;
            this.initialAssociatedText = aEADParameters.getAssociatedText();
            cipherParameters2 = aEADParameters.getKey();
        } else if (cipherParameters instanceof ParametersWithIV) {
            this.nonce = ((ParametersWithIV)cipherParameters).getIV();
            this.macSize = this.engine.getBlockSize();
            this.initialAssociatedText = null;
            cipherParameters2 = ((ParametersWithIV)cipherParameters).getParameters();
        } else {
            throw new IllegalArgumentException("Invalid parameters specified");
        }
        this.mac = new byte[this.macSize];
        this.forEncryption = bl;
        this.engine.init(true, cipherParameters2);
        this.counter[0] = 1;
        if (this.initialAssociatedText != null) {
            this.processAADBytes(this.initialAssociatedText, 0, this.initialAssociatedText.length);
        }
    }

    public String getAlgorithmName() {
        return this.engine.getAlgorithmName() + "/KCCM";
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

    private void processAAD(byte[] byArray, int n, int n2, int n3) {
        int n4;
        if (n2 - n < this.engine.getBlockSize()) {
            throw new IllegalArgumentException("authText buffer too short");
        }
        if (n2 % this.engine.getBlockSize() != 0) {
            throw new IllegalArgumentException("padding not supported");
        }
        System.arraycopy(this.nonce, 0, this.G1, 0, this.nonce.length - this.Nb_ - 1);
        this.intToBytes(n3, this.buffer, 0);
        System.arraycopy(this.buffer, 0, this.G1, this.nonce.length - this.Nb_ - 1, 4);
        this.G1[this.G1.length - 1] = this.getFlag(true, this.macSize);
        this.engine.processBlock(this.G1, 0, this.macBlock, 0);
        this.intToBytes(n2, this.buffer, 0);
        if (n2 <= this.engine.getBlockSize() - this.Nb_) {
            int n5;
            for (n5 = 0; n5 < n2; ++n5) {
                int n6 = n5 + this.Nb_;
                this.buffer[n6] = (byte)(this.buffer[n6] ^ byArray[n + n5]);
            }
            for (n5 = 0; n5 < this.engine.getBlockSize(); ++n5) {
                int n7 = n5;
                this.macBlock[n7] = (byte)(this.macBlock[n7] ^ this.buffer[n5]);
            }
            this.engine.processBlock(this.macBlock, 0, this.macBlock, 0);
            return;
        }
        for (n4 = 0; n4 < this.engine.getBlockSize(); ++n4) {
            int n8 = n4;
            this.macBlock[n8] = (byte)(this.macBlock[n8] ^ this.buffer[n4]);
        }
        this.engine.processBlock(this.macBlock, 0, this.macBlock, 0);
        for (n4 = n2; n4 != 0; n4 -= this.engine.getBlockSize()) {
            for (int i = 0; i < this.engine.getBlockSize(); ++i) {
                int n9 = i;
                this.macBlock[n9] = (byte)(this.macBlock[n9] ^ byArray[i + n]);
            }
            this.engine.processBlock(this.macBlock, 0, this.macBlock, 0);
            n += this.engine.getBlockSize();
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

    public int processPacket(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws IllegalStateException, InvalidCipherTextException {
        int n4;
        if (byArray.length - n < n2) {
            throw new DataLengthException("input buffer too short");
        }
        if (byArray2.length - n3 < n2) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.associatedText.size() > 0) {
            if (this.forEncryption) {
                this.processAAD(this.associatedText.getBuffer(), 0, this.associatedText.size(), this.data.size());
            } else {
                this.processAAD(this.associatedText.getBuffer(), 0, this.associatedText.size(), this.data.size() - this.macSize);
            }
        }
        if (this.forEncryption) {
            int n5;
            if (n2 % this.engine.getBlockSize() != 0) {
                throw new DataLengthException("partial blocks not supported");
            }
            this.CalculateMac(byArray, n, n2);
            this.engine.processBlock(this.nonce, 0, this.s, 0);
            int n6 = n2;
            while (n6 > 0) {
                this.ProcessBlock(byArray, n, n2, byArray2, n3);
                n6 -= this.engine.getBlockSize();
                n += this.engine.getBlockSize();
                n3 += this.engine.getBlockSize();
            }
            for (n5 = 0; n5 < this.counter.length; ++n5) {
                int n7 = n5;
                this.s[n7] = (byte)(this.s[n7] + this.counter[n5]);
            }
            this.engine.processBlock(this.s, 0, this.buffer, 0);
            for (n5 = 0; n5 < this.macSize; ++n5) {
                byArray2[n3 + n5] = (byte)(this.buffer[n5] ^ this.macBlock[n5]);
            }
            System.arraycopy(this.macBlock, 0, this.mac, 0, this.macSize);
            this.reset();
            return n2 + this.macSize;
        }
        if ((n2 - this.macSize) % this.engine.getBlockSize() != 0) {
            throw new DataLengthException("partial blocks not supported");
        }
        this.engine.processBlock(this.nonce, 0, this.s, 0);
        int n8 = n2 / this.engine.getBlockSize();
        for (n4 = 0; n4 < n8; ++n4) {
            this.ProcessBlock(byArray, n, n2, byArray2, n3);
            n += this.engine.getBlockSize();
            n3 += this.engine.getBlockSize();
        }
        if (n2 > n) {
            for (n4 = 0; n4 < this.counter.length; ++n4) {
                int n9 = n4;
                this.s[n9] = (byte)(this.s[n9] + this.counter[n4]);
            }
            this.engine.processBlock(this.s, 0, this.buffer, 0);
            for (n4 = 0; n4 < this.macSize; ++n4) {
                byArray2[n3 + n4] = (byte)(this.buffer[n4] ^ byArray[n + n4]);
            }
            n3 += this.macSize;
        }
        for (n4 = 0; n4 < this.counter.length; ++n4) {
            int n10 = n4;
            this.s[n10] = (byte)(this.s[n10] + this.counter[n4]);
        }
        this.engine.processBlock(this.s, 0, this.buffer, 0);
        System.arraycopy(byArray2, n3 - this.macSize, this.buffer, 0, this.macSize);
        this.CalculateMac(byArray2, 0, n3 - this.macSize);
        System.arraycopy(this.macBlock, 0, this.mac, 0, this.macSize);
        byte[] byArray3 = new byte[this.macSize];
        System.arraycopy(this.buffer, 0, byArray3, 0, this.macSize);
        if (!Arrays.constantTimeAreEqual(this.mac, byArray3)) {
            throw new InvalidCipherTextException("mac check failed");
        }
        this.reset();
        return n2 - this.macSize;
    }

    private void ProcessBlock(byte[] byArray, int n, int n2, byte[] byArray2, int n3) {
        int n4;
        for (n4 = 0; n4 < this.counter.length; ++n4) {
            int n5 = n4;
            this.s[n5] = (byte)(this.s[n5] + this.counter[n4]);
        }
        this.engine.processBlock(this.s, 0, this.buffer, 0);
        for (n4 = 0; n4 < this.engine.getBlockSize(); ++n4) {
            byArray2[n3 + n4] = (byte)(this.buffer[n4] ^ byArray[n + n4]);
        }
    }

    private void CalculateMac(byte[] byArray, int n, int n2) {
        int n3 = n2;
        while (n3 > 0) {
            for (int i = 0; i < this.engine.getBlockSize(); ++i) {
                int n4 = i;
                this.macBlock[n4] = (byte)(this.macBlock[n4] ^ byArray[n + i]);
            }
            this.engine.processBlock(this.macBlock, 0, this.macBlock, 0);
            n3 -= this.engine.getBlockSize();
            n += this.engine.getBlockSize();
        }
    }

    public int doFinal(byte[] byArray, int n) throws IllegalStateException, InvalidCipherTextException {
        int n2 = this.processPacket(this.data.getBuffer(), 0, this.data.size(), byArray, n);
        this.reset();
        return n2;
    }

    public byte[] getMac() {
        return Arrays.clone(this.mac);
    }

    public int getUpdateOutputSize(int n) {
        return n;
    }

    public int getOutputSize(int n) {
        return n + this.macSize;
    }

    public void reset() {
        Arrays.fill(this.G1, (byte)0);
        Arrays.fill(this.buffer, (byte)0);
        Arrays.fill(this.counter, (byte)0);
        Arrays.fill(this.macBlock, (byte)0);
        this.counter[0] = 1;
        this.data.reset();
        this.associatedText.reset();
        if (this.initialAssociatedText != null) {
            this.processAADBytes(this.initialAssociatedText, 0, this.initialAssociatedText.length);
        }
    }

    private void intToBytes(int n, byte[] byArray, int n2) {
        byArray[n2 + 3] = (byte)(n >> 24);
        byArray[n2 + 2] = (byte)(n >> 16);
        byArray[n2 + 1] = (byte)(n >> 8);
        byArray[n2] = (byte)n;
    }

    private byte getFlag(boolean bl, int n) {
        StringBuffer stringBuffer = new StringBuffer();
        if (bl) {
            stringBuffer.append("1");
        } else {
            stringBuffer.append("0");
        }
        switch (n) {
            case 8: {
                stringBuffer.append("010");
                break;
            }
            case 16: {
                stringBuffer.append("011");
                break;
            }
            case 32: {
                stringBuffer.append("100");
                break;
            }
            case 48: {
                stringBuffer.append("101");
                break;
            }
            case 64: {
                stringBuffer.append("110");
            }
        }
        String string = Integer.toBinaryString(this.Nb_ - 1);
        while (string.length() < 4) {
            string = new StringBuffer(string).insert(0, "0").toString();
        }
        stringBuffer.append(string);
        return (byte)Integer.parseInt(stringBuffer.toString(), 2);
    }

    private class ExposedByteArrayOutputStream
    extends ByteArrayOutputStream {
        public byte[] getBuffer() {
            return this.buf;
        }
    }
}

