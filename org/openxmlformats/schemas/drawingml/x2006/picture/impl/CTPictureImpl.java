/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.picture.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPictureNonVisual;

public class CTPictureImpl
extends XmlComplexContentImpl
implements CTPicture {
    private static final long serialVersionUID = 1L;
    private static final QName NVPICPR$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/picture", "nvPicPr");
    private static final QName BLIPFILL$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/picture", "blipFill");
    private static final QName SPPR$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/picture", "spPr");

    public CTPictureImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPictureNonVisual getNvPicPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPictureNonVisual cTPictureNonVisual = null;
            cTPictureNonVisual = (CTPictureNonVisual)((Object)this.get_store().find_element_user(NVPICPR$0, 0));
            if (cTPictureNonVisual == null) {
                return null;
            }
            return cTPictureNonVisual;
        }
    }

    @Override
    public void setNvPicPr(CTPictureNonVisual cTPictureNonVisual) {
        this.generatedSetterHelperImpl(cTPictureNonVisual, NVPICPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPictureNonVisual addNewNvPicPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPictureNonVisual cTPictureNonVisual = null;
            cTPictureNonVisual = (CTPictureNonVisual)((Object)this.get_store().add_element_user(NVPICPR$0));
            return cTPictureNonVisual;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlipFillProperties getBlipFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlipFillProperties cTBlipFillProperties = null;
            cTBlipFillProperties = (CTBlipFillProperties)((Object)this.get_store().find_element_user(BLIPFILL$2, 0));
            if (cTBlipFillProperties == null) {
                return null;
            }
            return cTBlipFillProperties;
        }
    }

    @Override
    public void setBlipFill(CTBlipFillProperties cTBlipFillProperties) {
        this.generatedSetterHelperImpl(cTBlipFillProperties, BLIPFILL$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTBlipFillProperties addNewBlipFill() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTBlipFillProperties cTBlipFillProperties = null;
            cTBlipFillProperties = (CTBlipFillProperties)((Object)this.get_store().add_element_user(BLIPFILL$2));
            return cTBlipFillProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeProperties getSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeProperties cTShapeProperties = null;
            cTShapeProperties = (CTShapeProperties)((Object)this.get_store().find_element_user(SPPR$4, 0));
            if (cTShapeProperties == null) {
                return null;
            }
            return cTShapeProperties;
        }
    }

    @Override
    public void setSpPr(CTShapeProperties cTShapeProperties) {
        this.generatedSetterHelperImpl(cTShapeProperties, SPPR$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTShapeProperties addNewSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTShapeProperties cTShapeProperties = null;
            cTShapeProperties = (CTShapeProperties)((Object)this.get_store().add_element_user(SPPR$4));
            return cTShapeProperties;
        }
    }
}

