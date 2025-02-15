/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.STAdjAngle
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSite;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjAngle;

public class CTConnectionSiteImpl
extends XmlComplexContentImpl
implements CTConnectionSite {
    private static final long serialVersionUID = 1L;
    private static final QName POS$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "pos");
    private static final QName ANG$2 = new QName("", "ang");

    public CTConnectionSiteImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAdjPoint2D getPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAdjPoint2D cTAdjPoint2D = null;
            cTAdjPoint2D = (CTAdjPoint2D)((Object)this.get_store().find_element_user(POS$0, 0));
            if (cTAdjPoint2D == null) {
                return null;
            }
            return cTAdjPoint2D;
        }
    }

    @Override
    public void setPos(CTAdjPoint2D cTAdjPoint2D) {
        this.generatedSetterHelperImpl(cTAdjPoint2D, POS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAdjPoint2D addNewPos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAdjPoint2D cTAdjPoint2D = null;
            cTAdjPoint2D = (CTAdjPoint2D)((Object)this.get_store().add_element_user(POS$0));
            return cTAdjPoint2D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object getAng() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ANG$2));
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
    public STAdjAngle xgetAng() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjAngle sTAdjAngle = null;
            sTAdjAngle = (STAdjAngle)this.get_store().find_attribute_user(ANG$2);
            return sTAdjAngle;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAng(Object object) {
        Object object2 = this.monitor();
        synchronized (object2) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ANG$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ANG$2));
            }
            simpleValue.setObjectValue(object);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAng(STAdjAngle sTAdjAngle) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STAdjAngle sTAdjAngle2 = null;
            sTAdjAngle2 = (STAdjAngle)this.get_store().find_attribute_user(ANG$2);
            if (sTAdjAngle2 == null) {
                sTAdjAngle2 = (STAdjAngle)this.get_store().add_attribute_user(ANG$2);
            }
            sTAdjAngle2.set((XmlObject)sTAdjAngle);
        }
    }
}

