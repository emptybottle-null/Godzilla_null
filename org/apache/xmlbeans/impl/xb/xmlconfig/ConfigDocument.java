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
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Usertypeconfig;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface ConfigDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(ConfigDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLCONFIG").resolveHandle("config4185doctype");

    public Config getConfig();

    public void setConfig(Config var1);

    public Config addNewConfig();

    public static final class Factory {
        public static ConfigDocument newInstance() {
            return (ConfigDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static ConfigDocument newInstance(XmlOptions options) {
            return (ConfigDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static ConfigDocument parse(String xmlAsString) throws XmlException {
            return (ConfigDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static ConfigDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (ConfigDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static ConfigDocument parse(File file) throws XmlException, IOException {
            return (ConfigDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static ConfigDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (ConfigDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static ConfigDocument parse(URL u) throws XmlException, IOException {
            return (ConfigDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static ConfigDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (ConfigDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static ConfigDocument parse(InputStream is) throws XmlException, IOException {
            return (ConfigDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static ConfigDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (ConfigDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static ConfigDocument parse(Reader r) throws XmlException, IOException {
            return (ConfigDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static ConfigDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (ConfigDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static ConfigDocument parse(XMLStreamReader sr) throws XmlException {
            return (ConfigDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static ConfigDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (ConfigDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static ConfigDocument parse(Node node) throws XmlException {
            return (ConfigDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static ConfigDocument parse(Node node, XmlOptions options) throws XmlException {
            return (ConfigDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static ConfigDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (ConfigDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static ConfigDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (ConfigDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface Config
    extends XmlObject {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Config.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLCONFIG").resolveHandle("configf467elemtype");

        public Nsconfig[] getNamespaceArray();

        public Nsconfig getNamespaceArray(int var1);

        public int sizeOfNamespaceArray();

        public void setNamespaceArray(Nsconfig[] var1);

        public void setNamespaceArray(int var1, Nsconfig var2);

        public Nsconfig insertNewNamespace(int var1);

        public Nsconfig addNewNamespace();

        public void removeNamespace(int var1);

        public Qnameconfig[] getQnameArray();

        public Qnameconfig getQnameArray(int var1);

        public int sizeOfQnameArray();

        public void setQnameArray(Qnameconfig[] var1);

        public void setQnameArray(int var1, Qnameconfig var2);

        public Qnameconfig insertNewQname(int var1);

        public Qnameconfig addNewQname();

        public void removeQname(int var1);

        public Extensionconfig[] getExtensionArray();

        public Extensionconfig getExtensionArray(int var1);

        public int sizeOfExtensionArray();

        public void setExtensionArray(Extensionconfig[] var1);

        public void setExtensionArray(int var1, Extensionconfig var2);

        public Extensionconfig insertNewExtension(int var1);

        public Extensionconfig addNewExtension();

        public void removeExtension(int var1);

        public Usertypeconfig[] getUsertypeArray();

        public Usertypeconfig getUsertypeArray(int var1);

        public int sizeOfUsertypeArray();

        public void setUsertypeArray(Usertypeconfig[] var1);

        public void setUsertypeArray(int var1, Usertypeconfig var2);

        public Usertypeconfig insertNewUsertype(int var1);

        public Usertypeconfig addNewUsertype();

        public void removeUsertype(int var1);

        public static final class Factory {
            public static Config newInstance() {
                return (Config)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static Config newInstance(XmlOptions options) {
                return (Config)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }
}

