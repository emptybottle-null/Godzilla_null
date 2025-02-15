/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;

public interface XDDFCategoryDataSource
extends XDDFDataSource<String> {
    @Override
    default public int getColIndex() {
        return 0;
    }

    @Override
    default public boolean isLiteral() {
        return false;
    }

    @Override
    default public boolean isNumeric() {
        return false;
    }

    @Override
    default public boolean isReference() {
        return true;
    }

    @Override
    default public String getDataRangeReference() {
        return this.getFormula();
    }
}

