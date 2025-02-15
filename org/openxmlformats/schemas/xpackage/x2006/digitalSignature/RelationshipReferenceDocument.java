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
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTRelationshipReference;
import org.w3c.dom.Node;

public interface RelationshipReferenceDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(RelationshipReferenceDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("relationshipreference8903doctype");

    public CTRelationshipReference getRelationshipReference();

    public void setRelationshipReference(CTRelationshipReference var1);

    public CTRelationshipReference addNewRelationshipReference();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(RelationshipReferenceDocument.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static RelationshipReferenceDocument newInstance() {
            return (RelationshipReferenceDocument)Factory.getTypeLoader().newInstance(type, null);
        }

        public static RelationshipReferenceDocument newInstance(XmlOptions xmlOptions) {
            return (RelationshipReferenceDocument)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static RelationshipReferenceDocument parse(String string) throws XmlException {
            return (RelationshipReferenceDocument)Factory.getTypeLoader().parse(string, type, null);
        }

        public static RelationshipReferenceDocument parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (RelationshipReferenceDocument)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static RelationshipReferenceDocument parse(File file) throws XmlException, IOException {
            return (RelationshipReferenceDocument)Factory.getTypeLoader().parse(file, type, null);
        }

        public static RelationshipReferenceDocument parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (RelationshipReferenceDocument)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static RelationshipReferenceDocument parse(URL uRL) throws XmlException, IOException {
            return (RelationshipReferenceDocument)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static RelationshipReferenceDocument parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (RelationshipReferenceDocument)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static RelationshipReferenceDocument parse(InputStream inputStream) throws XmlException, IOException {
            return (RelationshipReferenceDocument)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static RelationshipReferenceDocument parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (RelationshipReferenceDocument)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static RelationshipReferenceDocument parse(Reader reader) throws XmlException, IOException {
            return (RelationshipReferenceDocument)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static RelationshipReferenceDocument parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (RelationshipReferenceDocument)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static RelationshipReferenceDocument parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (RelationshipReferenceDocument)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static RelationshipReferenceDocument parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (RelationshipReferenceDocument)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static RelationshipReferenceDocument parse(Node node) throws XmlException {
            return (RelationshipReferenceDocument)Factory.getTypeLoader().parse(node, type, null);
        }

        public static RelationshipReferenceDocument parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (RelationshipReferenceDocument)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static RelationshipReferenceDocument parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (RelationshipReferenceDocument)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static RelationshipReferenceDocument parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (RelationshipReferenceDocument)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

