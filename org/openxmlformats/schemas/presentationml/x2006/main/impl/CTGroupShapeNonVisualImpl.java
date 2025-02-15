/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGroupDrawingShapeProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTApplicationNonVisualDrawingProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShapeNonVisual;

public class CTGroupShapeNonVisualImpl
extends XmlComplexContentImpl
implements CTGroupShapeNonVisual {
    private static final long serialVersionUID = 1L;
    private static final QName CNVPR$0 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cNvPr");
    private static final QName CNVGRPSPPR$2 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cNvGrpSpPr");
    private static final QName NVPR$4 = new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "nvPr");

    public CTGroupShapeNonVisualImpl(SchemaType schemaType) {
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
    public CTNonVisualGroupDrawingShapeProps getCNvGrpSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNonVisualGroupDrawingShapeProps cTNonVisualGroupDrawingShapeProps = null;
            cTNonVisualGroupDrawingShapeProps = (CTNonVisualGroupDrawingShapeProps)((Object)this.get_store().find_element_user(CNVGRPSPPR$2, 0));
            if (cTNonVisualGroupDrawingShapeProps == null) {
                return null;
            }
            return cTNonVisualGroupDrawingShapeProps;
        }
    }

    @Override
    public void setCNvGrpSpPr(CTNonVisualGroupDrawingShapeProps cTNonVisualGroupDrawingShapeProps) {
        this.generatedSetterHelperImpl(cTNonVisualGroupDrawingShapeProps, CNVGRPSPPR$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNonVisualGroupDrawingShapeProps addNewCNvGrpSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNonVisualGroupDrawingShapeProps cTNonVisualGroupDrawingShapeProps = null;
            cTNonVisualGroupDrawingShapeProps = (CTNonVisualGroupDrawingShapeProps)((Object)this.get_store().add_element_user(CNVGRPSPPR$2));
            return cTNonVisualGroupDrawingShapeProps;
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

