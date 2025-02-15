/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.composite;

import com.kitfox.svg.composite.AdobeCompositeContext;
import java.awt.Composite;
import java.awt.CompositeContext;
import java.awt.RenderingHints;
import java.awt.image.ColorModel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdobeComposite
implements Composite {
    public static final int CT_NORMAL = 0;
    public static final int CT_MULTIPLY = 1;
    public static final int CT_LAST = 2;
    final int compositeType;
    final float extraAlpha;

    public AdobeComposite(int compositeType, float extraAlpha) {
        this.compositeType = compositeType;
        this.extraAlpha = extraAlpha;
        if (compositeType < 0 || compositeType >= 2) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Invalid composite type");
        }
        if (extraAlpha < 0.0f || extraAlpha > 1.0f) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Invalid alpha");
        }
    }

    public int getCompositeType() {
        return this.compositeType;
    }

    @Override
    public CompositeContext createContext(ColorModel srcColorModel, ColorModel dstColorModel, RenderingHints hints) {
        return new AdobeCompositeContext(this.compositeType, this.extraAlpha);
    }
}

