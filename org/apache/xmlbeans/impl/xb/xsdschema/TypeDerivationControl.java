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
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.xb.xsdschema.DerivationControl;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface TypeDerivationControl
extends DerivationControl {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(TypeDerivationControl.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("typederivationcontrol3239type");
    public static final DerivationControl.Enum EXTENSION = DerivationControl.EXTENSION;
    public static final DerivationControl.Enum RESTRICTION = DerivationControl.RESTRICTION;
    public static final DerivationControl.Enum LIST = DerivationControl.LIST;
    public static final DerivationControl.Enum UNION = DerivationControl.UNION;
    public static final int INT_EXTENSION = 2;
    public static final int INT_RESTRICTION = 3;
    public static final int INT_LIST = 4;
    public static final int INT_UNION = 5;

    public static final class Factory {
        public static TypeDerivationControl newValue(Object obj) {
            return (TypeDerivationControl)type.newValue(obj);
        }

        public static TypeDerivationControl newInstance() {
            return (TypeDerivationControl)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static TypeDerivationControl newInstance(XmlOptions options) {
            return (TypeDerivationControl)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static TypeDerivationControl parse(String xmlAsString) throws XmlException {
            return (TypeDerivationControl)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static TypeDerivationControl parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (TypeDerivationControl)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static TypeDerivationControl parse(File file) throws XmlException, IOException {
            return (TypeDerivationControl)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static TypeDerivationControl parse(File file, XmlOptions options) throws XmlException, IOException {
            return (TypeDerivationControl)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static TypeDerivationControl parse(URL u) throws XmlException, IOException {
            return (TypeDerivationControl)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static TypeDerivationControl parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (TypeDerivationControl)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static TypeDerivationControl parse(InputStream is) throws XmlException, IOException {
            return (TypeDerivationControl)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static TypeDerivationControl parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (TypeDerivationControl)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static TypeDerivationControl parse(Reader r) throws XmlException, IOException {
            return (TypeDerivationControl)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static TypeDerivationControl parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (TypeDerivationControl)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static TypeDerivationControl parse(XMLStreamReader sr) throws XmlException {
            return (TypeDerivationControl)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static TypeDerivationControl parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (TypeDerivationControl)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static TypeDerivationControl parse(Node node) throws XmlException {
            return (TypeDerivationControl)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static TypeDerivationControl parse(Node node, XmlOptions options) throws XmlException {
            return (TypeDerivationControl)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static TypeDerivationControl parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (TypeDerivationControl)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static TypeDerivationControl parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (TypeDerivationControl)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

