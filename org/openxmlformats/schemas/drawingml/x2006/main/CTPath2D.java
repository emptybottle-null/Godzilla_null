/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo
 *  org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode
 */
package org.openxmlformats.schemas.drawingml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.List;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DArcTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DClose;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DLineTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DMoveTo;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DQuadBezierTo;
import org.openxmlformats.schemas.drawingml.x2006.main.STPathFillMode;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate;
import org.w3c.dom.Node;

public interface CTPath2D
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPath2D.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctpath2d73d2type");

    public List<CTPath2DClose> getCloseList();

    @Deprecated
    public CTPath2DClose[] getCloseArray();

    public CTPath2DClose getCloseArray(int var1);

    public int sizeOfCloseArray();

    public void setCloseArray(CTPath2DClose[] var1);

    public void setCloseArray(int var1, CTPath2DClose var2);

    public CTPath2DClose insertNewClose(int var1);

    public CTPath2DClose addNewClose();

    public void removeClose(int var1);

    public List<CTPath2DMoveTo> getMoveToList();

    @Deprecated
    public CTPath2DMoveTo[] getMoveToArray();

    public CTPath2DMoveTo getMoveToArray(int var1);

    public int sizeOfMoveToArray();

    public void setMoveToArray(CTPath2DMoveTo[] var1);

    public void setMoveToArray(int var1, CTPath2DMoveTo var2);

    public CTPath2DMoveTo insertNewMoveTo(int var1);

    public CTPath2DMoveTo addNewMoveTo();

    public void removeMoveTo(int var1);

    public List<CTPath2DLineTo> getLnToList();

    @Deprecated
    public CTPath2DLineTo[] getLnToArray();

    public CTPath2DLineTo getLnToArray(int var1);

    public int sizeOfLnToArray();

    public void setLnToArray(CTPath2DLineTo[] var1);

    public void setLnToArray(int var1, CTPath2DLineTo var2);

    public CTPath2DLineTo insertNewLnTo(int var1);

    public CTPath2DLineTo addNewLnTo();

    public void removeLnTo(int var1);

    public List<CTPath2DArcTo> getArcToList();

    @Deprecated
    public CTPath2DArcTo[] getArcToArray();

    public CTPath2DArcTo getArcToArray(int var1);

    public int sizeOfArcToArray();

    public void setArcToArray(CTPath2DArcTo[] var1);

    public void setArcToArray(int var1, CTPath2DArcTo var2);

    public CTPath2DArcTo insertNewArcTo(int var1);

    public CTPath2DArcTo addNewArcTo();

    public void removeArcTo(int var1);

    public List<CTPath2DQuadBezierTo> getQuadBezToList();

    @Deprecated
    public CTPath2DQuadBezierTo[] getQuadBezToArray();

    public CTPath2DQuadBezierTo getQuadBezToArray(int var1);

    public int sizeOfQuadBezToArray();

    public void setQuadBezToArray(CTPath2DQuadBezierTo[] var1);

    public void setQuadBezToArray(int var1, CTPath2DQuadBezierTo var2);

    public CTPath2DQuadBezierTo insertNewQuadBezTo(int var1);

    public CTPath2DQuadBezierTo addNewQuadBezTo();

    public void removeQuadBezTo(int var1);

    public List<CTPath2DCubicBezierTo> getCubicBezToList();

    @Deprecated
    public CTPath2DCubicBezierTo[] getCubicBezToArray();

    public CTPath2DCubicBezierTo getCubicBezToArray(int var1);

    public int sizeOfCubicBezToArray();

    public void setCubicBezToArray(CTPath2DCubicBezierTo[] var1);

    public void setCubicBezToArray(int var1, CTPath2DCubicBezierTo var2);

    public CTPath2DCubicBezierTo insertNewCubicBezTo(int var1);

    public CTPath2DCubicBezierTo addNewCubicBezTo();

    public void removeCubicBezTo(int var1);

    public long getW();

    public STPositiveCoordinate xgetW();

    public boolean isSetW();

    public void setW(long var1);

    public void xsetW(STPositiveCoordinate var1);

    public void unsetW();

    public long getH();

    public STPositiveCoordinate xgetH();

    public boolean isSetH();

    public void setH(long var1);

    public void xsetH(STPositiveCoordinate var1);

    public void unsetH();

    public STPathFillMode.Enum getFill();

    public STPathFillMode xgetFill();

    public boolean isSetFill();

    public void setFill(STPathFillMode.Enum var1);

    public void xsetFill(STPathFillMode var1);

    public void unsetFill();

    public boolean getStroke();

    public XmlBoolean xgetStroke();

    public boolean isSetStroke();

    public void setStroke(boolean var1);

    public void xsetStroke(XmlBoolean var1);

    public void unsetStroke();

    public boolean getExtrusionOk();

    public XmlBoolean xgetExtrusionOk();

    public boolean isSetExtrusionOk();

    public void setExtrusionOk(boolean var1);

    public void xsetExtrusionOk(XmlBoolean var1);

    public void unsetExtrusionOk();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPath2D.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPath2D newInstance() {
            return (CTPath2D)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPath2D newInstance(XmlOptions xmlOptions) {
            return (CTPath2D)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPath2D parse(String string) throws XmlException {
            return (CTPath2D)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPath2D parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPath2D)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPath2D parse(File file) throws XmlException, IOException {
            return (CTPath2D)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPath2D parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPath2D)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPath2D parse(URL uRL) throws XmlException, IOException {
            return (CTPath2D)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPath2D parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPath2D)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPath2D parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPath2D)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPath2D parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPath2D)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPath2D parse(Reader reader) throws XmlException, IOException {
            return (CTPath2D)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPath2D parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPath2D)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPath2D parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPath2D)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPath2D parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPath2D)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPath2D parse(Node node) throws XmlException {
            return (CTPath2D)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPath2D parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPath2D)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPath2D parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPath2D)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPath2D parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPath2D)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
        }

        @Deprecated
        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return Factory.getTypeLoader().newValidatingXMLInputStream(xMLInputStream, type, null);
        }

        @Deprecated
        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return Factory.getTypeLoader().newValidatingXMLInputStream(xMLInputStream, type, xmlOptions);
        }

        private Factory() {
        }
    }
}

