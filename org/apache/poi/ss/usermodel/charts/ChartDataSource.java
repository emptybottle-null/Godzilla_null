/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel.charts;

import org.apache.poi.util.Removal;

@Deprecated
@Removal(version="4.2")
public interface ChartDataSource<T> {
    public int getPointCount();

    public T getPointAt(int var1);

    public boolean isReference();

    public boolean isNumeric();

    public String getFormulaString();
}

