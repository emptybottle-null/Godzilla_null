/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTBandFmts
 */
package org.openxmlformats.schemas.drawingml.x2006.chart;

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
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBandFmts;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurfaceSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.w3c.dom.Node;

public interface CTSurfaceChart
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTSurfaceChart.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctsurfacecharta96btype");

    public CTBoolean getWireframe();

    public boolean isSetWireframe();

    public void setWireframe(CTBoolean var1);

    public CTBoolean addNewWireframe();

    public void unsetWireframe();

    public List<CTSurfaceSer> getSerList();

    @Deprecated
    public CTSurfaceSer[] getSerArray();

    public CTSurfaceSer getSerArray(int var1);

    public int sizeOfSerArray();

    public void setSerArray(CTSurfaceSer[] var1);

    public void setSerArray(int var1, CTSurfaceSer var2);

    public CTSurfaceSer insertNewSer(int var1);

    public CTSurfaceSer addNewSer();

    public void removeSer(int var1);

    public CTBandFmts getBandFmts();

    public boolean isSetBandFmts();

    public void setBandFmts(CTBandFmts var1);

    public CTBandFmts addNewBandFmts();

    public void unsetBandFmts();

    public List<CTUnsignedInt> getAxIdList();

    @Deprecated
    public CTUnsignedInt[] getAxIdArray();

    public CTUnsignedInt getAxIdArray(int var1);

    public int sizeOfAxIdArray();

    public void setAxIdArray(CTUnsignedInt[] var1);

    public void setAxIdArray(int var1, CTUnsignedInt var2);

    public CTUnsignedInt insertNewAxId(int var1);

    public CTUnsignedInt addNewAxId();

    public void removeAxId(int var1);

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTSurfaceChart.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTSurfaceChart newInstance() {
            return (CTSurfaceChart)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTSurfaceChart newInstance(XmlOptions xmlOptions) {
            return (CTSurfaceChart)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTSurfaceChart parse(String string) throws XmlException {
            return (CTSurfaceChart)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTSurfaceChart parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTSurfaceChart)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTSurfaceChart parse(File file) throws XmlException, IOException {
            return (CTSurfaceChart)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTSurfaceChart parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSurfaceChart)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTSurfaceChart parse(URL uRL) throws XmlException, IOException {
            return (CTSurfaceChart)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTSurfaceChart parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSurfaceChart)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTSurfaceChart parse(InputStream inputStream) throws XmlException, IOException {
            return (CTSurfaceChart)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTSurfaceChart parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSurfaceChart)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTSurfaceChart parse(Reader reader) throws XmlException, IOException {
            return (CTSurfaceChart)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTSurfaceChart parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTSurfaceChart)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTSurfaceChart parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTSurfaceChart)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTSurfaceChart parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTSurfaceChart)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTSurfaceChart parse(Node node) throws XmlException {
            return (CTSurfaceChart)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTSurfaceChart parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTSurfaceChart)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTSurfaceChart parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTSurfaceChart)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTSurfaceChart parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTSurfaceChart)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

