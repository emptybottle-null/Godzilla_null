/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.x2006.encryption;

import com.microsoft.schemas.office.x2006.encryption.CTDataIntegrity;
import com.microsoft.schemas.office.x2006.encryption.CTKeyData;
import com.microsoft.schemas.office.x2006.encryption.CTKeyEncryptors;
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
import org.w3c.dom.Node;

public interface CTEncryption
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTEncryption.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("ctencryption365ftype");

    public CTKeyData getKeyData();

    public void setKeyData(CTKeyData var1);

    public CTKeyData addNewKeyData();

    public CTDataIntegrity getDataIntegrity();

    public void setDataIntegrity(CTDataIntegrity var1);

    public CTDataIntegrity addNewDataIntegrity();

    public CTKeyEncryptors getKeyEncryptors();

    public void setKeyEncryptors(CTKeyEncryptors var1);

    public CTKeyEncryptors addNewKeyEncryptors();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTEncryption.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTEncryption newInstance() {
            return (CTEncryption)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTEncryption newInstance(XmlOptions xmlOptions) {
            return (CTEncryption)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTEncryption parse(String string) throws XmlException {
            return (CTEncryption)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTEncryption parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTEncryption)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTEncryption parse(File file) throws XmlException, IOException {
            return (CTEncryption)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTEncryption parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEncryption)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTEncryption parse(URL uRL) throws XmlException, IOException {
            return (CTEncryption)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTEncryption parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEncryption)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTEncryption parse(InputStream inputStream) throws XmlException, IOException {
            return (CTEncryption)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTEncryption parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEncryption)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTEncryption parse(Reader reader) throws XmlException, IOException {
            return (CTEncryption)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTEncryption parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTEncryption)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTEncryption parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTEncryption)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTEncryption parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTEncryption)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTEncryption parse(Node node) throws XmlException {
            return (CTEncryption)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTEncryption parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTEncryption)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTEncryption parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTEncryption)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTEncryption parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTEncryption)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

