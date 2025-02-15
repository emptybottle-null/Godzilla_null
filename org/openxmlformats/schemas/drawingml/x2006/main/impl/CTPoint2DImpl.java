/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate;

public class CTPoint2DImpl
extends XmlComplexContentImpl
implements CTPoint2D {
    private static final long serialVersionUID = 1L;
    private static final QName X$0 = new QName("", "x");
    private static final QName Y$2 = new QName("", "y");

    public CTPoint2DImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(X$0));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCoordinate xgetX() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate sTCoordinate = null;
            sTCoordinate = (STCoordinate)((Object)this.get_store().find_attribute_user(X$0));
            return sTCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setX(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(X$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(X$0));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetX(STCoordinate sTCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate sTCoordinate2 = null;
            sTCoordinate2 = (STCoordinate)((Object)this.get_store().find_attribute_user(X$0));
            if (sTCoordinate2 == null) {
                sTCoordinate2 = (STCoordinate)((Object)this.get_store().add_attribute_user(X$0));
            }
            sTCoordinate2.set(sTCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(Y$2));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STCoordinate xgetY() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate sTCoordinate = null;
            sTCoordinate = (STCoordinate)((Object)this.get_store().find_attribute_user(Y$2));
            return sTCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setY(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(Y$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(Y$2));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetY(STCoordinate sTCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STCoordinate sTCoordinate2 = null;
            sTCoordinate2 = (STCoordinate)((Object)this.get_store().find_attribute_user(Y$2));
            if (sTCoordinate2 == null) {
                sTCoordinate2 = (STCoordinate)((Object)this.get_store().add_attribute_user(Y$2));
            }
            sTCoordinate2.set(sTCoordinate);
        }
    }
}

