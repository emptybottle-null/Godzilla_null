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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface CTBreak
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTBreak.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctbreak815etype");

    public long getId();

    public XmlUnsignedInt xgetId();

    public boolean isSetId();

    public void setId(long var1);

    public void xsetId(XmlUnsignedInt var1);

    public void unsetId();

    public long getMin();

    public XmlUnsignedInt xgetMin();

    public boolean isSetMin();

    public void setMin(long var1);

    public void xsetMin(XmlUnsignedInt var1);

    public void unsetMin();

    public long getMax();

    public XmlUnsignedInt xgetMax();

    public boolean isSetMax();

    public void setMax(long var1);

    public void xsetMax(XmlUnsignedInt var1);

    public void unsetMax();

    public boolean getMan();

    public XmlBoolean xgetMan();

    public boolean isSetMan();

    public void setMan(boolean var1);

    public void xsetMan(XmlBoolean var1);

    public void unsetMan();

    public boolean getPt();

    public XmlBoolean xgetPt();

    public boolean isSetPt();

    public void setPt(boolean var1);

    public void xsetPt(XmlBoolean var1);

    public void unsetPt();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTBreak.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTBreak newInstance() {
            return (CTBreak)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTBreak newInstance(XmlOptions xmlOptions) {
            return (CTBreak)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTBreak parse(String string) throws XmlException {
            return (CTBreak)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTBreak parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTBreak)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTBreak parse(File file) throws XmlException, IOException {
            return (CTBreak)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTBreak parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBreak)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTBreak parse(URL uRL) throws XmlException, IOException {
            return (CTBreak)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTBreak parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBreak)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTBreak parse(InputStream inputStream) throws XmlException, IOException {
            return (CTBreak)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTBreak parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBreak)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTBreak parse(Reader reader) throws XmlException, IOException {
            return (CTBreak)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTBreak parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTBreak)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTBreak parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTBreak)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTBreak parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTBreak)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTBreak parse(Node node) throws XmlException {
            return (CTBreak)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTBreak parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTBreak)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTBreak parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTBreak)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTBreak parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTBreak)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

