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

public interface SelectorDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(SelectorDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("selectorcb44doctype");

    public Selector getSelector();

    public void setSelector(Selector var1);

    public Selector addNewSelector();

    public static final class Factory {
        public static SelectorDocument newInstance() {
            return (SelectorDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static SelectorDocument newInstance(XmlOptions options) {
            return (SelectorDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static SelectorDocument parse(String xmlAsString) throws XmlException {
            return (SelectorDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static SelectorDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (SelectorDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static SelectorDocument parse(File file) throws XmlException, IOException {
            return (SelectorDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static SelectorDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (SelectorDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static SelectorDocument parse(URL u) throws XmlException, IOException {
            return (SelectorDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static SelectorDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (SelectorDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static SelectorDocument parse(InputStream is) throws XmlException, IOException {
            return (SelectorDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static SelectorDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (SelectorDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static SelectorDocument parse(Reader r) throws XmlException, IOException {
            return (SelectorDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static SelectorDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (SelectorDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static SelectorDocument parse(XMLStreamReader sr) throws XmlException {
            return (SelectorDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static SelectorDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (SelectorDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static SelectorDocument parse(Node node) throws XmlException {
            return (SelectorDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static SelectorDocument parse(Node node, XmlOptions options) throws XmlException {
            return (SelectorDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static SelectorDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (SelectorDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static SelectorDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (SelectorDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface Selector
    extends Annotated {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Selector.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("selector233felemtype");

        public String getXpath();

        public Xpath xgetXpath();

        public void setXpath(String var1);

        public void xsetXpath(Xpath var1);

        public static final class Factory {
            public static Selector newInstance() {
                return (Selector)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static Selector newInstance(XmlOptions options) {
                return (Selector)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }

        public static interface Xpath
        extends XmlToken {
            public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Xpath.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("xpath6f9aattrtype");

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

