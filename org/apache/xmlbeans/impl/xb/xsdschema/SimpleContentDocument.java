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
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleExtensionType;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleRestrictionType;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface SimpleContentDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(SimpleContentDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("simplecontent8acedoctype");

    public SimpleContent getSimpleContent();

    public void setSimpleContent(SimpleContent var1);

    public SimpleContent addNewSimpleContent();

    public static final class Factory {
        public static SimpleContentDocument newInstance() {
            return (SimpleContentDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static SimpleContentDocument newInstance(XmlOptions options) {
            return (SimpleContentDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static SimpleContentDocument parse(String xmlAsString) throws XmlException {
            return (SimpleContentDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static SimpleContentDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (SimpleContentDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static SimpleContentDocument parse(File file) throws XmlException, IOException {
            return (SimpleContentDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static SimpleContentDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (SimpleContentDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static SimpleContentDocument parse(URL u) throws XmlException, IOException {
            return (SimpleContentDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static SimpleContentDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (SimpleContentDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static SimpleContentDocument parse(InputStream is) throws XmlException, IOException {
            return (SimpleContentDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static SimpleContentDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (SimpleContentDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static SimpleContentDocument parse(Reader r) throws XmlException, IOException {
            return (SimpleContentDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static SimpleContentDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (SimpleContentDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static SimpleContentDocument parse(XMLStreamReader sr) throws XmlException {
            return (SimpleContentDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static SimpleContentDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (SimpleContentDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static SimpleContentDocument parse(Node node) throws XmlException {
            return (SimpleContentDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static SimpleContentDocument parse(Node node, XmlOptions options) throws XmlException {
            return (SimpleContentDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static SimpleContentDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (SimpleContentDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static SimpleContentDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (SimpleContentDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface SimpleContent
    extends Annotated {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(SimpleContent.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("simplecontent9a5belemtype");

        public SimpleRestrictionType getRestriction();

        public boolean isSetRestriction();

        public void setRestriction(SimpleRestrictionType var1);

        public SimpleRestrictionType addNewRestriction();

        public void unsetRestriction();

        public SimpleExtensionType getExtension();

        public boolean isSetExtension();

        public void setExtension(SimpleExtensionType var1);

        public SimpleExtensionType addNewExtension();

        public void unsetExtension();

        public static final class Factory {
            public static SimpleContent newInstance() {
                return (SimpleContent)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static SimpleContent newInstance(XmlOptions options) {
                return (SimpleContent)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }
}

