/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetPr
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetProtection
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetViews
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCsPageSetup
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetViews
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetBackgroundPicture
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItems
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCsPageSetup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLegacyDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetBackgroundPicture;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItems;

public class CTChartsheetImpl
extends XmlComplexContentImpl
implements CTChartsheet {
    private static final long serialVersionUID = 1L;
    private static final QName SHEETPR$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sheetPr");
    private static final QName SHEETVIEWS$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sheetViews");
    private static final QName SHEETPROTECTION$4 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sheetProtection");
    private static final QName CUSTOMSHEETVIEWS$6 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "customSheetViews");
    private static final QName PAGEMARGINS$8 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "pageMargins");
    private static final QName PAGESETUP$10 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "pageSetup");
    private static final QName HEADERFOOTER$12 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "headerFooter");
    private static final QName DRAWING$14 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "drawing");
    private static final QName LEGACYDRAWING$16 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "legacyDrawing");
    private static final QName LEGACYDRAWINGHF$18 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "legacyDrawingHF");
    private static final QName PICTURE$20 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "picture");
    private static final QName WEBPUBLISHITEMS$22 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "webPublishItems");
    private static final QName EXTLST$24 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "extLst");

    public CTChartsheetImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartsheetPr getSheetPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartsheetPr cTChartsheetPr = null;
            cTChartsheetPr = (CTChartsheetPr)this.get_store().find_element_user(SHEETPR$0, 0);
            if (cTChartsheetPr == null) {
                return null;
            }
            return cTChartsheetPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSheetPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHEETPR$0) != 0;
        }
    }

    @Override
    public void setSheetPr(CTChartsheetPr cTChartsheetPr) {
        this.generatedSetterHelperImpl((XmlObject)cTChartsheetPr, SHEETPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartsheetPr addNewSheetPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartsheetPr cTChartsheetPr = null;
            cTChartsheetPr = (CTChartsheetPr)this.get_store().add_element_user(SHEETPR$0);
            return cTChartsheetPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSheetPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHEETPR$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartsheetViews getSheetViews() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartsheetViews cTChartsheetViews = null;
            cTChartsheetViews = (CTChartsheetViews)this.get_store().find_element_user(SHEETVIEWS$2, 0);
            if (cTChartsheetViews == null) {
                return null;
            }
            return cTChartsheetViews;
        }
    }

    @Override
    public void setSheetViews(CTChartsheetViews cTChartsheetViews) {
        this.generatedSetterHelperImpl((XmlObject)cTChartsheetViews, SHEETVIEWS$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartsheetViews addNewSheetViews() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartsheetViews cTChartsheetViews = null;
            cTChartsheetViews = (CTChartsheetViews)this.get_store().add_element_user(SHEETVIEWS$2);
            return cTChartsheetViews;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartsheetProtection getSheetProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartsheetProtection cTChartsheetProtection = null;
            cTChartsheetProtection = (CTChartsheetProtection)this.get_store().find_element_user(SHEETPROTECTION$4, 0);
            if (cTChartsheetProtection == null) {
                return null;
            }
            return cTChartsheetProtection;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSheetProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHEETPROTECTION$4) != 0;
        }
    }

    @Override
    public void setSheetProtection(CTChartsheetProtection cTChartsheetProtection) {
        this.generatedSetterHelperImpl((XmlObject)cTChartsheetProtection, SHEETPROTECTION$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTChartsheetProtection addNewSheetProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTChartsheetProtection cTChartsheetProtection = null;
            cTChartsheetProtection = (CTChartsheetProtection)this.get_store().add_element_user(SHEETPROTECTION$4);
            return cTChartsheetProtection;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSheetProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHEETPROTECTION$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomChartsheetViews getCustomSheetViews() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomChartsheetViews cTCustomChartsheetViews = null;
            cTCustomChartsheetViews = (CTCustomChartsheetViews)this.get_store().find_element_user(CUSTOMSHEETVIEWS$6, 0);
            if (cTCustomChartsheetViews == null) {
                return null;
            }
            return cTCustomChartsheetViews;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCustomSheetViews() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUSTOMSHEETVIEWS$6) != 0;
        }
    }

    @Override
    public void setCustomSheetViews(CTCustomChartsheetViews cTCustomChartsheetViews) {
        this.generatedSetterHelperImpl((XmlObject)cTCustomChartsheetViews, CUSTOMSHEETVIEWS$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomChartsheetViews addNewCustomSheetViews() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomChartsheetViews cTCustomChartsheetViews = null;
            cTCustomChartsheetViews = (CTCustomChartsheetViews)this.get_store().add_element_user(CUSTOMSHEETVIEWS$6);
            return cTCustomChartsheetViews;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCustomSheetViews() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUSTOMSHEETVIEWS$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageMargins getPageMargins() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageMargins cTPageMargins = null;
            cTPageMargins = (CTPageMargins)((Object)this.get_store().find_element_user(PAGEMARGINS$8, 0));
            if (cTPageMargins == null) {
                return null;
            }
            return cTPageMargins;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPageMargins() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PAGEMARGINS$8) != 0;
        }
    }

    @Override
    public void setPageMargins(CTPageMargins cTPageMargins) {
        this.generatedSetterHelperImpl(cTPageMargins, PAGEMARGINS$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageMargins addNewPageMargins() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageMargins cTPageMargins = null;
            cTPageMargins = (CTPageMargins)((Object)this.get_store().add_element_user(PAGEMARGINS$8));
            return cTPageMargins;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPageMargins() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PAGEMARGINS$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCsPageSetup getPageSetup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCsPageSetup cTCsPageSetup = null;
            cTCsPageSetup = (CTCsPageSetup)this.get_store().find_element_user(PAGESETUP$10, 0);
            if (cTCsPageSetup == null) {
                return null;
            }
            return cTCsPageSetup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPageSetup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PAGESETUP$10) != 0;
        }
    }

    @Override
    public void setPageSetup(CTCsPageSetup cTCsPageSetup) {
        this.generatedSetterHelperImpl((XmlObject)cTCsPageSetup, PAGESETUP$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCsPageSetup addNewPageSetup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCsPageSetup cTCsPageSetup = null;
            cTCsPageSetup = (CTCsPageSetup)this.get_store().add_element_user(PAGESETUP$10);
            return cTCsPageSetup;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPageSetup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PAGESETUP$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHeaderFooter getHeaderFooter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHeaderFooter cTHeaderFooter = null;
            cTHeaderFooter = (CTHeaderFooter)((Object)this.get_store().find_element_user(HEADERFOOTER$12, 0));
            if (cTHeaderFooter == null) {
                return null;
            }
            return cTHeaderFooter;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHeaderFooter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HEADERFOOTER$12) != 0;
        }
    }

    @Override
    public void setHeaderFooter(CTHeaderFooter cTHeaderFooter) {
        this.generatedSetterHelperImpl(cTHeaderFooter, HEADERFOOTER$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHeaderFooter addNewHeaderFooter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHeaderFooter cTHeaderFooter = null;
            cTHeaderFooter = (CTHeaderFooter)((Object)this.get_store().add_element_user(HEADERFOOTER$12));
            return cTHeaderFooter;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHeaderFooter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HEADERFOOTER$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDrawing getDrawing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDrawing cTDrawing = null;
            cTDrawing = (CTDrawing)((Object)this.get_store().find_element_user(DRAWING$14, 0));
            if (cTDrawing == null) {
                return null;
            }
            return cTDrawing;
        }
    }

    @Override
    public void setDrawing(CTDrawing cTDrawing) {
        this.generatedSetterHelperImpl(cTDrawing, DRAWING$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDrawing addNewDrawing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDrawing cTDrawing = null;
            cTDrawing = (CTDrawing)((Object)this.get_store().add_element_user(DRAWING$14));
            return cTDrawing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLegacyDrawing getLegacyDrawing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLegacyDrawing cTLegacyDrawing = null;
            cTLegacyDrawing = (CTLegacyDrawing)((Object)this.get_store().find_element_user(LEGACYDRAWING$16, 0));
            if (cTLegacyDrawing == null) {
                return null;
            }
            return cTLegacyDrawing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLegacyDrawing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LEGACYDRAWING$16) != 0;
        }
    }

    @Override
    public void setLegacyDrawing(CTLegacyDrawing cTLegacyDrawing) {
        this.generatedSetterHelperImpl(cTLegacyDrawing, LEGACYDRAWING$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLegacyDrawing addNewLegacyDrawing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLegacyDrawing cTLegacyDrawing = null;
            cTLegacyDrawing = (CTLegacyDrawing)((Object)this.get_store().add_element_user(LEGACYDRAWING$16));
            return cTLegacyDrawing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLegacyDrawing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LEGACYDRAWING$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLegacyDrawing getLegacyDrawingHF() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLegacyDrawing cTLegacyDrawing = null;
            cTLegacyDrawing = (CTLegacyDrawing)((Object)this.get_store().find_element_user(LEGACYDRAWINGHF$18, 0));
            if (cTLegacyDrawing == null) {
                return null;
            }
            return cTLegacyDrawing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLegacyDrawingHF() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(LEGACYDRAWINGHF$18) != 0;
        }
    }

    @Override
    public void setLegacyDrawingHF(CTLegacyDrawing cTLegacyDrawing) {
        this.generatedSetterHelperImpl(cTLegacyDrawing, LEGACYDRAWINGHF$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTLegacyDrawing addNewLegacyDrawingHF() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTLegacyDrawing cTLegacyDrawing = null;
            cTLegacyDrawing = (CTLegacyDrawing)((Object)this.get_store().add_element_user(LEGACYDRAWINGHF$18));
            return cTLegacyDrawing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLegacyDrawingHF() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(LEGACYDRAWINGHF$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheetBackgroundPicture getPicture() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheetBackgroundPicture cTSheetBackgroundPicture = null;
            cTSheetBackgroundPicture = (CTSheetBackgroundPicture)this.get_store().find_element_user(PICTURE$20, 0);
            if (cTSheetBackgroundPicture == null) {
                return null;
            }
            return cTSheetBackgroundPicture;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPicture() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PICTURE$20) != 0;
        }
    }

    @Override
    public void setPicture(CTSheetBackgroundPicture cTSheetBackgroundPicture) {
        this.generatedSetterHelperImpl((XmlObject)cTSheetBackgroundPicture, PICTURE$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheetBackgroundPicture addNewPicture() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheetBackgroundPicture cTSheetBackgroundPicture = null;
            cTSheetBackgroundPicture = (CTSheetBackgroundPicture)this.get_store().add_element_user(PICTURE$20);
            return cTSheetBackgroundPicture;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPicture() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PICTURE$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWebPublishItems getWebPublishItems() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWebPublishItems cTWebPublishItems = null;
            cTWebPublishItems = (CTWebPublishItems)this.get_store().find_element_user(WEBPUBLISHITEMS$22, 0);
            if (cTWebPublishItems == null) {
                return null;
            }
            return cTWebPublishItems;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWebPublishItems() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WEBPUBLISHITEMS$22) != 0;
        }
    }

    @Override
    public void setWebPublishItems(CTWebPublishItems cTWebPublishItems) {
        this.generatedSetterHelperImpl((XmlObject)cTWebPublishItems, WEBPUBLISHITEMS$22, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWebPublishItems addNewWebPublishItems() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWebPublishItems cTWebPublishItems = null;
            cTWebPublishItems = (CTWebPublishItems)this.get_store().add_element_user(WEBPUBLISHITEMS$22);
            return cTWebPublishItems;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWebPublishItems() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WEBPUBLISHITEMS$22, 0);
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
            cTExtensionList = (CTExtensionList)this.get_store().find_element_user(EXTLST$24, 0);
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
        this.generatedSetterHelperImpl((XmlObject)cTExtensionList, EXTLST$24, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)this.get_store().add_element_user(EXTLST$24);
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

