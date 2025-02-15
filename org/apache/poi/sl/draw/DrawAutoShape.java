/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw;

import org.apache.poi.sl.draw.DrawTextShape;
import org.apache.poi.sl.usermodel.AutoShape;
import org.apache.poi.sl.usermodel.TextShape;

public class DrawAutoShape
extends DrawTextShape {
    public DrawAutoShape(AutoShape<?, ?> shape) {
        super((TextShape<?, ?>)shape);
    }
}

