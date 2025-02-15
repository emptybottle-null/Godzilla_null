/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGridChange
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGrid;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGridChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTTblGridBaseImpl;

public class CTTblGridImpl
extends CTTblGridBaseImpl
implements CTTblGrid {
    private static final long serialVersionUID = 1L;
    private static final QName TBLGRIDCHANGE$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblGridChange");

    public CTTblGridImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblGridChange getTblGridChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblGridChange cTTblGridChange = null;
            cTTblGridChange = (CTTblGridChange)this.get_store().find_element_user(TBLGRIDCHANGE$0, 0);
            if (cTTblGridChange == null) {
                return null;
            }
            return cTTblGridChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetTblGridChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(TBLGRIDCHANGE$0) != 0;
        }
    }

    @Override
    public void setTblGridChange(CTTblGridChange cTTblGridChange) {
        this.generatedSetterHelperImpl((XmlObject)cTTblGridChange, TBLGRIDCHANGE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTblGridChange addNewTblGridChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTblGridChange cTTblGridChange = null;
            cTTblGridChange = (CTTblGridChange)this.get_store().add_element_user(TBLGRIDCHANGE$0);
            return cTTblGridChange;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetTblGridChange() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(TBLGRIDCHANGE$0, 0);
        }
    }
}

