/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.FtrDocument;

public class FtrDocumentImpl
extends XmlComplexContentImpl
implements FtrDocument {
    private static final long serialVersionUID = 1L;
    private static final QName FTR$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "ftr");

    public FtrDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHdrFtr getFtr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHdrFtr cTHdrFtr = null;
            cTHdrFtr = (CTHdrFtr)((Object)this.get_store().find_element_user(FTR$0, 0));
            if (cTHdrFtr == null) {
                return null;
            }
            return cTHdrFtr;
        }
    }

    @Override
    public void setFtr(CTHdrFtr cTHdrFtr) {
        this.generatedSetterHelperImpl(cTHdrFtr, FTR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHdrFtr addNewFtr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHdrFtr cTHdrFtr = null;
            cTHdrFtr = (CTHdrFtr)((Object)this.get_store().add_element_user(FTR$0));
            return cTHdrFtr;
        }
    }
}

