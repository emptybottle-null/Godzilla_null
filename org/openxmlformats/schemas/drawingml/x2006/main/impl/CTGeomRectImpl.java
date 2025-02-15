/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate;

public class CTGeomRectImpl
extends XmlComplexContentImpl
implements CTGeomRect {
    private static final long serialVersionUID = 1L;
    private static final QName L$0 = new QName("", "l");
    private static final QName T$2 = new QName("", "t");
    private static final QName R$4 = new QName("", "r");
    private static final QName B$6 = new QName("", "b");

    public CTGeomRectImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getL() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(L$0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getObjectValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STAdjCoordinate xgetL() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate = null;
            sTAdjCoordinate = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(L$0));
            return sTAdjCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setL(Object object) {
        Object object2 = this.monitor();
        synchronized (object2) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(L$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(L$0));
            }
            simpleValue.setObjectValue(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetL(STAdjCoordinate sTAdjCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate2 = null;
            sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(L$0));
            if (sTAdjCoordinate2 == null) {
                sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().add_attribute_user(L$0));
            }
            sTAdjCoordinate2.set(sTAdjCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(T$2));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getObjectValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STAdjCoordinate xgetT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate = null;
            sTAdjCoordinate = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(T$2));
            return sTAdjCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setT(Object object) {
        Object object2 = this.monitor();
        synchronized (object2) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(T$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(T$2));
            }
            simpleValue.setObjectValue(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetT(STAdjCoordinate sTAdjCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate2 = null;
            sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(T$2));
            if (sTAdjCoordinate2 == null) {
                sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().add_attribute_user(T$2));
            }
            sTAdjCoordinate2.set(sTAdjCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(R$4));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getObjectValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STAdjCoordinate xgetR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate = null;
            sTAdjCoordinate = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(R$4));
            return sTAdjCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setR(Object object) {
        Object object2 = this.monitor();
        synchronized (object2) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(R$4));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(R$4));
            }
            simpleValue.setObjectValue(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetR(STAdjCoordinate sTAdjCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate2 = null;
            sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(R$4));
            if (sTAdjCoordinate2 == null) {
                sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().add_attribute_user(R$4));
            }
            sTAdjCoordinate2.set(sTAdjCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(B$6));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getObjectValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STAdjCoordinate xgetB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate = null;
            sTAdjCoordinate = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(B$6));
            return sTAdjCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setB(Object object) {
        Object object2 = this.monitor();
        synchronized (object2) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(B$6));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(B$6));
            }
            simpleValue.setObjectValue(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetB(STAdjCoordinate sTAdjCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate2 = null;
            sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(B$6));
            if (sTAdjCoordinate2 == null) {
                sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().add_attribute_user(B$6));
            }
            sTAdjCoordinate2.set(sTAdjCoordinate);
        }
    }
}

