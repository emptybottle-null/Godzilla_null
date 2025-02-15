/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.pattern;

import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatternPaintContext
implements PaintContext {
    BufferedImage source;
    Rectangle deviceBounds;
    AffineTransform xform;
    int sourceWidth;
    int sourceHeight;
    BufferedImage buf;

    public PatternPaintContext(BufferedImage source, Rectangle deviceBounds, AffineTransform userXform, AffineTransform distortXform) {
        this.source = source;
        this.deviceBounds = deviceBounds;
        try {
            this.xform = distortXform.createInverse();
            this.xform.concatenate(userXform.createInverse());
        } catch (Exception e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, null, e);
        }
        this.sourceWidth = source.getWidth();
        this.sourceHeight = source.getHeight();
    }

    @Override
    public void dispose() {
    }

    @Override
    public ColorModel getColorModel() {
        return this.source.getColorModel();
    }

    @Override
    public Raster getRaster(int x, int y, int w, int h) {
        if (this.buf == null || this.buf.getWidth() != w || this.buf.getHeight() != h) {
            this.buf = new BufferedImage(w, h, this.source.getType());
        }
        Point2D.Float srcPt = new Point2D.Float();
        Point2D.Float destPt = new Point2D.Float();
        for (int j = 0; j < h; ++j) {
            for (int i = 0; i < w; ++i) {
                int jj;
                destPt.setLocation(i + x, j + y);
                this.xform.transform(destPt, srcPt);
                int ii = (int)srcPt.x % this.sourceWidth;
                if (ii < 0) {
                    ii += this.sourceWidth;
                }
                if ((jj = (int)srcPt.y % this.sourceHeight) < 0) {
                    jj += this.sourceHeight;
                }
                this.buf.setRGB(i, j, this.source.getRGB(ii, jj));
            }
        }
        return this.buf.getData();
    }

    public static void main(String[] argv) {
        int i = -4;
        System.err.println("Hello " + i % 4);
    }
}

