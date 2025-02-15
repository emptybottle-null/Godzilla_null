/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcChain;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CalcChainDocument;

public class CalcChainDocumentImpl
extends XmlComplexContentImpl
implements CalcChainDocument {
    private static final long serialVersionUID = 1L;
    private static final QName CALCCHAIN$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "calcChain");

    public CalcChainDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCalcChain getCalcChain() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCalcChain cTCalcChain = null;
            cTCalcChain = (CTCalcChain)((Object)this.get_store().find_element_user(CALCCHAIN$0, 0));
            if (cTCalcChain == null) {
                return null;
            }
            return cTCalcChain;
        }
    }

    @Override
    public void setCalcChain(CTCalcChain cTCalcChain) {
        this.generatedSetterHelperImpl(cTCalcChain, CALCCHAIN$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTCalcChain addNewCalcChain() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTCalcChain cTCalcChain = null;
            cTCalcChain = (CTCalcChain)((Object)this.get_store().add_element_user(CALCCHAIN$0));
            return cTCalcChain;
        }
    }
}

