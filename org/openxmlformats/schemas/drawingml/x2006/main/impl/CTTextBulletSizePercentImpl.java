/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePercent;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextBulletSizePercent;

public class CTTextBulletSizePercentImpl
extends XmlComplexContentImpl
implements CTTextBulletSizePercent {
    private static final long serialVersionUID = 1L;
    private static final QName VAL$0 = new QName("", "val");

    public CTTextBulletSizePercentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
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
    public STTextBulletSizePercent xgetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextBulletSizePercent sTTextBulletSizePercent = null;
            sTTextBulletSizePercent = (STTextBulletSizePercent)((Object)this.get_store().find_attribute_user(VAL$0));
            return sTTextBulletSizePercent;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(VAL$0) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setVal(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(VAL$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetVal(STTextBulletSizePercent sTTextBulletSizePercent) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STTextBulletSizePercent sTTextBulletSizePercent2 = null;
            sTTextBulletSizePercent2 = (STTextBulletSizePercent)((Object)this.get_store().find_attribute_user(VAL$0));
            if (sTTextBulletSizePercent2 == null) {
                sTTextBulletSizePercent2 = (STTextBulletSizePercent)((Object)this.get_store().add_attribute_user(VAL$0));
            }
            sTTextBulletSizePercent2.set(sTTextBulletSizePercent);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetVal() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(VAL$0);
        }
    }
}

