/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.Font;
import com.kitfox.svg.RenderableElement;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.ShapeElement;
import com.kitfox.svg.pathcmd.BuildHistory;
import com.kitfox.svg.pathcmd.PathCommand;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

public class MissingGlyph
extends ShapeElement {
    public static final String TAG_NAME = "missingglyph";
    private Shape path = null;
    private float horizAdvX = -1.0f;
    private float vertOriginX = -1.0f;
    private float vertOriginY = -1.0f;
    private float vertAdvY = -1.0f;

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
        String commandList = "";
        if (this.getPres(sty.setName("d"))) {
            commandList = sty.getStringValue();
        }
        if (commandList != null) {
            String fillRule = this.getStyle(sty.setName("fill-rule")) ? sty.getStringValue() : "nonzero";
            PathCommand[] commands = MissingGlyph.parsePathList(commandList);
            GeneralPath buildPath = new GeneralPath(fillRule.equals("evenodd") ? 0 : 1, commands.length);
            BuildHistory hist = new BuildHistory();
            for (int i = 0; i < commands.length; ++i) {
                PathCommand cmd = commands[i];
                cmd.appendPath(buildPath, hist);
            }
            AffineTransform at = new AffineTransform();
            at.scale(1.0, -1.0);
            this.path = at.createTransformedShape(buildPath);
        }
        if (this.getPres(sty.setName("horiz-adv-x"))) {
            this.horizAdvX = sty.getFloatValue();
        }
        if (this.getPres(sty.setName("vert-origin-x"))) {
            this.vertOriginX = sty.getFloatValue();
        }
        if (this.getPres(sty.setName("vert-origin-y"))) {
            this.vertOriginY = sty.getFloatValue();
        }
        if (this.getPres(sty.setName("vert-adv-y"))) {
            this.vertAdvY = sty.getFloatValue();
        }
    }

    public Shape getPath() {
        return this.path;
    }

    @Override
    public void render(Graphics2D g) throws SVGException {
        if (this.path != null) {
            this.renderShape(g, this.path);
        }
        for (SVGElement ele : this.children) {
            if (!(ele instanceof RenderableElement)) continue;
            ((RenderableElement)ele).render(g);
        }
    }

    public float getHorizAdvX() {
        if (this.horizAdvX == -1.0f) {
            this.horizAdvX = ((Font)this.parent).getHorizAdvX();
        }
        return this.horizAdvX;
    }

    public float getVertOriginX() {
        if (this.vertOriginX == -1.0f) {
            this.vertOriginX = this.getHorizAdvX() / 2.0f;
        }
        return this.vertOriginX;
    }

    public float getVertOriginY() {
        if (this.vertOriginY == -1.0f) {
            this.vertOriginY = ((Font)this.parent).getFontFace().getAscent();
        }
        return this.vertOriginY;
    }

    public float getVertAdvY() {
        if (this.vertAdvY == -1.0f) {
            this.vertAdvY = ((Font)this.parent).getFontFace().getUnitsPerEm();
        }
        return this.vertAdvY;
    }

    @Override
    public Shape getShape() {
        if (this.path != null) {
            return this.shapeToParent(this.path);
        }
        return null;
    }

    @Override
    public Rectangle2D getBoundingBox() throws SVGException {
        if (this.path != null) {
            return this.boundsToParent(this.includeStrokeInBounds(this.path.getBounds2D()));
        }
        return null;
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        return false;
    }

    public void setPath(Shape path) {
        this.path = path;
    }

    public void setHorizAdvX(float horizAdvX) {
        this.horizAdvX = horizAdvX;
    }

    public void setVertOriginX(float vertOriginX) {
        this.vertOriginX = vertOriginX;
    }

    public void setVertOriginY(float vertOriginY) {
        this.vertOriginY = vertOriginY;
    }

    public void setVertAdvY(float vertAdvY) {
        this.vertAdvY = vertAdvY;
    }
}

