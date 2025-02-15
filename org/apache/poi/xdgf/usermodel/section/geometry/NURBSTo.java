/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel.section.geometry;

import com.graphbuilder.curve.ControlPath;
import com.graphbuilder.curve.ShapeMultiPath;
import com.graphbuilder.curve.ValueVector;
import com.graphbuilder.geom.PointFactory;
import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.geom.SplineRenderer;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;
import org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow;

public class NURBSTo
implements GeometryRow {
    NURBSTo _master;
    Double x;
    Double y;
    Double a;
    Double b;
    Double c;
    Double d;
    String e;
    Boolean deleted;

    public NURBSTo(RowType row) {
        if (row.isSetDel()) {
            this.deleted = row.getDel();
        }
        for (CellType cell : row.getCellArray()) {
            String cellName = cell.getN();
            if (cellName.equals("X")) {
                this.x = XDGFCell.parseDoubleValue(cell);
                continue;
            }
            if (cellName.equals("Y")) {
                this.y = XDGFCell.parseDoubleValue(cell);
                continue;
            }
            if (cellName.equals("A")) {
                this.a = XDGFCell.parseDoubleValue(cell);
                continue;
            }
            if (cellName.equals("B")) {
                this.b = XDGFCell.parseDoubleValue(cell);
                continue;
            }
            if (cellName.equals("C")) {
                this.c = XDGFCell.parseDoubleValue(cell);
                continue;
            }
            if (cellName.equals("D")) {
                this.d = XDGFCell.parseDoubleValue(cell);
                continue;
            }
            if (cellName.equals("E")) {
                this.e = cell.getV();
                continue;
            }
            throw new POIXMLException("Invalid cell '" + cellName + "' in NURBS row");
        }
    }

    public boolean getDel() {
        if (this.deleted != null) {
            return this.deleted;
        }
        return this._master != null && this._master.getDel();
    }

    public Double getX() {
        return this.x == null ? this._master.x : this.x;
    }

    public Double getY() {
        return this.y == null ? this._master.y : this.y;
    }

    public Double getA() {
        return this.a == null ? this._master.a : this.a;
    }

    public Double getB() {
        return this.b == null ? this._master.b : this.b;
    }

    public Double getC() {
        return this.c == null ? this._master.c : this.c;
    }

    public Double getD() {
        return this.d == null ? this._master.d : this.d;
    }

    public String getE() {
        return this.e == null ? this._master.e : this.e;
    }

    @Override
    public void setupMaster(GeometryRow row) {
        this._master = (NURBSTo)row;
    }

    @Override
    public void addToPath(Path2D.Double path, XDGFShape parent) {
        if (this.getDel()) {
            return;
        }
        Point2D last = path.getCurrentPoint();
        String formula = this.getE().trim();
        if (!formula.startsWith("NURBS(") || !formula.endsWith(")")) {
            throw new POIXMLException("Invalid NURBS formula: " + formula);
        }
        String[] components = formula.substring(6, formula.length() - 1).split(",");
        if (components.length < 8) {
            throw new POIXMLException("Invalid NURBS formula (not enough arguments)");
        }
        if ((components.length - 4) % 4 != 0) {
            throw new POIXMLException("Invalid NURBS formula -- need 4 + n*4 arguments, got " + components.length);
        }
        double lastControlX = this.getX();
        double lastControlY = this.getY();
        double secondToLastKnot = this.getA();
        double lastWeight = this.getB();
        double firstKnot = this.getC();
        double firstWeight = this.getD();
        double lastKnot = Double.parseDouble(components[0].trim());
        int degree = Integer.parseInt(components[1].trim());
        int xType = Integer.parseInt(components[2].trim());
        int yType = Integer.parseInt(components[3].trim());
        double xScale = 1.0;
        double yScale = 1.0;
        if (xType == 0) {
            xScale = parent.getWidth();
        }
        if (yType == 0) {
            yScale = parent.getHeight();
        }
        ControlPath controlPath = new ControlPath();
        ValueVector knots = new ValueVector();
        ValueVector weights = new ValueVector();
        knots.add(firstKnot);
        weights.add(firstWeight);
        controlPath.addPoint(PointFactory.create(last.getX(), last.getY()));
        int sets = (components.length - 4) / 4;
        for (int i = 0; i < sets; ++i) {
            double x1 = Double.parseDouble(components[4 + i * 4 + 0].trim());
            double y1 = Double.parseDouble(components[4 + i * 4 + 1].trim());
            double k = Double.parseDouble(components[4 + i * 4 + 2].trim());
            double w = Double.parseDouble(components[4 + i * 4 + 3].trim());
            controlPath.addPoint(PointFactory.create(x1 * xScale, y1 * yScale));
            knots.add(k);
            weights.add(w);
        }
        knots.add(secondToLastKnot);
        knots.add(lastKnot);
        weights.add(lastWeight);
        controlPath.addPoint(PointFactory.create(lastControlX, lastControlY));
        ShapeMultiPath shape = SplineRenderer.createNurbsSpline(controlPath, knots, weights, degree);
        path.append(shape, true);
    }
}

