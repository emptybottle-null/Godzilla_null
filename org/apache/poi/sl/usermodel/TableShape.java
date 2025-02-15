/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.TableCell;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;

public interface TableShape<S extends Shape<S, P>, P extends TextParagraph<S, P, ? extends TextRun>>
extends Shape<S, P>,
PlaceableShape<S, P> {
    public int getNumberOfColumns();

    public int getNumberOfRows();

    public TableCell<S, P> getCell(int var1, int var2);

    public double getColumnWidth(int var1);

    public void setColumnWidth(int var1, double var2);

    public double getRowHeight(int var1);

    public void setRowHeight(int var1, double var2);
}

