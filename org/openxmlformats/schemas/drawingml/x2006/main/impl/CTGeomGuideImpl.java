/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide;
import org.openxmlformats.schemas.drawingml.x2006.main.STGeomGuideFormula;
import org.openxmlformats.schemas.drawingml.x2006.main.STGeomGuideName;

public class CTGeomGuideImpl
extends XmlComplexContentImpl
implements CTGeomGuide {
    private static final long serialVersionUID = 1L;
    private static final QName NAME$0 = new QName("", "name");
    private static final QName FMLA$2 = new QName("", "fmla");

    public CTGeomGuideImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$0));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STGeomGuideName xgetName() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STGeomGuideName sTGeomGuideName = null;
            sTGeomGuideName = (STGeomGuideName)((Object)this.get_store().find_attribute_user(NAME$0));
            return sTGeomGuideName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setName(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(NAME$0));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(NAME$0));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetName(STGeomGuideName sTGeomGuideName) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STGeomGuideName sTGeomGuideName2 = null;
            sTGeomGuideName2 = (STGeomGuideName)((Object)this.get_store().find_attribute_user(NAME$0));
            if (sTGeomGuideName2 == null) {
                sTGeomGuideName2 = (STGeomGuideName)((Object)this.get_store().add_attribute_user(NAME$0));
            }
            sTGeomGuideName2.set(sTGeomGuideName);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getFmla() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FMLA$2));
            if (simpleValue == null) {
                return null;
            }
            return simpleValue.getStringValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STGeomGuideFormula xgetFmla() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STGeomGuideFormula sTGeomGuideFormula = null;
            sTGeomGuideFormula = (STGeomGuideFormula)((Object)this.get_store().find_attribute_user(FMLA$2));
            return sTGeomGuideFormula;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setFmla(String string) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(FMLA$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(FMLA$2));
            }
            simpleValue.setStringValue(string);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetFmla(STGeomGuideFormula sTGeomGuideFormula) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STGeomGuideFormula sTGeomGuideFormula2 = null;
            sTGeomGuideFormula2 = (STGeomGuideFormula)((Object)this.get_store().find_attribute_user(FMLA$2));
            if (sTGeomGuideFormula2 == null) {
                sTGeomGuideFormula2 = (STGeomGuideFormula)((Object)this.get_store().add_attribute_user(FMLA$2));
            }
            sTGeomGuideFormula2.set(sTGeomGuideFormula);
        }
    }
}

