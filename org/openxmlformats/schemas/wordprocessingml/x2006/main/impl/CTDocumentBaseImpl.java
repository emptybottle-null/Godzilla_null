/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocumentBase;

public class CTDocumentBaseImpl
extends XmlComplexContentImpl
implements CTDocumentBase {
    private static final long serialVersionUID = 1L;
    private static final QName BACKGROUND$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "background");

    public CTDocumentBaseImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBackground getBackground() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBackground cTBackground = null;
            cTBackground = (CTBackground)((Object)this.get_store().find_element_user(BACKGROUND$0, 0));
            if (cTBackground == null) {
                return null;
            }
            return cTBackground;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetBackground() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(BACKGROUND$0) != 0;
        }
    }

    @Override
    public void setBackground(CTBackground cTBackground) {
        this.generatedSetterHelperImpl(cTBackground, BACKGROUND$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBackground addNewBackground() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBackground cTBackground = null;
            cTBackground = (CTBackground)((Object)this.get_store().add_element_user(BACKGROUND$0));
            return cTBackground;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetBackground() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(BACKGROUND$0, 0);
        }
    }
}

