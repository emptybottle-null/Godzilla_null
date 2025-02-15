/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.GOST28147Engine;
import org.bouncycastle.crypto.engines.GOST28147WrapEngine;
import org.bouncycastle.crypto.modes.GCFBBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.ParametersWithSBox;
import org.bouncycastle.crypto.params.ParametersWithUKM;
import org.bouncycastle.util.Pack;

public class CryptoProWrapEngine
extends GOST28147WrapEngine {
    public void init(boolean bl, CipherParameters cipherParameters) {
        KeyParameter keyParameter;
        CipherParameters cipherParameters2;
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters2 = (ParametersWithRandom)cipherParameters;
            cipherParameters = ((ParametersWithRandom)cipherParameters2).getParameters();
        }
        cipherParameters2 = (ParametersWithUKM)cipherParameters;
        byte[] byArray = null;
        if (((ParametersWithUKM)cipherParameters2).getParameters() instanceof ParametersWithSBox) {
            keyParameter = (KeyParameter)((ParametersWithSBox)((ParametersWithUKM)cipherParameters2).getParameters()).getParameters();
            byArray = ((ParametersWithSBox)((ParametersWithUKM)cipherParameters2).getParameters()).getSBox();
        } else {
            keyParameter = (KeyParameter)((ParametersWithUKM)cipherParameters2).getParameters();
        }
        keyParameter = new KeyParameter(CryptoProWrapEngine.cryptoProDiversify(keyParameter.getKey(), ((ParametersWithUKM)cipherParameters2).getUKM(), byArray));
        if (byArray != null) {
            super.init(bl, new ParametersWithUKM(new ParametersWithSBox(keyParameter, byArray), ((ParametersWithUKM)cipherParameters2).getUKM()));
        } else {
            super.init(bl, new ParametersWithUKM(keyParameter, ((ParametersWithUKM)cipherParameters2).getUKM()));
        }
    }

    private static byte[] cryptoProDiversify(byte[] byArray, byte[] byArray2, byte[] byArray3) {
        for (int i = 0; i != 8; ++i) {
            int n = 0;
            int n2 = 0;
            for (int j = 0; j != 8; ++j) {
                int n3 = Pack.littleEndianToInt(byArray, j * 4);
                if (CryptoProWrapEngine.bitSet(byArray2[i], j)) {
                    n += n3;
                    continue;
                }
                n2 += n3;
            }
            byte[] byArray4 = new byte[8];
            Pack.intToLittleEndian(n, byArray4, 0);
            Pack.intToLittleEndian(n2, byArray4, 4);
            GCFBBlockCipher gCFBBlockCipher = new GCFBBlockCipher(new GOST28147Engine());
            gCFBBlockCipher.init(true, new ParametersWithIV(new ParametersWithSBox(new KeyParameter(byArray), byArray3), byArray4));
            gCFBBlockCipher.processBlock(byArray, 0, byArray, 0);
            gCFBBlockCipher.processBlock(byArray, 8, byArray, 8);
            gCFBBlockCipher.processBlock(byArray, 16, byArray, 16);
            gCFBBlockCipher.processBlock(byArray, 24, byArray, 24);
        }
        return byArray;
    }

    private static boolean bitSet(byte by, int n) {
        return (by & 1 << n) != 0;
    }
}

