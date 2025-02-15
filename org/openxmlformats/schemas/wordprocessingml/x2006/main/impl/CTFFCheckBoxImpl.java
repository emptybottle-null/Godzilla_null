/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFCheckBox;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;

public class CTFFCheckBoxImpl
extends XmlComplexContentImpl
implements CTFFCheckBox {
    private static final long serialVersionUID = 1L;
    private static final QName SIZE$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "size");
    private static final QName SIZEAUTO$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sizeAuto");
    private static final QName DEFAULT$4 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "default");
    private static final QName CHECKED$6 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "checked");

    public CTFFCheckBoxImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHpsMeasure getSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHpsMeasure cTHpsMeasure = null;
            cTHpsMeasure = (CTHpsMeasure)((Object)this.get_store().find_element_user(SIZE$0, 0));
            if (cTHpsMeasure == null) {
                return null;
            }
            return cTHpsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SIZE$0) != 0;
        }
    }

    @Override
    public void setSize(CTHpsMeasure cTHpsMeasure) {
        this.generatedSetterHelperImpl(cTHpsMeasure, SIZE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHpsMeasure addNewSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHpsMeasure cTHpsMeasure = null;
            cTHpsMeasure = (CTHpsMeasure)((Object)this.get_store().add_element_user(SIZE$0));
            return cTHpsMeasure;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSize() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SIZE$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getSizeAuto() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(SIZEAUTO$2, 0));
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
    public boolean isSetSizeAuto() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(SIZEAUTO$2) != 0;
        }
    }

    @Override
    public void setSizeAuto(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, SIZEAUTO$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewSizeAuto() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(SIZEAUTO$2));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSizeAuto() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(SIZEAUTO$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(DEFAULT$4, 0));
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
    public boolean isSetDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(DEFAULT$4) != 0;
        }
    }

    @Override
    public void setDefault(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, DEFAULT$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(DEFAULT$4));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDefault() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(DEFAULT$4, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff getChecked() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().find_element_user(CHECKED$6, 0));
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
    public boolean isSetChecked() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CHECKED$6) != 0;
        }
    }

    @Override
    public void setChecked(CTOnOff cTOnOff) {
        this.generatedSetterHelperImpl(cTOnOff, CHECKED$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOnOff addNewChecked() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOnOff cTOnOff = null;
            cTOnOff = (CTOnOff)((Object)this.get_store().add_element_user(CHECKED$6));
            return cTOnOff;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetChecked() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CHECKED$6, 0);
        }
    }
}

