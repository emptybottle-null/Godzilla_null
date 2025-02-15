/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.SVGUniverse;

public class SVGCache {
    private static final SVGUniverse svgUniverse = new SVGUniverse();

    private SVGCache() {
    }

    public static SVGUniverse getSVGUniverse() {
        return svgUniverse;
    }
}

