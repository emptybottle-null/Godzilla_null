/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.macs.CMac;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.crypto.modes.SICBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

public class EAXBlockCipher
implements AEADBlockCipher {
    private static final byte nTAG = 0;
    private static final byte hTAG = 1;
    private static final byte cTAG = 2;
    private SICBlockCipher cipher;
    private boolean forEncryption;
    private int blockSize;
    private Mac mac;
    private byte[] nonceMac;
    private byte[] associatedTextMac;
    private byte[] macBlock;
    private int macSize;
    private byte[] bufBlock;
    private int bufOff;
    private boolean cipherInitialized;
    private byte[] initialAssociatedText;

    public EAXBlockCipher(BlockCipher blockCipher) {
        this.blockSize = blockCipher.getBlockSize();
        this.mac = new CMac(blockCipher);
        this.macBlock = new byte[this.blockSize];
        this.associatedTextMac = new byte[this.mac.getMacSize()];
        this.nonceMac = new byte[this.mac.getMacSize()];
        this.cipher = new SICBlockCipher(blockCipher);
    }

    public String getAlgorithmName() {
        return this.cipher.getUnderlyingCipher().getAlgorithmName() + "/EAX";
    }

    public BlockCipher getUnderlyingCipher() {
        return this.cipher.getUnderlyingCipher();
    }

    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        CipherParameters cipherParameters2;
        byte[] byArray;
        Object object;
        this.forEncryption = bl;
        if (cipherParameters instanceof AEADParameters) {
            object = (AEADParameters)cipherParameters;
            byArray = ((AEADParameters)object).getNonce();
            this.initialAssociatedText = ((AEADParameters)object).getAssociatedText();
            this.macSize = ((AEADParameters)object).getMacSize() / 8;
            cipherParameters2 = ((AEADParameters)object).getKey();
        } else if (cipherParameters instanceof ParametersWithIV) {
            object = (ParametersWithIV)cipherParameters;
            byArray = ((ParametersWithIV)object).getIV();
            this.initialAssociatedText = null;
            this.macSize = this.mac.getMacSize() / 2;
            cipherParameters2 = ((ParametersWithIV)object).getParameters();
        } else {
            throw new IllegalArgumentException("invalid parameters passed to EAX");
        }
        this.bufBlock = new byte[bl ? this.blockSize : this.blockSize + this.macSize];
        object = new byte[this.blockSize];
        this.mac.init(cipherParameters2);
        object[this.blockSize - 1] = false;
        this.mac.update((byte[])object, 0, this.blockSize);
        this.mac.update(byArray, 0, byArray.length);
        this.mac.doFinal(this.nonceMac, 0);
        this.cipher.init(true, new ParametersWithIV(null, this.nonceMac));
        this.reset();
    }

    private void initCipher() {
        if (this.cipherInitialized) {
            return;
        }
        this.cipherInitialized = true;
        this.mac.doFinal(this.associatedTextMac, 0);
        byte[] byArray = new byte[this.blockSize];
        byArray[this.blockSize - 1] = 2;
        this.mac.update(byArray, 0, this.blockSize);
    }

    private void calculateMac() {
        byte[] byArray = new byte[this.blockSize];
        this.mac.doFinal(byArray, 0);
        for (int i = 0; i < this.macBlock.length; ++i) {
            this.macBlock[i] = (byte)(this.nonceMac[i] ^ this.associatedTextMac[i] ^ byArray[i]);
        }
    }

    public void reset() {
        this.reset(true);
    }

    private void reset(boolean bl) {
        this.cipher.reset();
        this.mac.reset();
        this.bufOff = 0;
        Arrays.fill(this.bufBlock, (byte)0);
        if (bl) {
            Arrays.fill(this.macBlock, (byte)0);
        }
        byte[] byArray = new byte[this.blockSize];
        byArray[this.blockSize - 1] = 1;
        this.mac.update(byArray, 0, this.blockSize);
        this.cipherInitialized = false;
        if (this.initialAssociatedText != null) {
            this.processAADBytes(this.initialAssociatedText, 0, this.initialAssociatedText.length);
        }
    }

    public void processAADByte(byte by) {
        if (this.cipherInitialized) {
            throw new IllegalStateException("AAD data cannot be added after encryption/decryption processing has begun.");
        }
        this.mac.update(by);
    }

    public void processAADBytes(byte[] byArray, int n, int n2) {
        if (this.cipherInitialized) {
            throw new IllegalStateException("AAD data cannot be added after encryption/decryption processing has begun.");
        }
        this.mac.update(byArray, n, n2);
    }

    public int processByte(byte by, byte[] byArray, int n) throws DataLengthException {
        this.initCipher();
        return this.process(by, byArray, n);
    }

    public int processBytes(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws DataLengthException {
        this.initCipher();
        if (byArray.length < n + n2) {
            throw new DataLengthException("Input buffer too short");
        }
        int n4 = 0;
        for (int i = 0; i != n2; ++i) {
            n4 += this.process(byArray[n + i], byArray2, n3 + n4);
        }
        return n4;
    }

    public int doFinal(byte[] byArray, int n) throws IllegalStateException, InvalidCipherTextException {
        this.initCipher();
        int n2 = this.bufOff;
        byte[] byArray2 = new byte[this.bufBlock.length];
        this.bufOff = 0;
        if (this.forEncryption) {
            if (byArray.length < n + n2 + this.macSize) {
                throw new OutputLengthException("Output buffer too short");
            }
            this.cipher.processBlock(this.bufBlock, 0, byArray2, 0);
            System.arraycopy(byArray2, 0, byArray, n, n2);
            this.mac.update(byArray2, 0, n2);
            this.calculateMac();
            System.arraycopy(this.macBlock, 0, byArray, n + n2, this.macSize);
            this.reset(false);
            return n2 + this.macSize;
        }
        if (n2 < this.macSize) {
            throw new InvalidCipherTextException("data too short");
        }
        if (byArray.length < n + n2 - this.macSize) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (n2 > this.macSize) {
            this.mac.update(this.bufBlock, 0, n2 - this.macSize);
            this.cipher.processBlock(this.bufBlock, 0, byArray2, 0);
            System.arraycopy(byArray2, 0, byArray, n, n2 - this.macSize);
        }
        this.calculateMac();
        if (!this.verifyMac(this.bufBlock, n2 - this.macSize)) {
            throw new InvalidCipherTextException("mac check in EAX failed");
        }
        this.reset(false);
        return n2 - this.macSize;
    }

    public byte[] getMac() {
        byte[] byArray = new byte[this.macSize];
        System.arraycopy(this.macBlock, 0, byArray, 0, this.macSize);
        return byArray;
    }

    public int getUpdateOutputSize(int n) {
        int n2 = n + this.bufOff;
        if (!this.forEncryption) {
            if (n2 < this.macSize) {
                return 0;
            }
            n2 -= this.macSize;
        }
        return n2 - n2 % this.blockSize;
    }

    public int getOutputSize(int n) {
        int n2 = n + this.bufOff;
        if (this.forEncryption) {
            return n2 + this.macSize;
        }
        return n2 < this.macSize ? 0 : n2 - this.macSize;
    }

    private int process(byte by, byte[] byArray, int n) {
        this.bufBlock[this.bufOff++] = by;
        if (this.bufOff == this.bufBlock.length) {
            int n2;
            if (byArray.length < n + this.blockSize) {
                throw new OutputLengthException("Output buffer is too short");
            }
            if (this.forEncryption) {
                n2 = this.cipher.processBlock(this.bufBlock, 0, byArray, n);
                this.mac.update(byArray, n, this.blockSize);
            } else {
                this.mac.update(this.bufBlock, 0, this.blockSize);
                n2 = this.cipher.processBlock(this.bufBlock, 0, byArray, n);
            }
            this.bufOff = 0;
            if (!this.forEncryption) {
                System.arraycopy(this.bufBlock, this.blockSize, this.bufBlock, 0, this.macSize);
                this.bufOff = this.macSize;
            }
            return n2;
        }
        return 0;
    }

    private boolean verifyMac(byte[] byArray, int n) {
        int n2 = 0;
        for (int i = 0; i < this.macSize; ++i) {
            n2 |= this.macBlock[i] ^ byArray[n + i];
        }
        return n2 == 0;
    }
}

