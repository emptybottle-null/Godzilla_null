/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import org.apache.poi.sl.draw.BitmapImageRenderer;
import org.apache.poi.sl.draw.DrawSimpleShape;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.draw.ImageRenderer;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.PictureShape;
import org.apache.poi.sl.usermodel.RectAlign;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public class DrawPictureShape
extends DrawSimpleShape {
    private static final POILogger LOG = POILogFactory.getLogger(DrawPictureShape.class);
    private static final String[] KNOWN_RENDERER = new String[]{"org.apache.poi.hwmf.draw.HwmfImageRenderer", "org.apache.poi.hemf.draw.HemfImageRenderer", "org.apache.poi.xslf.draw.SVGImageRenderer"};

    public DrawPictureShape(PictureShape<?, ?> shape) {
        super((SimpleShape<?, ?>)shape);
    }

    @Override
    public void drawContent(Graphics2D graphics) {
        PictureData[] pics;
        SimpleShape ps = this.getShape();
        Rectangle2D anchor = DrawPictureShape.getAnchor(graphics, ps);
        Insets insets = ps.getClipping();
        for (PictureData data : pics = new PictureData[]{ps.getAlternativePictureData(), ps.getPictureData()}) {
            if (data == null) continue;
            try {
                String ct = data.getContentType();
                ImageRenderer renderer = DrawPictureShape.getImageRenderer(graphics, ct);
                if (!renderer.canRender(ct)) continue;
                renderer.loadImage(data.getData(), ct);
                renderer.drawImage(graphics, anchor, insets);
                return;
            } catch (IOException e) {
                LOG.log(7, "image can't be loaded/rendered.", e);
            }
        }
    }

    public static ImageRenderer getImageRenderer(Graphics2D graphics, String contentType) {
        ImageRenderer renderer;
        ImageRenderer imageRenderer = renderer = graphics != null ? (ImageRenderer)graphics.getRenderingHint(Drawable.IMAGE_RENDERER) : null;
        if (renderer != null && renderer.canRender(contentType)) {
            return renderer;
        }
        BitmapImageRenderer bir = new BitmapImageRenderer();
        if (bir.canRender(contentType)) {
            return bir;
        }
        ClassLoader cl = ImageRenderer.class.getClassLoader();
        for (String kr : KNOWN_RENDERER) {
            ImageRenderer ir;
            try {
                ir = (ImageRenderer)cl.loadClass(kr).newInstance();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                LOG.log(3, "Known image renderer '" + kr + " not found/loaded - include poi-scratchpad jar!", e);
                continue;
            }
            if (!ir.canRender(contentType)) continue;
            return ir;
        }
        LOG.log(5, "No suiteable image renderer found for content-type '" + contentType + "' - include poi-scratchpad jar!");
        return bir;
    }

    @Override
    protected Paint getFillPaint(Graphics2D graphics) {
        return null;
    }

    @Override
    protected PictureShape<?, ?> getShape() {
        return (PictureShape)this.shape;
    }

    public void resize() {
        SimpleShape ps = this.getShape();
        Dimension dim = ps.getPictureData().getImageDimension();
        Rectangle2D origRect = ps.getAnchor();
        double x = origRect.getX();
        double y = origRect.getY();
        double w = dim.getWidth();
        double h = dim.getHeight();
        ps.setAnchor(new Rectangle2D.Double(x, y, w, h));
    }

    public void resize(Rectangle2D target) {
        this.resize(target, RectAlign.CENTER);
    }

    public void resize(Rectangle2D target, RectAlign align) {
        SimpleShape ps = this.getShape();
        Dimension dim = ps.getPictureData().getImageDimension();
        if (dim.width <= 0 || dim.height <= 0) {
            ps.setAnchor(target);
            return;
        }
        double w = target.getWidth();
        double h = target.getHeight();
        double sx = w / (double)dim.width;
        double sy = h / (double)dim.height;
        double dx = 0.0;
        double dy = 0.0;
        if (sx > sy) {
            w = sy * (double)dim.width;
            dx = target.getWidth() - w;
        } else if (sy > sx) {
            h = sx * (double)dim.height;
            dy = target.getHeight() - h;
        } else {
            ps.setAnchor(target);
            return;
        }
        double x = target.getX();
        double y = target.getY();
        switch (align) {
            case TOP: {
                x += dx / 2.0;
                break;
            }
            case TOP_RIGHT: {
                x += dx;
                break;
            }
            case RIGHT: {
                x += dx;
                y += dy / 2.0;
                break;
            }
            case BOTTOM_RIGHT: {
                x += dx;
                y += dy;
                break;
            }
            case BOTTOM: {
                x += dx / 2.0;
                y += dy;
                break;
            }
            case BOTTOM_LEFT: {
                y += dy;
                break;
            }
            case LEFT: {
                y += dy / 2.0;
                break;
            }
            case TOP_LEFT: {
                break;
            }
            default: {
                x += dx / 2.0;
                y += dy / 2.0;
            }
        }
        ps.setAnchor(new Rectangle2D.Double(x, y, w, h));
    }
}

