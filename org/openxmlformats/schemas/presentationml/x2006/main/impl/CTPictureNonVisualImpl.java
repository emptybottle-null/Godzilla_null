/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualPictureProperties;
import org.openxmlformats.schemas.presentationml.x2006.main.CTApplicationNonVisualDrawingProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPictureNonVisual;

public class CTPictureNonVisualImpl
extends XmlComplexContentImpl
implements CTPictureNonVisual {
    private static final long serialVersionUID = 1L;
    private static final QName CNVPR$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cNvPr");
    private static final QName CNVPICPR$2 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cNvPicPr");
    private static final QName NVPR$4 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvPr");

    public CTPictureNonVisualImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNonVisualDrawingProps getCNvPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNonVisualDrawingProps cTNonVisualDrawingProps = null;
            cTNonVisualDrawingProps = (CTNonVisualDrawingProps)((Object)this.get_store().find_element_user(CNVPR$0, 0));
            if (cTNonVisualDrawingProps == null) {
                return null;
            }
            return cTNonVisualDrawingProps;
        }
    }

    @Override
    public void setCNvPr(CTNonVisualDrawingProps cTNonVisualDrawingProps) {
        this.generatedSetterHelperImpl(cTNonVisualDrawingProps, CNVPR$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNonVisualDrawingProps addNewCNvPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNonVisualDrawingProps cTNonVisualDrawingProps = null;
            cTNonVisualDrawingProps = (CTNonVisualDrawingProps)((Object)this.get_store().add_element_user(CNVPR$0));
            return cTNonVisualDrawingProps;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNonVisualPictureProperties getCNvPicPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNonVisualPictureProperties cTNonVisualPictureProperties = null;
            cTNonVisualPictureProperties = (CTNonVisualPictureProperties)((Object)this.get_store().find_element_user(CNVPICPR$2, 0));
            if (cTNonVisualPictureProperties == null) {
                return null;
            }
            return cTNonVisualPictureProperties;
        }
    }

    @Override
    public void setCNvPicPr(CTNonVisualPictureProperties cTNonVisualPictureProperties) {
        this.generatedSetterHelperImpl(cTNonVisualPictureProperties, CNVPICPR$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNonVisualPictureProperties addNewCNvPicPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNonVisualPictureProperties cTNonVisualPictureProperties = null;
            cTNonVisualPictureProperties = (CTNonVisualPictureProperties)((Object)this.get_store().add_element_user(CNVPICPR$2));
            return cTNonVisualPictureProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTApplicationNonVisualDrawingProps getNvPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTApplicationNonVisualDrawingProps cTApplicationNonVisualDrawingProps = null;
            cTApplicationNonVisualDrawingProps = (CTApplicationNonVisualDrawingProps)((Object)this.get_store().find_element_user(NVPR$4, 0));
            if (cTApplicationNonVisualDrawingProps == null) {
                return null;
            }
            return cTApplicationNonVisualDrawingProps;
        }
    }

    @Override
    public void setNvPr(CTApplicationNonVisualDrawingProps cTApplicationNonVisualDrawingProps) {
        this.generatedSetterHelperImpl(cTApplicationNonVisualDrawingProps, NVPR$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTApplicationNonVisualDrawingProps addNewNvPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTApplicationNonVisualDrawingProps cTApplicationNonVisualDrawingProps = null;
            cTApplicationNonVisualDrawingProps = (CTApplicationNonVisualDrawingProps)((Object)this.get_store().add_element_user(NVPR$4));
            return cTApplicationNonVisualDrawingProps;
        }
    }
}

