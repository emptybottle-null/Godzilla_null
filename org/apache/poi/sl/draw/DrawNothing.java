/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw;

import java.awt.Graphics2D;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.usermodel.Shape;

public class DrawNothing
implements Drawable {
    protected final Shape<?, ?> shape;

    public DrawNothing(Shape<?, ?> shape) {
        this.shape = shape;
    }

    @Override
    public void applyTransform(Graphics2D graphics) {
    }

    @Override
    public void draw(Graphics2D graphics) {
    }

    @Override
    public void drawContent(Graphics2D context) {
    }
}

