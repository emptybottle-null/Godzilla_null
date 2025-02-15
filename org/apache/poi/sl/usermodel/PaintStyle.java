/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.io.InputStream;
import java.util.List;
import org.apache.poi.sl.usermodel.ColorStyle;
import org.apache.poi.sl.usermodel.Insets2D;

public interface PaintStyle {

    public static interface TexturePaint
    extends PaintStyle {
        public InputStream getImageData();

        public String getContentType();

        public int getAlpha();

        default public boolean isRotatedWithShape() {
            return true;
        }

        default public Dimension2D getScale() {
            return null;
        }

        default public Point2D getOffset() {
            return null;
        }

        default public FlipMode getFlipMode() {
            return FlipMode.NONE;
        }

        default public TextureAlignment getAlignment() {
            return null;
        }

        default public Insets2D getInsets() {
            return null;
        }

        default public Insets2D getStretch() {
            return null;
        }

        default public List<ColorStyle> getDuoTone() {
            return null;
        }
    }

    public static interface GradientPaint
    extends PaintStyle {
        public double getGradientAngle();

        public ColorStyle[] getGradientColors();

        public float[] getGradientFractions();

        public boolean isRotatedWithShape();

        public GradientType getGradientType();

        default public Insets2D getFillToInsets() {
            return null;
        }

        public static enum GradientType {
            linear,
            circular,
            rectangular,
            shape;

        }
    }

    public static interface SolidPaint
    extends PaintStyle {
        public ColorStyle getSolidColor();
    }

    public static enum TextureAlignment {
        BOTTOM("b"),
        BOTTOM_LEFT("bl"),
        BOTTOM_RIGHT("br"),
        CENTER("ctr"),
        LEFT("l"),
        RIGHT("r"),
        TOP("t"),
        TOP_LEFT("tl"),
        TOP_RIGHT("tr");

        private final String ooxmlId;

        private TextureAlignment(String ooxmlId) {
            this.ooxmlId = ooxmlId;
        }

        public static TextureAlignment fromOoxmlId(String ooxmlId) {
            for (TextureAlignment ta : TextureAlignment.values()) {
                if (!ta.ooxmlId.equals(ooxmlId)) continue;
                return ta;
            }
            return null;
        }
    }

    public static enum FlipMode {
        NONE,
        X,
        Y,
        XY;

    }

    public static enum PaintModifier {
        NONE,
        NORM,
        LIGHTEN,
        LIGHTEN_LESS,
        DARKEN,
        DARKEN_LESS;

    }
}

