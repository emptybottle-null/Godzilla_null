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
import org.apache.xmlbeans.impl.xb.xsdschema.RealGroup;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface NamedGroup
extends RealGroup {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(NamedGroup.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("namedgroup878dtype");

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
        public static NamedGroup newInstance() {
            return (NamedGroup)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static NamedGroup newInstance(XmlOptions options) {
            return (NamedGroup)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static NamedGroup parse(String xmlAsString) throws XmlException {
            return (NamedGroup)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static NamedGroup parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (NamedGroup)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static NamedGroup parse(File file) throws XmlException, IOException {
            return (NamedGroup)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static NamedGroup parse(File file, XmlOptions options) throws XmlException, IOException {
            return (NamedGroup)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static NamedGroup parse(URL u) throws XmlException, IOException {
            return (NamedGroup)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static NamedGroup parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (NamedGroup)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static NamedGroup parse(InputStream is) throws XmlException, IOException {
            return (NamedGroup)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static NamedGroup parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (NamedGroup)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static NamedGroup parse(Reader r) throws XmlException, IOException {
            return (NamedGroup)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static NamedGroup parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (NamedGroup)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static NamedGroup parse(XMLStreamReader sr) throws XmlException {
            return (NamedGroup)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static NamedGroup parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (NamedGroup)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static NamedGroup parse(Node node) throws XmlException {
            return (NamedGroup)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static NamedGroup parse(Node node, XmlOptions options) throws XmlException {
            return (NamedGroup)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static NamedGroup parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (NamedGroup)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static NamedGroup parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (NamedGroup)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface All
    extends org.apache.xmlbeans.impl.xb.xsdschema.All {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(All.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("all82daelemtype");

        public static final class Factory {
            public static All newInstance() {
                return (All)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static All newInstance(XmlOptions options) {
                return (All)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }
}

