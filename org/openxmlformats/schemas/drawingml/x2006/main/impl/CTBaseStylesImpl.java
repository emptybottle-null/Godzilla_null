/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix;

public class CTBaseStylesImpl
extends XmlComplexContentImpl
implements CTBaseStyles {
    private static final long serialVersionUID = 1L;
    private static final QName CLRSCHEME$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "clrScheme");
    private static final QName FONTSCHEME$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "fontScheme");
    private static final QName FMTSCHEME$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "fmtScheme");
    private static final QName EXTLST$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extLst");

    public CTBaseStylesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorScheme getClrScheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorScheme cTColorScheme = null;
            cTColorScheme = (CTColorScheme)((Object)this.get_store().find_element_user(CLRSCHEME$0, 0));
            if (cTColorScheme == null) {
                return null;
            }
            return cTColorScheme;
        }
    }

    @Override
    public void setClrScheme(CTColorScheme cTColorScheme) {
        this.generatedSetterHelperImpl(cTColorScheme, CLRSCHEME$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTColorScheme addNewClrScheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTColorScheme cTColorScheme = null;
            cTColorScheme = (CTColorScheme)((Object)this.get_store().add_element_user(CLRSCHEME$0));
            return cTColorScheme;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFontScheme getFontScheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFontScheme cTFontScheme = null;
            cTFontScheme = (CTFontScheme)((Object)this.get_store().find_element_user(FONTSCHEME$2, 0));
            if (cTFontScheme == null) {
                return null;
            }
            return cTFontScheme;
        }
    }

    @Override
    public void setFontScheme(CTFontScheme cTFontScheme) {
        this.generatedSetterHelperImpl(cTFontScheme, FONTSCHEME$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFontScheme addNewFontScheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFontScheme cTFontScheme = null;
            cTFontScheme = (CTFontScheme)((Object)this.get_store().add_element_user(FONTSCHEME$2));
            return cTFontScheme;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStyleMatrix getFmtScheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStyleMatrix cTStyleMatrix = null;
            cTStyleMatrix = (CTStyleMatrix)((Object)this.get_store().find_element_user(FMTSCHEME$4, 0));
            if (cTStyleMatrix == null) {
                return null;
            }
            return cTStyleMatrix;
        }
    }

    @Override
    public void setFmtScheme(CTStyleMatrix cTStyleMatrix) {
        this.generatedSetterHelperImpl(cTStyleMatrix, FMTSCHEME$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStyleMatrix addNewFmtScheme() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStyleMatrix cTStyleMatrix = null;
            cTStyleMatrix = (CTStyleMatrix)((Object)this.get_store().add_element_user(FMTSCHEME$4));
            return cTStyleMatrix;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtensionList cTOfficeArtExtensionList = null;
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().find_element_user(EXTLST$6, 0));
            if (cTOfficeArtExtensionList == null) {
                return null;
            }
            return cTOfficeArtExtensionList;
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
            return this.get_store().count_elements(EXTLST$6) != 0;
        }
    }

    @Override
    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        this.generatedSetterHelperImpl(cTOfficeArtExtensionList, EXTLST$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtensionList cTOfficeArtExtensionList = null;
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().add_element_user(EXTLST$6));
            return cTOfficeArtExtensionList;
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
            this.get_store().remove_element(EXTLST$6, 0);
        }
    }
}

