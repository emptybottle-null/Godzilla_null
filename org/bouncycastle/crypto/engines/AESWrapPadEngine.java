/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.engines.RFC5649WrapEngine;

public class AESWrapPadEngine
extends RFC5649WrapEngine {
    public AESWrapPadEngine() {
        super(new AESEngine());
    }
}

