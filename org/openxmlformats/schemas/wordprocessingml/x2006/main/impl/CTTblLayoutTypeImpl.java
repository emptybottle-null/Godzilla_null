/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLayoutType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblLayoutType;

public class CTTblLayoutTypeImpl
extends XmlComplexContentImpl
implements CTTblLayoutType {
    private static final long serialVersionUID = 1L;
    private static final QName TYPE$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "type");

    public CTTblLayoutTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTblLayoutType.Enum getType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$0));
            if (simpleValue == null) {
                return null;
            }
            return (STTblLayoutType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTblLayoutType xgetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTblLayoutType sTTblLayoutType = null;
            sTTblLayoutType = (STTblLayoutType)((Object)this.get_store().find_attribute_user(TYPE$0));
            return sTTblLayoutType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(TYPE$0) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setType(STTblLayoutType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TYPE$0));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetType(STTblLayoutType sTTblLayoutType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTblLayoutType sTTblLayoutType2 = null;
            sTTblLayoutType2 = (STTblLayoutType)((Object)this.get_store().find_attribute_user(TYPE$0));
            if (sTTblLayoutType2 == null) {
                sTTblLayoutType2 = (STTblLayoutType)((Object)this.get_store().add_attribute_user(TYPE$0));
            }
            sTTblLayoutType2.set(sTTblLayoutType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(TYPE$0);
        }
    }
}

