/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.IllegalPathStateException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.util.Internal;

@Internal
class PathGradientPaint
implements Paint {
    private final Color[] colors;
    private final float[] fractions;
    private final int capStyle;
    private final int joinStyle;
    private final int transparency;

    PathGradientPaint(float[] fractions, Color[] colors) {
        this(fractions, colors, 1, 1);
    }

    private PathGradientPaint(float[] fractions, Color[] colors, int capStyle, int joinStyle) {
        this.colors = (Color[])colors.clone();
        this.fractions = (float[])fractions.clone();
        this.capStyle = capStyle;
        this.joinStyle = joinStyle;
        boolean opaque = true;
        for (Color c : colors) {
            if (c == null) continue;
            opaque = opaque && c.getAlpha() == 255;
        }
        this.transparency = opaque ? 1 : 3;
    }

    @Override
    public PaintContext createContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform transform, RenderingHints hints) {
        return new PathGradientContext(cm, deviceBounds, userBounds, transform, hints);
    }

    @Override
    public int getTransparency() {
        return this.transparency;
    }

    class PathGradientContext
    implements PaintContext {
        final Rectangle deviceBounds;
        final Rectangle2D userBounds;
        protected final AffineTransform xform;
        final RenderingHints hints;
        protected final Shape shape;
        final PaintContext pCtx;
        final int gradientSteps;
        WritableRaster raster;

        PathGradientContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform xform, RenderingHints hints) {
            this.shape = (Shape)hints.get(Drawable.GRADIENT_SHAPE);
            if (this.shape == null) {
                throw new IllegalPathStateException("PathGradientPaint needs a shape to be set via the rendering hint Drawable.GRADIANT_SHAPE.");
            }
            this.deviceBounds = deviceBounds;
            this.userBounds = userBounds;
            this.xform = xform;
            this.hints = hints;
            this.gradientSteps = this.getGradientSteps(this.shape);
            Point2D.Double start = new Point2D.Double(0.0, 0.0);
            Point2D.Double end = new Point2D.Double(this.gradientSteps, 0.0);
            LinearGradientPaint gradientPaint = new LinearGradientPaint(start, end, PathGradientPaint.this.fractions, PathGradientPaint.this.colors, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform());
            Rectangle bounds = new Rectangle(0, 0, this.gradientSteps, 1);
            this.pCtx = gradientPaint.createContext(cm, bounds, bounds, new AffineTransform(), hints);
        }

        @Override
        public void dispose() {
        }

        @Override
        public ColorModel getColorModel() {
            return this.pCtx.getColorModel();
        }

        @Override
        public Raster getRaster(int xOffset, int yOffset, int w, int h) {
            ColorModel cm = this.getColorModel();
            if (this.raster == null) {
                this.createRaster();
            }
            WritableRaster childRaster = cm.createCompatibleWritableRaster(w, h);
            Rectangle2D.Double childRect = new Rectangle2D.Double(xOffset, yOffset, w, h);
            if (!childRect.intersects(this.deviceBounds)) {
                return childRaster;
            }
            Rectangle2D.Double destRect = new Rectangle2D.Double();
            Rectangle2D.intersect(childRect, this.deviceBounds, destRect);
            int dx = (int)(((RectangularShape)destRect).getX() - this.deviceBounds.getX());
            int dy = (int)(((RectangularShape)destRect).getY() - this.deviceBounds.getY());
            int dw = (int)((RectangularShape)destRect).getWidth();
            int dh = (int)((RectangularShape)destRect).getHeight();
            Object data = this.raster.getDataElements(dx, dy, dw, dh, null);
            dx = (int)(((RectangularShape)destRect).getX() - ((RectangularShape)childRect).getX());
            dy = (int)(((RectangularShape)destRect).getY() - ((RectangularShape)childRect).getY());
            childRaster.setDataElements(dx, dy, dw, dh, data);
            return childRaster;
        }

        int getGradientSteps(Shape gradientShape) {
            Rectangle rect = gradientShape.getBounds();
            int lower = 1;
            int upper = (int)(Math.max(rect.getWidth(), rect.getHeight()) / 2.0);
            while (lower < upper - 1) {
                int mid = lower + (upper - lower) / 2;
                BasicStroke bs = new BasicStroke(mid, PathGradientPaint.this.capStyle, PathGradientPaint.this.joinStyle);
                Area area = new Area(bs.createStrokedShape(gradientShape));
                if (area.isSingular()) {
                    upper = mid;
                    continue;
                }
                lower = mid;
            }
            return upper;
        }

        void createRaster() {
            ColorModel cm = this.getColorModel();
            this.raster = cm.createCompatibleWritableRaster((int)this.deviceBounds.getWidth(), (int)this.deviceBounds.getHeight());
            BufferedImage img = new BufferedImage(cm, this.raster, false, null);
            Graphics2D graphics = img.createGraphics();
            graphics.setRenderingHints(this.hints);
            graphics.translate(-this.deviceBounds.getX(), -this.deviceBounds.getY());
            graphics.transform(this.xform);
            Raster img2 = this.pCtx.getRaster(0, 0, this.gradientSteps, 1);
            int[] rgb = new int[cm.getNumComponents()];
            for (int i = this.gradientSteps - 1; i >= 0; --i) {
                img2.getPixel(this.gradientSteps - i - 1, 0, rgb);
                Color c = new Color(rgb[0], rgb[1], rgb[2]);
                if (rgb.length == 4) {
                    graphics.setComposite(AlphaComposite.getInstance(2, (float)rgb[3] / 255.0f));
                }
                graphics.setStroke(new BasicStroke(i + 1, PathGradientPaint.this.capStyle, PathGradientPaint.this.joinStyle));
                graphics.setColor(c);
                if (i == this.gradientSteps - 1) {
                    graphics.fill(this.shape);
                }
                graphics.draw(this.shape);
            }
            graphics.dispose();
        }
    }
}

