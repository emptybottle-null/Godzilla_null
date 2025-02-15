/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.encodings;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;

public class PKCS1Encoding
implements AsymmetricBlockCipher {
    public static final String STRICT_LENGTH_ENABLED_PROPERTY = "org.bouncycastle.pkcs1.strict";
    public static final String NOT_STRICT_LENGTH_ENABLED_PROPERTY = "org.bouncycastle.pkcs1.not_strict";
    private static final int HEADER_LENGTH = 10;
    private SecureRandom random;
    private AsymmetricBlockCipher engine;
    private boolean forEncryption;
    private boolean forPrivateKey;
    private boolean useStrictLength;
    private int pLen = -1;
    private byte[] fallback = null;
    private byte[] blockBuffer;

    public PKCS1Encoding(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.engine = asymmetricBlockCipher;
        this.useStrictLength = this.useStrict();
    }

    public PKCS1Encoding(AsymmetricBlockCipher asymmetricBlockCipher, int n) {
        this.engine = asymmetricBlockCipher;
        this.useStrictLength = this.useStrict();
        this.pLen = n;
    }

    public PKCS1Encoding(AsymmetricBlockCipher asymmetricBlockCipher, byte[] byArray) {
        this.engine = asymmetricBlockCipher;
        this.useStrictLength = this.useStrict();
        this.fallback = byArray;
        this.pLen = byArray.length;
    }

    private boolean useStrict() {
        String string = (String)AccessController.doPrivileged(new PrivilegedAction(){

            public Object run() {
                return System.getProperty(PKCS1Encoding.STRICT_LENGTH_ENABLED_PROPERTY);
            }
        });
        String string2 = (String)AccessController.doPrivileged(new PrivilegedAction(){

            public Object run() {
                return System.getProperty(PKCS1Encoding.NOT_STRICT_LENGTH_ENABLED_PROPERTY);
            }
        });
        if (string2 != null) {
            return !string2.equals("true");
        }
        return string == null || string.equals("true");
    }

    public AsymmetricBlockCipher getUnderlyingCipher() {
        return this.engine;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        AsymmetricKeyParameter asymmetricKeyParameter;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
            this.random = parametersWithRandom.getRandom();
            asymmetricKeyParameter = (AsymmetricKeyParameter)parametersWithRandom.getParameters();
        } else {
            asymmetricKeyParameter = (AsymmetricKeyParameter)cipherParameters;
            if (!asymmetricKeyParameter.isPrivate() && bl) {
                this.random = new SecureRandom();
            }
        }
        this.engine.init(bl, cipherParameters);
        this.forPrivateKey = asymmetricKeyParameter.isPrivate();
        this.forEncryption = bl;
        this.blockBuffer = new byte[this.engine.getOutputBlockSize()];
        if (this.pLen > 0 && this.fallback == null && this.random == null) {
            throw new IllegalArgumentException("encoder requires random");
        }
    }

    public int getInputBlockSize() {
        int n = this.engine.getInputBlockSize();
        if (this.forEncryption) {
            return n - 10;
        }
        return n;
    }

    public int getOutputBlockSize() {
        int n = this.engine.getOutputBlockSize();
        if (this.forEncryption) {
            return n;
        }
        return n - 10;
    }

    public byte[] processBlock(byte[] byArray, int n, int n2) throws InvalidCipherTextException {
        if (this.forEncryption) {
            return this.encodeBlock(byArray, n, n2);
        }
        return this.decodeBlock(byArray, n, n2);
    }

    private byte[] encodeBlock(byte[] byArray, int n, int n2) throws InvalidCipherTextException {
        if (n2 > this.getInputBlockSize()) {
            throw new IllegalArgumentException("input data too large");
        }
        byte[] byArray2 = new byte[this.engine.getInputBlockSize()];
        if (this.forPrivateKey) {
            byArray2[0] = 1;
            for (int i = 1; i != byArray2.length - n2 - 1; ++i) {
                byArray2[i] = -1;
            }
        } else {
            this.random.nextBytes(byArray2);
            byArray2[0] = 2;
            for (int i = 1; i != byArray2.length - n2 - 1; ++i) {
                while (byArray2[i] == 0) {
                    byArray2[i] = (byte)this.random.nextInt();
                }
            }
        }
        byArray2[byArray2.length - n2 - 1] = 0;
        System.arraycopy(byArray, n, byArray2, byArray2.length - n2, n2);
        return this.engine.processBlock(byArray2, 0, byArray2.length);
    }

    private static int checkPkcs1Encoding(byte[] byArray, int n) {
        int n2 = 0;
        n2 |= byArray[0] ^ 2;
        int n3 = byArray.length - (n + 1);
        for (int i = 1; i < n3; ++i) {
            int n4 = byArray[i];
            n4 |= n4 >> 1;
            n4 |= n4 >> 2;
            n4 |= n4 >> 4;
            n2 |= (n4 & 1) - 1;
        }
        n2 |= byArray[byArray.length - (n + 1)];
        n2 |= n2 >> 1;
        n2 |= n2 >> 2;
        n2 |= n2 >> 4;
        return ~((n2 & 1) - 1);
    }

    private byte[] decodeBlockOrRandom(byte[] byArray, int n, int n2) throws InvalidCipherTextException {
        byte[] byArray2;
        if (!this.forPrivateKey) {
            throw new InvalidCipherTextException("sorry, this method is only for decryption, not for signing");
        }
        byte[] byArray3 = this.engine.processBlock(byArray, n, n2);
        if (this.fallback == null) {
            byArray2 = new byte[this.pLen];
            this.random.nextBytes(byArray2);
        } else {
            byArray2 = this.fallback;
        }
        byte[] byArray4 = this.useStrictLength & byArray3.length != this.engine.getOutputBlockSize() ? this.blockBuffer : byArray3;
        int n3 = PKCS1Encoding.checkPkcs1Encoding(byArray4, this.pLen);
        byte[] byArray5 = new byte[this.pLen];
        for (int i = 0; i < this.pLen; ++i) {
            byArray5[i] = (byte)(byArray4[i + (byArray4.length - this.pLen)] & ~n3 | byArray2[i] & n3);
        }
        Arrays.fill(byArray4, (byte)0);
        return byArray5;
    }

    private byte[] decodeBlock(byte[] byArray, int n, int n2) throws InvalidCipherTextException {
        if (this.pLen != -1) {
            return this.decodeBlockOrRandom(byArray, n, n2);
        }
        byte[] byArray2 = this.engine.processBlock(byArray, n, n2);
        boolean bl = this.useStrictLength & byArray2.length != this.engine.getOutputBlockSize();
        byte[] byArray3 = byArray2.length < this.getOutputBlockSize() ? this.blockBuffer : byArray2;
        byte by = byArray3[0];
        boolean bl2 = this.forPrivateKey ? by != 2 : by != 1;
        int n3 = this.findStart(by, byArray3);
        if (bl2 | ++n3 < 10) {
            Arrays.fill(byArray3, (byte)0);
            throw new InvalidCipherTextException("block incorrect");
        }
        if (bl) {
            Arrays.fill(byArray3, (byte)0);
            throw new InvalidCipherTextException("block incorrect size");
        }
        byte[] byArray4 = new byte[byArray3.length - n3];
        System.arraycopy(byArray3, n3, byArray4, 0, byArray4.length);
        return byArray4;
    }

    private int findStart(byte by, byte[] byArray) throws InvalidCipherTextException {
        int n = -1;
        boolean bl = false;
        for (int i = 1; i != byArray.length; ++i) {
            byte by2 = byArray[i];
            if (by2 == 0 & n < 0) {
                n = i;
            }
            bl |= by == 1 & n < 0 & by2 != -1;
        }
        if (bl) {
            return -1;
        }
        return n;
    }
}

