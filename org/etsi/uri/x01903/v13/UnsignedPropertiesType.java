/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType
 */
package org.etsi.uri.x01903.v13;

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
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType;
import org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType;
import org.w3c.dom.Node;

public interface UnsignedPropertiesType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(UnsignedPropertiesType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("unsignedpropertiestype49d6type");

    public UnsignedSignaturePropertiesType getUnsignedSignatureProperties();

    public boolean isSetUnsignedSignatureProperties();

    public void setUnsignedSignatureProperties(UnsignedSignaturePropertiesType var1);

    public UnsignedSignaturePropertiesType addNewUnsignedSignatureProperties();

    public void unsetUnsignedSignatureProperties();

    public UnsignedDataObjectPropertiesType getUnsignedDataObjectProperties();

    public boolean isSetUnsignedDataObjectProperties();

    public void setUnsignedDataObjectProperties(UnsignedDataObjectPropertiesType var1);

    public UnsignedDataObjectPropertiesType addNewUnsignedDataObjectProperties();

    public void unsetUnsignedDataObjectProperties();

    public String getId();

    public XmlID xgetId();

    public boolean isSetId();

    public void setId(String var1);

    public void xsetId(XmlID var1);

    public void unsetId();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(UnsignedPropertiesType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static UnsignedPropertiesType newInstance() {
            return (UnsignedPropertiesType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static UnsignedPropertiesType newInstance(XmlOptions xmlOptions) {
            return (UnsignedPropertiesType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static UnsignedPropertiesType parse(String string) throws XmlException {
            return (UnsignedPropertiesType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static UnsignedPropertiesType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (UnsignedPropertiesType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static UnsignedPropertiesType parse(File file) throws XmlException, IOException {
            return (UnsignedPropertiesType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static UnsignedPropertiesType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (UnsignedPropertiesType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static UnsignedPropertiesType parse(URL uRL) throws XmlException, IOException {
            return (UnsignedPropertiesType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static UnsignedPropertiesType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (UnsignedPropertiesType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static UnsignedPropertiesType parse(InputStream inputStream) throws XmlException, IOException {
            return (UnsignedPropertiesType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static UnsignedPropertiesType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (UnsignedPropertiesType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static UnsignedPropertiesType parse(Reader reader) throws XmlException, IOException {
            return (UnsignedPropertiesType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static UnsignedPropertiesType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (UnsignedPropertiesType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static UnsignedPropertiesType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (UnsignedPropertiesType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static UnsignedPropertiesType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (UnsignedPropertiesType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static UnsignedPropertiesType parse(Node node) throws XmlException {
            return (UnsignedPropertiesType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static UnsignedPropertiesType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (UnsignedPropertiesType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static UnsignedPropertiesType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (UnsignedPropertiesType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static UnsignedPropertiesType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (UnsignedPropertiesType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

