/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
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
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.etsi.uri.x01903.v13.SignedPropertiesType;
import org.etsi.uri.x01903.v13.UnsignedPropertiesType;
import org.w3c.dom.Node;

public interface QualifyingPropertiesType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(QualifyingPropertiesType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("qualifyingpropertiestype9e16type");

    public SignedPropertiesType getSignedProperties();

    public boolean isSetSignedProperties();

    public void setSignedProperties(SignedPropertiesType var1);

    public SignedPropertiesType addNewSignedProperties();

    public void unsetSignedProperties();

    public UnsignedPropertiesType getUnsignedProperties();

    public boolean isSetUnsignedProperties();

    public void setUnsignedProperties(UnsignedPropertiesType var1);

    public UnsignedPropertiesType addNewUnsignedProperties();

    public void unsetUnsignedProperties();

    public String getTarget();

    public XmlAnyURI xgetTarget();

    public void setTarget(String var1);

    public void xsetTarget(XmlAnyURI var1);

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
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(QualifyingPropertiesType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static QualifyingPropertiesType newInstance() {
            return (QualifyingPropertiesType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static QualifyingPropertiesType newInstance(XmlOptions xmlOptions) {
            return (QualifyingPropertiesType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static QualifyingPropertiesType parse(String string) throws XmlException {
            return (QualifyingPropertiesType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static QualifyingPropertiesType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (QualifyingPropertiesType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static QualifyingPropertiesType parse(File file) throws XmlException, IOException {
            return (QualifyingPropertiesType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static QualifyingPropertiesType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (QualifyingPropertiesType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static QualifyingPropertiesType parse(URL uRL) throws XmlException, IOException {
            return (QualifyingPropertiesType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static QualifyingPropertiesType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (QualifyingPropertiesType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static QualifyingPropertiesType parse(InputStream inputStream) throws XmlException, IOException {
            return (QualifyingPropertiesType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static QualifyingPropertiesType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (QualifyingPropertiesType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static QualifyingPropertiesType parse(Reader reader) throws XmlException, IOException {
            return (QualifyingPropertiesType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static QualifyingPropertiesType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (QualifyingPropertiesType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static QualifyingPropertiesType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (QualifyingPropertiesType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static QualifyingPropertiesType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (QualifyingPropertiesType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static QualifyingPropertiesType parse(Node node) throws XmlException {
            return (QualifyingPropertiesType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static QualifyingPropertiesType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (QualifyingPropertiesType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static QualifyingPropertiesType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (QualifyingPropertiesType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static QualifyingPropertiesType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (QualifyingPropertiesType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

