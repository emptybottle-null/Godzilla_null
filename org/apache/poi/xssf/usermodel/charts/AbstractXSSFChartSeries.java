/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel.charts;

import org.apache.poi.ss.usermodel.charts.ChartSeries;
import org.apache.poi.ss.usermodel.charts.TitleType;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.Removal;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;

@Deprecated
@Removal(version="4.2")
public abstract class AbstractXSSFChartSeries
implements ChartSeries {
    private String titleValue;
    private CellReference titleRef;
    private TitleType titleType;

    @Override
    public void setTitle(CellReference titleReference) {
        this.titleType = TitleType.CELL_REFERENCE;
        this.titleRef = titleReference;
    }

    @Override
    public void setTitle(String title) {
        this.titleType = TitleType.STRING;
        this.titleValue = title;
    }

    @Override
    public CellReference getTitleCellReference() {
        if (TitleType.CELL_REFERENCE.equals((Object)this.titleType)) {
            return this.titleRef;
        }
        throw new IllegalStateException("Title type is not CellReference.");
    }

    @Override
    public String getTitleString() {
        if (TitleType.STRING.equals((Object)this.titleType)) {
            return this.titleValue;
        }
        throw new IllegalStateException("Title type is not String.");
    }

    @Override
    public TitleType getTitleType() {
        return this.titleType;
    }

    protected boolean isTitleSet() {
        return this.titleType != null;
    }

    protected CTSerTx getCTSerTx() {
        CTSerTx tx = CTSerTx.Factory.newInstance();
        switch (this.titleType) {
            case CELL_REFERENCE: {
                tx.addNewStrRef().setF(this.titleRef.formatAsString());
                return tx;
            }
            case STRING: {
                tx.setV(this.titleValue);
                return tx;
            }
        }
        throw new IllegalStateException("Unkown title type: " + (Object)((Object)this.titleType));
    }
}

