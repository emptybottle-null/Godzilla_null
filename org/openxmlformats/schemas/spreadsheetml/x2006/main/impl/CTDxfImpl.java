/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt;

public class CTDxfImpl
extends XmlComplexContentImpl
implements CTDxf {
    private static final long serialVersionUID = 1L;
    private static final QName FONT$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "font");
    private static final QName NUMFMT$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "numFmt");
    private static final QName FILL$4 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "fill");
    private static final QName ALIGNMENT$6 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "alignment");
    private static final QName BORDER$8 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "border");
    private static final QName PROTECTION$10 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "protection");
    private static final QName EXTLST$12 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "extLst");

    public CTDxfImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFont getFont() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFont cTFont = null;
            cTFont = (CTFont)((Object)this.get_store().find_element_user(FONT$0, 0));
            if (cTFont == null) {
                return null;
            }
            return cTFont;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFont() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FONT$0) != 0;
        }
    }

    @Override
    public void setFont(CTFont cTFont) {
        this.generatedSetterHelperImpl(cTFont, FONT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFont addNewFont() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFont cTFont = null;
            cTFont = (CTFont)((Object)this.get_store().add_element_user(FONT$0));
            return cTFont;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFont() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FONT$0, 0);
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
            cTNumFmt = (CTNumFmt)((Object)this.get_store().find_element_user(NUMFMT$2, 0));
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
            return this.get_store().count_elements(NUMFMT$2) != 0;
        }
    }

    @Override
    public void setNumFmt(CTNumFmt cTNumFmt) {
        this.generatedSetterHelperImpl(cTNumFmt, NUMFMT$2, 0, (short)1);
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
            cTNumFmt = (CTNumFmt)((Object)this.get_store().add_element_user(NUMFMT$2));
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
            this.get_store().remove_element(NUMFMT$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFill getFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFill cTFill = null;
            cTFill = (CTFill)((Object)this.get_store().find_element_user(FILL$4, 0));
            if (cTFill == null) {
                return null;
            }
            return cTFill;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FILL$4) != 0;
        }
    }

    @Override
    public void setFill(CTFill cTFill) {
        this.generatedSetterHelperImpl(cTFill, FILL$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFill addNewFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFill cTFill = null;
            cTFill = (CTFill)((Object)this.get_store().add_element_user(FILL$4));
            return cTFill;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FILL$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCellAlignment getAlignment() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCellAlignment cTCellAlignment = null;
            cTCellAlignment = (CTCellAlignment)((Object)this.get_store().find_element_user(ALIGNMENT$6, 0));
            if (cTCellAlignment == null) {
                return null;
            }
            return cTCellAlignment;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAlignment() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(ALIGNMENT$6) != 0;
        }
    }

    @Override
    public void setAlignment(CTCellAlignment cTCellAlignment) {
        this.generatedSetterHelperImpl(cTCellAlignment, ALIGNMENT$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCellAlignment addNewAlignment() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCellAlignment cTCellAlignment = null;
            cTCellAlignment = (CTCellAlignment)((Object)this.get_store().add_element_user(ALIGNMENT$6));
            return cTCellAlignment;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAlignment() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(ALIGNMENT$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder getBorder() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)((Object)this.get_store().find_element_user(BORDER$8, 0));
            if (cTBorder == null) {
                return null;
            }
            return cTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBorder() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BORDER$8) != 0;
        }
    }

    @Override
    public void setBorder(CTBorder cTBorder) {
        this.generatedSetterHelperImpl(cTBorder, BORDER$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBorder addNewBorder() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBorder cTBorder = null;
            cTBorder = (CTBorder)((Object)this.get_store().add_element_user(BORDER$8));
            return cTBorder;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBorder() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BORDER$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCellProtection getProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCellProtection cTCellProtection = null;
            cTCellProtection = (CTCellProtection)((Object)this.get_store().find_element_user(PROTECTION$10, 0));
            if (cTCellProtection == null) {
                return null;
            }
            return cTCellProtection;
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
            return this.get_store().count_elements(PROTECTION$10) != 0;
        }
    }

    @Override
    public void setProtection(CTCellProtection cTCellProtection) {
        this.generatedSetterHelperImpl(cTCellProtection, PROTECTION$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCellProtection addNewProtection() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCellProtection cTCellProtection = null;
            cTCellProtection = (CTCellProtection)((Object)this.get_store().add_element_user(PROTECTION$10));
            return cTCellProtection;
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
            this.get_store().remove_element(PROTECTION$10, 0);
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
            cTExtensionList = (CTExtensionList)this.get_store().find_element_user(EXTLST$12, 0);
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
            return this.get_store().count_elements(EXTLST$12) != 0;
        }
    }

    @Override
    public void setExtLst(CTExtensionList cTExtensionList) {
        this.generatedSetterHelperImpl((XmlObject)cTExtensionList, EXTLST$12, 0, (short)1);
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
            cTExtensionList = (CTExtensionList)this.get_store().add_element_user(EXTLST$12);
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
            this.get_store().remove_element(EXTLST$12, 0);
        }
    }
}

