/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate;

public class CTAdjPoint2DImpl
extends XmlComplexContentImpl
implements CTAdjPoint2D {
    private static final long serialVersionUID = 1L;
    private static final QName X$0 = new QName("", "x");
    private static final QName Y$2 = new QName("", "y");

    public CTAdjPoint2DImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(X$0));
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
    public STAdjCoordinate xgetX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate = null;
            sTAdjCoordinate = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(X$0));
            return sTAdjCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setX(Object object) {
        Object object2 = this.monitor();
        synchronized (object2) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(X$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(X$0));
            }
            simpleValue.setObjectValue(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetX(STAdjCoordinate sTAdjCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate2 = null;
            sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(X$0));
            if (sTAdjCoordinate2 == null) {
                sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().add_attribute_user(X$0));
            }
            sTAdjCoordinate2.set(sTAdjCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(Y$2));
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
    public STAdjCoordinate xgetY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate = null;
            sTAdjCoordinate = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(Y$2));
            return sTAdjCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setY(Object object) {
        Object object2 = this.monitor();
        synchronized (object2) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(Y$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(Y$2));
            }
            simpleValue.setObjectValue(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetY(STAdjCoordinate sTAdjCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjCoordinate sTAdjCoordinate2 = null;
            sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().find_attribute_user(Y$2));
            if (sTAdjCoordinate2 == null) {
                sTAdjCoordinate2 = (STAdjCoordinate)((Object)this.get_store().add_attribute_user(Y$2));
            }
            sTAdjCoordinate2.set(sTAdjCoordinate);
        }
    }
}

