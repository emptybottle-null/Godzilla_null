/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;

public class CTGraphicalObjectImpl
extends XmlComplexContentImpl
implements CTGraphicalObject {
    private static final long serialVersionUID = 1L;
    private static final QName GRAPHICDATA$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "graphicData");

    public CTGraphicalObjectImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGraphicalObjectData getGraphicData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGraphicalObjectData cTGraphicalObjectData = null;
            cTGraphicalObjectData = (CTGraphicalObjectData)((Object)this.get_store().find_element_user(GRAPHICDATA$0, 0));
            if (cTGraphicalObjectData == null) {
                return null;
            }
            return cTGraphicalObjectData;
        }
    }

    @Override
    public void setGraphicData(CTGraphicalObjectData cTGraphicalObjectData) {
        this.generatedSetterHelperImpl(cTGraphicalObjectData, GRAPHICDATA$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGraphicalObjectData addNewGraphicData() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGraphicalObjectData cTGraphicalObjectData = null;
            cTGraphicalObjectData = (CTGraphicalObjectData)((Object)this.get_store().add_element_user(GRAPHICDATA$0));
            return cTGraphicalObjectData;
        }
    }
}

