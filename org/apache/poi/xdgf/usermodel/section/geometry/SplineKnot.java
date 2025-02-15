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

public class SplineKnot
implements GeometryRow {
    SplineKnot _master;
    Double x;
    Double y;
    Double a;
    Boolean deleted;

    public SplineKnot(RowType row) {
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
            throw new POIXMLException("Invalid cell '" + cellName + "' in SplineKnot row");
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
        this._master = (SplineKnot)row;
    }

    @Override
    public void addToPath(Path2D.Double path, XDGFShape parent) {
        throw new POIXMLException("Error: Use SplineRenderer!");
    }

    public String toString() {
        return "{SplineKnot x=" + this.getX() + " y=" + this.getY() + " a=" + this.getA() + "}";
    }
}

