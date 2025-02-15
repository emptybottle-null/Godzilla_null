/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.geom;

import java.awt.Shape;
import org.apache.poi.sl.draw.geom.Path;

public class Outline {
    private Shape shape;
    private Path path;

    public Outline(Shape shape, Path path) {
        this.shape = shape;
        this.path = path;
    }

    public Path getPath() {
        return this.path;
    }

    public Shape getOutline() {
        return this.shape;
    }
}

