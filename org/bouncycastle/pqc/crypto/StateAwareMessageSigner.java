/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.MessageSigner;

public interface StateAwareMessageSigner
extends MessageSigner {
    public AsymmetricKeyParameter getUpdatedPrivateKey();
}

