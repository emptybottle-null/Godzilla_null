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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarDir;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarGrouping;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTGapAmount;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTOverlap;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.w3c.dom.Node;

public interface CTBarChart
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTBarChart.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctbarchart4151type");

    public CTBarDir getBarDir();

    public void setBarDir(CTBarDir var1);

    public CTBarDir addNewBarDir();

    public CTBarGrouping getGrouping();

    public boolean isSetGrouping();

    public void setGrouping(CTBarGrouping var1);

    public CTBarGrouping addNewGrouping();

    public void unsetGrouping();

    public CTBoolean getVaryColors();

    public boolean isSetVaryColors();

    public void setVaryColors(CTBoolean var1);

    public CTBoolean addNewVaryColors();

    public void unsetVaryColors();

    public List<CTBarSer> getSerList();

    @Deprecated
    public CTBarSer[] getSerArray();

    public CTBarSer getSerArray(int var1);

    public int sizeOfSerArray();

    public void setSerArray(CTBarSer[] var1);

    public void setSerArray(int var1, CTBarSer var2);

    public CTBarSer insertNewSer(int var1);

    public CTBarSer addNewSer();

    public void removeSer(int var1);

    public CTDLbls getDLbls();

    public boolean isSetDLbls();

    public void setDLbls(CTDLbls var1);

    public CTDLbls addNewDLbls();

    public void unsetDLbls();

    public CTGapAmount getGapWidth();

    public boolean isSetGapWidth();

    public void setGapWidth(CTGapAmount var1);

    public CTGapAmount addNewGapWidth();

    public void unsetGapWidth();

    public CTOverlap getOverlap();

    public boolean isSetOverlap();

    public void setOverlap(CTOverlap var1);

    public CTOverlap addNewOverlap();

    public void unsetOverlap();

    public List<CTChartLines> getSerLinesList();

    @Deprecated
    public CTChartLines[] getSerLinesArray();

    public CTChartLines getSerLinesArray(int var1);

    public int sizeOfSerLinesArray();

    public void setSerLinesArray(CTChartLines[] var1);

    public void setSerLinesArray(int var1, CTChartLines var2);

    public CTChartLines insertNewSerLines(int var1);

    public CTChartLines addNewSerLines();

    public void removeSerLines(int var1);

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTBarChart.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTBarChart newInstance() {
            return (CTBarChart)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTBarChart newInstance(XmlOptions xmlOptions) {
            return (CTBarChart)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTBarChart parse(String string) throws XmlException {
            return (CTBarChart)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTBarChart parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTBarChart)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTBarChart parse(File file) throws XmlException, IOException {
            return (CTBarChart)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTBarChart parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBarChart)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTBarChart parse(URL uRL) throws XmlException, IOException {
            return (CTBarChart)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTBarChart parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBarChart)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTBarChart parse(InputStream inputStream) throws XmlException, IOException {
            return (CTBarChart)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTBarChart parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBarChart)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTBarChart parse(Reader reader) throws XmlException, IOException {
            return (CTBarChart)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTBarChart parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBarChart)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTBarChart parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTBarChart)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTBarChart parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTBarChart)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTBarChart parse(Node node) throws XmlException {
            return (CTBarChart)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTBarChart parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTBarChart)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTBarChart parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTBarChart)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTBarChart parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTBarChart)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

