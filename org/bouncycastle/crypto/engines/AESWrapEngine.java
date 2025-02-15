/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.engines.RFC3394WrapEngine;

public class AESWrapEngine
extends RFC3394WrapEngine {
    public AESWrapEngine() {
        super(new AESEngine());
    }

    public AESWrapEngine(boolean bl) {
        super(new AESEngine(), bl);
    }
}

