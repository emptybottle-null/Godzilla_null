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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.impl.xb.xsdschema.ComplexRestrictionType;
import org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface ComplexContentDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(ComplexContentDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("complexcontentc57adoctype");

    public ComplexContent getComplexContent();

    public void setComplexContent(ComplexContent var1);

    public ComplexContent addNewComplexContent();

    public static final class Factory {
        public static ComplexContentDocument newInstance() {
            return (ComplexContentDocument)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static ComplexContentDocument newInstance(XmlOptions options) {
            return (ComplexContentDocument)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static ComplexContentDocument parse(String xmlAsString) throws XmlException {
            return (ComplexContentDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static ComplexContentDocument parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (ComplexContentDocument)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static ComplexContentDocument parse(File file) throws XmlException, IOException {
            return (ComplexContentDocument)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static ComplexContentDocument parse(File file, XmlOptions options) throws XmlException, IOException {
            return (ComplexContentDocument)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static ComplexContentDocument parse(URL u) throws XmlException, IOException {
            return (ComplexContentDocument)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static ComplexContentDocument parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (ComplexContentDocument)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static ComplexContentDocument parse(InputStream is) throws XmlException, IOException {
            return (ComplexContentDocument)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static ComplexContentDocument parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (ComplexContentDocument)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static ComplexContentDocument parse(Reader r) throws XmlException, IOException {
            return (ComplexContentDocument)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static ComplexContentDocument parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (ComplexContentDocument)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static ComplexContentDocument parse(XMLStreamReader sr) throws XmlException {
            return (ComplexContentDocument)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static ComplexContentDocument parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (ComplexContentDocument)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static ComplexContentDocument parse(Node node) throws XmlException {
            return (ComplexContentDocument)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static ComplexContentDocument parse(Node node, XmlOptions options) throws XmlException {
            return (ComplexContentDocument)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static ComplexContentDocument parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (ComplexContentDocument)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static ComplexContentDocument parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (ComplexContentDocument)XmlBeans.getContextTypeLoader().parse(xis, type, options);
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

    public static interface ComplexContent
    extends Annotated {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(ComplexContent.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("complexcontentaa7felemtype");

        public ComplexRestrictionType getRestriction();

        public boolean isSetRestriction();

        public void setRestriction(ComplexRestrictionType var1);

        public ComplexRestrictionType addNewRestriction();

        public void unsetRestriction();

        public ExtensionType getExtension();

        public boolean isSetExtension();

        public void setExtension(ExtensionType var1);

        public ExtensionType addNewExtension();

        public void unsetExtension();

        public boolean getMixed();

        public XmlBoolean xgetMixed();

        public boolean isSetMixed();

        public void setMixed(boolean var1);

        public void xsetMixed(XmlBoolean var1);

        public void unsetMixed();

        public static final class Factory {
            public static ComplexContent newInstance() {
                return (ComplexContent)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static ComplexContent newInstance(XmlOptions options) {
                return (ComplexContent)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }
}

