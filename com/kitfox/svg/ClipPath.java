/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.ShapeElement;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Shape;
import java.awt.geom.Area;

public class ClipPath
extends SVGElement {
    public static final String TAG_NAME = "clippath";
    public static final int CP_USER_SPACE_ON_USE = 0;
    public static final int CP_OBJECT_BOUNDING_BOX = 1;
    int clipPathUnits = 0;

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
        this.clipPathUnits = this.getPres(sty.setName("clipPathUnits")) && sty.getStringValue().equals("objectBoundingBox") ? 1 : 0;
    }

    public int getClipPathUnits() {
        return this.clipPathUnits;
    }

    public Shape getClipPathShape() {
        if (this.children.isEmpty()) {
            return null;
        }
        if (this.children.size() == 1) {
            return ((ShapeElement)this.children.get(0)).getShape();
        }
        Area clipArea = null;
        for (SVGElement svgElement : this.children) {
            Shape shape;
            ShapeElement se = (ShapeElement)svgElement;
            if (clipArea == null) {
                shape = se.getShape();
                if (shape == null) continue;
                clipArea = new Area(se.getShape());
                continue;
            }
            shape = se.getShape();
            if (shape == null) continue;
            clipArea.intersect(new Area(shape));
        }
        return clipArea;
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        StyleAttribute sty = new StyleAttribute();
        boolean shapeChange = false;
        if (this.getPres(sty.setName("clipPathUnits"))) {
            int newUnits;
            String newUnitsStrn = sty.getStringValue();
            int n = newUnits = newUnitsStrn.equals("objectBoundingBox") ? 1 : 0;
            if (newUnits != this.clipPathUnits) {
                this.clipPathUnits = newUnits;
                shapeChange = true;
            }
        }
        if (shapeChange) {
            this.build();
        }
        for (int i = 0; i < this.children.size(); ++i) {
            SVGElement ele = (SVGElement)this.children.get(i);
            ele.updateTime(curTime);
        }
        return shapeChange;
    }
}

