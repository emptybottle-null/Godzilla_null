/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellWatches
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTControls
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomProperties
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataConsolidate
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRanges
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTScenarios
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetBackgroundPicture
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTags
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortState
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItems
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAutoFilter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellWatches;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCols;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTConditionalFormatting;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTControls;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomProperties;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomSheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataConsolidate;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidations;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHyperlinks;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIgnoredErrors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLegacyDrawing;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMergeCells;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageBreak;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageSetup;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRanges;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTScenarios;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetBackgroundPicture;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetCalcPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetData;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetDimension;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetFormatPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetViews;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSmartTags;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSortState;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableParts;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItems;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;

public class CTWorksheetImpl
extends XmlComplexContentImpl
implements CTWorksheet {
    private static final long serialVersionUID = 1L;
    private static final QName SHEETPR$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sheetPr");
    private static final QName DIMENSION$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "dimension");
    private static final QName SHEETVIEWS$4 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sheetViews");
    private static final QName SHEETFORMATPR$6 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sheetFormatPr");
    private static final QName COLS$8 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "cols");
    private static final QName SHEETDATA$10 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sheetData");
    private static final QName SHEETCALCPR$12 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sheetCalcPr");
    private static final QName SHEETPROTECTION$14 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sheetProtection");
    private static final QName PROTECTEDRANGES$16 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "protectedRanges");
    private static final QName SCENARIOS$18 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "scenarios");
    private static final QName AUTOFILTER$20 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "autoFilter");
    private static final QName SORTSTATE$22 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sortState");
    private static final QName DATACONSOLIDATE$24 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "dataConsolidate");
    private static final QName CUSTOMSHEETVIEWS$26 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "customSheetViews");
    private static final QName MERGECELLS$28 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "mergeCells");
    private static final QName PHONETICPR$30 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "phoneticPr");
    private static final QName CONDITIONALFORMATTING$32 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "conditionalFormatting");
    private static final QName DATAVALIDATIONS$34 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "dataValidations");
    private static final QName HYPERLINKS$36 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "hyperlinks");
    private static final QName PRINTOPTIONS$38 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "printOptions");
    private static final QName PAGEMARGINS$40 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "pageMargins");
    private static final QName PAGESETUP$42 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "pageSetup");
    private static final QName HEADERFOOTER$44 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "headerFooter");
    private static final QName ROWBREAKS$46 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "rowBreaks");
    private static final QName COLBREAKS$48 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "colBreaks");
    private static final QName CUSTOMPROPERTIES$50 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "customProperties");
    private static final QName CELLWATCHES$52 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "cellWatches");
    private static final QName IGNOREDERRORS$54 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "ignoredErrors");
    private static final QName SMARTTAGS$56 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "smartTags");
    private static final QName DRAWING$58 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "drawing");
    private static final QName LEGACYDRAWING$60 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "legacyDrawing");
    private static final QName LEGACYDRAWINGHF$62 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "legacyDrawingHF");
    private static final QName PICTURE$64 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "picture");
    private static final QName OLEOBJECTS$66 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "oleObjects");
    private static final QName CONTROLS$68 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "controls");
    private static final QName WEBPUBLISHITEMS$70 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "webPublishItems");
    private static final QName TABLEPARTS$72 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "tableParts");
    private static final QName EXTLST$74 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "extLst");

    public CTWorksheetImpl(SchemaType schemaType) {
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
    public CTSheetDimension getDimension() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheetDimension cTSheetDimension = null;
            cTSheetDimension = (CTSheetDimension)((Object)this.get_store().find_element_user(DIMENSION$2, 0));
            if (cTSheetDimension == null) {
                return null;
            }
            return cTSheetDimension;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDimension() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DIMENSION$2) != 0;
        }
    }

    @Override
    public void setDimension(CTSheetDimension cTSheetDimension) {
        this.generatedSetterHelperImpl(cTSheetDimension, DIMENSION$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheetDimension addNewDimension() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheetDimension cTSheetDimension = null;
            cTSheetDimension = (CTSheetDimension)((Object)this.get_store().add_element_user(DIMENSION$2));
            return cTSheetDimension;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDimension() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DIMENSION$2, 0);
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
            cTSheetViews = (CTSheetViews)((Object)this.get_store().find_element_user(SHEETVIEWS$4, 0));
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
            return this.get_store().count_elements(SHEETVIEWS$4) != 0;
        }
    }

    @Override
    public void setSheetViews(CTSheetViews cTSheetViews) {
        this.generatedSetterHelperImpl(cTSheetViews, SHEETVIEWS$4, 0, (short)1);
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
            cTSheetViews = (CTSheetViews)((Object)this.get_store().add_element_user(SHEETVIEWS$4));
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
            this.get_store().remove_element(SHEETVIEWS$4, 0);
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
            cTSheetFormatPr = (CTSheetFormatPr)((Object)this.get_store().find_element_user(SHEETFORMATPR$6, 0));
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
            return this.get_store().count_elements(SHEETFORMATPR$6) != 0;
        }
    }

    @Override
    public void setSheetFormatPr(CTSheetFormatPr cTSheetFormatPr) {
        this.generatedSetterHelperImpl(cTSheetFormatPr, SHEETFORMATPR$6, 0, (short)1);
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
            cTSheetFormatPr = (CTSheetFormatPr)((Object)this.get_store().add_element_user(SHEETFORMATPR$6));
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
            this.get_store().remove_element(SHEETFORMATPR$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTCols> getColsList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ColsList
            extends AbstractList<CTCols> {
                ColsList() {
                }

                @Override
                public CTCols get(int n) {
                    return CTWorksheetImpl.this.getColsArray(n);
                }

                @Override
                public CTCols set(int n, CTCols cTCols) {
                    CTCols cTCols2 = CTWorksheetImpl.this.getColsArray(n);
                    CTWorksheetImpl.this.setColsArray(n, cTCols);
                    return cTCols2;
                }

                @Override
                public void add(int n, CTCols cTCols) {
                    CTWorksheetImpl.this.insertNewCols(n).set(cTCols);
                }

                @Override
                public CTCols remove(int n) {
                    CTCols cTCols = CTWorksheetImpl.this.getColsArray(n);
                    CTWorksheetImpl.this.removeCols(n);
                    return cTCols;
                }

                @Override
                public int size() {
                    return CTWorksheetImpl.this.sizeOfColsArray();
                }
            }
            return new ColsList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTCols[] getColsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(COLS$8, arrayList);
            CTCols[] cTColsArray = new CTCols[arrayList.size()];
            arrayList.toArray(cTColsArray);
            return cTColsArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCols getColsArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCols cTCols = null;
            cTCols = (CTCols)((Object)this.get_store().find_element_user(COLS$8, n));
            if (cTCols == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTCols;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfColsArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COLS$8);
        }
    }

    @Override
    public void setColsArray(CTCols[] cTColsArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTColsArray, COLS$8);
    }

    @Override
    public void setColsArray(int n, CTCols cTCols) {
        this.generatedSetterHelperImpl(cTCols, COLS$8, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCols insertNewCols(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCols cTCols = null;
            cTCols = (CTCols)((Object)this.get_store().insert_element_user(COLS$8, n));
            return cTCols;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCols addNewCols() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCols cTCols = null;
            cTCols = (CTCols)((Object)this.get_store().add_element_user(COLS$8));
            return cTCols;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeCols(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COLS$8, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheetData getSheetData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheetData cTSheetData = null;
            cTSheetData = (CTSheetData)((Object)this.get_store().find_element_user(SHEETDATA$10, 0));
            if (cTSheetData == null) {
                return null;
            }
            return cTSheetData;
        }
    }

    @Override
    public void setSheetData(CTSheetData cTSheetData) {
        this.generatedSetterHelperImpl(cTSheetData, SHEETDATA$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheetData addNewSheetData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheetData cTSheetData = null;
            cTSheetData = (CTSheetData)((Object)this.get_store().add_element_user(SHEETDATA$10));
            return cTSheetData;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheetCalcPr getSheetCalcPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheetCalcPr cTSheetCalcPr = null;
            cTSheetCalcPr = (CTSheetCalcPr)((Object)this.get_store().find_element_user(SHEETCALCPR$12, 0));
            if (cTSheetCalcPr == null) {
                return null;
            }
            return cTSheetCalcPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSheetCalcPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SHEETCALCPR$12) != 0;
        }
    }

    @Override
    public void setSheetCalcPr(CTSheetCalcPr cTSheetCalcPr) {
        this.generatedSetterHelperImpl(cTSheetCalcPr, SHEETCALCPR$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSheetCalcPr addNewSheetCalcPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSheetCalcPr cTSheetCalcPr = null;
            cTSheetCalcPr = (CTSheetCalcPr)((Object)this.get_store().add_element_user(SHEETCALCPR$12));
            return cTSheetCalcPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSheetCalcPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SHEETCALCPR$12, 0);
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
            cTSheetProtection = (CTSheetProtection)((Object)this.get_store().find_element_user(SHEETPROTECTION$14, 0));
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
            return this.get_store().count_elements(SHEETPROTECTION$14) != 0;
        }
    }

    @Override
    public void setSheetProtection(CTSheetProtection cTSheetProtection) {
        this.generatedSetterHelperImpl(cTSheetProtection, SHEETPROTECTION$14, 0, (short)1);
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
            cTSheetProtection = (CTSheetProtection)((Object)this.get_store().add_element_user(SHEETPROTECTION$14));
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
            this.get_store().remove_element(SHEETPROTECTION$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTProtectedRanges getProtectedRanges() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTProtectedRanges cTProtectedRanges = null;
            cTProtectedRanges = (CTProtectedRanges)this.get_store().find_element_user(PROTECTEDRANGES$16, 0);
            if (cTProtectedRanges == null) {
                return null;
            }
            return cTProtectedRanges;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetProtectedRanges() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PROTECTEDRANGES$16) != 0;
        }
    }

    @Override
    public void setProtectedRanges(CTProtectedRanges cTProtectedRanges) {
        this.generatedSetterHelperImpl((XmlObject)cTProtectedRanges, PROTECTEDRANGES$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTProtectedRanges addNewProtectedRanges() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTProtectedRanges cTProtectedRanges = null;
            cTProtectedRanges = (CTProtectedRanges)this.get_store().add_element_user(PROTECTEDRANGES$16);
            return cTProtectedRanges;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetProtectedRanges() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PROTECTEDRANGES$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTScenarios getScenarios() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTScenarios cTScenarios = null;
            cTScenarios = (CTScenarios)this.get_store().find_element_user(SCENARIOS$18, 0);
            if (cTScenarios == null) {
                return null;
            }
            return cTScenarios;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetScenarios() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SCENARIOS$18) != 0;
        }
    }

    @Override
    public void setScenarios(CTScenarios cTScenarios) {
        this.generatedSetterHelperImpl((XmlObject)cTScenarios, SCENARIOS$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTScenarios addNewScenarios() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTScenarios cTScenarios = null;
            cTScenarios = (CTScenarios)this.get_store().add_element_user(SCENARIOS$18);
            return cTScenarios;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetScenarios() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SCENARIOS$18, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAutoFilter getAutoFilter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAutoFilter cTAutoFilter = null;
            cTAutoFilter = (CTAutoFilter)((Object)this.get_store().find_element_user(AUTOFILTER$20, 0));
            if (cTAutoFilter == null) {
                return null;
            }
            return cTAutoFilter;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAutoFilter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AUTOFILTER$20) != 0;
        }
    }

    @Override
    public void setAutoFilter(CTAutoFilter cTAutoFilter) {
        this.generatedSetterHelperImpl(cTAutoFilter, AUTOFILTER$20, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAutoFilter addNewAutoFilter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAutoFilter cTAutoFilter = null;
            cTAutoFilter = (CTAutoFilter)((Object)this.get_store().add_element_user(AUTOFILTER$20));
            return cTAutoFilter;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAutoFilter() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AUTOFILTER$20, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSortState getSortState() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSortState cTSortState = null;
            cTSortState = (CTSortState)this.get_store().find_element_user(SORTSTATE$22, 0);
            if (cTSortState == null) {
                return null;
            }
            return cTSortState;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSortState() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SORTSTATE$22) != 0;
        }
    }

    @Override
    public void setSortState(CTSortState cTSortState) {
        this.generatedSetterHelperImpl((XmlObject)cTSortState, SORTSTATE$22, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSortState addNewSortState() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSortState cTSortState = null;
            cTSortState = (CTSortState)this.get_store().add_element_user(SORTSTATE$22);
            return cTSortState;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSortState() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SORTSTATE$22, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDataConsolidate getDataConsolidate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDataConsolidate cTDataConsolidate = null;
            cTDataConsolidate = (CTDataConsolidate)this.get_store().find_element_user(DATACONSOLIDATE$24, 0);
            if (cTDataConsolidate == null) {
                return null;
            }
            return cTDataConsolidate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDataConsolidate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DATACONSOLIDATE$24) != 0;
        }
    }

    @Override
    public void setDataConsolidate(CTDataConsolidate cTDataConsolidate) {
        this.generatedSetterHelperImpl((XmlObject)cTDataConsolidate, DATACONSOLIDATE$24, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDataConsolidate addNewDataConsolidate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDataConsolidate cTDataConsolidate = null;
            cTDataConsolidate = (CTDataConsolidate)this.get_store().add_element_user(DATACONSOLIDATE$24);
            return cTDataConsolidate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDataConsolidate() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DATACONSOLIDATE$24, 0);
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
            cTCustomSheetViews = (CTCustomSheetViews)this.get_store().find_element_user(CUSTOMSHEETVIEWS$26, 0);
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
            return this.get_store().count_elements(CUSTOMSHEETVIEWS$26) != 0;
        }
    }

    @Override
    public void setCustomSheetViews(CTCustomSheetViews cTCustomSheetViews) {
        this.generatedSetterHelperImpl((XmlObject)cTCustomSheetViews, CUSTOMSHEETVIEWS$26, 0, (short)1);
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
            cTCustomSheetViews = (CTCustomSheetViews)this.get_store().add_element_user(CUSTOMSHEETVIEWS$26);
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
            this.get_store().remove_element(CUSTOMSHEETVIEWS$26, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMergeCells getMergeCells() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMergeCells cTMergeCells = null;
            cTMergeCells = (CTMergeCells)((Object)this.get_store().find_element_user(MERGECELLS$28, 0));
            if (cTMergeCells == null) {
                return null;
            }
            return cTMergeCells;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMergeCells() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MERGECELLS$28) != 0;
        }
    }

    @Override
    public void setMergeCells(CTMergeCells cTMergeCells) {
        this.generatedSetterHelperImpl(cTMergeCells, MERGECELLS$28, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMergeCells addNewMergeCells() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMergeCells cTMergeCells = null;
            cTMergeCells = (CTMergeCells)((Object)this.get_store().add_element_user(MERGECELLS$28));
            return cTMergeCells;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMergeCells() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MERGECELLS$28, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPhoneticPr getPhoneticPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPhoneticPr cTPhoneticPr = null;
            cTPhoneticPr = (CTPhoneticPr)((Object)this.get_store().find_element_user(PHONETICPR$30, 0));
            if (cTPhoneticPr == null) {
                return null;
            }
            return cTPhoneticPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPhoneticPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PHONETICPR$30) != 0;
        }
    }

    @Override
    public void setPhoneticPr(CTPhoneticPr cTPhoneticPr) {
        this.generatedSetterHelperImpl(cTPhoneticPr, PHONETICPR$30, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPhoneticPr addNewPhoneticPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPhoneticPr cTPhoneticPr = null;
            cTPhoneticPr = (CTPhoneticPr)((Object)this.get_store().add_element_user(PHONETICPR$30));
            return cTPhoneticPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPhoneticPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PHONETICPR$30, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public List<CTConditionalFormatting> getConditionalFormattingList() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            final class ConditionalFormattingList
            extends AbstractList<CTConditionalFormatting> {
                ConditionalFormattingList() {
                }

                @Override
                public CTConditionalFormatting get(int n) {
                    return CTWorksheetImpl.this.getConditionalFormattingArray(n);
                }

                @Override
                public CTConditionalFormatting set(int n, CTConditionalFormatting cTConditionalFormatting) {
                    CTConditionalFormatting cTConditionalFormatting2 = CTWorksheetImpl.this.getConditionalFormattingArray(n);
                    CTWorksheetImpl.this.setConditionalFormattingArray(n, cTConditionalFormatting);
                    return cTConditionalFormatting2;
                }

                @Override
                public void add(int n, CTConditionalFormatting cTConditionalFormatting) {
                    CTWorksheetImpl.this.insertNewConditionalFormatting(n).set(cTConditionalFormatting);
                }

                @Override
                public CTConditionalFormatting remove(int n) {
                    CTConditionalFormatting cTConditionalFormatting = CTWorksheetImpl.this.getConditionalFormattingArray(n);
                    CTWorksheetImpl.this.removeConditionalFormatting(n);
                    return cTConditionalFormatting;
                }

                @Override
                public int size() {
                    return CTWorksheetImpl.this.sizeOfConditionalFormattingArray();
                }
            }
            return new ConditionalFormattingList();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    @Deprecated
    public CTConditionalFormatting[] getConditionalFormattingArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            ArrayList arrayList = new ArrayList();
            this.get_store().find_all_element_users(CONDITIONALFORMATTING$32, arrayList);
            CTConditionalFormatting[] cTConditionalFormattingArray = new CTConditionalFormatting[arrayList.size()];
            arrayList.toArray(cTConditionalFormattingArray);
            return cTConditionalFormattingArray;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConditionalFormatting getConditionalFormattingArray(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConditionalFormatting cTConditionalFormatting = null;
            cTConditionalFormatting = (CTConditionalFormatting)((Object)this.get_store().find_element_user(CONDITIONALFORMATTING$32, n));
            if (cTConditionalFormatting == null) {
                throw new IndexOutOfBoundsException();
            }
            return cTConditionalFormatting;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int sizeOfConditionalFormattingArray() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CONDITIONALFORMATTING$32);
        }
    }

    @Override
    public void setConditionalFormattingArray(CTConditionalFormatting[] cTConditionalFormattingArray) {
        this.check_orphaned();
        this.arraySetterHelper(cTConditionalFormattingArray, CONDITIONALFORMATTING$32);
    }

    @Override
    public void setConditionalFormattingArray(int n, CTConditionalFormatting cTConditionalFormatting) {
        this.generatedSetterHelperImpl(cTConditionalFormatting, CONDITIONALFORMATTING$32, n, (short)2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConditionalFormatting insertNewConditionalFormatting(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConditionalFormatting cTConditionalFormatting = null;
            cTConditionalFormatting = (CTConditionalFormatting)((Object)this.get_store().insert_element_user(CONDITIONALFORMATTING$32, n));
            return cTConditionalFormatting;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTConditionalFormatting addNewConditionalFormatting() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTConditionalFormatting cTConditionalFormatting = null;
            cTConditionalFormatting = (CTConditionalFormatting)((Object)this.get_store().add_element_user(CONDITIONALFORMATTING$32));
            return cTConditionalFormatting;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeConditionalFormatting(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CONDITIONALFORMATTING$32, n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDataValidations getDataValidations() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDataValidations cTDataValidations = null;
            cTDataValidations = (CTDataValidations)((Object)this.get_store().find_element_user(DATAVALIDATIONS$34, 0));
            if (cTDataValidations == null) {
                return null;
            }
            return cTDataValidations;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDataValidations() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DATAVALIDATIONS$34) != 0;
        }
    }

    @Override
    public void setDataValidations(CTDataValidations cTDataValidations) {
        this.generatedSetterHelperImpl(cTDataValidations, DATAVALIDATIONS$34, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDataValidations addNewDataValidations() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDataValidations cTDataValidations = null;
            cTDataValidations = (CTDataValidations)((Object)this.get_store().add_element_user(DATAVALIDATIONS$34));
            return cTDataValidations;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDataValidations() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DATAVALIDATIONS$34, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHyperlinks getHyperlinks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHyperlinks cTHyperlinks = null;
            cTHyperlinks = (CTHyperlinks)((Object)this.get_store().find_element_user(HYPERLINKS$36, 0));
            if (cTHyperlinks == null) {
                return null;
            }
            return cTHyperlinks;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHyperlinks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(HYPERLINKS$36) != 0;
        }
    }

    @Override
    public void setHyperlinks(CTHyperlinks cTHyperlinks) {
        this.generatedSetterHelperImpl(cTHyperlinks, HYPERLINKS$36, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHyperlinks addNewHyperlinks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHyperlinks cTHyperlinks = null;
            cTHyperlinks = (CTHyperlinks)((Object)this.get_store().add_element_user(HYPERLINKS$36));
            return cTHyperlinks;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHyperlinks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(HYPERLINKS$36, 0);
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
            cTPrintOptions = (CTPrintOptions)((Object)this.get_store().find_element_user(PRINTOPTIONS$38, 0));
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
            return this.get_store().count_elements(PRINTOPTIONS$38) != 0;
        }
    }

    @Override
    public void setPrintOptions(CTPrintOptions cTPrintOptions) {
        this.generatedSetterHelperImpl(cTPrintOptions, PRINTOPTIONS$38, 0, (short)1);
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
            cTPrintOptions = (CTPrintOptions)((Object)this.get_store().add_element_user(PRINTOPTIONS$38));
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
            this.get_store().remove_element(PRINTOPTIONS$38, 0);
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
            cTPageMargins = (CTPageMargins)((Object)this.get_store().find_element_user(PAGEMARGINS$40, 0));
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
            return this.get_store().count_elements(PAGEMARGINS$40) != 0;
        }
    }

    @Override
    public void setPageMargins(CTPageMargins cTPageMargins) {
        this.generatedSetterHelperImpl(cTPageMargins, PAGEMARGINS$40, 0, (short)1);
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
            cTPageMargins = (CTPageMargins)((Object)this.get_store().add_element_user(PAGEMARGINS$40));
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
            this.get_store().remove_element(PAGEMARGINS$40, 0);
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
            cTPageSetup = (CTPageSetup)((Object)this.get_store().find_element_user(PAGESETUP$42, 0));
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
            return this.get_store().count_elements(PAGESETUP$42) != 0;
        }
    }

    @Override
    public void setPageSetup(CTPageSetup cTPageSetup) {
        this.generatedSetterHelperImpl(cTPageSetup, PAGESETUP$42, 0, (short)1);
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
            cTPageSetup = (CTPageSetup)((Object)this.get_store().add_element_user(PAGESETUP$42));
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
            this.get_store().remove_element(PAGESETUP$42, 0);
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
            cTHeaderFooter = (CTHeaderFooter)((Object)this.get_store().find_element_user(HEADERFOOTER$44, 0));
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
            return this.get_store().count_elements(HEADERFOOTER$44) != 0;
        }
    }

    @Override
    public void setHeaderFooter(CTHeaderFooter cTHeaderFooter) {
        this.generatedSetterHelperImpl(cTHeaderFooter, HEADERFOOTER$44, 0, (short)1);
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
            cTHeaderFooter = (CTHeaderFooter)((Object)this.get_store().add_element_user(HEADERFOOTER$44));
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
            this.get_store().remove_element(HEADERFOOTER$44, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageBreak getRowBreaks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageBreak cTPageBreak = null;
            cTPageBreak = (CTPageBreak)((Object)this.get_store().find_element_user(ROWBREAKS$46, 0));
            if (cTPageBreak == null) {
                return null;
            }
            return cTPageBreak;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRowBreaks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ROWBREAKS$46) != 0;
        }
    }

    @Override
    public void setRowBreaks(CTPageBreak cTPageBreak) {
        this.generatedSetterHelperImpl(cTPageBreak, ROWBREAKS$46, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageBreak addNewRowBreaks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageBreak cTPageBreak = null;
            cTPageBreak = (CTPageBreak)((Object)this.get_store().add_element_user(ROWBREAKS$46));
            return cTPageBreak;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRowBreaks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ROWBREAKS$46, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageBreak getColBreaks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageBreak cTPageBreak = null;
            cTPageBreak = (CTPageBreak)((Object)this.get_store().find_element_user(COLBREAKS$48, 0));
            if (cTPageBreak == null) {
                return null;
            }
            return cTPageBreak;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetColBreaks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COLBREAKS$48) != 0;
        }
    }

    @Override
    public void setColBreaks(CTPageBreak cTPageBreak) {
        this.generatedSetterHelperImpl(cTPageBreak, COLBREAKS$48, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPageBreak addNewColBreaks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPageBreak cTPageBreak = null;
            cTPageBreak = (CTPageBreak)((Object)this.get_store().add_element_user(COLBREAKS$48));
            return cTPageBreak;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetColBreaks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COLBREAKS$48, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomProperties getCustomProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomProperties cTCustomProperties = null;
            cTCustomProperties = (CTCustomProperties)this.get_store().find_element_user(CUSTOMPROPERTIES$50, 0);
            if (cTCustomProperties == null) {
                return null;
            }
            return cTCustomProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCustomProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CUSTOMPROPERTIES$50) != 0;
        }
    }

    @Override
    public void setCustomProperties(CTCustomProperties cTCustomProperties) {
        this.generatedSetterHelperImpl((XmlObject)cTCustomProperties, CUSTOMPROPERTIES$50, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCustomProperties addNewCustomProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCustomProperties cTCustomProperties = null;
            cTCustomProperties = (CTCustomProperties)this.get_store().add_element_user(CUSTOMPROPERTIES$50);
            return cTCustomProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCustomProperties() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CUSTOMPROPERTIES$50, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCellWatches getCellWatches() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCellWatches cTCellWatches = null;
            cTCellWatches = (CTCellWatches)this.get_store().find_element_user(CELLWATCHES$52, 0);
            if (cTCellWatches == null) {
                return null;
            }
            return cTCellWatches;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCellWatches() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CELLWATCHES$52) != 0;
        }
    }

    @Override
    public void setCellWatches(CTCellWatches cTCellWatches) {
        this.generatedSetterHelperImpl((XmlObject)cTCellWatches, CELLWATCHES$52, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCellWatches addNewCellWatches() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCellWatches cTCellWatches = null;
            cTCellWatches = (CTCellWatches)this.get_store().add_element_user(CELLWATCHES$52);
            return cTCellWatches;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCellWatches() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CELLWATCHES$52, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTIgnoredErrors getIgnoredErrors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTIgnoredErrors cTIgnoredErrors = null;
            cTIgnoredErrors = (CTIgnoredErrors)((Object)this.get_store().find_element_user(IGNOREDERRORS$54, 0));
            if (cTIgnoredErrors == null) {
                return null;
            }
            return cTIgnoredErrors;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetIgnoredErrors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(IGNOREDERRORS$54) != 0;
        }
    }

    @Override
    public void setIgnoredErrors(CTIgnoredErrors cTIgnoredErrors) {
        this.generatedSetterHelperImpl(cTIgnoredErrors, IGNOREDERRORS$54, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTIgnoredErrors addNewIgnoredErrors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTIgnoredErrors cTIgnoredErrors = null;
            cTIgnoredErrors = (CTIgnoredErrors)((Object)this.get_store().add_element_user(IGNOREDERRORS$54));
            return cTIgnoredErrors;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetIgnoredErrors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(IGNOREDERRORS$54, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSmartTags getSmartTags() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSmartTags cTSmartTags = null;
            cTSmartTags = (CTSmartTags)this.get_store().find_element_user(SMARTTAGS$56, 0);
            if (cTSmartTags == null) {
                return null;
            }
            return cTSmartTags;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSmartTags() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SMARTTAGS$56) != 0;
        }
    }

    @Override
    public void setSmartTags(CTSmartTags cTSmartTags) {
        this.generatedSetterHelperImpl((XmlObject)cTSmartTags, SMARTTAGS$56, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSmartTags addNewSmartTags() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSmartTags cTSmartTags = null;
            cTSmartTags = (CTSmartTags)this.get_store().add_element_user(SMARTTAGS$56);
            return cTSmartTags;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSmartTags() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SMARTTAGS$56, 0);
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
            cTDrawing = (CTDrawing)((Object)this.get_store().find_element_user(DRAWING$58, 0));
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
            return this.get_store().count_elements(DRAWING$58) != 0;
        }
    }

    @Override
    public void setDrawing(CTDrawing cTDrawing) {
        this.generatedSetterHelperImpl(cTDrawing, DRAWING$58, 0, (short)1);
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
            cTDrawing = (CTDrawing)((Object)this.get_store().add_element_user(DRAWING$58));
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
            this.get_store().remove_element(DRAWING$58, 0);
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
            cTLegacyDrawing = (CTLegacyDrawing)((Object)this.get_store().find_element_user(LEGACYDRAWING$60, 0));
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
            return this.get_store().count_elements(LEGACYDRAWING$60) != 0;
        }
    }

    @Override
    public void setLegacyDrawing(CTLegacyDrawing cTLegacyDrawing) {
        this.generatedSetterHelperImpl(cTLegacyDrawing, LEGACYDRAWING$60, 0, (short)1);
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
            cTLegacyDrawing = (CTLegacyDrawing)((Object)this.get_store().add_element_user(LEGACYDRAWING$60));
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
            this.get_store().remove_element(LEGACYDRAWING$60, 0);
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
            cTLegacyDrawing = (CTLegacyDrawing)((Object)this.get_store().find_element_user(LEGACYDRAWINGHF$62, 0));
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
            return this.get_store().count_elements(LEGACYDRAWINGHF$62) != 0;
        }
    }

    @Override
    public void setLegacyDrawingHF(CTLegacyDrawing cTLegacyDrawing) {
        this.generatedSetterHelperImpl(cTLegacyDrawing, LEGACYDRAWINGHF$62, 0, (short)1);
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
            cTLegacyDrawing = (CTLegacyDrawing)((Object)this.get_store().add_element_user(LEGACYDRAWINGHF$62));
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
            this.get_store().remove_element(LEGACYDRAWINGHF$62, 0);
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
            cTSheetBackgroundPicture = (CTSheetBackgroundPicture)this.get_store().find_element_user(PICTURE$64, 0);
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
            return this.get_store().count_elements(PICTURE$64) != 0;
        }
    }

    @Override
    public void setPicture(CTSheetBackgroundPicture cTSheetBackgroundPicture) {
        this.generatedSetterHelperImpl((XmlObject)cTSheetBackgroundPicture, PICTURE$64, 0, (short)1);
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
            cTSheetBackgroundPicture = (CTSheetBackgroundPicture)this.get_store().add_element_user(PICTURE$64);
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
            this.get_store().remove_element(PICTURE$64, 0);
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
            cTOleObjects = (CTOleObjects)((Object)this.get_store().find_element_user(OLEOBJECTS$66, 0));
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
            return this.get_store().count_elements(OLEOBJECTS$66) != 0;
        }
    }

    @Override
    public void setOleObjects(CTOleObjects cTOleObjects) {
        this.generatedSetterHelperImpl(cTOleObjects, OLEOBJECTS$66, 0, (short)1);
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
            cTOleObjects = (CTOleObjects)((Object)this.get_store().add_element_user(OLEOBJECTS$66));
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
            this.get_store().remove_element(OLEOBJECTS$66, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTControls getControls() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTControls cTControls = null;
            cTControls = (CTControls)this.get_store().find_element_user(CONTROLS$68, 0);
            if (cTControls == null) {
                return null;
            }
            return cTControls;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetControls() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CONTROLS$68) != 0;
        }
    }

    @Override
    public void setControls(CTControls cTControls) {
        this.generatedSetterHelperImpl((XmlObject)cTControls, CONTROLS$68, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTControls addNewControls() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTControls cTControls = null;
            cTControls = (CTControls)this.get_store().add_element_user(CONTROLS$68);
            return cTControls;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetControls() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CONTROLS$68, 0);
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
            cTWebPublishItems = (CTWebPublishItems)this.get_store().find_element_user(WEBPUBLISHITEMS$70, 0);
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
            return this.get_store().count_elements(WEBPUBLISHITEMS$70) != 0;
        }
    }

    @Override
    public void setWebPublishItems(CTWebPublishItems cTWebPublishItems) {
        this.generatedSetterHelperImpl((XmlObject)cTWebPublishItems, WEBPUBLISHITEMS$70, 0, (short)1);
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
            cTWebPublishItems = (CTWebPublishItems)this.get_store().add_element_user(WEBPUBLISHITEMS$70);
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
            this.get_store().remove_element(WEBPUBLISHITEMS$70, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableParts getTableParts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableParts cTTableParts = null;
            cTTableParts = (CTTableParts)((Object)this.get_store().find_element_user(TABLEPARTS$72, 0));
            if (cTTableParts == null) {
                return null;
            }
            return cTTableParts;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTableParts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TABLEPARTS$72) != 0;
        }
    }

    @Override
    public void setTableParts(CTTableParts cTTableParts) {
        this.generatedSetterHelperImpl(cTTableParts, TABLEPARTS$72, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableParts addNewTableParts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableParts cTTableParts = null;
            cTTableParts = (CTTableParts)((Object)this.get_store().add_element_user(TABLEPARTS$72));
            return cTTableParts;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTableParts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TABLEPARTS$72, 0);
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
            cTExtensionList = (CTExtensionList)this.get_store().find_element_user(EXTLST$74, 0);
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
            return this.get_store().count_elements(EXTLST$74) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionList, EXTLST$74, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)this.get_store().add_element_user(EXTLST$74);
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
            this.get_store().remove_element(EXTLST$74, 0);
        }
    }
}

