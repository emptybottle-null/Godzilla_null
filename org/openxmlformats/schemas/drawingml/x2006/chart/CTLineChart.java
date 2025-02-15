/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTUpDownBars
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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTGrouping;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUpDownBars;
import org.w3c.dom.Node;

public interface CTLineChart
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTLineChart.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctlinechart249ctype");

    public CTGrouping getGrouping();

    public void setGrouping(CTGrouping var1);

    public CTGrouping addNewGrouping();

    public CTBoolean getVaryColors();

    public boolean isSetVaryColors();

    public void setVaryColors(CTBoolean var1);

    public CTBoolean addNewVaryColors();

    public void unsetVaryColors();

    public List<CTLineSer> getSerList();

    @Deprecated
    public CTLineSer[] getSerArray();

    public CTLineSer getSerArray(int var1);

    public int sizeOfSerArray();

    public void setSerArray(CTLineSer[] var1);

    public void setSerArray(int var1, CTLineSer var2);

    public CTLineSer insertNewSer(int var1);

    public CTLineSer addNewSer();

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

    public CTChartLines getHiLowLines();

    public boolean isSetHiLowLines();

    public void setHiLowLines(CTChartLines var1);

    public CTChartLines addNewHiLowLines();

    public void unsetHiLowLines();

    public CTUpDownBars getUpDownBars();

    public boolean isSetUpDownBars();

    public void setUpDownBars(CTUpDownBars var1);

    public CTUpDownBars addNewUpDownBars();

    public void unsetUpDownBars();

    public CTBoolean getMarker();

    public boolean isSetMarker();

    public void setMarker(CTBoolean var1);

    public CTBoolean addNewMarker();

    public void unsetMarker();

    public CTBoolean getSmooth();

    public boolean isSetSmooth();

    public void setSmooth(CTBoolean var1);

    public CTBoolean addNewSmooth();

    public void unsetSmooth();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTLineChart.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTLineChart newInstance() {
            return (CTLineChart)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTLineChart newInstance(XmlOptions xmlOptions) {
            return (CTLineChart)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTLineChart parse(String string) throws XmlException {
            return (CTLineChart)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTLineChart parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTLineChart)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTLineChart parse(File file) throws XmlException, IOException {
            return (CTLineChart)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTLineChart parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLineChart)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTLineChart parse(URL uRL) throws XmlException, IOException {
            return (CTLineChart)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTLineChart parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLineChart)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTLineChart parse(InputStream inputStream) throws XmlException, IOException {
            return (CTLineChart)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTLineChart parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLineChart)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTLineChart parse(Reader reader) throws XmlException, IOException {
            return (CTLineChart)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTLineChart parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLineChart)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTLineChart parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTLineChart)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTLineChart parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTLineChart)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTLineChart parse(Node node) throws XmlException {
            return (CTLineChart)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTLineChart parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTLineChart)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTLineChart parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTLineChart)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTLineChart parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTLineChart)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

