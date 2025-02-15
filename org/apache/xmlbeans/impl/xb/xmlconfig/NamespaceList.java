/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xmlconfig;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface NamespaceList
extends XmlAnySimpleType {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(NamespaceList.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLCONFIG").resolveHandle("namespacelist20datype");

    public Object getObjectValue();

    public void setObjectValue(Object var1);

    public Object objectValue();

    public void objectSet(Object var1);

    public SchemaType instanceType();

    public static final class Factory {
        public static NamespaceList newValue(Object obj) {
            return (NamespaceList)type.newValue(obj);
        }

        public static NamespaceList newInstance() {
            return (NamespaceList)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static NamespaceList newInstance(XmlOptions options) {
            return (NamespaceList)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static NamespaceList parse(String xmlAsString) throws XmlException {
            return (NamespaceList)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static NamespaceList parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (NamespaceList)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static NamespaceList parse(File file) throws XmlException, IOException {
            return (NamespaceList)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static NamespaceList parse(File file, XmlOptions options) throws XmlException, IOException {
            return (NamespaceList)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static NamespaceList parse(URL u) throws XmlException, IOException {
            return (NamespaceList)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static NamespaceList parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (NamespaceList)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static NamespaceList parse(InputStream is) throws XmlException, IOException {
            return (NamespaceList)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static NamespaceList parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (NamespaceList)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static NamespaceList parse(Reader r) throws XmlException, IOException {
            return (NamespaceList)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static NamespaceList parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (NamespaceList)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static NamespaceList parse(XMLStreamReader sr) throws XmlException {
            return (NamespaceList)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static NamespaceList parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (NamespaceList)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static NamespaceList parse(Node node) throws XmlException {
            return (NamespaceList)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static NamespaceList parse(Node node, XmlOptions options) throws XmlException {
            return (NamespaceList)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static NamespaceList parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (NamespaceList)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static NamespaceList parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (NamespaceList)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface Member2
    extends XmlAnySimpleType {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Member2.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLCONFIG").resolveHandle("anon5680type");

        public List getListValue();

        public List xgetListValue();

        public void setListValue(List var1);

        public List listValue();

        public List xlistValue();

        public void set(List var1);

        public static final class Factory {
            public static Member2 newValue(Object obj) {
                return (Member2)type.newValue(obj);
            }

            public static Member2 newInstance() {
                return (Member2)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static Member2 newInstance(XmlOptions options) {
                return (Member2)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }

        public static interface Item
        extends XmlAnySimpleType {
            public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Item.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLCONFIG").resolveHandle("anon0798type");

            public Object getObjectValue();

            public void setObjectValue(Object var1);

            public Object objectValue();

            public void objectSet(Object var1);

            public SchemaType instanceType();

            public static final class Factory {
                public static Item newValue(Object obj) {
                    return (Item)type.newValue(obj);
                }

                public static Item newInstance() {
                    return (Item)XmlBeans.getContextTypeLoader().newInstance(type, null);
                }

                public static Item newInstance(XmlOptions options) {
                    return (Item)XmlBeans.getContextTypeLoader().newInstance(type, options);
                }

                private Factory() {
                }
            }

            public static interface Member
            extends XmlToken {
                public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Member.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLCONFIG").resolveHandle("anon1dd3type");
                public static final Enum LOCAL = Enum.forString("##local");
                public static final int INT_LOCAL = 1;

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
                    static final int INT_LOCAL = 1;
                    public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("##local", 1)});
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
    }

    public static interface Member
    extends XmlToken {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Member.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLCONFIG").resolveHandle("anonc6fftype");
        public static final Enum ANY = Enum.forString("##any");
        public static final int INT_ANY = 1;

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
            static final int INT_ANY = 1;
            public static final StringEnumAbstractBase.Table table = new StringEnumAbstractBase.Table(new Enum[]{new Enum("##any", 1)});
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

