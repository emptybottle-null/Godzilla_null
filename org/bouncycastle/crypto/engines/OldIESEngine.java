/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BasicAgreement;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.engines.IESEngine;
import org.bouncycastle.util.Pack;

public class OldIESEngine
extends IESEngine {
    public OldIESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac) {
        super(basicAgreement, derivationFunction, mac);
    }

    public OldIESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac, BufferedBlockCipher bufferedBlockCipher) {
        super(basicAgreement, derivationFunction, mac, bufferedBlockCipher);
    }

    protected byte[] getLengthTag(byte[] byArray) {
        byte[] byArray2 = new byte[4];
        if (byArray != null) {
            Pack.intToBigEndian(byArray.length * 8, byArray2, 0);
        }
        return byArray2;
    }
}

