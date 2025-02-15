/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumVal;

public interface XDDFNumericalDataSource<T extends Number>
extends XDDFDataSource<T> {
    public String getFormatCode();

    public void setFormatCode(String var1);

    @Override
    default public boolean isLiteral() {
        return false;
    }

    @Internal
    default public void fillNumericalCache(CTNumData cache) {
        String formatCode = this.getFormatCode();
        if (formatCode == null) {
            if (cache.isSetFormatCode()) {
                cache.unsetFormatCode();
            }
        } else {
            cache.setFormatCode(formatCode);
        }
        cache.setPtArray(null);
        int numOfPoints = this.getPointCount();
        int effectiveNumOfPoints = 0;
        for (int i = 0; i < numOfPoints; ++i) {
            Object value = this.getPointAt(i);
            if (value == null) continue;
            CTNumVal ctNumVal = cache.addNewPt();
            ctNumVal.setIdx(i);
            ctNumVal.setV(value.toString());
            ++effectiveNumOfPoints;
        }
        if (effectiveNumOfPoints == 0) {
            cache.unsetPtCount();
        } else if (cache.isSetPtCount()) {
            cache.getPtCount().setVal(numOfPoints);
        } else {
            cache.addNewPtCount().setVal(numOfPoints);
        }
    }
}

