/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw;

import java.awt.Graphics2D;
import org.apache.poi.sl.draw.DrawBackground;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.draw.DrawSheet;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.usermodel.Background;
import org.apache.poi.sl.usermodel.Slide;

public class DrawSlide
extends DrawSheet {
    public DrawSlide(Slide<?, ?> slide) {
        super(slide);
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setRenderingHint(Drawable.CURRENT_SLIDE, this.sheet);
        Background bg = this.sheet.getBackground();
        if (bg != null) {
            DrawFactory drawFact = DrawFactory.getInstance(graphics);
            DrawBackground db = drawFact.getDrawable(bg);
            db.draw(graphics);
        }
        super.draw(graphics);
        graphics.setRenderingHint(Drawable.CURRENT_SLIDE, null);
    }
}

