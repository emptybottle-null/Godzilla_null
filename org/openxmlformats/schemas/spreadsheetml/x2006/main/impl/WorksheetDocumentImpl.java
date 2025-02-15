/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.WorksheetDocument;

public class WorksheetDocumentImpl
extends XmlComplexContentImpl
implements WorksheetDocument {
    private static final long serialVersionUID = 1L;
    private static final QName WORKSHEET$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "worksheet");

    public WorksheetDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWorksheet getWorksheet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWorksheet cTWorksheet = null;
            cTWorksheet = (CTWorksheet)((Object)this.get_store().find_element_user(WORKSHEET$0, 0));
            if (cTWorksheet == null) {
                return null;
            }
            return cTWorksheet;
        }
    }

    @Override
    public void setWorksheet(CTWorksheet cTWorksheet) {
        this.generatedSetterHelperImpl(cTWorksheet, WORKSHEET$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWorksheet addNewWorksheet() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWorksheet cTWorksheet = null;
            cTWorksheet = (CTWorksheet)((Object)this.get_store().add_element_user(WORKSHEET$0));
            return cTWorksheet;
        }
    }
}

