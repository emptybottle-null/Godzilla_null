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
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.OpenAttrs;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface AnnotationDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(AnnotationDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("annotationb034doctype");

    public Annotation getAnnotation();

    public void setAnnotation(Annotation var1);

    public Annotation addNewAnnotation();

    public static final class Factory {
        public static AnnotationDocument newInstance() {
            return (AnnotationDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static AnnotationDocument newInstance(XmlOptions options) {
            return (AnnotationDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static AnnotationDocument parse(String xmlAsString) throws XmlException {
            return (AnnotationDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static AnnotationDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (AnnotationDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static AnnotationDocument parse(File file) throws XmlException, IOException {
            return (AnnotationDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static AnnotationDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (AnnotationDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static AnnotationDocument parse(URL u) throws XmlException, IOException {
            return (AnnotationDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static AnnotationDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (AnnotationDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static AnnotationDocument parse(InputStream is) throws XmlException, IOException {
            return (AnnotationDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static AnnotationDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (AnnotationDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static AnnotationDocument parse(Reader r) throws XmlException, IOException {
            return (AnnotationDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static AnnotationDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (AnnotationDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static AnnotationDocument parse(XMLStreamReader sr) throws XmlException {
            return (AnnotationDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static AnnotationDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (AnnotationDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static AnnotationDocument parse(Node node) throws XmlException {
            return (AnnotationDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static AnnotationDocument parse(Node node, XmlOptions options) throws XmlException {
            return (AnnotationDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static AnnotationDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (AnnotationDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static AnnotationDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (AnnotationDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface Annotation
    extends OpenAttrs {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Annotation.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("annotation5abfelemtype");

        public AppinfoDocument.Appinfo[] getAppinfoArray();

        public AppinfoDocument.Appinfo getAppinfoArray(int var1);

        public int sizeOfAppinfoArray();

        public void setAppinfoArray(AppinfoDocument.Appinfo[] var1);

        public void setAppinfoArray(int var1, AppinfoDocument.Appinfo var2);

        public AppinfoDocument.Appinfo insertNewAppinfo(int var1);

        public AppinfoDocument.Appinfo addNewAppinfo();

        public void removeAppinfo(int var1);

        public DocumentationDocument.Documentation[] getDocumentationArray();

        public DocumentationDocument.Documentation getDocumentationArray(int var1);

        public int sizeOfDocumentationArray();

        public void setDocumentationArray(DocumentationDocument.Documentation[] var1);

        public void setDocumentationArray(int var1, DocumentationDocument.Documentation var2);

        public DocumentationDocument.Documentation insertNewDocumentation(int var1);

        public DocumentationDocument.Documentation addNewDocumentation();

        public void removeDocumentation(int var1);

        public String getId();

        public XmlID xgetId();

        public boolean isSetId();

        public void setId(String var1);

        public void xsetId(XmlID var1);

        public void unsetId();

        public static final class Factory {
            public static Annotation newInstance() {
                return (Annotation)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static Annotation newInstance(XmlOptions options) {
                return (Annotation)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }
}

