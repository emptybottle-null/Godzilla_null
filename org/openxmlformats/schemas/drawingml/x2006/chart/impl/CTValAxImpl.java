/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTDispUnits
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxisUnit;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCrossBetween;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCrosses;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDispUnits;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDouble;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumFmt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickLblPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickMark;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public class CTValAxImpl
extends XmlComplexContentImpl
implements CTValAx {
    private static final long serialVersionUID = 1L;
    private static final QName AXID$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "axId");
    private static final QName SCALING$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "scaling");
    private static final QName DELETE$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "delete");
    private static final QName AXPOS$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "axPos");
    private static final QName MAJORGRIDLINES$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "majorGridlines");
    private static final QName MINORGRIDLINES$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "minorGridlines");
    private static final QName TITLE$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "title");
    private static final QName NUMFMT$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "numFmt");
    private static final QName MAJORTICKMARK$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "majorTickMark");
    private static final QName MINORTICKMARK$18 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "minorTickMark");
    private static final QName TICKLBLPOS$20 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "tickLblPos");
    private static final QName SPPR$22 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "spPr");
    private static final QName TXPR$24 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "txPr");
    private static final QName CROSSAX$26 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "crossAx");
    private static final QName CROSSES$28 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "crosses");
    private static final QName CROSSESAT$30 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "crossesAt");
    private static final QName CROSSBETWEEN$32 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "crossBetween");
    private static final QName MAJORUNIT$34 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "majorUnit");
    private static final QName MINORUNIT$36 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "minorUnit");
    private static final QName DISPUNITS$38 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "dispUnits");
    private static final QName EXTLST$40 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "extLst");

    public CTValAxImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUnsignedInt getAxId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUnsignedInt cTUnsignedInt = null;
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().find_element_user(AXID$0, 0));
            if (cTUnsignedInt == null) {
                return null;
            }
            return cTUnsignedInt;
        }
    }

    @Override
    public void setAxId(CTUnsignedInt cTUnsignedInt) {
        this.generatedSetterHelperImpl(cTUnsignedInt, AXID$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUnsignedInt addNewAxId() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUnsignedInt cTUnsignedInt = null;
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().add_element_user(AXID$0));
            return cTUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTScaling getScaling() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTScaling cTScaling = null;
            cTScaling = (CTScaling)((Object)this.get_store().find_element_user(SCALING$2, 0));
            if (cTScaling == null) {
                return null;
            }
            return cTScaling;
        }
    }

    @Override
    public void setScaling(CTScaling cTScaling) {
        this.generatedSetterHelperImpl(cTScaling, SCALING$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTScaling addNewScaling() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTScaling cTScaling = null;
            cTScaling = (CTScaling)((Object)this.get_store().add_element_user(SCALING$2));
            return cTScaling;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getDelete() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(DELETE$4, 0));
            if (cTBoolean == null) {
                return null;
            }
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDelete() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DELETE$4) != 0;
        }
    }

    @Override
    public void setDelete(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, DELETE$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewDelete() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(DELETE$4));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDelete() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DELETE$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAxPos getAxPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAxPos cTAxPos = null;
            cTAxPos = (CTAxPos)((Object)this.get_store().find_element_user(AXPOS$6, 0));
            if (cTAxPos == null) {
                return null;
            }
            return cTAxPos;
        }
    }

    @Override
    public void setAxPos(CTAxPos cTAxPos) {
        this.generatedSetterHelperImpl(cTAxPos, AXPOS$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAxPos addNewAxPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAxPos cTAxPos = null;
            cTAxPos = (CTAxPos)((Object)this.get_store().add_element_user(AXPOS$6));
            return cTAxPos;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartLines getMajorGridlines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartLines cTChartLines = null;
            cTChartLines = (CTChartLines)((Object)this.get_store().find_element_user(MAJORGRIDLINES$8, 0));
            if (cTChartLines == null) {
                return null;
            }
            return cTChartLines;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMajorGridlines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MAJORGRIDLINES$8) != 0;
        }
    }

    @Override
    public void setMajorGridlines(CTChartLines cTChartLines) {
        this.generatedSetterHelperImpl(cTChartLines, MAJORGRIDLINES$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartLines addNewMajorGridlines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartLines cTChartLines = null;
            cTChartLines = (CTChartLines)((Object)this.get_store().add_element_user(MAJORGRIDLINES$8));
            return cTChartLines;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMajorGridlines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MAJORGRIDLINES$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartLines getMinorGridlines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartLines cTChartLines = null;
            cTChartLines = (CTChartLines)((Object)this.get_store().find_element_user(MINORGRIDLINES$10, 0));
            if (cTChartLines == null) {
                return null;
            }
            return cTChartLines;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMinorGridlines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MINORGRIDLINES$10) != 0;
        }
    }

    @Override
    public void setMinorGridlines(CTChartLines cTChartLines) {
        this.generatedSetterHelperImpl(cTChartLines, MINORGRIDLINES$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartLines addNewMinorGridlines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartLines cTChartLines = null;
            cTChartLines = (CTChartLines)((Object)this.get_store().add_element_user(MINORGRIDLINES$10));
            return cTChartLines;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMinorGridlines() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MINORGRIDLINES$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTitle getTitle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTitle cTTitle = null;
            cTTitle = (CTTitle)((Object)this.get_store().find_element_user(TITLE$12, 0));
            if (cTTitle == null) {
                return null;
            }
            return cTTitle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTitle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TITLE$12) != 0;
        }
    }

    @Override
    public void setTitle(CTTitle cTTitle) {
        this.generatedSetterHelperImpl(cTTitle, TITLE$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTitle addNewTitle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTitle cTTitle = null;
            cTTitle = (CTTitle)((Object)this.get_store().add_element_user(TITLE$12));
            return cTTitle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTitle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TITLE$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumFmt getNumFmt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumFmt cTNumFmt = null;
            cTNumFmt = (CTNumFmt)((Object)this.get_store().find_element_user(NUMFMT$14, 0));
            if (cTNumFmt == null) {
                return null;
            }
            return cTNumFmt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNumFmt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NUMFMT$14) != 0;
        }
    }

    @Override
    public void setNumFmt(CTNumFmt cTNumFmt) {
        this.generatedSetterHelperImpl(cTNumFmt, NUMFMT$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumFmt addNewNumFmt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumFmt cTNumFmt = null;
            cTNumFmt = (CTNumFmt)((Object)this.get_store().add_element_user(NUMFMT$14));
            return cTNumFmt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNumFmt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NUMFMT$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTickMark getMajorTickMark() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTickMark cTTickMark = null;
            cTTickMark = (CTTickMark)((Object)this.get_store().find_element_user(MAJORTICKMARK$16, 0));
            if (cTTickMark == null) {
                return null;
            }
            return cTTickMark;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMajorTickMark() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MAJORTICKMARK$16) != 0;
        }
    }

    @Override
    public void setMajorTickMark(CTTickMark cTTickMark) {
        this.generatedSetterHelperImpl(cTTickMark, MAJORTICKMARK$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTickMark addNewMajorTickMark() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTickMark cTTickMark = null;
            cTTickMark = (CTTickMark)((Object)this.get_store().add_element_user(MAJORTICKMARK$16));
            return cTTickMark;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMajorTickMark() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MAJORTICKMARK$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTickMark getMinorTickMark() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTickMark cTTickMark = null;
            cTTickMark = (CTTickMark)((Object)this.get_store().find_element_user(MINORTICKMARK$18, 0));
            if (cTTickMark == null) {
                return null;
            }
            return cTTickMark;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMinorTickMark() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MINORTICKMARK$18) != 0;
        }
    }

    @Override
    public void setMinorTickMark(CTTickMark cTTickMark) {
        this.generatedSetterHelperImpl(cTTickMark, MINORTICKMARK$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTickMark addNewMinorTickMark() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTickMark cTTickMark = null;
            cTTickMark = (CTTickMark)((Object)this.get_store().add_element_user(MINORTICKMARK$18));
            return cTTickMark;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMinorTickMark() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MINORTICKMARK$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTickLblPos getTickLblPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTickLblPos cTTickLblPos = null;
            cTTickLblPos = (CTTickLblPos)((Object)this.get_store().find_element_user(TICKLBLPOS$20, 0));
            if (cTTickLblPos == null) {
                return null;
            }
            return cTTickLblPos;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTickLblPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TICKLBLPOS$20) != 0;
        }
    }

    @Override
    public void setTickLblPos(CTTickLblPos cTTickLblPos) {
        this.generatedSetterHelperImpl(cTTickLblPos, TICKLBLPOS$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTickLblPos addNewTickLblPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTickLblPos cTTickLblPos = null;
            cTTickLblPos = (CTTickLblPos)((Object)this.get_store().add_element_user(TICKLBLPOS$20));
            return cTTickLblPos;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTickLblPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TICKLBLPOS$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeProperties getSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeProperties cTShapeProperties = null;
            cTShapeProperties = (CTShapeProperties)((Object)this.get_store().find_element_user(SPPR$22, 0));
            if (cTShapeProperties == null) {
                return null;
            }
            return cTShapeProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SPPR$22) != 0;
        }
    }

    @Override
    public void setSpPr(CTShapeProperties cTShapeProperties) {
        this.generatedSetterHelperImpl(cTShapeProperties, SPPR$22, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeProperties addNewSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeProperties cTShapeProperties = null;
            cTShapeProperties = (CTShapeProperties)((Object)this.get_store().add_element_user(SPPR$22));
            return cTShapeProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SPPR$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBody getTxPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBody cTTextBody = null;
            cTTextBody = (CTTextBody)((Object)this.get_store().find_element_user(TXPR$24, 0));
            if (cTTextBody == null) {
                return null;
            }
            return cTTextBody;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTxPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TXPR$24) != 0;
        }
    }

    @Override
    public void setTxPr(CTTextBody cTTextBody) {
        this.generatedSetterHelperImpl(cTTextBody, TXPR$24, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextBody addNewTxPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextBody cTTextBody = null;
            cTTextBody = (CTTextBody)((Object)this.get_store().add_element_user(TXPR$24));
            return cTTextBody;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTxPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TXPR$24, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUnsignedInt getCrossAx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUnsignedInt cTUnsignedInt = null;
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().find_element_user(CROSSAX$26, 0));
            if (cTUnsignedInt == null) {
                return null;
            }
            return cTUnsignedInt;
        }
    }

    @Override
    public void setCrossAx(CTUnsignedInt cTUnsignedInt) {
        this.generatedSetterHelperImpl(cTUnsignedInt, CROSSAX$26, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTUnsignedInt addNewCrossAx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTUnsignedInt cTUnsignedInt = null;
            cTUnsignedInt = (CTUnsignedInt)((Object)this.get_store().add_element_user(CROSSAX$26));
            return cTUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCrosses getCrosses() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCrosses cTCrosses = null;
            cTCrosses = (CTCrosses)((Object)this.get_store().find_element_user(CROSSES$28, 0));
            if (cTCrosses == null) {
                return null;
            }
            return cTCrosses;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCrosses() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CROSSES$28) != 0;
        }
    }

    @Override
    public void setCrosses(CTCrosses cTCrosses) {
        this.generatedSetterHelperImpl(cTCrosses, CROSSES$28, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCrosses addNewCrosses() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCrosses cTCrosses = null;
            cTCrosses = (CTCrosses)((Object)this.get_store().add_element_user(CROSSES$28));
            return cTCrosses;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCrosses() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CROSSES$28, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDouble getCrossesAt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDouble cTDouble = null;
            cTDouble = (CTDouble)((Object)this.get_store().find_element_user(CROSSESAT$30, 0));
            if (cTDouble == null) {
                return null;
            }
            return cTDouble;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCrossesAt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CROSSESAT$30) != 0;
        }
    }

    @Override
    public void setCrossesAt(CTDouble cTDouble) {
        this.generatedSetterHelperImpl(cTDouble, CROSSESAT$30, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDouble addNewCrossesAt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDouble cTDouble = null;
            cTDouble = (CTDouble)((Object)this.get_store().add_element_user(CROSSESAT$30));
            return cTDouble;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCrossesAt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CROSSESAT$30, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCrossBetween getCrossBetween() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCrossBetween cTCrossBetween = null;
            cTCrossBetween = (CTCrossBetween)((Object)this.get_store().find_element_user(CROSSBETWEEN$32, 0));
            if (cTCrossBetween == null) {
                return null;
            }
            return cTCrossBetween;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCrossBetween() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CROSSBETWEEN$32) != 0;
        }
    }

    @Override
    public void setCrossBetween(CTCrossBetween cTCrossBetween) {
        this.generatedSetterHelperImpl(cTCrossBetween, CROSSBETWEEN$32, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCrossBetween addNewCrossBetween() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCrossBetween cTCrossBetween = null;
            cTCrossBetween = (CTCrossBetween)((Object)this.get_store().add_element_user(CROSSBETWEEN$32));
            return cTCrossBetween;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCrossBetween() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CROSSBETWEEN$32, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAxisUnit getMajorUnit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAxisUnit cTAxisUnit = null;
            cTAxisUnit = (CTAxisUnit)((Object)this.get_store().find_element_user(MAJORUNIT$34, 0));
            if (cTAxisUnit == null) {
                return null;
            }
            return cTAxisUnit;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMajorUnit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MAJORUNIT$34) != 0;
        }
    }

    @Override
    public void setMajorUnit(CTAxisUnit cTAxisUnit) {
        this.generatedSetterHelperImpl(cTAxisUnit, MAJORUNIT$34, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAxisUnit addNewMajorUnit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAxisUnit cTAxisUnit = null;
            cTAxisUnit = (CTAxisUnit)((Object)this.get_store().add_element_user(MAJORUNIT$34));
            return cTAxisUnit;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMajorUnit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MAJORUNIT$34, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAxisUnit getMinorUnit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAxisUnit cTAxisUnit = null;
            cTAxisUnit = (CTAxisUnit)((Object)this.get_store().find_element_user(MINORUNIT$36, 0));
            if (cTAxisUnit == null) {
                return null;
            }
            return cTAxisUnit;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMinorUnit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MINORUNIT$36) != 0;
        }
    }

    @Override
    public void setMinorUnit(CTAxisUnit cTAxisUnit) {
        this.generatedSetterHelperImpl(cTAxisUnit, MINORUNIT$36, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAxisUnit addNewMinorUnit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAxisUnit cTAxisUnit = null;
            cTAxisUnit = (CTAxisUnit)((Object)this.get_store().add_element_user(MINORUNIT$36));
            return cTAxisUnit;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMinorUnit() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MINORUNIT$36, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDispUnits getDispUnits() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDispUnits cTDispUnits = null;
            cTDispUnits = (CTDispUnits)this.get_store().find_element_user(DISPUNITS$38, 0);
            if (cTDispUnits == null) {
                return null;
            }
            return cTDispUnits;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDispUnits() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DISPUNITS$38) != 0;
        }
    }

    @Override
    public void setDispUnits(CTDispUnits cTDispUnits) {
        this.generatedSetterHelperImpl((XmlObject)cTDispUnits, DISPUNITS$38, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDispUnits addNewDispUnits() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDispUnits cTDispUnits = null;
            cTDispUnits = (CTDispUnits)this.get_store().add_element_user(DISPUNITS$38);
            return cTDispUnits;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDispUnits() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DISPUNITS$38, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)((Object)this.get_store().find_element_user(EXTLST$40, 0));
            if (cTExtensionList == null) {
                return null;
            }
            return cTExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTLST$40) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl(cTExtensionList, EXTLST$40, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExtensionList cTExtensionList = null;
            cTExtensionList = (CTExtensionList)((Object)this.get_store().add_element_user(EXTLST$40));
            return cTExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTLST$40, 0);
        }
    }
}

