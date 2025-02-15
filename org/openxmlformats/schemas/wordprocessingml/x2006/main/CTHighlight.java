/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;
import org.w3c.dom.Node;

public interface CTHighlight
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTHighlight.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cthighlight071etype");

    public STHighlightColor.Enum getVal();

    public STHighlightColor xgetVal();

    public void setVal(STHighlightColor.Enum var1);

    public void xsetVal(STHighlightColor var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTHighlight.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTHighlight newInstance() {
            return (CTHighlight)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTHighlight newInstance(XmlOptions xmlOptions) {
            return (CTHighlight)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTHighlight parse(String string) throws XmlException {
            return (CTHighlight)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTHighlight parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTHighlight)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTHighlight parse(File file) throws XmlException, IOException {
            return (CTHighlight)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTHighlight parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHighlight)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTHighlight parse(URL uRL) throws XmlException, IOException {
            return (CTHighlight)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTHighlight parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHighlight)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTHighlight parse(InputStream inputStream) throws XmlException, IOException {
            return (CTHighlight)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTHighlight parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHighlight)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTHighlight parse(Reader reader) throws XmlException, IOException {
            return (CTHighlight)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTHighlight parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHighlight)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTHighlight parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTHighlight)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTHighlight parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTHighlight)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTHighlight parse(Node node) throws XmlException {
            return (CTHighlight)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTHighlight parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTHighlight)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTHighlight parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTHighlight)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTHighlight parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTHighlight)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

