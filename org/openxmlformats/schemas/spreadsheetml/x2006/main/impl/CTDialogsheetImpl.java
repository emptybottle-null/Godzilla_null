/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDialogsheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLegacyDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageSetup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetFormatPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetViews;

public class CTDialogsheetImpl
extends XmlComplexContentImpl
implements CTDialogsheet {
    private static final long serialVersionUID = 1L;
    private static final QName SHEETPR$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sheetPr");
    private static final QName SHEETVIEWS$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sheetViews");
    private static final QName SHEETFORMATPR$4 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sheetFormatPr");
    private static final QName SHEETPROTECTION$6 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sheetProtection");
    private static final QName CUSTOMSHEETVIEWS$8 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "customSheetViews");
    private static final QName PRINTOPTIONS$10 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "printOptions");
    private static final QName PAGEMARGINS$12 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "pageMargins");
    private static final QName PAGESETUP$14 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "pageSetup");
    private static final QName HEADERFOOTER$16 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "headerFooter");
    private static final QName DRAWING$18 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "drawing");
    private static final QName LEGACYDRAWING$20 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "legacyDrawing");
    private static final QName LEGACYDRAWINGHF$22 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "legacyDrawingHF");
    private static final QName OLEOBJECTS$24 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "oleObjects");
    private static final QName EXTLST$26 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "extLst");

    public CTDialogsheetImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheetPr getSheetPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheetPr cTSheetPr = null;
            cTSheetPr = (CTSheetPr)((Object)this.get_store().find_element_user(SHEETPR$0, 0));
            if (cTSheetPr == null) {
                return null;
            }
            return cTSheetPr;
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
    public void setSheetPr(CTSheetPr cTSheetPr) {
        this.generatedSetterHelperImpl(cTSheetPr, SHEETPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheetPr addNewSheetPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheetPr cTSheetPr = null;
            cTSheetPr = (CTSheetPr)((Object)this.get_store().add_element_user(SHEETPR$0));
            return cTSheetPr;
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
    public CTSheetViews getSheetViews() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheetViews cTSheetViews = null;
            cTSheetViews = (CTSheetViews)((Object)this.get_store().find_element_user(SHEETVIEWS$2, 0));
            if (cTSheetViews == null) {
                return null;
            }
            return cTSheetViews;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSheetViews() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHEETVIEWS$2) != 0;
        }
    }

    @Override
    public void setSheetViews(CTSheetViews cTSheetViews) {
        this.generatedSetterHelperImpl(cTSheetViews, SHEETVIEWS$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheetViews addNewSheetViews() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheetViews cTSheetViews = null;
            cTSheetViews = (CTSheetViews)((Object)this.get_store().add_element_user(SHEETVIEWS$2));
            return cTSheetViews;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSheetViews() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHEETVIEWS$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheetFormatPr getSheetFormatPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheetFormatPr cTSheetFormatPr = null;
            cTSheetFormatPr = (CTSheetFormatPr)((Object)this.get_store().find_element_user(SHEETFORMATPR$4, 0));
            if (cTSheetFormatPr == null) {
                return null;
            }
            return cTSheetFormatPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSheetFormatPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHEETFORMATPR$4) != 0;
        }
    }

    @Override
    public void setSheetFormatPr(CTSheetFormatPr cTSheetFormatPr) {
        this.generatedSetterHelperImpl(cTSheetFormatPr, SHEETFORMATPR$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheetFormatPr addNewSheetFormatPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheetFormatPr cTSheetFormatPr = null;
            cTSheetFormatPr = (CTSheetFormatPr)((Object)this.get_store().add_element_user(SHEETFORMATPR$4));
            return cTSheetFormatPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSheetFormatPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHEETFORMATPR$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheetProtection getSheetProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheetProtection cTSheetProtection = null;
            cTSheetProtection = (CTSheetProtection)((Object)this.get_store().find_element_user(SHEETPROTECTION$6, 0));
            if (cTSheetProtection == null) {
                return null;
            }
            return cTSheetProtection;
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
            return this.get_store().count_elements(SHEETPROTECTION$6) != 0;
        }
    }

    @Override
    public void setSheetProtection(CTSheetProtection cTSheetProtection) {
        this.generatedSetterHelperImpl(cTSheetProtection, SHEETPROTECTION$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheetProtection addNewSheetProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheetProtection cTSheetProtection = null;
            cTSheetProtection = (CTSheetProtection)((Object)this.get_store().add_element_user(SHEETPROTECTION$6));
            return cTSheetProtection;
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
            this.get_store().remove_element(SHEETPROTECTION$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomSheetViews getCustomSheetViews() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomSheetViews cTCustomSheetViews = null;
            cTCustomSheetViews = (CTCustomSheetViews)this.get_store().find_element_user(CUSTOMSHEETVIEWS$8, 0);
            if (cTCustomSheetViews == null) {
                return null;
            }
            return cTCustomSheetViews;
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
            return this.get_store().count_elements(CUSTOMSHEETVIEWS$8) != 0;
        }
    }

    @Override
    public void setCustomSheetViews(CTCustomSheetViews cTCustomSheetViews) {
        this.generatedSetterHelperImpl((XmlObject)cTCustomSheetViews, CUSTOMSHEETVIEWS$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomSheetViews addNewCustomSheetViews() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomSheetViews cTCustomSheetViews = null;
            cTCustomSheetViews = (CTCustomSheetViews)this.get_store().add_element_user(CUSTOMSHEETVIEWS$8);
            return cTCustomSheetViews;
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
            this.get_store().remove_element(CUSTOMSHEETVIEWS$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPrintOptions getPrintOptions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPrintOptions cTPrintOptions = null;
            cTPrintOptions = (CTPrintOptions)((Object)this.get_store().find_element_user(PRINTOPTIONS$10, 0));
            if (cTPrintOptions == null) {
                return null;
            }
            return cTPrintOptions;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPrintOptions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PRINTOPTIONS$10) != 0;
        }
    }

    @Override
    public void setPrintOptions(CTPrintOptions cTPrintOptions) {
        this.generatedSetterHelperImpl(cTPrintOptions, PRINTOPTIONS$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPrintOptions addNewPrintOptions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPrintOptions cTPrintOptions = null;
            cTPrintOptions = (CTPrintOptions)((Object)this.get_store().add_element_user(PRINTOPTIONS$10));
            return cTPrintOptions;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPrintOptions() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PRINTOPTIONS$10, 0);
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
            cTPageMargins = (CTPageMargins)((Object)this.get_store().find_element_user(PAGEMARGINS$12, 0));
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
            return this.get_store().count_elements(PAGEMARGINS$12) != 0;
        }
    }

    @Override
    public void setPageMargins(CTPageMargins cTPageMargins) {
        this.generatedSetterHelperImpl(cTPageMargins, PAGEMARGINS$12, 0, (short)1);
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
            cTPageMargins = (CTPageMargins)((Object)this.get_store().add_element_user(PAGEMARGINS$12));
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
            this.get_store().remove_element(PAGEMARGINS$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageSetup getPageSetup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageSetup cTPageSetup = null;
            cTPageSetup = (CTPageSetup)((Object)this.get_store().find_element_user(PAGESETUP$14, 0));
            if (cTPageSetup == null) {
                return null;
            }
            return cTPageSetup;
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
            return this.get_store().count_elements(PAGESETUP$14) != 0;
        }
    }

    @Override
    public void setPageSetup(CTPageSetup cTPageSetup) {
        this.generatedSetterHelperImpl(cTPageSetup, PAGESETUP$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageSetup addNewPageSetup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageSetup cTPageSetup = null;
            cTPageSetup = (CTPageSetup)((Object)this.get_store().add_element_user(PAGESETUP$14));
            return cTPageSetup;
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
            this.get_store().remove_element(PAGESETUP$14, 0);
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
            cTHeaderFooter = (CTHeaderFooter)((Object)this.get_store().find_element_user(HEADERFOOTER$16, 0));
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
            return this.get_store().count_elements(HEADERFOOTER$16) != 0;
        }
    }

    @Override
    public void setHeaderFooter(CTHeaderFooter cTHeaderFooter) {
        this.generatedSetterHelperImpl(cTHeaderFooter, HEADERFOOTER$16, 0, (short)1);
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
            cTHeaderFooter = (CTHeaderFooter)((Object)this.get_store().add_element_user(HEADERFOOTER$16));
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
            this.get_store().remove_element(HEADERFOOTER$16, 0);
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
            cTDrawing = (CTDrawing)((Object)this.get_store().find_element_user(DRAWING$18, 0));
            if (cTDrawing == null) {
                return null;
            }
            return cTDrawing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDrawing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DRAWING$18) != 0;
        }
    }

    @Override
    public void setDrawing(CTDrawing cTDrawing) {
        this.generatedSetterHelperImpl(cTDrawing, DRAWING$18, 0, (short)1);
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
            cTDrawing = (CTDrawing)((Object)this.get_store().add_element_user(DRAWING$18));
            return cTDrawing;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDrawing() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DRAWING$18, 0);
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
            cTLegacyDrawing = (CTLegacyDrawing)((Object)this.get_store().find_element_user(LEGACYDRAWING$20, 0));
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
            return this.get_store().count_elements(LEGACYDRAWING$20) != 0;
        }
    }

    @Override
    public void setLegacyDrawing(CTLegacyDrawing cTLegacyDrawing) {
        this.generatedSetterHelperImpl(cTLegacyDrawing, LEGACYDRAWING$20, 0, (short)1);
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
            cTLegacyDrawing = (CTLegacyDrawing)((Object)this.get_store().add_element_user(LEGACYDRAWING$20));
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
            this.get_store().remove_element(LEGACYDRAWING$20, 0);
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
            cTLegacyDrawing = (CTLegacyDrawing)((Object)this.get_store().find_element_user(LEGACYDRAWINGHF$22, 0));
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
            return this.get_store().count_elements(LEGACYDRAWINGHF$22) != 0;
        }
    }

    @Override
    public void setLegacyDrawingHF(CTLegacyDrawing cTLegacyDrawing) {
        this.generatedSetterHelperImpl(cTLegacyDrawing, LEGACYDRAWINGHF$22, 0, (short)1);
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
            cTLegacyDrawing = (CTLegacyDrawing)((Object)this.get_store().add_element_user(LEGACYDRAWINGHF$22));
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
            this.get_store().remove_element(LEGACYDRAWINGHF$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOleObjects getOleObjects() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOleObjects cTOleObjects = null;
            cTOleObjects = (CTOleObjects)((Object)this.get_store().find_element_user(OLEOBJECTS$24, 0));
            if (cTOleObjects == null) {
                return null;
            }
            return cTOleObjects;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetOleObjects() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(OLEOBJECTS$24) != 0;
        }
    }

    @Override
    public void setOleObjects(CTOleObjects cTOleObjects) {
        this.generatedSetterHelperImpl(cTOleObjects, OLEOBJECTS$24, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOleObjects addNewOleObjects() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOleObjects cTOleObjects = null;
            cTOleObjects = (CTOleObjects)((Object)this.get_store().add_element_user(OLEOBJECTS$24));
            return cTOleObjects;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetOleObjects() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(OLEOBJECTS$24, 0);
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
            cTExtensionList = (CTExtensionList)this.get_store().find_element_user(EXTLST$26, 0);
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
        this.generatedSetterHelperImpl((XmlObject)cTExtensionList, EXTLST$26, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)this.get_store().add_element_user(EXTLST$26);
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

