/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.FillElement;
import com.kitfox.svg.RenderableElement;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.pattern.PatternPaint;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatternSVG
extends FillElement {
    public static final String TAG_NAME = "pattern";
    public static final int GU_OBJECT_BOUNDING_BOX = 0;
    public static final int GU_USER_SPACE_ON_USE = 1;
    int gradientUnits = 0;
    float x;
    float y;
    float width;
    float height;
    AffineTransform patternXform = new AffineTransform();
    Rectangle2D.Float viewBox;
    Paint texPaint;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    public void loaderAddChild(SVGLoaderHelper helper, SVGElement child) throws SVGElementException {
        super.loaderAddChild(helper, child);
    }

    @Override
    protected void build() throws SVGException {
        super.build();
        StyleAttribute sty = new StyleAttribute();
        String href = null;
        if (this.getPres(sty.setName("xlink:href"))) {
            href = sty.getStringValue();
        }
        if (href != null) {
            try {
                URI src = this.getXMLBase().resolve(href);
                PatternSVG patSrc = (PatternSVG)this.diagram.getUniverse().getElement(src);
                this.gradientUnits = patSrc.gradientUnits;
                this.x = patSrc.x;
                this.y = patSrc.y;
                this.width = patSrc.width;
                this.height = patSrc.height;
                this.viewBox = patSrc.viewBox;
                this.patternXform.setTransform(patSrc.patternXform);
                this.children.addAll(patSrc.children);
            } catch (Exception e) {
                Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not parse xlink:href", e);
            }
        }
        String gradientUnits = "";
        if (this.getPres(sty.setName("gradientUnits"))) {
            gradientUnits = sty.getStringValue().toLowerCase();
        }
        this.gradientUnits = gradientUnits.equals("userspaceonuse") ? 1 : 0;
        String patternTransform = "";
        if (this.getPres(sty.setName("patternTransform"))) {
            patternTransform = sty.getStringValue();
        }
        this.patternXform = PatternSVG.parseTransform(patternTransform);
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
        if (this.getPres(sty.setName("viewBox"))) {
            float[] dim = sty.getFloatList();
            this.viewBox = new Rectangle2D.Float(dim[0], dim[1], dim[2], dim[3]);
        }
        this.preparePattern();
    }

    protected void preparePattern() throws SVGException {
        int tileWidth = (int)this.width;
        int tileHeight = (int)this.height;
        float stretchX = 1.0f;
        float stretchY = 1.0f;
        if (!this.patternXform.isIdentity()) {
            float xlateX = (float)this.patternXform.getTranslateX();
            float xlateY = (float)this.patternXform.getTranslateY();
            Point2D.Float pt = new Point2D.Float();
            Point2D.Float pt2 = new Point2D.Float();
            pt.setLocation(this.width, 0.0f);
            this.patternXform.transform(pt, pt2);
            pt2.x -= xlateX;
            pt2.y -= xlateY;
            stretchX = (float)Math.sqrt(pt2.x * pt2.x + pt2.y * pt2.y) * 1.5f / this.width;
            pt.setLocation(this.height, 0.0f);
            this.patternXform.transform(pt, pt2);
            pt2.x -= xlateX;
            pt2.y -= xlateY;
            stretchY = (float)Math.sqrt(pt2.x * pt2.x + pt2.y * pt2.y) * 1.5f / this.height;
            tileWidth = (int)((float)tileWidth * stretchX);
            tileHeight = (int)((float)tileHeight * stretchY);
        }
        if (tileWidth == 0 || tileHeight == 0) {
            return;
        }
        BufferedImage buf = new BufferedImage(tileWidth, tileHeight, 2);
        Graphics2D g = buf.createGraphics();
        g.setClip(0, 0, tileWidth, tileHeight);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (SVGElement ele : this.children) {
            if (!(ele instanceof RenderableElement)) continue;
            AffineTransform xform = new AffineTransform();
            if (this.viewBox == null) {
                xform.translate(-this.x, -this.y);
            } else {
                xform.scale((float)tileWidth / this.viewBox.width, (float)tileHeight / this.viewBox.height);
                xform.translate(-this.viewBox.x, -this.viewBox.y);
            }
            g.setTransform(xform);
            ((RenderableElement)ele).render(g);
        }
        g.dispose();
        if (this.patternXform.isIdentity()) {
            this.texPaint = new TexturePaint(buf, new Rectangle2D.Float(this.x, this.y, this.width, this.height));
        } else {
            this.patternXform.scale(1.0f / stretchX, 1.0f / stretchY);
            this.texPaint = new PatternPaint(buf, this.patternXform);
        }
    }

    @Override
    public Paint getPaint(Rectangle2D bounds, AffineTransform xform) {
        return this.texPaint;
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        return false;
    }
}

