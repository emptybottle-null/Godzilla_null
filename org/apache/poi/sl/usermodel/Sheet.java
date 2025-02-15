/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import java.awt.Graphics2D;
import org.apache.poi.sl.usermodel.Background;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.PlaceholderDetails;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.ShapeContainer;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;

public interface Sheet<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>>
extends ShapeContainer<S, P> {
    public SlideShow<S, P> getSlideShow();

    public boolean getFollowMasterGraphics();

    public MasterSheet<S, P> getMasterSheet();

    public Background<S, P> getBackground();

    public void draw(Graphics2D var1);

    public PlaceholderDetails getPlaceholderDetails(Placeholder var1);
}

