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
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleType;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface TopLevelSimpleType
extends SimpleType {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(TopLevelSimpleType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("toplevelsimpletypec958type");

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
        public static TopLevelSimpleType newInstance() {
            return (TopLevelSimpleType)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static TopLevelSimpleType newInstance(XmlOptions options) {
            return (TopLevelSimpleType)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static TopLevelSimpleType parse(String xmlAsString) throws XmlException {
            return (TopLevelSimpleType)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static TopLevelSimpleType parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (TopLevelSimpleType)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static TopLevelSimpleType parse(File file) throws XmlException, IOException {
            return (TopLevelSimpleType)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static TopLevelSimpleType parse(File file, XmlOptions options) throws XmlException, IOException {
            return (TopLevelSimpleType)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static TopLevelSimpleType parse(URL u) throws XmlException, IOException {
            return (TopLevelSimpleType)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static TopLevelSimpleType parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (TopLevelSimpleType)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static TopLevelSimpleType parse(InputStream is) throws XmlException, IOException {
            return (TopLevelSimpleType)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static TopLevelSimpleType parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (TopLevelSimpleType)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static TopLevelSimpleType parse(Reader r) throws XmlException, IOException {
            return (TopLevelSimpleType)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static TopLevelSimpleType parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (TopLevelSimpleType)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static TopLevelSimpleType parse(XMLStreamReader sr) throws XmlException {
            return (TopLevelSimpleType)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static TopLevelSimpleType parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (TopLevelSimpleType)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static TopLevelSimpleType parse(Node node) throws XmlException {
            return (TopLevelSimpleType)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static TopLevelSimpleType parse(Node node, XmlOptions options) throws XmlException {
            return (TopLevelSimpleType)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static TopLevelSimpleType parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (TopLevelSimpleType)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static TopLevelSimpleType parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (TopLevelSimpleType)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

