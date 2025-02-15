/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinMiterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositivePercentage;

public class CTLineJoinMiterPropertiesImpl
extends XmlComplexContentImpl
implements CTLineJoinMiterProperties {
    private static final long serialVersionUID = 1L;
    private static final QName LIM$0 = new QName("", "lim");

    public CTLineJoinMiterPropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getLim() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LIM$0));
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
    public STPositivePercentage xgetLim() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositivePercentage sTPositivePercentage = null;
            sTPositivePercentage = (STPositivePercentage)((Object)this.get_store().find_attribute_user(LIM$0));
            return sTPositivePercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetLim() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(LIM$0) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLim(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LIM$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(LIM$0));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLim(STPositivePercentage sTPositivePercentage) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositivePercentage sTPositivePercentage2 = null;
            sTPositivePercentage2 = (STPositivePercentage)((Object)this.get_store().find_attribute_user(LIM$0));
            if (sTPositivePercentage2 == null) {
                sTPositivePercentage2 = (STPositivePercentage)((Object)this.get_store().add_attribute_user(LIM$0));
            }
            sTPositivePercentage2.set(sTPositivePercentage);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetLim() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(LIM$0);
        }
    }
}

