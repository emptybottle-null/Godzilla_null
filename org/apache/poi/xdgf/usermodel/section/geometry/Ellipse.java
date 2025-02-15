/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel.section.geometry;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;
import org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow;

public class Ellipse
implements GeometryRow {
    Ellipse _master;
    Double x;
    Double y;
    Double a;
    Double b;
    Double c;
    Double d;
    Boolean deleted;

    public Ellipse(RowType row) {
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
            throw new POIXMLException("Invalid cell '" + cellName + "' in Ellipse row");
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
        this._master = (Ellipse)row;
    }

    public Path2D.Double getPath() {
        if (this.getDel()) {
            return null;
        }
        double cx = this.getX();
        double cy = this.getY();
        double a = this.getA();
        double b = this.getB();
        double c = this.getC();
        double d = this.getD();
        double rx = Math.hypot(a - cx, b - cy);
        double ry = Math.hypot(c - cx, d - cy);
        double angle = (Math.PI * 2 + (cy > b ? 1.0 : -1.0) * Math.acos((cx - a) / rx)) % (Math.PI * 2);
        Ellipse2D.Double ellipse = new Ellipse2D.Double(cx - rx, cy - ry, rx * 2.0, ry * 2.0);
        Path2D.Double path = new Path2D.Double(ellipse);
        AffineTransform tr = new AffineTransform();
        tr.rotate(angle, cx, cy);
        path.transform(tr);
        return path;
    }

    @Override
    public void addToPath(Path2D.Double path, XDGFShape parent) {
        throw new POIXMLException("Ellipse elements cannot be part of a path");
    }
}

