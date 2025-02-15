/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMaster;
import org.openxmlformats.schemas.presentationml.x2006.main.SldMasterDocument;

public class SldMasterDocumentImpl
extends XmlComplexContentImpl
implements SldMasterDocument {
    private static final long serialVersionUID = 1L;
    private static final QName SLDMASTER$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "sldMaster");

    public SldMasterDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideMaster getSldMaster() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideMaster cTSlideMaster = null;
            cTSlideMaster = (CTSlideMaster)((Object)this.get_store().find_element_user(SLDMASTER$0, 0));
            if (cTSlideMaster == null) {
                return null;
            }
            return cTSlideMaster;
        }
    }

    @Override
    public void setSldMaster(CTSlideMaster cTSlideMaster) {
        this.generatedSetterHelperImpl(cTSlideMaster, SLDMASTER$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSlideMaster addNewSldMaster() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSlideMaster cTSlideMaster = null;
            cTSlideMaster = (CTSlideMaster)((Object)this.get_store().add_element_user(SLDMASTER$0));
            return cTSlideMaster;
        }
    }
}

