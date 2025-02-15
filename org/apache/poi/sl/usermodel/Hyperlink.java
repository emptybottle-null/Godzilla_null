/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;

public interface Hyperlink<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>>
extends org.apache.poi.common.usermodel.Hyperlink {
    public void linkToEmail(String var1);

    public void linkToUrl(String var1);

    public void linkToSlide(Slide<S, P> var1);

    public void linkToNextSlide();

    public void linkToPreviousSlide();

    public void linkToFirstSlide();

    public void linkToLastSlide();
}

