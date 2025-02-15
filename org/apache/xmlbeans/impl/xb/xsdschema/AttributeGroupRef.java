/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema;

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
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroup;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface AttributeGroupRef
extends AttributeGroup {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(AttributeGroupRef.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("attributegroupref8375type");

    @Override
    public QName getRef();

    @Override
    public XmlQName xgetRef();

    @Override
    public boolean isSetRef();

    @Override
    public void setRef(QName var1);

    @Override
    public void xsetRef(XmlQName var1);

    @Override
    public void unsetRef();

    public static final class Factory {
        public static AttributeGroupRef newInstance() {
            return (AttributeGroupRef)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static AttributeGroupRef newInstance(XmlOptions options) {
            return (AttributeGroupRef)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static AttributeGroupRef parse(String xmlAsString) throws XmlException {
            return (AttributeGroupRef)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static AttributeGroupRef parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (AttributeGroupRef)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static AttributeGroupRef parse(File file) throws XmlException, IOException {
            return (AttributeGroupRef)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static AttributeGroupRef parse(File file, XmlOptions options) throws XmlException, IOException {
            return (AttributeGroupRef)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static AttributeGroupRef parse(URL u) throws XmlException, IOException {
            return (AttributeGroupRef)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static AttributeGroupRef parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (AttributeGroupRef)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static AttributeGroupRef parse(InputStream is) throws XmlException, IOException {
            return (AttributeGroupRef)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static AttributeGroupRef parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (AttributeGroupRef)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static AttributeGroupRef parse(Reader r) throws XmlException, IOException {
            return (AttributeGroupRef)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static AttributeGroupRef parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (AttributeGroupRef)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static AttributeGroupRef parse(XMLStreamReader sr) throws XmlException {
            return (AttributeGroupRef)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static AttributeGroupRef parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (AttributeGroupRef)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static AttributeGroupRef parse(Node node) throws XmlException {
            return (AttributeGroupRef)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static AttributeGroupRef parse(Node node, XmlOptions options) throws XmlException {
            return (AttributeGroupRef)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static AttributeGroupRef parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (AttributeGroupRef)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static AttributeGroupRef parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (AttributeGroupRef)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

