/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGUniverse;
import com.kitfox.svg.animation.parser.AnimTimeParser;
import java.io.StringReader;
import java.net.URI;

public class SVGLoaderHelper {
    public final SVGUniverse universe;
    public final SVGDiagram diagram;
    public final URI xmlBase;
    public final AnimTimeParser animTimeParser = new AnimTimeParser(new StringReader(""));

    public SVGLoaderHelper(URI xmlBase, SVGUniverse universe, SVGDiagram diagram) {
        this.xmlBase = xmlBase;
        this.universe = universe;
        this.diagram = diagram;
    }
}

