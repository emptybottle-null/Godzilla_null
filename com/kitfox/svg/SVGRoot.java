/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.Group;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.Style;
import com.kitfox.svg.xml.NumberWithUnits;
import com.kitfox.svg.xml.StyleAttribute;
import com.kitfox.svg.xml.StyleSheet;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class SVGRoot
extends Group {
    public static final String TAG_NAME = "svg";
    NumberWithUnits x;
    NumberWithUnits y;
    NumberWithUnits width;
    NumberWithUnits height;
    Rectangle2D.Float viewBox = null;
    public static final int PA_X_NONE = 0;
    public static final int PA_X_MIN = 1;
    public static final int PA_X_MID = 2;
    public static final int PA_X_MAX = 3;
    public static final int PA_Y_NONE = 0;
    public static final int PA_Y_MIN = 1;
    public static final int PA_Y_MID = 2;
    public static final int PA_Y_MAX = 3;
    public static final int PS_MEET = 0;
    public static final int PS_SLICE = 1;
    int parSpecifier = 0;
    int parAlignX = 2;
    int parAlignY = 2;
    final AffineTransform viewXform = new AffineTransform();
    final Rectangle2D.Float clipRect = new Rectangle2D.Float();
    private StyleSheet styleSheet;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    public void build() throws SVGException {
        super.build();
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("x"))) {
            this.x = sty.getNumberWithUnits();
        }
        if (this.getPres(sty.setName("y"))) {
            this.y = sty.getNumberWithUnits();
        }
        if (this.getPres(sty.setName("width"))) {
            this.width = sty.getNumberWithUnits();
        }
        if (this.getPres(sty.setName("height"))) {
            this.height = sty.getNumberWithUnits();
        }
        if (this.getPres(sty.setName("viewBox"))) {
            float[] coords = sty.getFloatList();
            this.viewBox = new Rectangle2D.Float(coords[0], coords[1], coords[2], coords[3]);
        }
        if (this.getPres(sty.setName("preserveAspectRatio"))) {
            String preserve = sty.getStringValue();
            if (this.contains(preserve, "none")) {
                this.parAlignX = 0;
                this.parAlignY = 0;
            } else if (this.contains(preserve, "xMinYMin")) {
                this.parAlignX = 1;
                this.parAlignY = 1;
            } else if (this.contains(preserve, "xMidYMin")) {
                this.parAlignX = 2;
                this.parAlignY = 1;
            } else if (this.contains(preserve, "xMaxYMin")) {
                this.parAlignX = 3;
                this.parAlignY = 1;
            } else if (this.contains(preserve, "xMinYMid")) {
                this.parAlignX = 1;
                this.parAlignY = 2;
            } else if (this.contains(preserve, "xMidYMid")) {
                this.parAlignX = 2;
                this.parAlignY = 2;
            } else if (this.contains(preserve, "xMaxYMid")) {
                this.parAlignX = 3;
                this.parAlignY = 2;
            } else if (this.contains(preserve, "xMinYMax")) {
                this.parAlignX = 1;
                this.parAlignY = 3;
            } else if (this.contains(preserve, "xMidYMax")) {
                this.parAlignX = 2;
                this.parAlignY = 3;
            } else if (this.contains(preserve, "xMaxYMax")) {
                this.parAlignX = 3;
                this.parAlignY = 3;
            }
            if (this.contains(preserve, "meet")) {
                this.parSpecifier = 0;
            } else if (this.contains(preserve, "slice")) {
                this.parSpecifier = 1;
            }
        }
        this.prepareViewport();
    }

    private boolean contains(String text, String find) {
        return text.indexOf(find) != -1;
    }

    @Override
    public SVGRoot getRoot() {
        return this;
    }

    protected void prepareViewport() {
        float hh;
        float yy;
        float ww;
        float xx;
        Rectangle2D defaultBounds;
        Rectangle deviceViewport = this.diagram.getDeviceViewport();
        try {
            defaultBounds = this.getBoundingBox();
        } catch (SVGException ex) {
            defaultBounds = new Rectangle2D.Float();
        }
        if (this.width != null) {
            float f = xx = this.x == null ? 0.0f : StyleAttribute.convertUnitsToPixels(this.x.getUnits(), this.x.getValue());
            ww = this.width.getUnits() == 9 ? this.width.getValue() * (float)deviceViewport.width : StyleAttribute.convertUnitsToPixels(this.width.getUnits(), this.width.getValue());
        } else if (this.viewBox != null) {
            xx = this.viewBox.x;
            ww = this.viewBox.width;
            this.width = new NumberWithUnits(ww, 1);
            this.x = new NumberWithUnits(xx, 1);
        } else {
            xx = (float)defaultBounds.getX();
            ww = (float)defaultBounds.getWidth();
            this.width = new NumberWithUnits(ww, 1);
            this.x = new NumberWithUnits(xx, 1);
        }
        if (this.height != null) {
            float f = yy = this.y == null ? 0.0f : StyleAttribute.convertUnitsToPixels(this.y.getUnits(), this.y.getValue());
            hh = this.height.getUnits() == 9 ? this.height.getValue() * (float)deviceViewport.height : StyleAttribute.convertUnitsToPixels(this.height.getUnits(), this.height.getValue());
        } else if (this.viewBox != null) {
            yy = this.viewBox.y;
            hh = this.viewBox.height;
            this.height = new NumberWithUnits(hh, 1);
            this.y = new NumberWithUnits(yy, 1);
        } else {
            yy = (float)defaultBounds.getY();
            hh = (float)defaultBounds.getHeight();
            this.height = new NumberWithUnits(hh, 1);
            this.y = new NumberWithUnits(yy, 1);
        }
        this.clipRect.setRect(xx, yy, ww, hh);
    }

    public void renderToViewport(Graphics2D g) throws SVGException {
        this.render(g);
    }

    @Override
    public void render(Graphics2D g) throws SVGException {
        this.prepareViewport();
        Rectangle targetViewport = g.getClipBounds();
        Rectangle deviceViewport = this.diagram.getDeviceViewport();
        if (this.width != null && this.height != null) {
            float xx = this.x == null ? 0.0f : StyleAttribute.convertUnitsToPixels(this.x.getUnits(), this.x.getValue());
            float ww = this.width.getUnits() == 9 ? this.width.getValue() * (float)deviceViewport.width : StyleAttribute.convertUnitsToPixels(this.width.getUnits(), this.width.getValue());
            float yy = this.y == null ? 0.0f : StyleAttribute.convertUnitsToPixels(this.y.getUnits(), this.y.getValue());
            float hh = this.height.getUnits() == 9 ? this.height.getValue() * (float)deviceViewport.height : StyleAttribute.convertUnitsToPixels(this.height.getUnits(), this.height.getValue());
            targetViewport = new Rectangle((int)xx, (int)yy, (int)ww, (int)hh);
        } else {
            targetViewport = new Rectangle(deviceViewport);
        }
        this.clipRect.setRect(targetViewport);
        this.viewXform.setTransform(this.calcViewportTransform(targetViewport));
        AffineTransform cachedXform = g.getTransform();
        g.transform(this.viewXform);
        super.render(g);
        g.setTransform(cachedXform);
    }

    public AffineTransform calcViewportTransform(Rectangle targetViewport) {
        AffineTransform xform = new AffineTransform();
        if (this.viewBox == null) {
            xform.setToIdentity();
        } else {
            xform.setToIdentity();
            xform.setToTranslation(targetViewport.x, targetViewport.y);
            xform.scale(targetViewport.width, targetViewport.height);
            xform.scale(1.0f / this.viewBox.width, 1.0f / this.viewBox.height);
            xform.translate(-this.viewBox.x, -this.viewBox.y);
        }
        return xform;
    }

    @Override
    public void pick(Rectangle2D pickArea, AffineTransform ltw, boolean boundingBox, List<List<SVGElement>> retVec) throws SVGException {
        if (this.viewXform != null) {
            ltw = new AffineTransform(ltw);
            ltw.concatenate(this.viewXform);
        }
        super.pick(pickArea, ltw, boundingBox, retVec);
    }

    @Override
    public void pick(Point2D point, boolean boundingBox, List<List<SVGElement>> retVec) throws SVGException {
        Point2D.Double xPoint = new Point2D.Double(point.getX(), point.getY());
        if (this.viewXform != null) {
            try {
                this.viewXform.inverseTransform(point, xPoint);
            } catch (NoninvertibleTransformException ex) {
                throw new SVGException(ex);
            }
        }
        super.pick(xPoint, boundingBox, retVec);
    }

    @Override
    public Shape getShape() {
        Shape shape = super.getShape();
        return this.viewXform.createTransformedShape(shape);
    }

    @Override
    public Rectangle2D getBoundingBox() throws SVGException {
        Rectangle2D bbox = super.getBoundingBox();
        return this.viewXform.createTransformedShape(bbox).getBounds2D();
    }

    public float getDeviceWidth() {
        return this.clipRect.width;
    }

    public float getDeviceHeight() {
        return this.clipRect.height;
    }

    public Rectangle2D getDeviceRect(Rectangle2D rect) {
        rect.setRect(this.clipRect);
        return rect;
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        float[] coords;
        Rectangle2D.Float newViewBox;
        NumberWithUnits newVal;
        boolean changeState = super.updateTime(curTime);
        StyleAttribute sty = new StyleAttribute();
        boolean shapeChange = false;
        if (this.getPres(sty.setName("x")) && !(newVal = sty.getNumberWithUnits()).equals(this.x)) {
            this.x = newVal;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("y")) && !(newVal = sty.getNumberWithUnits()).equals(this.y)) {
            this.y = newVal;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("width")) && !(newVal = sty.getNumberWithUnits()).equals(this.width)) {
            this.width = newVal;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("height")) && !(newVal = sty.getNumberWithUnits()).equals(this.height)) {
            this.height = newVal;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("viewBox")) && !(newViewBox = new Rectangle2D.Float((coords = sty.getFloatList())[0], coords[1], coords[2], coords[3])).equals(this.viewBox)) {
            this.viewBox = newViewBox;
            shapeChange = true;
        }
        if (shapeChange) {
            this.build();
        }
        return changeState || shapeChange;
    }

    public StyleSheet getStyleSheet() {
        if (this.styleSheet == null) {
            for (int i = 0; i < this.getNumChildren(); ++i) {
                SVGElement ele = this.getChild(i);
                if (!(ele instanceof Style)) continue;
                return ((Style)ele).getStyleSheet();
            }
        }
        return this.styleSheet;
    }

    public void setStyleSheet(StyleSheet styleSheet) {
        this.styleSheet = styleSheet;
    }
}

