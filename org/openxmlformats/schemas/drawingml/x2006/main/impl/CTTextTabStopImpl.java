/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate32;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextTabAlignType;

public class CTTextTabStopImpl
extends XmlComplexContentImpl
implements CTTextTabStop {
    private static final long serialVersionUID = 1L;
    private static final QName POS$0 = new QName("", "pos");
    private static final QName ALGN$2 = new QName("", "algn");

    public CTTextTabStopImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(POS$0));
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
    public STCoordinate32 xgetPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate32 sTCoordinate32 = null;
            sTCoordinate32 = (STCoordinate32)((Object)this.get_store().find_attribute_user(POS$0));
            return sTCoordinate32;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(POS$0) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setPos(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(POS$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(POS$0));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetPos(STCoordinate32 sTCoordinate32) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate32 sTCoordinate322 = null;
            sTCoordinate322 = (STCoordinate32)((Object)this.get_store().find_attribute_user(POS$0));
            if (sTCoordinate322 == null) {
                sTCoordinate322 = (STCoordinate32)((Object)this.get_store().add_attribute_user(POS$0));
            }
            sTCoordinate322.set(sTCoordinate32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(POS$0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextTabAlignType.Enum getAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALGN$2));
            if (simpleValue == null) {
                return null;
            }
            return (STTextTabAlignType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STTextTabAlignType xgetAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextTabAlignType sTTextTabAlignType = null;
            sTTextTabAlignType = (STTextTabAlignType)((Object)this.get_store().find_attribute_user(ALGN$2));
            return sTTextTabAlignType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(ALGN$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAlgn(STTextTabAlignType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALGN$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ALGN$2));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAlgn(STTextTabAlignType sTTextTabAlignType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextTabAlignType sTTextTabAlignType2 = null;
            sTTextTabAlignType2 = (STTextTabAlignType)((Object)this.get_store().find_attribute_user(ALGN$2));
            if (sTTextTabAlignType2 == null) {
                sTTextTabAlignType2 = (STTextTabAlignType)((Object)this.get_store().add_attribute_user(ALGN$2));
            }
            sTTextTabAlignType2.set(sTTextTabAlignType);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAlgn() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(ALGN$2);
        }
    }
}

