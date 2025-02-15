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
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDxfId;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STTableStyleType;
import org.w3c.dom.Node;

public interface CTTableStyleElement
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTableStyleElement.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttablestyleelementa658type");

    public STTableStyleType.Enum getType();

    public STTableStyleType xgetType();

    public void setType(STTableStyleType.Enum var1);

    public void xsetType(STTableStyleType var1);

    public long getSize();

    public XmlUnsignedInt xgetSize();

    public boolean isSetSize();

    public void setSize(long var1);

    public void xsetSize(XmlUnsignedInt var1);

    public void unsetSize();

    public long getDxfId();

    public STDxfId xgetDxfId();

    public boolean isSetDxfId();

    public void setDxfId(long var1);

    public void xsetDxfId(STDxfId var1);

    public void unsetDxfId();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTableStyleElement.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTableStyleElement newInstance() {
            return (CTTableStyleElement)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTableStyleElement newInstance(XmlOptions xmlOptions) {
            return (CTTableStyleElement)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTableStyleElement parse(String string) throws XmlException {
            return (CTTableStyleElement)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTableStyleElement parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyleElement)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTableStyleElement parse(File file) throws XmlException, IOException {
            return (CTTableStyleElement)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTableStyleElement parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyleElement)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTableStyleElement parse(URL uRL) throws XmlException, IOException {
            return (CTTableStyleElement)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTableStyleElement parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyleElement)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTableStyleElement parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTableStyleElement)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTableStyleElement parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyleElement)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTableStyleElement parse(Reader reader) throws XmlException, IOException {
            return (CTTableStyleElement)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTableStyleElement parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTableStyleElement)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTableStyleElement parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTableStyleElement)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTableStyleElement parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyleElement)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTableStyleElement parse(Node node) throws XmlException {
            return (CTTableStyleElement)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTableStyleElement parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTableStyleElement)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTableStyleElement parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTableStyleElement)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTableStyleElement parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTableStyleElement)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

