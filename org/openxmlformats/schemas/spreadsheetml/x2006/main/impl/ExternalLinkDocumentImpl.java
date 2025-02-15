/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalLink;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.ExternalLinkDocument;

public class ExternalLinkDocumentImpl
extends XmlComplexContentImpl
implements ExternalLinkDocument {
    private static final long serialVersionUID = 1L;
    private static final QName EXTERNALLINK$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "externalLink");

    public ExternalLinkDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalLink getExternalLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalLink cTExternalLink = null;
            cTExternalLink = (CTExternalLink)((Object)this.get_store().find_element_user(EXTERNALLINK$0, 0));
            if (cTExternalLink == null) {
                return null;
            }
            return cTExternalLink;
        }
    }

    @Override
    public void setExternalLink(CTExternalLink cTExternalLink) {
        this.generatedSetterHelperImpl(cTExternalLink, EXTERNALLINK$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTExternalLink addNewExternalLink() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTExternalLink cTExternalLink = null;
            cTExternalLink = (CTExternalLink)((Object)this.get_store().add_element_user(EXTERNALLINK$0));
            return cTExternalLink;
        }
    }
}

