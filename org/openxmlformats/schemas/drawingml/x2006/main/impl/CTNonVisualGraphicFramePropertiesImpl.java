/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectFrameLocking;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGraphicFrameProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;

public class CTNonVisualGraphicFramePropertiesImpl
extends XmlComplexContentImpl
implements CTNonVisualGraphicFrameProperties {
    private static final long serialVersionUID = 1L;
    private static final QName GRAPHICFRAMELOCKS$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "graphicFrameLocks");
    private static final QName EXTLST$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "extLst");

    public CTNonVisualGraphicFramePropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGraphicalObjectFrameLocking getGraphicFrameLocks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGraphicalObjectFrameLocking cTGraphicalObjectFrameLocking = null;
            cTGraphicalObjectFrameLocking = (CTGraphicalObjectFrameLocking)((Object)this.get_store().find_element_user(GRAPHICFRAMELOCKS$0, 0));
            if (cTGraphicalObjectFrameLocking == null) {
                return null;
            }
            return cTGraphicalObjectFrameLocking;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetGraphicFrameLocks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(GRAPHICFRAMELOCKS$0) != 0;
        }
    }

    @Override
    public void setGraphicFrameLocks(CTGraphicalObjectFrameLocking cTGraphicalObjectFrameLocking) {
        this.generatedSetterHelperImpl(cTGraphicalObjectFrameLocking, GRAPHICFRAMELOCKS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGraphicalObjectFrameLocking addNewGraphicFrameLocks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGraphicalObjectFrameLocking cTGraphicalObjectFrameLocking = null;
            cTGraphicalObjectFrameLocking = (CTGraphicalObjectFrameLocking)((Object)this.get_store().add_element_user(GRAPHICFRAMELOCKS$0));
            return cTGraphicalObjectFrameLocking;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetGraphicFrameLocks() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(GRAPHICFRAMELOCKS$0, 0);
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

