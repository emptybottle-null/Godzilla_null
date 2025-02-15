/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTPivotFmts
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDispBlanksAs;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegend;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPivotFmts;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D;

public class CTChartImpl
extends XmlComplexContentImpl
implements CTChart {
    private static final long serialVersionUID = 1L;
    private static final QName TITLE$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "title");
    private static final QName AUTOTITLEDELETED$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "autoTitleDeleted");
    private static final QName PIVOTFMTS$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "pivotFmts");
    private static final QName VIEW3D$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "view3D");
    private static final QName FLOOR$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "floor");
    private static final QName SIDEWALL$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "sideWall");
    private static final QName BACKWALL$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "backWall");
    private static final QName PLOTAREA$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "plotArea");
    private static final QName LEGEND$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "legend");
    private static final QName PLOTVISONLY$18 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "plotVisOnly");
    private static final QName DISPBLANKSAS$20 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "dispBlanksAs");
    private static final QName SHOWDLBLSOVERMAX$22 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "showDLblsOverMax");
    private static final QName EXTLST$24 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "extLst");

    public CTChartImpl(SchemaType schemaType) {
        super(schemaType);
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
            cTTitle = (CTTitle)((Object)this.get_store().find_element_user(TITLE$0, 0));
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
            return this.get_store().count_elements(TITLE$0) != 0;
        }
    }

    @Override
    public void setTitle(CTTitle cTTitle) {
        this.generatedSetterHelperImpl(cTTitle, TITLE$0, 0, (short)1);
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
            cTTitle = (CTTitle)((Object)this.get_store().add_element_user(TITLE$0));
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
            this.get_store().remove_element(TITLE$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getAutoTitleDeleted() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(AUTOTITLEDELETED$2, 0));
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
    public boolean isSetAutoTitleDeleted() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AUTOTITLEDELETED$2) != 0;
        }
    }

    @Override
    public void setAutoTitleDeleted(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, AUTOTITLEDELETED$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewAutoTitleDeleted() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(AUTOTITLEDELETED$2));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAutoTitleDeleted() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AUTOTITLEDELETED$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPivotFmts getPivotFmts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPivotFmts cTPivotFmts = null;
            cTPivotFmts = (CTPivotFmts)this.get_store().find_element_user(PIVOTFMTS$4, 0);
            if (cTPivotFmts == null) {
                return null;
            }
            return cTPivotFmts;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPivotFmts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PIVOTFMTS$4) != 0;
        }
    }

    @Override
    public void setPivotFmts(CTPivotFmts cTPivotFmts) {
        this.generatedSetterHelperImpl((XmlObject)cTPivotFmts, PIVOTFMTS$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPivotFmts addNewPivotFmts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPivotFmts cTPivotFmts = null;
            cTPivotFmts = (CTPivotFmts)this.get_store().add_element_user(PIVOTFMTS$4);
            return cTPivotFmts;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPivotFmts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PIVOTFMTS$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTView3D getView3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTView3D cTView3D = null;
            cTView3D = (CTView3D)((Object)this.get_store().find_element_user(VIEW3D$6, 0));
            if (cTView3D == null) {
                return null;
            }
            return cTView3D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetView3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(VIEW3D$6) != 0;
        }
    }

    @Override
    public void setView3D(CTView3D cTView3D) {
        this.generatedSetterHelperImpl(cTView3D, VIEW3D$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTView3D addNewView3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTView3D cTView3D = null;
            cTView3D = (CTView3D)((Object)this.get_store().add_element_user(VIEW3D$6));
            return cTView3D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetView3D() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(VIEW3D$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSurface getFloor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSurface cTSurface = null;
            cTSurface = (CTSurface)((Object)this.get_store().find_element_user(FLOOR$8, 0));
            if (cTSurface == null) {
                return null;
            }
            return cTSurface;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFloor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FLOOR$8) != 0;
        }
    }

    @Override
    public void setFloor(CTSurface cTSurface) {
        this.generatedSetterHelperImpl(cTSurface, FLOOR$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSurface addNewFloor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSurface cTSurface = null;
            cTSurface = (CTSurface)((Object)this.get_store().add_element_user(FLOOR$8));
            return cTSurface;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFloor() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FLOOR$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSurface getSideWall() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSurface cTSurface = null;
            cTSurface = (CTSurface)((Object)this.get_store().find_element_user(SIDEWALL$10, 0));
            if (cTSurface == null) {
                return null;
            }
            return cTSurface;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSideWall() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SIDEWALL$10) != 0;
        }
    }

    @Override
    public void setSideWall(CTSurface cTSurface) {
        this.generatedSetterHelperImpl(cTSurface, SIDEWALL$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSurface addNewSideWall() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSurface cTSurface = null;
            cTSurface = (CTSurface)((Object)this.get_store().add_element_user(SIDEWALL$10));
            return cTSurface;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSideWall() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SIDEWALL$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSurface getBackWall() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSurface cTSurface = null;
            cTSurface = (CTSurface)((Object)this.get_store().find_element_user(BACKWALL$12, 0));
            if (cTSurface == null) {
                return null;
            }
            return cTSurface;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBackWall() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BACKWALL$12) != 0;
        }
    }

    @Override
    public void setBackWall(CTSurface cTSurface) {
        this.generatedSetterHelperImpl(cTSurface, BACKWALL$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSurface addNewBackWall() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSurface cTSurface = null;
            cTSurface = (CTSurface)((Object)this.get_store().add_element_user(BACKWALL$12));
            return cTSurface;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBackWall() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BACKWALL$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPlotArea getPlotArea() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPlotArea cTPlotArea = null;
            cTPlotArea = (CTPlotArea)((Object)this.get_store().find_element_user(PLOTAREA$14, 0));
            if (cTPlotArea == null) {
                return null;
            }
            return cTPlotArea;
        }
    }

    @Override
    public void setPlotArea(CTPlotArea cTPlotArea) {
        this.generatedSetterHelperImpl(cTPlotArea, PLOTAREA$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPlotArea addNewPlotArea() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPlotArea cTPlotArea = null;
            cTPlotArea = (CTPlotArea)((Object)this.get_store().add_element_user(PLOTAREA$14));
            return cTPlotArea;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLegend getLegend() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLegend cTLegend = null;
            cTLegend = (CTLegend)((Object)this.get_store().find_element_user(LEGEND$16, 0));
            if (cTLegend == null) {
                return null;
            }
            return cTLegend;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLegend() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LEGEND$16) != 0;
        }
    }

    @Override
    public void setLegend(CTLegend cTLegend) {
        this.generatedSetterHelperImpl(cTLegend, LEGEND$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLegend addNewLegend() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLegend cTLegend = null;
            cTLegend = (CTLegend)((Object)this.get_store().add_element_user(LEGEND$16));
            return cTLegend;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLegend() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LEGEND$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getPlotVisOnly() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(PLOTVISONLY$18, 0));
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
    public boolean isSetPlotVisOnly() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PLOTVISONLY$18) != 0;
        }
    }

    @Override
    public void setPlotVisOnly(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, PLOTVISONLY$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewPlotVisOnly() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(PLOTVISONLY$18));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPlotVisOnly() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PLOTVISONLY$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDispBlanksAs getDispBlanksAs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDispBlanksAs cTDispBlanksAs = null;
            cTDispBlanksAs = (CTDispBlanksAs)((Object)this.get_store().find_element_user(DISPBLANKSAS$20, 0));
            if (cTDispBlanksAs == null) {
                return null;
            }
            return cTDispBlanksAs;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDispBlanksAs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DISPBLANKSAS$20) != 0;
        }
    }

    @Override
    public void setDispBlanksAs(CTDispBlanksAs cTDispBlanksAs) {
        this.generatedSetterHelperImpl(cTDispBlanksAs, DISPBLANKSAS$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDispBlanksAs addNewDispBlanksAs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDispBlanksAs cTDispBlanksAs = null;
            cTDispBlanksAs = (CTDispBlanksAs)((Object)this.get_store().add_element_user(DISPBLANKSAS$20));
            return cTDispBlanksAs;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDispBlanksAs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DISPBLANKSAS$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getShowDLblsOverMax() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(SHOWDLBLSOVERMAX$22, 0));
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
    public boolean isSetShowDLblsOverMax() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHOWDLBLSOVERMAX$22) != 0;
        }
    }

    @Override
    public void setShowDLblsOverMax(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, SHOWDLBLSOVERMAX$22, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewShowDLblsOverMax() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(SHOWDLBLSOVERMAX$22));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetShowDLblsOverMax() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHOWDLBLSOVERMAX$22, 0);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().find_element_user(EXTLST$24, 0));
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
            return this.get_store().count_elements(EXTLST$24) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl(cTExtensionList, EXTLST$24, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().add_element_user(EXTLST$24));
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
            this.get_store().remove_element(EXTLST$24, 0);
        }
    }
}

