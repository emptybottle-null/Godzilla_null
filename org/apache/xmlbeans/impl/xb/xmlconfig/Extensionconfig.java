/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xmlconfig;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.xb.xmlconfig.JavaNameList;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface Extensionconfig
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Extensionconfig.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLCONFIG").resolveHandle("extensionconfig2ac2type");

    public Interface[] getInterfaceArray();

    public Interface getInterfaceArray(int var1);

    public int sizeOfInterfaceArray();

    public void setInterfaceArray(Interface[] var1);

    public void setInterfaceArray(int var1, Interface var2);

    public Interface insertNewInterface(int var1);

    public Interface addNewInterface();

    public void removeInterface(int var1);

    public PrePostSet getPrePostSet();

    public boolean isSetPrePostSet();

    public void setPrePostSet(PrePostSet var1);

    public PrePostSet addNewPrePostSet();

    public void unsetPrePostSet();

    public Object getFor();

    public JavaNameList xgetFor();

    public boolean isSetFor();

    public void setFor(Object var1);

    public void xsetFor(JavaNameList var1);

    public void unsetFor();

    public static final class Factory {
        public static Extensionconfig newInstance() {
            return (Extensionconfig)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static Extensionconfig newInstance(XmlOptions options) {
            return (Extensionconfig)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static Extensionconfig parse(String xmlAsString) throws XmlException {
            return (Extensionconfig)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static Extensionconfig parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (Extensionconfig)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static Extensionconfig parse(File file) throws XmlException, IOException {
            return (Extensionconfig)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static Extensionconfig parse(File file, XmlOptions options) throws XmlException, IOException {
            return (Extensionconfig)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static Extensionconfig parse(URL u) throws XmlException, IOException {
            return (Extensionconfig)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static Extensionconfig parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (Extensionconfig)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static Extensionconfig parse(InputStream is) throws XmlException, IOException {
            return (Extensionconfig)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static Extensionconfig parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (Extensionconfig)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static Extensionconfig parse(Reader r) throws XmlException, IOException {
            return (Extensionconfig)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static Extensionconfig parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (Extensionconfig)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static Extensionconfig parse(XMLStreamReader sr) throws XmlException {
            return (Extensionconfig)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static Extensionconfig parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (Extensionconfig)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static Extensionconfig parse(Node node) throws XmlException {
            return (Extensionconfig)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static Extensionconfig parse(Node node, XmlOptions options) throws XmlException {
            return (Extensionconfig)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static Extensionconfig parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (Extensionconfig)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static Extensionconfig parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (Extensionconfig)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface PrePostSet
    extends XmlObject {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(PrePostSet.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLCONFIG").resolveHandle("prepostset5c9delemtype");

        public String getStaticHandler();

        public XmlString xgetStaticHandler();

        public void setStaticHandler(String var1);

        public void xsetStaticHandler(XmlString var1);

        public static final class Factory {
            public static PrePostSet newInstance() {
                return (PrePostSet)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static PrePostSet newInstance(XmlOptions options) {
                return (PrePostSet)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }

    public static interface Interface
    extends XmlObject {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Interface.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLCONFIG").resolveHandle("interface02a7elemtype");

        public String getStaticHandler();

        public XmlString xgetStaticHandler();

        public void setStaticHandler(String var1);

        public void xsetStaticHandler(XmlString var1);

        public String getName();

        public XmlString xgetName();

        public boolean isSetName();

        public void setName(String var1);

        public void xsetName(XmlString var1);

        public void unsetName();

        public static final class Factory {
            public static Interface newInstance() {
                return (Interface)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static Interface newInstance(XmlOptions options) {
                return (Interface)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }
}

