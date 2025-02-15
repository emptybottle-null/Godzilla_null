/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSst;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.SstDocument;

public class SstDocumentImpl
extends XmlComplexContentImpl
implements SstDocument {
    private static final long serialVersionUID = 1L;
    private static final QName SST$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "sst");

    public SstDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSst getSst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSst cTSst = null;
            cTSst = (CTSst)((Object)this.get_store().find_element_user(SST$0, 0));
            if (cTSst == null) {
                return null;
            }
            return cTSst;
        }
    }

    @Override
    public void setSst(CTSst cTSst) {
        this.generatedSetterHelperImpl(cTSst, SST$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSst addNewSst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSst cTSst = null;
            cTSst = (CTSst)((Object)this.get_store().add_element_user(SST$0));
            return cTSst;
        }
    }
}

