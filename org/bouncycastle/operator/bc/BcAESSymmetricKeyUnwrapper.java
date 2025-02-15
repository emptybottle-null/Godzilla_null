/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.operator.bc;

import org.bouncycastle.crypto.engines.AESWrapEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.operator.bc.AESUtil;
import org.bouncycastle.operator.bc.BcSymmetricKeyUnwrapper;

public class BcAESSymmetricKeyUnwrapper
extends BcSymmetricKeyUnwrapper {
    public BcAESSymmetricKeyUnwrapper(KeyParameter keyParameter) {
        super(AESUtil.determineKeyEncAlg(keyParameter), new AESWrapEngine(), keyParameter);
    }
}

