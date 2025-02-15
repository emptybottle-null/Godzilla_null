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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAreaSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTGrouping;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.w3c.dom.Node;

public interface CTAreaChart
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTAreaChart.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctareachart31b5type");

    public CTGrouping getGrouping();

    public boolean isSetGrouping();

    public void setGrouping(CTGrouping var1);

    public CTGrouping addNewGrouping();

    public void unsetGrouping();

    public CTBoolean getVaryColors();

    public boolean isSetVaryColors();

    public void setVaryColors(CTBoolean var1);

    public CTBoolean addNewVaryColors();

    public void unsetVaryColors();

    public List<CTAreaSer> getSerList();

    @Deprecated
    public CTAreaSer[] getSerArray();

    public CTAreaSer getSerArray(int var1);

    public int sizeOfSerArray();

    public void setSerArray(CTAreaSer[] var1);

    public void setSerArray(int var1, CTAreaSer var2);

    public CTAreaSer insertNewSer(int var1);

    public CTAreaSer addNewSer();

    public void removeSer(int var1);

    public CTDLbls getDLbls();

    public boolean isSetDLbls();

    public void setDLbls(CTDLbls var1);

    public CTDLbls addNewDLbls();

    public void unsetDLbls();

    public CTChartLines getDropLines();

    public boolean isSetDropLines();

    public void setDropLines(CTChartLines var1);

    public CTChartLines addNewDropLines();

    public void unsetDropLines();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTAreaChart.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTAreaChart newInstance() {
            return (CTAreaChart)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTAreaChart newInstance(XmlOptions xmlOptions) {
            return (CTAreaChart)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTAreaChart parse(String string) throws XmlException {
            return (CTAreaChart)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTAreaChart parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTAreaChart)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTAreaChart parse(File file) throws XmlException, IOException {
            return (CTAreaChart)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTAreaChart parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAreaChart)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTAreaChart parse(URL uRL) throws XmlException, IOException {
            return (CTAreaChart)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTAreaChart parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAreaChart)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTAreaChart parse(InputStream inputStream) throws XmlException, IOException {
            return (CTAreaChart)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTAreaChart parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAreaChart)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTAreaChart parse(Reader reader) throws XmlException, IOException {
            return (CTAreaChart)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTAreaChart parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAreaChart)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTAreaChart parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTAreaChart)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTAreaChart parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTAreaChart)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTAreaChart parse(Node node) throws XmlException {
            return (CTAreaChart)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTAreaChart parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTAreaChart)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTAreaChart parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTAreaChart)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTAreaChart parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTAreaChart)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

