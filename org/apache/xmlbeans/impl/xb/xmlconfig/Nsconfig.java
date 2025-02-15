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
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.xb.xmlconfig.NamespaceList;
import org.apache.xmlbeans.impl.xb.xmlconfig.NamespacePrefixList;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface Nsconfig
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Nsconfig.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLCONFIG").resolveHandle("nsconfigaebatype");

    public String getPackage();

    public XmlString xgetPackage();

    public boolean isSetPackage();

    public void setPackage(String var1);

    public void xsetPackage(XmlString var1);

    public void unsetPackage();

    public String getPrefix();

    public XmlString xgetPrefix();

    public boolean isSetPrefix();

    public void setPrefix(String var1);

    public void xsetPrefix(XmlString var1);

    public void unsetPrefix();

    public String getSuffix();

    public XmlString xgetSuffix();

    public boolean isSetSuffix();

    public void setSuffix(String var1);

    public void xsetSuffix(XmlString var1);

    public void unsetSuffix();

    public Object getUri();

    public NamespaceList xgetUri();

    public boolean isSetUri();

    public void setUri(Object var1);

    public void xsetUri(NamespaceList var1);

    public void unsetUri();

    public List getUriprefix();

    public NamespacePrefixList xgetUriprefix();

    public boolean isSetUriprefix();

    public void setUriprefix(List var1);

    public void xsetUriprefix(NamespacePrefixList var1);

    public void unsetUriprefix();

    public static final class Factory {
        public static Nsconfig newInstance() {
            return (Nsconfig)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static Nsconfig newInstance(XmlOptions options) {
            return (Nsconfig)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static Nsconfig parse(String xmlAsString) throws XmlException {
            return (Nsconfig)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static Nsconfig parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (Nsconfig)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static Nsconfig parse(File file) throws XmlException, IOException {
            return (Nsconfig)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static Nsconfig parse(File file, XmlOptions options) throws XmlException, IOException {
            return (Nsconfig)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static Nsconfig parse(URL u) throws XmlException, IOException {
            return (Nsconfig)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static Nsconfig parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (Nsconfig)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static Nsconfig parse(InputStream is) throws XmlException, IOException {
            return (Nsconfig)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static Nsconfig parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (Nsconfig)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static Nsconfig parse(Reader r) throws XmlException, IOException {
            return (Nsconfig)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static Nsconfig parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (Nsconfig)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static Nsconfig parse(XMLStreamReader sr) throws XmlException {
            return (Nsconfig)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static Nsconfig parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (Nsconfig)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static Nsconfig parse(Node node) throws XmlException {
            return (Nsconfig)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static Nsconfig parse(Node node, XmlOptions options) throws XmlException {
            return (Nsconfig)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static Nsconfig parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (Nsconfig)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static Nsconfig parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (Nsconfig)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

