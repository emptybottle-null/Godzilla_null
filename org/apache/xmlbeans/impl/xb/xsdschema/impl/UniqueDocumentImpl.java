/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.Keybase;
import org.apache.xmlbeans.impl.xb.xsdschema.UniqueDocument;

public class UniqueDocumentImpl
extends XmlComplexContentImpl
implements UniqueDocument {
    private static final long serialVersionUID = 1L;
    private static final QName UNIQUE$0 = new QName("http://www.w3.org/2001/XMLSchema", "unique");

    public UniqueDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Keybase getUnique() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Keybase target = null;
            target = (Keybase)((Object)this.get_store().find_element_user(UNIQUE$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setUnique(Keybase unique) {
        this.generatedSetterHelperImpl(unique, UNIQUE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Keybase addNewUnique() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Keybase target = null;
            target = (Keybase)((Object)this.get_store().add_element_user(UNIQUE$0));
            return target;
        }
    }
}

