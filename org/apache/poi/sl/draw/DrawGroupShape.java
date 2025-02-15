/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.draw.DrawShape;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.usermodel.GroupShape;
import org.apache.poi.sl.usermodel.Shape;

public class DrawGroupShape
extends DrawShape {
    public DrawGroupShape(GroupShape<?, ?> shape) {
        super(shape);
    }

    @Override
    public void draw(Graphics2D graphics) {
        Rectangle2D interior = this.getShape().getInteriorAnchor();
        Rectangle2D exterior = this.getShape().getAnchor();
        AffineTransform tx = (AffineTransform)graphics.getRenderingHint(Drawable.GROUP_TRANSFORM);
        AffineTransform tx0 = new AffineTransform(tx);
        double scaleX = interior.getWidth() == 0.0 ? 1.0 : exterior.getWidth() / interior.getWidth();
        double scaleY = interior.getHeight() == 0.0 ? 1.0 : exterior.getHeight() / interior.getHeight();
        tx.translate(exterior.getX(), exterior.getY());
        tx.scale(scaleX, scaleY);
        tx.translate(-interior.getX(), -interior.getY());
        DrawFactory drawFact = DrawFactory.getInstance(graphics);
        AffineTransform at2 = graphics.getTransform();
        Iterator iterator = this.getShape().iterator();
        while (iterator.hasNext()) {
            Shape child = (Shape)iterator.next();
            AffineTransform at = graphics.getTransform();
            graphics.setRenderingHint(Drawable.GSAVE, true);
            Drawable draw = drawFact.getDrawable(child);
            draw.applyTransform(graphics);
            draw.draw(graphics);
            graphics.setTransform(at);
            graphics.setRenderingHint(Drawable.GRESTORE, true);
        }
        graphics.setTransform(at2);
        graphics.setRenderingHint(Drawable.GROUP_TRANSFORM, tx0);
    }

    protected GroupShape<?, ?> getShape() {
        return (GroupShape)this.shape;
    }
}

