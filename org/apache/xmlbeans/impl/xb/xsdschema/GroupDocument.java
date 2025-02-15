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
import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface GroupDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(GroupDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("group6eb6doctype");

    public NamedGroup getGroup();

    public void setGroup(NamedGroup var1);

    public NamedGroup addNewGroup();

    public static final class Factory {
        public static GroupDocument newInstance() {
            return (GroupDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static GroupDocument newInstance(XmlOptions options) {
            return (GroupDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static GroupDocument parse(String xmlAsString) throws XmlException {
            return (GroupDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static GroupDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (GroupDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static GroupDocument parse(File file) throws XmlException, IOException {
            return (GroupDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static GroupDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (GroupDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static GroupDocument parse(URL u) throws XmlException, IOException {
            return (GroupDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static GroupDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (GroupDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static GroupDocument parse(InputStream is) throws XmlException, IOException {
            return (GroupDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static GroupDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (GroupDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static GroupDocument parse(Reader r) throws XmlException, IOException {
            return (GroupDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static GroupDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (GroupDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static GroupDocument parse(XMLStreamReader sr) throws XmlException {
            return (GroupDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static GroupDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (GroupDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static GroupDocument parse(Node node) throws XmlException {
            return (GroupDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static GroupDocument parse(Node node, XmlOptions options) throws XmlException {
            return (GroupDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static GroupDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (GroupDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static GroupDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (GroupDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

