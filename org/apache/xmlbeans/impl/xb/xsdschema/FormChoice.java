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
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlNMTOKEN;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface FormChoice
extends XmlNMTOKEN {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(FormChoice.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("formchoicef2aetype");
    public static final Enum QUALIFIED = Enum.forString("qualified");
    public static final Enum UNQUALIFIED = Enum.forString("unqualified");
    public static final int INT_QUALIFIED = 1;
    public static final int INT_UNQUALIFIED = 2;

    public StringEnumAbstractBase enumValue();

    public void set(StringEnumAbstractBase var1);

    public static final class Factory {
        public static FormChoice newValue(Object obj) {
            return (FormChoice)type.newValue(obj);
        }

        public static FormChoice newInstance() {
            return (FormChoice)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static FormChoice newInstance(XmlOptions options) {
            return (FormChoice)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static FormChoice parse(String xmlAsString) throws XmlException {
            return (FormChoice)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static FormChoice parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (FormChoice)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static FormChoice parse(File file) throws XmlException, IOException {
            return (FormChoice)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static FormChoice parse(File file, XmlOptions options) throws XmlException, IOException {
            return (FormChoice)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static FormChoice parse(URL u) throws XmlException, IOException {
            return (FormChoice)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static FormChoice parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (FormChoice)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static FormChoice parse(InputStream is) throws XmlException, IOException {
            return (FormChoice)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static FormChoice parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (FormChoice)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static FormChoice parse(Reader r) throws XmlException, IOException {
            return (FormChoice)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static FormChoice parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (FormChoice)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static FormChoice parse(XMLStreamReader sr) throws XmlException {
            return (FormChoice)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static FormChoice parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (FormChoice)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static FormChoice parse(Node node) throws XmlException {
            return (FormChoice)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static FormChoice parse(Node node, XmlOptions options) throws XmlException {
            return (FormChoice)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static FormChoice parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (FormChoice)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static FormChoice parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (FormChoice)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static final class Enum
    extends StringEnumAbstractBase {
        static final int INT_QUALIFIED = 1;
        static final int INT_UNQUALIFIED = 2;
        public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("qualified", 1), new Enum("unqualified", 2)});
        private static final long serialVersionUID = 1L;

        public static Enum forString(String s) {
            return (Enum)table.forString(s);
        }

        public static Enum forInt(int i) {
            return (Enum)table.forInt(i);
        }

        private Enum(String s, int i) {
            super(s, i);
        }

        private Object readResolve() {
            return Enum.forInt(this.intValue());
        }
    }
}

