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
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.xb.xmlconfig.Qnametargetlist;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface Qnameconfig
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Qnameconfig.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLCONFIG").resolveHandle("qnameconfig463ftype");

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

    public List getTarget();

    public Qnametargetlist xgetTarget();

    public boolean isSetTarget();

    public void setTarget(List var1);

    public void xsetTarget(Qnametargetlist var1);

    public void unsetTarget();

    public static final class Factory {
        public static Qnameconfig newInstance() {
            return (Qnameconfig)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static Qnameconfig newInstance(XmlOptions options) {
            return (Qnameconfig)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static Qnameconfig parse(String xmlAsString) throws XmlException {
            return (Qnameconfig)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static Qnameconfig parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (Qnameconfig)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static Qnameconfig parse(File file) throws XmlException, IOException {
            return (Qnameconfig)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static Qnameconfig parse(File file, XmlOptions options) throws XmlException, IOException {
            return (Qnameconfig)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static Qnameconfig parse(URL u) throws XmlException, IOException {
            return (Qnameconfig)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static Qnameconfig parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (Qnameconfig)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static Qnameconfig parse(InputStream is) throws XmlException, IOException {
            return (Qnameconfig)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static Qnameconfig parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (Qnameconfig)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static Qnameconfig parse(Reader r) throws XmlException, IOException {
            return (Qnameconfig)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static Qnameconfig parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (Qnameconfig)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static Qnameconfig parse(XMLStreamReader sr) throws XmlException {
            return (Qnameconfig)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static Qnameconfig parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (Qnameconfig)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static Qnameconfig parse(Node node) throws XmlException {
            return (Qnameconfig)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static Qnameconfig parse(Node node, XmlOptions options) throws XmlException {
            return (Qnameconfig)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static Qnameconfig parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (Qnameconfig)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static Qnameconfig parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (Qnameconfig)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

