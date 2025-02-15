/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing;

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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface CTAnchorClientData
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTAnchorClientData.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctanchorclientdata02betype");

    public boolean getFLocksWithSheet();

    public XmlBoolean xgetFLocksWithSheet();

    public boolean isSetFLocksWithSheet();

    public void setFLocksWithSheet(boolean var1);

    public void xsetFLocksWithSheet(XmlBoolean var1);

    public void unsetFLocksWithSheet();

    public boolean getFPrintsWithSheet();

    public XmlBoolean xgetFPrintsWithSheet();

    public boolean isSetFPrintsWithSheet();

    public void setFPrintsWithSheet(boolean var1);

    public void xsetFPrintsWithSheet(XmlBoolean var1);

    public void unsetFPrintsWithSheet();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTAnchorClientData.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTAnchorClientData newInstance() {
            return (CTAnchorClientData)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTAnchorClientData newInstance(XmlOptions xmlOptions) {
            return (CTAnchorClientData)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTAnchorClientData parse(String string) throws XmlException {
            return (CTAnchorClientData)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTAnchorClientData parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTAnchorClientData)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTAnchorClientData parse(File file) throws XmlException, IOException {
            return (CTAnchorClientData)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTAnchorClientData parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAnchorClientData)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTAnchorClientData parse(URL uRL) throws XmlException, IOException {
            return (CTAnchorClientData)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTAnchorClientData parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAnchorClientData)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTAnchorClientData parse(InputStream inputStream) throws XmlException, IOException {
            return (CTAnchorClientData)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTAnchorClientData parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAnchorClientData)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTAnchorClientData parse(Reader reader) throws XmlException, IOException {
            return (CTAnchorClientData)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTAnchorClientData parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTAnchorClientData)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTAnchorClientData parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTAnchorClientData)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTAnchorClientData parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTAnchorClientData)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTAnchorClientData parse(Node node) throws XmlException {
            return (CTAnchorClientData)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTAnchorClientData parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTAnchorClientData)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTAnchorClientData parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTAnchorClientData)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTAnchorClientData parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTAnchorClientData)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

