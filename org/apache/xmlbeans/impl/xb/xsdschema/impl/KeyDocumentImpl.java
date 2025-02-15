/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.KeyDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Keybase;

public class KeyDocumentImpl
extends XmlComplexContentImpl
implements KeyDocument {
    private static final long serialVersionUID = 1L;
    private static final QName KEY$0 = new QName("http://www.w3.org/2001/XMLSchema", "key");

    public KeyDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Keybase getKey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Keybase target = null;
            target = (Keybase)((Object)this.get_store().find_element_user(KEY$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setKey(Keybase key) {
        this.generatedSetterHelperImpl(key, KEY$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Keybase addNewKey() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            Keybase target = null;
            target = (Keybase)((Object)this.get_store().add_element_user(KEY$0));
            return target;
        }
    }
}

