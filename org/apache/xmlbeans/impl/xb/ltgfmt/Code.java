/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.ltgfmt;

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
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface Code
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Code.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLTOOLS").resolveHandle("codef72ftype");

    public String getID();

    public XmlToken xgetID();

    public boolean isSetID();

    public void setID(String var1);

    public void xsetID(XmlToken var1);

    public void unsetID();

    public static final class Factory {
        public static Code newInstance() {
            return (Code)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static Code newInstance(XmlOptions options) {
            return (Code)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static Code parse(String xmlAsString) throws XmlException {
            return (Code)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static Code parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (Code)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static Code parse(File file) throws XmlException, IOException {
            return (Code)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static Code parse(File file, XmlOptions options) throws XmlException, IOException {
            return (Code)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static Code parse(URL u) throws XmlException, IOException {
            return (Code)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static Code parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (Code)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static Code parse(InputStream is) throws XmlException, IOException {
            return (Code)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static Code parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (Code)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static Code parse(Reader r) throws XmlException, IOException {
            return (Code)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static Code parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (Code)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static Code parse(XMLStreamReader sr) throws XmlException {
            return (Code)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static Code parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (Code)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static Code parse(Node node) throws XmlException {
            return (Code)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static Code parse(Node node, XmlOptions options) throws XmlException {
            return (Code)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static Code parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (Code)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static Code parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (Code)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

