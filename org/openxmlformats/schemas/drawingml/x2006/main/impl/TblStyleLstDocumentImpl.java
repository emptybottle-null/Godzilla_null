/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.TblStyleLstDocument;

public class TblStyleLstDocumentImpl
extends XmlComplexContentImpl
implements TblStyleLstDocument {
    private static final long serialVersionUID = 1L;
    private static final QName TBLSTYLELST$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "tblStyleLst");

    public TblStyleLstDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableStyleList getTblStyleLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableStyleList cTTableStyleList = null;
            cTTableStyleList = (CTTableStyleList)((Object)this.get_store().find_element_user(TBLSTYLELST$0, 0));
            if (cTTableStyleList == null) {
                return null;
            }
            return cTTableStyleList;
        }
    }

    @Override
    public void setTblStyleLst(CTTableStyleList cTTableStyleList) {
        this.generatedSetterHelperImpl(cTTableStyleList, TBLSTYLELST$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTTableStyleList addNewTblStyleLst() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTTableStyleList cTTableStyleList = null;
            cTTableStyleList = (CTTableStyleList)((Object)this.get_store().add_element_user(TBLSTYLELST$0));
            return cTTableStyleList;
        }
    }
}

