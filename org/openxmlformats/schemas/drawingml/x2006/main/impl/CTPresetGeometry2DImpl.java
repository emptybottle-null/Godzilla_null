/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuideList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;

public class CTPresetGeometry2DImpl
extends XmlComplexContentImpl
implements CTPresetGeometry2D {
    private static final long serialVersionUID = 1L;
    private static final QName AVLST$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "avLst");
    private static final QName PRST$2 = new QName("", "prst");

    public CTPresetGeometry2DImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGeomGuideList getAvLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGeomGuideList cTGeomGuideList = null;
            cTGeomGuideList = (CTGeomGuideList)((Object)this.get_store().find_element_user(AVLST$0, 0));
            if (cTGeomGuideList == null) {
                return null;
            }
            return cTGeomGuideList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetAvLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(AVLST$0) != 0;
        }
    }

    @Override
    public void setAvLst(CTGeomGuideList cTGeomGuideList) {
        this.generatedSetterHelperImpl(cTGeomGuideList, AVLST$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGeomGuideList addNewAvLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGeomGuideList cTGeomGuideList = null;
            cTGeomGuideList = (CTGeomGuideList)((Object)this.get_store().add_element_user(AVLST$0));
            return cTGeomGuideList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetAvLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(AVLST$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STShapeType.Enum getPrst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PRST$2));
            if (simpleValue == null) {
                return null;
            }
            return (STShapeType.Enum)simpleValue.getEnumValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STShapeType xgetPrst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STShapeType sTShapeType = null;
            sTShapeType = (STShapeType)((Object)this.get_store().find_attribute_user(PRST$2));
            return sTShapeType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setPrst(STShapeType.Enum enum_) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(PRST$2));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(PRST$2));
            }
            simpleValue.setEnumValue(enum_);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetPrst(STShapeType sTShapeType) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STShapeType sTShapeType2 = null;
            sTShapeType2 = (STShapeType)((Object)this.get_store().find_attribute_user(PRST$2));
            if (sTShapeType2 == null) {
                sTShapeType2 = (STShapeType)((Object)this.get_store().add_attribute_user(PRST$2));
            }
            sTShapeType2.set(sTShapeType);
        }
    }
}

