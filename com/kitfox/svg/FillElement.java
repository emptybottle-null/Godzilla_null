/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.SVGElement;
import java.awt.Paint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public abstract class FillElement
extends SVGElement {
    public abstract Paint getPaint(Rectangle2D var1, AffineTransform var2);
}

