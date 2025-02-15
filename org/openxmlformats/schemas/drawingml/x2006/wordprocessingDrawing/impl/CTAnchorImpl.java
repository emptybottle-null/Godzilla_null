/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTEffectExtent
 *  org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH
 *  org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosV
 *  org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapNone
 *  org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
 *  org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapThrough
 *  org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapTight
 *  org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapTopBottom
 */
package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGraphicFrameProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTEffectExtent;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosH;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTPosV;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapNone;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapThrough;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapTight;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapTopBottom;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapDistance;

public class CTAnchorImpl
extends XmlComplexContentImpl
implements CTAnchor {
    private static final long serialVersionUID = 1L;
    private static final QName SIMPLEPOS$0 = new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "simplePos");
    private static final QName POSITIONH$2 = new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "positionH");
    private static final QName POSITIONV$4 = new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "positionV");
    private static final QName EXTENT$6 = new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "extent");
    private static final QName EFFECTEXTENT$8 = new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "effectExtent");
    private static final QName WRAPNONE$10 = new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "wrapNone");
    private static final QName WRAPSQUARE$12 = new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "wrapSquare");
    private static final QName WRAPTIGHT$14 = new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "wrapTight");
    private static final QName WRAPTHROUGH$16 = new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "wrapThrough");
    private static final QName WRAPTOPANDBOTTOM$18 = new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "wrapTopAndBottom");
    private static final QName DOCPR$20 = new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "docPr");
    private static final QName CNVGRAPHICFRAMEPR$22 = new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "cNvGraphicFramePr");
    private static final QName GRAPHIC$24 = new QName("http://schemas.openxmlformats.org/drawingml/2006/main", "graphic");
    private static final QName DISTT$26 = new QName("", "distT");
    private static final QName DISTB$28 = new QName("", "distB");
    private static final QName DISTL$30 = new QName("", "distL");
    private static final QName DISTR$32 = new QName("", "distR");
    private static final QName SIMPLEPOS2$34 = new QName("", "simplePos");
    private static final QName RELATIVEHEIGHT$36 = new QName("", "relativeHeight");
    private static final QName BEHINDDOC$38 = new QName("", "behindDoc");
    private static final QName LOCKED$40 = new QName("", "locked");
    private static final QName LAYOUTINCELL$42 = new QName("", "layoutInCell");
    private static final QName HIDDEN$44 = new QName("", "hidden");
    private static final QName ALLOWOVERLAP$46 = new QName("", "allowOverlap");

    public CTAnchorImpl(SchemaType schemaType) {
        super(schemaType);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPoint2D getSimplePos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPoint2D cTPoint2D = null;
            cTPoint2D = (CTPoint2D)((Object)this.get_store().find_element_user(SIMPLEPOS$0, 0));
            if (cTPoint2D == null) {
                return null;
            }
            return cTPoint2D;
        }
    }

    @Override
    public void setSimplePos(CTPoint2D cTPoint2D) {
        this.generatedSetterHelperImpl(cTPoint2D, SIMPLEPOS$0, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPoint2D addNewSimplePos() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPoint2D cTPoint2D = null;
            cTPoint2D = (CTPoint2D)((Object)this.get_store().add_element_user(SIMPLEPOS$0));
            return cTPoint2D;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPosH getPositionH() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPosH cTPosH = null;
            cTPosH = (CTPosH)this.get_store().find_element_user(POSITIONH$2, 0);
            if (cTPosH == null) {
                return null;
            }
            return cTPosH;
        }
    }

    @Override
    public void setPositionH(CTPosH cTPosH) {
        this.generatedSetterHelperImpl((XmlObject)cTPosH, POSITIONH$2, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPosH addNewPositionH() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPosH cTPosH = null;
            cTPosH = (CTPosH)this.get_store().add_element_user(POSITIONH$2);
            return cTPosH;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPosV getPositionV() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPosV cTPosV = null;
            cTPosV = (CTPosV)this.get_store().find_element_user(POSITIONV$4, 0);
            if (cTPosV == null) {
                return null;
            }
            return cTPosV;
        }
    }

    @Override
    public void setPositionV(CTPosV cTPosV) {
        this.generatedSetterHelperImpl((XmlObject)cTPosV, POSITIONV$4, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTPosV addNewPositionV() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTPosV cTPosV = null;
            cTPosV = (CTPosV)this.get_store().add_element_user(POSITIONV$4);
            return cTPosV;
        }
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
            cTPositiveSize2D = (CTPositiveSize2D)((Object)this.get_store().find_element_user(EXTENT$6, 0));
            if (cTPositiveSize2D == null) {
                return null;
            }
            return cTPositiveSize2D;
        }
    }

    @Override
    public void setExtent(CTPositiveSize2D cTPositiveSize2D) {
        this.generatedSetterHelperImpl(cTPositiveSize2D, EXTENT$6, 0, (short)1);
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
            cTPositiveSize2D = (CTPositiveSize2D)((Object)this.get_store().add_element_user(EXTENT$6));
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
            cTEffectExtent = (CTEffectExtent)this.get_store().find_element_user(EFFECTEXTENT$8, 0);
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
            return this.get_store().count_elements(EFFECTEXTENT$8) != 0;
        }
    }

    @Override
    public void setEffectExtent(CTEffectExtent cTEffectExtent) {
        this.generatedSetterHelperImpl((XmlObject)cTEffectExtent, EFFECTEXTENT$8, 0, (short)1);
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
            cTEffectExtent = (CTEffectExtent)this.get_store().add_element_user(EFFECTEXTENT$8);
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
            this.get_store().remove_element(EFFECTEXTENT$8, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWrapNone getWrapNone() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWrapNone cTWrapNone = null;
            cTWrapNone = (CTWrapNone)this.get_store().find_element_user(WRAPNONE$10, 0);
            if (cTWrapNone == null) {
                return null;
            }
            return cTWrapNone;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWrapNone() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WRAPNONE$10) != 0;
        }
    }

    @Override
    public void setWrapNone(CTWrapNone cTWrapNone) {
        this.generatedSetterHelperImpl((XmlObject)cTWrapNone, WRAPNONE$10, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWrapNone addNewWrapNone() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWrapNone cTWrapNone = null;
            cTWrapNone = (CTWrapNone)this.get_store().add_element_user(WRAPNONE$10);
            return cTWrapNone;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWrapNone() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WRAPNONE$10, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWrapSquare getWrapSquare() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWrapSquare cTWrapSquare = null;
            cTWrapSquare = (CTWrapSquare)this.get_store().find_element_user(WRAPSQUARE$12, 0);
            if (cTWrapSquare == null) {
                return null;
            }
            return cTWrapSquare;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWrapSquare() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WRAPSQUARE$12) != 0;
        }
    }

    @Override
    public void setWrapSquare(CTWrapSquare cTWrapSquare) {
        this.generatedSetterHelperImpl((XmlObject)cTWrapSquare, WRAPSQUARE$12, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWrapSquare addNewWrapSquare() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWrapSquare cTWrapSquare = null;
            cTWrapSquare = (CTWrapSquare)this.get_store().add_element_user(WRAPSQUARE$12);
            return cTWrapSquare;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWrapSquare() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WRAPSQUARE$12, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWrapTight getWrapTight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWrapTight cTWrapTight = null;
            cTWrapTight = (CTWrapTight)this.get_store().find_element_user(WRAPTIGHT$14, 0);
            if (cTWrapTight == null) {
                return null;
            }
            return cTWrapTight;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWrapTight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WRAPTIGHT$14) != 0;
        }
    }

    @Override
    public void setWrapTight(CTWrapTight cTWrapTight) {
        this.generatedSetterHelperImpl((XmlObject)cTWrapTight, WRAPTIGHT$14, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWrapTight addNewWrapTight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWrapTight cTWrapTight = null;
            cTWrapTight = (CTWrapTight)this.get_store().add_element_user(WRAPTIGHT$14);
            return cTWrapTight;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWrapTight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WRAPTIGHT$14, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWrapThrough getWrapThrough() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWrapThrough cTWrapThrough = null;
            cTWrapThrough = (CTWrapThrough)this.get_store().find_element_user(WRAPTHROUGH$16, 0);
            if (cTWrapThrough == null) {
                return null;
            }
            return cTWrapThrough;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWrapThrough() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WRAPTHROUGH$16) != 0;
        }
    }

    @Override
    public void setWrapThrough(CTWrapThrough cTWrapThrough) {
        this.generatedSetterHelperImpl((XmlObject)cTWrapThrough, WRAPTHROUGH$16, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWrapThrough addNewWrapThrough() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWrapThrough cTWrapThrough = null;
            cTWrapThrough = (CTWrapThrough)this.get_store().add_element_user(WRAPTHROUGH$16);
            return cTWrapThrough;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWrapThrough() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WRAPTHROUGH$16, 0);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWrapTopBottom getWrapTopAndBottom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWrapTopBottom cTWrapTopBottom = null;
            cTWrapTopBottom = (CTWrapTopBottom)this.get_store().find_element_user(WRAPTOPANDBOTTOM$18, 0);
            if (cTWrapTopBottom == null) {
                return null;
            }
            return cTWrapTopBottom;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetWrapTopAndBottom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().count_elements(WRAPTOPANDBOTTOM$18) != 0;
        }
    }

    @Override
    public void setWrapTopAndBottom(CTWrapTopBottom cTWrapTopBottom) {
        this.generatedSetterHelperImpl((XmlObject)cTWrapTopBottom, WRAPTOPANDBOTTOM$18, 0, (short)1);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public CTWrapTopBottom addNewWrapTopAndBottom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            CTWrapTopBottom cTWrapTopBottom = null;
            cTWrapTopBottom = (CTWrapTopBottom)this.get_store().add_element_user(WRAPTOPANDBOTTOM$18);
            return cTWrapTopBottom;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetWrapTopAndBottom() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_element(WRAPTOPANDBOTTOM$18, 0);
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
            cTNonVisualDrawingProps = (CTNonVisualDrawingProps)((Object)this.get_store().find_element_user(DOCPR$20, 0));
            if (cTNonVisualDrawingProps == null) {
                return null;
            }
            return cTNonVisualDrawingProps;
        }
    }

    @Override
    public void setDocPr(CTNonVisualDrawingProps cTNonVisualDrawingProps) {
        this.generatedSetterHelperImpl(cTNonVisualDrawingProps, DOCPR$20, 0, (short)1);
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
            cTNonVisualDrawingProps = (CTNonVisualDrawingProps)((Object)this.get_store().add_element_user(DOCPR$20));
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
            cTNonVisualGraphicFrameProperties = (CTNonVisualGraphicFrameProperties)((Object)this.get_store().find_element_user(CNVGRAPHICFRAMEPR$22, 0));
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
            return this.get_store().count_elements(CNVGRAPHICFRAMEPR$22) != 0;
        }
    }

    @Override
    public void setCNvGraphicFramePr(CTNonVisualGraphicFrameProperties cTNonVisualGraphicFrameProperties) {
        this.generatedSetterHelperImpl(cTNonVisualGraphicFrameProperties, CNVGRAPHICFRAMEPR$22, 0, (short)1);
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
            cTNonVisualGraphicFrameProperties = (CTNonVisualGraphicFrameProperties)((Object)this.get_store().add_element_user(CNVGRAPHICFRAMEPR$22));
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
            this.get_store().remove_element(CNVGRAPHICFRAMEPR$22, 0);
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
            cTGraphicalObject = (CTGraphicalObject)((Object)this.get_store().find_element_user(GRAPHIC$24, 0));
            if (cTGraphicalObject == null) {
                return null;
            }
            return cTGraphicalObject;
        }
    }

    @Override
    public void setGraphic(CTGraphicalObject cTGraphicalObject) {
        this.generatedSetterHelperImpl(cTGraphicalObject, GRAPHIC$24, 0, (short)1);
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
            cTGraphicalObject = (CTGraphicalObject)((Object)this.get_store().add_element_user(GRAPHIC$24));
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DISTT$26));
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
            sTWrapDistance = (STWrapDistance)((Object)this.get_store().find_attribute_user(DISTT$26));
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
            return this.get_store().find_attribute_user(DISTT$26) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DISTT$26));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DISTT$26));
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
            sTWrapDistance2 = (STWrapDistance)((Object)this.get_store().find_attribute_user(DISTT$26));
            if (sTWrapDistance2 == null) {
                sTWrapDistance2 = (STWrapDistance)((Object)this.get_store().add_attribute_user(DISTT$26));
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
            this.get_store().remove_attribute(DISTT$26);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DISTB$28));
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
            sTWrapDistance = (STWrapDistance)((Object)this.get_store().find_attribute_user(DISTB$28));
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
            return this.get_store().find_attribute_user(DISTB$28) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DISTB$28));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DISTB$28));
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
            sTWrapDistance2 = (STWrapDistance)((Object)this.get_store().find_attribute_user(DISTB$28));
            if (sTWrapDistance2 == null) {
                sTWrapDistance2 = (STWrapDistance)((Object)this.get_store().add_attribute_user(DISTB$28));
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
            this.get_store().remove_attribute(DISTB$28);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DISTL$30));
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
            sTWrapDistance = (STWrapDistance)((Object)this.get_store().find_attribute_user(DISTL$30));
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
            return this.get_store().find_attribute_user(DISTL$30) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DISTL$30));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DISTL$30));
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
            sTWrapDistance2 = (STWrapDistance)((Object)this.get_store().find_attribute_user(DISTL$30));
            if (sTWrapDistance2 == null) {
                sTWrapDistance2 = (STWrapDistance)((Object)this.get_store().add_attribute_user(DISTL$30));
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
            this.get_store().remove_attribute(DISTL$30);
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DISTR$32));
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
            sTWrapDistance = (STWrapDistance)((Object)this.get_store().find_attribute_user(DISTR$32));
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
            return this.get_store().find_attribute_user(DISTR$32) != null;
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
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(DISTR$32));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(DISTR$32));
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
            sTWrapDistance2 = (STWrapDistance)((Object)this.get_store().find_attribute_user(DISTR$32));
            if (sTWrapDistance2 == null) {
                sTWrapDistance2 = (STWrapDistance)((Object)this.get_store().add_attribute_user(DISTR$32));
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
            this.get_store().remove_attribute(DISTR$32);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getSimplePos2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SIMPLEPOS2$34));
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetSimplePos2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(SIMPLEPOS2$34));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetSimplePos2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(SIMPLEPOS2$34) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setSimplePos2(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(SIMPLEPOS2$34));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(SIMPLEPOS2$34));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetSimplePos2(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(SIMPLEPOS2$34));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(SIMPLEPOS2$34));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetSimplePos2() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(SIMPLEPOS2$34);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long getRelativeHeight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RELATIVEHEIGHT$36));
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
    public XmlUnsignedInt xgetRelativeHeight() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt = null;
            xmlUnsignedInt = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(RELATIVEHEIGHT$36));
            return xmlUnsignedInt;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setRelativeHeight(long l) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(RELATIVEHEIGHT$36));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(RELATIVEHEIGHT$36));
            }
            simpleValue.setLongValue(l);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetRelativeHeight(XmlUnsignedInt xmlUnsignedInt) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlUnsignedInt xmlUnsignedInt2 = null;
            xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().find_attribute_user(RELATIVEHEIGHT$36));
            if (xmlUnsignedInt2 == null) {
                xmlUnsignedInt2 = (XmlUnsignedInt)((Object)this.get_store().add_attribute_user(RELATIVEHEIGHT$36));
            }
            xmlUnsignedInt2.set(xmlUnsignedInt);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getBehindDoc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BEHINDDOC$38));
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetBehindDoc() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(BEHINDDOC$38));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBehindDoc(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(BEHINDDOC$38));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(BEHINDDOC$38));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetBehindDoc(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(BEHINDDOC$38));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(BEHINDDOC$38));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getLocked() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LOCKED$40));
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetLocked() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(LOCKED$40));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLocked(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LOCKED$40));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(LOCKED$40));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLocked(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(LOCKED$40));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(LOCKED$40));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getLayoutInCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LAYOUTINCELL$42));
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetLayoutInCell() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(LAYOUTINCELL$42));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setLayoutInCell(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(LAYOUTINCELL$42));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(LAYOUTINCELL$42));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetLayoutInCell(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(LAYOUTINCELL$42));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(LAYOUTINCELL$42));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HIDDEN$44));
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(HIDDEN$44));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isSetHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return this.get_store().find_attribute_user(HIDDEN$44) != null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setHidden(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(HIDDEN$44));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(HIDDEN$44));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetHidden(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(HIDDEN$44));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(HIDDEN$44));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void unsetHidden() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            this.get_store().remove_attribute(HIDDEN$44);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean getAllowOverlap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALLOWOVERLAP$46));
            if (simpleValue == null) {
                return false;
            }
            return simpleValue.getBooleanValue();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlBoolean xgetAllowOverlap() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean = null;
            xmlBoolean = (XmlBoolean)((Object)this.get_store().find_attribute_user(ALLOWOVERLAP$46));
            return xmlBoolean;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setAllowOverlap(boolean bl) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            SimpleValue simpleValue = null;
            simpleValue = (SimpleValue)((Object)this.get_store().find_attribute_user(ALLOWOVERLAP$46));
            if (simpleValue == null) {
                simpleValue = (SimpleValue)((Object)this.get_store().add_attribute_user(ALLOWOVERLAP$46));
            }
            simpleValue.setBooleanValue(bl);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void xsetAllowOverlap(XmlBoolean xmlBoolean) {
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            XmlBoolean xmlBoolean2 = null;
            xmlBoolean2 = (XmlBoolean)((Object)this.get_store().find_attribute_user(ALLOWOVERLAP$46));
            if (xmlBoolean2 == null) {
                xmlBoolean2 = (XmlBoolean)((Object)this.get_store().add_attribute_user(ALLOWOVERLAP$46));
            }
            xmlBoolean2.set(xmlBoolean);
        }
    }
}

