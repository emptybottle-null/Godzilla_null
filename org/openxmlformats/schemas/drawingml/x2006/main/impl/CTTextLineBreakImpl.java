/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;

public class CTTextLineBreakImpl
extends XmlComplexContentImpl
implements CTTextLineBreak {
    private static final long serialVersionUID = 1L;
    private static final QName RPR$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "rPr");

    public CTTextLineBreakImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextCharacterProperties getRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextCharacterProperties cTTextCharacterProperties = null;
            cTTextCharacterProperties = (CTTextCharacterProperties)((Object)this.get_store().find_element_user(RPR$0, 0));
            if (cTTextCharacterProperties == null) {
                return null;
            }
            return cTTextCharacterProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(RPR$0) != 0;
        }
    }

    @Override
    public void setRPr(CTTextCharacterProperties cTTextCharacterProperties) {
        this.generatedSetterHelperImpl(cTTextCharacterProperties, RPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTextCharacterProperties addNewRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTextCharacterProperties cTTextCharacterProperties = null;
            cTTextCharacterProperties = (CTTextCharacterProperties)((Object)this.get_store().add_element_user(RPR$0));
            return cTTextCharacterProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(RPR$0, 0);
        }
    }
}

