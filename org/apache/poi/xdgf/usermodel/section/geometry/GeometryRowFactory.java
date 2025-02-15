/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel.section.geometry;

import com.microsoft.schemas.office.visio.x2012.main.RowType;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.xdgf.usermodel.section.geometry.ArcTo;
import org.apache.poi.xdgf.usermodel.section.geometry.Ellipse;
import org.apache.poi.xdgf.usermodel.section.geometry.EllipticalArcTo;
import org.apache.poi.xdgf.usermodel.section.geometry.GeometryRow;
import org.apache.poi.xdgf.usermodel.section.geometry.InfiniteLine;
import org.apache.poi.xdgf.usermodel.section.geometry.LineTo;
import org.apache.poi.xdgf.usermodel.section.geometry.MoveTo;
import org.apache.poi.xdgf.usermodel.section.geometry.NURBSTo;
import org.apache.poi.xdgf.usermodel.section.geometry.PolyLineTo;
import org.apache.poi.xdgf.usermodel.section.geometry.RelCubBezTo;
import org.apache.poi.xdgf.usermodel.section.geometry.RelEllipticalArcTo;
import org.apache.poi.xdgf.usermodel.section.geometry.RelLineTo;
import org.apache.poi.xdgf.usermodel.section.geometry.RelMoveTo;
import org.apache.poi.xdgf.usermodel.section.geometry.RelQuadBezTo;
import org.apache.poi.xdgf.usermodel.section.geometry.SplineKnot;
import org.apache.poi.xdgf.usermodel.section.geometry.SplineStart;
import org.apache.poi.xdgf.util.ObjectFactory;

public class GeometryRowFactory {
    static final ObjectFactory<GeometryRow, RowType> _rowTypes = new ObjectFactory();

    public static GeometryRow load(RowType row) {
        return _rowTypes.load(row.getT(), row);
    }

    static {
        try {
            _rowTypes.put("ArcTo", ArcTo.class, RowType.class);
            _rowTypes.put("Ellipse", Ellipse.class, RowType.class);
            _rowTypes.put("EllipticalArcTo", EllipticalArcTo.class, RowType.class);
            _rowTypes.put("InfiniteLine", InfiniteLine.class, RowType.class);
            _rowTypes.put("LineTo", LineTo.class, RowType.class);
            _rowTypes.put("MoveTo", MoveTo.class, RowType.class);
            _rowTypes.put("NURBSTo", NURBSTo.class, RowType.class);
            _rowTypes.put("PolylineTo", PolyLineTo.class, RowType.class);
            _rowTypes.put("PolyLineTo", PolyLineTo.class, RowType.class);
            _rowTypes.put("RelCubBezTo", RelCubBezTo.class, RowType.class);
            _rowTypes.put("RelEllipticalArcTo", RelEllipticalArcTo.class, RowType.class);
            _rowTypes.put("RelLineTo", RelLineTo.class, RowType.class);
            _rowTypes.put("RelMoveTo", RelMoveTo.class, RowType.class);
            _rowTypes.put("RelQuadBezTo", RelQuadBezTo.class, RowType.class);
            _rowTypes.put("SplineKnot", SplineKnot.class, RowType.class);
            _rowTypes.put("SplineStart", SplineStart.class, RowType.class);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new POIXMLException("Internal error", e);
        }
    }
}

