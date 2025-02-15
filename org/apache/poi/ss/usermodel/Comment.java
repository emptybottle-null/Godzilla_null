/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellAddress;

public interface Comment {
    public void setVisible(boolean var1);

    public boolean isVisible();

    public CellAddress getAddress();

    public void setAddress(CellAddress var1);

    public void setAddress(int var1, int var2);

    public int getRow();

    public void setRow(int var1);

    public int getColumn();

    public void setColumn(int var1);

    public String getAuthor();

    public void setAuthor(String var1);

    public RichTextString getString();

    public void setString(RichTextString var1);

    public ClientAnchor getClientAnchor();
}

