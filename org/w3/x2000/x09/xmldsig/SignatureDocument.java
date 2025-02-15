/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.w3.x2000.x09.xmldsig;

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
import org.w3.x2000.x09.xmldsig.SignatureType;
import org.w3c.dom.Node;

public interface SignatureDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(SignatureDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("signature5269doctype");

    public SignatureType getSignature();

    public void setSignature(SignatureType var1);

    public SignatureType addNewSignature();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(SignatureDocument.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static SignatureDocument newInstance() {
            return (SignatureDocument)Factory.getTypeLoader().newInstance(type, null);
        }

        public static SignatureDocument newInstance(XmlOptions xmlOptions) {
            return (SignatureDocument)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static SignatureDocument parse(String string) throws XmlException {
            return (SignatureDocument)Factory.getTypeLoader().parse(string, type, null);
        }

        public static SignatureDocument parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (SignatureDocument)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static SignatureDocument parse(File file) throws XmlException, IOException {
            return (SignatureDocument)Factory.getTypeLoader().parse(file, type, null);
        }

        public static SignatureDocument parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignatureDocument)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static SignatureDocument parse(URL uRL) throws XmlException, IOException {
            return (SignatureDocument)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static SignatureDocument parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignatureDocument)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static SignatureDocument parse(InputStream inputStream) throws XmlException, IOException {
            return (SignatureDocument)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static SignatureDocument parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignatureDocument)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static SignatureDocument parse(Reader reader) throws XmlException, IOException {
            return (SignatureDocument)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static SignatureDocument parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignatureDocument)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static SignatureDocument parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (SignatureDocument)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static SignatureDocument parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (SignatureDocument)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static SignatureDocument parse(Node node) throws XmlException {
            return (SignatureDocument)Factory.getTypeLoader().parse(node, type, null);
        }

        public static SignatureDocument parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (SignatureDocument)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static SignatureDocument parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (SignatureDocument)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static SignatureDocument parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (SignatureDocument)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

