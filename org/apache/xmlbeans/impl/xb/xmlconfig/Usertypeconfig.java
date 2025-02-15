/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xmlconfig;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface Usertypeconfig
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Usertypeconfig.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLCONFIG").resolveHandle("usertypeconfig7bbatype");

    public String getStaticHandler();

    public XmlString xgetStaticHandler();

    public void setStaticHandler(String var1);

    public void xsetStaticHandler(XmlString var1);

    public QName getName();

    public XmlQName xgetName();

    public boolean isSetName();

    public void setName(QName var1);

    public void xsetName(XmlQName var1);

    public void unsetName();

    public String getJavaname();

    public XmlString xgetJavaname();

    public boolean isSetJavaname();

    public void setJavaname(String var1);

    public void xsetJavaname(XmlString var1);

    public void unsetJavaname();

    public static final class Factory {
        public static Usertypeconfig newInstance() {
            return (Usertypeconfig)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static Usertypeconfig newInstance(XmlOptions options) {
            return (Usertypeconfig)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static Usertypeconfig parse(String xmlAsString) throws XmlException {
            return (Usertypeconfig)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static Usertypeconfig parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (Usertypeconfig)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static Usertypeconfig parse(File file) throws XmlException, IOException {
            return (Usertypeconfig)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static Usertypeconfig parse(File file, XmlOptions options) throws XmlException, IOException {
            return (Usertypeconfig)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static Usertypeconfig parse(URL u) throws XmlException, IOException {
            return (Usertypeconfig)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static Usertypeconfig parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (Usertypeconfig)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static Usertypeconfig parse(InputStream is) throws XmlException, IOException {
            return (Usertypeconfig)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static Usertypeconfig parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (Usertypeconfig)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static Usertypeconfig parse(Reader r) throws XmlException, IOException {
            return (Usertypeconfig)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static Usertypeconfig parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (Usertypeconfig)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static Usertypeconfig parse(XMLStreamReader sr) throws XmlException {
            return (Usertypeconfig)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static Usertypeconfig parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (Usertypeconfig)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static Usertypeconfig parse(Node node) throws XmlException {
            return (Usertypeconfig)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static Usertypeconfig parse(Node node, XmlOptions options) throws XmlException {
            return (Usertypeconfig)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static Usertypeconfig parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (Usertypeconfig)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static Usertypeconfig parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (Usertypeconfig)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

