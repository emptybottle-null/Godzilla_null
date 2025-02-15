/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStretchInfoProperties;

public class CTStretchInfoPropertiesImpl
extends XmlComplexContentImpl
implements CTStretchInfoProperties {
    private static final long serialVersionUID = 1L;
    private static final QName FILLRECT$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "fillRect");

    public CTStretchInfoPropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRelativeRect getFillRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRelativeRect cTRelativeRect = null;
            cTRelativeRect = (CTRelativeRect)((Object)this.get_store().find_element_user(FILLRECT$0, 0));
            if (cTRelativeRect == null) {
                return null;
            }
            return cTRelativeRect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetFillRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(FILLRECT$0) != 0;
        }
    }

    @Override
    public void setFillRect(CTRelativeRect cTRelativeRect) {
        this.generatedSetterHelperImpl(cTRelativeRect, FILLRECT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRelativeRect addNewFillRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRelativeRect cTRelativeRect = null;
            cTRelativeRect = (CTRelativeRect)((Object)this.get_store().add_element_user(FILLRECT$0));
            return cTRelativeRect;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetFillRect() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(FILLRECT$0, 0);
        }
    }
}

