/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import java.util.regex.Pattern;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.TableStyleInfo;
import org.apache.poi.ss.util.CellReference;

public interface Table {
    public static final Pattern isStructuredReference = Pattern.compile("[a-zA-Z_\\\\][a-zA-Z0-9._]*\\[.*\\]");

    public int getStartColIndex();

    public int getStartRowIndex();

    public int getEndColIndex();

    public int getEndRowIndex();

    public String getName();

    public String getStyleName();

    public int findColumnIndex(String var1);

    public String getSheetName();

    public boolean isHasTotalsRow();

    public int getTotalsRowCount();

    public int getHeaderRowCount();

    public TableStyleInfo getStyle();

    default public boolean contains(Cell cell) {
        if (cell == null) {
            return false;
        }
        return this.contains(new CellReference(cell.getSheet().getSheetName(), cell.getRowIndex(), cell.getColumnIndex(), true, true));
    }

    public boolean contains(CellReference var1);
}

