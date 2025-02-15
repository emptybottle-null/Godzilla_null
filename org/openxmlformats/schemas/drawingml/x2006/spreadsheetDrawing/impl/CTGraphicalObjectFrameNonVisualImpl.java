/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGraphicFrameProperties;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrameNonVisual;

public class CTGraphicalObjectFrameNonVisualImpl
extends XmlComplexContentImpl
implements CTGraphicalObjectFrameNonVisual {
    private static final long serialVersionUID = 1L;
    private static final QName CNVPR$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "cNvPr");
    private static final QName CNVGRAPHICFRAMEPR$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "cNvGraphicFramePr");

    public CTGraphicalObjectFrameNonVisualImpl(SchemaType schemaType) {
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
    public CTNonVisualGraphicFrameProperties getCNvGraphicFramePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNonVisualGraphicFrameProperties cTNonVisualGraphicFrameProperties = null;
            cTNonVisualGraphicFrameProperties = (CTNonVisualGraphicFrameProperties)((Object)this.get_store().find_element_user(CNVGRAPHICFRAMEPR$2, 0));
            if (cTNonVisualGraphicFrameProperties == null) {
                return null;
            }
            return cTNonVisualGraphicFrameProperties;
        }
    }

    @Override
    public void setCNvGraphicFramePr(CTNonVisualGraphicFrameProperties cTNonVisualGraphicFrameProperties) {
        this.generatedSetterHelperImpl(cTNonVisualGraphicFrameProperties, CNVGRAPHICFRAMEPR$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNonVisualGraphicFrameProperties addNewCNvGraphicFramePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNonVisualGraphicFrameProperties cTNonVisualGraphicFrameProperties = null;
            cTNonVisualGraphicFrameProperties = (CTNonVisualGraphicFrameProperties)((Object)this.get_store().add_element_user(CNVGRAPHICFRAMEPR$2));
            return cTNonVisualGraphicFrameProperties;
        }
    }
}

