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
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface ListDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(ListDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("listcde5doctype");

    public List getList();

    public void setList(List var1);

    public List addNewList();

    public static final class Factory {
        public static ListDocument newInstance() {
            return (ListDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static ListDocument newInstance(XmlOptions options) {
            return (ListDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static ListDocument parse(String xmlAsString) throws XmlException {
            return (ListDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static ListDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (ListDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static ListDocument parse(File file) throws XmlException, IOException {
            return (ListDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static ListDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (ListDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static ListDocument parse(URL u) throws XmlException, IOException {
            return (ListDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static ListDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (ListDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static ListDocument parse(InputStream is) throws XmlException, IOException {
            return (ListDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static ListDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (ListDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static ListDocument parse(Reader r) throws XmlException, IOException {
            return (ListDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static ListDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (ListDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static ListDocument parse(XMLStreamReader sr) throws XmlException {
            return (ListDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static ListDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (ListDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static ListDocument parse(Node node) throws XmlException {
            return (ListDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static ListDocument parse(Node node, XmlOptions options) throws XmlException {
            return (ListDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static ListDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (ListDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static ListDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (ListDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface List
    extends Annotated {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(List.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("list391felemtype");

        public LocalSimpleType getSimpleType();

        public boolean isSetSimpleType();

        public void setSimpleType(LocalSimpleType var1);

        public LocalSimpleType addNewSimpleType();

        public void unsetSimpleType();

        public QName getItemType();

        public XmlQName xgetItemType();

        public boolean isSetItemType();

        public void setItemType(QName var1);

        public void xsetItemType(XmlQName var1);

        public void unsetItemType();

        public static final class Factory {
            public static List newInstance() {
                return (List)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static List newInstance(XmlOptions options) {
                return (List)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }
}

