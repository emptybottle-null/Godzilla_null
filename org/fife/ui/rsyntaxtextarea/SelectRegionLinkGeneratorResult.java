/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import javax.swing.event.HyperlinkEvent;
import org.fife.ui.rsyntaxtextarea.LinkGeneratorResult;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class SelectRegionLinkGeneratorResult
implements LinkGeneratorResult {
    private RSyntaxTextArea textArea;
    private int sourceOffset;
    private int selStart;
    private int selEnd;

    public SelectRegionLinkGeneratorResult(RSyntaxTextArea textArea, int sourceOffset, int selStart, int selEnd) {
        this.textArea = textArea;
        this.sourceOffset = sourceOffset;
        this.selStart = selStart;
        this.selEnd = selEnd;
    }

    @Override
    public HyperlinkEvent execute() {
        this.textArea.select(this.selStart, this.selEnd);
        return null;
    }

    @Override
    public int getSourceOffset() {
        return this.sourceOffset;
    }
}

