/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw;

import org.apache.poi.sl.draw.DrawAutoShape;
import org.apache.poi.sl.usermodel.AutoShape;
import org.apache.poi.sl.usermodel.TextBox;

public class DrawTextBox
extends DrawAutoShape {
    public DrawTextBox(TextBox<?, ?> shape) {
        super((AutoShape<?, ?>)shape);
    }
}

