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
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroup;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface NamedAttributeGroup
extends AttributeGroup {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(NamedAttributeGroup.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("namedattributegroup2e29type");

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
        public static NamedAttributeGroup newInstance() {
            return (NamedAttributeGroup)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static NamedAttributeGroup newInstance(XmlOptions options) {
            return (NamedAttributeGroup)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static NamedAttributeGroup parse(String xmlAsString) throws XmlException {
            return (NamedAttributeGroup)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static NamedAttributeGroup parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (NamedAttributeGroup)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static NamedAttributeGroup parse(File file) throws XmlException, IOException {
            return (NamedAttributeGroup)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static NamedAttributeGroup parse(File file, XmlOptions options) throws XmlException, IOException {
            return (NamedAttributeGroup)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static NamedAttributeGroup parse(URL u) throws XmlException, IOException {
            return (NamedAttributeGroup)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static NamedAttributeGroup parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (NamedAttributeGroup)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static NamedAttributeGroup parse(InputStream is) throws XmlException, IOException {
            return (NamedAttributeGroup)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static NamedAttributeGroup parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (NamedAttributeGroup)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static NamedAttributeGroup parse(Reader r) throws XmlException, IOException {
            return (NamedAttributeGroup)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static NamedAttributeGroup parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (NamedAttributeGroup)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static NamedAttributeGroup parse(XMLStreamReader sr) throws XmlException {
            return (NamedAttributeGroup)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static NamedAttributeGroup parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (NamedAttributeGroup)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static NamedAttributeGroup parse(Node node) throws XmlException {
            return (NamedAttributeGroup)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static NamedAttributeGroup parse(Node node, XmlOptions options) throws XmlException {
            return (NamedAttributeGroup)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static NamedAttributeGroup parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (NamedAttributeGroup)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static NamedAttributeGroup parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (NamedAttributeGroup)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

