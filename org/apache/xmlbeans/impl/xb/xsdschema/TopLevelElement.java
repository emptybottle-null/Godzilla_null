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
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.xb.xsdschema.Element;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface TopLevelElement
extends Element {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(TopLevelElement.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("toplevelelement98d8type");

    @Override
    public String getName();

    @Override
    public XmlNCName xgetName();

    @Override
    public boolean isSetName();

    @Override
    public void setName(String var1);

    @Override
    public void xsetName(XmlNCName var1);

    @Override
    public void unsetName();

    public static final class Factory {
        public static TopLevelElement newInstance() {
            return (TopLevelElement)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static TopLevelElement newInstance(XmlOptions options) {
            return (TopLevelElement)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static TopLevelElement parse(String xmlAsString) throws XmlException {
            return (TopLevelElement)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static TopLevelElement parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (TopLevelElement)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static TopLevelElement parse(File file) throws XmlException, IOException {
            return (TopLevelElement)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static TopLevelElement parse(File file, XmlOptions options) throws XmlException, IOException {
            return (TopLevelElement)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static TopLevelElement parse(URL u) throws XmlException, IOException {
            return (TopLevelElement)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static TopLevelElement parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (TopLevelElement)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static TopLevelElement parse(InputStream is) throws XmlException, IOException {
            return (TopLevelElement)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static TopLevelElement parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (TopLevelElement)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static TopLevelElement parse(Reader r) throws XmlException, IOException {
            return (TopLevelElement)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static TopLevelElement parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (TopLevelElement)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static TopLevelElement parse(XMLStreamReader sr) throws XmlException {
            return (TopLevelElement)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static TopLevelElement parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (TopLevelElement)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static TopLevelElement parse(Node node) throws XmlException {
            return (TopLevelElement)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static TopLevelElement parse(Node node, XmlOptions options) throws XmlException {
            return (TopLevelElement)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static TopLevelElement parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (TopLevelElement)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static TopLevelElement parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (TopLevelElement)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

