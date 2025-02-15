/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw;

import java.awt.Graphics2D;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.draw.DrawPictureShape;
import org.apache.poi.sl.draw.DrawShape;
import org.apache.poi.sl.usermodel.GraphicalFrame;
import org.apache.poi.sl.usermodel.PictureShape;

public class DrawGraphicalFrame
extends DrawShape {
    public DrawGraphicalFrame(GraphicalFrame<?, ?> shape) {
        super(shape);
    }

    @Override
    public void draw(Graphics2D context) {
        PictureShape ps = ((GraphicalFrame)this.getShape()).getFallbackPicture();
        if (ps == null) {
            return;
        }
        DrawPictureShape dps = DrawFactory.getInstance(context).getDrawable(ps);
        dps.draw(context);
    }
}

