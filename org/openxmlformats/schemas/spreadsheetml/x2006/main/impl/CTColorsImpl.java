/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMRUColors
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIndexedColors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMRUColors;

public class CTColorsImpl
extends XmlComplexContentImpl
implements CTColors {
    private static final long serialVersionUID = 1L;
    private static final QName INDEXEDCOLORS$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "indexedColors");
    private static final QName MRUCOLORS$2 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "mruColors");

    public CTColorsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTIndexedColors getIndexedColors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTIndexedColors cTIndexedColors = null;
            cTIndexedColors = (CTIndexedColors)((Object)this.get_store().find_element_user(INDEXEDCOLORS$0, 0));
            if (cTIndexedColors == null) {
                return null;
            }
            return cTIndexedColors;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetIndexedColors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(INDEXEDCOLORS$0) != 0;
        }
    }

    @Override
    public void setIndexedColors(CTIndexedColors cTIndexedColors) {
        this.generatedSetterHelperImpl(cTIndexedColors, INDEXEDCOLORS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTIndexedColors addNewIndexedColors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTIndexedColors cTIndexedColors = null;
            cTIndexedColors = (CTIndexedColors)((Object)this.get_store().add_element_user(INDEXEDCOLORS$0));
            return cTIndexedColors;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetIndexedColors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(INDEXEDCOLORS$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMRUColors getMruColors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMRUColors cTMRUColors = null;
            cTMRUColors = (CTMRUColors)this.get_store().find_element_user(MRUCOLORS$2, 0);
            if (cTMRUColors == null) {
                return null;
            }
            return cTMRUColors;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetMruColors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(MRUCOLORS$2) != 0;
        }
    }

    @Override
    public void setMruColors(CTMRUColors cTMRUColors) {
        this.generatedSetterHelperImpl((XmlObject)cTMRUColors, MRUCOLORS$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTMRUColors addNewMruColors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTMRUColors cTMRUColors = null;
            cTMRUColors = (CTMRUColors)this.get_store().add_element_user(MRUCOLORS$2);
            return cTMRUColors;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetMruColors() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(MRUCOLORS$2, 0);
        }
    }
}

