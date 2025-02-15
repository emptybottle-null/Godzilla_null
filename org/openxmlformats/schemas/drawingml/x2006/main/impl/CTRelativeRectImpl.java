/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;

public class CTRelativeRectImpl
extends XmlComplexContentImpl
implements CTRelativeRect {
    private static final long serialVersionUID = 1L;
    private static final QName L$0 = new QName("", "l");
    private static final QName T$2 = new QName("", "t");
    private static final QName R$4 = new QName("", "r");
    private static final QName B$6 = new QName("", "b");

    public CTRelativeRectImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getL() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(L$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(L$0));
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
    public STPercentage xgetL() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage = null;
            sTPercentage = (STPercentage)((Object)this.get_store().find_attribute_user(L$0));
            if (sTPercentage == null) {
                sTPercentage = (STPercentage)this.get_default_attribute_value(L$0);
            }
            return sTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetL() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(L$0) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setL(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(L$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(L$0));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetL(STPercentage sTPercentage) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage2 = null;
            sTPercentage2 = (STPercentage)((Object)this.get_store().find_attribute_user(L$0));
            if (sTPercentage2 == null) {
                sTPercentage2 = (STPercentage)((Object)this.get_store().add_attribute_user(L$0));
            }
            sTPercentage2.set(sTPercentage);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetL() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(L$0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(T$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(T$2));
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
    public STPercentage xgetT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage = null;
            sTPercentage = (STPercentage)((Object)this.get_store().find_attribute_user(T$2));
            if (sTPercentage == null) {
                sTPercentage = (STPercentage)this.get_default_attribute_value(T$2);
            }
            return sTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(T$2) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setT(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(T$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(T$2));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetT(STPercentage sTPercentage) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage2 = null;
            sTPercentage2 = (STPercentage)((Object)this.get_store().find_attribute_user(T$2));
            if (sTPercentage2 == null) {
                sTPercentage2 = (STPercentage)((Object)this.get_store().add_attribute_user(T$2));
            }
            sTPercentage2.set(sTPercentage);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(T$2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(R$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(R$4));
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
    public STPercentage xgetR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage = null;
            sTPercentage = (STPercentage)((Object)this.get_store().find_attribute_user(R$4));
            if (sTPercentage == null) {
                sTPercentage = (STPercentage)this.get_default_attribute_value(R$4);
            }
            return sTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(R$4) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setR(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(R$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(R$4));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetR(STPercentage sTPercentage) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage2 = null;
            sTPercentage2 = (STPercentage)((Object)this.get_store().find_attribute_user(R$4));
            if (sTPercentage2 == null) {
                sTPercentage2 = (STPercentage)((Object)this.get_store().add_attribute_user(R$4));
            }
            sTPercentage2.set(sTPercentage);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(R$4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(B$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_default_attribute_value(B$6));
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
    public STPercentage xgetB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage = null;
            sTPercentage = (STPercentage)((Object)this.get_store().find_attribute_user(B$6));
            if (sTPercentage == null) {
                sTPercentage = (STPercentage)this.get_default_attribute_value(B$6);
            }
            return sTPercentage;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(B$6) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setB(int n) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(B$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(B$6));
            }
            simpleValue.setIntValue(n);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetB(STPercentage sTPercentage) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPercentage sTPercentage2 = null;
            sTPercentage2 = (STPercentage)((Object)this.get_store().find_attribute_user(B$6));
            if (sTPercentage2 == null) {
                sTPercentage2 = (STPercentage)((Object)this.get_store().add_attribute_user(B$6));
            }
            sTPercentage2.set(sTPercentage);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(B$6);
        }
    }
}

