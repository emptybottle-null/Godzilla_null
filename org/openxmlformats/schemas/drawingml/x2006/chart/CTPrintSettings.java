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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTHeaderFooter;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPageMargins;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPageSetup;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRelId;
import org.w3c.dom.Node;

public interface CTPrintSettings
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPrintSettings.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctprintsettings61b6type");

    public CTHeaderFooter getHeaderFooter();

    public boolean isSetHeaderFooter();

    public void setHeaderFooter(CTHeaderFooter var1);

    public CTHeaderFooter addNewHeaderFooter();

    public void unsetHeaderFooter();

    public CTPageMargins getPageMargins();

    public boolean isSetPageMargins();

    public void setPageMargins(CTPageMargins var1);

    public CTPageMargins addNewPageMargins();

    public void unsetPageMargins();

    public CTPageSetup getPageSetup();

    public boolean isSetPageSetup();

    public void setPageSetup(CTPageSetup var1);

    public CTPageSetup addNewPageSetup();

    public void unsetPageSetup();

    public CTRelId getLegacyDrawingHF();

    public boolean isSetLegacyDrawingHF();

    public void setLegacyDrawingHF(CTRelId var1);

    public CTRelId addNewLegacyDrawingHF();

    public void unsetLegacyDrawingHF();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPrintSettings.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPrintSettings newInstance() {
            return (CTPrintSettings)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPrintSettings newInstance(XmlOptions xmlOptions) {
            return (CTPrintSettings)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPrintSettings parse(String string) throws XmlException {
            return (CTPrintSettings)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPrintSettings parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPrintSettings)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPrintSettings parse(File file) throws XmlException, IOException {
            return (CTPrintSettings)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPrintSettings parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPrintSettings)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPrintSettings parse(URL uRL) throws XmlException, IOException {
            return (CTPrintSettings)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPrintSettings parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPrintSettings)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPrintSettings parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPrintSettings)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPrintSettings parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPrintSettings)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPrintSettings parse(Reader reader) throws XmlException, IOException {
            return (CTPrintSettings)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPrintSettings parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPrintSettings)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPrintSettings parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPrintSettings)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPrintSettings parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPrintSettings)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPrintSettings parse(Node node) throws XmlException {
            return (CTPrintSettings)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPrintSettings parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPrintSettings)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPrintSettings parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPrintSettings)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPrintSettings parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPrintSettings)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

