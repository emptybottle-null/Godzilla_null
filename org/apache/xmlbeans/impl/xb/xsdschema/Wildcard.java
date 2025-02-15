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
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.impl.xb.xsdschema.NamespaceList;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface Wildcard
extends Annotated {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Wildcard.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("wildcarde0b9type");

    public Object getNamespace();

    public NamespaceList xgetNamespace();

    public boolean isSetNamespace();

    public void setNamespace(Object var1);

    public void xsetNamespace(NamespaceList var1);

    public void unsetNamespace();

    public ProcessContents.Enum getProcessContents();

    public ProcessContents xgetProcessContents();

    public boolean isSetProcessContents();

    public void setProcessContents(ProcessContents.Enum var1);

    public void xsetProcessContents(ProcessContents var1);

    public void unsetProcessContents();

    public static final class Factory {
        public static Wildcard newInstance() {
            return (Wildcard)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static Wildcard newInstance(XmlOptions options) {
            return (Wildcard)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static Wildcard parse(String xmlAsString) throws XmlException {
            return (Wildcard)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static Wildcard parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (Wildcard)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static Wildcard parse(File file) throws XmlException, IOException {
            return (Wildcard)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static Wildcard parse(File file, XmlOptions options) throws XmlException, IOException {
            return (Wildcard)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static Wildcard parse(URL u) throws XmlException, IOException {
            return (Wildcard)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static Wildcard parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (Wildcard)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static Wildcard parse(InputStream is) throws XmlException, IOException {
            return (Wildcard)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static Wildcard parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (Wildcard)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static Wildcard parse(Reader r) throws XmlException, IOException {
            return (Wildcard)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static Wildcard parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (Wildcard)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static Wildcard parse(XMLStreamReader sr) throws XmlException {
            return (Wildcard)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static Wildcard parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (Wildcard)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static Wildcard parse(Node node) throws XmlException {
            return (Wildcard)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static Wildcard parse(Node node, XmlOptions options) throws XmlException {
            return (Wildcard)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static Wildcard parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (Wildcard)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static Wildcard parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (Wildcard)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface ProcessContents
    extends XmlNMTOKEN {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(ProcessContents.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("processcontents864aattrtype");
        public static final Enum SKIP = Enum.forString("skip");
        public static final Enum LAX = Enum.forString("lax");
        public static final Enum STRICT = Enum.forString("strict");
        public static final int INT_SKIP = 1;
        public static final int INT_LAX = 2;
        public static final int INT_STRICT = 3;

        public StringEnumAbstractBase enumValue();

        public void set(StringEnumAbstractBase var1);

        public static final class Factory {
            public static ProcessContents newValue(Object obj) {
                return (ProcessContents)type.newValue(obj);
            }

            public static ProcessContents newInstance() {
                return (ProcessContents)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static ProcessContents newInstance(XmlOptions options) {
                return (ProcessContents)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }

        public static final class Enum
        extends StringEnumAbstractBase {
            static final int INT_SKIP = 1;
            static final int INT_LAX = 2;
            static final int INT_STRICT = 3;
            public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("skip", 1), new Enum("lax", 2), new Enum("strict", 3)});
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

