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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTGapAmount;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTShape;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.w3c.dom.Node;

public interface CTBar3DChart
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTBar3DChart.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctbar3dcharte4c2type");

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

    public CTGapAmount getGapDepth();

    public boolean isSetGapDepth();

    public void setGapDepth(CTGapAmount var1);

    public CTGapAmount addNewGapDepth();

    public void unsetGapDepth();

    public CTShape getShape();

    public boolean isSetShape();

    public void setShape(CTShape var1);

    public CTShape addNewShape();

    public void unsetShape();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTBar3DChart.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTBar3DChart newInstance() {
            return (CTBar3DChart)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTBar3DChart newInstance(XmlOptions xmlOptions) {
            return (CTBar3DChart)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTBar3DChart parse(String string) throws XmlException {
            return (CTBar3DChart)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTBar3DChart parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTBar3DChart)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTBar3DChart parse(File file) throws XmlException, IOException {
            return (CTBar3DChart)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTBar3DChart parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBar3DChart)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTBar3DChart parse(URL uRL) throws XmlException, IOException {
            return (CTBar3DChart)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTBar3DChart parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBar3DChart)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTBar3DChart parse(InputStream inputStream) throws XmlException, IOException {
            return (CTBar3DChart)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTBar3DChart parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBar3DChart)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTBar3DChart parse(Reader reader) throws XmlException, IOException {
            return (CTBar3DChart)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTBar3DChart parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBar3DChart)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTBar3DChart parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTBar3DChart)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTBar3DChart parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTBar3DChart)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTBar3DChart parse(Node node) throws XmlException {
            return (CTBar3DChart)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTBar3DChart parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTBar3DChart)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTBar3DChart parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTBar3DChart)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTBar3DChart parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTBar3DChart)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

