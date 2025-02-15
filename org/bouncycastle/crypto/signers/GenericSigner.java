/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.signers;

import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;

public class GenericSigner
implements Signer {
    private final AsymmetricBlockCipher engine;
    private final Digest digest;
    private boolean forSigning;

    public GenericSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest) {
        this.engine = asymmetricBlockCipher;
        this.digest = digest;
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        this.forSigning = bl;
        AsymmetricKeyParameter asymmetricKeyParameter = cipherParameters instanceof ParametersWithRandom ? (AsymmetricKeyParameter)((ParametersWithRandom)cipherParameters).getParameters() : (AsymmetricKeyParameter)cipherParameters;
        if (bl && !asymmetricKeyParameter.isPrivate()) {
            throw new IllegalArgumentException("signing requires private key");
        }
        if (!bl && asymmetricKeyParameter.isPrivate()) {
            throw new IllegalArgumentException("verification requires public key");
        }
        this.reset();
        this.engine.init(bl, cipherParameters);
    }

    public void update(byte by) {
        this.digest.update(by);
    }

    public void update(byte[] byArray, int n, int n2) {
        this.digest.update(byArray, n, n2);
    }

    public byte[] generateSignature() throws CryptoException, DataLengthException {
        if (!this.forSigning) {
            throw new IllegalStateException("GenericSigner not initialised for signature generation.");
        }
        byte[] byArray = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(byArray, 0);
        return this.engine.processBlock(byArray, 0, byArray.length);
    }

    public boolean verifySignature(byte[] byArray) {
        if (this.forSigning) {
            throw new IllegalStateException("GenericSigner not initialised for verification");
        }
        byte[] byArray2 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(byArray2, 0);
        try {
            byte[] byArray3 = this.engine.processBlock(byArray, 0, byArray.length);
            if (byArray3.length < byArray2.length) {
                byte[] byArray4 = new byte[byArray2.length];
                System.arraycopy(byArray3, 0, byArray4, byArray4.length - byArray3.length, byArray3.length);
                byArray3 = byArray4;
            }
            return Arrays.constantTimeAreEqual(byArray3, byArray2);
        } catch (Exception exception) {
            return false;
        }
    }

    public void reset() {
        this.digest.reset();
    }
}

