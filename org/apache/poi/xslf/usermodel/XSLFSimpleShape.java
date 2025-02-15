/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.draw.geom.CustomGeometry;
import org.apache.poi.sl.draw.geom.Guide;
import org.apache.poi.sl.draw.geom.PresetGeometries;
import org.apache.poi.sl.usermodel.FillStyle;
import org.apache.poi.sl.usermodel.LineDecoration;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.sl.usermodel.StrokeStyle;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.model.PropertyFetcher;
import org.apache.poi.xslf.usermodel.XSLFColor;
import org.apache.poi.xslf.usermodel.XSLFHyperlink;
import org.apache.poi.xslf.usermodel.XSLFPropertiesDelegate;
import org.apache.poi.xslf.usermodel.XSLFShadow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTheme;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetLineDashProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrix;
import org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrixReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineCap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndLength;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndType;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndWidth;
import org.openxmlformats.schemas.drawingml.x2006.main.STPresetLineDashVal;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;

public abstract class XSLFSimpleShape
extends XSLFShape
implements SimpleShape<XSLFShape, XSLFTextParagraph> {
    private static CTOuterShadowEffect NO_SHADOW = CTOuterShadowEffect.Factory.newInstance();
    private static final POILogger LOG = POILogFactory.getLogger(XSLFSimpleShape.class);

    XSLFSimpleShape(XmlObject shape, XSLFSheet sheet) {
        super(shape, sheet);
    }

    @Override
    public void setShapeType(ShapeType type) {
        XSLFPropertiesDelegate.XSLFGeometryProperties gp = XSLFPropertiesDelegate.getGeometryDelegate(this.getShapeProperties());
        if (gp == null) {
            return;
        }
        if (gp.isSetCustGeom()) {
            gp.unsetCustGeom();
        }
        CTPresetGeometry2D prst = gp.isSetPrstGeom() ? gp.getPrstGeom() : gp.addNewPrstGeom();
        prst.setPrst(STShapeType.Enum.forInt(type.ooxmlId));
    }

    @Override
    public ShapeType getShapeType() {
        STShapeType.Enum geom;
        XSLFPropertiesDelegate.XSLFGeometryProperties gp = XSLFPropertiesDelegate.getGeometryDelegate(this.getShapeProperties());
        if (gp != null && gp.isSetPrstGeom() && (geom = gp.getPrstGeom().getPrst()) != null) {
            return ShapeType.forId(geom.intValue(), true);
        }
        return null;
    }

    protected CTTransform2D getXfrm(boolean create) {
        PropertyFetcher<CTTransform2D> fetcher = new PropertyFetcher<CTTransform2D>(){

            @Override
            public boolean fetch(XSLFShape shape) {
                XmlObject xo = shape.getShapeProperties();
                if (xo instanceof CTShapeProperties && ((CTShapeProperties)xo).isSetXfrm()) {
                    this.setValue(((CTShapeProperties)xo).getXfrm());
                    return true;
                }
                return false;
            }
        };
        this.fetchShapeProperty(fetcher);
        CTTransform2D xfrm = (CTTransform2D)fetcher.getValue();
        if (!create || xfrm != null) {
            return xfrm;
        }
        XmlObject xo = this.getShapeProperties();
        if (xo instanceof CTShapeProperties) {
            return ((CTShapeProperties)xo).addNewXfrm();
        }
        LOG.log(5, this.getClass() + " doesn't have xfrm element.");
        return null;
    }

    @Override
    public Rectangle2D getAnchor() {
        CTTransform2D xfrm = this.getXfrm(false);
        if (xfrm == null || !xfrm.isSetOff()) {
            return null;
        }
        CTPoint2D off = xfrm.getOff();
        double x = Units.toPoints(off.getX());
        double y = Units.toPoints(off.getY());
        CTPositiveSize2D ext = xfrm.getExt();
        double cx = Units.toPoints(ext.getCx());
        double cy = Units.toPoints(ext.getCy());
        return new Rectangle2D.Double(x, y, cx, cy);
    }

    @Override
    public void setAnchor(Rectangle2D anchor) {
        CTTransform2D xfrm = this.getXfrm(true);
        if (xfrm == null) {
            return;
        }
        CTPoint2D off = xfrm.isSetOff() ? xfrm.getOff() : xfrm.addNewOff();
        long x = Units.toEMU(anchor.getX());
        long y = Units.toEMU(anchor.getY());
        off.setX(x);
        off.setY(y);
        CTPositiveSize2D ext = xfrm.isSetExt() ? xfrm.getExt() : xfrm.addNewExt();
        long cx = Units.toEMU(anchor.getWidth());
        long cy = Units.toEMU(anchor.getHeight());
        ext.setCx(cx);
        ext.setCy(cy);
    }

    @Override
    public void setRotation(double theta) {
        CTTransform2D xfrm = this.getXfrm(true);
        if (xfrm != null) {
            xfrm.setRot((int)(theta * 60000.0));
        }
    }

    @Override
    public double getRotation() {
        CTTransform2D xfrm = this.getXfrm(false);
        return xfrm == null || !xfrm.isSetRot() ? 0.0 : (double)xfrm.getRot() / 60000.0;
    }

    @Override
    public void setFlipHorizontal(boolean flip) {
        CTTransform2D xfrm = this.getXfrm(true);
        if (xfrm != null) {
            xfrm.setFlipH(flip);
        }
    }

    @Override
    public void setFlipVertical(boolean flip) {
        CTTransform2D xfrm = this.getXfrm(true);
        if (xfrm != null) {
            xfrm.setFlipV(flip);
        }
    }

    @Override
    public boolean getFlipHorizontal() {
        CTTransform2D xfrm = this.getXfrm(false);
        return xfrm != null && xfrm.isSetFlipH() && xfrm.getFlipH();
    }

    @Override
    public boolean getFlipVertical() {
        CTTransform2D xfrm = this.getXfrm(false);
        return xfrm != null && xfrm.isSetFlipV() && xfrm.getFlipV();
    }

    private CTLineProperties getDefaultLineProperties() {
        CTShapeStyle style = this.getSpStyle();
        if (style == null) {
            return null;
        }
        CTStyleMatrixReference lnRef = style.getLnRef();
        if (lnRef == null) {
            return null;
        }
        int idx = Math.toIntExact(lnRef.getIdx());
        XSLFTheme theme = this.getSheet().getTheme();
        if (theme == null) {
            return null;
        }
        CTBaseStyles styles = theme.getXmlObject().getThemeElements();
        if (styles == null) {
            return null;
        }
        CTStyleMatrix styleMatrix = styles.getFmtScheme();
        if (styleMatrix == null) {
            return null;
        }
        CTLineStyleList lineStyles = styleMatrix.getLnStyleLst();
        if (lineStyles == null || lineStyles.sizeOfLnArray() < idx) {
            return null;
        }
        return lineStyles.getLnArray(idx - 1);
    }

    public void setLineColor(Color color) {
        CTLineProperties ln = XSLFSimpleShape.getLn(this, true);
        if (ln == null) {
            return;
        }
        if (ln.isSetSolidFill()) {
            ln.unsetSolidFill();
        }
        if (ln.isSetGradFill()) {
            ln.unsetGradFill();
        }
        if (ln.isSetPattFill()) {
            ln.unsetPattFill();
        }
        if (ln.isSetNoFill()) {
            ln.unsetNoFill();
        }
        if (color == null) {
            ln.addNewNoFill();
        } else {
            CTSolidColorFillProperties fill = ln.addNewSolidFill();
            XSLFColor col = new XSLFColor(fill, this.getSheet().getTheme(), fill.getSchemeClr(), this.getSheet());
            col.setColor(color);
        }
    }

    public Color getLineColor() {
        PaintStyle ps = this.getLinePaint();
        if (ps instanceof PaintStyle.SolidPaint) {
            return ((PaintStyle.SolidPaint)ps).getSolidColor().getColor();
        }
        return null;
    }

    protected PaintStyle getLinePaint() {
        XSLFSheet sheet = this.getSheet();
        final XSLFTheme theme = sheet.getTheme();
        final boolean hasPlaceholder = this.getPlaceholder() != null;
        PropertyFetcher<PaintStyle> fetcher = new PropertyFetcher<PaintStyle>(){

            @Override
            public boolean fetch(XSLFShape shape) {
                CTLineProperties spPr = XSLFSimpleShape.getLn(shape, false);
                XSLFPropertiesDelegate.XSLFFillProperties fp = XSLFPropertiesDelegate.getFillDelegate(spPr);
                if (fp != null && fp.isSetNoFill()) {
                    this.setValue(null);
                    return true;
                }
                PackagePart pp = shape.getSheet().getPackagePart();
                PaintStyle paint = XSLFSimpleShape.this.selectPaint(fp, null, pp, theme, hasPlaceholder);
                if (paint != null) {
                    this.setValue(paint);
                    return true;
                }
                CTShapeStyle style = shape.getSpStyle();
                if (style != null && (paint = XSLFSimpleShape.this.selectPaint(fp = XSLFPropertiesDelegate.getFillDelegate(style.getLnRef()), null, pp, theme, hasPlaceholder)) == null) {
                    paint = this.getThemePaint(style, pp);
                }
                if (paint != null) {
                    this.setValue(paint);
                    return true;
                }
                return false;
            }

            PaintStyle getThemePaint(CTShapeStyle style, PackagePart pp) {
                CTStyleMatrixReference lnRef = style.getLnRef();
                if (lnRef == null) {
                    return null;
                }
                int idx = Math.toIntExact(lnRef.getIdx());
                CTSchemeColor phClr = lnRef.getSchemeClr();
                if (idx <= 0) {
                    return null;
                }
                CTLineProperties props = theme.getXmlObject().getThemeElements().getFmtScheme().getLnStyleLst().getLnArray(idx - 1);
                XSLFPropertiesDelegate.XSLFFillProperties fp = XSLFPropertiesDelegate.getFillDelegate(props);
                return XSLFSimpleShape.this.selectPaint(fp, phClr, pp, theme, hasPlaceholder);
            }
        };
        this.fetchShapeProperty(fetcher);
        return (PaintStyle)fetcher.getValue();
    }

    public void setLineWidth(double width) {
        CTLineProperties lnPr = XSLFSimpleShape.getLn(this, true);
        if (lnPr == null) {
            return;
        }
        if (width == 0.0) {
            if (lnPr.isSetW()) {
                lnPr.unsetW();
            }
            if (!lnPr.isSetNoFill()) {
                lnPr.addNewNoFill();
            }
            if (lnPr.isSetSolidFill()) {
                lnPr.unsetSolidFill();
            }
            if (lnPr.isSetGradFill()) {
                lnPr.unsetGradFill();
            }
            if (lnPr.isSetPattFill()) {
                lnPr.unsetPattFill();
            }
        } else {
            if (lnPr.isSetNoFill()) {
                lnPr.unsetNoFill();
            }
            lnPr.setW(Units.toEMU(width));
        }
    }

    public double getLineWidth() {
        PropertyFetcher<Double> fetcher = new PropertyFetcher<Double>(){

            @Override
            public boolean fetch(XSLFShape shape) {
                CTLineProperties ln = XSLFSimpleShape.getLn(shape, false);
                if (ln != null) {
                    if (ln.isSetNoFill()) {
                        this.setValue(0.0);
                        return true;
                    }
                    if (ln.isSetW()) {
                        this.setValue(Units.toPoints(ln.getW()));
                        return true;
                    }
                }
                return false;
            }
        };
        this.fetchShapeProperty(fetcher);
        double lineWidth = 0.0;
        if (fetcher.getValue() == null) {
            CTLineProperties defaultLn = this.getDefaultLineProperties();
            if (defaultLn != null && defaultLn.isSetW()) {
                lineWidth = Units.toPoints(defaultLn.getW());
            }
        } else {
            lineWidth = (Double)fetcher.getValue();
        }
        return lineWidth;
    }

    public void setLineCompound(StrokeStyle.LineCompound compound) {
        CTLineProperties ln = XSLFSimpleShape.getLn(this, true);
        if (ln == null) {
            return;
        }
        if (compound == null) {
            if (ln.isSetCmpd()) {
                ln.unsetCmpd();
            }
        } else {
            STCompoundLine.Enum xCmpd;
            switch (compound) {
                default: {
                    xCmpd = STCompoundLine.SNG;
                    break;
                }
                case DOUBLE: {
                    xCmpd = STCompoundLine.DBL;
                    break;
                }
                case THICK_THIN: {
                    xCmpd = STCompoundLine.THICK_THIN;
                    break;
                }
                case THIN_THICK: {
                    xCmpd = STCompoundLine.THIN_THICK;
                    break;
                }
                case TRIPLE: {
                    xCmpd = STCompoundLine.TRI;
                }
            }
            ln.setCmpd(xCmpd);
        }
    }

    public StrokeStyle.LineCompound getLineCompound() {
        CTLineProperties defaultLn;
        PropertyFetcher<Integer> fetcher = new PropertyFetcher<Integer>(){

            @Override
            public boolean fetch(XSLFShape shape) {
                STCompoundLine.Enum stCmpd;
                CTLineProperties ln = XSLFSimpleShape.getLn(shape, false);
                if (ln != null && (stCmpd = ln.getCmpd()) != null) {
                    this.setValue(stCmpd.intValue());
                    return true;
                }
                return false;
            }
        };
        this.fetchShapeProperty(fetcher);
        Integer cmpd = (Integer)fetcher.getValue();
        if (cmpd == null && (defaultLn = this.getDefaultLineProperties()) != null && defaultLn.isSetCmpd()) {
            switch (defaultLn.getCmpd().intValue()) {
                default: {
                    return StrokeStyle.LineCompound.SINGLE;
                }
                case 2: {
                    return StrokeStyle.LineCompound.DOUBLE;
                }
                case 3: {
                    return StrokeStyle.LineCompound.THICK_THIN;
                }
                case 4: {
                    return StrokeStyle.LineCompound.THIN_THICK;
                }
                case 5: 
            }
            return StrokeStyle.LineCompound.TRIPLE;
        }
        return null;
    }

    public void setLineDash(StrokeStyle.LineDash dash) {
        CTLineProperties ln = XSLFSimpleShape.getLn(this, true);
        if (ln == null) {
            return;
        }
        if (dash == null) {
            if (ln.isSetPrstDash()) {
                ln.unsetPrstDash();
            }
        } else {
            CTPresetLineDashProperties ldp = ln.isSetPrstDash() ? ln.getPrstDash() : ln.addNewPrstDash();
            ldp.setVal(STPresetLineDashVal.Enum.forInt(dash.ooxmlId));
        }
    }

    public StrokeStyle.LineDash getLineDash() {
        CTLineProperties defaultLn;
        PropertyFetcher<StrokeStyle.LineDash> fetcher = new PropertyFetcher<StrokeStyle.LineDash>(){

            @Override
            public boolean fetch(XSLFShape shape) {
                CTLineProperties ln = XSLFSimpleShape.getLn(shape, false);
                if (ln == null || !ln.isSetPrstDash()) {
                    return false;
                }
                this.setValue(StrokeStyle.LineDash.fromOoxmlId(ln.getPrstDash().getVal().intValue()));
                return true;
            }
        };
        this.fetchShapeProperty(fetcher);
        StrokeStyle.LineDash dash = (StrokeStyle.LineDash)((Object)fetcher.getValue());
        if (dash == null && (defaultLn = this.getDefaultLineProperties()) != null && defaultLn.isSetPrstDash()) {
            dash = StrokeStyle.LineDash.fromOoxmlId(defaultLn.getPrstDash().getVal().intValue());
        }
        return dash;
    }

    public void setLineCap(StrokeStyle.LineCap cap) {
        CTLineProperties ln = XSLFSimpleShape.getLn(this, true);
        if (ln == null) {
            return;
        }
        if (cap == null) {
            if (ln.isSetCap()) {
                ln.unsetCap();
            }
        } else {
            ln.setCap(STLineCap.Enum.forInt(cap.ooxmlId));
        }
    }

    public StrokeStyle.LineCap getLineCap() {
        CTLineProperties defaultLn;
        PropertyFetcher<StrokeStyle.LineCap> fetcher = new PropertyFetcher<StrokeStyle.LineCap>(){

            @Override
            public boolean fetch(XSLFShape shape) {
                CTLineProperties ln = XSLFSimpleShape.getLn(shape, false);
                if (ln != null && ln.isSetCap()) {
                    this.setValue(StrokeStyle.LineCap.fromOoxmlId(ln.getCap().intValue()));
                    return true;
                }
                return false;
            }
        };
        this.fetchShapeProperty(fetcher);
        StrokeStyle.LineCap cap = (StrokeStyle.LineCap)((Object)fetcher.getValue());
        if (cap == null && (defaultLn = this.getDefaultLineProperties()) != null && defaultLn.isSetCap()) {
            cap = StrokeStyle.LineCap.fromOoxmlId(defaultLn.getCap().intValue());
        }
        return cap;
    }

    @Override
    public void setFillColor(Color color) {
        XSLFPropertiesDelegate.XSLFFillProperties fp = XSLFPropertiesDelegate.getFillDelegate(this.getShapeProperties());
        if (fp == null) {
            return;
        }
        if (color == null) {
            if (fp.isSetSolidFill()) {
                fp.unsetSolidFill();
            }
            if (fp.isSetGradFill()) {
                fp.unsetGradFill();
            }
            if (fp.isSetPattFill()) {
                fp.unsetGradFill();
            }
            if (fp.isSetBlipFill()) {
                fp.unsetBlipFill();
            }
            if (!fp.isSetNoFill()) {
                fp.addNewNoFill();
            }
        } else {
            if (fp.isSetNoFill()) {
                fp.unsetNoFill();
            }
            CTSolidColorFillProperties fill = fp.isSetSolidFill() ? fp.getSolidFill() : fp.addNewSolidFill();
            XSLFColor col = new XSLFColor(fill, this.getSheet().getTheme(), fill.getSchemeClr(), this.getSheet());
            col.setColor(color);
        }
    }

    @Override
    public Color getFillColor() {
        PaintStyle ps = this.getFillPaint();
        if (ps instanceof PaintStyle.SolidPaint) {
            return DrawPaint.applyColorTransform(((PaintStyle.SolidPaint)ps).getSolidColor());
        }
        return null;
    }

    public XSLFShadow getShadow() {
        int idx;
        CTShapeStyle style;
        PropertyFetcher<CTOuterShadowEffect> fetcher = new PropertyFetcher<CTOuterShadowEffect>(){

            @Override
            public boolean fetch(XSLFShape shape) {
                XSLFPropertiesDelegate.XSLFEffectProperties ep = XSLFPropertiesDelegate.getEffectDelegate(shape.getShapeProperties());
                if (ep != null && ep.isSetEffectLst()) {
                    CTOuterShadowEffect obj = ep.getEffectLst().getOuterShdw();
                    this.setValue(obj == null ? NO_SHADOW : obj);
                    return true;
                }
                return false;
            }
        };
        this.fetchShapeProperty(fetcher);
        CTOuterShadowEffect obj = (CTOuterShadowEffect)fetcher.getValue();
        if (obj == null && (style = this.getSpStyle()) != null && style.getEffectRef() != null && (idx = (int)style.getEffectRef().getIdx()) != 0) {
            CTStyleMatrix styleMatrix = this.getSheet().getTheme().getXmlObject().getThemeElements().getFmtScheme();
            CTEffectStyleItem ef = styleMatrix.getEffectStyleLst().getEffectStyleArray(idx - 1);
            obj = ef.getEffectLst().getOuterShdw();
        }
        return obj == null || obj == NO_SHADOW ? null : new XSLFShadow(obj, this);
    }

    @Override
    public CustomGeometry getGeometry() {
        CustomGeometry geom;
        XSLFPropertiesDelegate.XSLFGeometryProperties gp = XSLFPropertiesDelegate.getGeometryDelegate(this.getShapeProperties());
        if (gp == null) {
            return null;
        }
        PresetGeometries dict = PresetGeometries.getInstance();
        if (gp.isSetPrstGeom()) {
            String name = gp.getPrstGeom().getPrst().toString();
            geom = (CustomGeometry)dict.get(name);
            if (geom == null) {
                throw new IllegalStateException("Unknown shape geometry: " + name + ", available geometries are: " + dict.keySet());
            }
        } else if (gp.isSetCustGeom()) {
            XMLStreamReader staxReader = gp.getCustGeom().newXMLStreamReader();
            geom = PresetGeometries.convertCustomGeometry(staxReader);
            try {
                staxReader.close();
            } catch (XMLStreamException e) {
                LOG.log(5, "An error occurred while closing a Custom Geometry XML Stream Reader: " + e.getMessage());
            }
        } else {
            geom = (CustomGeometry)dict.get("rect");
        }
        return geom;
    }

    @Override
    void copy(XSLFShape sh) {
        double tgtLineWidth;
        double srcLineWidth;
        XSLFPropertiesDelegate.XSLFFillProperties fp;
        super.copy(sh);
        XSLFSimpleShape s = (XSLFSimpleShape)sh;
        Color srsSolidFill = s.getFillColor();
        Color tgtSoliFill = this.getFillColor();
        if (srsSolidFill != null && !srsSolidFill.equals(tgtSoliFill)) {
            this.setFillColor(srsSolidFill);
        }
        if ((fp = XSLFPropertiesDelegate.getFillDelegate(this.getShapeProperties())) != null && fp.isSetBlipFill()) {
            CTBlip blip = fp.getBlipFill().getBlip();
            String blipId = blip.getEmbed();
            String relId = this.getSheet().importBlip(blipId, s.getSheet());
            blip.setEmbed(relId);
        }
        Color srcLineColor = s.getLineColor();
        Color tgtLineColor = this.getLineColor();
        if (srcLineColor != null && !srcLineColor.equals(tgtLineColor)) {
            this.setLineColor(srcLineColor);
        }
        if ((srcLineWidth = s.getLineWidth()) != (tgtLineWidth = this.getLineWidth())) {
            this.setLineWidth(srcLineWidth);
        }
        StrokeStyle.LineDash srcLineDash = s.getLineDash();
        StrokeStyle.LineDash tgtLineDash = this.getLineDash();
        if (srcLineDash != null && srcLineDash != tgtLineDash) {
            this.setLineDash(srcLineDash);
        }
        StrokeStyle.LineCap srcLineCap = s.getLineCap();
        StrokeStyle.LineCap tgtLineCap = this.getLineCap();
        if (srcLineCap != null && srcLineCap != tgtLineCap) {
            this.setLineCap(srcLineCap);
        }
    }

    public void setLineHeadDecoration(LineDecoration.DecorationShape style) {
        CTLineEndProperties lnEnd;
        CTLineProperties ln = XSLFSimpleShape.getLn(this, true);
        if (ln == null) {
            return;
        }
        CTLineEndProperties cTLineEndProperties = lnEnd = ln.isSetHeadEnd() ? ln.getHeadEnd() : ln.addNewHeadEnd();
        if (style == null) {
            if (lnEnd.isSetType()) {
                lnEnd.unsetType();
            }
        } else {
            lnEnd.setType(STLineEndType.Enum.forInt(style.ooxmlId));
        }
    }

    public LineDecoration.DecorationShape getLineHeadDecoration() {
        CTLineProperties ln = XSLFSimpleShape.getLn(this, false);
        LineDecoration.DecorationShape ds = LineDecoration.DecorationShape.NONE;
        if (ln != null && ln.isSetHeadEnd() && ln.getHeadEnd().isSetType()) {
            ds = LineDecoration.DecorationShape.fromOoxmlId(ln.getHeadEnd().getType().intValue());
        }
        return ds;
    }

    public void setLineHeadWidth(LineDecoration.DecorationSize style) {
        CTLineEndProperties lnEnd;
        CTLineProperties ln = XSLFSimpleShape.getLn(this, true);
        if (ln == null) {
            return;
        }
        CTLineEndProperties cTLineEndProperties = lnEnd = ln.isSetHeadEnd() ? ln.getHeadEnd() : ln.addNewHeadEnd();
        if (style == null) {
            if (lnEnd.isSetW()) {
                lnEnd.unsetW();
            }
        } else {
            lnEnd.setW(STLineEndWidth.Enum.forInt(style.ooxmlId));
        }
    }

    public LineDecoration.DecorationSize getLineHeadWidth() {
        CTLineProperties ln = XSLFSimpleShape.getLn(this, false);
        LineDecoration.DecorationSize ds = LineDecoration.DecorationSize.MEDIUM;
        if (ln != null && ln.isSetHeadEnd() && ln.getHeadEnd().isSetW()) {
            ds = LineDecoration.DecorationSize.fromOoxmlId(ln.getHeadEnd().getW().intValue());
        }
        return ds;
    }

    public void setLineHeadLength(LineDecoration.DecorationSize style) {
        CTLineEndProperties lnEnd;
        CTLineProperties ln = XSLFSimpleShape.getLn(this, true);
        if (ln == null) {
            return;
        }
        CTLineEndProperties cTLineEndProperties = lnEnd = ln.isSetHeadEnd() ? ln.getHeadEnd() : ln.addNewHeadEnd();
        if (style == null) {
            if (lnEnd.isSetLen()) {
                lnEnd.unsetLen();
            }
        } else {
            lnEnd.setLen(STLineEndLength.Enum.forInt(style.ooxmlId));
        }
    }

    public LineDecoration.DecorationSize getLineHeadLength() {
        CTLineProperties ln = XSLFSimpleShape.getLn(this, false);
        LineDecoration.DecorationSize ds = LineDecoration.DecorationSize.MEDIUM;
        if (ln != null && ln.isSetHeadEnd() && ln.getHeadEnd().isSetLen()) {
            ds = LineDecoration.DecorationSize.fromOoxmlId(ln.getHeadEnd().getLen().intValue());
        }
        return ds;
    }

    public void setLineTailDecoration(LineDecoration.DecorationShape style) {
        CTLineEndProperties lnEnd;
        CTLineProperties ln = XSLFSimpleShape.getLn(this, true);
        if (ln == null) {
            return;
        }
        CTLineEndProperties cTLineEndProperties = lnEnd = ln.isSetTailEnd() ? ln.getTailEnd() : ln.addNewTailEnd();
        if (style == null) {
            if (lnEnd.isSetType()) {
                lnEnd.unsetType();
            }
        } else {
            lnEnd.setType(STLineEndType.Enum.forInt(style.ooxmlId));
        }
    }

    public LineDecoration.DecorationShape getLineTailDecoration() {
        CTLineProperties ln = XSLFSimpleShape.getLn(this, false);
        LineDecoration.DecorationShape ds = LineDecoration.DecorationShape.NONE;
        if (ln != null && ln.isSetTailEnd() && ln.getTailEnd().isSetType()) {
            ds = LineDecoration.DecorationShape.fromOoxmlId(ln.getTailEnd().getType().intValue());
        }
        return ds;
    }

    public void setLineTailWidth(LineDecoration.DecorationSize style) {
        CTLineEndProperties lnEnd;
        CTLineProperties ln = XSLFSimpleShape.getLn(this, true);
        if (ln == null) {
            return;
        }
        CTLineEndProperties cTLineEndProperties = lnEnd = ln.isSetTailEnd() ? ln.getTailEnd() : ln.addNewTailEnd();
        if (style == null) {
            if (lnEnd.isSetW()) {
                lnEnd.unsetW();
            }
        } else {
            lnEnd.setW(STLineEndWidth.Enum.forInt(style.ooxmlId));
        }
    }

    public LineDecoration.DecorationSize getLineTailWidth() {
        CTLineProperties ln = XSLFSimpleShape.getLn(this, false);
        LineDecoration.DecorationSize ds = LineDecoration.DecorationSize.MEDIUM;
        if (ln != null && ln.isSetTailEnd() && ln.getTailEnd().isSetW()) {
            ds = LineDecoration.DecorationSize.fromOoxmlId(ln.getTailEnd().getW().intValue());
        }
        return ds;
    }

    public void setLineTailLength(LineDecoration.DecorationSize style) {
        CTLineEndProperties lnEnd;
        CTLineProperties ln = XSLFSimpleShape.getLn(this, true);
        if (ln == null) {
            return;
        }
        CTLineEndProperties cTLineEndProperties = lnEnd = ln.isSetTailEnd() ? ln.getTailEnd() : ln.addNewTailEnd();
        if (style == null) {
            if (lnEnd.isSetLen()) {
                lnEnd.unsetLen();
            }
        } else {
            lnEnd.setLen(STLineEndLength.Enum.forInt(style.ooxmlId));
        }
    }

    public LineDecoration.DecorationSize getLineTailLength() {
        CTLineProperties ln = XSLFSimpleShape.getLn(this, false);
        LineDecoration.DecorationSize ds = LineDecoration.DecorationSize.MEDIUM;
        if (ln != null && ln.isSetTailEnd() && ln.getTailEnd().isSetLen()) {
            ds = LineDecoration.DecorationSize.fromOoxmlId(ln.getTailEnd().getLen().intValue());
        }
        return ds;
    }

    @Override
    public Guide getAdjustValue(String name) {
        XSLFPropertiesDelegate.XSLFGeometryProperties gp = XSLFPropertiesDelegate.getGeometryDelegate(this.getShapeProperties());
        if (gp != null && gp.isSetPrstGeom() && gp.getPrstGeom().isSetAvLst()) {
            for (CTGeomGuide g : gp.getPrstGeom().getAvLst().getGdArray()) {
                if (!g.getName().equals(name)) continue;
                return new Guide(g.getName(), g.getFmla());
            }
        }
        return null;
    }

    @Override
    public LineDecoration getLineDecoration() {
        return new LineDecoration(){

            @Override
            public LineDecoration.DecorationShape getHeadShape() {
                return XSLFSimpleShape.this.getLineHeadDecoration();
            }

            @Override
            public LineDecoration.DecorationSize getHeadWidth() {
                return XSLFSimpleShape.this.getLineHeadWidth();
            }

            @Override
            public LineDecoration.DecorationSize getHeadLength() {
                return XSLFSimpleShape.this.getLineHeadLength();
            }

            @Override
            public LineDecoration.DecorationShape getTailShape() {
                return XSLFSimpleShape.this.getLineTailDecoration();
            }

            @Override
            public LineDecoration.DecorationSize getTailWidth() {
                return XSLFSimpleShape.this.getLineTailWidth();
            }

            @Override
            public LineDecoration.DecorationSize getTailLength() {
                return XSLFSimpleShape.this.getLineTailLength();
            }
        };
    }

    @Override
    public FillStyle getFillStyle() {
        return this::getFillPaint;
    }

    @Override
    public StrokeStyle getStrokeStyle() {
        return new StrokeStyle(){

            @Override
            public PaintStyle getPaint() {
                return XSLFSimpleShape.this.getLinePaint();
            }

            @Override
            public StrokeStyle.LineCap getLineCap() {
                return XSLFSimpleShape.this.getLineCap();
            }

            @Override
            public StrokeStyle.LineDash getLineDash() {
                return XSLFSimpleShape.this.getLineDash();
            }

            @Override
            public double getLineWidth() {
                return XSLFSimpleShape.this.getLineWidth();
            }

            @Override
            public StrokeStyle.LineCompound getLineCompound() {
                return XSLFSimpleShape.this.getLineCompound();
            }
        };
    }

    @Override
    public void setStrokeStyle(Object ... styles) {
        if (styles.length == 0) {
            this.setLineColor(null);
            return;
        }
        for (Object st : styles) {
            if (st instanceof Number) {
                this.setLineWidth(((Number)st).doubleValue());
                continue;
            }
            if (st instanceof StrokeStyle.LineCap) {
                this.setLineCap((StrokeStyle.LineCap)((Object)st));
                continue;
            }
            if (st instanceof StrokeStyle.LineDash) {
                this.setLineDash((StrokeStyle.LineDash)((Object)st));
                continue;
            }
            if (st instanceof StrokeStyle.LineCompound) {
                this.setLineCompound((StrokeStyle.LineCompound)((Object)st));
                continue;
            }
            if (!(st instanceof Color)) continue;
            this.setLineColor((Color)st);
        }
    }

    public XSLFHyperlink getHyperlink() {
        CTNonVisualDrawingProps cNvPr = this.getCNvPr();
        if (!cNvPr.isSetHlinkClick()) {
            return null;
        }
        return new XSLFHyperlink(cNvPr.getHlinkClick(), this.getSheet());
    }

    public XSLFHyperlink createHyperlink() {
        XSLFHyperlink hl = this.getHyperlink();
        if (hl == null) {
            CTNonVisualDrawingProps cNvPr = this.getCNvPr();
            hl = new XSLFHyperlink(cNvPr.addNewHlinkClick(), this.getSheet());
        }
        return hl;
    }

    private static CTLineProperties getLn(XSLFShape shape, boolean create) {
        XmlObject pr = shape.getShapeProperties();
        if (!(pr instanceof CTShapeProperties)) {
            LOG.log(5, shape.getClass() + " doesn't have line properties");
            return null;
        }
        CTShapeProperties spr = (CTShapeProperties)pr;
        return spr.isSetLn() || !create ? spr.getLn() : spr.addNewLn();
    }
}

