/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xmlconfig;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface NamespacePrefixList
extends XmlAnySimpleType {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(NamespacePrefixList.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLCONFIG").resolveHandle("namespaceprefixlistec0ctype");

    public List getListValue();

    public List xgetListValue();

    public void setListValue(List var1);

    public List listValue();

    public List xlistValue();

    public void set(List var1);

    public static final class Factory {
        public static NamespacePrefixList newValue(Object obj) {
            return (NamespacePrefixList)type.newValue(obj);
        }

        public static NamespacePrefixList newInstance() {
            return (NamespacePrefixList)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static NamespacePrefixList newInstance(XmlOptions options) {
            return (NamespacePrefixList)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static NamespacePrefixList parse(String xmlAsString) throws XmlException {
            return (NamespacePrefixList)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static NamespacePrefixList parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (NamespacePrefixList)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static NamespacePrefixList parse(File file) throws XmlException, IOException {
            return (NamespacePrefixList)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static NamespacePrefixList parse(File file, XmlOptions options) throws XmlException, IOException {
            return (NamespacePrefixList)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static NamespacePrefixList parse(URL u) throws XmlException, IOException {
            return (NamespacePrefixList)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static NamespacePrefixList parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (NamespacePrefixList)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static NamespacePrefixList parse(InputStream is) throws XmlException, IOException {
            return (NamespacePrefixList)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static NamespacePrefixList parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (NamespacePrefixList)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static NamespacePrefixList parse(Reader r) throws XmlException, IOException {
            return (NamespacePrefixList)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static NamespacePrefixList parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (NamespacePrefixList)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static NamespacePrefixList parse(XMLStreamReader sr) throws XmlException {
            return (NamespacePrefixList)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static NamespacePrefixList parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (NamespacePrefixList)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static NamespacePrefixList parse(Node node) throws XmlException {
            return (NamespacePrefixList)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static NamespacePrefixList parse(Node node, XmlOptions options) throws XmlException {
            return (NamespacePrefixList)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static NamespacePrefixList parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (NamespacePrefixList)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static NamespacePrefixList parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (NamespacePrefixList)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

