/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.xpackage.x2006.digitalSignature;

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
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface CTRelationshipReference
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTRelationshipReference.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("ctrelationshipreferencee68ftype");

    public String getSourceId();

    public XmlString xgetSourceId();

    public void setSourceId(String var1);

    public void xsetSourceId(XmlString var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTRelationshipReference.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTRelationshipReference newInstance() {
            return (CTRelationshipReference)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTRelationshipReference newInstance(XmlOptions xmlOptions) {
            return (CTRelationshipReference)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTRelationshipReference parse(String string) throws XmlException {
            return (CTRelationshipReference)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTRelationshipReference parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTRelationshipReference)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTRelationshipReference parse(File file) throws XmlException, IOException {
            return (CTRelationshipReference)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTRelationshipReference parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRelationshipReference)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTRelationshipReference parse(URL uRL) throws XmlException, IOException {
            return (CTRelationshipReference)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTRelationshipReference parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRelationshipReference)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTRelationshipReference parse(InputStream inputStream) throws XmlException, IOException {
            return (CTRelationshipReference)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTRelationshipReference parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRelationshipReference)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTRelationshipReference parse(Reader reader) throws XmlException, IOException {
            return (CTRelationshipReference)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTRelationshipReference parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTRelationshipReference)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTRelationshipReference parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTRelationshipReference)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTRelationshipReference parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTRelationshipReference)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTRelationshipReference parse(Node node) throws XmlException {
            return (CTRelationshipReference)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTRelationshipReference parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTRelationshipReference)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTRelationshipReference parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTRelationshipReference)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTRelationshipReference parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTRelationshipReference)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

