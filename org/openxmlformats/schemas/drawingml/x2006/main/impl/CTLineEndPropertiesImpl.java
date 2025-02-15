/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndLength;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndType;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndWidth;

public class CTLineEndPropertiesImpl
extends XmlComplexContentImpl
implements CTLineEndProperties {
    private static final long serialVersionUID = 1L;
    private static final QName TYPE$0 = new QName("", "type");
    private static final QName W$2 = new QName("", "w");
    private static final QName LEN$4 = new QName("", "len");

    public CTLineEndPropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STLineEndType.Enum getType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(TYPE$0));
            if (simpleValue == null) {
                return null;
            }
            return (STLineEndType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STLineEndType xgetType() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLineEndType sTLineEndType = null;
            sTLineEndType = (STLineEndType)((Object)this.get_store().find_attribute_user(TYPE$0));
            return sTLineEndType;
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
    public void setType(STLineEndType.Enum enum_) {
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
    public void xsetType(STLineEndType sTLineEndType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLineEndType sTLineEndType2 = null;
            sTLineEndType2 = (STLineEndType)((Object)this.get_store().find_attribute_user(TYPE$0));
            if (sTLineEndType2 == null) {
                sTLineEndType2 = (STLineEndType)((Object)this.get_store().add_attribute_user(TYPE$0));
            }
            sTLineEndType2.set(sTLineEndType);
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
    public STLineEndWidth.Enum getW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(W$2));
            if (simpleValue == null) {
                return null;
            }
            return (STLineEndWidth.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STLineEndWidth xgetW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLineEndWidth sTLineEndWidth = null;
            sTLineEndWidth = (STLineEndWidth)((Object)this.get_store().find_attribute_user(W$2));
            return sTLineEndWidth;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(W$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setW(STLineEndWidth.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(W$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(W$2));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetW(STLineEndWidth sTLineEndWidth) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLineEndWidth sTLineEndWidth2 = null;
            sTLineEndWidth2 = (STLineEndWidth)((Object)this.get_store().find_attribute_user(W$2));
            if (sTLineEndWidth2 == null) {
                sTLineEndWidth2 = (STLineEndWidth)((Object)this.get_store().add_attribute_user(W$2));
            }
            sTLineEndWidth2.set(sTLineEndWidth);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetW() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(W$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STLineEndLength.Enum getLen() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LEN$4));
            if (simpleValue == null) {
                return null;
            }
            return (STLineEndLength.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STLineEndLength xgetLen() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLineEndLength sTLineEndLength = null;
            sTLineEndLength = (STLineEndLength)((Object)this.get_store().find_attribute_user(LEN$4));
            return sTLineEndLength;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLen() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(LEN$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLen(STLineEndLength.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LEN$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(LEN$4));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLen(STLineEndLength sTLineEndLength) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STLineEndLength sTLineEndLength2 = null;
            sTLineEndLength2 = (STLineEndLength)((Object)this.get_store().find_attribute_user(LEN$4));
            if (sTLineEndLength2 == null) {
                sTLineEndLength2 = (STLineEndLength)((Object)this.get_store().add_attribute_user(LEN$4));
            }
            sTLineEndLength2.set(sTLineEndLength);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLen() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(LEN$4);
        }
    }
}

