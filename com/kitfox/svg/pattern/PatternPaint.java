/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.pattern;

import com.kitfox.svg.pattern.PatternPaintContext;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

public class PatternPaint
implements Paint {
    BufferedImage source;
    AffineTransform xform;

    public PatternPaint(BufferedImage source, AffineTransform xform) {
        this.source = source;
        this.xform = xform;
    }

    @Override
    public PaintContext createContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform xform, RenderingHints hints) {
        return new PatternPaintContext(this.source, deviceBounds, xform, this.xform);
    }

    @Override
    public int getTransparency() {
        return this.source.getColorModel().getTransparency();
    }
}

