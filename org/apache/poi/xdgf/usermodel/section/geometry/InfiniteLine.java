/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel.section.geometry;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.RowType;
import java.awt.geom.Path2D;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;
import org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow;

public class InfiniteLine
implements GeometryRow {
    InfiniteLine _master;
    Double x;
    Double y;
    Double a;
    Double b;
    Boolean deleted;

    public InfiniteLine(RowType row) {
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
            throw new POIXMLException("Invalid cell '" + cellName + "' in InfiniteLine row");
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

    @Override
    public void setupMaster(GeometryRow row) {
        this._master = (InfiniteLine)row;
    }

    @Override
    public void addToPath(Path2D.Double path, XDGFShape parent) {
        if (this.getDel()) {
            return;
        }
        throw new POIXMLException("InfiniteLine elements cannot be part of a path");
    }

    public Path2D.Double getPath() {
        Path2D.Double path = new Path2D.Double();
        double max_val = 100000.0;
        double x0 = this.getX();
        double y0 = this.getY();
        double x1 = this.getA();
        double y1 = this.getB();
        if (x0 == x1) {
            path.moveTo(x0, -max_val);
            path.lineTo(x0, max_val);
        } else if (y0 == y1) {
            path.moveTo(-max_val, y0);
            path.lineTo(max_val, y0);
        } else {
            double m = (y1 - y0) / (x1 - x0);
            double c = y0 - m * x0;
            path.moveTo(max_val, m * max_val + c);
            path.lineTo(max_val, (max_val - c) / m);
        }
        return path;
    }
}

