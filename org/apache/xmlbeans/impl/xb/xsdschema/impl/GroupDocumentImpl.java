/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.GroupDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup;

public class GroupDocumentImpl
extends XmlComplexContentImpl
implements GroupDocument {
    private static final long serialVersionUID = 1L;
    private static final QName GROUP$0 = new QName("http://www.w3.org/2001/XMLSchema", "group");

    public GroupDocumentImpl(SchemaType sType) {
        super(sType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NamedGroup getGroup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NamedGroup target = null;
            target = (NamedGroup)((Object)this.get_store().find_element_user(GROUP$0, 0));
            if (target == null) {
                return null;
            }
            return target;
        }
    }

    @Override
    public void setGroup(NamedGroup group) {
        this.generatedSetterHelperImpl(group, GROUP$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public NamedGroup addNewGroup() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            NamedGroup target = null;
            target = (NamedGroup)((Object)this.get_store().add_element_user(GROUP$0));
            return target;
        }
    }
}

