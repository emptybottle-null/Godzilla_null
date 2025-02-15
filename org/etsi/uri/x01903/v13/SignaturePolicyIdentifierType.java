/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.etsi.uri.x01903.v13.SignaturePolicyIdType
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
import org.etsi.uri.x01903.v13.SignaturePolicyIdType;
import org.w3c.dom.Node;

public interface SignaturePolicyIdentifierType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(SignaturePolicyIdentifierType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("signaturepolicyidentifiertype80aftype");

    public SignaturePolicyIdType getSignaturePolicyId();

    public boolean isSetSignaturePolicyId();

    public void setSignaturePolicyId(SignaturePolicyIdType var1);

    public SignaturePolicyIdType addNewSignaturePolicyId();

    public void unsetSignaturePolicyId();

    public XmlObject getSignaturePolicyImplied();

    public boolean isSetSignaturePolicyImplied();

    public void setSignaturePolicyImplied(XmlObject var1);

    public XmlObject addNewSignaturePolicyImplied();

    public void unsetSignaturePolicyImplied();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(SignaturePolicyIdentifierType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static SignaturePolicyIdentifierType newInstance() {
            return (SignaturePolicyIdentifierType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static SignaturePolicyIdentifierType newInstance(XmlOptions xmlOptions) {
            return (SignaturePolicyIdentifierType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static SignaturePolicyIdentifierType parse(String string) throws XmlException {
            return (SignaturePolicyIdentifierType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static SignaturePolicyIdentifierType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (SignaturePolicyIdentifierType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static SignaturePolicyIdentifierType parse(File file) throws XmlException, IOException {
            return (SignaturePolicyIdentifierType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static SignaturePolicyIdentifierType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignaturePolicyIdentifierType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static SignaturePolicyIdentifierType parse(URL uRL) throws XmlException, IOException {
            return (SignaturePolicyIdentifierType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static SignaturePolicyIdentifierType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignaturePolicyIdentifierType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static SignaturePolicyIdentifierType parse(InputStream inputStream) throws XmlException, IOException {
            return (SignaturePolicyIdentifierType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static SignaturePolicyIdentifierType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignaturePolicyIdentifierType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static SignaturePolicyIdentifierType parse(Reader reader) throws XmlException, IOException {
            return (SignaturePolicyIdentifierType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static SignaturePolicyIdentifierType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignaturePolicyIdentifierType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static SignaturePolicyIdentifierType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (SignaturePolicyIdentifierType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static SignaturePolicyIdentifierType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (SignaturePolicyIdentifierType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static SignaturePolicyIdentifierType parse(Node node) throws XmlException {
            return (SignaturePolicyIdentifierType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static SignaturePolicyIdentifierType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (SignaturePolicyIdentifierType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static SignaturePolicyIdentifierType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (SignaturePolicyIdentifierType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static SignaturePolicyIdentifierType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (SignaturePolicyIdentifierType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

