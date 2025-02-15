/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.geom;

import com.graphbuilder.curve.ControlPath;
import com.graphbuilder.curve.ShapeMultiPath;
import com.graphbuilder.curve.ValueVector;
import com.graphbuilder.geom.PointFactory;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import org.apache.poi.xdgf.geom.SplineRenderer;
import org.apache.poi.xdgf.usermodel.XDGFShape;
import org.apache.poi.xdgf.usermodel.section.geometry.SplineKnot;
import org.apache.poi.xdgf.usermodel.section.geometry.SplineStart;

public class SplineCollector {
    SplineStart _start;
    ArrayList<SplineKnot> _knots = new ArrayList();

    public SplineCollector(SplineStart start) {
        this._start = start;
    }

    public void addKnot(SplineKnot knot) {
        if (!knot.getDel()) {
            this._knots.add(knot);
        }
    }

    public void addToPath(Path2D.Double path, XDGFShape parent) {
        Point2D last = path.getCurrentPoint();
        ControlPath controlPath = new ControlPath();
        ValueVector knots = new ValueVector(this._knots.size() + 3);
        double firstKnot = this._start.getB();
        double lastKnot = this._start.getC();
        int degree = this._start.getD();
        knots.add(firstKnot);
        knots.add(this._start.getA());
        controlPath.addPoint(PointFactory.create(last.getX(), last.getY()));
        controlPath.addPoint(PointFactory.create(this._start.getX(), this._start.getY()));
        for (SplineKnot knot : this._knots) {
            knots.add(knot.getA());
            controlPath.addPoint(PointFactory.create(knot.getX(), knot.getY()));
        }
        knots.add(lastKnot);
        ShapeMultiPath shape = SplineRenderer.createNurbsSpline(controlPath, knots, null, degree);
        path.append(shape, true);
    }
}

