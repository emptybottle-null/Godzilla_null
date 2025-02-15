/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrChange
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTcPrInnerImpl;

public class CTTcPrImpl
extends CTTcPrInnerImpl
implements CTTcPr {
    private static final long serialVersionUID = 1L;
    private static final QName TCPRCHANGE$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tcPrChange");

    public CTTcPrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTcPrChange getTcPrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTcPrChange cTTcPrChange = null;
            cTTcPrChange = (CTTcPrChange)this.get_store().find_element_user(TCPRCHANGE$0, 0);
            if (cTTcPrChange == null) {
                return null;
            }
            return cTTcPrChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTcPrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TCPRCHANGE$0) != 0;
        }
    }

    @Override
    public void setTcPrChange(CTTcPrChange cTTcPrChange) {
        this.generatedSetterHelperImpl((XmlObject)cTTcPrChange, TCPRCHANGE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTcPrChange addNewTcPrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTcPrChange cTTcPrChange = null;
            cTTcPrChange = (CTTcPrChange)this.get_store().add_element_user(TCPRCHANGE$0);
            return cTTcPrChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTcPrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TCPRCHANGE$0, 0);
        }
    }
}

