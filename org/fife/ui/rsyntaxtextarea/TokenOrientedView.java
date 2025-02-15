/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import org.fife.ui.rsyntaxtextarea.Token;

public interface TokenOrientedView {
    public Token getTokenListForPhysicalLineAbove(int var1);

    public Token getTokenListForPhysicalLineBelow(int var1);
}

