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
import org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface PatternDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(PatternDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("pattern9585doctype");

    public Pattern getPattern();

    public void setPattern(Pattern var1);

    public Pattern addNewPattern();

    public static final class Factory {
        public static PatternDocument newInstance() {
            return (PatternDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static PatternDocument newInstance(XmlOptions options) {
            return (PatternDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static PatternDocument parse(String xmlAsString) throws XmlException {
            return (PatternDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static PatternDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (PatternDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static PatternDocument parse(File file) throws XmlException, IOException {
            return (PatternDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static PatternDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (PatternDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static PatternDocument parse(URL u) throws XmlException, IOException {
            return (PatternDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static PatternDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (PatternDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static PatternDocument parse(InputStream is) throws XmlException, IOException {
            return (PatternDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static PatternDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (PatternDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static PatternDocument parse(Reader r) throws XmlException, IOException {
            return (PatternDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static PatternDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (PatternDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static PatternDocument parse(XMLStreamReader sr) throws XmlException {
            return (PatternDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static PatternDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (PatternDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static PatternDocument parse(Node node) throws XmlException {
            return (PatternDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static PatternDocument parse(Node node, XmlOptions options) throws XmlException {
            return (PatternDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static PatternDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (PatternDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static PatternDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (PatternDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface Pattern
    extends NoFixedFacet {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Pattern.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("pattern6809elemtype");

        public static final class Factory {
            public static Pattern newInstance() {
                return (Pattern)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static Pattern newInstance(XmlOptions options) {
                return (Pattern)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }
}

