/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.sl.usermodel.VerticalAlignment;

public interface TextShape<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>>
extends SimpleShape<S, P>,
Iterable<P> {
    public String getText();

    public TextRun setText(String var1);

    public TextRun appendText(String var1, boolean var2);

    public List<P> getTextParagraphs();

    public Insets2D getInsets();

    public void setInsets(Insets2D var1);

    public double getTextHeight();

    public double getTextHeight(Graphics2D var1);

    public VerticalAlignment getVerticalAlignment();

    public void setVerticalAlignment(VerticalAlignment var1);

    public boolean isHorizontalCentered();

    public void setHorizontalCentered(Boolean var1);

    public boolean getWordWrap();

    public void setWordWrap(boolean var1);

    public TextDirection getTextDirection();

    public void setTextDirection(TextDirection var1);

    public Double getTextRotation();

    public void setTextRotation(Double var1);

    public void setTextPlaceholder(TextPlaceholder var1);

    public TextPlaceholder getTextPlaceholder();

    public Rectangle2D resizeToFitText();

    public Rectangle2D resizeToFitText(Graphics2D var1);

    public static enum TextPlaceholder {
        TITLE(0),
        BODY(1),
        CENTER_TITLE(6),
        CENTER_BODY(5),
        HALF_BODY(7),
        QUARTER_BODY(8),
        NOTES(2),
        OTHER(4);

        public final int nativeId;

        private TextPlaceholder(int nativeId) {
            this.nativeId = nativeId;
        }

        public static TextPlaceholder fromNativeId(int nativeId) {
            for (TextPlaceholder ld : TextPlaceholder.values()) {
                if (ld.nativeId != nativeId) continue;
                return ld;
            }
            return null;
        }

        public static boolean isTitle(int nativeId) {
            return nativeId == TextPlaceholder.TITLE.nativeId || nativeId == TextPlaceholder.CENTER_TITLE.nativeId;
        }
    }

    public static enum TextAutofit {
        NONE,
        NORMAL,
        SHAPE;

    }

    public static enum TextDirection {
        HORIZONTAL,
        VERTICAL,
        VERTICAL_270,
        STACKED;

    }
}

