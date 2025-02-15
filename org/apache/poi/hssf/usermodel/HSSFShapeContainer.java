/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.ss.usermodel.ShapeContainer;

public interface HSSFShapeContainer
extends ShapeContainer<HSSFShape> {
    public List<HSSFShape> getChildren();

    public void addShape(HSSFShape var1);

    public void setCoordinates(int var1, int var2, int var3, int var4);

    public void clear();

    public int getX1();

    public int getY1();

    public int getX2();

    public int getY2();

    public boolean removeShape(HSSFShape var1);
}

