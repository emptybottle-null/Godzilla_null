/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDocPart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;

public class CTSdtDocPartImpl
extends XmlComplexContentImpl
implements CTSdtDocPart {
    private static final long serialVersionUID = 1L;
    private static final QName DOCPARTGALLERY$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "docPartGallery");
    private static final QName DOCPARTCATEGORY$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "docPartCategory");
    private static final QName DOCPARTUNIQUE$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "docPartUnique");

    public CTSdtDocPartImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString getDocPartGallery() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().find_element_user(DOCPARTGALLERY$0, 0));
            if (cTString == null) {
                return null;
            }
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDocPartGallery() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DOCPARTGALLERY$0) != 0;
        }
    }

    @Override
    public void setDocPartGallery(CTString cTString) {
        this.generatedSetterHelperImpl(cTString, DOCPARTGALLERY$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString addNewDocPartGallery() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().add_element_user(DOCPARTGALLERY$0));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDocPartGallery() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DOCPARTGALLERY$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString getDocPartCategory() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().find_element_user(DOCPARTCATEGORY$2, 0));
            if (cTString == null) {
                return null;
            }
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDocPartCategory() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DOCPARTCATEGORY$2) != 0;
        }
    }

    @Override
    public void setDocPartCategory(CTString cTString) {
        this.generatedSetterHelperImpl(cTString, DOCPARTCATEGORY$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTString addNewDocPartCategory() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTString cTString = null;
            cTString = (CTString)((Object)this.get_store().add_element_user(DOCPARTCATEGORY$2));
            return cTString;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDocPartCategory() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DOCPARTCATEGORY$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getDocPartUnique() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(DOCPARTUNIQUE$4, 0));
            if (cTOnOff == null) {
                return null;
            }
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDocPartUnique() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DOCPARTUNIQUE$4) != 0;
        }
    }

    @Override
    public void setDocPartUnique(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, DOCPARTUNIQUE$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewDocPartUnique() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(DOCPARTUNIQUE$4));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDocPartUnique() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DOCPARTUNIQUE$4, 0);
        }
    }
}

