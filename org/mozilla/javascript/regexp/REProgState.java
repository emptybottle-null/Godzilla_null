/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.regexp;

import org.mozilla.javascript.regexp.REBackTrackData;

class REProgState {
    final REProgState previous;
    final int min;
    final int max;
    final int index;
    final int continuationOp;
    final int continuationPc;
    final REBackTrackData backTrack;

    REProgState(REProgState previous, int min, int max, int index, REBackTrackData backTrack, int continuationOp, int continuationPc) {
        this.previous = previous;
        this.min = min;
        this.max = max;
        this.index = index;
        this.continuationOp = continuationOp;
        this.continuationPc = continuationPc;
        this.backTrack = backTrack;
    }
}

