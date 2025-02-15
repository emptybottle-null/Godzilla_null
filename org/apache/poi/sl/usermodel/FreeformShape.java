/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import java.awt.geom.Path2D;
import org.apache.poi.sl.usermodel.AutoShape;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;

public interface FreeformShape<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>>
extends AutoShape<S, P> {
    public Path2D getPath();

    public int setPath(Path2D var1);
}

