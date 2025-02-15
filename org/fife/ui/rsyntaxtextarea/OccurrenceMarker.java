/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaHighlighter;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rtextarea.SmartHighlightPainter;

public interface OccurrenceMarker {
    public Token getTokenToMark(RSyntaxTextArea var1);

    public boolean isValidType(RSyntaxTextArea var1, Token var2);

    public void markOccurrences(RSyntaxDocument var1, Token var2, RSyntaxTextAreaHighlighter var3, SmartHighlightPainter var4);
}

