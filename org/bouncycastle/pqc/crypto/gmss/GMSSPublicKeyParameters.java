/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.gmss;

import org.bouncycastle.pqc.crypto.gmss.GMSSKeyParameters;
import org.bouncycastle.pqc.crypto.gmss.GMSSParameters;

public class GMSSPublicKeyParameters
extends GMSSKeyParameters {
    private byte[] gmssPublicKey;

    public GMSSPublicKeyParameters(byte[] byArray, GMSSParameters gMSSParameters) {
        super(false, gMSSParameters);
        this.gmssPublicKey = byArray;
    }

    public byte[] getPublicKey() {
        return this.gmssPublicKey;
    }
}

