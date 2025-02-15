/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.chart.ErrorBarType;
import org.apache.poi.xddf.usermodel.chart.ErrorDirection;
import org.apache.poi.xddf.usermodel.chart.ErrorValueType;
import org.apache.poi.xddf.usermodel.chart.XDDFChartExtensionList;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumRef;

public class XDDFErrorBars {
    private CTErrBars bars;

    public XDDFErrorBars() {
        this(CTErrBars.Factory.newInstance());
    }

    @Internal
    protected XDDFErrorBars(CTErrBars bars) {
        this.bars = bars;
    }

    @Internal
    protected XmlObject getXmlObject() {
        return this.bars;
    }

    public XDDFChartExtensionList getExtensionList() {
        if (this.bars.isSetExtLst()) {
            return new XDDFChartExtensionList(this.bars.getExtLst());
        }
        return null;
    }

    public void setExtensionList(XDDFChartExtensionList list) {
        if (list == null) {
            if (this.bars.isSetExtLst()) {
                this.bars.unsetExtLst();
            }
        } else {
            this.bars.setExtLst(list.getXmlObject());
        }
    }

    public XDDFShapeProperties getShapeProperties() {
        if (this.bars.isSetSpPr()) {
            return new XDDFShapeProperties(this.bars.getSpPr());
        }
        return null;
    }

    public void setShapeProperties(XDDFShapeProperties properties) {
        if (properties == null) {
            if (this.bars.isSetSpPr()) {
                this.bars.unsetSpPr();
            }
        } else if (this.bars.isSetSpPr()) {
            this.bars.setSpPr(properties.getXmlObject());
        } else {
            this.bars.addNewSpPr().set(properties.getXmlObject());
        }
    }

    public ErrorBarType getErrorBarType() {
        return ErrorBarType.valueOf(this.bars.getErrBarType().getVal());
    }

    public void setErrorBarType(ErrorBarType barType) {
        this.bars.getErrBarType().setVal(barType.underlying);
    }

    public ErrorValueType getErrorValueType() {
        return ErrorValueType.valueOf(this.bars.getErrValType().getVal());
    }

    public void setErrorValueType(ErrorValueType valueType) {
        this.bars.getErrValType().setVal(valueType.underlying);
    }

    public ErrorDirection getErrorDirection() {
        if (this.bars.isSetErrDir()) {
            return ErrorDirection.valueOf(this.bars.getErrDir().getVal());
        }
        return null;
    }

    public void setErrorDirection(ErrorDirection direction) {
        if (direction == null) {
            if (this.bars.isSetErrDir()) {
                this.bars.unsetErrDir();
            }
        } else if (this.bars.isSetErrDir()) {
            this.bars.getErrDir().setVal(direction.underlying);
        } else {
            this.bars.addNewErrDir().setVal(direction.underlying);
        }
    }

    public Boolean getNoEndCap() {
        if (this.bars.isSetVal()) {
            return this.bars.getNoEndCap().getVal();
        }
        return null;
    }

    public void setNoEndCap(Boolean noEndCap) {
        if (noEndCap == null) {
            if (this.bars.isSetNoEndCap()) {
                this.bars.unsetNoEndCap();
            }
        } else if (this.bars.isSetNoEndCap()) {
            this.bars.getNoEndCap().setVal(noEndCap);
        } else {
            this.bars.addNewNoEndCap().setVal(noEndCap);
        }
    }

    public Double getValue() {
        if (this.bars.isSetVal()) {
            return this.bars.getVal().getVal();
        }
        return null;
    }

    public void setValue(Double value) {
        if (value == null) {
            if (this.bars.isSetVal()) {
                this.bars.unsetVal();
            }
        } else if (this.bars.isSetVal()) {
            this.bars.getVal().setVal(value);
        } else {
            this.bars.addNewVal().setVal(value);
        }
    }

    public XDDFNumericalDataSource<Double> getMinus() {
        if (this.bars.isSetMinus()) {
            return XDDFDataSourcesFactory.fromDataSource(this.bars.getMinus());
        }
        return null;
    }

    public void setMinus(XDDFNumericalDataSource<Double> ds) {
        if (ds == null) {
            if (this.bars.isSetMinus()) {
                this.bars.unsetMinus();
            }
        } else if (this.bars.isSetMinus()) {
            ds.fillNumericalCache(this.retrieveCache(this.bars.getMinus(), ds.getDataRangeReference()));
        } else {
            CTNumDataSource ctDS = this.bars.addNewMinus();
            ctDS.addNewNumLit();
            ds.fillNumericalCache(this.retrieveCache(ctDS, ds.getDataRangeReference()));
        }
    }

    public XDDFNumericalDataSource<Double> getPlus() {
        if (this.bars.isSetPlus()) {
            return XDDFDataSourcesFactory.fromDataSource(this.bars.getPlus());
        }
        return null;
    }

    public void setPlus(XDDFNumericalDataSource<Double> ds) {
        if (ds == null) {
            if (this.bars.isSetPlus()) {
                this.bars.unsetPlus();
            }
        } else if (this.bars.isSetPlus()) {
            ds.fillNumericalCache(this.retrieveCache(this.bars.getPlus(), ds.getDataRangeReference()));
        } else {
            CTNumDataSource ctDS = this.bars.addNewPlus();
            ctDS.addNewNumLit();
            ds.fillNumericalCache(this.retrieveCache(ctDS, ds.getDataRangeReference()));
        }
    }

    private CTNumData retrieveCache(CTNumDataSource ds, String dataRangeReference) {
        if (ds.isSetNumRef()) {
            CTNumRef numRef = ds.getNumRef();
            numRef.setF(dataRangeReference);
            return numRef.getNumCache();
        }
        return ds.getNumLit();
    }
}

