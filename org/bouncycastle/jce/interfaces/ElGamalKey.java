/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.interfaces;

import javax.crypto.interfaces.DHKey;
import org.bouncycastle.jce.spec.ElGamalParameterSpec;

public interface ElGamalKey
extends DHKey {
    public ElGamalParameterSpec getParameters();
}

