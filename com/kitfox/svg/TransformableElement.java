/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public abstract class TransformableElement
extends SVGElement {
    AffineTransform xform = null;

    public TransformableElement() {
    }

    public TransformableElement(String id, SVGElement parent) {
        super(id, parent);
    }

    public AffineTransform getXForm() {
        return this.xform == null ? null : new AffineTransform(this.xform);
    }

    @Override
    protected void build() throws SVGException {
        super.build();
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("transform"))) {
            this.xform = TransformableElement.parseTransform(sty.getStringValue());
        }
    }

    protected Shape shapeToParent(Shape shape) {
        if (this.xform == null) {
            return shape;
        }
        return this.xform.createTransformedShape(shape);
    }

    protected Rectangle2D boundsToParent(Rectangle2D rect) {
        if (this.xform == null || rect == null) {
            return rect;
        }
        return this.xform.createTransformedShape(rect).getBounds2D();
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        AffineTransform newXform;
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("transform")) && !(newXform = TransformableElement.parseTransform(sty.getStringValue())).equals(this.xform)) {
            this.xform = newXform;
            return true;
        }
        return false;
    }
}

