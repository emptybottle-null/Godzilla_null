/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaModulateFixedEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositivePercentage;

public class CTAlphaModulateFixedEffectImpl
extends XmlComplexContentImpl
implements CTAlphaModulateFixedEffect {
    private static final long serialVersionUID = 1L;
    private static final QName AMT$0 = new QName("", "amt");

    public CTAlphaModulateFixedEffectImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getAmt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(AMT$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(AMT$0));
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
    public STPositivePercentage xgetAmt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositivePercentage sTPositivePercentage = null;
            sTPositivePercentage = (STPositivePercentage)((Object)this.get_store().find_attribute_user(AMT$0));
            if (sTPositivePercentage == null) {
                sTPositivePercentage = (STPositivePercentage)this.get_default_attribute_value(AMT$0);
            }
            return sTPositivePercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAmt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(AMT$0) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAmt(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(AMT$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(AMT$0));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAmt(STPositivePercentage sTPositivePercentage) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositivePercentage sTPositivePercentage2 = null;
            sTPositivePercentage2 = (STPositivePercentage)((Object)this.get_store().find_attribute_user(AMT$0));
            if (sTPositivePercentage2 == null) {
                sTPositivePercentage2 = (STPositivePercentage)((Object)this.get_store().add_attribute_user(AMT$0));
            }
            sTPositivePercentage2.set(sTPositivePercentage);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAmt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(AMT$0);
        }
    }
}

