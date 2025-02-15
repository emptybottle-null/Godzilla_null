/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellStyles
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorders;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellStyleXfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellStyles;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellXfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFills;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFonts;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmts;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyles;

public class CTStylesheetImpl
extends XmlComplexContentImpl
implements CTStylesheet {
    private static final long serialVersionUID = 1L;
    private static final QName NUMFMTS$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "numFmts");
    private static final QName FONTS$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "fonts");
    private static final QName FILLS$4 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "fills");
    private static final QName BORDERS$6 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "borders");
    private static final QName CELLSTYLEXFS$8 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "cellStyleXfs");
    private static final QName CELLXFS$10 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "cellXfs");
    private static final QName CELLSTYLES$12 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "cellStyles");
    private static final QName DXFS$14 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "dxfs");
    private static final QName TABLESTYLES$16 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "tableStyles");
    private static final QName COLORS$18 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "colors");
    private static final QName EXTLST$20 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "extLst");

    public CTStylesheetImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumFmts getNumFmts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumFmts cTNumFmts = null;
            cTNumFmts = (CTNumFmts)((Object)this.get_store().find_element_user(NUMFMTS$0, 0));
            if (cTNumFmts == null) {
                return null;
            }
            return cTNumFmts;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetNumFmts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(NUMFMTS$0) != 0;
        }
    }

    @Override
    public void setNumFmts(CTNumFmts cTNumFmts) {
        this.generatedSetterHelperImpl(cTNumFmts, NUMFMTS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNumFmts addNewNumFmts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNumFmts cTNumFmts = null;
            cTNumFmts = (CTNumFmts)((Object)this.get_store().add_element_user(NUMFMTS$0));
            return cTNumFmts;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetNumFmts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(NUMFMTS$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFonts getFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFonts cTFonts = null;
            cTFonts = (CTFonts)((Object)this.get_store().find_element_user(FONTS$2, 0));
            if (cTFonts == null) {
                return null;
            }
            return cTFonts;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FONTS$2) != 0;
        }
    }

    @Override
    public void setFonts(CTFonts cTFonts) {
        this.generatedSetterHelperImpl(cTFonts, FONTS$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFonts addNewFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFonts cTFonts = null;
            cTFonts = (CTFonts)((Object)this.get_store().add_element_user(FONTS$2));
            return cTFonts;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFonts() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FONTS$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFills getFills() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFills cTFills = null;
            cTFills = (CTFills)((Object)this.get_store().find_element_user(FILLS$4, 0));
            if (cTFills == null) {
                return null;
            }
            return cTFills;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFills() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FILLS$4) != 0;
        }
    }

    @Override
    public void setFills(CTFills cTFills) {
        this.generatedSetterHelperImpl(cTFills, FILLS$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFills addNewFills() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFills cTFills = null;
            cTFills = (CTFills)((Object)this.get_store().add_element_user(FILLS$4));
            return cTFills;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFills() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FILLS$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorders getBorders() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorders cTBorders = null;
            cTBorders = (CTBorders)((Object)this.get_store().find_element_user(BORDERS$6, 0));
            if (cTBorders == null) {
                return null;
            }
            return cTBorders;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBorders() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BORDERS$6) != 0;
        }
    }

    @Override
    public void setBorders(CTBorders cTBorders) {
        this.generatedSetterHelperImpl(cTBorders, BORDERS$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorders addNewBorders() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorders cTBorders = null;
            cTBorders = (CTBorders)((Object)this.get_store().add_element_user(BORDERS$6));
            return cTBorders;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBorders() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BORDERS$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCellStyleXfs getCellStyleXfs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCellStyleXfs cTCellStyleXfs = null;
            cTCellStyleXfs = (CTCellStyleXfs)((Object)this.get_store().find_element_user(CELLSTYLEXFS$8, 0));
            if (cTCellStyleXfs == null) {
                return null;
            }
            return cTCellStyleXfs;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCellStyleXfs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CELLSTYLEXFS$8) != 0;
        }
    }

    @Override
    public void setCellStyleXfs(CTCellStyleXfs cTCellStyleXfs) {
        this.generatedSetterHelperImpl(cTCellStyleXfs, CELLSTYLEXFS$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCellStyleXfs addNewCellStyleXfs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCellStyleXfs cTCellStyleXfs = null;
            cTCellStyleXfs = (CTCellStyleXfs)((Object)this.get_store().add_element_user(CELLSTYLEXFS$8));
            return cTCellStyleXfs;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCellStyleXfs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CELLSTYLEXFS$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCellXfs getCellXfs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCellXfs cTCellXfs = null;
            cTCellXfs = (CTCellXfs)((Object)this.get_store().find_element_user(CELLXFS$10, 0));
            if (cTCellXfs == null) {
                return null;
            }
            return cTCellXfs;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCellXfs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CELLXFS$10) != 0;
        }
    }

    @Override
    public void setCellXfs(CTCellXfs cTCellXfs) {
        this.generatedSetterHelperImpl(cTCellXfs, CELLXFS$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCellXfs addNewCellXfs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCellXfs cTCellXfs = null;
            cTCellXfs = (CTCellXfs)((Object)this.get_store().add_element_user(CELLXFS$10));
            return cTCellXfs;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCellXfs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CELLXFS$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCellStyles getCellStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCellStyles cTCellStyles = null;
            cTCellStyles = (CTCellStyles)this.get_store().find_element_user(CELLSTYLES$12, 0);
            if (cTCellStyles == null) {
                return null;
            }
            return cTCellStyles;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCellStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CELLSTYLES$12) != 0;
        }
    }

    @Override
    public void setCellStyles(CTCellStyles cTCellStyles) {
        this.generatedSetterHelperImpl((XmlObject)cTCellStyles, CELLSTYLES$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCellStyles addNewCellStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCellStyles cTCellStyles = null;
            cTCellStyles = (CTCellStyles)this.get_store().add_element_user(CELLSTYLES$12);
            return cTCellStyles;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCellStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CELLSTYLES$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDxfs getDxfs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDxfs cTDxfs = null;
            cTDxfs = (CTDxfs)((Object)this.get_store().find_element_user(DXFS$14, 0));
            if (cTDxfs == null) {
                return null;
            }
            return cTDxfs;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDxfs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DXFS$14) != 0;
        }
    }

    @Override
    public void setDxfs(CTDxfs cTDxfs) {
        this.generatedSetterHelperImpl(cTDxfs, DXFS$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTDxfs addNewDxfs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTDxfs cTDxfs = null;
            cTDxfs = (CTDxfs)((Object)this.get_store().add_element_user(DXFS$14));
            return cTDxfs;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDxfs() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DXFS$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableStyles getTableStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableStyles cTTableStyles = null;
            cTTableStyles = (CTTableStyles)((Object)this.get_store().find_element_user(TABLESTYLES$16, 0));
            if (cTTableStyles == null) {
                return null;
            }
            return cTTableStyles;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTableStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TABLESTYLES$16) != 0;
        }
    }

    @Override
    public void setTableStyles(CTTableStyles cTTableStyles) {
        this.generatedSetterHelperImpl(cTTableStyles, TABLESTYLES$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableStyles addNewTableStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableStyles cTTableStyles = null;
            cTTableStyles = (CTTableStyles)((Object)this.get_store().add_element_user(TABLESTYLES$16));
            return cTTableStyles;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTableStyles() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TABLESTYLES$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColors getColors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColors cTColors = null;
            cTColors = (CTColors)((Object)this.get_store().find_element_user(COLORS$18, 0));
            if (cTColors == null) {
                return null;
            }
            return cTColors;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetColors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(COLORS$18) != 0;
        }
    }

    @Override
    public void setColors(CTColors cTColors) {
        this.generatedSetterHelperImpl(cTColors, COLORS$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColors addNewColors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColors cTColors = null;
            cTColors = (CTColors)((Object)this.get_store().add_element_user(COLORS$18));
            return cTColors;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetColors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(COLORS$18, 0);
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
            cTExtensionList = (CTExtensionList)this.get_store().find_element_user(EXTLST$20, 0);
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
            return this.get_store().count_elements(EXTLST$20) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionList, EXTLST$20, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)this.get_store().add_element_user(EXTLST$20);
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
            this.get_store().remove_element(EXTLST$20, 0);
        }
    }
}

