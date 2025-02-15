/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.model;

import com.jediterm.terminal.TerminalColor;
import com.jediterm.terminal.TextStyle;
import com.jediterm.terminal.model.TerminalLine;
import org.jetbrains.annotations.NotNull;

public abstract class TerminalLineIntervalHighlighting {
    private final TerminalLine myLine;
    private final int myStartOffset;
    private final int myEndOffset;
    private final TextStyle myStyle;
    private boolean myDisposed;

    TerminalLineIntervalHighlighting(@NotNull TerminalLine line, int startOffset, int length, @NotNull TextStyle style) {
        if (line == null) {
            TerminalLineIntervalHighlighting.$$$reportNull$$$0(0);
        }
        if (style == null) {
            TerminalLineIntervalHighlighting.$$$reportNull$$$0(1);
        }
        this.myDisposed = false;
        if (startOffset < 0) {
            throw new IllegalArgumentException("Negative startOffset: " + startOffset);
        }
        if (length < 0) {
            throw new IllegalArgumentException("Negative length: " + length);
        }
        this.myLine = line;
        this.myStartOffset = startOffset;
        this.myEndOffset = startOffset + length;
        this.myStyle = style;
    }

    @NotNull
    public TerminalLine getLine() {
        TerminalLine terminalLine = this.myLine;
        if (terminalLine == null) {
            TerminalLineIntervalHighlighting.$$$reportNull$$$0(2);
        }
        return terminalLine;
    }

    public int getStartOffset() {
        return this.myStartOffset;
    }

    public int getEndOffset() {
        return this.myEndOffset;
    }

    public int getLength() {
        return this.myEndOffset - this.myStartOffset;
    }

    public boolean isDisposed() {
        return this.myDisposed;
    }

    public final void dispose() {
        this.doDispose();
        this.myDisposed = true;
    }

    protected abstract void doDispose();

    public boolean intersectsWith(int otherStartOffset, int otherEndOffset) {
        return this.myEndOffset > otherStartOffset && otherEndOffset > this.myStartOffset;
    }

    @NotNull
    public TextStyle mergeWith(@NotNull TextStyle style) {
        TerminalColor background;
        TerminalColor foreground;
        if (style == null) {
            TerminalLineIntervalHighlighting.$$$reportNull$$$0(3);
        }
        if ((foreground = this.myStyle.getForeground()) == null) {
            foreground = style.getForeground();
        }
        if ((background = this.myStyle.getBackground()) == null) {
            background = style.getBackground();
        }
        return new TextStyle(foreground, background);
    }

    public String toString() {
        return "startOffset=" + this.myStartOffset + ", endOffset=" + this.myEndOffset + ", disposed=" + this.myDisposed;
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
            case 2: {
                string = "@NotNull method %s.%s must not return null";
                break;
            }
        }
        switch (n) {
            default: {
                n2 = 3;
                break;
            }
            case 2: {
                n2 = 2;
                break;
            }
        }
        Object[] objectArray3 = new Object[n2];
        switch (n) {
            default: {
                objectArray2 = objectArray3;
                objectArray3[0] = "line";
                break;
            }
            case 1: 
            case 3: {
                objectArray2 = objectArray3;
                objectArray3[0] = "style";
                break;
            }
            case 2: {
                objectArray2 = objectArray3;
                objectArray3[0] = "com/jediterm/terminal/model/TerminalLineIntervalHighlighting";
                break;
            }
        }
        switch (n) {
            default: {
                objectArray = objectArray2;
                objectArray2[1] = "com/jediterm/terminal/model/TerminalLineIntervalHighlighting";
                break;
            }
            case 2: {
                objectArray = objectArray2;
                objectArray2[1] = "getLine";
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
                break;
            }
            case 3: {
                objectArray = objectArray;
                objectArray[2] = "mergeWith";
                break;
            }
        }
        String string2 = String.format(string, objectArray);
        switch (n) {
            default: {
                runtimeException = new IllegalArgumentException(string2);
                break;
            }
            case 2: {
                runtimeException = new IllegalStateException(string2);
                break;
            }
        }
        throw runtimeException;
    }
}

