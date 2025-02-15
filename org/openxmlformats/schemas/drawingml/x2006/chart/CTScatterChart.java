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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterStyle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.w3c.dom.Node;

public interface CTScatterChart
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTScatterChart.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctscatterchart2bfctype");

    public CTScatterStyle getScatterStyle();

    public void setScatterStyle(CTScatterStyle var1);

    public CTScatterStyle addNewScatterStyle();

    public CTBoolean getVaryColors();

    public boolean isSetVaryColors();

    public void setVaryColors(CTBoolean var1);

    public CTBoolean addNewVaryColors();

    public void unsetVaryColors();

    public List<CTScatterSer> getSerList();

    @Deprecated
    public CTScatterSer[] getSerArray();

    public CTScatterSer getSerArray(int var1);

    public int sizeOfSerArray();

    public void setSerArray(CTScatterSer[] var1);

    public void setSerArray(int var1, CTScatterSer var2);

    public CTScatterSer insertNewSer(int var1);

    public CTScatterSer addNewSer();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTScatterChart.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTScatterChart newInstance() {
            return (CTScatterChart)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTScatterChart newInstance(XmlOptions xmlOptions) {
            return (CTScatterChart)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTScatterChart parse(String string) throws XmlException {
            return (CTScatterChart)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTScatterChart parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTScatterChart)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTScatterChart parse(File file) throws XmlException, IOException {
            return (CTScatterChart)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTScatterChart parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTScatterChart)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTScatterChart parse(URL uRL) throws XmlException, IOException {
            return (CTScatterChart)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTScatterChart parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTScatterChart)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTScatterChart parse(InputStream inputStream) throws XmlException, IOException {
            return (CTScatterChart)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTScatterChart parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTScatterChart)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTScatterChart parse(Reader reader) throws XmlException, IOException {
            return (CTScatterChart)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTScatterChart parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTScatterChart)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTScatterChart parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTScatterChart)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTScatterChart parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTScatterChart)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTScatterChart parse(Node node) throws XmlException {
            return (CTScatterChart)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTScatterChart parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTScatterChart)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTScatterChart parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTScatterChart)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTScatterChart parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTScatterChart)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

