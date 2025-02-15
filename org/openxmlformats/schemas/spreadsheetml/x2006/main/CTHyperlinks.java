/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;

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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHyperlink;
import org.w3c.dom.Node;

public interface CTHyperlinks
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTHyperlinks.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cthyperlinks6416type");

    public List<CTHyperlink> getHyperlinkList();

    @Deprecated
    public CTHyperlink[] getHyperlinkArray();

    public CTHyperlink getHyperlinkArray(int var1);

    public int sizeOfHyperlinkArray();

    public void setHyperlinkArray(CTHyperlink[] var1);

    public void setHyperlinkArray(int var1, CTHyperlink var2);

    public CTHyperlink insertNewHyperlink(int var1);

    public CTHyperlink addNewHyperlink();

    public void removeHyperlink(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTHyperlinks.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTHyperlinks newInstance() {
            return (CTHyperlinks)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTHyperlinks newInstance(XmlOptions xmlOptions) {
            return (CTHyperlinks)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTHyperlinks parse(String string) throws XmlException {
            return (CTHyperlinks)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTHyperlinks parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTHyperlinks)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTHyperlinks parse(File file) throws XmlException, IOException {
            return (CTHyperlinks)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTHyperlinks parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHyperlinks)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTHyperlinks parse(URL uRL) throws XmlException, IOException {
            return (CTHyperlinks)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTHyperlinks parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHyperlinks)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTHyperlinks parse(InputStream inputStream) throws XmlException, IOException {
            return (CTHyperlinks)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTHyperlinks parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHyperlinks)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTHyperlinks parse(Reader reader) throws XmlException, IOException {
            return (CTHyperlinks)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTHyperlinks parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTHyperlinks)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTHyperlinks parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTHyperlinks)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTHyperlinks parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTHyperlinks)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTHyperlinks parse(Node node) throws XmlException {
            return (CTHyperlinks)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTHyperlinks parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTHyperlinks)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTHyperlinks parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTHyperlinks)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTHyperlinks parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTHyperlinks)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

