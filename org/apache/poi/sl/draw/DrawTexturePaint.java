/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.util.Dimension2DDouble;

class DrawTexturePaint
extends TexturePaint {
    private final PaintStyle.TexturePaint fill;
    private final Shape shape;
    private final double flipX;
    private final double flipY;
    private final boolean isBitmapSrc;
    private static final Insets2D INSETS_EMPTY = new Insets2D(0.0, 0.0, 0.0, 0.0);

    DrawTexturePaint(BufferedImage txtr, Shape shape, PaintStyle.TexturePaint fill, double flipX, double flipY, boolean isBitmapSrc) {
        super(txtr, new Rectangle2D.Double(0.0, 0.0, txtr.getWidth(), txtr.getHeight()));
        this.fill = fill;
        this.shape = shape;
        this.flipX = flipX;
        this.flipY = flipY;
        this.isBitmapSrc = isBitmapSrc;
    }

    @Override
    public PaintContext createContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform xform, RenderingHints hints) {
        Rectangle2D usedBounds;
        Dimension2DDouble userDim = new Dimension2DDouble();
        if (this.fill.isRotatedWithShape() || this.shape == null) {
            usedBounds = userBounds;
        } else {
            AffineTransform transform = new AffineTransform(xform);
            transform.preConcatenate(AffineTransform.getTranslateInstance(-transform.getTranslateX(), -transform.getTranslateY()));
            Point2D p1 = new Point2D.Double(1.0, 0.0);
            p1 = transform.transform(p1, p1);
            double rad = Math.atan2(p1.getY(), p1.getX());
            if (rad != 0.0) {
                xform.rotate(-rad, userBounds.getCenterX(), userBounds.getCenterY());
            }
            transform = AffineTransform.getRotateInstance(rad, userBounds.getCenterX(), userBounds.getCenterY());
            usedBounds = transform.createTransformedShape(this.shape).getBounds2D();
        }
        ((Dimension2D)userDim).setSize(usedBounds.getWidth(), usedBounds.getHeight());
        xform.translate(usedBounds.getX(), usedBounds.getY());
        BufferedImage bi = this.getImage(usedBounds);
        if (this.fill.getStretch() != null) {
            TexturePaint tp = new TexturePaint(bi, new Rectangle2D.Double(0.0, 0.0, bi.getWidth(), bi.getHeight()));
            return tp.createContext(cm, deviceBounds, usedBounds, xform, hints);
        }
        if (this.fill.getScale() != null) {
            AffineTransform newXform = this.getTiledInstance(usedBounds, (AffineTransform)xform.clone());
            TexturePaint tp = new TexturePaint(bi, new Rectangle2D.Double(0.0, 0.0, bi.getWidth(), bi.getHeight()));
            return tp.createContext(cm, deviceBounds, userBounds, newXform, hints);
        }
        return super.createContext(cm, deviceBounds, userBounds, xform, hints);
    }

    public BufferedImage getImage(Rectangle2D userBounds) {
        BufferedImage bi = super.getImage();
        Insets2D insets = this.fill.getInsets();
        Insets2D stretch = this.fill.getStretch();
        if ((insets == null || INSETS_EMPTY.equals(insets)) && stretch == null) {
            return bi;
        }
        if (insets != null && !INSETS_EMPTY.equals(insets)) {
            int width = bi.getWidth();
            int height = bi.getHeight();
            bi = bi.getSubimage((int)(Math.max(insets.left, 0.0) / 100000.0 * (double)width), (int)(Math.max(insets.top, 0.0) / 100000.0 * (double)height), (int)((100000.0 - Math.max(insets.left, 0.0) - Math.max(insets.right, 0.0)) / 100000.0 * (double)width), (int)((100000.0 - Math.max(insets.top, 0.0) - Math.max(insets.bottom, 0.0)) / 100000.0 * (double)height));
            int addTop = (int)(Math.max(-insets.top, 0.0) / 100000.0 * (double)height);
            int addLeft = (int)(Math.max(-insets.left, 0.0) / 100000.0 * (double)width);
            int addBottom = (int)(Math.max(-insets.bottom, 0.0) / 100000.0 * (double)height);
            int addRight = (int)(Math.max(-insets.right, 0.0) / 100000.0 * (double)width);
            if (addTop > 0 || addLeft > 0 || addBottom > 0 || addRight > 0) {
                int[] buf = new int[bi.getWidth() * bi.getHeight()];
                bi.getRGB(0, 0, bi.getWidth(), bi.getHeight(), buf, 0, bi.getWidth());
                BufferedImage borderBi = new BufferedImage(bi.getWidth() + addLeft + addRight, bi.getHeight() + addTop + addBottom, bi.getType());
                borderBi.setRGB(addLeft, addTop, bi.getWidth(), bi.getHeight(), buf, 0, bi.getWidth());
                bi = borderBi;
            }
        }
        if (stretch != null) {
            Rectangle2D.Double srcBounds = new Rectangle2D.Double(0.0, 0.0, bi.getWidth(), bi.getHeight());
            Rectangle2D.Double dstBounds = new Rectangle2D.Double(stretch.left / 100000.0 * userBounds.getWidth(), stretch.top / 100000.0 * userBounds.getHeight(), (100000.0 - stretch.left - stretch.right) / 100000.0 * userBounds.getWidth(), (100000.0 - stretch.top - stretch.bottom) / 100000.0 * userBounds.getHeight());
            BufferedImage stretchBi = new BufferedImage((int)userBounds.getWidth(), (int)userBounds.getHeight(), 2);
            Graphics2D g = stretchBi.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g.setComposite(AlphaComposite.Clear);
            g.fillRect(0, 0, stretchBi.getWidth(), stretchBi.getHeight());
            g.setComposite(AlphaComposite.SrcOver);
            AffineTransform at = new AffineTransform();
            at.translate(dstBounds.getCenterX(), dstBounds.getCenterY());
            at.scale(((RectangularShape)dstBounds).getWidth() / ((RectangularShape)srcBounds).getWidth(), ((RectangularShape)dstBounds).getHeight() / ((RectangularShape)srcBounds).getHeight());
            at.translate(-srcBounds.getCenterX(), -srcBounds.getCenterY());
            g.drawRenderedImage(bi, at);
            g.dispose();
            bi = stretchBi;
        }
        return bi;
    }

    private AffineTransform getTiledInstance(Rectangle2D usedBounds, AffineTransform xform) {
        double alg_y;
        double alg_x;
        BufferedImage bi = this.getImage();
        Dimension2D scale = this.fill.getScale();
        assert (scale != null);
        double img_w = (double)bi.getWidth() * (scale.getWidth() == 0.0 ? 1.0 : scale.getWidth()) / this.flipX;
        double img_h = (double)bi.getHeight() * (scale.getHeight() == 0.0 ? 1.0 : scale.getHeight()) / this.flipY;
        PaintStyle.TextureAlignment ta = this.fill.getAlignment();
        double usr_w = usedBounds.getWidth();
        double usr_h = usedBounds.getHeight();
        switch (ta == null ? PaintStyle.TextureAlignment.TOP_LEFT : ta) {
            case BOTTOM: {
                alg_x = (usr_w - img_w) / 2.0;
                alg_y = usr_h - img_h;
                break;
            }
            case BOTTOM_LEFT: {
                alg_x = 0.0;
                alg_y = usr_h - img_h;
                break;
            }
            case BOTTOM_RIGHT: {
                alg_x = usr_w - img_w;
                alg_y = usr_h - img_h;
                break;
            }
            case CENTER: {
                alg_x = (usr_w - img_w) / 2.0;
                alg_y = (usr_h - img_h) / 2.0;
                break;
            }
            case LEFT: {
                alg_x = 0.0;
                alg_y = (usr_h - img_h) / 2.0;
                break;
            }
            case RIGHT: {
                alg_x = usr_w - img_w;
                alg_y = (usr_h - img_h) / 2.0;
                break;
            }
            case TOP: {
                alg_x = (usr_w - img_w) / 2.0;
                alg_y = 0.0;
                break;
            }
            default: {
                alg_x = 0.0;
                alg_y = 0.0;
                break;
            }
            case TOP_RIGHT: {
                alg_x = usr_w - img_w;
                alg_y = 0.0;
            }
        }
        xform.translate(alg_x, alg_y);
        Point2D offset = this.fill.getOffset();
        if (offset != null) {
            xform.translate(offset.getX(), offset.getY());
        }
        xform.scale(scale.getWidth() / (this.isBitmapSrc ? this.flipX : 1.0), scale.getHeight() / (this.isBitmapSrc ? this.flipY : 1.0));
        return xform;
    }
}

