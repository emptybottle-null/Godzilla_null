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
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface Facet
extends Annotated {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Facet.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("facet446etype");

    public XmlAnySimpleType getValue();

    public void setValue(XmlAnySimpleType var1);

    public XmlAnySimpleType addNewValue();

    public boolean getFixed();

    public XmlBoolean xgetFixed();

    public boolean isSetFixed();

    public void setFixed(boolean var1);

    public void xsetFixed(XmlBoolean var1);

    public void unsetFixed();

    public static final class Factory {
        public static Facet newInstance() {
            return (Facet)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static Facet newInstance(XmlOptions options) {
            return (Facet)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static Facet parse(String xmlAsString) throws XmlException {
            return (Facet)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static Facet parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (Facet)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static Facet parse(File file) throws XmlException, IOException {
            return (Facet)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static Facet parse(File file, XmlOptions options) throws XmlException, IOException {
            return (Facet)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static Facet parse(URL u) throws XmlException, IOException {
            return (Facet)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static Facet parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (Facet)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static Facet parse(InputStream is) throws XmlException, IOException {
            return (Facet)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static Facet parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (Facet)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static Facet parse(Reader r) throws XmlException, IOException {
            return (Facet)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static Facet parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (Facet)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static Facet parse(XMLStreamReader sr) throws XmlException {
            return (Facet)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static Facet parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (Facet)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static Facet parse(Node node) throws XmlException {
            return (Facet)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static Facet parse(Node node, XmlOptions options) throws XmlException {
            return (Facet)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static Facet parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (Facet)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static Facet parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (Facet)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

