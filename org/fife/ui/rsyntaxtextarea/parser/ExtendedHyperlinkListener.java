/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.parser;

import java.util.EventListener;
import javax.swing.event.HyperlinkEvent;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public interface ExtendedHyperlinkListener
extends EventListener {
    public void linkClicked(RSyntaxTextArea var1, HyperlinkEvent var2);
}

