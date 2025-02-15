/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import java.awt.Insets;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;

public interface PictureShape<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>>
extends SimpleShape<S, P> {
    public PictureData getPictureData();

    default public PictureData getAlternativePictureData() {
        return null;
    }

    public Insets getClipping();
}

