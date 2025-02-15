/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.substwsdl;

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
import org.apache.xmlbeans.impl.xb.substwsdl.TImport;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface DefinitionsDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(DefinitionsDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLTOOLS").resolveHandle("definitionsc7f1doctype");

    public Definitions getDefinitions();

    public void setDefinitions(Definitions var1);

    public Definitions addNewDefinitions();

    public static final class Factory {
        public static DefinitionsDocument newInstance() {
            return (DefinitionsDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static DefinitionsDocument newInstance(XmlOptions options) {
            return (DefinitionsDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static DefinitionsDocument parse(String xmlAsString) throws XmlException {
            return (DefinitionsDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static DefinitionsDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (DefinitionsDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static DefinitionsDocument parse(File file) throws XmlException, IOException {
            return (DefinitionsDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static DefinitionsDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (DefinitionsDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static DefinitionsDocument parse(URL u) throws XmlException, IOException {
            return (DefinitionsDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static DefinitionsDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (DefinitionsDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static DefinitionsDocument parse(InputStream is) throws XmlException, IOException {
            return (DefinitionsDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static DefinitionsDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (DefinitionsDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static DefinitionsDocument parse(Reader r) throws XmlException, IOException {
            return (DefinitionsDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static DefinitionsDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (DefinitionsDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static DefinitionsDocument parse(XMLStreamReader sr) throws XmlException {
            return (DefinitionsDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static DefinitionsDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (DefinitionsDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static DefinitionsDocument parse(Node node) throws XmlException {
            return (DefinitionsDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static DefinitionsDocument parse(Node node, XmlOptions options) throws XmlException {
            return (DefinitionsDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static DefinitionsDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (DefinitionsDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static DefinitionsDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (DefinitionsDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface Definitions
    extends XmlObject {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(Definitions.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLTOOLS").resolveHandle("definitions05ddelemtype");

        public TImport[] getImportArray();

        public TImport getImportArray(int var1);

        public int sizeOfImportArray();

        public void setImportArray(TImport[] var1);

        public void setImportArray(int var1, TImport var2);

        public TImport insertNewImport(int var1);

        public TImport addNewImport();

        public void removeImport(int var1);

        public XmlObject[] getTypesArray();

        public XmlObject getTypesArray(int var1);

        public int sizeOfTypesArray();

        public void setTypesArray(XmlObject[] var1);

        public void setTypesArray(int var1, XmlObject var2);

        public XmlObject insertNewTypes(int var1);

        public XmlObject addNewTypes();

        public void removeTypes(int var1);

        public XmlObject[] getMessageArray();

        public XmlObject getMessageArray(int var1);

        public int sizeOfMessageArray();

        public void setMessageArray(XmlObject[] var1);

        public void setMessageArray(int var1, XmlObject var2);

        public XmlObject insertNewMessage(int var1);

        public XmlObject addNewMessage();

        public void removeMessage(int var1);

        public XmlObject[] getBindingArray();

        public XmlObject getBindingArray(int var1);

        public int sizeOfBindingArray();

        public void setBindingArray(XmlObject[] var1);

        public void setBindingArray(int var1, XmlObject var2);

        public XmlObject insertNewBinding(int var1);

        public XmlObject addNewBinding();

        public void removeBinding(int var1);

        public XmlObject[] getPortTypeArray();

        public XmlObject getPortTypeArray(int var1);

        public int sizeOfPortTypeArray();

        public void setPortTypeArray(XmlObject[] var1);

        public void setPortTypeArray(int var1, XmlObject var2);

        public XmlObject insertNewPortType(int var1);

        public XmlObject addNewPortType();

        public void removePortType(int var1);

        public XmlObject[] getServiceArray();

        public XmlObject getServiceArray(int var1);

        public int sizeOfServiceArray();

        public void setServiceArray(XmlObject[] var1);

        public void setServiceArray(int var1, XmlObject var2);

        public XmlObject insertNewService(int var1);

        public XmlObject addNewService();

        public void removeService(int var1);

        public static final class Factory {
            public static Definitions newInstance() {
                return (Definitions)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static Definitions newInstance(XmlOptions options) {
                return (Definitions)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }
}

