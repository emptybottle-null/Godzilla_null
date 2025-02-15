/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTDocumentBaseImpl;

public class CTDocument1Impl
extends CTDocumentBaseImpl
implements CTDocument1 {
    private static final long serialVersionUID = 1L;
    private static final QName BODY$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "body");

    public CTDocument1Impl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBody getBody() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBody cTBody = null;
            cTBody = (CTBody)((Object)this.get_store().find_element_user(BODY$0, 0));
            if (cTBody == null) {
                return null;
            }
            return cTBody;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBody() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BODY$0) != 0;
        }
    }

    @Override
    public void setBody(CTBody cTBody) {
        this.generatedSetterHelperImpl(cTBody, BODY$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBody addNewBody() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBody cTBody = null;
            cTBody = (CTBody)((Object)this.get_store().add_element_user(BODY$0));
            return cTBody;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBody() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BODY$0, 0);
        }
    }
}

