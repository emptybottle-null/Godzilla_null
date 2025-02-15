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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedName;
import org.w3c.dom.Node;

public interface CTExternalDefinedNames
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTExternalDefinedNames.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctexternaldefinednamesccf3type");

    public List<CTExternalDefinedName> getDefinedNameList();

    @Deprecated
    public CTExternalDefinedName[] getDefinedNameArray();

    public CTExternalDefinedName getDefinedNameArray(int var1);

    public int sizeOfDefinedNameArray();

    public void setDefinedNameArray(CTExternalDefinedName[] var1);

    public void setDefinedNameArray(int var1, CTExternalDefinedName var2);

    public CTExternalDefinedName insertNewDefinedName(int var1);

    public CTExternalDefinedName addNewDefinedName();

    public void removeDefinedName(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTExternalDefinedNames.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTExternalDefinedNames newInstance() {
            return (CTExternalDefinedNames)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTExternalDefinedNames newInstance(XmlOptions xmlOptions) {
            return (CTExternalDefinedNames)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTExternalDefinedNames parse(String string) throws XmlException {
            return (CTExternalDefinedNames)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTExternalDefinedNames parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTExternalDefinedNames)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTExternalDefinedNames parse(File file) throws XmlException, IOException {
            return (CTExternalDefinedNames)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTExternalDefinedNames parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTExternalDefinedNames)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTExternalDefinedNames parse(URL uRL) throws XmlException, IOException {
            return (CTExternalDefinedNames)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTExternalDefinedNames parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTExternalDefinedNames)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTExternalDefinedNames parse(InputStream inputStream) throws XmlException, IOException {
            return (CTExternalDefinedNames)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTExternalDefinedNames parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTExternalDefinedNames)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTExternalDefinedNames parse(Reader reader) throws XmlException, IOException {
            return (CTExternalDefinedNames)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTExternalDefinedNames parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTExternalDefinedNames)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTExternalDefinedNames parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTExternalDefinedNames)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTExternalDefinedNames parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTExternalDefinedNames)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTExternalDefinedNames parse(Node node) throws XmlException {
            return (CTExternalDefinedNames)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTExternalDefinedNames parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTExternalDefinedNames)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTExternalDefinedNames parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTExternalDefinedNames)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTExternalDefinedNames parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTExternalDefinedNames)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

