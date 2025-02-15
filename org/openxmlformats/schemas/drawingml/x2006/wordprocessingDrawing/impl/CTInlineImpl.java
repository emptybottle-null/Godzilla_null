/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTEffectExtent
 */
package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGraphicFrameProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTEffectExtent;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapDistance;

public class CTInlineImpl
extends XmlComplexContentImpl
implements CTInline {
    private static final long serialVersionUID = 1L;
    private static final QName EXTENT$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "extent");
    private static final QName EFFECTEXTENT$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "effectExtent");
    private static final QName DOCPR$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "docPr");
    private static final QName CNVGRAPHICFRAMEPR$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "cNvGraphicFramePr");
    private static final QName GRAPHIC$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "graphic");
    private static final QName DISTT$10 = new QName("", "distT");
    private static final QName DISTB$12 = new QName("", "distB");
    private static final QName DISTL$14 = new QName("", "distL");
    private static final QName DISTR$16 = new QName("", "distR");

    public CTInlineImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositiveSize2D getExtent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositiveSize2D cTPositiveSize2D = null;
            cTPositiveSize2D = (CTPositiveSize2D)((Object)this.get_store().find_element_user(EXTENT$0, 0));
            if (cTPositiveSize2D == null) {
                return null;
            }
            return cTPositiveSize2D;
        }
    }

    @Override
    public void setExtent(CTPositiveSize2D cTPositiveSize2D) {
        this.generatedSetterHelperImpl(cTPositiveSize2D, EXTENT$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPositiveSize2D addNewExtent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPositiveSize2D cTPositiveSize2D = null;
            cTPositiveSize2D = (CTPositiveSize2D)((Object)this.get_store().add_element_user(EXTENT$0));
            return cTPositiveSize2D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectExtent getEffectExtent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectExtent cTEffectExtent = null;
            cTEffectExtent = (CTEffectExtent)this.get_store().find_element_user(EFFECTEXTENT$2, 0);
            if (cTEffectExtent == null) {
                return null;
            }
            return cTEffectExtent;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetEffectExtent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(EFFECTEXTENT$2) != 0;
        }
    }

    @Override
    public void setEffectExtent(CTEffectExtent cTEffectExtent) {
        this.generatedSetterHelperImpl((XmlObject)cTEffectExtent, EFFECTEXTENT$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTEffectExtent addNewEffectExtent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTEffectExtent cTEffectExtent = null;
            cTEffectExtent = (CTEffectExtent)this.get_store().add_element_user(EFFECTEXTENT$2);
            return cTEffectExtent;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetEffectExtent() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(EFFECTEXTENT$2, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNonVisualDrawingProps getDocPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNonVisualDrawingProps cTNonVisualDrawingProps = null;
            cTNonVisualDrawingProps = (CTNonVisualDrawingProps)((Object)this.get_store().find_element_user(DOCPR$4, 0));
            if (cTNonVisualDrawingProps == null) {
                return null;
            }
            return cTNonVisualDrawingProps;
        }
    }

    @Override
    public void setDocPr(CTNonVisualDrawingProps cTNonVisualDrawingProps) {
        this.generatedSetterHelperImpl(cTNonVisualDrawingProps, DOCPR$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTNonVisualDrawingProps addNewDocPr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTNonVisualDrawingProps cTNonVisualDrawingProps = null;
            cTNonVisualDrawingProps = (CTNonVisualDrawingProps)((Object)this.get_store().add_element_user(DOCPR$4));
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
            cTNonVisualGraphicFrameProperties = (CTNonVisualGraphicFrameProperties)((Object)this.get_store().find_element_user(CNVGRAPHICFRAMEPR$6, 0));
            if (cTNonVisualGraphicFrameProperties == null) {
                return null;
            }
            return cTNonVisualGraphicFrameProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetCNvGraphicFramePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(CNVGRAPHICFRAMEPR$6) != 0;
        }
    }

    @Override
    public void setCNvGraphicFramePr(CTNonVisualGraphicFrameProperties cTNonVisualGraphicFrameProperties) {
        this.generatedSetterHelperImpl(cTNonVisualGraphicFrameProperties, CNVGRAPHICFRAMEPR$6, 0, (short)1);
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
            cTNonVisualGraphicFrameProperties = (CTNonVisualGraphicFrameProperties)((Object)this.get_store().add_element_user(CNVGRAPHICFRAMEPR$6));
            return cTNonVisualGraphicFrameProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetCNvGraphicFramePr() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(CNVGRAPHICFRAMEPR$6, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGraphicalObject getGraphic() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGraphicalObject cTGraphicalObject = null;
            cTGraphicalObject = (CTGraphicalObject)((Object)this.get_store().find_element_user(GRAPHIC$8, 0));
            if (cTGraphicalObject == null) {
                return null;
            }
            return cTGraphicalObject;
        }
    }

    @Override
    public void setGraphic(CTGraphicalObject cTGraphicalObject) {
        this.generatedSetterHelperImpl(cTGraphicalObject, GRAPHIC$8, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTGraphicalObject addNewGraphic() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTGraphicalObject cTGraphicalObject = null;
            cTGraphicalObject = (CTGraphicalObject)((Object)this.get_store().add_element_user(GRAPHIC$8));
            return cTGraphicalObject;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getDistT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DISTT$10));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STWrapDistance xgetDistT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STWrapDistance sTWrapDistance = null;
            sTWrapDistance = (STWrapDistance)((Object)this.get_store().find_attribute_user(DISTT$10));
            return sTWrapDistance;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDistT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DISTT$10) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDistT(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DISTT$10));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DISTT$10));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDistT(STWrapDistance sTWrapDistance) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STWrapDistance sTWrapDistance2 = null;
            sTWrapDistance2 = (STWrapDistance)((Object)this.get_store().find_attribute_user(DISTT$10));
            if (sTWrapDistance2 == null) {
                sTWrapDistance2 = (STWrapDistance)((Object)this.get_store().add_attribute_user(DISTT$10));
            }
            sTWrapDistance2.set(sTWrapDistance);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDistT() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DISTT$10);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getDistB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DISTB$12));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STWrapDistance xgetDistB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STWrapDistance sTWrapDistance = null;
            sTWrapDistance = (STWrapDistance)((Object)this.get_store().find_attribute_user(DISTB$12));
            return sTWrapDistance;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDistB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DISTB$12) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDistB(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DISTB$12));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DISTB$12));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDistB(STWrapDistance sTWrapDistance) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STWrapDistance sTWrapDistance2 = null;
            sTWrapDistance2 = (STWrapDistance)((Object)this.get_store().find_attribute_user(DISTB$12));
            if (sTWrapDistance2 == null) {
                sTWrapDistance2 = (STWrapDistance)((Object)this.get_store().add_attribute_user(DISTB$12));
            }
            sTWrapDistance2.set(sTWrapDistance);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDistB() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DISTB$12);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getDistL() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DISTL$14));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STWrapDistance xgetDistL() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STWrapDistance sTWrapDistance = null;
            sTWrapDistance = (STWrapDistance)((Object)this.get_store().find_attribute_user(DISTL$14));
            return sTWrapDistance;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDistL() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DISTL$14) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDistL(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DISTL$14));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DISTL$14));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDistL(STWrapDistance sTWrapDistance) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STWrapDistance sTWrapDistance2 = null;
            sTWrapDistance2 = (STWrapDistance)((Object)this.get_store().find_attribute_user(DISTL$14));
            if (sTWrapDistance2 == null) {
                sTWrapDistance2 = (STWrapDistance)((Object)this.get_store().add_attribute_user(DISTL$14));
            }
            sTWrapDistance2.set(sTWrapDistance);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDistL() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DISTL$14);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getDistR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DISTR$16));
            if (simpleValue == null) {
                return 0L;
            }
            return simpleValue.getLongValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public STWrapDistance xgetDistR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STWrapDistance sTWrapDistance = null;
            sTWrapDistance = (STWrapDistance)((Object)this.get_store().find_attribute_user(DISTR$16));
            return sTWrapDistance;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetDistR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(DISTR$16) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setDistR(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DISTR$16));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DISTR$16));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetDistR(STWrapDistance sTWrapDistance) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            STWrapDistance sTWrapDistance2 = null;
            sTWrapDistance2 = (STWrapDistance)((Object)this.get_store().find_attribute_user(DISTR$16));
            if (sTWrapDistance2 == null) {
                sTWrapDistance2 = (STWrapDistance)((Object)this.get_store().add_attribute_user(DISTR$16));
            }
            sTWrapDistance2.set(sTWrapDistance);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetDistR() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(DISTR$16);
        }
    }
}

