/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.sl.draw.geom.CustomGeometry;
import org.apache.poi.sl.draw.geom.PresetGeometries;
import org.apache.poi.sl.usermodel.FreeformShape;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.usermodel.XSLFAutoShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DClose;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DLineTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DMoveTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DQuadBezierTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShapeNonVisual;

public class XSLFFreeformShape
extends XSLFAutoShape
implements FreeformShape<XSLFShape, XSLFTextParagraph> {
    private static final POILogger LOG = POILogFactory.getLogger(XSLFFreeformShape.class);

    XSLFFreeformShape(CTShape shape, XSLFSheet sheet) {
        super(shape, sheet);
    }

    @Override
    public int setPath(Path2D path) {
        CTPath2D ctPath = CTPath2D.Factory.newInstance();
        Rectangle2D bounds = path.getBounds2D();
        int x0 = Units.toEMU(bounds.getX());
        int y0 = Units.toEMU(bounds.getY());
        PathIterator it = path.getPathIterator(new AffineTransform());
        int numPoints = 0;
        ctPath.setH(Units.toEMU(bounds.getHeight()));
        ctPath.setW(Units.toEMU(bounds.getWidth()));
        double[] vals = new double[6];
        while (!it.isDone()) {
            CTAdjPoint2D[] points;
            int type = it.currentSegment(vals);
            switch (type) {
                case 0: {
                    points = XSLFFreeformShape.addMoveTo(ctPath);
                    break;
                }
                case 1: {
                    points = XSLFFreeformShape.addLineTo(ctPath);
                    break;
                }
                case 2: {
                    points = XSLFFreeformShape.addQuadBezierTo(ctPath);
                    break;
                }
                case 3: {
                    points = XSLFFreeformShape.addCubicBezierTo(ctPath);
                    break;
                }
                case 4: {
                    points = XSLFFreeformShape.addClosePath(ctPath);
                    break;
                }
                default: {
                    throw new IllegalStateException("Unrecognized path segment type: " + type);
                }
            }
            int i = 0;
            for (CTAdjPoint2D point : points) {
                point.setX(Units.toEMU(vals[i++]) - x0);
                point.setY(Units.toEMU(vals[i++]) - y0);
            }
            numPoints += Math.max(points.length, 1);
            it.next();
        }
        XmlObject xo = this.getShapeProperties();
        if (!(xo instanceof CTShapeProperties)) {
            return -1;
        }
        ((CTShapeProperties)xo).getCustGeom().getPathLst().setPathArray(new CTPath2D[]{ctPath});
        this.setAnchor(bounds);
        return numPoints;
    }

    @Override
    public CustomGeometry getGeometry() {
        XmlObject xo = this.getShapeProperties();
        if (!(xo instanceof CTShapeProperties)) {
            return null;
        }
        XmlOptions xop = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xop.setSaveOuter();
        XMLStreamReader staxReader = ((CTShapeProperties)xo).getCustGeom().newXMLStreamReader(xop);
        CustomGeometry custGeo = PresetGeometries.convertCustomGeometry(staxReader);
        try {
            staxReader.close();
        } catch (XMLStreamException e) {
            LOG.log(5, "An error occurred while closing a Custom Geometry XML Stream Reader: " + e.getMessage());
        }
        return custGeo;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Path2D.Double getPath() {
        Path2D.Double path = new Path2D.Double();
        XmlObject xo = this.getShapeProperties();
        if (!(xo instanceof CTShapeProperties)) {
            return null;
        }
        CTCustomGeometry2D geom = ((CTShapeProperties)xo).getCustGeom();
        for (CTPath2D spPath : geom.getPathLst().getPathArray()) {
            XmlCursor cursor = spPath.newCursor();
            try {
                if (!cursor.toFirstChild()) continue;
                do {
                    XmlObject ch;
                    if ((ch = cursor.getObject()) instanceof CTPath2DMoveTo) {
                        XSLFFreeformShape.addMoveTo(path, (CTPath2DMoveTo)ch);
                        continue;
                    }
                    if (ch instanceof CTPath2DLineTo) {
                        XSLFFreeformShape.addLineTo(path, (CTPath2DLineTo)ch);
                        continue;
                    }
                    if (ch instanceof CTPath2DQuadBezierTo) {
                        XSLFFreeformShape.addQuadBezierTo(path, (CTPath2DQuadBezierTo)ch);
                        continue;
                    }
                    if (ch instanceof CTPath2DCubicBezierTo) {
                        XSLFFreeformShape.addCubicBezierTo(path, (CTPath2DCubicBezierTo)ch);
                        continue;
                    }
                    if (ch instanceof CTPath2DClose) {
                        XSLFFreeformShape.addClosePath(path);
                        continue;
                    }
                    LOG.log(5, "can't handle path of type " + xo.getClass());
                } while (cursor.toNextSibling());
            } finally {
                cursor.dispose();
            }
        }
        AffineTransform at = new AffineTransform();
        CTTransform2D xfrm = this.getXfrm(false);
        Rectangle2D.Double xfrm2d = new Rectangle2D.Double(xfrm.getOff().getX(), xfrm.getOff().getY(), xfrm.getExt().getCx(), xfrm.getExt().getCy());
        Rectangle2D bounds = this.getAnchor();
        at.translate(bounds.getX() + bounds.getCenterX(), bounds.getY() + bounds.getCenterY());
        at.scale(7.874015748031496E-5, 7.874015748031496E-5);
        at.translate(-xfrm2d.getCenterX(), -xfrm2d.getCenterY());
        return new Path2D.Double(at.createTransformedShape(path));
    }

    private static CTAdjPoint2D[] addMoveTo(CTPath2D path) {
        return new CTAdjPoint2D[]{path.addNewMoveTo().addNewPt()};
    }

    private static void addMoveTo(Path2D path, CTPath2DMoveTo xo) {
        CTAdjPoint2D pt = xo.getPt();
        path.moveTo(((Long)pt.getX()).longValue(), ((Long)pt.getY()).longValue());
    }

    private static CTAdjPoint2D[] addLineTo(CTPath2D path) {
        return new CTAdjPoint2D[]{path.addNewLnTo().addNewPt()};
    }

    private static void addLineTo(Path2D path, CTPath2DLineTo xo) {
        CTAdjPoint2D pt = xo.getPt();
        path.lineTo(((Long)pt.getX()).longValue(), ((Long)pt.getY()).longValue());
    }

    private static CTAdjPoint2D[] addQuadBezierTo(CTPath2D path) {
        CTPath2DQuadBezierTo bez = path.addNewQuadBezTo();
        return new CTAdjPoint2D[]{bez.addNewPt(), bez.addNewPt()};
    }

    private static void addQuadBezierTo(Path2D path, CTPath2DQuadBezierTo xo) {
        CTAdjPoint2D pt1 = xo.getPtArray(0);
        CTAdjPoint2D pt2 = xo.getPtArray(1);
        path.quadTo(((Long)pt1.getX()).longValue(), ((Long)pt1.getY()).longValue(), ((Long)pt2.getX()).longValue(), ((Long)pt2.getY()).longValue());
    }

    private static CTAdjPoint2D[] addCubicBezierTo(CTPath2D path) {
        CTPath2DCubicBezierTo bez = path.addNewCubicBezTo();
        return new CTAdjPoint2D[]{bez.addNewPt(), bez.addNewPt(), bez.addNewPt()};
    }

    private static void addCubicBezierTo(Path2D path, CTPath2DCubicBezierTo xo) {
        CTAdjPoint2D pt1 = xo.getPtArray(0);
        CTAdjPoint2D pt2 = xo.getPtArray(1);
        CTAdjPoint2D pt3 = xo.getPtArray(2);
        path.curveTo(((Long)pt1.getX()).longValue(), ((Long)pt1.getY()).longValue(), ((Long)pt2.getX()).longValue(), ((Long)pt2.getY()).longValue(), ((Long)pt3.getX()).longValue(), ((Long)pt3.getY()).longValue());
    }

    private static CTAdjPoint2D[] addClosePath(CTPath2D path) {
        path.addNewClose();
        return new CTAdjPoint2D[0];
    }

    private static void addClosePath(Path2D path) {
        path.closePath();
    }

    static CTShape prototype(int shapeId) {
        CTShape ct = CTShape.Factory.newInstance();
        CTShapeNonVisual nvSpPr = ct.addNewNvSpPr();
        CTNonVisualDrawingProps cnv = nvSpPr.addNewCNvPr();
        cnv.setName("Freeform " + shapeId);
        cnv.setId(shapeId);
        nvSpPr.addNewCNvSpPr();
        nvSpPr.addNewNvPr();
        CTShapeProperties spPr = ct.addNewSpPr();
        CTCustomGeometry2D geom = spPr.addNewCustGeom();
        geom.addNewAvLst();
        geom.addNewGdLst();
        geom.addNewAhLst();
        geom.addNewCxnLst();
        CTGeomRect rect = geom.addNewRect();
        rect.setR("r");
        rect.setB("b");
        rect.setT("t");
        rect.setL("l");
        geom.addNewPathLst();
        return ct;
    }
}

