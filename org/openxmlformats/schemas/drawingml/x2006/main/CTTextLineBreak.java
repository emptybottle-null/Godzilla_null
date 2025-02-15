/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main;

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
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.w3c.dom.Node;

public interface CTTextLineBreak
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTextLineBreak.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttextlinebreak932ftype");

    public CTTextCharacterProperties getRPr();

    public boolean isSetRPr();

    public void setRPr(CTTextCharacterProperties var1);

    public CTTextCharacterProperties addNewRPr();

    public void unsetRPr();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTextLineBreak.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTextLineBreak newInstance() {
            return (CTTextLineBreak)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTextLineBreak newInstance(XmlOptions xmlOptions) {
            return (CTTextLineBreak)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTextLineBreak parse(String string) throws XmlException {
            return (CTTextLineBreak)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTextLineBreak parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTextLineBreak)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTextLineBreak parse(File file) throws XmlException, IOException {
            return (CTTextLineBreak)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTextLineBreak parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextLineBreak)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTextLineBreak parse(URL uRL) throws XmlException, IOException {
            return (CTTextLineBreak)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTextLineBreak parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextLineBreak)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTextLineBreak parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTextLineBreak)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTextLineBreak parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextLineBreak)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTextLineBreak parse(Reader reader) throws XmlException, IOException {
            return (CTTextLineBreak)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTextLineBreak parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextLineBreak)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTextLineBreak parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTextLineBreak)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTextLineBreak parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTextLineBreak)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTextLineBreak parse(Node node) throws XmlException {
            return (CTTextLineBreak)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTextLineBreak parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTextLineBreak)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTextLineBreak parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTextLineBreak)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTextLineBreak parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTextLineBreak)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

