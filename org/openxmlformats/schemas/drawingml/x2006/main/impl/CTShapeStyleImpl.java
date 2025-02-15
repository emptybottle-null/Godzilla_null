/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrixReference;

public class CTShapeStyleImpl
extends XmlComplexContentImpl
implements CTShapeStyle {
    private static final long serialVersionUID = 1L;
    private static final QName LNREF$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "lnRef");
    private static final QName FILLREF$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "fillRef");
    private static final QName EFFECTREF$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "effectRef");
    private static final QName FONTREF$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "fontRef");

    public CTShapeStyleImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStyleMatrixReference getLnRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStyleMatrixReference cTStyleMatrixReference = null;
            cTStyleMatrixReference = (CTStyleMatrixReference)((Object)this.get_store().find_element_user(LNREF$0, 0));
            if (cTStyleMatrixReference == null) {
                return null;
            }
            return cTStyleMatrixReference;
        }
    }

    @Override
    public void setLnRef(CTStyleMatrixReference cTStyleMatrixReference) {
        this.generatedSetterHelperImpl(cTStyleMatrixReference, LNREF$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStyleMatrixReference addNewLnRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStyleMatrixReference cTStyleMatrixReference = null;
            cTStyleMatrixReference = (CTStyleMatrixReference)((Object)this.get_store().add_element_user(LNREF$0));
            return cTStyleMatrixReference;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStyleMatrixReference getFillRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStyleMatrixReference cTStyleMatrixReference = null;
            cTStyleMatrixReference = (CTStyleMatrixReference)((Object)this.get_store().find_element_user(FILLREF$2, 0));
            if (cTStyleMatrixReference == null) {
                return null;
            }
            return cTStyleMatrixReference;
        }
    }

    @Override
    public void setFillRef(CTStyleMatrixReference cTStyleMatrixReference) {
        this.generatedSetterHelperImpl(cTStyleMatrixReference, FILLREF$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStyleMatrixReference addNewFillRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStyleMatrixReference cTStyleMatrixReference = null;
            cTStyleMatrixReference = (CTStyleMatrixReference)((Object)this.get_store().add_element_user(FILLREF$2));
            return cTStyleMatrixReference;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStyleMatrixReference getEffectRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStyleMatrixReference cTStyleMatrixReference = null;
            cTStyleMatrixReference = (CTStyleMatrixReference)((Object)this.get_store().find_element_user(EFFECTREF$4, 0));
            if (cTStyleMatrixReference == null) {
                return null;
            }
            return cTStyleMatrixReference;
        }
    }

    @Override
    public void setEffectRef(CTStyleMatrixReference cTStyleMatrixReference) {
        this.generatedSetterHelperImpl(cTStyleMatrixReference, EFFECTREF$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTStyleMatrixReference addNewEffectRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTStyleMatrixReference cTStyleMatrixReference = null;
            cTStyleMatrixReference = (CTStyleMatrixReference)((Object)this.get_store().add_element_user(EFFECTREF$4));
            return cTStyleMatrixReference;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFontReference getFontRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFontReference cTFontReference = null;
            cTFontReference = (CTFontReference)((Object)this.get_store().find_element_user(FONTREF$6, 0));
            if (cTFontReference == null) {
                return null;
            }
            return cTFontReference;
        }
    }

    @Override
    public void setFontRef(CTFontReference cTFontReference) {
        this.generatedSetterHelperImpl(cTFontReference, FONTREF$6, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTFontReference addNewFontRef() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTFontReference cTFontReference = null;
            cTFontReference = (CTFontReference)((Object)this.get_store().add_element_user(FONTREF$6));
            return cTFontReference;
        }
    }
}

