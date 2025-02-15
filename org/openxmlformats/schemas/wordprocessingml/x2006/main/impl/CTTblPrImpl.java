/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrChange
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblPrBaseImpl;

public class CTTblPrImpl
extends CTTblPrBaseImpl
implements CTTblPr {
    private static final long serialVersionUID = 1L;
    private static final QName TBLPRCHANGE$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblPrChange");

    public CTTblPrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblPrChange getTblPrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblPrChange cTTblPrChange = null;
            cTTblPrChange = (CTTblPrChange)this.get_store().find_element_user(TBLPRCHANGE$0, 0);
            if (cTTblPrChange == null) {
                return null;
            }
            return cTTblPrChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTblPrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLPRCHANGE$0) != 0;
        }
    }

    @Override
    public void setTblPrChange(CTTblPrChange cTTblPrChange) {
        this.generatedSetterHelperImpl((XmlObject)cTTblPrChange, TBLPRCHANGE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblPrChange addNewTblPrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblPrChange cTTblPrChange = null;
            cTTblPrChange = (CTTblPrChange)this.get_store().add_element_user(TBLPRCHANGE$0);
            return cTTblPrChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTblPrChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLPRCHANGE$0, 0);
        }
    }
}

