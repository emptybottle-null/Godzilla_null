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
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.etsi.uri.x01903.v13.QualifyingPropertiesType;
import org.w3c.dom.Node;

public interface QualifyingPropertiesDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(QualifyingPropertiesDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("qualifyingproperties53ccdoctype");

    public QualifyingPropertiesType getQualifyingProperties();

    public void setQualifyingProperties(QualifyingPropertiesType var1);

    public QualifyingPropertiesType addNewQualifyingProperties();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(QualifyingPropertiesDocument.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static QualifyingPropertiesDocument newInstance() {
            return (QualifyingPropertiesDocument)Factory.getTypeLoader().newInstance(type, null);
        }

        public static QualifyingPropertiesDocument newInstance(XmlOptions xmlOptions) {
            return (QualifyingPropertiesDocument)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static QualifyingPropertiesDocument parse(String string) throws XmlException {
            return (QualifyingPropertiesDocument)Factory.getTypeLoader().parse(string, type, null);
        }

        public static QualifyingPropertiesDocument parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (QualifyingPropertiesDocument)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static QualifyingPropertiesDocument parse(File file) throws XmlException, IOException {
            return (QualifyingPropertiesDocument)Factory.getTypeLoader().parse(file, type, null);
        }

        public static QualifyingPropertiesDocument parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (QualifyingPropertiesDocument)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static QualifyingPropertiesDocument parse(URL uRL) throws XmlException, IOException {
            return (QualifyingPropertiesDocument)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static QualifyingPropertiesDocument parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (QualifyingPropertiesDocument)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static QualifyingPropertiesDocument parse(InputStream inputStream) throws XmlException, IOException {
            return (QualifyingPropertiesDocument)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static QualifyingPropertiesDocument parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (QualifyingPropertiesDocument)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static QualifyingPropertiesDocument parse(Reader reader) throws XmlException, IOException {
            return (QualifyingPropertiesDocument)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static QualifyingPropertiesDocument parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (QualifyingPropertiesDocument)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static QualifyingPropertiesDocument parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (QualifyingPropertiesDocument)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static QualifyingPropertiesDocument parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (QualifyingPropertiesDocument)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static QualifyingPropertiesDocument parse(Node node) throws XmlException {
            return (QualifyingPropertiesDocument)Factory.getTypeLoader().parse(node, type, null);
        }

        public static QualifyingPropertiesDocument parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (QualifyingPropertiesDocument)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static QualifyingPropertiesDocument parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (QualifyingPropertiesDocument)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static QualifyingPropertiesDocument parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (QualifyingPropertiesDocument)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

