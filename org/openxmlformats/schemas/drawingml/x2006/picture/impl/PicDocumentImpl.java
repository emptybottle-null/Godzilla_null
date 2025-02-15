/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.picture.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.picture.PicDocument;

public class PicDocumentImpl
extends XmlComplexContentImpl
implements PicDocument {
    private static final long serialVersionUID = 1L;
    private static final QName PIC$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/picture", "pic");

    public PicDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPicture getPic() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPicture cTPicture = null;
            cTPicture = (CTPicture)((Object)this.get_store().find_element_user(PIC$0, 0));
            if (cTPicture == null) {
                return null;
            }
            return cTPicture;
        }
    }

    @Override
    public void setPic(CTPicture cTPicture) {
        this.generatedSetterHelperImpl(cTPicture, PIC$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPicture addNewPic() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPicture cTPicture = null;
            cTPicture = (CTPicture)((Object)this.get_store().add_element_user(PIC$0));
            return cTPicture;
        }
    }
}

