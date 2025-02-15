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
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlNMTOKEN;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface AllNNI
extends XmlAnySimpleType {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(AllNNI.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("allnni78cbtype");

    public Object getObjectValue();

    public void setObjectValue(Object var1);

    public Object objectValue();

    public void objectSet(Object var1);

    public SchemaType instanceType();

    public static final class Factory {
        public static AllNNI newValue(Object obj) {
            return (AllNNI)type.newValue(obj);
        }

        public static AllNNI newInstance() {
            return (AllNNI)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static AllNNI newInstance(XmlOptions options) {
            return (AllNNI)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static AllNNI parse(String xmlAsString) throws XmlException {
            return (AllNNI)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static AllNNI parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (AllNNI)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static AllNNI parse(File file) throws XmlException, IOException {
            return (AllNNI)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static AllNNI parse(File file, XmlOptions options) throws XmlException, IOException {
            return (AllNNI)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static AllNNI parse(URL u) throws XmlException, IOException {
            return (AllNNI)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static AllNNI parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (AllNNI)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static AllNNI parse(InputStream is) throws XmlException, IOException {
            return (AllNNI)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static AllNNI parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (AllNNI)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static AllNNI parse(Reader r) throws XmlException, IOException {
            return (AllNNI)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static AllNNI parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (AllNNI)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static AllNNI parse(XMLStreamReader sr) throws XmlException {
            return (AllNNI)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static AllNNI parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (AllNNI)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static AllNNI parse(Node node) throws XmlException {
            return (AllNNI)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static AllNNI parse(Node node, XmlOptions options) throws XmlException {
            return (AllNNI)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static AllNNI parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (AllNNI)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static AllNNI parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (AllNNI)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface Member
    extends XmlNMTOKEN {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Member.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("anon0330type");
        public static final Enum UNBOUNDED = Enum.forString("unbounded");
        public static final int INT_UNBOUNDED = 1;

        public StringEnumAbstractBase enumValue();

        public void set(StringEnumAbstractBase var1);

        public static final class Factory {
            public static Member newValue(Object obj) {
                return (Member)type.newValue(obj);
            }

            public static Member newInstance() {
                return (Member)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static Member newInstance(XmlOptions options) {
                return (Member)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }

        public static final class Enum
        extends StringEnumAbstractBase {
            static final int INT_UNBOUNDED = 1;
            public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("unbounded", 1)});
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
}

