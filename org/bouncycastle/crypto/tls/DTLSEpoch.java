/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.tls.DTLSReplayWindow;
import org.bouncycastle.crypto.tls.TlsCipher;

class DTLSEpoch {
    private final DTLSReplayWindow replayWindow = new DTLSReplayWindow();
    private final int epoch;
    private final TlsCipher cipher;
    private long sequenceNumber = 0L;

    DTLSEpoch(int n, TlsCipher tlsCipher) {
        if (n < 0) {
            throw new IllegalArgumentException("'epoch' must be >= 0");
        }
        if (tlsCipher == null) {
            throw new IllegalArgumentException("'cipher' cannot be null");
        }
        this.epoch = n;
        this.cipher = tlsCipher;
    }

    long allocateSequenceNumber() {
        return this.sequenceNumber++;
    }

    TlsCipher getCipher() {
        return this.cipher;
    }

    int getEpoch() {
        return this.epoch;
    }

    DTLSReplayWindow getReplayWindow() {
        return this.replayWindow;
    }

    long getSequenceNumber() {
        return this.sequenceNumber;
    }
}

