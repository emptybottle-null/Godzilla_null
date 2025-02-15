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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayout;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegendEntry;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegendPos;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.w3c.dom.Node;

public interface CTLegend
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTLegend.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctlegenda54ftype");

    public CTLegendPos getLegendPos();

    public boolean isSetLegendPos();

    public void setLegendPos(CTLegendPos var1);

    public CTLegendPos addNewLegendPos();

    public void unsetLegendPos();

    public List<CTLegendEntry> getLegendEntryList();

    @Deprecated
    public CTLegendEntry[] getLegendEntryArray();

    public CTLegendEntry getLegendEntryArray(int var1);

    public int sizeOfLegendEntryArray();

    public void setLegendEntryArray(CTLegendEntry[] var1);

    public void setLegendEntryArray(int var1, CTLegendEntry var2);

    public CTLegendEntry insertNewLegendEntry(int var1);

    public CTLegendEntry addNewLegendEntry();

    public void removeLegendEntry(int var1);

    public CTLayout getLayout();

    public boolean isSetLayout();

    public void setLayout(CTLayout var1);

    public CTLayout addNewLayout();

    public void unsetLayout();

    public CTBoolean getOverlay();

    public boolean isSetOverlay();

    public void setOverlay(CTBoolean var1);

    public CTBoolean addNewOverlay();

    public void unsetOverlay();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTLegend.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTLegend newInstance() {
            return (CTLegend)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTLegend newInstance(XmlOptions xmlOptions) {
            return (CTLegend)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTLegend parse(String string) throws XmlException {
            return (CTLegend)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTLegend parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTLegend)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTLegend parse(File file) throws XmlException, IOException {
            return (CTLegend)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTLegend parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLegend)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTLegend parse(URL uRL) throws XmlException, IOException {
            return (CTLegend)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTLegend parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLegend)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTLegend parse(InputStream inputStream) throws XmlException, IOException {
            return (CTLegend)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTLegend parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLegend)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTLegend parse(Reader reader) throws XmlException, IOException {
            return (CTLegend)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTLegend parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLegend)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTLegend parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTLegend)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTLegend parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTLegend)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTLegend parse(Node node) throws XmlException {
            return (CTLegend)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTLegend parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTLegend)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTLegend parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTLegend)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTLegend parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTLegend)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

