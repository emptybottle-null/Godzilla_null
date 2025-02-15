/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.TableDocument;

public class TableDocumentImpl
extends XmlComplexContentImpl
implements TableDocument {
    private static final long serialVersionUID = 1L;
    private static final QName TABLE$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "table");

    public TableDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTable getTable() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTable cTTable = null;
            cTTable = (CTTable)((Object)this.get_store().find_element_user(TABLE$0, 0));
            if (cTTable == null) {
                return null;
            }
            return cTTable;
        }
    }

    @Override
    public void setTable(CTTable cTTable) {
        this.generatedSetterHelperImpl(cTTable, TABLE$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTable addNewTable() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTable cTTable = null;
            cTTable = (CTTable)((Object)this.get_store().add_element_user(TABLE$0));
            return cTTable;
        }
    }
}

