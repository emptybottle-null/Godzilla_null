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
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.xb.xsdschema.NumFacet;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface TotalDigitsDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(TotalDigitsDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("totaldigits4a8bdoctype");

    public TotalDigits getTotalDigits();

    public void setTotalDigits(TotalDigits var1);

    public TotalDigits addNewTotalDigits();

    public static final class Factory {
        public static TotalDigitsDocument newInstance() {
            return (TotalDigitsDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static TotalDigitsDocument newInstance(XmlOptions options) {
            return (TotalDigitsDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static TotalDigitsDocument parse(String xmlAsString) throws XmlException {
            return (TotalDigitsDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static TotalDigitsDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (TotalDigitsDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static TotalDigitsDocument parse(File file) throws XmlException, IOException {
            return (TotalDigitsDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static TotalDigitsDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (TotalDigitsDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static TotalDigitsDocument parse(URL u) throws XmlException, IOException {
            return (TotalDigitsDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static TotalDigitsDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (TotalDigitsDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static TotalDigitsDocument parse(InputStream is) throws XmlException, IOException {
            return (TotalDigitsDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static TotalDigitsDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (TotalDigitsDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static TotalDigitsDocument parse(Reader r) throws XmlException, IOException {
            return (TotalDigitsDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static TotalDigitsDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (TotalDigitsDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static TotalDigitsDocument parse(XMLStreamReader sr) throws XmlException {
            return (TotalDigitsDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static TotalDigitsDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (TotalDigitsDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static TotalDigitsDocument parse(Node node) throws XmlException {
            return (TotalDigitsDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static TotalDigitsDocument parse(Node node, XmlOptions options) throws XmlException {
            return (TotalDigitsDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static TotalDigitsDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (TotalDigitsDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static TotalDigitsDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (TotalDigitsDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface TotalDigits
    extends NumFacet {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(TotalDigits.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("totaldigits2615elemtype");

        public static final class Factory {
            public static TotalDigits newInstance() {
                return (TotalDigits)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static TotalDigits newInstance(XmlOptions options) {
                return (TotalDigits)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }
}

