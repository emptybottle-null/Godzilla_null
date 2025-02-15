/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JPanel;

public class Style
implements Cloneable {
    public static final Color DEFAULT_FOREGROUND = Color.BLACK;
    public static final Color DEFAULT_BACKGROUND = null;
    public static final Font DEFAULT_FONT = null;
    public Color foreground;
    public Color background;
    public boolean underline;
    public Font font;
    public FontMetrics fontMetrics;

    public Style() {
        this(DEFAULT_FOREGROUND);
    }

    public Style(Color fg) {
        this(fg, DEFAULT_BACKGROUND);
    }

    public Style(Color fg, Color bg) {
        this(fg, bg, DEFAULT_FONT);
    }

    public Style(Color fg, Color bg, Font font) {
        this(fg, bg, font, false);
    }

    public Style(Color fg, Color bg, Font font, boolean underline) {
        this.foreground = fg;
        this.background = bg;
        this.font = font;
        this.underline = underline;
        this.fontMetrics = font == null ? null : new JPanel().getFontMetrics(font);
    }

    private boolean areEqual(Object o1, Object o2) {
        return o1 == null && o2 == null || o1 != null && o1.equals(o2);
    }

    public Object clone() {
        Style clone;
        try {
            clone = (Style)super.clone();
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace();
            return null;
        }
        clone.foreground = this.foreground;
        clone.background = this.background;
        clone.font = this.font;
        clone.underline = this.underline;
        clone.fontMetrics = this.fontMetrics;
        return clone;
    }

    public boolean equals(Object o2) {
        if (o2 instanceof Style) {
            Style ss2 = (Style)o2;
            if (this.underline == ss2.underline && this.areEqual(this.foreground, ss2.foreground) && this.areEqual(this.background, ss2.background) && this.areEqual(this.font, ss2.font) && this.areEqual(this.fontMetrics, ss2.fontMetrics)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int n = hashCode = this.underline ? 1 : 0;
        if (this.foreground != null) {
            hashCode ^= this.foreground.hashCode();
        }
        if (this.background != null) {
            hashCode ^= this.background.hashCode();
        }
        return hashCode;
    }

    public String toString() {
        return "[Style: foreground: " + this.foreground + ", background: " + this.background + ", underline: " + this.underline + ", font: " + this.font + "]";
    }
}

