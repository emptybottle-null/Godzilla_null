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
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarkerSize;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarkerStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.w3c.dom.Node;

public interface CTMarker
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTMarker.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctmarkera682type");

    public CTMarkerStyle getSymbol();

    public boolean isSetSymbol();

    public void setSymbol(CTMarkerStyle var1);

    public CTMarkerStyle addNewSymbol();

    public void unsetSymbol();

    public CTMarkerSize getSize();

    public boolean isSetSize();

    public void setSize(CTMarkerSize var1);

    public CTMarkerSize addNewSize();

    public void unsetSize();

    public CTShapeProperties getSpPr();

    public boolean isSetSpPr();

    public void setSpPr(CTShapeProperties var1);

    public CTShapeProperties addNewSpPr();

    public void unsetSpPr();

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTMarker.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTMarker newInstance() {
            return (CTMarker)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTMarker newInstance(XmlOptions xmlOptions) {
            return (CTMarker)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTMarker parse(String string) throws XmlException {
            return (CTMarker)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTMarker parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTMarker)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTMarker parse(File file) throws XmlException, IOException {
            return (CTMarker)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTMarker parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTMarker)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTMarker parse(URL uRL) throws XmlException, IOException {
            return (CTMarker)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTMarker parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTMarker)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTMarker parse(InputStream inputStream) throws XmlException, IOException {
            return (CTMarker)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTMarker parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTMarker)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTMarker parse(Reader reader) throws XmlException, IOException {
            return (CTMarker)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTMarker parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTMarker)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTMarker parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTMarker)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTMarker parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTMarker)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTMarker parse(Node node) throws XmlException {
            return (CTMarker)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTMarker parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTMarker)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTMarker parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTMarker)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTMarker parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTMarker)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

