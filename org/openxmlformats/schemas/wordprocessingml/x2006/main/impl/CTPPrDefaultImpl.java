/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrDefault;

public class CTPPrDefaultImpl
extends XmlComplexContentImpl
implements CTPPrDefault {
    private static final long serialVersionUID = 1L;
    private static final QName PPR$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pPr");

    public CTPPrDefaultImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPPr getPPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPPr cTPPr = null;
            cTPPr = (CTPPr)((Object)this.get_store().find_element_user(PPR$0, 0));
            if (cTPPr == null) {
                return null;
            }
            return cTPPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetPPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(PPR$0) != 0;
        }
    }

    @Override
    public void setPPr(CTPPr cTPPr) {
        this.generatedSetterHelperImpl(cTPPr, PPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPPr addNewPPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPPr cTPPr = null;
            cTPPr = (CTPPr)((Object)this.get_store().add_element_user(PPR$0));
            return cTPPr;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetPPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(PPR$0, 0);
        }
    }
}

