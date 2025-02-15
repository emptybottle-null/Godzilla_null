/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPathShadeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;
import org.openxmlformats.schemas.drawingml.x2006.main.STPathShadeType;

public class CTPathShadePropertiesImpl
extends XmlComplexContentImpl
implements CTPathShadeProperties {
    private static final long serialVersionUID = 1L;
    private static final QName FILLTORECT$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "fillToRect");
    private static final QName PATH$2 = new QName("", "path");

    public CTPathShadePropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRelativeRect getFillToRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRelativeRect cTRelativeRect = null;
            cTRelativeRect = (CTRelativeRect)((Object)this.get_store().find_element_user(FILLTORECT$0, 0));
            if (cTRelativeRect == null) {
                return null;
            }
            return cTRelativeRect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFillToRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FILLTORECT$0) != 0;
        }
    }

    @Override
    public void setFillToRect(CTRelativeRect cTRelativeRect) {
        this.generatedSetterHelperImpl(cTRelativeRect, FILLTORECT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRelativeRect addNewFillToRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRelativeRect cTRelativeRect = null;
            cTRelativeRect = (CTRelativeRect)((Object)this.get_store().add_element_user(FILLTORECT$0));
            return cTRelativeRect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFillToRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FILLTORECT$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPathShadeType.Enum getPath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PATH$2));
            if (simpleValue == null) {
                return null;
            }
            return (STPathShadeType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STPathShadeType xgetPath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPathShadeType sTPathShadeType = null;
            sTPathShadeType = (STPathShadeType)((Object)this.get_store().find_attribute_user(PATH$2));
            return sTPathShadeType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(PATH$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setPath(STPathShadeType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PATH$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(PATH$2));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetPath(STPathShadeType sTPathShadeType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPathShadeType sTPathShadeType2 = null;
            sTPathShadeType2 = (STPathShadeType)((Object)this.get_store().find_attribute_user(PATH$2));
            if (sTPathShadeType2 == null) {
                sTPathShadeType2 = (STPathShadeType)((Object)this.get_store().add_attribute_user(PATH$2));
            }
            sTPathShadeType2.set(sTPathShadeType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPath() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(PATH$2);
        }
    }
}

