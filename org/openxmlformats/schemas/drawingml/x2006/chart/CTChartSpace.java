/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTPivotSource
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTStyle
 *  org.openxmlformats.schemas.drawingml.x2006.chart.CTTextLanguageID
 */
package org.openxmlformats.schemas.drawingml.x2006.chart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExternalData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPivotSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRelId;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStyle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTextLanguageID;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.w3c.dom.Node;

public interface CTChartSpace
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTChartSpace.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctchartspacef9b4type");

    public CTBoolean getDate1904();

    public boolean isSetDate1904();

    public void setDate1904(CTBoolean var1);

    public CTBoolean addNewDate1904();

    public void unsetDate1904();

    public CTTextLanguageID getLang();

    public boolean isSetLang();

    public void setLang(CTTextLanguageID var1);

    public CTTextLanguageID addNewLang();

    public void unsetLang();

    public CTBoolean getRoundedCorners();

    public boolean isSetRoundedCorners();

    public void setRoundedCorners(CTBoolean var1);

    public CTBoolean addNewRoundedCorners();

    public void unsetRoundedCorners();

    public CTStyle getStyle();

    public boolean isSetStyle();

    public void setStyle(CTStyle var1);

    public CTStyle addNewStyle();

    public void unsetStyle();

    public CTColorMapping getClrMapOvr();

    public boolean isSetClrMapOvr();

    public void setClrMapOvr(CTColorMapping var1);

    public CTColorMapping addNewClrMapOvr();

    public void unsetClrMapOvr();

    public CTPivotSource getPivotSource();

    public boolean isSetPivotSource();

    public void setPivotSource(CTPivotSource var1);

    public CTPivotSource addNewPivotSource();

    public void unsetPivotSource();

    public CTProtection getProtection();

    public boolean isSetProtection();

    public void setProtection(CTProtection var1);

    public CTProtection addNewProtection();

    public void unsetProtection();

    public CTChart getChart();

    public void setChart(CTChart var1);

    public CTChart addNewChart();

    public CTShapeProperties getSpPr();

    public boolean isSetSpPr();

    public void setSpPr(CTShapeProperties var1);

    public CTShapeProperties addNewSpPr();

    public void unsetSpPr();

    public CTTextBody getTxPr();

    public boolean isSetTxPr();

    public void setTxPr(CTTextBody var1);

    public CTTextBody addNewTxPr();

    public void unsetTxPr();

    public CTExternalData getExternalData();

    public boolean isSetExternalData();

    public void setExternalData(CTExternalData var1);

    public CTExternalData addNewExternalData();

    public void unsetExternalData();

    public CTPrintSettings getPrintSettings();

    public boolean isSetPrintSettings();

    public void setPrintSettings(CTPrintSettings var1);

    public CTPrintSettings addNewPrintSettings();

    public void unsetPrintSettings();

    public CTRelId getUserShapes();

    public boolean isSetUserShapes();

    public void setUserShapes(CTRelId var1);

    public CTRelId addNewUserShapes();

    public void unsetUserShapes();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTChartSpace.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTChartSpace newInstance() {
            return (CTChartSpace)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTChartSpace newInstance(XmlOptions xmlOptions) {
            return (CTChartSpace)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTChartSpace parse(String string) throws XmlException {
            return (CTChartSpace)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTChartSpace parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTChartSpace)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTChartSpace parse(File file) throws XmlException, IOException {
            return (CTChartSpace)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTChartSpace parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTChartSpace)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTChartSpace parse(URL uRL) throws XmlException, IOException {
            return (CTChartSpace)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTChartSpace parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTChartSpace)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTChartSpace parse(InputStream inputStream) throws XmlException, IOException {
            return (CTChartSpace)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTChartSpace parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTChartSpace)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTChartSpace parse(Reader reader) throws XmlException, IOException {
            return (CTChartSpace)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTChartSpace parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTChartSpace)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTChartSpace parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTChartSpace)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTChartSpace parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTChartSpace)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTChartSpace parse(Node node) throws XmlException {
            return (CTChartSpace)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTChartSpace parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTChartSpace)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTChartSpace parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTChartSpace)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTChartSpace parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTChartSpace)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

