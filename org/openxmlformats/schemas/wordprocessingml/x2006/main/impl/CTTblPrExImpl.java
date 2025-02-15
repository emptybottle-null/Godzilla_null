/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExChange
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrEx;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblPrExBaseImpl;

public class CTTblPrExImpl
extends CTTblPrExBaseImpl
implements CTTblPrEx {
    private static final long serialVersionUID = 1L;
    private static final QName TBLPREXCHANGE$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblPrExChange");

    public CTTblPrExImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblPrExChange getTblPrExChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblPrExChange cTTblPrExChange = null;
            cTTblPrExChange = (CTTblPrExChange)this.get_store().find_element_user(TBLPREXCHANGE$0, 0);
            if (cTTblPrExChange == null) {
                return null;
            }
            return cTTblPrExChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTblPrExChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLPREXCHANGE$0) != 0;
        }
    }

    @Override
    public void setTblPrExChange(CTTblPrExChange cTTblPrExChange) {
        this.generatedSetterHelperImpl((XmlObject)cTTblPrExChange, TBLPREXCHANGE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblPrExChange addNewTblPrExChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblPrExChange cTTblPrExChange = null;
            cTTblPrExChange = (CTTblPrExChange)this.get_store().add_element_user(TBLPREXCHANGE$0);
            return cTTblPrExChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTblPrExChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLPREXCHANGE$0, 0);
        }
    }
}

