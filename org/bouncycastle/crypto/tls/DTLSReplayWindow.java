/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

class DTLSReplayWindow {
    private static final long VALID_SEQ_MASK = 0xFFFFFFFFFFFFL;
    private static final long WINDOW_SIZE = 64L;
    private long latestConfirmedSeq = -1L;
    private long bitmap = 0L;

    DTLSReplayWindow() {
    }

    boolean shouldDiscard(long l) {
        if ((l & 0xFFFFFFFFFFFFL) != l) {
            return true;
        }
        if (l <= this.latestConfirmedSeq) {
            long l2 = this.latestConfirmedSeq - l;
            if (l2 >= 64L) {
                return true;
            }
            if ((this.bitmap & 1L << (int)l2) != 0L) {
                return true;
            }
        }
        return false;
    }

    void reportAuthenticated(long l) {
        if ((l & 0xFFFFFFFFFFFFL) != l) {
            throw new IllegalArgumentException("'seq' out of range");
        }
        if (l <= this.latestConfirmedSeq) {
            long l2 = this.latestConfirmedSeq - l;
            if (l2 < 64L) {
                this.bitmap |= 1L << (int)l2;
            }
        } else {
            long l3 = l - this.latestConfirmedSeq;
            if (l3 >= 64L) {
                this.bitmap = 1L;
            } else {
                this.bitmap <<= (int)l3;
                this.bitmap |= 1L;
            }
            this.latestConfirmedSeq = l;
        }
    }

    void reset() {
        this.latestConfirmedSeq = -1L;
        this.bitmap = 0L;
    }
}

