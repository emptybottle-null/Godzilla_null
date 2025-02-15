/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel.section.geometry;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;
import org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow;

public class EllipticalArcTo
implements GeometryRow {
    EllipticalArcTo _master;
    Double x;
    Double y;
    Double a;
    Double b;
    Double c;
    Double d;
    Boolean deleted;
    public static int draw;

    public EllipticalArcTo(RowType row) {
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
            throw new POIXMLException("Invalid cell '" + cellName + "' in EllipticalArcTo row");
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

    @Override
    public void setupMaster(GeometryRow row) {
        this._master = (EllipticalArcTo)row;
    }

    @Override
    public void addToPath(Path2D.Double path, XDGFShape parent) {
        if (this.getDel()) {
            return;
        }
        double x = this.getX();
        double y = this.getY();
        double a = this.getA();
        double b = this.getB();
        double c = this.getC();
        double d = this.getD();
        EllipticalArcTo.createEllipticalArc(x, y, a, b, c, d, path);
    }

    public static void createEllipticalArc(double x, double y, double a, double b, double c, double d, Path2D.Double path) {
        Point2D last = path.getCurrentPoint();
        double x0 = last.getX();
        double y0 = last.getY();
        AffineTransform at = AffineTransform.getRotateInstance(-c);
        double[] pts = new double[]{x0, y0, x, y, a, b};
        at.transform(pts, 0, pts, 0, 3);
        x0 = pts[0];
        y0 = pts[1];
        x = pts[2];
        y = pts[3];
        a = pts[4];
        b = pts[5];
        double d2 = d * d;
        double cx = ((x0 - x) * (x0 + x) * (y - b) - (x - a) * (x + a) * (y0 - y) + d2 * (y0 - y) * (y - b) * (y0 - b)) / (2.0 * ((x0 - x) * (y - b) - (x - a) * (y0 - y)));
        double cy = ((x0 - x) * (x - a) * (x0 - a) / d2 + (x - a) * (y0 - y) * (y0 + y) - (x0 - x) * (y - b) * (y + b)) / (2.0 * ((x - a) * (y0 - y) - (x0 - x) * (y - b)));
        double rx = Math.sqrt(Math.pow(x0 - cx, 2.0) + Math.pow(y0 - cy, 2.0) * d2);
        double ry = rx / d;
        double ctrlAngle = Math.toDegrees(Math.atan2((b - cy) / ry, (a - cx) / rx));
        double startAngle = Math.toDegrees(Math.atan2((y0 - cy) / ry, (x0 - cx) / rx));
        double endAngle = Math.toDegrees(Math.atan2((y - cy) / ry, (x - cx) / rx));
        double sweep = EllipticalArcTo.computeSweep(startAngle, endAngle, ctrlAngle);
        Arc2D.Double arc = new Arc2D.Double(cx - rx, cy - ry, rx * 2.0, ry * 2.0, -startAngle, sweep, 0);
        at.setToRotation(c);
        path.append(at.createTransformedShape(arc), false);
    }

    protected static double computeSweep(double startAngle, double endAngle, double ctrlAngle) {
        startAngle = (360.0 + startAngle) % 360.0;
        endAngle = (360.0 + endAngle) % 360.0;
        ctrlAngle = (360.0 + ctrlAngle) % 360.0;
        double sweep = startAngle < endAngle ? (startAngle < ctrlAngle && ctrlAngle < endAngle ? startAngle - endAngle : 360.0 + (startAngle - endAngle)) : (endAngle < ctrlAngle && ctrlAngle < startAngle ? startAngle - endAngle : -(360.0 - (startAngle - endAngle)));
        return sweep;
    }
}

