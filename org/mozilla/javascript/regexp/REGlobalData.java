/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.regexp;

import org.mozilla.javascript.regexp.REBackTrackData;
import org.mozilla.javascript.regexp.RECompiled;
import org.mozilla.javascript.regexp.REProgState;

class REGlobalData {
    boolean multiline;
    RECompiled regexp;
    int skipped;
    int cp;
    long[] parens;
    REProgState stateStackTop;
    REBackTrackData backTrackStackTop;

    REGlobalData() {
    }

    int parensIndex(int i) {
        return (int)this.parens[i];
    }

    int parensLength(int i) {
        return (int)(this.parens[i] >>> 32);
    }

    void setParens(int i, int index, int length) {
        if (this.backTrackStackTop != null && this.backTrackStackTop.parens == this.parens) {
            this.parens = (long[])this.parens.clone();
        }
        this.parens[i] = (long)index & 0xFFFFFFFFL | (long)length << 32;
    }
}

