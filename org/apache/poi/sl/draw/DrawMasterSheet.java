/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw;

import java.awt.Graphics2D;
import org.apache.poi.sl.draw.DrawSheet;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.sl.usermodel.Slide;

public class DrawMasterSheet
extends DrawSheet {
    public DrawMasterSheet(MasterSheet<?, ?> sheet) {
        super(sheet);
    }

    @Override
    protected boolean canDraw(Graphics2D graphics, Shape<?, ?> shape) {
        Placeholder ph;
        Slide slide = (Slide)graphics.getRenderingHint(Drawable.CURRENT_SLIDE);
        if (shape instanceof SimpleShape && (ph = ((SimpleShape)shape).getPlaceholder()) != null) {
            return slide.getDisplayPlaceholder(ph);
        }
        return slide.getFollowMasterGraphics();
    }
}

