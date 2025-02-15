/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.FillElement;
import com.kitfox.svg.Marker;
import com.kitfox.svg.RenderableElement;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public abstract class ShapeElement
extends RenderableElement {
    protected float strokeWidthScalar = 1.0f;

    @Override
    public abstract void render(Graphics2D var1) throws SVGException;

    @Override
    void pick(Point2D point, boolean boundingBox, List<List<SVGElement>> retVec) throws SVGException {
        if ((boundingBox ? this.getBoundingBox() : this.getShape()).contains(point)) {
            retVec.add(this.getPath(null));
        }
    }

    @Override
    void pick(Rectangle2D pickArea, AffineTransform ltw, boolean boundingBox, List<List<SVGElement>> retVec) throws SVGException {
        if (ltw.createTransformedShape(boundingBox ? this.getBoundingBox() : this.getShape()).intersects(pickArea)) {
            retVec.add(this.getPath(null));
        }
    }

    private Paint handleCurrentColor(StyleAttribute styleAttrib) throws SVGException {
        if (styleAttrib.getStringValue().equals("currentColor")) {
            StyleAttribute currentColorAttrib = new StyleAttribute();
            if (this.getStyle(currentColorAttrib.setName("color")) && !currentColorAttrib.getStringValue().equals("none")) {
                return currentColorAttrib.getColorValue();
            }
            return null;
        }
        return styleAttrib.getColorValue();
    }

    protected void renderShape(Graphics2D g, Shape shape) throws SVGException {
        StyleAttribute styleAttrib = new StyleAttribute();
        if (this.getStyle(styleAttrib.setName("visibility")) && !styleAttrib.getStringValue().equals("visible")) {
            return;
        }
        if (this.getStyle(styleAttrib.setName("display")) && styleAttrib.getStringValue().equals("none")) {
            return;
        }
        Paint fillPaint = Color.black;
        if (this.getStyle(styleAttrib.setName("fill"))) {
            if (styleAttrib.getStringValue().equals("none")) {
                fillPaint = null;
            } else {
                URI uri;
                fillPaint = this.handleCurrentColor(styleAttrib);
                if (fillPaint == null && (uri = styleAttrib.getURIValue(this.getXMLBase())) != null) {
                    Rectangle2D bounds = shape.getBounds2D();
                    AffineTransform xform = g.getTransform();
                    SVGElement ele = this.diagram.getUniverse().getElement(uri);
                    if (ele != null) {
                        try {
                            fillPaint = ((FillElement)ele).getPaint(bounds, xform);
                        } catch (IllegalArgumentException e) {
                            throw new SVGException(e);
                        }
                    }
                }
            }
        }
        float opacity = 1.0f;
        if (this.getStyle(styleAttrib.setName("opacity"))) {
            opacity = styleAttrib.getRatioValue();
        }
        float fillOpacity = opacity;
        if (this.getStyle(styleAttrib.setName("fill-opacity"))) {
            fillOpacity *= styleAttrib.getRatioValue();
        }
        Paint strokePaint = null;
        if (this.getStyle(styleAttrib.setName("stroke"))) {
            if (styleAttrib.getStringValue().equals("none")) {
                strokePaint = null;
            } else {
                URI uri;
                strokePaint = this.handleCurrentColor(styleAttrib);
                if (strokePaint == null && (uri = styleAttrib.getURIValue(this.getXMLBase())) != null) {
                    Rectangle2D bounds = shape.getBounds2D();
                    AffineTransform xform = g.getTransform();
                    SVGElement ele = this.diagram.getUniverse().getElement(uri);
                    if (ele != null) {
                        strokePaint = ((FillElement)ele).getPaint(bounds, xform);
                    }
                }
            }
        }
        float[] strokeDashArray = null;
        if (this.getStyle(styleAttrib.setName("stroke-dasharray")) && (strokeDashArray = styleAttrib.getFloatList()).length == 0) {
            strokeDashArray = null;
        }
        float strokeDashOffset = 0.0f;
        if (this.getStyle(styleAttrib.setName("stroke-dashoffset"))) {
            strokeDashOffset = styleAttrib.getFloatValueWithUnits();
        }
        int strokeLinecap = 0;
        if (this.getStyle(styleAttrib.setName("stroke-linecap"))) {
            String val = styleAttrib.getStringValue();
            if (val.equals("round")) {
                strokeLinecap = 1;
            } else if (val.equals("square")) {
                strokeLinecap = 2;
            }
        }
        int strokeLinejoin = 0;
        if (this.getStyle(styleAttrib.setName("stroke-linejoin"))) {
            String val = styleAttrib.getStringValue();
            if (val.equals("round")) {
                strokeLinejoin = 1;
            } else if (val.equals("bevel")) {
                strokeLinejoin = 2;
            }
        }
        float strokeMiterLimit = 4.0f;
        if (this.getStyle(styleAttrib.setName("stroke-miterlimit"))) {
            strokeMiterLimit = Math.max(styleAttrib.getFloatValueWithUnits(), 1.0f);
        }
        float strokeOpacity = opacity;
        if (this.getStyle(styleAttrib.setName("stroke-opacity"))) {
            strokeOpacity *= styleAttrib.getRatioValue();
        }
        float strokeWidth = 1.0f;
        if (this.getStyle(styleAttrib.setName("stroke-width"))) {
            strokeWidth = styleAttrib.getFloatValueWithUnits();
        }
        strokeWidth *= this.strokeWidthScalar;
        Marker markerStart = null;
        if (this.getStyle(styleAttrib.setName("marker-start")) && !styleAttrib.getStringValue().equals("none")) {
            URI uri = styleAttrib.getURIValue(this.getXMLBase());
            markerStart = (Marker)this.diagram.getUniverse().getElement(uri);
        }
        Marker markerMid = null;
        if (this.getStyle(styleAttrib.setName("marker-mid")) && !styleAttrib.getStringValue().equals("none")) {
            URI uri = styleAttrib.getURIValue(this.getXMLBase());
            markerMid = (Marker)this.diagram.getUniverse().getElement(uri);
        }
        Marker markerEnd = null;
        if (this.getStyle(styleAttrib.setName("marker-end")) && !styleAttrib.getStringValue().equals("none")) {
            URI uri = styleAttrib.getURIValue(this.getXMLBase());
            markerEnd = (Marker)this.diagram.getUniverse().getElement(uri);
        }
        if (fillPaint != null && fillOpacity != 0.0f && !(fillOpacity <= 0.0f)) {
            if (fillOpacity < 1.0f) {
                Composite cachedComposite = g.getComposite();
                g.setComposite(AlphaComposite.getInstance(3, fillOpacity));
                g.setPaint(fillPaint);
                g.fill(shape);
                g.setComposite(cachedComposite);
            } else {
                g.setPaint(fillPaint);
                g.fill(shape);
            }
        }
        if (strokePaint != null && strokeOpacity != 0.0f) {
            Shape strokeShape;
            BasicStroke stroke = strokeDashArray == null ? new BasicStroke(strokeWidth, strokeLinecap, strokeLinejoin, strokeMiterLimit) : new BasicStroke(strokeWidth, strokeLinecap, strokeLinejoin, strokeMiterLimit, strokeDashArray, strokeDashOffset);
            AffineTransform cacheXform = g.getTransform();
            if (this.vectorEffect == 1) {
                strokeShape = cacheXform.createTransformedShape(shape);
                strokeShape = stroke.createStrokedShape(strokeShape);
            } else {
                strokeShape = stroke.createStrokedShape(shape);
            }
            if (!(strokeOpacity <= 0.0f)) {
                Composite cachedComposite = g.getComposite();
                if (strokeOpacity < 1.0f) {
                    g.setComposite(AlphaComposite.getInstance(3, strokeOpacity));
                }
                if (this.vectorEffect == 1) {
                    g.setTransform(new AffineTransform());
                }
                g.setPaint(strokePaint);
                g.fill(strokeShape);
                if (this.vectorEffect == 1) {
                    g.setTransform(cacheXform);
                }
                if (strokeOpacity < 1.0f) {
                    g.setComposite(cachedComposite);
                }
            }
        }
        if (markerStart != null || markerMid != null || markerEnd != null) {
            Marker.MarkerLayout layout = new Marker.MarkerLayout();
            layout.layout(shape);
            ArrayList<Marker.MarkerPos> list = layout.getMarkerList();
            block7: for (int i = 0; i < list.size(); ++i) {
                Marker.MarkerPos pos = list.get(i);
                switch (pos.type) {
                    case 0: {
                        if (markerStart == null) continue block7;
                        markerStart.render(g, pos, strokeWidth);
                        continue block7;
                    }
                    case 1: {
                        if (markerMid == null) continue block7;
                        markerMid.render(g, pos, strokeWidth);
                        continue block7;
                    }
                    case 2: {
                        if (markerEnd == null) continue block7;
                        markerEnd.render(g, pos, strokeWidth);
                    }
                }
            }
        }
    }

    public abstract Shape getShape();

    protected Rectangle2D includeStrokeInBounds(Rectangle2D rect) throws SVGException {
        StyleAttribute styleAttrib = new StyleAttribute();
        if (!this.getStyle(styleAttrib.setName("stroke"))) {
            return rect;
        }
        double strokeWidth = 1.0;
        if (this.getStyle(styleAttrib.setName("stroke-width"))) {
            strokeWidth = styleAttrib.getDoubleValue();
        }
        rect.setRect(rect.getX() - strokeWidth / 2.0, rect.getY() - strokeWidth / 2.0, rect.getWidth() + strokeWidth, rect.getHeight() + strokeWidth);
        return rect;
    }
}

