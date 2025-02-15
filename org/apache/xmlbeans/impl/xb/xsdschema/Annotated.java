/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.OpenAttrs;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface Annotated
extends OpenAttrs {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Annotated.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("annotateda52dtype");

    public AnnotationDocument.Annotation getAnnotation();

    public boolean isSetAnnotation();

    public void setAnnotation(AnnotationDocument.Annotation var1);

    public AnnotationDocument.Annotation addNewAnnotation();

    public void unsetAnnotation();

    public String getId();

    public XmlID xgetId();

    public boolean isSetId();

    public void setId(String var1);

    public void xsetId(XmlID var1);

    public void unsetId();

    public static final class Factory {
        public static Annotated newInstance() {
            return (Annotated)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static Annotated newInstance(XmlOptions options) {
            return (Annotated)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static Annotated parse(String xmlAsString) throws XmlException {
            return (Annotated)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static Annotated parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (Annotated)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static Annotated parse(File file) throws XmlException, IOException {
            return (Annotated)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static Annotated parse(File file, XmlOptions options) throws XmlException, IOException {
            return (Annotated)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static Annotated parse(URL u) throws XmlException, IOException {
            return (Annotated)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static Annotated parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (Annotated)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static Annotated parse(InputStream is) throws XmlException, IOException {
            return (Annotated)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static Annotated parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (Annotated)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static Annotated parse(Reader r) throws XmlException, IOException {
            return (Annotated)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static Annotated parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (Annotated)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static Annotated parse(XMLStreamReader sr) throws XmlException {
            return (Annotated)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static Annotated parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (Annotated)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static Annotated parse(Node node) throws XmlException {
            return (Annotated)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static Annotated parse(Node node, XmlOptions options) throws XmlException {
            return (Annotated)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static Annotated parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (Annotated)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static Annotated parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (Annotated)XmlBeans.getContextTypeLoader().parse(xis, type, options);
        }

        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xis) throws XmlException, XMLStreamException {
            return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xis, type, null);
        }

        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xis, type, options);
        }

        private Factory() {
        }
    }
}

