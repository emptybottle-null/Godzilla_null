/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTGroupLocking
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupLocking;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGroupDrawingShapeProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;

public class CTNonVisualGroupDrawingShapePropsImpl
extends XmlComplexContentImpl
implements CTNonVisualGroupDrawingShapeProps {
    private static final long serialVersionUID = 1L;
    private static final QName GRPSPLOCKS$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "grpSpLocks");
    private static final QName EXTLST$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extLst");

    public CTNonVisualGroupDrawingShapePropsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupLocking getGrpSpLocks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupLocking cTGroupLocking = null;
            cTGroupLocking = (CTGroupLocking)this.get_store().find_element_user(GRPSPLOCKS$0, 0);
            if (cTGroupLocking == null) {
                return null;
            }
            return cTGroupLocking;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGrpSpLocks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GRPSPLOCKS$0) != 0;
        }
    }

    @Override
    public void setGrpSpLocks(CTGroupLocking cTGroupLocking) {
        this.generatedSetterHelperImpl((XmlObject)cTGroupLocking, GRPSPLOCKS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGroupLocking addNewGrpSpLocks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGroupLocking cTGroupLocking = null;
            cTGroupLocking = (CTGroupLocking)this.get_store().add_element_user(GRPSPLOCKS$0);
            return cTGroupLocking;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGrpSpLocks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GRPSPLOCKS$0, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtensionList getExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtensionList cTOfficeArtExtensionList = null;
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().find_element_user(EXTLST$2, 0));
            if (cTOfficeArtExtensionList == null) {
                return null;
            }
            return cTOfficeArtExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EXTLST$2) != 0;
        }
    }

    @Override
    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        this.generatedSetterHelperImpl(cTOfficeArtExtensionList, EXTLST$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTOfficeArtExtensionList addNewExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTOfficeArtExtensionList cTOfficeArtExtensionList = null;
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList)((Object)this.get_store().add_element_user(EXTLST$2));
            return cTOfficeArtExtensionList;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetExtLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EXTLST$2, 0);
        }
    }
}

