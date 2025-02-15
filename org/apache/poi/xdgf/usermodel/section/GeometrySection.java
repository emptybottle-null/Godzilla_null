/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel.section;

import com.microsoft.schemas.office.visio.x2012.main.RowType;
import com.microsoft.schemas.office.visio.x2012.main.SectionType;
import java.awt.geom.Path2D;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.geom.SplineCollector;
import org.apache.poi.xdgf.usermodel.XDGFCell;
import org.apache.poi.xdgf.usermodel.XDGFShape;
import org.apache.poi.xdgf.usermodel.XDGFSheet;
import org.apache.poi.xdgf.usermodel.section.CombinedIterable;
import org.apache.poi.xdgf.usermodel.section.XDGFSection;
import org.apache.poi.xdgf.usermodel.section.geometry.Ellipse;
import org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow;
import org.apache.poi.xdgf.usermodel.section.geometry.GeometryRowFactory;
import org.apache.poi.xdgf.usermodel.section.geometry.InfiniteLine;
import org.apache.poi.xdgf.usermodel.section.geometry.SplineKnot;
import org.apache.poi.xdgf.usermodel.section.geometry.SplineStart;

public class GeometrySection
extends XDGFSection {
    GeometrySection _master;
    SortedMap<Long, GeometryRow> _rows = new TreeMap<Long, GeometryRow>();

    public GeometrySection(SectionType section, XDGFSheet containingSheet) {
        super(section, containingSheet);
        for (RowType row : section.getRowArray()) {
            if (this._rows.containsKey(row.getIX())) {
                throw new POIXMLException("Index element '" + row.getIX() + "' already exists");
            }
            this._rows.put(row.getIX(), GeometryRowFactory.load(row));
        }
    }

    @Override
    public void setupMaster(XDGFSection master) {
        this._master = (GeometrySection)master;
        for (Map.Entry<Long, GeometryRow> entry : this._rows.entrySet()) {
            GeometryRow masterRow = (GeometryRow)this._master._rows.get(entry.getKey());
            if (masterRow == null) continue;
            try {
                entry.getValue().setupMaster(masterRow);
            } catch (ClassCastException classCastException) {}
        }
    }

    public Boolean getNoShow() {
        Boolean noShow = XDGFCell.maybeGetBoolean(this._cells, "NoShow");
        if (noShow == null) {
            if (this._master != null) {
                return this._master.getNoShow();
            }
            return false;
        }
        return noShow;
    }

    public Iterable<GeometryRow> getCombinedRows() {
        return new CombinedIterable<GeometryRow>(this._rows, this._master == null ? null : this._master._rows);
    }

    public Path2D.Double getPath(XDGFShape parent) {
        GeometryRow first;
        Iterator<GeometryRow> rows = this.getCombinedRows().iterator();
        GeometryRow geometryRow = first = rows.hasNext() ? rows.next() : null;
        if (first instanceof Ellipse) {
            return ((Ellipse)first).getPath();
        }
        if (first instanceof InfiniteLine) {
            return ((InfiniteLine)first).getPath();
        }
        if (first instanceof SplineStart) {
            throw new POIXMLException("SplineStart must be preceded by another type");
        }
        Path2D.Double path = new Path2D.Double();
        SplineCollector renderer = null;
        while (true) {
            GeometryRow row;
            if (first != null) {
                row = first;
                first = null;
            } else {
                if (!rows.hasNext()) break;
                row = rows.next();
            }
            if (row instanceof SplineStart) {
                if (renderer != null) {
                    throw new POIXMLException("SplineStart found multiple times!");
                }
                renderer = new SplineCollector((SplineStart)row);
                continue;
            }
            if (row instanceof SplineKnot) {
                if (renderer == null) {
                    throw new POIXMLException("SplineKnot found without SplineStart!");
                }
                renderer.addKnot((SplineKnot)row);
                continue;
            }
            if (renderer != null) {
                renderer.addToPath(path, parent);
                renderer = null;
            }
            row.addToPath(path, parent);
        }
        if (renderer != null) {
            renderer.addToPath(path, parent);
        }
        return path;
    }
}

