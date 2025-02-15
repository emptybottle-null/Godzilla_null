/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xmlschema;

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
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface SpaceAttribute
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(SpaceAttribute.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLLANG").resolveHandle("space9344attrtypetype");

    public Space.Enum getSpace();

    public Space xgetSpace();

    public boolean isSetSpace();

    public void setSpace(Space.Enum var1);

    public void xsetSpace(Space var1);

    public void unsetSpace();

    public static final class Factory {
        public static SpaceAttribute newInstance() {
            return (SpaceAttribute)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static SpaceAttribute newInstance(XmlOptions options) {
            return (SpaceAttribute)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static SpaceAttribute parse(String xmlAsString) throws XmlException {
            return (SpaceAttribute)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static SpaceAttribute parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (SpaceAttribute)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static SpaceAttribute parse(File file) throws XmlException, IOException {
            return (SpaceAttribute)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static SpaceAttribute parse(File file, XmlOptions options) throws XmlException, IOException {
            return (SpaceAttribute)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static SpaceAttribute parse(URL u) throws XmlException, IOException {
            return (SpaceAttribute)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static SpaceAttribute parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (SpaceAttribute)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static SpaceAttribute parse(InputStream is) throws XmlException, IOException {
            return (SpaceAttribute)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static SpaceAttribute parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (SpaceAttribute)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static SpaceAttribute parse(Reader r) throws XmlException, IOException {
            return (SpaceAttribute)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static SpaceAttribute parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (SpaceAttribute)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static SpaceAttribute parse(XMLStreamReader sr) throws XmlException {
            return (SpaceAttribute)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static SpaceAttribute parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (SpaceAttribute)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static SpaceAttribute parse(Node node) throws XmlException {
            return (SpaceAttribute)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static SpaceAttribute parse(Node node, XmlOptions options) throws XmlException {
            return (SpaceAttribute)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static SpaceAttribute parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (SpaceAttribute)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static SpaceAttribute parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (SpaceAttribute)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface Space
    extends XmlNCName {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Space.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLLANG").resolveHandle("spaceb986attrtype");
        public static final Enum DEFAULT = Enum.forString("default");
        public static final Enum PRESERVE = Enum.forString("preserve");
        public static final int INT_DEFAULT = 1;
        public static final int INT_PRESERVE = 2;

        public StringEnumAbstractBase enumValue();

        public void set(StringEnumAbstractBase var1);

        public static final class Factory {
            public static Space newValue(Object obj) {
                return (Space)type.newValue(obj);
            }

            public static Space newInstance() {
                return (Space)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static Space newInstance(XmlOptions options) {
                return (Space)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }

        public static final class Enum
        extends StringEnumAbstractBase {
            static final int INT_DEFAULT = 1;
            static final int INT_PRESERVE = 2;
            public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("default", 1), new Enum("preserve", 2)});
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

