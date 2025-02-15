/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.WorkbookDocument;

public class WorkbookDocumentImpl
extends XmlComplexContentImpl
implements WorkbookDocument {
    private static final long serialVersionUID = 1L;
    private static final QName WORKBOOK$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "workbook");

    public WorkbookDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWorkbook getWorkbook() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWorkbook cTWorkbook = null;
            cTWorkbook = (CTWorkbook)((Object)this.get_store().find_element_user(WORKBOOK$0, 0));
            if (cTWorkbook == null) {
                return null;
            }
            return cTWorkbook;
        }
    }

    @Override
    public void setWorkbook(CTWorkbook cTWorkbook) {
        this.generatedSetterHelperImpl(cTWorkbook, WORKBOOK$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWorkbook addNewWorkbook() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWorkbook cTWorkbook = null;
            cTWorkbook = (CTWorkbook)((Object)this.get_store().add_element_user(WORKBOOK$0));
            return cTWorkbook;
        }
    }
}

