/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCells;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.SingleXmlCellsDocument;

public class SingleXmlCellsDocumentImpl
extends XmlComplexContentImpl
implements SingleXmlCellsDocument {
    private static final long serialVersionUID = 1L;
    private static final QName SINGLEXMLCELLS$0 = new QName("http://schemas.openxmlformats.org/spreadsheetml/2006/main", "singleXmlCells");

    public SingleXmlCellsDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSingleXmlCells getSingleXmlCells() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSingleXmlCells cTSingleXmlCells = null;
            cTSingleXmlCells = (CTSingleXmlCells)((Object)this.get_store().find_element_user(SINGLEXMLCELLS$0, 0));
            if (cTSingleXmlCells == null) {
                return null;
            }
            return cTSingleXmlCells;
        }
    }

    @Override
    public void setSingleXmlCells(CTSingleXmlCells cTSingleXmlCells) {
        this.generatedSetterHelperImpl(cTSingleXmlCells, SINGLEXMLCELLS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTSingleXmlCells addNewSingleXmlCells() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTSingleXmlCells cTSingleXmlCells = null;
            cTSingleXmlCells = (CTSingleXmlCells)((Object)this.get_store().add_element_user(SINGLEXMLCELLS$0));
            return cTSingleXmlCells;
        }
    }
}

