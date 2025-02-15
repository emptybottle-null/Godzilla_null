/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.regexp;

import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;

final class GlobData {
    int mode;
    boolean global;
    String str;
    Scriptable arrayobj;
    Function lambda;
    String repstr;
    int dollar = -1;
    StringBuilder charBuf;
    int leftIndex;

    GlobData() {
    }
}

