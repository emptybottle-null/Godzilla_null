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
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface Keybase
extends Annotated {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Keybase.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("keybase3955type");

    public SelectorDocument.Selector getSelector();

    public void setSelector(SelectorDocument.Selector var1);

    public SelectorDocument.Selector addNewSelector();

    public FieldDocument.Field[] getFieldArray();

    public FieldDocument.Field getFieldArray(int var1);

    public int sizeOfFieldArray();

    public void setFieldArray(FieldDocument.Field[] var1);

    public void setFieldArray(int var1, FieldDocument.Field var2);

    public FieldDocument.Field insertNewField(int var1);

    public FieldDocument.Field addNewField();

    public void removeField(int var1);

    public String getName();

    public XmlNCName xgetName();

    public void setName(String var1);

    public void xsetName(XmlNCName var1);

    public static final class Factory {
        public static Keybase newInstance() {
            return (Keybase)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static Keybase newInstance(XmlOptions options) {
            return (Keybase)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static Keybase parse(String xmlAsString) throws XmlException {
            return (Keybase)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static Keybase parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (Keybase)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static Keybase parse(File file) throws XmlException, IOException {
            return (Keybase)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static Keybase parse(File file, XmlOptions options) throws XmlException, IOException {
            return (Keybase)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static Keybase parse(URL u) throws XmlException, IOException {
            return (Keybase)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static Keybase parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (Keybase)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static Keybase parse(InputStream is) throws XmlException, IOException {
            return (Keybase)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static Keybase parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (Keybase)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static Keybase parse(Reader r) throws XmlException, IOException {
            return (Keybase)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static Keybase parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (Keybase)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static Keybase parse(XMLStreamReader sr) throws XmlException {
            return (Keybase)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static Keybase parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (Keybase)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static Keybase parse(Node node) throws XmlException {
            return (Keybase)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static Keybase parse(Node node, XmlOptions options) throws XmlException {
            return (Keybase)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static Keybase parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (Keybase)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static Keybase parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (Keybase)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

