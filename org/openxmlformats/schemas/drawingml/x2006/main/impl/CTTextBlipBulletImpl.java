/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBlipBullet;

public class CTTextBlipBulletImpl
extends XmlComplexContentImpl
implements CTTextBlipBullet {
    private static final long serialVersionUID = 1L;
    private static final QName BLIP$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "blip");

    public CTTextBlipBulletImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlip getBlip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlip cTBlip = null;
            cTBlip = (CTBlip)((Object)this.get_store().find_element_user(BLIP$0, 0));
            if (cTBlip == null) {
                return null;
            }
            return cTBlip;
        }
    }

    @Override
    public void setBlip(CTBlip cTBlip) {
        this.generatedSetterHelperImpl(cTBlip, BLIP$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlip addNewBlip() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlip cTBlip = null;
            cTBlip = (CTBlip)((Object)this.get_store().add_element_user(BLIP$0));
            return cTBlip;
        }
    }
}

