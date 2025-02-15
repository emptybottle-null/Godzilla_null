/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel.charts;

import org.apache.poi.ss.usermodel.charts.TitleType;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Removal;

@Deprecated
@Removal(version="4.2")
public interface ChartSeries {
    public void setTitle(String var1);

    public void setTitle(CellReference var1);

    public String getTitleString();

    public CellReference getTitleCellReference();

    public TitleType getTitleType();
}

