/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DMoveTo;

public class CTPath2DMoveToImpl
extends XmlComplexContentImpl
implements CTPath2DMoveTo {
    private static final long serialVersionUID = 1L;
    private static final QName PT$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "pt");

    public CTPath2DMoveToImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAdjPoint2D getPt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAdjPoint2D cTAdjPoint2D = null;
            cTAdjPoint2D = (CTAdjPoint2D)((Object)this.get_store().find_element_user(PT$0, 0));
            if (cTAdjPoint2D == null) {
                return null;
            }
            return cTAdjPoint2D;
        }
    }

    @Override
    public void setPt(CTAdjPoint2D cTAdjPoint2D) {
        this.generatedSetterHelperImpl(cTAdjPoint2D, PT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTAdjPoint2D addNewPt() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTAdjPoint2D cTAdjPoint2D = null;
            cTAdjPoint2D = (CTAdjPoint2D)((Object)this.get_store().add_element_user(PT$0));
            return cTAdjPoint2D;
        }
    }
}

