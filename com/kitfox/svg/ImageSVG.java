/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.RenderableElement;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.app.data.Handler;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageSVG
extends RenderableElement {
    public static final String TAG_NAME = "image";
    float x = 0.0f;
    float y = 0.0f;
    float width = 0.0f;
    float height = 0.0f;
    URL imageSrc = null;
    AffineTransform xform;
    Rectangle2D bounds;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    protected void build() throws SVGException {
        super.build();
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("x"))) {
            this.x = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("y"))) {
            this.y = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("width"))) {
            this.width = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("height"))) {
            this.height = sty.getFloatValueWithUnits();
        }
        try {
            if (this.getPres(sty.setName("xlink:href"))) {
                URI src = sty.getURIValue(this.getXMLBase());
                if ("data".equals(src.getScheme())) {
                    this.imageSrc = new URL(null, src.toASCIIString(), new Handler());
                } else if (!this.diagram.getUniverse().isImageDataInlineOnly()) {
                    try {
                        this.imageSrc = src.toURL();
                    } catch (Exception e) {
                        Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not parse xlink:href " + src, e);
                        this.imageSrc = null;
                    }
                }
            }
        } catch (Exception e) {
            throw new SVGException(e);
        }
        if (this.imageSrc != null) {
            this.diagram.getUniverse().registerImage(this.imageSrc);
            BufferedImage img = this.diagram.getUniverse().getImage(this.imageSrc);
            if (img == null) {
                this.xform = new AffineTransform();
                this.bounds = new Rectangle2D.Float();
                return;
            }
            if (this.width == 0.0f) {
                this.width = img.getWidth();
            }
            if (this.height == 0.0f) {
                this.height = img.getHeight();
            }
            this.xform = new AffineTransform();
            this.xform.translate(this.x, this.y);
            this.xform.scale(this.width / (float)img.getWidth(), this.height / (float)img.getHeight());
        }
        this.bounds = new Rectangle2D.Float(this.x, this.y, this.width, this.height);
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    @Override
    void pick(Point2D point, boolean boundingBox, List<List<SVGElement>> retVec) throws SVGException {
        if (this.getBoundingBox().contains(point)) {
            retVec.add(this.getPath(null));
        }
    }

    @Override
    void pick(Rectangle2D pickArea, AffineTransform ltw, boolean boundingBox, List<List<SVGElement>> retVec) throws SVGException {
        if (ltw.createTransformedShape(this.getBoundingBox()).intersects(pickArea)) {
            retVec.add(this.getPath(null));
        }
    }

    @Override
    public void render(Graphics2D g) throws SVGException {
        BufferedImage img;
        StyleAttribute styleAttrib = new StyleAttribute();
        if (this.getStyle(styleAttrib.setName("visibility")) && !styleAttrib.getStringValue().equals("visible")) {
            return;
        }
        if (this.getStyle(styleAttrib.setName("display")) && styleAttrib.getStringValue().equals("none")) {
            return;
        }
        this.beginLayer(g);
        float opacity = 1.0f;
        if (this.getStyle(styleAttrib.setName("opacity"))) {
            opacity = styleAttrib.getRatioValue();
        }
        if (opacity <= 0.0f) {
            return;
        }
        Composite oldComp = null;
        if (opacity < 1.0f) {
            oldComp = g.getComposite();
            AlphaComposite comp = AlphaComposite.getInstance(3, opacity);
            g.setComposite(comp);
        }
        if ((img = this.diagram.getUniverse().getImage(this.imageSrc)) == null) {
            return;
        }
        AffineTransform curXform = g.getTransform();
        g.transform(this.xform);
        g.drawImage((Image)img, 0, 0, null);
        g.setTransform(curXform);
        if (oldComp != null) {
            g.setComposite(oldComp);
        }
        this.finishLayer(g);
    }

    @Override
    public Rectangle2D getBoundingBox() {
        return this.boundsToParent(this.bounds);
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        float newVal;
        boolean changeState = super.updateTime(curTime);
        StyleAttribute sty = new StyleAttribute();
        boolean shapeChange = false;
        if (this.getPres(sty.setName("x")) && (newVal = sty.getFloatValueWithUnits()) != this.x) {
            this.x = newVal;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("y")) && (newVal = sty.getFloatValueWithUnits()) != this.y) {
            this.y = newVal;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("width")) && (newVal = sty.getFloatValueWithUnits()) != this.width) {
            this.width = newVal;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("height")) && (newVal = sty.getFloatValueWithUnits()) != this.height) {
            this.height = newVal;
            shapeChange = true;
        }
        try {
            if (this.getPres(sty.setName("xlink:href"))) {
                URI src = sty.getURIValue(this.getXMLBase());
                URL newVal2 = null;
                if ("data".equals(src.getScheme())) {
                    newVal2 = new URL(null, src.toASCIIString(), new Handler());
                } else if (!this.diagram.getUniverse().isImageDataInlineOnly()) {
                    newVal2 = src.toURL();
                }
                if (newVal2 != null && !newVal2.equals(this.imageSrc)) {
                    this.imageSrc = newVal2;
                    shapeChange = true;
                }
            }
        } catch (IllegalArgumentException ie) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Image provided with illegal value for href: \"" + sty.getStringValue() + '\"', ie);
        } catch (Exception e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not parse xlink:href", e);
        }
        if (shapeChange) {
            this.build();
        }
        return changeState || shapeChange;
    }
}

