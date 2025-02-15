/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import java.util.List;
import org.apache.poi.sl.usermodel.Comment;
import org.apache.poi.sl.usermodel.MasterSheet;
import org.apache.poi.sl.usermodel.Notes;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;

public interface Slide<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>>
extends Sheet<S, P> {
    public Notes<S, P> getNotes();

    public void setNotes(Notes<S, P> var1);

    public boolean getFollowMasterBackground();

    public void setFollowMasterBackground(boolean var1);

    public boolean getFollowMasterColourScheme();

    public void setFollowMasterColourScheme(boolean var1);

    public boolean getFollowMasterObjects();

    public void setFollowMasterObjects(boolean var1);

    public int getSlideNumber();

    public String getTitle();

    public boolean getDisplayPlaceholder(Placeholder var1);

    public void setHidden(boolean var1);

    public boolean isHidden();

    public List<? extends Comment> getComments();

    public MasterSheet<S, P> getSlideLayout();

    public String getSlideName();
}

