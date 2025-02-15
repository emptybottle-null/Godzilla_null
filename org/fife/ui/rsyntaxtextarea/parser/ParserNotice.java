/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.parser;

import java.awt.Color;
import org.fife.ui.rsyntaxtextarea.parser.Parser;

public interface ParserNotice
extends Comparable<ParserNotice> {
    public boolean containsPosition(int var1);

    public Color getColor();

    public int getLength();

    public Level getLevel();

    public int getLine();

    public boolean getKnowsOffsetAndLength();

    public String getMessage();

    public int getOffset();

    public Parser getParser();

    public boolean getShowInEditor();

    public String getToolTipText();

    public static enum Level {
        INFO(2),
        WARNING(1),
        ERROR(0);

        private int value;

        private Level(int value) {
            this.value = value;
        }

        public int getNumericValue() {
            return this.value;
        }

        public boolean isEqualToOrWorseThan(Level other) {
            return this.value <= other.getNumericValue();
        }
    }
}

