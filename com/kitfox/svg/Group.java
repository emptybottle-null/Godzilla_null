/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.RenderableElement;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.ShapeElement;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.List;

public class Group
extends ShapeElement {
    public static final String TAG_NAME = "group";
    Rectangle2D boundingBox;
    Shape cachedShape;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    public void loaderAddChild(SVGLoaderHelper helper, SVGElement child) throws SVGElementException {
        super.loaderAddChild(helper, child);
    }

    protected boolean outsideClip(Graphics2D g) throws SVGException {
        Shape clip = g.getClip();
        if (clip == null) {
            return false;
        }
        Rectangle2D rect = this.getBoundingBox();
        return !clip.intersects(rect);
    }

    @Override
    void pick(Point2D point, boolean boundingBox, List<List<SVGElement>> retVec) throws SVGException {
        Point2D.Double xPoint = new Point2D.Double(point.getX(), point.getY());
        if (this.xform != null) {
            try {
                this.xform.inverseTransform(point, xPoint);
            } catch (NoninvertibleTransformException ex) {
                throw new SVGException(ex);
            }
        }
        for (SVGElement ele : this.children) {
            if (!(ele instanceof RenderableElement)) continue;
            RenderableElement rendEle = (RenderableElement)ele;
            rendEle.pick(xPoint, boundingBox, retVec);
        }
    }

    @Override
    void pick(Rectangle2D pickArea, AffineTransform ltw, boolean boundingBox, List<List<SVGElement>> retVec) throws SVGException {
        if (this.xform != null) {
            ltw = new AffineTransform(ltw);
            ltw.concatenate(this.xform);
        }
        for (SVGElement ele : this.children) {
            if (!(ele instanceof RenderableElement)) continue;
            RenderableElement rendEle = (RenderableElement)ele;
            rendEle.pick(pickArea, ltw, boundingBox, retVec);
        }
    }

    @Override
    public void render(Graphics2D g) throws SVGException {
        StyleAttribute styleAttrib = new StyleAttribute();
        if (this.getStyle(styleAttrib.setName("display")) && styleAttrib.getStringValue().equals("none")) {
            return;
        }
        boolean ignoreClip = this.diagram.ignoringClipHeuristic();
        this.beginLayer(g);
        Iterator it = this.children.iterator();
        Shape clip = g.getClip();
        while (it.hasNext()) {
            SVGElement ele = (SVGElement)it.next();
            if (!(ele instanceof RenderableElement)) continue;
            RenderableElement rendEle = (RenderableElement)ele;
            if (!(ele instanceof Group) && !ignoreClip && clip != null && !clip.intersects(rendEle.getBoundingBox())) continue;
            rendEle.render(g);
        }
        this.finishLayer(g);
    }

    @Override
    public Shape getShape() {
        if (this.cachedShape == null) {
            this.calcShape();
        }
        return this.cachedShape;
    }

    public void calcShape() {
        Area retShape = new Area();
        for (SVGElement ele : this.children) {
            ShapeElement shpEle;
            Shape shape;
            if (!(ele instanceof ShapeElement) || (shape = (shpEle = (ShapeElement)ele).getShape()) == null) continue;
            retShape.add(new Area(shape));
        }
        this.cachedShape = this.shapeToParent(retShape);
    }

    @Override
    public Rectangle2D getBoundingBox() throws SVGException {
        if (this.boundingBox == null) {
            this.calcBoundingBox();
        }
        return this.boundingBox;
    }

    public void calcBoundingBox() throws SVGException {
        Rectangle2D retRect = null;
        for (SVGElement ele : this.children) {
            RenderableElement rendEle;
            Rectangle2D bounds;
            if (!(ele instanceof RenderableElement) || (bounds = (rendEle = (RenderableElement)ele).getBoundingBox()) == null || bounds.getWidth() == 0.0 && bounds.getHeight() == 0.0) continue;
            if (retRect == null) {
                retRect = bounds;
                continue;
            }
            if (retRect.getWidth() == 0.0 && retRect.getHeight() == 0.0) continue;
            retRect = retRect.createUnion(bounds);
        }
        if (retRect == null) {
            retRect = new Rectangle2D.Float();
        }
        this.boundingBox = this.boundsToParent(retRect);
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        boolean changeState = super.updateTime(curTime);
        for (SVGElement ele : this.children) {
            boolean updateVal = ele.updateTime(curTime);
            boolean bl = changeState = changeState || updateVal;
            if (ele instanceof ShapeElement) {
                this.cachedShape = null;
            }
            if (!(ele instanceof RenderableElement)) continue;
            this.boundingBox = null;
        }
        return changeState;
    }
}

