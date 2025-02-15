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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STRef;
import org.w3c.dom.Node;

public interface CTLocation
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTLocation.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctlocationc23etype");

    public String getRef();

    public STRef xgetRef();

    public void setRef(String var1);

    public void xsetRef(STRef var1);

    public long getFirstHeaderRow();

    public XmlUnsignedInt xgetFirstHeaderRow();

    public void setFirstHeaderRow(long var1);

    public void xsetFirstHeaderRow(XmlUnsignedInt var1);

    public long getFirstDataRow();

    public XmlUnsignedInt xgetFirstDataRow();

    public void setFirstDataRow(long var1);

    public void xsetFirstDataRow(XmlUnsignedInt var1);

    public long getFirstDataCol();

    public XmlUnsignedInt xgetFirstDataCol();

    public void setFirstDataCol(long var1);

    public void xsetFirstDataCol(XmlUnsignedInt var1);

    public long getRowPageCount();

    public XmlUnsignedInt xgetRowPageCount();

    public boolean isSetRowPageCount();

    public void setRowPageCount(long var1);

    public void xsetRowPageCount(XmlUnsignedInt var1);

    public void unsetRowPageCount();

    public long getColPageCount();

    public XmlUnsignedInt xgetColPageCount();

    public boolean isSetColPageCount();

    public void setColPageCount(long var1);

    public void xsetColPageCount(XmlUnsignedInt var1);

    public void unsetColPageCount();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTLocation.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTLocation newInstance() {
            return (CTLocation)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTLocation newInstance(XmlOptions xmlOptions) {
            return (CTLocation)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTLocation parse(String string) throws XmlException {
            return (CTLocation)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTLocation parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTLocation)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTLocation parse(File file) throws XmlException, IOException {
            return (CTLocation)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTLocation parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLocation)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTLocation parse(URL uRL) throws XmlException, IOException {
            return (CTLocation)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTLocation parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLocation)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTLocation parse(InputStream inputStream) throws XmlException, IOException {
            return (CTLocation)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTLocation parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLocation)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTLocation parse(Reader reader) throws XmlException, IOException {
            return (CTLocation)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTLocation parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTLocation)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTLocation parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTLocation)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTLocation parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTLocation)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTLocation parse(Node node) throws XmlException {
            return (CTLocation)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTLocation parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTLocation)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTLocation parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTLocation)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTLocation parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTLocation)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

