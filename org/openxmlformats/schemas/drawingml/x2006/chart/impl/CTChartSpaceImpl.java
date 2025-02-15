/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTPivotSource
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTStyle
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTTextLanguageID
 */
package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExternalData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPivotSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRelId;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStyle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTextLanguageID;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public class CTChartSpaceImpl
extends XmlComplexContentImpl
implements CTChartSpace {
    private static final long serialVersionUID = 1L;
    private static final QName DATE1904$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "date1904");
    private static final QName LANG$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "lang");
    private static final QName ROUNDEDCORNERS$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "roundedCorners");
    private static final QName STYLE$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "style");
    private static final QName CLRMAPOVR$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "clrMapOvr");
    private static final QName PIVOTSOURCE$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "pivotSource");
    private static final QName PROTECTION$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "protection");
    private static final QName CHART$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "chart");
    private static final QName SPPR$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "spPr");
    private static final QName TXPR$18 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "txPr");
    private static final QName EXTERNALDATA$20 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "externalData");
    private static final QName PRINTSETTINGS$22 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "printSettings");
    private static final QName USERSHAPES$24 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "userShapes");
    private static final QName EXTLST$26 = new QName("http://schemas.openxmlformats.org/drawingml/2006/chart", "extLst");

    public CTChartSpaceImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getDate1904() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(DATE1904$0, 0));
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
    public boolean isSetDate1904() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DATE1904$0) != 0;
        }
    }

    @Override
    public void setDate1904(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, DATE1904$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewDate1904() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(DATE1904$0));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDate1904() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DATE1904$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextLanguageID getLang() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextLanguageID cTTextLanguageID = null;
            cTTextLanguageID = (CTTextLanguageID)this.get_store().find_element_user(LANG$2, 0);
            if (cTTextLanguageID == null) {
                return null;
            }
            return cTTextLanguageID;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLang() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LANG$2) != 0;
        }
    }

    @Override
    public void setLang(CTTextLanguageID cTTextLanguageID) {
        this.generatedSetterHelperImpl((XmlObject)cTTextLanguageID, LANG$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextLanguageID addNewLang() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextLanguageID cTTextLanguageID = null;
            cTTextLanguageID = (CTTextLanguageID)this.get_store().add_element_user(LANG$2);
            return cTTextLanguageID;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLang() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LANG$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean getRoundedCorners() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().find_element_user(ROUNDEDCORNERS$4, 0));
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
    public boolean isSetRoundedCorners() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ROUNDEDCORNERS$4) != 0;
        }
    }

    @Override
    public void setRoundedCorners(CTBoolean cTBoolean) {
        this.generatedSetterHelperImpl(cTBoolean, ROUNDEDCORNERS$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBoolean addNewRoundedCorners() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBoolean cTBoolean = null;
            cTBoolean = (CTBoolean)((Object)this.get_store().add_element_user(ROUNDEDCORNERS$4));
            return cTBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRoundedCorners() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ROUNDEDCORNERS$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStyle getStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStyle cTStyle = null;
            cTStyle = (CTStyle)this.get_store().find_element_user(STYLE$6, 0);
            if (cTStyle == null) {
                return null;
            }
            return cTStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(STYLE$6) != 0;
        }
    }

    @Override
    public void setStyle(CTStyle cTStyle) {
        this.generatedSetterHelperImpl((XmlObject)cTStyle, STYLE$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStyle addNewStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStyle cTStyle = null;
            cTStyle = (CTStyle)this.get_store().add_element_user(STYLE$6);
            return cTStyle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStyle() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(STYLE$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorMapping getClrMapOvr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorMapping cTColorMapping = null;
            cTColorMapping = (CTColorMapping)((Object)this.get_store().find_element_user(CLRMAPOVR$8, 0));
            if (cTColorMapping == null) {
                return null;
            }
            return cTColorMapping;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetClrMapOvr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CLRMAPOVR$8) != 0;
        }
    }

    @Override
    public void setClrMapOvr(CTColorMapping cTColorMapping) {
        this.generatedSetterHelperImpl(cTColorMapping, CLRMAPOVR$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorMapping addNewClrMapOvr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorMapping cTColorMapping = null;
            cTColorMapping = (CTColorMapping)((Object)this.get_store().add_element_user(CLRMAPOVR$8));
            return cTColorMapping;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetClrMapOvr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CLRMAPOVR$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPivotSource getPivotSource() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPivotSource cTPivotSource = null;
            cTPivotSource = (CTPivotSource)this.get_store().find_element_user(PIVOTSOURCE$10, 0);
            if (cTPivotSource == null) {
                return null;
            }
            return cTPivotSource;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPivotSource() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PIVOTSOURCE$10) != 0;
        }
    }

    @Override
    public void setPivotSource(CTPivotSource cTPivotSource) {
        this.generatedSetterHelperImpl((XmlObject)cTPivotSource, PIVOTSOURCE$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPivotSource addNewPivotSource() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPivotSource cTPivotSource = null;
            cTPivotSource = (CTPivotSource)this.get_store().add_element_user(PIVOTSOURCE$10);
            return cTPivotSource;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPivotSource() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PIVOTSOURCE$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTProtection getProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTProtection cTProtection = null;
            cTProtection = (CTProtection)this.get_store().find_element_user(PROTECTION$12, 0);
            if (cTProtection == null) {
                return null;
            }
            return cTProtection;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PROTECTION$12) != 0;
        }
    }

    @Override
    public void setProtection(CTProtection cTProtection) {
        this.generatedSetterHelperImpl((XmlObject)cTProtection, PROTECTION$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTProtection addNewProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTProtection cTProtection = null;
            cTProtection = (CTProtection)this.get_store().add_element_user(PROTECTION$12);
            return cTProtection;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PROTECTION$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChart getChart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChart cTChart = null;
            cTChart = (CTChart)((Object)this.get_store().find_element_user(CHART$14, 0));
            if (cTChart == null) {
                return null;
            }
            return cTChart;
        }
    }

    @Override
    public void setChart(CTChart cTChart) {
        this.generatedSetterHelperImpl(cTChart, CHART$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChart addNewChart() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChart cTChart = null;
            cTChart = (CTChart)((Object)this.get_store().add_element_user(CHART$14));
            return cTChart;
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
            cTShapeProperties = (CTShapeProperties)((Object)this.get_store().find_element_user(SPPR$16, 0));
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
            return this.get_store().count_elements(SPPR$16) != 0;
        }
    }

    @Override
    public void setSpPr(CTShapeProperties cTShapeProperties) {
        this.generatedSetterHelperImpl(cTShapeProperties, SPPR$16, 0, (short)1);
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
            cTShapeProperties = (CTShapeProperties)((Object)this.get_store().add_element_user(SPPR$16));
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
            this.get_store().remove_element(SPPR$16, 0);
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
            cTTextBody = (CTTextBody)((Object)this.get_store().find_element_user(TXPR$18, 0));
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
            return this.get_store().count_elements(TXPR$18) != 0;
        }
    }

    @Override
    public void setTxPr(CTTextBody cTTextBody) {
        this.generatedSetterHelperImpl(cTTextBody, TXPR$18, 0, (short)1);
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
            cTTextBody = (CTTextBody)((Object)this.get_store().add_element_user(TXPR$18));
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
            this.get_store().remove_element(TXPR$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalData getExternalData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalData cTExternalData = null;
            cTExternalData = (CTExternalData)((Object)this.get_store().find_element_user(EXTERNALDATA$20, 0));
            if (cTExternalData == null) {
                return null;
            }
            return cTExternalData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExternalData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTERNALDATA$20) != 0;
        }
    }

    @Override
    public void setExternalData(CTExternalData cTExternalData) {
        this.generatedSetterHelperImpl(cTExternalData, EXTERNALDATA$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalData addNewExternalData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalData cTExternalData = null;
            cTExternalData = (CTExternalData)((Object)this.get_store().add_element_user(EXTERNALDATA$20));
            return cTExternalData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExternalData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTERNALDATA$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPrintSettings getPrintSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPrintSettings cTPrintSettings = null;
            cTPrintSettings = (CTPrintSettings)((Object)this.get_store().find_element_user(PRINTSETTINGS$22, 0));
            if (cTPrintSettings == null) {
                return null;
            }
            return cTPrintSettings;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPrintSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PRINTSETTINGS$22) != 0;
        }
    }

    @Override
    public void setPrintSettings(CTPrintSettings cTPrintSettings) {
        this.generatedSetterHelperImpl(cTPrintSettings, PRINTSETTINGS$22, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPrintSettings addNewPrintSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPrintSettings cTPrintSettings = null;
            cTPrintSettings = (CTPrintSettings)((Object)this.get_store().add_element_user(PRINTSETTINGS$22));
            return cTPrintSettings;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPrintSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PRINTSETTINGS$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRelId getUserShapes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRelId cTRelId = null;
            cTRelId = (CTRelId)((Object)this.get_store().find_element_user(USERSHAPES$24, 0));
            if (cTRelId == null) {
                return null;
            }
            return cTRelId;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetUserShapes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(USERSHAPES$24) != 0;
        }
    }

    @Override
    public void setUserShapes(CTRelId cTRelId) {
        this.generatedSetterHelperImpl(cTRelId, USERSHAPES$24, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRelId addNewUserShapes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRelId cTRelId = null;
            cTRelId = (CTRelId)((Object)this.get_store().add_element_user(USERSHAPES$24));
            return cTRelId;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetUserShapes() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(USERSHAPES$24, 0);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().find_element_user(EXTLST$26, 0));
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
            return this.get_store().count_elements(EXTLST$26) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl(cTExtensionList, EXTLST$26, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)((Object)this.get_store().add_element_user(EXTLST$26));
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
            this.get_store().remove_element(EXTLST$26, 0);
        }
    }
}

