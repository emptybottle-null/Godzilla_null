/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.ui;

final class LineCellInterval {
    private final int myLine;
    private final int myStartColumn;
    private final int myEndColumn;

    public LineCellInterval(int line, int startColumn, int endColumn) {
        this.myLine = line;
        this.myStartColumn = startColumn;
        this.myEndColumn = endColumn;
    }

    public int getLine() {
        return this.myLine;
    }

    public int getStartColumn() {
        return this.myStartColumn;
    }

    public int getEndColumn() {
        return this.myEndColumn;
    }

    public int getCellCount() {
        return this.myEndColumn - this.myStartColumn + 1;
    }
}

