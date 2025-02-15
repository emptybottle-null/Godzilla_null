/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.x2006.encryption;

import com.microsoft.schemas.office.x2006.encryption.CTKeyEncryptor;
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
import org.w3c.dom.Node;

public interface CTKeyEncryptors
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTKeyEncryptors.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("ctkeyencryptorsa09ctype");

    public List<CTKeyEncryptor> getKeyEncryptorList();

    @Deprecated
    public CTKeyEncryptor[] getKeyEncryptorArray();

    public CTKeyEncryptor getKeyEncryptorArray(int var1);

    public int sizeOfKeyEncryptorArray();

    public void setKeyEncryptorArray(CTKeyEncryptor[] var1);

    public void setKeyEncryptorArray(int var1, CTKeyEncryptor var2);

    public CTKeyEncryptor insertNewKeyEncryptor(int var1);

    public CTKeyEncryptor addNewKeyEncryptor();

    public void removeKeyEncryptor(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTKeyEncryptors.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTKeyEncryptors newInstance() {
            return (CTKeyEncryptors)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTKeyEncryptors newInstance(XmlOptions xmlOptions) {
            return (CTKeyEncryptors)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTKeyEncryptors parse(String string) throws XmlException {
            return (CTKeyEncryptors)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTKeyEncryptors parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTKeyEncryptors)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTKeyEncryptors parse(File file) throws XmlException, IOException {
            return (CTKeyEncryptors)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTKeyEncryptors parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTKeyEncryptors)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTKeyEncryptors parse(URL uRL) throws XmlException, IOException {
            return (CTKeyEncryptors)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTKeyEncryptors parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTKeyEncryptors)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTKeyEncryptors parse(InputStream inputStream) throws XmlException, IOException {
            return (CTKeyEncryptors)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTKeyEncryptors parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTKeyEncryptors)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTKeyEncryptors parse(Reader reader) throws XmlException, IOException {
            return (CTKeyEncryptors)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTKeyEncryptors parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTKeyEncryptors)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTKeyEncryptors parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTKeyEncryptors)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTKeyEncryptors parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTKeyEncryptors)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTKeyEncryptors parse(Node node) throws XmlException {
            return (CTKeyEncryptors)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTKeyEncryptors parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTKeyEncryptors)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTKeyEncryptors parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTKeyEncryptors)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTKeyEncryptors parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTKeyEncryptors)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

