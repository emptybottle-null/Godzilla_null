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
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.xb.xsdschema.Keybase;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface KeyrefDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(KeyrefDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("keyref45afdoctype");

    public Keyref getKeyref();

    public void setKeyref(Keyref var1);

    public Keyref addNewKeyref();

    public static final class Factory {
        public static KeyrefDocument newInstance() {
            return (KeyrefDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static KeyrefDocument newInstance(XmlOptions options) {
            return (KeyrefDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static KeyrefDocument parse(String xmlAsString) throws XmlException {
            return (KeyrefDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static KeyrefDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (KeyrefDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static KeyrefDocument parse(File file) throws XmlException, IOException {
            return (KeyrefDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static KeyrefDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (KeyrefDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static KeyrefDocument parse(URL u) throws XmlException, IOException {
            return (KeyrefDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static KeyrefDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (KeyrefDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static KeyrefDocument parse(InputStream is) throws XmlException, IOException {
            return (KeyrefDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static KeyrefDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (KeyrefDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static KeyrefDocument parse(Reader r) throws XmlException, IOException {
            return (KeyrefDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static KeyrefDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (KeyrefDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static KeyrefDocument parse(XMLStreamReader sr) throws XmlException {
            return (KeyrefDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static KeyrefDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (KeyrefDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static KeyrefDocument parse(Node node) throws XmlException {
            return (KeyrefDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static KeyrefDocument parse(Node node, XmlOptions options) throws XmlException {
            return (KeyrefDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static KeyrefDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (KeyrefDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static KeyrefDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (KeyrefDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface Keyref
    extends Keybase {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Keyref.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("keyref7a1felemtype");

        public QName getRefer();

        public XmlQName xgetRefer();

        public void setRefer(QName var1);

        public void xsetRefer(XmlQName var1);

        public static final class Factory {
            public static Keyref newInstance() {
                return (Keyref)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static Keyref newInstance(XmlOptions options) {
                return (Keyref)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }
}

