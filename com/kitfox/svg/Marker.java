/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.Group;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Marker
extends Group {
    public static final String TAG_NAME = "marker";
    AffineTransform viewXform;
    AffineTransform markerXform;
    Rectangle2D viewBox;
    float refX;
    float refY;
    float markerWidth = 1.0f;
    float markerHeight = 1.0f;
    float orient = Float.NaN;
    boolean markerUnitsStrokeWidth = true;
    public static final int MARKER_START = 0;
    public static final int MARKER_MID = 1;
    public static final int MARKER_END = 2;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    protected void build() throws SVGException {
        String markerUnits;
        super.build();
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("refX"))) {
            this.refX = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("refY"))) {
            this.refY = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("markerWidth"))) {
            this.markerWidth = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("markerHeight"))) {
            this.markerHeight = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("orient"))) {
            this.orient = "auto".equals(sty.getStringValue()) ? Float.NaN : sty.getFloatValue();
        }
        if (this.getPres(sty.setName("viewBox"))) {
            float[] dim = sty.getFloatList();
            this.viewBox = new Rectangle2D.Float(dim[0], dim[1], dim[2], dim[3]);
        }
        if (this.viewBox == null) {
            this.viewBox = new Rectangle(0, 0, 1, 1);
        }
        if (this.getPres(sty.setName("markerUnits")) && (markerUnits = sty.getStringValue()) != null && markerUnits.equals("userSpaceOnUse")) {
            this.markerUnitsStrokeWidth = false;
        }
        this.viewXform = new AffineTransform();
        this.viewXform.scale(1.0 / this.viewBox.getWidth(), 1.0 / this.viewBox.getHeight());
        this.viewXform.translate(-this.viewBox.getX(), -this.viewBox.getY());
        this.markerXform = new AffineTransform();
        this.markerXform.scale(this.markerWidth, this.markerHeight);
        this.markerXform.concatenate(this.viewXform);
        this.markerXform.translate(-this.refX, -this.refY);
    }

    @Override
    protected boolean outsideClip(Graphics2D g) throws SVGException {
        Shape clip = g.getClip();
        Rectangle2D rect = super.getBoundingBox();
        return clip != null && !clip.intersects(rect);
    }

    @Override
    public void render(Graphics2D g) throws SVGException {
        AffineTransform oldXform = g.getTransform();
        g.transform(this.markerXform);
        super.render(g);
        g.setTransform(oldXform);
    }

    public void render(Graphics2D g, MarkerPos pos, float strokeWidth) throws SVGException {
        AffineTransform cacheXform = g.getTransform();
        g.translate(pos.x, pos.y);
        if (this.markerUnitsStrokeWidth) {
            g.scale(strokeWidth, strokeWidth);
        }
        g.rotate(Math.atan2(pos.dy, pos.dx));
        g.transform(this.markerXform);
        super.render(g);
        g.setTransform(cacheXform);
    }

    @Override
    public Shape getShape() {
        Shape shape = super.getShape();
        return this.markerXform.createTransformedShape(shape);
    }

    @Override
    public Rectangle2D getBoundingBox() throws SVGException {
        Rectangle2D rect = super.getBoundingBox();
        return this.markerXform.createTransformedShape(rect).getBounds2D();
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        boolean changeState = super.updateTime(curTime);
        this.build();
        return changeState;
    }

    public static class MarkerLayout {
        private ArrayList<MarkerPos> markerList = new ArrayList();
        boolean started = false;

        public void layout(Shape shape) {
            double px = 0.0;
            double py = 0.0;
            double[] coords = new double[6];
            PathIterator it = shape.getPathIterator(null);
            while (!it.isDone()) {
                switch (it.currentSegment(coords)) {
                    case 0: {
                        px = coords[0];
                        py = coords[1];
                        this.started = false;
                        break;
                    }
                    case 4: {
                        this.started = false;
                        break;
                    }
                    case 1: {
                        double x = coords[0];
                        double y = coords[1];
                        this.markerIn(px, py, x - px, y - py);
                        this.markerOut(x, y, x - px, y - py);
                        px = x;
                        py = y;
                        break;
                    }
                    case 2: {
                        double k0x = coords[0];
                        double k0y = coords[1];
                        double x = coords[2];
                        double y = coords[3];
                        if (px != k0x || py != k0y) {
                            this.markerIn(px, py, k0x - px, k0y - py);
                        } else {
                            this.markerIn(px, py, x - px, y - py);
                        }
                        if (x != k0x || y != k0y) {
                            this.markerOut(x, y, x - k0x, y - k0y);
                        } else {
                            this.markerOut(x, y, x - px, y - py);
                        }
                        this.markerIn(px, py, k0x - px, k0y - py);
                        this.markerOut(x, y, x - k0x, y - k0y);
                        px = x;
                        py = y;
                        break;
                    }
                    case 3: {
                        double k0x = coords[0];
                        double k0y = coords[1];
                        double k1x = coords[2];
                        double k1y = coords[3];
                        double x = coords[4];
                        double y = coords[5];
                        if (px != k0x || py != k0y) {
                            this.markerIn(px, py, k0x - px, k0y - py);
                        } else if (px != k1x || py != k1y) {
                            this.markerIn(px, py, k1x - px, k1y - py);
                        } else {
                            this.markerIn(px, py, x - px, y - py);
                        }
                        if (x != k1x || y != k1y) {
                            this.markerOut(x, y, x - k1x, y - k1y);
                        } else if (x != k0x || y != k0y) {
                            this.markerOut(x, y, x - k0x, y - k0y);
                        } else {
                            this.markerOut(x, y, x - px, y - py);
                        }
                        px = x;
                        py = y;
                        break;
                    }
                }
                it.next();
            }
            for (int i = 1; i < this.markerList.size(); ++i) {
                MarkerPos prev = this.markerList.get(i - 1);
                MarkerPos cur = this.markerList.get(i);
                if (cur.type != 0) continue;
                prev.type = 2;
            }
            MarkerPos last = this.markerList.get(this.markerList.size() - 1);
            last.type = 2;
        }

        private void markerIn(double x, double y, double dx, double dy) {
            if (!this.started) {
                this.started = true;
                this.markerList.add(new MarkerPos(0, x, y, dx, dy));
            }
        }

        private void markerOut(double x, double y, double dx, double dy) {
            this.markerList.add(new MarkerPos(1, x, y, dx, dy));
        }

        public ArrayList<MarkerPos> getMarkerList() {
            return this.markerList;
        }
    }

    public static class MarkerPos {
        int type;
        double x;
        double y;
        double dx;
        double dy;

        public MarkerPos(int type, double x, double y, double dx, double dy) {
            this.type = type;
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
        }
    }
}

