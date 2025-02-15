/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.draw.DrawShape;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.usermodel.Background;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.sl.usermodel.ShapeContainer;
import org.apache.poi.sl.usermodel.Sheet;

public class DrawBackground
extends DrawShape {
    public DrawBackground(Background<?, ?> shape) {
        super(shape);
    }

    @Override
    public void draw(Graphics2D graphics) {
        Dimension pg = this.shape.getSheet().getSlideShow().getPageSize();
        final Rectangle2D.Double anchor = new Rectangle2D.Double(0.0, 0.0, pg.getWidth(), pg.getHeight());
        PlaceableShape ps = new PlaceableShape(){

            public ShapeContainer<?, ?> getParent() {
                return null;
            }

            @Override
            public Rectangle2D getAnchor() {
                return anchor;
            }

            @Override
            public void setAnchor(Rectangle2D newAnchor) {
            }

            @Override
            public double getRotation() {
                return 0.0;
            }

            @Override
            public void setRotation(double theta) {
            }

            @Override
            public void setFlipHorizontal(boolean flip) {
            }

            @Override
            public void setFlipVertical(boolean flip) {
            }

            @Override
            public boolean getFlipHorizontal() {
                return false;
            }

            @Override
            public boolean getFlipVertical() {
                return false;
            }

            public Sheet<?, ?> getSheet() {
                return DrawBackground.this.shape.getSheet();
            }
        };
        DrawFactory drawFact = DrawFactory.getInstance(graphics);
        DrawPaint dp = drawFact.getPaint(ps);
        Paint fill = dp.getPaint(graphics, this.getShape().getFillStyle().getPaint());
        Rectangle2D anchor2 = DrawBackground.getAnchor(graphics, anchor);
        if (fill != null) {
            graphics.setRenderingHint(Drawable.GRADIENT_SHAPE, anchor);
            graphics.setPaint(fill);
            DrawPaint.fillPaintWorkaround(graphics, anchor2);
        }
    }

    protected Background<?, ?> getShape() {
        return (Background)this.shape;
    }
}

