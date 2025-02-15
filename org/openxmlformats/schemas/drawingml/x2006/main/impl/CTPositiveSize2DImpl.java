/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate;

public class CTPositiveSize2DImpl
extends XmlComplexContentImpl
implements CTPositiveSize2D {
    private static final long serialVersionUID = 1L;
    private static final QName CX$0 = new QName("", "cx");
    private static final QName CY$2 = new QName("", "cy");

    public CTPositiveSize2DImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getCx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CX$0));
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
    public STPositiveCoordinate xgetCx() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveCoordinate sTPositiveCoordinate = null;
            sTPositiveCoordinate = (STPositiveCoordinate)((Object)this.get_store().find_attribute_user(CX$0));
            return sTPositiveCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCx(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CX$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CX$0));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCx(STPositiveCoordinate sTPositiveCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveCoordinate sTPositiveCoordinate2 = null;
            sTPositiveCoordinate2 = (STPositiveCoordinate)((Object)this.get_store().find_attribute_user(CX$0));
            if (sTPositiveCoordinate2 == null) {
                sTPositiveCoordinate2 = (STPositiveCoordinate)((Object)this.get_store().add_attribute_user(CX$0));
            }
            sTPositiveCoordinate2.set(sTPositiveCoordinate);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getCy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CY$2));
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
    public STPositiveCoordinate xgetCy() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveCoordinate sTPositiveCoordinate = null;
            sTPositiveCoordinate = (STPositiveCoordinate)((Object)this.get_store().find_attribute_user(CY$2));
            return sTPositiveCoordinate;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setCy(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(CY$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(CY$2));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetCy(STPositiveCoordinate sTPositiveCoordinate) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STPositiveCoordinate sTPositiveCoordinate2 = null;
            sTPositiveCoordinate2 = (STPositiveCoordinate)((Object)this.get_store().find_attribute_user(CY$2));
            if (sTPositiveCoordinate2 == null) {
                sTPositiveCoordinate2 = (STPositiveCoordinate)((Object)this.get_store().add_attribute_user(CY$2));
            }
            sTPositiveCoordinate2.set(sTPositiveCoordinate);
        }
    }
}

