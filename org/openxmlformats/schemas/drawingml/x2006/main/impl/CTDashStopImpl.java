/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDashStop;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositivePercentage;

public class CTDashStopImpl
extends XmlComplexContentImpl
implements CTDashStop {
    private static final long serialVersionUID = 1L;
    private static final QName D$0 = new QName("", "d");
    private static final QName SP$2 = new QName("", "sp");

    public CTDashStopImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getD() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(D$0));
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
    public STPositivePercentage xgetD() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositivePercentage sTPositivePercentage = null;
            sTPositivePercentage = (STPositivePercentage)((Object)this.get_store().find_attribute_user(D$0));
            return sTPositivePercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setD(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(D$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(D$0));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetD(STPositivePercentage sTPositivePercentage) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositivePercentage sTPositivePercentage2 = null;
            sTPositivePercentage2 = (STPositivePercentage)((Object)this.get_store().find_attribute_user(D$0));
            if (sTPositivePercentage2 == null) {
                sTPositivePercentage2 = (STPositivePercentage)((Object)this.get_store().add_attribute_user(D$0));
            }
            sTPositivePercentage2.set(sTPositivePercentage);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SP$2));
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
    public STPositivePercentage xgetSp() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositivePercentage sTPositivePercentage = null;
            sTPositivePercentage = (STPositivePercentage)((Object)this.get_store().find_attribute_user(SP$2));
            return sTPositivePercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSp(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SP$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SP$2));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSp(STPositivePercentage sTPositivePercentage) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositivePercentage sTPositivePercentage2 = null;
            sTPositivePercentage2 = (STPositivePercentage)((Object)this.get_store().find_attribute_user(SP$2));
            if (sTPositivePercentage2 == null) {
                sTPositivePercentage2 = (STPositivePercentage)((Object)this.get_store().add_attribute_user(SP$2));
            }
            sTPositivePercentage2.set(sTPositivePercentage);
        }
    }
}

