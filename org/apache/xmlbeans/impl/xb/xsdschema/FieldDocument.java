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
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface FieldDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(FieldDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("field3f9bdoctype");

    public Field getField();

    public void setField(Field var1);

    public Field addNewField();

    public static final class Factory {
        public static FieldDocument newInstance() {
            return (FieldDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static FieldDocument newInstance(XmlOptions options) {
            return (FieldDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static FieldDocument parse(String xmlAsString) throws XmlException {
            return (FieldDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static FieldDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (FieldDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static FieldDocument parse(File file) throws XmlException, IOException {
            return (FieldDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static FieldDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (FieldDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static FieldDocument parse(URL u) throws XmlException, IOException {
            return (FieldDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static FieldDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (FieldDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static FieldDocument parse(InputStream is) throws XmlException, IOException {
            return (FieldDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static FieldDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (FieldDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static FieldDocument parse(Reader r) throws XmlException, IOException {
            return (FieldDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static FieldDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (FieldDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static FieldDocument parse(XMLStreamReader sr) throws XmlException {
            return (FieldDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static FieldDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (FieldDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static FieldDocument parse(Node node) throws XmlException {
            return (FieldDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static FieldDocument parse(Node node, XmlOptions options) throws XmlException {
            return (FieldDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static FieldDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (FieldDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static FieldDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (FieldDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface Field
    extends Annotated {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Field.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("field12f5elemtype");

        public String getXpath();

        public Xpath xgetXpath();

        public void setXpath(String var1);

        public void xsetXpath(Xpath var1);

        public static final class Factory {
            public static Field newInstance() {
                return (Field)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static Field newInstance(XmlOptions options) {
                return (Field)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }

        public static interface Xpath
        extends XmlToken {
            public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Xpath.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("xpath7f90attrtype");

            public static final class Factory {
                public static Xpath newValue(Object obj) {
                    return (Xpath)type.newValue(obj);
                }

                public static Xpath newInstance() {
                    return (Xpath)XmlBeans.getContextTypeLoader().newInstance(type, null);
                }

                public static Xpath newInstance(XmlOptions options) {
                    return (Xpath)XmlBeans.getContextTypeLoader().newInstance(type, options);
                }

                private Factory() {
                }
            }
        }
    }
}

