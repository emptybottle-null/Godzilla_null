/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.Digest;

class DigestInputBuffer
extends ByteArrayOutputStream {
    DigestInputBuffer() {
    }

    void updateDigest(Digest digest) {
        digest.update(this.buf, 0, this.count);
    }
}

