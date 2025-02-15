/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.STSlideSizeType
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize;
import org.openxmlformats.schemas.presentationml.x2006.main.STSlideSizeCoordinate;
import org.openxmlformats.schemas.presentationml.x2006.main.STSlideSizeType;

public class CTSlideSizeImpl
extends XmlComplexContentImpl
implements CTSlideSize {
    private static final long serialVersionUID = 1L;
    private static final QName CX$0 = new QName("", "cx");
    private static final QName CY$2 = new QName("", "cy");
    private static final QName TYPE$4 = new QName("", "type");

    public CTSlideSizeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getCx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CX$0));
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
    public STSlideSizeCoordinate xgetCx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSlideSizeCoordinate sTSlideSizeCoordinate = null;
            sTSlideSizeCoordinate = (STSlideSizeCoordinate)((Object)this.get_store().find_attribute_user(CX$0));
            return sTSlideSizeCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCx(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CX$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CX$0));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCx(STSlideSizeCoordinate sTSlideSizeCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSlideSizeCoordinate sTSlideSizeCoordinate2 = null;
            sTSlideSizeCoordinate2 = (STSlideSizeCoordinate)((Object)this.get_store().find_attribute_user(CX$0));
            if (sTSlideSizeCoordinate2 == null) {
                sTSlideSizeCoordinate2 = (STSlideSizeCoordinate)((Object)this.get_store().add_attribute_user(CX$0));
            }
            sTSlideSizeCoordinate2.set(sTSlideSizeCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getCy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CY$2));
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
    public STSlideSizeCoordinate xgetCy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSlideSizeCoordinate sTSlideSizeCoordinate = null;
            sTSlideSizeCoordinate = (STSlideSizeCoordinate)((Object)this.get_store().find_attribute_user(CY$2));
            return sTSlideSizeCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCy(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CY$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CY$2));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCy(STSlideSizeCoordinate sTSlideSizeCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSlideSizeCoordinate sTSlideSizeCoordinate2 = null;
            sTSlideSizeCoordinate2 = (STSlideSizeCoordinate)((Object)this.get_store().find_attribute_user(CY$2));
            if (sTSlideSizeCoordinate2 == null) {
                sTSlideSizeCoordinate2 = (STSlideSizeCoordinate)((Object)this.get_store().add_attribute_user(CY$2));
            }
            sTSlideSizeCoordinate2.set(sTSlideSizeCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STSlideSizeType.Enum getType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(TYPE$4));
            }
            if (simpleValue == null) {
                return null;
            }
            return (STSlideSizeType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STSlideSizeType xgetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSlideSizeType sTSlideSizeType = null;
            sTSlideSizeType = (STSlideSizeType)this.get_store().find_attribute_user(TYPE$4);
            if (sTSlideSizeType == null) {
                sTSlideSizeType = (STSlideSizeType)this.get_default_attribute_value(TYPE$4);
            }
            return sTSlideSizeType;
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
            return this.get_store().find_attribute_user(TYPE$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setType(STSlideSizeType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(TYPE$4));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetType(STSlideSizeType sTSlideSizeType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STSlideSizeType sTSlideSizeType2 = null;
            sTSlideSizeType2 = (STSlideSizeType)this.get_store().find_attribute_user(TYPE$4);
            if (sTSlideSizeType2 == null) {
                sTSlideSizeType2 = (STSlideSizeType)this.get_store().add_attribute_user(TYPE$4);
            }
            sTSlideSizeType2.set((XmlObject)sTSlideSizeType);
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
            this.get_store().remove_attribute(TYPE$4);
        }
    }
}

