/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.HdrDocument;

public class HdrDocumentImpl
extends XmlComplexContentImpl
implements HdrDocument {
    private static final long serialVersionUID = 1L;
    private static final QName HDR$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hdr");

    public HdrDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHdrFtr getHdr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHdrFtr cTHdrFtr = null;
            cTHdrFtr = (CTHdrFtr)((Object)this.get_store().find_element_user(HDR$0, 0));
            if (cTHdrFtr == null) {
                return null;
            }
            return cTHdrFtr;
        }
    }

    @Override
    public void setHdr(CTHdrFtr cTHdrFtr) {
        this.generatedSetterHelperImpl(cTHdrFtr, HDR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTHdrFtr addNewHdr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTHdrFtr cTHdrFtr = null;
            cTHdrFtr = (CTHdrFtr)((Object)this.get_store().add_element_user(HDR$0));
            return cTHdrFtr;
        }
    }
}

