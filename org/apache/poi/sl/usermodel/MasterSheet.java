/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;

public interface MasterSheet<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>>
extends Sheet<S, P> {
    public SimpleShape<S, P> getPlaceholder(Placeholder var1);
}

