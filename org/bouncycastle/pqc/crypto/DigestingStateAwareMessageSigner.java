/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.DigestingMessageSigner;
import org.bouncycastle.pqc.crypto.StateAwareMessageSigner;

public class DigestingStateAwareMessageSigner
extends DigestingMessageSigner {
    private final StateAwareMessageSigner signer;

    public DigestingStateAwareMessageSigner(StateAwareMessageSigner stateAwareMessageSigner, Digest digest) {
        super(stateAwareMessageSigner, digest);
        this.signer = stateAwareMessageSigner;
    }

    public AsymmetricKeyParameter getUpdatedPrivateKey() {
        return this.signer.getUpdatedPrivateKey();
    }
}

