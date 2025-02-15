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
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXf;
import org.w3c.dom.Node;

public interface CTCellStyleXfs
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTCellStyleXfs.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctcellstylexfsa81ftype");

    public List<CTXf> getXfList();

    @Deprecated
    public CTXf[] getXfArray();

    public CTXf getXfArray(int var1);

    public int sizeOfXfArray();

    public void setXfArray(CTXf[] var1);

    public void setXfArray(int var1, CTXf var2);

    public CTXf insertNewXf(int var1);

    public CTXf addNewXf();

    public void removeXf(int var1);

    public long getCount();

    public XmlUnsignedInt xgetCount();

    public boolean isSetCount();

    public void setCount(long var1);

    public void xsetCount(XmlUnsignedInt var1);

    public void unsetCount();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTCellStyleXfs.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTCellStyleXfs newInstance() {
            return (CTCellStyleXfs)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTCellStyleXfs newInstance(XmlOptions xmlOptions) {
            return (CTCellStyleXfs)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTCellStyleXfs parse(String string) throws XmlException {
            return (CTCellStyleXfs)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTCellStyleXfs parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTCellStyleXfs)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTCellStyleXfs parse(File file) throws XmlException, IOException {
            return (CTCellStyleXfs)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTCellStyleXfs parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCellStyleXfs)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTCellStyleXfs parse(URL uRL) throws XmlException, IOException {
            return (CTCellStyleXfs)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTCellStyleXfs parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCellStyleXfs)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTCellStyleXfs parse(InputStream inputStream) throws XmlException, IOException {
            return (CTCellStyleXfs)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTCellStyleXfs parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCellStyleXfs)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTCellStyleXfs parse(Reader reader) throws XmlException, IOException {
            return (CTCellStyleXfs)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTCellStyleXfs parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCellStyleXfs)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTCellStyleXfs parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTCellStyleXfs)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTCellStyleXfs parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTCellStyleXfs)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTCellStyleXfs parse(Node node) throws XmlException {
            return (CTCellStyleXfs)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTCellStyleXfs parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTCellStyleXfs)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTCellStyleXfs parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTCellStyleXfs)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTCellStyleXfs parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTCellStyleXfs)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

