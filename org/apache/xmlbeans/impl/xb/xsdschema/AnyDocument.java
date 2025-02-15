/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigInteger;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.xb.xsdschema.AllNNI;
import org.apache.xmlbeans.impl.xb.xsdschema.Wildcard;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface AnyDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(AnyDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("anye729doctype");

    public Any getAny();

    public void setAny(Any var1);

    public Any addNewAny();

    public static final class Factory {
        public static AnyDocument newInstance() {
            return (AnyDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static AnyDocument newInstance(XmlOptions options) {
            return (AnyDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static AnyDocument parse(String xmlAsString) throws XmlException {
            return (AnyDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static AnyDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (AnyDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static AnyDocument parse(File file) throws XmlException, IOException {
            return (AnyDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static AnyDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (AnyDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static AnyDocument parse(URL u) throws XmlException, IOException {
            return (AnyDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static AnyDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (AnyDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static AnyDocument parse(InputStream is) throws XmlException, IOException {
            return (AnyDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static AnyDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (AnyDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static AnyDocument parse(Reader r) throws XmlException, IOException {
            return (AnyDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static AnyDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (AnyDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static AnyDocument parse(XMLStreamReader sr) throws XmlException {
            return (AnyDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static AnyDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (AnyDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static AnyDocument parse(Node node) throws XmlException {
            return (AnyDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static AnyDocument parse(Node node, XmlOptions options) throws XmlException {
            return (AnyDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static AnyDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (AnyDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static AnyDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (AnyDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface Any
    extends Wildcard {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Any.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("anye9d1elemtype");

        public BigInteger getMinOccurs();

        public XmlNonNegativeInteger xgetMinOccurs();

        public boolean isSetMinOccurs();

        public void setMinOccurs(BigInteger var1);

        public void xsetMinOccurs(XmlNonNegativeInteger var1);

        public void unsetMinOccurs();

        public Object getMaxOccurs();

        public AllNNI xgetMaxOccurs();

        public boolean isSetMaxOccurs();

        public void setMaxOccurs(Object var1);

        public void xsetMaxOccurs(AllNNI var1);

        public void unsetMaxOccurs();

        public static final class Factory {
            public static Any newInstance() {
                return (Any)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static Any newInstance(XmlOptions options) {
                return (Any)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }
}

