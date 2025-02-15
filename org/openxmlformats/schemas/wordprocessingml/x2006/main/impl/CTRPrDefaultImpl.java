/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPrDefault;

public class CTRPrDefaultImpl
extends XmlComplexContentImpl
implements CTRPrDefault {
    private static final long serialVersionUID = 1L;
    private static final QName RPR$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rPr");

    public CTRPrDefaultImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRPr getRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRPr cTRPr = null;
            cTRPr = (CTRPr)((Object)this.get_store().find_element_user(RPR$0, 0));
            if (cTRPr == null) {
                return null;
            }
            return cTRPr;
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
    public void setRPr(CTRPr cTRPr) {
        this.generatedSetterHelperImpl(cTRPr, RPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTRPr addNewRPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTRPr cTRPr = null;
            cTRPr = (CTRPr)((Object)this.get_store().add_element_user(RPR$0));
            return cTRPr;
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

