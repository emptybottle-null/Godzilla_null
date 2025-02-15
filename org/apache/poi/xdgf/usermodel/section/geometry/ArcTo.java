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

public class ArcTo
implements GeometryRow {
    ArcTo _master;
    Double x;
    Double y;
    Double a;
    Boolean deleted;

    public ArcTo(RowType row) {
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
            throw new POIXMLException("Invalid cell '" + cellName + "' in ArcTo row");
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

    @Override
    public void setupMaster(GeometryRow row) {
        this._master = (ArcTo)row;
    }

    @Override
    public void addToPath(Path2D.Double path, XDGFShape parent) {
        if (this.getDel()) {
            return;
        }
        Point2D last = path.getCurrentPoint();
        double x = this.getX();
        double y = this.getY();
        double a = this.getA();
        if (a == 0.0) {
            path.lineTo(x, y);
            return;
        }
        double x0 = last.getX();
        double y0 = last.getY();
        double chordLength = Math.hypot(y - y0, x - x0);
        double radius = (4.0 * a * a + chordLength * chordLength) / (8.0 * Math.abs(a));
        double cx = x0 + (x - x0) / 2.0;
        double cy = y0 + (y - y0) / 2.0;
        double rotate = Math.atan2(y - cy, x - cx);
        Arc2D.Double arc = new Arc2D.Double(x0, y0 - radius, chordLength, 2.0 * radius, 180.0, x0 < x ? 180.0 : -180.0, 0);
        path.append(AffineTransform.getRotateInstance(rotate, x0, y0).createTransformedShape(arc), true);
    }
}

