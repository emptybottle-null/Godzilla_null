/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.parser;

import java.awt.Color;
import org.fife.ui.rsyntaxtextarea.parser.Parser;
import org.fife.ui.rsyntaxtextarea.parser.ParserNotice;

public class DefaultParserNotice
implements ParserNotice {
    private Parser parser;
    private ParserNotice.Level level;
    private int line;
    private int offset;
    private int length;
    private boolean showInEditor;
    private Color color;
    private String message;
    private String toolTipText;
    private static final Color[] DEFAULT_COLORS = new Color[]{new Color(255, 0, 128), new Color(244, 200, 45), Color.gray};

    public DefaultParserNotice(Parser parser, String msg, int line) {
        this(parser, msg, line, -1, -1);
    }

    public DefaultParserNotice(Parser parser, String message, int line, int offset, int length) {
        this.parser = parser;
        this.message = message;
        this.line = line;
        this.offset = offset;
        this.length = length;
        this.setLevel(ParserNotice.Level.ERROR);
        this.setShowInEditor(true);
    }

    @Override
    public int compareTo(ParserNotice other) {
        int diff = -1;
        if (other != null && (diff = this.level.getNumericValue() - other.getLevel().getNumericValue()) == 0 && (diff = this.line - other.getLine()) == 0) {
            diff = this.message.compareTo(other.getMessage());
        }
        return diff;
    }

    @Override
    public boolean containsPosition(int pos) {
        return this.offset <= pos && pos < this.offset + this.length;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ParserNotice)) {
            return false;
        }
        return this.compareTo((ParserNotice)obj) == 0;
    }

    @Override
    public Color getColor() {
        Color c = this.color;
        if (c == null) {
            c = DEFAULT_COLORS[this.getLevel().getNumericValue()];
        }
        return c;
    }

    @Override
    public boolean getKnowsOffsetAndLength() {
        return this.offset >= 0 && this.length >= 0;
    }

    @Override
    public int getLength() {
        return this.length;
    }

    @Override
    public ParserNotice.Level getLevel() {
        return this.level;
    }

    @Override
    public int getLine() {
        return this.line;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public int getOffset() {
        return this.offset;
    }

    @Override
    public Parser getParser() {
        return this.parser;
    }

    @Override
    public boolean getShowInEditor() {
        return this.showInEditor;
    }

    @Override
    public String getToolTipText() {
        return this.toolTipText != null ? this.toolTipText : this.getMessage();
    }

    public int hashCode() {
        return this.line << 16 | this.offset;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setLevel(ParserNotice.Level level) {
        if (level == null) {
            level = ParserNotice.Level.ERROR;
        }
        this.level = level;
    }

    public void setShowInEditor(boolean show) {
        this.showInEditor = show;
    }

    public void setToolTipText(String text) {
        this.toolTipText = text;
    }

    public String toString() {
        return "Line " + this.getLine() + ": " + this.getMessage();
    }
}

