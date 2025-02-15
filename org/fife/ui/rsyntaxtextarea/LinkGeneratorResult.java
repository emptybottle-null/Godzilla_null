/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import javax.swing.event.HyperlinkEvent;

public interface LinkGeneratorResult {
    public HyperlinkEvent execute();

    public int getSourceOffset();
}

