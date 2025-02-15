/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.regexp;

import org.mozilla.javascript.regexp.REGlobalData;
import org.mozilla.javascript.regexp.REProgState;

class REBackTrackData {
    final REBackTrackData previous;
    final int op;
    final int pc;
    final int cp;
    final int continuationOp;
    final int continuationPc;
    final long[] parens;
    final REProgState stateStackTop;

    REBackTrackData(REGlobalData gData, int op, int pc, int cp, int continuationOp, int continuationPc) {
        this.previous = gData.backTrackStackTop;
        this.op = op;
        this.pc = pc;
        this.cp = cp;
        this.continuationOp = continuationOp;
        this.continuationPc = continuationPc;
        this.parens = gData.parens;
        this.stateStackTop = gData.stateStackTop;
    }
}

