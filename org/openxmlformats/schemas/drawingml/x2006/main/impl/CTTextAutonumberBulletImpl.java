/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAutonumberScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextBulletStartAtNum;

public class CTTextAutonumberBulletImpl
extends XmlComplexContentImpl
implements CTTextAutonumberBullet {
    private static final long serialVersionUID = 1L;
    private static final QName TYPE$0 = new QName("", "type");
    private static final QName STARTAT$2 = new QName("", "startAt");

    public CTTextAutonumberBulletImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextAutonumberScheme.Enum getType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$0));
            if (simpleValue == null) {
                return null;
            }
            return (STTextAutonumberScheme.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextAutonumberScheme xgetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextAutonumberScheme sTTextAutonumberScheme = null;
            sTTextAutonumberScheme = (STTextAutonumberScheme)((Object)this.get_store().find_attribute_user(TYPE$0));
            return sTTextAutonumberScheme;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setType(STTextAutonumberScheme.Enum enum_) {
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
    public void xsetType(STTextAutonumberScheme sTTextAutonumberScheme) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextAutonumberScheme sTTextAutonumberScheme2 = null;
            sTTextAutonumberScheme2 = (STTextAutonumberScheme)((Object)this.get_store().find_attribute_user(TYPE$0));
            if (sTTextAutonumberScheme2 == null) {
                sTTextAutonumberScheme2 = (STTextAutonumberScheme)((Object)this.get_store().add_attribute_user(TYPE$0));
            }
            sTTextAutonumberScheme2.set(sTTextAutonumberScheme);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getStartAt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STARTAT$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(STARTAT$2));
            }
            if (simpleValue == null) {
                return 0;
            }
            return simpleValue.getIntValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextBulletStartAtNum xgetStartAt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextBulletStartAtNum sTTextBulletStartAtNum = null;
            sTTextBulletStartAtNum = (STTextBulletStartAtNum)((Object)this.get_store().find_attribute_user(STARTAT$2));
            if (sTTextBulletStartAtNum == null) {
                sTTextBulletStartAtNum = (STTextBulletStartAtNum)this.get_default_attribute_value(STARTAT$2);
            }
            return sTTextBulletStartAtNum;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetStartAt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(STARTAT$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setStartAt(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(STARTAT$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(STARTAT$2));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetStartAt(STTextBulletStartAtNum sTTextBulletStartAtNum) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextBulletStartAtNum sTTextBulletStartAtNum2 = null;
            sTTextBulletStartAtNum2 = (STTextBulletStartAtNum)((Object)this.get_store().find_attribute_user(STARTAT$2));
            if (sTTextBulletStartAtNum2 == null) {
                sTTextBulletStartAtNum2 = (STTextBulletStartAtNum)((Object)this.get_store().add_attribute_user(STARTAT$2));
            }
            sTTextBulletStartAtNum2.set(sTTextBulletStartAtNum);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetStartAt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(STARTAT$2);
        }
    }
}

