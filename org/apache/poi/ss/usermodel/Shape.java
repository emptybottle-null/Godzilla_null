/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import org.apache.poi.ss.usermodel.ChildAnchor;

public interface Shape {
    public String getShapeName();

    public Shape getParent();

    public ChildAnchor getAnchor();

    public boolean isNoFill();

    public void setNoFill(boolean var1);

    public void setFillColor(int var1, int var2, int var3);

    public void setLineStyleColor(int var1, int var2, int var3);
}

