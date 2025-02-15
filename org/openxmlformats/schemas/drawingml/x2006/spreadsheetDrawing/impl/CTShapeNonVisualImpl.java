/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingShapeProps;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShapeNonVisual;

public class CTShapeNonVisualImpl
extends XmlComplexContentImpl
implements CTShapeNonVisual {
    private static final long serialVersionUID = 1L;
    private static final QName CNVPR$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "cNvPr");
    private static final QName CNVSPPR$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "cNvSpPr");

    public CTShapeNonVisualImpl(SchemaType schemaType) {
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
    public CTNonVisualDrawingShapeProps getCNvSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNonVisualDrawingShapeProps cTNonVisualDrawingShapeProps = null;
            cTNonVisualDrawingShapeProps = (CTNonVisualDrawingShapeProps)((Object)this.get_store().find_element_user(CNVSPPR$2, 0));
            if (cTNonVisualDrawingShapeProps == null) {
                return null;
            }
            return cTNonVisualDrawingShapeProps;
        }
    }

    @Override
    public void setCNvSpPr(CTNonVisualDrawingShapeProps cTNonVisualDrawingShapeProps) {
        this.generatedSetterHelperImpl(cTNonVisualDrawingShapeProps, CNVSPPR$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNonVisualDrawingShapeProps addNewCNvSpPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNonVisualDrawingShapeProps cTNonVisualDrawingShapeProps = null;
            cTNonVisualDrawingShapeProps = (CTNonVisualDrawingShapeProps)((Object)this.get_store().add_element_user(CNVSPPR$2));
            return cTNonVisualDrawingShapeProps;
        }
    }
}

