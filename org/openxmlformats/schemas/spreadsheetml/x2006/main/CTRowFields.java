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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTField;
import org.w3c.dom.Node;

public interface CTRowFields
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTRowFields.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctrowfields0312type");

    public List<CTField> getFieldList();

    @Deprecated
    public CTField[] getFieldArray();

    public CTField getFieldArray(int var1);

    public int sizeOfFieldArray();

    public void setFieldArray(CTField[] var1);

    public void setFieldArray(int var1, CTField var2);

    public CTField insertNewField(int var1);

    public CTField addNewField();

    public void removeField(int var1);

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTRowFields.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTRowFields newInstance() {
            return (CTRowFields)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTRowFields newInstance(XmlOptions xmlOptions) {
            return (CTRowFields)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTRowFields parse(String string) throws XmlException {
            return (CTRowFields)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTRowFields parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTRowFields)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTRowFields parse(File file) throws XmlException, IOException {
            return (CTRowFields)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTRowFields parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRowFields)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTRowFields parse(URL uRL) throws XmlException, IOException {
            return (CTRowFields)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTRowFields parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRowFields)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTRowFields parse(InputStream inputStream) throws XmlException, IOException {
            return (CTRowFields)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTRowFields parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRowFields)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTRowFields parse(Reader reader) throws XmlException, IOException {
            return (CTRowFields)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTRowFields parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRowFields)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTRowFields parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTRowFields)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTRowFields parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTRowFields)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTRowFields parse(Node node) throws XmlException {
            return (CTRowFields)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTRowFields parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTRowFields)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTRowFields parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTRowFields)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTRowFields parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTRowFields)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

