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
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.XmlNMTOKEN;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.impl.xb.xsdschema.FormChoice;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface Attribute
extends Annotated {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Attribute.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("attribute83a9type");

    public LocalSimpleType getSimpleType();

    public boolean isSetSimpleType();

    public void setSimpleType(LocalSimpleType var1);

    public LocalSimpleType addNewSimpleType();

    public void unsetSimpleType();

    public String getName();

    public XmlNCName xgetName();

    public boolean isSetName();

    public void setName(String var1);

    public void xsetName(XmlNCName var1);

    public void unsetName();

    public QName getRef();

    public XmlQName xgetRef();

    public boolean isSetRef();

    public void setRef(QName var1);

    public void xsetRef(XmlQName var1);

    public void unsetRef();

    public QName getType();

    public XmlQName xgetType();

    public boolean isSetType();

    public void setType(QName var1);

    public void xsetType(XmlQName var1);

    public void unsetType();

    public Use.Enum getUse();

    public Use xgetUse();

    public boolean isSetUse();

    public void setUse(Use.Enum var1);

    public void xsetUse(Use var1);

    public void unsetUse();

    public String getDefault();

    public XmlString xgetDefault();

    public boolean isSetDefault();

    public void setDefault(String var1);

    public void xsetDefault(XmlString var1);

    public void unsetDefault();

    public String getFixed();

    public XmlString xgetFixed();

    public boolean isSetFixed();

    public void setFixed(String var1);

    public void xsetFixed(XmlString var1);

    public void unsetFixed();

    public FormChoice.Enum getForm();

    public FormChoice xgetForm();

    public boolean isSetForm();

    public void setForm(FormChoice.Enum var1);

    public void xsetForm(FormChoice var1);

    public void unsetForm();

    public static final class Factory {
        public static Attribute newInstance() {
            return (Attribute)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static Attribute newInstance(XmlOptions options) {
            return (Attribute)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static Attribute parse(String xmlAsString) throws XmlException {
            return (Attribute)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static Attribute parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (Attribute)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static Attribute parse(File file) throws XmlException, IOException {
            return (Attribute)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static Attribute parse(File file, XmlOptions options) throws XmlException, IOException {
            return (Attribute)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static Attribute parse(URL u) throws XmlException, IOException {
            return (Attribute)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static Attribute parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (Attribute)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static Attribute parse(InputStream is) throws XmlException, IOException {
            return (Attribute)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static Attribute parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (Attribute)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static Attribute parse(Reader r) throws XmlException, IOException {
            return (Attribute)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static Attribute parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (Attribute)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static Attribute parse(XMLStreamReader sr) throws XmlException {
            return (Attribute)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static Attribute parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (Attribute)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static Attribute parse(Node node) throws XmlException {
            return (Attribute)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static Attribute parse(Node node, XmlOptions options) throws XmlException {
            return (Attribute)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static Attribute parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (Attribute)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static Attribute parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (Attribute)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface Use
    extends XmlNMTOKEN {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Use.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("usea41aattrtype");
        public static final Enum PROHIBITED = Enum.forString("prohibited");
        public static final Enum OPTIONAL = Enum.forString("optional");
        public static final Enum REQUIRED = Enum.forString("required");
        public static final int INT_PROHIBITED = 1;
        public static final int INT_OPTIONAL = 2;
        public static final int INT_REQUIRED = 3;

        public StringEnumAbstractBase enumValue();

        public void set(StringEnumAbstractBase var1);

        public static final class Factory {
            public static Use newValue(Object obj) {
                return (Use)type.newValue(obj);
            }

            public static Use newInstance() {
                return (Use)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static Use newInstance(XmlOptions options) {
                return (Use)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }

        public static final class Enum
        extends StringEnumAbstractBase {
            static final int INT_PROHIBITED = 1;
            static final int INT_OPTIONAL = 2;
            static final int INT_REQUIRED = 3;
            public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("prohibited", 1), new Enum("optional", 2), new Enum("required", 3)});
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

