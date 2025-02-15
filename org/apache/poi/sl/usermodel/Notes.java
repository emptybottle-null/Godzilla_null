/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import java.util.List;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;

public interface Notes<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>>
extends Sheet<S, P> {
    public List<? extends List<P>> getTextParagraphs();
}

