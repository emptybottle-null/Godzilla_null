/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.Signer;

class SignerInputBuffer
extends ByteArrayOutputStream {
    SignerInputBuffer() {
    }

    void updateSigner(Signer signer) {
        signer.update(this.buf, 0, this.count);
    }
}

