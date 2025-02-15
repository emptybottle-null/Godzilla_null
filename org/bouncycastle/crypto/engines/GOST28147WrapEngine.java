/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.engines.GOST28147Engine;
import org.bouncycastle.crypto.macs.GOST28147Mac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.ParametersWithSBox;
import org.bouncycastle.crypto.params.ParametersWithUKM;
import org.bouncycastle.util.Arrays;

public class GOST28147WrapEngine
implements Wrapper {
    private GOST28147Engine cipher = new GOST28147Engine();
    private GOST28147Mac mac = new GOST28147Mac();

    public void init(boolean bl, CipherParameters cipherParameters) {
        CipherParameters cipherParameters2;
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters2 = (ParametersWithRandom)cipherParameters;
            cipherParameters = ((ParametersWithRandom)cipherParameters2).getParameters();
        }
        cipherParameters2 = (ParametersWithUKM)cipherParameters;
        this.cipher.init(bl, ((ParametersWithUKM)cipherParameters2).getParameters());
        KeyParameter keyParameter = ((ParametersWithUKM)cipherParameters2).getParameters() instanceof ParametersWithSBox ? (KeyParameter)((ParametersWithSBox)((ParametersWithUKM)cipherParameters2).getParameters()).getParameters() : (KeyParameter)((ParametersWithUKM)cipherParameters2).getParameters();
        this.mac.init(new ParametersWithIV(keyParameter, ((ParametersWithUKM)cipherParameters2).getUKM()));
    }

    public String getAlgorithmName() {
        return "GOST28147Wrap";
    }

    public byte[] wrap(byte[] byArray, int n, int n2) {
        this.mac.update(byArray, n, n2);
        byte[] byArray2 = new byte[n2 + this.mac.getMacSize()];
        this.cipher.processBlock(byArray, n, byArray2, 0);
        this.cipher.processBlock(byArray, n + 8, byArray2, 8);
        this.cipher.processBlock(byArray, n + 16, byArray2, 16);
        this.cipher.processBlock(byArray, n + 24, byArray2, 24);
        this.mac.doFinal(byArray2, n2);
        return byArray2;
    }

    public byte[] unwrap(byte[] byArray, int n, int n2) throws InvalidCipherTextException {
        byte[] byArray2 = new byte[n2 - this.mac.getMacSize()];
        this.cipher.processBlock(byArray, n, byArray2, 0);
        this.cipher.processBlock(byArray, n + 8, byArray2, 8);
        this.cipher.processBlock(byArray, n + 16, byArray2, 16);
        this.cipher.processBlock(byArray, n + 24, byArray2, 24);
        byte[] byArray3 = new byte[this.mac.getMacSize()];
        this.mac.update(byArray2, 0, byArray2.length);
        this.mac.doFinal(byArray3, 0);
        byte[] byArray4 = new byte[this.mac.getMacSize()];
        System.arraycopy(byArray, n + n2 - 4, byArray4, 0, this.mac.getMacSize());
        if (!Arrays.constantTimeAreEqual(byArray3, byArray4)) {
            throw new IllegalStateException("mac mismatch");
        }
        return byArray2;
    }
}

