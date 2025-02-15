/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.MastersDocument;
import com.microsoft.schemas.office.visio.x2012.main.MastersType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class MastersDocumentImpl
extends XmlComplexContentImpl
implements MastersDocument {
    private static final long serialVersionUID = 1L;
    private static final QName MASTERS$0 = new QName("http://schemas.microsoft.com/office/visio/2012/main", "Masters");

    public MastersDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public MastersType getMasters() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            MastersType mastersType = null;
            mastersType = (MastersType)((Object)this.get_store().find_element_user(MASTERS$0, 0));
            if (mastersType == null) {
                return null;
            }
            return mastersType;
        }
    }

    @Override
    public void setMasters(MastersType mastersType) {
        this.generatedSetterHelperImpl(mastersType, MASTERS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public MastersType addNewMasters() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            MastersType mastersType = null;
            mastersType = (MastersType)((Object)this.get_store().add_element_user(MASTERS$0));
            return mastersType;
        }
    }
}

