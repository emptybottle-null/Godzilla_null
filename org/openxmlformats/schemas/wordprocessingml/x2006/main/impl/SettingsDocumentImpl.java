/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.SettingsDocument;

public class SettingsDocumentImpl
extends XmlComplexContentImpl
implements SettingsDocument {
    private static final long serialVersionUID = 1L;
    private static final QName SETTINGS$0 = new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "settings");

    public SettingsDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSettings getSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSettings cTSettings = null;
            cTSettings = (CTSettings)((Object)this.get_store().find_element_user(SETTINGS$0, 0));
            if (cTSettings == null) {
                return null;
            }
            return cTSettings;
        }
    }

    @Override
    public void setSettings(CTSettings cTSettings) {
        this.generatedSetterHelperImpl(cTSettings, SETTINGS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSettings addNewSettings() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSettings cTSettings = null;
            cTSettings = (CTSettings)((Object)this.get_store().add_element_user(SETTINGS$0));
            return cTSettings;
        }
    }
}

