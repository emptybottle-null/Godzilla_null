/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.ClipPath;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.ShapeElement;
import com.kitfox.svg.TransformableElement;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.net.URI;
import java.util.List;

public abstract class RenderableElement
extends TransformableElement {
    AffineTransform cachedXform = null;
    Shape cachedClip = null;
    public static final int VECTOR_EFFECT_NONE = 0;
    public static final int VECTOR_EFFECT_NON_SCALING_STROKE = 1;
    int vectorEffect;

    public RenderableElement() {
    }

    public RenderableElement(String id, SVGElement parent) {
        super(id, parent);
    }

    @Override
    protected void build() throws SVGException {
        super.build();
        StyleAttribute sty = new StyleAttribute();
        this.vectorEffect = this.getPres(sty.setName("vector-effect")) ? ("non-scaling-stroke".equals(sty.getStringValue()) ? 1 : 0) : 0;
    }

    public abstract void render(Graphics2D var1) throws SVGException;

    abstract void pick(Point2D var1, boolean var2, List<List<SVGElement>> var3) throws SVGException;

    abstract void pick(Rectangle2D var1, AffineTransform var2, boolean var3, List<List<SVGElement>> var4) throws SVGException;

    public abstract Rectangle2D getBoundingBox() throws SVGException;

    protected void beginLayer(Graphics2D g) throws SVGException {
        URI uri;
        if (this.xform != null) {
            this.cachedXform = g.getTransform();
            g.transform(this.xform);
        }
        StyleAttribute styleAttrib = new StyleAttribute();
        Shape clipPath = null;
        int clipPathUnits = 0;
        if (this.getStyle(styleAttrib.setName("clip-path"), false) && !"none".equals(styleAttrib.getStringValue()) && (uri = styleAttrib.getURIValue(this.getXMLBase())) != null) {
            ClipPath ele = (ClipPath)this.diagram.getUniverse().getElement(uri);
            clipPath = ele.getClipPathShape();
            clipPathUnits = ele.getClipPathUnits();
        }
        if (clipPath != null) {
            if (clipPathUnits == 1 && this instanceof ShapeElement) {
                Rectangle2D rect = ((ShapeElement)this).getBoundingBox();
                AffineTransform at = new AffineTransform();
                at.scale(rect.getWidth(), rect.getHeight());
                clipPath = at.createTransformedShape(clipPath);
            }
            this.cachedClip = g.getClip();
            if (this.cachedClip == null) {
                g.setClip(clipPath);
            } else {
                Area newClip = new Area(this.cachedClip);
                newClip.intersect(new Area(clipPath));
                g.setClip(newClip);
            }
        }
    }

    protected void finishLayer(Graphics2D g) {
        if (this.cachedClip != null) {
            g.setClip(this.cachedClip);
        }
        if (this.cachedXform != null) {
            g.setTransform(this.cachedXform);
        }
    }
}

