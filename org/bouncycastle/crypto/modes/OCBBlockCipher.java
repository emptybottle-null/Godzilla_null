/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.modes;

import java.util.Vector;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

public class OCBBlockCipher
implements AEADBlockCipher {
    private static final int BLOCK_SIZE = 16;
    private BlockCipher hashCipher;
    private BlockCipher mainCipher;
    private boolean forEncryption;
    private int macSize;
    private byte[] initialAssociatedText;
    private Vector L;
    private byte[] L_Asterisk;
    private byte[] L_Dollar;
    private byte[] KtopInput = null;
    private byte[] Stretch = new byte[24];
    private byte[] OffsetMAIN_0 = new byte[16];
    private byte[] hashBlock;
    private byte[] mainBlock;
    private int hashBlockPos;
    private int mainBlockPos;
    private long hashBlockCount;
    private long mainBlockCount;
    private byte[] OffsetHASH;
    private byte[] Sum;
    private byte[] OffsetMAIN = new byte[16];
    private byte[] Checksum;
    private byte[] macBlock;

    public OCBBlockCipher(BlockCipher blockCipher, BlockCipher blockCipher2) {
        if (blockCipher == null) {
            throw new IllegalArgumentException("'hashCipher' cannot be null");
        }
        if (blockCipher.getBlockSize() != 16) {
            throw new IllegalArgumentException("'hashCipher' must have a block size of 16");
        }
        if (blockCipher2 == null) {
            throw new IllegalArgumentException("'mainCipher' cannot be null");
        }
        if (blockCipher2.getBlockSize() != 16) {
            throw new IllegalArgumentException("'mainCipher' must have a block size of 16");
        }
        if (!blockCipher.getAlgorithmName().equals(blockCipher2.getAlgorithmName())) {
            throw new IllegalArgumentException("'hashCipher' and 'mainCipher' must be the same algorithm");
        }
        this.hashCipher = blockCipher;
        this.mainCipher = blockCipher2;
    }

    public BlockCipher getUnderlyingCipher() {
        return this.mainCipher;
    }

    public String getAlgorithmName() {
        return this.mainCipher.getAlgorithmName() + "/OCB";
    }

    public void init(boolean bl, CipherParameters cipherParameters) throws IllegalArgumentException {
        KeyParameter keyParameter;
        int n;
        byte[] byArray;
        CipherParameters cipherParameters2;
        boolean bl2 = this.forEncryption;
        this.forEncryption = bl;
        this.macBlock = null;
        if (cipherParameters instanceof AEADParameters) {
            cipherParameters2 = (AEADParameters)cipherParameters;
            byArray = ((AEADParameters)cipherParameters2).getNonce();
            this.initialAssociatedText = ((AEADParameters)cipherParameters2).getAssociatedText();
            n = ((AEADParameters)cipherParameters2).getMacSize();
            if (n < 64 || n > 128 || n % 8 != 0) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + n);
            }
            this.macSize = n / 8;
            keyParameter = ((AEADParameters)cipherParameters2).getKey();
        } else if (cipherParameters instanceof ParametersWithIV) {
            cipherParameters2 = (ParametersWithIV)cipherParameters;
            byArray = ((ParametersWithIV)cipherParameters2).getIV();
            this.initialAssociatedText = null;
            this.macSize = 16;
            keyParameter = (KeyParameter)((ParametersWithIV)cipherParameters2).getParameters();
        } else {
            throw new IllegalArgumentException("invalid parameters passed to OCB");
        }
        this.hashBlock = new byte[16];
        this.mainBlock = new byte[bl ? 16 : 16 + this.macSize];
        if (byArray == null) {
            byArray = new byte[]{};
        }
        if (byArray.length > 15) {
            throw new IllegalArgumentException("IV must be no more than 15 bytes");
        }
        if (keyParameter != null) {
            this.hashCipher.init(true, keyParameter);
            this.mainCipher.init(bl, keyParameter);
            this.KtopInput = null;
        } else if (bl2 != bl) {
            throw new IllegalArgumentException("cannot change encrypting state without providing key.");
        }
        this.L_Asterisk = new byte[16];
        this.hashCipher.processBlock(this.L_Asterisk, 0, this.L_Asterisk, 0);
        this.L_Dollar = OCBBlockCipher.OCB_double(this.L_Asterisk);
        this.L = new Vector();
        this.L.addElement(OCBBlockCipher.OCB_double(this.L_Dollar));
        int n2 = this.processNonce(byArray);
        n = n2 % 8;
        int n3 = n2 / 8;
        if (n == 0) {
            System.arraycopy(this.Stretch, n3, this.OffsetMAIN_0, 0, 16);
        } else {
            for (int i = 0; i < 16; ++i) {
                int n4 = this.Stretch[n3] & 0xFF;
                int n5 = this.Stretch[++n3] & 0xFF;
                this.OffsetMAIN_0[i] = (byte)(n4 << n | n5 >>> 8 - n);
            }
        }
        this.hashBlockPos = 0;
        this.mainBlockPos = 0;
        this.hashBlockCount = 0L;
        this.mainBlockCount = 0L;
        this.OffsetHASH = new byte[16];
        this.Sum = new byte[16];
        System.arraycopy(this.OffsetMAIN_0, 0, this.OffsetMAIN, 0, 16);
        this.Checksum = new byte[16];
        if (this.initialAssociatedText != null) {
            this.processAADBytes(this.initialAssociatedText, 0, this.initialAssociatedText.length);
        }
    }

    protected int processNonce(byte[] byArray) {
        byte[] byArray2 = new byte[16];
        System.arraycopy(byArray, 0, byArray2, byArray2.length - byArray.length, byArray.length);
        byArray2[0] = (byte)(this.macSize << 4);
        int n = 15 - byArray.length;
        byArray2[n] = (byte)(byArray2[n] | 1);
        int n2 = byArray2[15] & 0x3F;
        byArray2[15] = (byte)(byArray2[15] & 0xC0);
        if (this.KtopInput == null || !Arrays.areEqual(byArray2, this.KtopInput)) {
            byte[] byArray3 = new byte[16];
            this.KtopInput = byArray2;
            this.hashCipher.processBlock(this.KtopInput, 0, byArray3, 0);
            System.arraycopy(byArray3, 0, this.Stretch, 0, 16);
            for (int i = 0; i < 8; ++i) {
                this.Stretch[16 + i] = (byte)(byArray3[i] ^ byArray3[i + 1]);
            }
        }
        return n2;
    }

    public byte[] getMac() {
        if (this.macBlock == null) {
            return new byte[this.macSize];
        }
        return Arrays.clone(this.macBlock);
    }

    public int getOutputSize(int n) {
        int n2 = n + this.mainBlockPos;
        if (this.forEncryption) {
            return n2 + this.macSize;
        }
        return n2 < this.macSize ? 0 : n2 - this.macSize;
    }

    public int getUpdateOutputSize(int n) {
        int n2 = n + this.mainBlockPos;
        if (!this.forEncryption) {
            if (n2 < this.macSize) {
                return 0;
            }
            n2 -= this.macSize;
        }
        return n2 - n2 % 16;
    }

    public void processAADByte(byte by) {
        this.hashBlock[this.hashBlockPos] = by;
        if (++this.hashBlockPos == this.hashBlock.length) {
            this.processHashBlock();
        }
    }

    public void processAADBytes(byte[] byArray, int n, int n2) {
        for (int i = 0; i < n2; ++i) {
            this.hashBlock[this.hashBlockPos] = byArray[n + i];
            if (++this.hashBlockPos != this.hashBlock.length) continue;
            this.processHashBlock();
        }
    }

    public int processByte(byte by, byte[] byArray, int n) throws DataLengthException {
        this.mainBlock[this.mainBlockPos] = by;
        if (++this.mainBlockPos == this.mainBlock.length) {
            this.processMainBlock(byArray, n);
            return 16;
        }
        return 0;
    }

    public int processBytes(byte[] byArray, int n, int n2, byte[] byArray2, int n3) throws DataLengthException {
        if (byArray.length < n + n2) {
            throw new DataLengthException("Input buffer too short");
        }
        int n4 = 0;
        for (int i = 0; i < n2; ++i) {
            this.mainBlock[this.mainBlockPos] = byArray[n + i];
            if (++this.mainBlockPos != this.mainBlock.length) continue;
            this.processMainBlock(byArray2, n3 + n4);
            n4 += 16;
        }
        return n4;
    }

    public int doFinal(byte[] byArray, int n) throws IllegalStateException, InvalidCipherTextException {
        byte[] byArray2 = null;
        if (!this.forEncryption) {
            if (this.mainBlockPos < this.macSize) {
                throw new InvalidCipherTextException("data too short");
            }
            this.mainBlockPos -= this.macSize;
            byArray2 = new byte[this.macSize];
            System.arraycopy(this.mainBlock, this.mainBlockPos, byArray2, 0, this.macSize);
        }
        if (this.hashBlockPos > 0) {
            OCBBlockCipher.OCB_extend(this.hashBlock, this.hashBlockPos);
            this.updateHASH(this.L_Asterisk);
        }
        if (this.mainBlockPos > 0) {
            if (this.forEncryption) {
                OCBBlockCipher.OCB_extend(this.mainBlock, this.mainBlockPos);
                OCBBlockCipher.xor(this.Checksum, this.mainBlock);
            }
            OCBBlockCipher.xor(this.OffsetMAIN, this.L_Asterisk);
            byte[] byArray3 = new byte[16];
            this.hashCipher.processBlock(this.OffsetMAIN, 0, byArray3, 0);
            OCBBlockCipher.xor(this.mainBlock, byArray3);
            if (byArray.length < n + this.mainBlockPos) {
                throw new OutputLengthException("Output buffer too short");
            }
            System.arraycopy(this.mainBlock, 0, byArray, n, this.mainBlockPos);
            if (!this.forEncryption) {
                OCBBlockCipher.OCB_extend(this.mainBlock, this.mainBlockPos);
                OCBBlockCipher.xor(this.Checksum, this.mainBlock);
            }
        }
        OCBBlockCipher.xor(this.Checksum, this.OffsetMAIN);
        OCBBlockCipher.xor(this.Checksum, this.L_Dollar);
        this.hashCipher.processBlock(this.Checksum, 0, this.Checksum, 0);
        OCBBlockCipher.xor(this.Checksum, this.Sum);
        this.macBlock = new byte[this.macSize];
        System.arraycopy(this.Checksum, 0, this.macBlock, 0, this.macSize);
        int n2 = this.mainBlockPos;
        if (this.forEncryption) {
            if (byArray.length < n + n2 + this.macSize) {
                throw new OutputLengthException("Output buffer too short");
            }
            System.arraycopy(this.macBlock, 0, byArray, n + n2, this.macSize);
            n2 += this.macSize;
        } else if (!Arrays.constantTimeAreEqual(this.macBlock, byArray2)) {
            throw new InvalidCipherTextException("mac check in OCB failed");
        }
        this.reset(false);
        return n2;
    }

    public void reset() {
        this.reset(true);
    }

    protected void clear(byte[] byArray) {
        if (byArray != null) {
            Arrays.fill(byArray, (byte)0);
        }
    }

    protected byte[] getLSub(int n) {
        while (n >= this.L.size()) {
            this.L.addElement(OCBBlockCipher.OCB_double((byte[])this.L.lastElement()));
        }
        return (byte[])this.L.elementAt(n);
    }

    protected void processHashBlock() {
        this.updateHASH(this.getLSub(OCBBlockCipher.OCB_ntz(++this.hashBlockCount)));
        this.hashBlockPos = 0;
    }

    protected void processMainBlock(byte[] byArray, int n) {
        if (byArray.length < n + 16) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (this.forEncryption) {
            OCBBlockCipher.xor(this.Checksum, this.mainBlock);
            this.mainBlockPos = 0;
        }
        OCBBlockCipher.xor(this.OffsetMAIN, this.getLSub(OCBBlockCipher.OCB_ntz(++this.mainBlockCount)));
        OCBBlockCipher.xor(this.mainBlock, this.OffsetMAIN);
        this.mainCipher.processBlock(this.mainBlock, 0, this.mainBlock, 0);
        OCBBlockCipher.xor(this.mainBlock, this.OffsetMAIN);
        System.arraycopy(this.mainBlock, 0, byArray, n, 16);
        if (!this.forEncryption) {
            OCBBlockCipher.xor(this.Checksum, this.mainBlock);
            System.arraycopy(this.mainBlock, 16, this.mainBlock, 0, this.macSize);
            this.mainBlockPos = this.macSize;
        }
    }

    protected void reset(boolean bl) {
        this.hashCipher.reset();
        this.mainCipher.reset();
        this.clear(this.hashBlock);
        this.clear(this.mainBlock);
        this.hashBlockPos = 0;
        this.mainBlockPos = 0;
        this.hashBlockCount = 0L;
        this.mainBlockCount = 0L;
        this.clear(this.OffsetHASH);
        this.clear(this.Sum);
        System.arraycopy(this.OffsetMAIN_0, 0, this.OffsetMAIN, 0, 16);
        this.clear(this.Checksum);
        if (bl) {
            this.macBlock = null;
        }
        if (this.initialAssociatedText != null) {
            this.processAADBytes(this.initialAssociatedText, 0, this.initialAssociatedText.length);
        }
    }

    protected void updateHASH(byte[] byArray) {
        OCBBlockCipher.xor(this.OffsetHASH, byArray);
        OCBBlockCipher.xor(this.hashBlock, this.OffsetHASH);
        this.hashCipher.processBlock(this.hashBlock, 0, this.hashBlock, 0);
        OCBBlockCipher.xor(this.Sum, this.hashBlock);
    }

    protected static byte[] OCB_double(byte[] byArray) {
        byte[] byArray2 = new byte[16];
        int n = OCBBlockCipher.shiftLeft(byArray, byArray2);
        byArray2[15] = (byte)(byArray2[15] ^ 135 >>> (1 - n << 3));
        return byArray2;
    }

    protected static void OCB_extend(byte[] byArray, int n) {
        byArray[n] = -128;
        while (++n < 16) {
            byArray[n] = 0;
        }
    }

    protected static int OCB_ntz(long l) {
        if (l == 0L) {
            return 64;
        }
        int n = 0;
        while ((l & 1L) == 0L) {
            ++n;
            l >>>= 1;
        }
        return n;
    }

    protected static int shiftLeft(byte[] byArray, byte[] byArray2) {
        int n = 16;
        int n2 = 0;
        while (--n >= 0) {
            int n3 = byArray[n] & 0xFF;
            byArray2[n] = (byte)(n3 << 1 | n2);
            n2 = n3 >>> 7 & 1;
        }
        return n2;
    }

    protected static void xor(byte[] byArray, byte[] byArray2) {
        for (int i = 15; i >= 0; --i) {
            int n = i;
            byArray[n] = (byte)(byArray[n] ^ byArray2[i]);
        }
    }
}

