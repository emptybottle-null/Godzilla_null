/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.model.hyperlinks;

import com.google.common.collect.Lists;
import com.jediterm.terminal.HyperlinkStyle;
import com.jediterm.terminal.TextStyle;
import com.jediterm.terminal.model.CharBuffer;
import com.jediterm.terminal.model.LinesBuffer;
import com.jediterm.terminal.model.TerminalLine;
import com.jediterm.terminal.model.TerminalTextBuffer;
import com.jediterm.terminal.model.hyperlinks.HyperlinkFilter;
import com.jediterm.terminal.model.hyperlinks.LinkResult;
import com.jediterm.terminal.model.hyperlinks.LinkResultItem;
import java.util.List;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class TextProcessing {
    private static final Logger LOG = Logger.getLogger(TextProcessing.class);
    private final List<HyperlinkFilter> myHyperlinkFilter;
    private TextStyle myHyperlinkColor;
    private HyperlinkStyle.HighlightMode myHighlightMode;
    private TerminalTextBuffer myTerminalTextBuffer;

    public TextProcessing(@NotNull TextStyle hyperlinkColor, @NotNull HyperlinkStyle.HighlightMode highlightMode) {
        if (hyperlinkColor == null) {
            TextProcessing.$$$reportNull$$$0(0);
        }
        if (highlightMode == null) {
            TextProcessing.$$$reportNull$$$0(1);
        }
        this.myHyperlinkColor = hyperlinkColor;
        this.myHighlightMode = highlightMode;
        this.myHyperlinkFilter = Lists.newArrayList();
    }

    public void setTerminalTextBuffer(@NotNull TerminalTextBuffer terminalTextBuffer) {
        if (terminalTextBuffer == null) {
            TextProcessing.$$$reportNull$$$0(2);
        }
        this.myTerminalTextBuffer = terminalTextBuffer;
    }

    public void processHyperlinks(@NotNull LinesBuffer buffer, @NotNull TerminalLine updatedLine) {
        if (buffer == null) {
            TextProcessing.$$$reportNull$$$0(3);
        }
        if (updatedLine == null) {
            TextProcessing.$$$reportNull$$$0(4);
        }
        if (this.myHyperlinkFilter.isEmpty()) {
            return;
        }
        this.doProcessHyperlinks(buffer, updatedLine);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void doProcessHyperlinks(@NotNull LinesBuffer buffer, @NotNull TerminalLine updatedLine) {
        if (buffer == null) {
            TextProcessing.$$$reportNull$$$0(5);
        }
        if (updatedLine == null) {
            TextProcessing.$$$reportNull$$$0(6);
        }
        this.myTerminalTextBuffer.lock();
        try {
            int startLineInd;
            int updatedLineInd = TextProcessing.findLineInd(buffer, updatedLine);
            if (updatedLineInd == -1) {
                updatedLineInd = this.findHistoryLineInd(this.myTerminalTextBuffer.getHistoryBuffer(), updatedLine);
                if (updatedLineInd == -1) {
                    LOG.debug("Cannot find line for links processing");
                    return;
                }
                buffer = this.myTerminalTextBuffer.getHistoryBuffer();
            }
            for (startLineInd = updatedLineInd; startLineInd > 0 && buffer.getLine(startLineInd - 1).isWrapped(); --startLineInd) {
            }
            String lineStr = this.joinLines(buffer, startLineInd, updatedLineInd);
            for (HyperlinkFilter filter : this.myHyperlinkFilter) {
                LinkResult result = filter.apply(lineStr);
                if (result == null) continue;
                for (LinkResultItem item : result.getItems()) {
                    HyperlinkStyle style = new HyperlinkStyle(this.myHyperlinkColor.getForeground(), this.myHyperlinkColor.getBackground(), item.getLinkInfo(), this.myHighlightMode, null);
                    if (item.getStartOffset() < 0 || item.getEndOffset() > lineStr.length()) continue;
                    int prevLinesLength = 0;
                    for (int lineInd = startLineInd; lineInd <= updatedLineInd; ++lineInd) {
                        int endLineOffset;
                        int startLineOffset = Math.max(prevLinesLength, item.getStartOffset());
                        if (startLineOffset < (endLineOffset = Math.min(prevLinesLength + this.myTerminalTextBuffer.getWidth(), item.getEndOffset()))) {
                            buffer.getLine(lineInd).writeString(startLineOffset - prevLinesLength, new CharBuffer(lineStr.substring(startLineOffset, endLineOffset)), style);
                        }
                        prevLinesLength += this.myTerminalTextBuffer.getWidth();
                    }
                }
            }
        } finally {
            this.myTerminalTextBuffer.unlock();
        }
    }

    private int findHistoryLineInd(@NotNull LinesBuffer historyBuffer, @NotNull TerminalLine line) {
        if (historyBuffer == null) {
            TextProcessing.$$$reportNull$$$0(7);
        }
        if (line == null) {
            TextProcessing.$$$reportNull$$$0(8);
        }
        int lastLineInd = Math.max(0, historyBuffer.getLineCount() - 200);
        for (int i = historyBuffer.getLineCount() - 1; i >= lastLineInd; --i) {
            if (historyBuffer.getLine(i) != line) continue;
            return i;
        }
        return -1;
    }

    private static int findLineInd(@NotNull LinesBuffer buffer, @NotNull TerminalLine line) {
        if (buffer == null) {
            TextProcessing.$$$reportNull$$$0(9);
        }
        if (line == null) {
            TextProcessing.$$$reportNull$$$0(10);
        }
        for (int i = 0; i < buffer.getLineCount(); ++i) {
            TerminalLine l = buffer.getLine(i);
            if (l != line) continue;
            return i;
        }
        return -1;
    }

    @NotNull
    private String joinLines(@NotNull LinesBuffer buffer, int startLineInd, int updatedLineInd) {
        if (buffer == null) {
            TextProcessing.$$$reportNull$$$0(11);
        }
        StringBuilder result = new StringBuilder();
        for (int i = startLineInd; i <= updatedLineInd; ++i) {
            String text = buffer.getLine(i).getText();
            if (i < updatedLineInd && text.length() < this.myTerminalTextBuffer.getWidth()) {
                text = text + new CharBuffer('\u0000', this.myTerminalTextBuffer.getWidth() - text.length());
            }
            result.append(text);
        }
        String string = result.toString();
        if (string == null) {
            TextProcessing.$$$reportNull$$$0(12);
        }
        return string;
    }

    public void addHyperlinkFilter(@NotNull HyperlinkFilter filter) {
        if (filter == null) {
            TextProcessing.$$$reportNull$$$0(13);
        }
        this.myHyperlinkFilter.add(filter);
    }

    private static /* synthetic */ void $$$reportNull$$$0(int n) {
        RuntimeException runtimeException;
        Object[] objectArray;
        Object[] objectArray2;
        int n2;
        String string;
        switch (n) {
            default: {
                string = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
            }
            case 12: {
                string = "@NotNull method %s.%s must not return null";
                break;
            }
        }
        switch (n) {
            default: {
                n2 = 3;
                break;
            }
            case 12: {
                n2 = 2;
                break;
            }
        }
        Object[] objectArray3 = new Object[n2];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "hyperlinkColor";
                break;
            }
            case 1: {
                objectArray2 = objectArray3;
                objectArray3[0] = "highlightMode";
                break;
            }
            case 2: {
                objectArray2 = objectArray3;
                objectArray3[0] = "terminalTextBuffer";
                break;
            }
            case 3: 
            case 5: 
            case 9: 
            case 11: {
                objectArray2 = objectArray3;
                objectArray3[0] = "buffer";
                break;
            }
            case 4: 
            case 6: {
                objectArray2 = objectArray3;
                objectArray3[0] = "updatedLine";
                break;
            }
            case 7: {
                objectArray2 = objectArray3;
                objectArray3[0] = "historyBuffer";
                break;
            }
            case 8: 
            case 10: {
                objectArray2 = objectArray3;
                objectArray3[0] = "line";
                break;
            }
            case 12: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jediterm/terminal/model/hyperlinks/TextProcessing";
                break;
            }
            case 13: {
                objectArray2 = objectArray3;
                objectArray3[0] = "filter";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jediterm/terminal/model/hyperlinks/TextProcessing";
                break;
            }
            case 12: {
                objectArray = objectArray2;
                objectArray2[1] = "joinLines";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray;
                objectArray[2] = "<init>";
                break;
            }
            case 2: {
                objectArray = objectArray;
                objectArray[2] = "setTerminalTextBuffer";
                break;
            }
            case 3: 
            case 4: {
                objectArray = objectArray;
                objectArray[2] = "processHyperlinks";
                break;
            }
            case 5: 
            case 6: {
                objectArray = objectArray;
                objectArray[2] = "doProcessHyperlinks";
                break;
            }
            case 7: 
            case 8: {
                objectArray = objectArray;
                objectArray[2] = "findHistoryLineInd";
                break;
            }
            case 9: 
            case 10: {
                objectArray = objectArray;
                objectArray[2] = "findLineInd";
                break;
            }
            case 11: {
                objectArray = objectArray;
                objectArray[2] = "joinLines";
                break;
            }
            case 12: {
                break;
            }
            case 13: {
                objectArray = objectArray;
                objectArray[2] = "addHyperlinkFilter";
                break;
            }
        }
        String string2 = String.format(string, objectArray);
        switch (n) {
            default: {
                runtimeException = new IllegalArgumentException(string2);
                break;
            }
            case 12: {
                runtimeException = new IllegalStateException(string2);
                break;
            }
        }
        throw runtimeException;
    }
}

