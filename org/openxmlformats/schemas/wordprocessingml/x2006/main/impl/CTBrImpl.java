/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBrClear;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBrType;

public class CTBrImpl
extends XmlComplexContentImpl
implements CTBr {
    private static final long serialVersionUID = 1L;
    private static final QName TYPE$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "type");
    private static final QName CLEAR$2 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "clear");

    public CTBrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBrType.Enum getType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$0));
            if (simpleValue == null) {
                return null;
            }
            return (STBrType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBrType xgetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBrType sTBrType = null;
            sTBrType = (STBrType)((Object)this.get_store().find_attribute_user(TYPE$0));
            return sTBrType;
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
    public void setType(STBrType.Enum enum_) {
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
    public void xsetType(STBrType sTBrType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBrType sTBrType2 = null;
            sTBrType2 = (STBrType)((Object)this.get_store().find_attribute_user(TYPE$0));
            if (sTBrType2 == null) {
                sTBrType2 = (STBrType)((Object)this.get_store().add_attribute_user(TYPE$0));
            }
            sTBrType2.set(sTBrType);
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

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBrClear.Enum getClear() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CLEAR$2));
            if (simpleValue == null) {
                return null;
            }
            return (STBrClear.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STBrClear xgetClear() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBrClear sTBrClear = null;
            sTBrClear = (STBrClear)((Object)this.get_store().find_attribute_user(CLEAR$2));
            return sTBrClear;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetClear() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(CLEAR$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setClear(STBrClear.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CLEAR$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CLEAR$2));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetClear(STBrClear sTBrClear) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STBrClear sTBrClear2 = null;
            sTBrClear2 = (STBrClear)((Object)this.get_store().find_attribute_user(CLEAR$2));
            if (sTBrClear2 == null) {
                sTBrClear2 = (STBrClear)((Object)this.get_store().add_attribute_user(CLEAR$2));
            }
            sTBrClear2.set(sTBrClear);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetClear() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(CLEAR$2);
        }
    }
}

