/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import java.awt.geom.Point2D;
import java.util.Date;

public interface Comment {
    public String getAuthor();

    public void setAuthor(String var1);

    public String getAuthorInitials();

    public void setAuthorInitials(String var1);

    public String getText();

    public void setText(String var1);

    public Date getDate();

    public void setDate(Date var1);

    public Point2D getOffset();

    public void setOffset(Point2D var1);
}

