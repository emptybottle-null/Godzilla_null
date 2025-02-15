/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarStyle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.w3c.dom.Node;

public interface CTRadarChart
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTRadarChart.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctradarchart0f04type");

    public CTRadarStyle getRadarStyle();

    public void setRadarStyle(CTRadarStyle var1);

    public CTRadarStyle addNewRadarStyle();

    public CTBoolean getVaryColors();

    public boolean isSetVaryColors();

    public void setVaryColors(CTBoolean var1);

    public CTBoolean addNewVaryColors();

    public void unsetVaryColors();

    public List<CTRadarSer> getSerList();

    @Deprecated
    public CTRadarSer[] getSerArray();

    public CTRadarSer getSerArray(int var1);

    public int sizeOfSerArray();

    public void setSerArray(CTRadarSer[] var1);

    public void setSerArray(int var1, CTRadarSer var2);

    public CTRadarSer insertNewSer(int var1);

    public CTRadarSer addNewSer();

    public void removeSer(int var1);

    public CTDLbls getDLbls();

    public boolean isSetDLbls();

    public void setDLbls(CTDLbls var1);

    public CTDLbls addNewDLbls();

    public void unsetDLbls();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTRadarChart.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTRadarChart newInstance() {
            return (CTRadarChart)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTRadarChart newInstance(XmlOptions xmlOptions) {
            return (CTRadarChart)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTRadarChart parse(String string) throws XmlException {
            return (CTRadarChart)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTRadarChart parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTRadarChart)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTRadarChart parse(File file) throws XmlException, IOException {
            return (CTRadarChart)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTRadarChart parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRadarChart)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTRadarChart parse(URL uRL) throws XmlException, IOException {
            return (CTRadarChart)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTRadarChart parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRadarChart)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTRadarChart parse(InputStream inputStream) throws XmlException, IOException {
            return (CTRadarChart)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTRadarChart parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRadarChart)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTRadarChart parse(Reader reader) throws XmlException, IOException {
            return (CTRadarChart)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTRadarChart parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRadarChart)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTRadarChart parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTRadarChart)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTRadarChart parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTRadarChart)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTRadarChart parse(Node node) throws XmlException {
            return (CTRadarChart)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTRadarChart parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTRadarChart)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTRadarChart parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTRadarChart)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTRadarChart parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTRadarChart)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

